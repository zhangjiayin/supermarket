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
package io.jpress.model.base;

import io.jpress.message.MessageKit;
import io.jpress.model.Metadata;
import io.jpress.model.core.JModel;
import io.jpress.model.query.MetaDataQuery;
import java.math.BigInteger;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.IDataLoader;

/**
 *  Auto generated by JPress, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseOrgInfo<M extends BaseOrgInfo<M>> extends JModel<M> implements IBean {

	public static final String CACHE_NAME = "orgInfo";
	public static final String METADATA_TYPE = "orgInfo";

	public static final String ACTION_ADD = "orgInfo:add";
	public static final String ACTION_DELETE = "orgInfo:delete";
	public static final String ACTION_UPDATE = "orgInfo:update";

	public void removeCache(Object key){
		if(key == null) return;
		CacheKit.remove(CACHE_NAME, key);
	}

	public void putCache(Object key,Object value){
		CacheKit.put(CACHE_NAME, key, value);
	}

	public M getCache(Object key){
		return CacheKit.get(CACHE_NAME, key);
	}

	public M getCache(Object key,IDataLoader dataloader){
		return CacheKit.get(CACHE_NAME, key, dataloader);
	}

	public Metadata createMetadata(){
		Metadata md = new Metadata();
		md.setObjectId(getId());
		md.setObjectType(METADATA_TYPE);
		return md;
	}

	public Metadata createMetadata(String key,String value){
		Metadata md = new Metadata();
		md.setObjectId(getId());
		md.setObjectType(METADATA_TYPE);
		md.setMetaKey(key);
		md.setMetaValue(value);
		return md;
	}

	public boolean saveOrUpdateMetadta(String key,String value){
		Metadata metadata = MetaDataQuery.me().findByTypeAndIdAndKey(METADATA_TYPE, getId(), key);
		if (metadata == null) {
			metadata = createMetadata(key, value);
			return metadata.save();
		}
		metadata.setMetaValue(value);
		return metadata.update();
	}

	public String metadata(String key) {
		Metadata m = MetaDataQuery.me().findByTypeAndIdAndKey(METADATA_TYPE, getId(), key);
		if (m != null) {
			return m.getMetaValue();
		}
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null){ return false; }
		if(!(o instanceof BaseOrgInfo<?>)){return false;}

		BaseOrgInfo<?> m = (BaseOrgInfo<?>) o;
		if(m.getId() == null){return false;}

		return m.getId().compareTo(this.getId()) == 0;
	}

	@Override
	public boolean save() {
		boolean saved = super.save();
		if (saved) { MessageKit.sendMessage(ACTION_ADD, this); }
		return saved;
	}

	@Override
	public boolean delete() {
		boolean deleted = super.delete();
		if (deleted) { MessageKit.sendMessage(ACTION_DELETE, this); }
		return deleted;
	}

	@Override
	public boolean deleteById(Object idValue) {
		boolean deleted = super.deleteById(idValue);
		if (deleted) { MessageKit.sendMessage(ACTION_DELETE, this); }
		return deleted;
	}

	@Override
	public boolean update() {
		boolean update = super.update();
		if (update) { MessageKit.sendMessage(ACTION_UPDATE, this); }
		return update;
	}

	public void setId(java.math.BigInteger id) {
		set("id", id);
	}

	public java.math.BigInteger getId() {
		Object id = get("id");
		if (id == null)
			return null;

		return id instanceof BigInteger ? (BigInteger)id : new BigInteger(id.toString());
	}

	public void setOrgNumber(java.lang.String orgNumber) {
		set("org_number", orgNumber);
	}

	public java.lang.String getOrgNumber() {
		return get("org_number");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setOrgAccount(java.lang.String orgAccount) {
		set("org_account", orgAccount);
	}

	public java.lang.String getOrgAccount() {
		return get("org_account");
	}

	public void setContext(java.lang.String context) {
		set("context", context);
	}

	public java.lang.String getContext() {
		return get("context");
	}

	public void setCapital(java.lang.String capital) {
		set("capital", capital);
	}

	public java.lang.String getCapital() {
		return get("capital");
	}
	public void setUpTime(java.util.Date upTime) {
		set("up_time", upTime);
	}

	public java.util.Date getUpTime() {
		return get("up_time");
	}
	public void setCity(java.lang.String city) {
		set("city", city);
	}

	public java.lang.String getCity() {
		return get("city");
	}
	
	public void setIcpFiling(java.lang.String icpFiling) {
		set("icp_filing", icpFiling);
	}

	public java.lang.String getIcpFiling() {
		return get("icp_filing");
	}
	
	
	public void setRepresentative(java.lang.String representative) {
		set("representative", representative);
	}

	public java.lang.String getRepresentative() {
		return get("representative");
	}
	
	public void setContact(java.lang.String contact) {
		set("contact", contact);
	}

	public java.lang.String getContact() {
		return get("contact");
	}
	
	public void setPlatformIco(java.lang.String platformIco) {
		set("platform_ico", platformIco);
	}

	public java.lang.String getPlatformIco() {
		return get("platform_ico");
	}
	public void setPlatformlistIco(java.lang.String platformlistIco) {
		set("platformlist_ico", platformlistIco);
	}

	public java.lang.String getPlatformlistIco() {
		return get("platformlist_ico");
	}
	public void setPlatformDetailImg(java.lang.String platformDetailImg) {
		set("platform_detail_img", platformDetailImg);
	}

	public java.lang.String getPlatformDetailImg() {
		return get("platform_detail_img");
	}
	
	public void setPcPlatformImg(java.lang.String pcPlatformImg) {
		set("pc_platform_img", pcPlatformImg);
	}

	public java.lang.String getPcPlatformImg() {
		return get("pc_platform_img");
	}
	
	public void setPcPlatformListImg(java.lang.String pcPlatformListImg) {
		set("pc_platform_list_img", pcPlatformListImg);
	}

	public java.lang.String getPcPlatformListImg() {
		return get("pc_platform_list_img");
	}
	
	public void setOrgNewestImg(java.lang.String orgNewestImg) {
		set("org_newest_img", orgNewestImg);
	}

	public java.lang.String getOrgNewestImg() {
		return get("org_newest_img");
	}
	
	public void setBusinessLicense(java.lang.String businessLicense) {
		set("business_license", businessLicense);
	}

	public java.lang.String getBusinessLicense() {
		return get("business_license");
	}
	
	public void setGrade(java.lang.String grade) {
		set("grade", grade);
	}

	public java.lang.String getGrade() {
		return get("grade");
	}
	public void setStatus(int Status) {
		set("status", Status);
	}

	public int getStatus() {
		return get("status");
	}
	
	public void setOrgGrayStatus(int orgGrayStatus) {
		set("org_gray_status", orgGrayStatus);
	}

	public int getOrgGrayStatus() {
		return get("org_gray_status");
	}
	
	public void setUsableProductAcount(java.lang.Long usableProductAcount) {
		set("usable_product_acount", usableProductAcount);
	}

	public int getUsableProductAcount() {
		return get("usable_product_acount");
	}
	


}