package com.linkwee.api.response;

import com.linkwee.core.base.BaseEntity;

public class CradListResponse extends BaseEntity {

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 链接地址
     */
    private String linkUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
