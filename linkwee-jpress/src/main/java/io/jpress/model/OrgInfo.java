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
package io.jpress.model;

import io.jpress.model.ModelSorter.ISortModel;
import io.jpress.model.base.BaseOrgInfo;
import io.jpress.model.core.Table;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

@Table(tableName = "tcim_org_info", primaryKey = "id")
public class OrgInfo extends BaseOrgInfo<OrgInfo> implements ISortModel<OrgInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public <T> T getFromListCache(Object key, IDataLoader dataloader) {
		Set<String> inCacheKeys = CacheKit.get(CACHE_NAME, "cachekeys");

		Set<String> cacheKeyList = new HashSet<String>();
		if (inCacheKeys != null) {
			cacheKeyList.addAll(inCacheKeys);
		}

		cacheKeyList.add(key.toString());
		CacheKit.put(CACHE_NAME, "cachekeys", cacheKeyList);

		return CacheKit.get("content_list", key, dataloader);
	}

	public void clearList() {
		Set<String> list = CacheKit.get(CACHE_NAME, "cachekeys");
		if (list != null && list.size() > 0) {
			for (String key : list) {
				if (!key.startsWith("module:")) {
					CacheKit.remove("content_list", key);
					continue;
				}

				// 不清除其他模型的内容
				if (key.startsWith("module:" + getModule())) {
					CacheKit.remove("content_list", key);
				}
			}
		}
	}

	@Override
	public boolean update() {
		removeCache(getId());

		clearList();

		return super.update();
	}

	@Override
	public boolean delete() {
		
		removeCache(getId());

		clearList();

		return super.delete();
	}

	@Override
	public boolean save() {
		removeCache(getId());

		clearList();

		return super.save();
	}



	public String getUsername() {
		return get("username");
	}

	public String getNickame() {
		return get("nickname");
	}





/*	public List<Metadata> getMetadatas() {
		if (metadatas == null) {
			metadatas = MetaDataQuery.me().findListByTypeAndId(METADATA_TYPE, getId());
		}
		return metadatas;
	}

	public void setMetadatas(List<Metadata> metadatas) {
		this.metadatas = metadatas;
	}*/
















	
	public String getModule(){
		return "platform";
	}

@Override
public void setLayer(int layer) {
	// TODO Auto-generated method stub
	
}

@Override
public BigInteger getParentId() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setParent(OrgInfo parent) {
	// TODO Auto-generated method stub
	
}

@Override
public OrgInfo getParent() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void addChild(OrgInfo child) {
	// TODO Auto-generated method stub
	
}

@Override
public List<OrgInfo> getChildList() {
	// TODO Auto-generated method stub
	return null;
}


}
