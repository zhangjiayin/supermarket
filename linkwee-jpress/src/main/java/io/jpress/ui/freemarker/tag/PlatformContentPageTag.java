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
package io.jpress.ui.freemarker.tag;

import io.jpress.core.render.freemarker.BasePaginateTag;
import io.jpress.core.render.freemarker.JTag;
import io.jpress.model.Content;
import io.jpress.model.query.ContentQuery;
import io.jpress.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.Page;

public class PlatformContentPageTag extends JTag {

	public static final String TAG_NAME = "jp.contentPage";	

	int pageNumber;
	String moduleName;
	String orderBy;
	String platformId;
	HttpServletRequest request;

	public PlatformContentPageTag(HttpServletRequest request, int pageNumber, String moduleName, String platformId,
			String orderBy) {
		this.request = request;
		this.pageNumber = pageNumber;
		this.moduleName = moduleName;
		this.platformId = platformId;
		this.orderBy = orderBy;
	}

	@Override
	public void onRender() {

		int pagesize = getParamToInt("pageSize", 10);
		orderBy = StringUtils.isBlank(orderBy) ? getParam("orderBy") : orderBy;

		//BigInteger[] taxonomyIds = null;


		Page<Content> page = ContentQuery.me().paginateInNormalByPid(pageNumber, pagesize, moduleName, platformId, orderBy);
		setVariable("page", page);
		setVariable("contents", page.getList());

		PlatformContentPaginateTag pagination = new PlatformContentPaginateTag(request, page, moduleName, platformId);
		setVariable("pagination", pagination);

		renderBody();
	}

	public static class PlatformContentPaginateTag extends BasePaginateTag {

		final String moduleName;
		final String platformId;
		final HttpServletRequest request;

		public PlatformContentPaginateTag(HttpServletRequest request, Page<Content> page, String moduleName,
				String platformId) {
			super(page);
			this.request = request;
			this.moduleName = moduleName;
			this.platformId = platformId;

		}

		@Override
		protected String getUrl(int pageNumber) {
			String url = JFinal.me().getContextPath() + "/" + moduleName + "-";
			if (StringUtils.isNotBlank(platformId)) {
				url = url.substring(0, url.length() - 1);
				url += "-" + pageNumber;
			} else {
				url += pageNumber;
			}

			if (enalbleFakeStatic()) {
				url += getFakeStaticSuffix();
			}

			String queryString = request.getQueryString();
			if (StringUtils.isNotBlank(queryString)) {
				url += "?" + queryString;
			}

			if (StringUtils.isNotBlank(getAnchor())) {
				url += "#" + getAnchor();
			}
			return url;
		}

	}

}
