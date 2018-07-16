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

import io.jpress.core.render.freemarker.JTag;
import io.jpress.model.Platform;
import io.jpress.model.query.PlatformQuery;

public class PreviousPlatformTag extends JTag {
	
	public static final String TAG_NAME = "jp.previous";

	private Platform currentPlatform;

	public PreviousPlatformTag(Platform platform) {
		this.currentPlatform = platform;
	}

	@Override
	public void onRender() {

		Platform platform = PlatformQuery.me().findPrevious(currentPlatform);

		if (platform == null) {
			renderText("");
			return;
		}

		setVariable("previous", platform);
		renderBody();
	}

}
