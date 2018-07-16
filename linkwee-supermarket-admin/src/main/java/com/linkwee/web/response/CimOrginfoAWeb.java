package com.linkwee.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkwee.web.model.CimOrgInfoA;
import com.linkwee.web.model.CimOrgInvestStrategyA;
import com.linkwee.web.model.CimOrgMemberinfoA;
import com.linkwee.web.model.CimOrgPicture;

import java.util.Date;
import java.util.List;

public class CimOrginfoAWeb extends CimOrgInfoA{

    private static final long serialVersionUID = 3141175126777462934L;

    /**
     * 编辑权限
     */
    private String detailEditPermission;

    /**
     * 投资攻略
     */
    private List<CimOrgInvestStrategyA> investStrategys;

    /**
     * 团队成员信息
     */
    private List<CimOrgMemberinfoA> teams;

    /**
     * 办公环境照
     */
    private String orgEnvironmentPicture;

    /**
     * 其他资格证
     */
    private String orgPaperPicture;

    /**
     * 机构图片信息
     */
    private List<CimOrgPicture> orgPictures;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date upTime;

    public String getDetailEditPermission() {
        return detailEditPermission;
    }

    public void setDetailEditPermission(String detailEditPermission) {
        this.detailEditPermission = detailEditPermission;
    }

    public List<CimOrgInvestStrategyA> getInvestStrategys() {
        return investStrategys;
    }

    public void setInvestStrategys(List<CimOrgInvestStrategyA> investStrategys) {
        this.investStrategys = investStrategys;
    }

    public List<CimOrgMemberinfoA> getTeams() {
        return teams;
    }

    public void setTeams(List<CimOrgMemberinfoA> teams) {
        this.teams = teams;
    }

    @Override
    public Date getUpTime() {
        return upTime;
    }

    @Override
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getOrgEnvironmentPicture() {
        return orgEnvironmentPicture;
    }

    public void setOrgEnvironmentPicture(String orgEnvironmentPicture) {
        this.orgEnvironmentPicture = orgEnvironmentPicture;
    }

    public String getOrgPaperPicture() {
        return orgPaperPicture;
    }

    public void setOrgPaperPicture(String orgPaperPicture) {
        this.orgPaperPicture = orgPaperPicture;
    }

    public List<CimOrgPicture> getOrgPictures() {
        return orgPictures;
    }

    public void setOrgPictures(List<CimOrgPicture> orgPictures) {
        this.orgPictures = orgPictures;
    }
}
