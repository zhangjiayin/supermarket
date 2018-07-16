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
import io.jpress.router.RouterMapping;
import io.jpress.template.TemplateManager;
import io.jpress.template.TplModule;
import io.jpress.ui.freemarker.tag.ContentPageTag;
import io.jpress.ui.freemarker.tag.PlatformContentPageTag;
import io.jpress.utils.StringUtils;

import com.jfinal.render.Render;

@RouterMapping(url = Consts.ROUTER_CONTENT_PLATFORM)
public class PlatformContentController extends BaseFrontController {

	protected TplModule module;
	protected String platformId;
	protected Integer page;

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

		setAttr(Consts.ATTR_PAGE_NUMBER, page);
		setAttr("platformId", platformId);
		setAttr("module", module);


		String order = getPara("order"); 
		if(module.isSupportOrder(order)){
			setAttr(ContentPageTag.TAG_NAME, new PlatformContentPageTag(getRequest(), page, module.getName(), platformId, order));
		}else{
			setAttr(ContentPageTag.TAG_NAME, new PlatformContentPageTag(getRequest(), page, module.getName(), platformId, null));
		}

		
		if (StringUtils.isNotBlank(platformId)) {
			render(String.format("platform_%s.html", module.getName()));
		} 
	}

/*	private void setGlobleAttrs(String platformId) {
		if (StringUtils.isBlank(platformId)) {
			return;
		}

		StringBuffer title = new StringBuffer();
		StringBuffer keywords = new StringBuffer();
		StringBuffer description = new StringBuffer();

		for (Taxonomy taxonomy : taxonomys) {
			title.append(taxonomy.getTitle()).append("-");
			keywords.append(taxonomy.getMetaKeywords()).append(",");
			description.append(taxonomy.getMetaDescription()).append(";");
		}

		title.deleteCharAt(title.length() - 1);
		keywords.deleteCharAt(keywords.length() - 1);
		description.deleteCharAt(description.length() - 1);

		setAttr(Consts.ATTR_GLOBAL_WEB_TITLE, title.toString());
		setAttr(Consts.ATTR_GLOBAL_META_KEYWORDS, keywords.toString());
		setAttr(Consts.ATTR_GLOBAL_META_DESCRIPTION, description.toString());
	}*/



	/**
	 * 分类的url种类： 种类1： http://www.xxx.com/module 该module下的所有内容 种类2：
	 * http://www.xxx.com/module-slug 该module下的slug分类的所有内容 种类3：
	 * http://www.xxx.com/module-slug1-slug2-slug3
	 * 该module下的、slug1、slug2、slug3、slug4的所有内容
	 */
	private void initRequest() {
		String moduleName = getPara(0);
		if (StringUtils.isBlank(moduleName)) {
			renderError(404);
		}

		module = TemplateManager.me().currentTemplateModule(moduleName);

		if (module == null) {
			renderError(404);
		}

		int paraCount = getParaCount();
		if (paraCount == 1) {
			page = 1;
		}

		platformId = getPara(1);
	/*	if (paraCount >= 2) {
			for (int i = 1; i < paraCount; i++) {
				String para = getPara(i);
				if (StringUtils.isNumeric(para)) {
					page = StringUtils.toInt(para, 1);
					if (i != paraCount - 1) {
						renderError(404);
					}
				} else {
					slugStrings = StringUtils.urlDecode(para);
				}
			}
		}*/
/*
		if (StringUtils.isNotBlank(slugStrings)) {
			slugs = slugStrings.split(",");
		}*/
		if(paraCount>=3){
			page = Integer.parseInt(getPara(2));
		}

		if (page == null || page <= 0) {
			page = 1;
		}
	}

	private Render onRenderBefore() {
		return HookInvoker.taxonomyRenderBefore(this);
	}

	private void onRenderAfter() {
		HookInvoker.taxonomyRenderAfter(this);
	}

	private boolean containsChinese(String s){
		  if (null == s || "".equals(s.trim())) return false;
		  for (int i = 0; i < s.length(); i++) {
		    if (isChinese(s.charAt(i))) return true;
		  }
		  return false;
	}
	private boolean isChinese(char a) { 
	     int v = (int)a; 
	     return (v >=19968 && v <= 171941); 
	}
	
}
