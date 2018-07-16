/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 *
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.admin.controller;

import io.jpress.Consts;
import io.jpress.core.JBaseCRUDController;
import io.jpress.core.interceptor.ActionCacheClearInterceptor;
import io.jpress.core.render.AjaxResult;
import io.jpress.interceptor.UCodeInterceptor;
import io.jpress.message.Actions;
import io.jpress.message.MessageKit;
import io.jpress.model.Platform;
import io.jpress.model.Taxonomy;
import io.jpress.model.User;
import io.jpress.model.query.MappingQuery;
import io.jpress.model.query.PlatformQuery;
import io.jpress.model.query.TaxonomyQuery;
import io.jpress.model.query.UserQuery;
import io.jpress.model.utils.PlatformRouter;
import io.jpress.router.RouterMapping;
import io.jpress.router.RouterNotAllowConvert;
import io.jpress.template.TemplateManager;
import io.jpress.template.TplModule;
import io.jpress.template.TplTaxonomyType;
import io.jpress.utils.JsoupUtils;
import io.jpress.utils.StringUtils;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;

@RouterMapping(url = "/admin/platform", viewPath = "/WEB-INF/admin/platform")
@Before(ActionCacheClearInterceptor.class)
@RouterNotAllowConvert
public class _PlatformController extends JBaseCRUDController<Platform> {

	private String getModuleName() {
		return getPara("m");
	}

	private String getStatus() {
		return getPara("s");
	}

	@Override
	public void index() {

		TplModule module = TemplateManager.me().currentTemplateModule(getModuleName());
		setAttr("module", module);
		setAttr("delete_count", PlatformQuery.me().findCountByModuleAndStatus(Platform.STATUS_DELETE));
		setAttr("draft_count", PlatformQuery.me().findCountByModuleAndStatus( Platform.STATUS_DRAFT));
		setAttr("normal_count", PlatformQuery.me().findCountByModuleAndStatus(Platform.STATUS_NORMAL));
		setAttr("count", PlatformQuery.me().findCountInNormalByModule());

		setAttr("tids", getPara("tids"));
		BigInteger[] tids = null;
		String[] tidStrings = getPara("tids", "").split(",");

		List<BigInteger> tidList = new ArrayList<BigInteger>();
		for (String stringid : tidStrings) {
			if (StringUtils.isNotBlank(stringid)) {
				tidList.add(new BigInteger(stringid));
			}
		}
		tids = tidList.toArray(new BigInteger[] {});

		String keyword = getPara("k", "").trim();

		Page<Platform> page = null;
		if (StringUtils.isNotBlank(getStatus())) {
			page = PlatformQuery.me().paginateBySearch(getPageNumber(), getPageSize(), getModuleName(), keyword,
					getStatus(), tids, null);
		} else {
			page = PlatformQuery.me().paginateByModuleNotInDelete(getPageNumber(), getPageSize(), getModuleName(),
					keyword, tids, null);
		}

		filterUI(tids);

		setAttr("page", page);

		String templateHtml = String.format("admin_content_index_%s.html", module.getName());
		for (int i = 0; i < 2; i++) {
			if (TemplateManager.me().existsFile(templateHtml)) {
				setAttr("include", TemplateManager.me().currentTemplatePath() + "/" + templateHtml);
				return;
			}
			templateHtml = templateHtml.substring(0, templateHtml.lastIndexOf("_")) + ".html";
		}
		setAttr("include", "_index_include.html");

	}

	private void filterUI(BigInteger[] tids) {
		TplModule module = TemplateManager.me().currentTemplateModule(getModuleName());

		if (module == null) {
			return;
		}

		List<TplTaxonomyType> types = module.getTaxonomyTypes();
		if (types != null && !types.isEmpty()) {
			HashMap<String, List<Taxonomy>> _taxonomyMap = new HashMap<String, List<Taxonomy>>();

			for (TplTaxonomyType type : types) {
				// 排除标签类的分类删选
				if (TplTaxonomyType.TYPE_SELECT.equals(type.getFormType())) {
					List<Taxonomy> taxonomys = TaxonomyQuery.me().findListByModuleAndTypeAsSort(getModuleName(),
							type.getName());
					processSelected(tids, taxonomys);
					_taxonomyMap.put(type.getTitle(), taxonomys);
				}
			}

			setAttr("_taxonomyMap", _taxonomyMap);
		}
	}

	private void processSelected(BigInteger[] tids, List<Taxonomy> taxonomys) {
		if (taxonomys == null || taxonomys.isEmpty()) {
			return;
		}
		if (tids == null || tids.length == 0) {
			return;
		}
		for (Taxonomy t : taxonomys) {
			for (BigInteger id : tids) {
				if (t.getId().compareTo(id) == 0) {
					t.put("_selected", "selected=\"selected\"");
				}
			}
		}
	}

	@Before(UCodeInterceptor.class)
	public void trash() {
		Platform platform = PlatformQuery.me().findById(getParaToBigInteger("id"));
		if (platform != null) {
			platform.setStatus(Platform.STATUS_DELETE);
			platform.saveOrUpdate();
			renderAjaxResultForSuccess("success");
		} else {
			renderAjaxResultForError("trash error!");
		}
	}

	@Before(UCodeInterceptor.class)
	public void draft() {
		Platform platform = PlatformQuery.me().findById(getParaToBigInteger("id"));
		if (platform != null) {
			platform.setStatus(Platform.STATUS_DRAFT);
			platform.saveOrUpdate();
			renderAjaxResultForSuccess("success");
		} else {
			renderAjaxResultForError("trash error!");
		}
	}

	@Before(UCodeInterceptor.class)
	public void batchTrash() {
		BigInteger[] ids = getParaValuesToBigInteger("dataItem");
		int count = PlatformQuery.me().batchTrash(ids);
		if (count > 0) {
			renderAjaxResultForSuccess("success");
		} else {
			renderAjaxResultForError("trash error!");
		}
	}

	@Before(UCodeInterceptor.class)
	public void batchDelete() {
		BigInteger[] ids = getParaValuesToBigInteger("dataItem");
		int count = PlatformQuery.me().batchDelete(ids);
		if (count > 0) {
			renderAjaxResultForSuccess("success");
		} else {
			renderAjaxResultForError("trash error!");
		}
	}

	@Before(UCodeInterceptor.class)
	public void restore() {
		BigInteger id = getParaToBigInteger("id");
		Platform platform = PlatformQuery.me().findById(id);
		if (platform != null && platform.isDelete()) {
			platform.setStatus(Platform.STATUS_DRAFT);
			platform.setModified(new Date());
			platform.saveOrUpdate();
			renderAjaxResultForSuccess("success");
		} else {
			renderAjaxResultForError("restore error!");
		}
	}

	@Before(UCodeInterceptor.class)
	public void delete() {
		BigInteger id = getParaToBigInteger("id");
		final Platform platform = PlatformQuery.me().findById(id);
		if (platform == null) {
			renderAjaxResultForError();
			return;
		}
		boolean isSuccess = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				if (platform.delete()) {
					MappingQuery.me().deleteByContentId(platform.getId());
					return true;
				}
				return false;
			}
		});

		if (isSuccess) {
			renderAjaxResultForSuccess();
			return;
		}
		renderAjaxResultForError();
	}

	@Override
	public void edit() {

		String moduleName = getModuleName();
		BigInteger contentId = getParaToBigInteger("id");

		Platform platform = PlatformQuery.me().findById(contentId);
		if (platform != null) {
			setAttr("platform", platform);
			moduleName = platform.getModule();
		}

		TplModule module = TemplateManager.me().currentTemplateModule(moduleName);
		setAttr("module", module);

		String _editor = getCookie("_editor", "tinymce");
		setAttr("_editor", _editor);

		//setAttr("urlPreffix", PlatformRouter.getContentRouterPreffix(module));
		//setAttr("urlSuffix", PlatformRouter.getContentRouterSuffix(moduleName));
		setAttr("urlPreffix", PlatformRouter.getContentRouterPreffix(module));
		setAttr("urlSuffix", PlatformRouter.getContentRouterSuffix(moduleName));

		setSlugInputDisplay(moduleName);

		String templateHtml = String.format("admin_content_edit_%s.html", moduleName);
		for (int i = 0; i < 2; i++) {
			if (TemplateManager.me().existsFile(templateHtml)) {
				setAttr("include", TemplateManager.me().currentTemplatePath() + "/" + templateHtml);
				return;
			}
			templateHtml = templateHtml.substring(0, templateHtml.lastIndexOf("_")) + ".html";
		}

		setAttr("include", "_edit_include.html");
	}

	private void setSlugInputDisplay(String moduleName) {
		if (Consts.MODULE_PAGE.equals(moduleName)) {
			setAttr("slugDisplay", "true");
			return;
		}

		String routerType = PlatformRouter.getRouterType();
		if (StringUtils.isBlank(routerType)) { // 没设置过，默认id
			return;
		}

		if (PlatformRouter.TYPE_DYNAMIC_ID.equals(routerType) || PlatformRouter.TYPE_STATIC_MODULE_ID.equals(routerType)
				|| PlatformRouter.TYPE_STATIC_DATE_ID.equals(routerType)
				|| PlatformRouter.TYPE_STATIC_PREFIX_ID.equals(routerType)) {
			return;
		}
		setAttr("slugDisplay", "true");
	}

	public void changeEditor() {
		String name = getPara();
		setCookie("_editor", name, Integer.MAX_VALUE);
		renderAjaxResultForSuccess();
	}

	public List<BigInteger> getOrCreateTaxonomyIds(String moduleName) {
		TplModule module = TemplateManager.me().currentTemplateModule(moduleName);
		List<TplTaxonomyType> types = module.getTaxonomyTypes();
		List<BigInteger> tIds = new ArrayList<BigInteger>();
		for (TplTaxonomyType type : types) {
			if (type.isInputType()) {
				String slugsData = getPara("_" + type.getName());
				if (StringUtils.isBlank(slugsData)) {
					continue;
				}

				List<Taxonomy> taxonomyList = TaxonomyQuery.me().findListByModuleAndType(moduleName, type.getName());

				String[] slugs = slugsData.split(",");
				for (String slug : slugs) {
					BigInteger id = getTaxonomyIdFromListBySlug(slug, taxonomyList);
					if (id == null) {
						Taxonomy taxonomy = new Taxonomy();
						taxonomy.setTitle(slug);
						taxonomy.setSlug(slug);
						taxonomy.setContentModule(moduleName);
						taxonomy.setType(type.getName());
						if (taxonomy.save()) {
							id = taxonomy.getId();
						}
					}
					tIds.add(id);
				}
			} else if (type.isSelectType()) {
				BigInteger[] ids = getParaValuesToBigInteger("_" + type.getName());
				if (ids != null && ids.length > 0)
					tIds.addAll(Arrays.asList(ids));
			}
		}
		return tIds;
	}

	private BigInteger getTaxonomyIdFromListBySlug(String slug, List<Taxonomy> list) {
		for (Taxonomy taxonomy : list) {
			if (slug.equals(taxonomy.getSlug()))
				return taxonomy.getId();
		}
		return null;
	}

	@Before(UCodeInterceptor.class)
	@Override
	public void save() {

		final Map<String, String> metas = getMetas();
		final Platform platform = getPlatform();

		if (StringUtils.isBlank(platform.getName())) {
			renderAjaxResultForError("平台标题不能为空！");
			return;
		}
		
		if (platform.getMinProfit() == null ) {
			renderAjaxResultForError("年化收益区间不能为空！");
			return;
		}
		
		if (platform.getMaxProfit() == null) {
			renderAjaxResultForError("年化收益区间不能为空！");
			return;
		}
		
		if (platform.getMinDeadLine() == null) {
			renderAjaxResultForError("产品期限不能为空！");
			return;
		}
		
		if (platform.getMaxDeadLine() == null) {
			renderAjaxResultForError("产品期限不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getDeadLineSelfDefined())) {
			renderAjaxResultForError("产品期限自定义显示不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getCapital())) {
			renderAjaxResultForError("注册资本不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getTrusteeship())) {
			renderAjaxResultForError("资金托管说明不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getBidSecurity())) {
			renderAjaxResultForError("投标保障不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getSecurityModel())) {
			renderAjaxResultForError("保障模式不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getCity())) {
			renderAjaxResultForError("所在城市不能为空！");
			return;
		}
		
		if (platform.getUpTime() == null) {
			renderAjaxResultForError("上线时间不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getBriefIntroduction())) {
			renderAjaxResultForError("平台简介不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getTeamIntroduction())) {
			renderAjaxResultForError("团队介绍不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getSiteRecord())) {
			renderAjaxResultForError("网站备案不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getContactUs())) {
			renderAjaxResultForError("网贷平台联系方式不能为空！");
			return;
		}
		
		if (StringUtils.isBlank(platform.getDetailImg())) {
			renderAjaxResultForError("请选择网贷平台缩略图");
			return;
		}

		String slug = StringUtils.isBlank(platform.getSlug()) ? platform.getName() : platform.getSlug();
		platform.setSlug(slug);

		String username = getPara("username");
		if (StringUtils.isNotBlank(username)) {
			User user = UserQuery.me().findUserByUsername(username);
			if (user == null) {
				renderAjaxResultForError("系统没有该用户：" + username);
				return;
			}
			platform.setUserId(user.getId());
		}

		Platform dbPlatform = PlatformQuery.me().findBySlug(platform.getSlug());
		if (dbPlatform != null && platform.getId() != null && dbPlatform.getId().compareTo(platform.getId()) != 0) {
			renderAjaxResultForError();
			return;
		}

		boolean saved = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {

				Platform oldPlatform = null;
				if (platform.getId() != null) {
					oldPlatform = PlatformQuery.me().findById(platform.getId());
				}
				
				oldPlatform = PlatformQuery.me().findByName(platform.getName(),1);
				if(oldPlatform != null ){
					platform.setId(oldPlatform.getId());
				}
				if (!platform.saveOrUpdate()) {
					return false;
				}

				//platform.updateCommentCount();

				List<BigInteger> ids = getOrCreateTaxonomyIds(platform.getModule());
				if (ids == null || ids.size() == 0) {
					MappingQuery.me().deleteByContentId(platform.getId());
				} else {
					if (!MappingQuery.me().doBatchUpdate(platform.getId(), ids.toArray(new BigInteger[0]))) {
						return false;
					}
				}

				if (metas != null) {
					for (Map.Entry<String, String> entry : metas.entrySet()) {
						platform.saveOrUpdateMetadta(entry.getKey(), entry.getValue());
					}
				}

				MessageKit.sendMessage(Actions.CONTENT_COUNT_UPDATE, ids.toArray(new BigInteger[] {}));

				return true;
			}
		});

		if (!saved) {
			renderAjaxResultForError();
			return;
		}

		AjaxResult ar = new AjaxResult();
		ar.setErrorCode(0);
		ar.setData(platform.getId());
		renderAjaxResult("save ok", 0, platform.getId());
	}

	private Platform getPlatform() {
		Platform platform = getModel(Platform.class);

		platform.setBriefIntroduction(JsoupUtils.getBodyHtml(platform.getBriefIntroduction()));
		platform.setStatus(Platform.STATUS_NORMAL);

		if (platform.getCreated() == null) {
			platform.setCreated(new Date());
		}
		platform.setModified(new Date());

		User user = getLoginedUser();
		platform.setUserId(user.getId());

		return platform;
	}

}
