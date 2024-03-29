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
public abstract class BasePlatform<M extends BasePlatform<M>> extends JModel<M> implements IBean {

	public static final String CACHE_NAME = "platform";
	public static final String METADATA_TYPE = "platform";

	public static final String ACTION_ADD = "platform:add";
	public static final String ACTION_DELETE = "platform:delete";
	public static final String ACTION_UPDATE = "platform:update";

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
		if(!(o instanceof BasePlatform<?>)){return false;}

		BasePlatform<?> m = (BasePlatform<?>) o;
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

	public void setName(java.lang.String title) {
		set("name", title);
	}

	public java.lang.String getName() {
		return get("name");
	}
	
	public void setSummary(java.lang.String summary) {
		set("summary", summary);
	}

	public java.lang.String getSummary() {
		return get("summary");
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

	public void setContext(java.lang.String context) {
		set("context", context);
	}

	public java.lang.String getContext() {
		return get("context");
	}

	public void setTrusteeship(java.lang.String trusteeship) {
		set("trusteeship", trusteeship);
	}
	
	public java.lang.String getTrusteeship() {
		return get("trusteeship");
	}

	public java.lang.String getSecurityModel() {
		return get("security_model");
	}

	public void setSecurityModel(java.lang.String securityModel) {
		set("security_model", securityModel);
	}

	public java.lang.String getBidSecurity() {
		return get("bid_security");
	}

	public void setBidSecurity(java.lang.String bidSecurity) {
		set("bid_security", bidSecurity);
	}

	public java.lang.Boolean getIsTransfer() {
		return get("is_transfer");
	}

	public void setIsTransfer(java.lang.Boolean isTransfer) {
		set("is_transfer", isTransfer);
	}

	public java.lang.Integer getGrade() {
		return get("grade");
	}
	
	public void setGrade(java.lang.Integer grade) {
		set("grade", grade);
	}
	
	public java.lang.String getGradeStr() {
		int grade = get("grade");
		String gradeStr = "";
		switch (grade) {
		case 1:
			gradeStr = "B";
			break;
		case 2:
			gradeStr = "BB";
			break;
		case 3:
			gradeStr = "BBB";
			break;
		case 4:
			gradeStr = "A";
			break;
		case 5:
			gradeStr = "AA";
			break;
		case 6:
			gradeStr = "AAA";
			break;
		default:
			break;
		}
		return gradeStr;
	}
	

	public void setMinProfit(java.math.BigDecimal minProfit) {
		set("min_profit", minProfit);
	}

	public java.math.BigDecimal getMinProfit() {
		return get("min_profit");
	}

	public void setMaxProfit(java.math.BigDecimal maxProfit) {
		set("max_profit", maxProfit);
	}

	public java.math.BigDecimal getMaxProfit() {
		return get("max_profit");
	}

	public void setMinDeadLine(java.lang.Integer minDeadLine) {
		set("min_dead_line", minDeadLine);
	}

	public java.lang.Integer getMinDeadLine() {
		return get("min_dead_line");
	}
	
	public void setMaxDeadLine(java.lang.Integer maxDeadLine) {
		set("max_dead_line", maxDeadLine);
	}

	public java.lang.Integer getMaxDeadLine() {
		return get("max_dead_line");
	}
	
	public void setDeadLineSelfDefined(java.lang.String deadLineSelfDefined) {
		set("dead_line_self_defined", deadLineSelfDefined);
	}

	public java.lang.String getDeadLineSelfDefined() {
		return get("dead_line_self_defined");
	}

	public void setDetailImg(java.lang.String detailImg) {
		set("platform_detail_img", detailImg);
	}

	public java.lang.String getDetailImg() {
		return get("platform_detail_img");
	}

	public void setBriefIntroduction(java.lang.String briefIntroduction) {
		set("brief_introduction", briefIntroduction);
	}

	public java.lang.String getBriefIntroduction() {
		return get("brief_introduction");
	}

	public void setTeamIntroduction(java.lang.String teamIntroduction) {
		set("team_introduction", teamIntroduction);
	}

	public java.lang.String getTeamIntroduction() {
		return get("team_introduction");
	}

	public void setSiteRecord(java.lang.String siteRecord) {
		set("site_record", siteRecord);
	}

	public java.lang.String getSiteRecord() {
		return get("site_record");
	}
	
	public void setContactUs(java.lang.String contactUs) {
		set("contact_us", contactUs);
	}

	public java.lang.String getContactUs() {
		return get("contact_us");
	}

	public void setPicInfo(java.lang.String picInfo) {
		set("pic_info", picInfo);
	}

	public java.lang.String getPicInfo() {
		return get("pic_info");
	}


	public void setStyle(java.lang.String style) {
		set("style", style);
	}

	public java.lang.String getStyle() {
		return get("style");
	}
	
	public void setUserId(java.math.BigInteger userId) {
		set("user_id", userId);
	}

	public java.math.BigInteger getUserId() {
		return get("user_id");
	}
	
	public String getUserName(){
		return get("userName");
	}
	
	public void setOrderNumber(java.lang.Integer orderNumber) {
		set("order_number", orderNumber);
	}

	public java.lang.Integer getOrderNumber() {
		return get("order_number");
	}
	
	public void setStatus(java.lang.String status) {
		set("status", status);
	}

	public java.lang.String getStatus() {
		return get("status");
	}

	public void setCreated(java.util.Date created) {
		set("created", created);
	}

	public java.util.Date getCreated() {
		return get("created");
	}

	public void setModified(java.util.Date modified) {
		set("modified", modified);
	}

	public java.util.Date getModified() {
		return get("modified");
	}
	

	public void setSlug(java.lang.String slug) {
		set("slug", slug);
	}

	public java.lang.String getSlug() {
		return get("slug");
	}
	
	public void setViewCount(java.lang.Long viewCount) {
		set("view_count", viewCount);
	}

	public java.lang.Long getViewCount() {
		return get("view_count");
	}
	
	public void setMetaKeywords(java.lang.String metaKeywords) {
		set("meta_keywords", metaKeywords);
	}

	public java.lang.String getMetaKeywords() {
		return get("meta_keywords");
	}

	public void setMetaDescription(java.lang.String metaDescription) {
		set("meta_description", metaDescription);
	}

	public java.lang.String getMetaDescription() {
		return get("meta_description");
	}

	public void setRemarks(java.lang.String remarks) {
		set("remarks", remarks);
	}

	public java.lang.String getRemarks() {
		return get("remarks");
	}

}
