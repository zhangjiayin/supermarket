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
package io.jpress.front.controller;

import io.jpress.Consts;
import io.jpress.core.BaseFrontController;
import io.jpress.core.addon.HookInvoker;
import io.jpress.core.cache.ActionCache;
import io.jpress.model.OrgInfo;
import io.jpress.model.Platform;
import io.jpress.model.Taxonomy;
import io.jpress.model.query.ContentQuery;
import io.jpress.model.query.OrgInfoQuery;
import io.jpress.model.query.PlatformQuery;
import io.jpress.router.RouterMapping;
import io.jpress.template.TemplateManager;
import io.jpress.template.TplModule;
import io.jpress.ui.freemarker.tag.NextContentTag;
import io.jpress.ui.freemarker.tag.NextPlatformTag;
import io.jpress.ui.freemarker.tag.PreviousContentTag;
import io.jpress.ui.freemarker.tag.PreviousPlatformTag;
import io.jpress.utils.StringUtils;

import java.math.BigInteger;
import java.util.List;

import com.jfinal.render.Render;

@RouterMapping(url = Consts.ROUTER_PLATFORM)
public class PlatformController extends BaseFrontController {

	private String slug;
	private BigInteger id;
	private int page;

	@ActionCache
	public void index() {
		try {
			Render render = onRenderBefore();
			if (render != null) {
				render(render);
			} else {
				doRender();
			}
		} finally {
			onRenderAfter();
		}

	}

	private void doRender() {
		initRequest();

		Platform platform = queryPlatform();
		if (null == platform) {
			renderError(404);
			return;
		}

		TplModule module = TemplateManager.me().currentTemplateModule(platform.getModule());

		if (module == null) {
			renderError(404);
			return;
		}

		updateContentViewCount(platform);
		setGlobleAttrs(platform);
		

		setAttr("p", page);
		setAttr("platform", platform);//后续调整页面
		setAttr("news", ContentQuery.me().queryContentByPlatformId(String.valueOf(platform.getId())));//后续调整页面
		List<OrgInfo> orgInfos = OrgInfoQuery.me().queryHotOrgInfos(4);
		setAttr("orgInfos", orgInfos);//热门平台推荐

		setAttr(NextContentTag.TAG_NAME, new NextPlatformTag(platform));
		setAttr(PreviousContentTag.TAG_NAME, new PreviousPlatformTag(platform));

		//setAttr(CommentPageTag.TAG_NAME, new CommentPageTag(getRequest(), platform, page));

		/*List<Taxonomy> taxonomys = TaxonomyQuery.me().findListByContentId(platform.getId());
		setAttr("taxonomys", taxonomys);*/
		//setAttr(MenusTag.TAG_NAME, new MenusTag(getRequest(), taxonomys, platform));

		String style = platform.getStyle();
		if (StringUtils.isNotBlank(style)) {
			render(String.format("content_%s_%s.html", module.getName(), style.trim()));
			return;
		}

		//style = tryGetTaxonomyTemplate(module, taxonomys);
		if (style != null) {
			render(String.format("content_%s_%s.html", module.getName(), style));
			return;
		}

		render(String.format("content_%s.html", module.getName()));

	}

	@SuppressWarnings("unused")
	private String tryGetTaxonomyTemplate(TplModule module, List<Taxonomy> taxonomys) {
		if (taxonomys == null || taxonomys.isEmpty())
			return null;
		String forSlug = null;
		for (Taxonomy taxonomy : taxonomys) {
			String tFile = String.format("content_%s_%s%s.html", module.getName(), Consts.TAXONOMY_TEMPLATE_PREFIX,taxonomy.getSlug());
			if (templateExists(tFile)) {
				if (forSlug == null) {
					forSlug = Consts.TAXONOMY_TEMPLATE_PREFIX + taxonomy.getSlug();
				} else {
					forSlug = null;
					break;
				}
			}
		}
		return forSlug;
	}

	private void updateContentViewCount(Platform platform) {
		long visitorCount = VisitorCounter.getVisitorCount(platform.getId());
		Long viewCount = platform.getViewCount() == null ? visitorCount : platform.getViewCount() + visitorCount;
		platform.setViewCount(viewCount);
		if (platform.update()) {
			VisitorCounter.clearVisitorCount(platform.getId());
		}
	}

	private void setGlobleAttrs(Platform platform) {

		setAttr(Consts.ATTR_GLOBAL_WEB_TITLE, platform.getName());

		if (StringUtils.isNotBlank(platform.getMetaKeywords())) {
			setAttr(Consts.ATTR_GLOBAL_META_KEYWORDS, platform.getMetaKeywords());
		} 

		if (StringUtils.isNotBlank(platform.getMetaDescription())) {
			setAttr(Consts.ATTR_GLOBAL_META_DESCRIPTION, platform.getMetaDescription());
		} else {
			setAttr(Consts.ATTR_GLOBAL_META_DESCRIPTION, platform.getSummary());
		}

	}

	private Platform queryPlatform() {
		if (id != null) {
			return PlatformQuery.me().findById(id);
		} else {
			return PlatformQuery.me().findBySlug(StringUtils.urlDecode(slug));
		}
	}

	private void initRequest() {
		String para = getPara(0);
		if (StringUtils.isBlank(para)) {
			id = getParaToBigInteger("id");
			slug = getPara("slug");
			page = getParaToInt("p", 1);
			if (id == null && slug == null) {
				renderError(404);
			}
			return;
		}

		if (StringUtils.isNumeric(para)) {
			id = new BigInteger(para);
		} else {
			slug = para;
		}
		page = getParaToInt(1, 1);

	}

	private Render onRenderBefore() {
		return HookInvoker.contentRenderBefore(this);
	}

	private void onRenderAfter() {
		HookInvoker.contentRenderAfter(this);
	}

}
