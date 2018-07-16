package com.linkwee.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.ApplicationUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.dao.CimOrgInfoAMapper;
import com.linkwee.web.model.*;
import com.linkwee.web.response.CimOrginfoAWeb;
import com.linkwee.web.response.OrgNameNumber;
import com.linkwee.web.service.*;
import com.linkwee.xoss.rbac.PermissionSign;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
*
* @描述：CimOrgInfoAService 服务实现类
*
* @创建人： Hxb
*
* @创建时间：2018年05月09日 18:01:30
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Service("cimOrgInfoAService")
public class CimOrgInfoAServiceImpl extends GenericServiceImpl<CimOrgInfoA, Long> implements CimOrgInfoAService{

   private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgInfoAServiceImpl.class);

   @Resource
   private CimOrgInfoAMapper cimOrgInfoAMapper;

   @Resource
   private CimOrgInvestStrategyAService investStrategyAService;

   @Resource
   private CimOrgMemberinfoAService memberinfoAService;

   @Resource
   private CimOrgPictureService cimOrgPictureService; //图片服务 与S平台共用

   @Resource
   private SysThirdkeyConfigService sysThirdkeyConfigService;

   @Override
   public GenericDao<CimOrgInfoA, Long> getDao() {
       return cimOrgInfoAMapper;
   }

   @Override
   public DataTableReturn selectByDatatables(DataTable dt) {
       DataTableReturn tableReturn = new DataTableReturn();
       tableReturn.setDraw(dt.getDraw()+1);
       LOGGER.debug(" -- CimOrginfoA -- 排序和模糊查询 ");
       Page<CimOrginfoAWeb> page = new Page<CimOrginfoAWeb>(dt.getStart()/dt.getLength()+1,dt.getLength());
       List<CimOrginfoAWeb> list = cimOrgInfoAMapper.selectBySearchInfo(dt,page);

       Subject currentUser = SecurityUtils.getSubject();
       String detailEditPermission = "0";
       if(currentUser.isPermitted(PermissionSign.ORG_A_DETAIL_EDIT)) {
           detailEditPermission = "1";
       }
       for(CimOrginfoAWeb temp : list){
           temp.setDetailEditPermission(detailEditPermission);
       }
       tableReturn.setData(list);
       tableReturn.setRecordsFiltered(page.getTotalCount());
       tableReturn.setRecordsTotal(page.getTotalCount());
       return tableReturn;
   }

    @Override
    public CimOrginfoAWeb findWebOrgInfo(String orgNumber) {
        return cimOrgInfoAMapper.findWebOrgInfo(orgNumber);
    }

    @Override
    public void updateOrgFullInfo(CimOrginfoAWeb request) {
        LOGGER.debug("更新机构 更新数据到机构表:CimOrginfoAWeb = {}", JSON.toJSONString(request));
        List<CimOrgInvestStrategyA> investStrategys = request.getInvestStrategys(); //获取投资攻略信息
        List<CimOrgMemberinfoA> teams = request.getTeams(); //获取团队信息
        if(request.getId() != null){
            request.setUpdateTime(new Date());
            cimOrgInfoAMapper.updateByOrgNumber(request);
            //判断投资攻略id是否null
            List<CimOrgInvestStrategyA> newInvestStrategys = new ArrayList<CimOrgInvestStrategyA>(); //新增加的投资攻略信息

            if(investStrategys != null){
                for(CimOrgInvestStrategyA newInvestStrategy : investStrategys){
                    newInvestStrategy.setOperator(request.getOrgUpdater());
                    newInvestStrategy.setUpdateTime(new Date());
                    if(newInvestStrategy.getId() == null){
                        newInvestStrategy.setCreateTime(new Date());
                        newInvestStrategys.add(newInvestStrategy); //保存id为null的成员信息 执行批量新增
                    }
                }
                if(newInvestStrategys.size() > 0){
                    investStrategys.removeAll(newInvestStrategys); //从集合中删除id为null的团队成员
                }
                /**
                 * 执行批量更新操作
                 */
                if(!investStrategys.isEmpty() && investStrategys.size() > 0){
                    LOGGER.debug("更新机构 批量投资攻略信息到团队信息表:investStrategys = {}", JSON.toJSONString(investStrategys));
                    investStrategyAService.batchUpdateInvestStrategy(investStrategys);
                    LOGGER.debug("更新机构 批量更新数据到投资攻略信息表成功！ ");
                }
            }

            /**
             * 执行批量插入操作
             */
            if(!newInvestStrategys.isEmpty() && newInvestStrategys.size() > 0){
                for(CimOrgInvestStrategyA temp : newInvestStrategys){
                    temp.setUpdateTime(new Date());
                    temp.setOrgNumber(request.getOrgNumber()); //设置机构主键到团队信息表
                }
                LOGGER.debug("更新机构 批量新增投资攻略:newInvestStrategys = {}", JSON.toJSONString(newInvestStrategys));
                investStrategyAService.insertBatch(newInvestStrategys);
                LOGGER.debug("更新机构 批量新增投资攻略成功！ ");
            }

            //判断团队id是否null
            List<CimOrgMemberinfoA> newTeams = new ArrayList<CimOrgMemberinfoA>(); //新增加的团队成员信息

            if(teams != null){
                for(CimOrgMemberinfoA newTeam : teams){
                    if(newTeam.getId() == null){
                        newTeams.add(newTeam); //保存id为null的成员信息 执行批量新增
                    }
                }

                if(newTeams.size() > 0){
                    teams.removeAll(newTeams); //从集合中删除id为null的团队成员
                }

                /**
                 * 执行批量更新操作
                 */
                if(!teams.isEmpty() && teams.size() > 0){
                    LOGGER.debug("更新机构 批量更新团队信息到团队信息表:teams = {}", JSON.toJSONString(teams));
                    memberinfoAService.updateBatchTeam(teams);
                    LOGGER.debug("更新机构 批量更新数据到团队信息表成功！ ");
                }

            }

            /**
             * 执行批量插入操作
             */
            if(!newTeams.isEmpty() && newTeams.size() > 0){
                for(CimOrgMemberinfoA team : newTeams){
                    team.setOrgNumber(request.getOrgNumber()); //设置机构主键到团队信息表
                }
                LOGGER.debug("更新机构 批量新增团队信息到团队信息表:newTeams = {}", JSON.toJSONString(newTeams));
                memberinfoAService.insertBatch(newTeams);
                LOGGER.debug("更新机构 批量新增数据到团队信息表成功！ ");
            }

            List<CimOrgPicture> pictures = pictureHandle("edit", request.getOrgNumber(),request.getOrgEnvironmentPicture(),request.getOrgPaperPicture());
            request.setOrgPictures(pictures); //保存图片

            SysThirdkeyConfig thirdkeyConfig = new SysThirdkeyConfig();
            thirdkeyConfig.setOrgNumber(request.getOrgNumber()); //机构编码
            thirdkeyConfig = sysThirdkeyConfigService.selectOne(thirdkeyConfig);
            if(thirdkeyConfig != null){
                if(request.getStatus() == null || request.getStatus() == 0){ //合作状态.0-合作结束，1-合作中' 2-待上线
                    thirdkeyConfig.setOrgStatus("n");
                }else if(request.getStatus() == 1){
                    thirdkeyConfig.setOrgStatus("y");
                }
                thirdkeyConfig.setUpdateTime(new Date());
                thirdkeyConfig.setCreateUser(request.getOrgUpdater());
                sysThirdkeyConfigService.updateThirdkeyByOrgNumber(thirdkeyConfig);
            }else{
                thirdkeyConfig = new SysThirdkeyConfig();
                //第三方api接口配置表生成公钥和私钥  后置添加，有可能之前上线的机构没有生成相应的配置
                thirdkeyConfig.setOrgNumber(request.getOrgNumber()); //机构编码
                thirdkeyConfig.setOrgKey(ApplicationUtils.randomUUID(true, true)); //生成公钥
                thirdkeyConfig.setOrgSecret(ApplicationUtils.randomUUID(true, true)); //生成私钥
                thirdkeyConfig.setOrgStatus(request.getStatus() == 1 ? "y":"n"); //默认非合作状态 n, y：开启，n：关闭
                thirdkeyConfig.setCreateTime(new Date());
                thirdkeyConfig.setArchive(0); //'逻辑删除：0:可用，1：删除'
                thirdkeyConfig.setCreateUser(request.getOrgUpdater()); //创建人
                sysThirdkeyConfigService.insert(thirdkeyConfig); //插入
            }
        }else{
            request.setOrgCreator(request.getOrgUpdater());
            request.setCreateTime(new Date());
            request.setOrgNumber(String.format("OPEN_A_%s_WEB",request.getOrgNumber()));
            cimOrgInfoAMapper.insertOrgInfoA(request);
            if(investStrategys != null && investStrategys.size() > 0){
                for(CimOrgInvestStrategyA investStrategy : investStrategys){
                    investStrategy.setOperator(request.getOrgUpdater());
                    investStrategy.setOrgNumber(request.getOrgNumber());
                }
                investStrategyAService.insertBatch(investStrategys);
            }
            if(teams != null && teams.size() > 0){
                for(CimOrgMemberinfoA team : teams){
                    team.setOrgNumber(request.getOrgNumber()); //设置机构主键到团队信息表
                }
                memberinfoAService.insertBatch(teams);
            }
            List<CimOrgPicture> pictures = pictureHandle("add", request.getOrgNumber(),request.getOrgEnvironmentPicture(),request.getOrgPaperPicture());
            request.setOrgPictures(pictures); //保存图片

            SysThirdkeyConfig thirdkeyConfig = new SysThirdkeyConfig();
            //第三方api接口配置表生成公钥和私钥
            thirdkeyConfig.setOrgNumber(request.getOrgNumber()); //机构编码
            thirdkeyConfig.setOrgKey(ApplicationUtils.randomUUID(true, true)); //生成公钥
            thirdkeyConfig.setOrgSecret(ApplicationUtils.randomUUID(true, true)); //生成私钥
            thirdkeyConfig.setOrgStatus(request.getStatus() == 1 ? "y":"n"); //默认非合作状态 n, y：开启，n：关闭
            thirdkeyConfig.setCreateTime(new Date());
            thirdkeyConfig.setArchive(0); //'逻辑删除：0:可用，1：删除'
            thirdkeyConfig.setCreateUser(request.getOrgUpdater()); //创建人
            LOGGER.debug("新增机构时 插入数据到第三方api接口配置表:thirdkeyConfig = {} ", JSON.toJSONString(thirdkeyConfig));
            sysThirdkeyConfigService.insert(thirdkeyConfig); //插入
            LOGGER.debug("插入数据到第三方api接口配置表成功！ ");
        }
        if(request.getOrgPictures() != null && request.getOrgPictures().size() > 0){
            cimOrgPictureService.insertBatchPicture(request.getOrgPictures());//图片批量插入
        }
    }

    @Override
    public void updateByOrgNumber(CimOrginfoAWeb org) {
        cimOrgInfoAMapper.updateByOrgNumber(org);
    }

    @Override
    public List<OrgNameNumber> queryAllOrgNameNumber(int status) {
        return cimOrgInfoAMapper.queryAllOrgNameNumber(status);
    }

    /**
     * 封装机构图片
     * @author hxb
     * @date 2016年11月23日 下午4:08:45
     * @param orgEnvironmentPicture 移动端-环境图片
     * @param orgPaperPicture 移动端-其他证件图片
     * @return
     */
    private List<CimOrgPicture> pictureHandle(String handleMethod,String orgNumber,String orgEnvironmentPicture,String orgPaperPicture){
        List<CimOrgPicture> pictures = new ArrayList<CimOrgPicture>();
        //封装移动端-机构环境照
        if(StringUtils.isNotBlank(orgEnvironmentPicture)){
            if(orgEnvironmentPicture.contains(",")){
                String[] orgEnvironments = orgEnvironmentPicture.split(",");
                for(String ev : orgEnvironments){
                    CimOrgPicture pic = new CimOrgPicture();
                    if(handleMethod.equals("add")){
                        pic.setOrgNumber(String.format("OPEN_A_%s_WEB",orgNumber));
                    }else{
                        pic.setOrgNumber(orgNumber);
                    }
                    pic.setOrgPicture(ev);
                    pic.setOrgPictureType(2);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
                    pic.setSource(1); //1:移动端图片,2:PC端图片
                    pictures.add(pic);
                }
            }else{
                CimOrgPicture pic = new CimOrgPicture();
                if(handleMethod.equals("add")){
                    pic.setOrgNumber(String.format("OPEN_A_%s_WEB",orgNumber));
                }else{
                    pic.setOrgNumber(orgNumber);
                }
                pic.setOrgPicture(orgEnvironmentPicture);
                pic.setOrgPictureType(2);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
                pic.setSource(1); //1:移动端图片,2:PC端图片
                pictures.add(pic);
            }
        }

        //封装移动端-机构资格证
        if(StringUtils.isNotBlank(orgPaperPicture)){
            if(orgPaperPicture.contains(",")){
                String[] orgEnvironments = orgPaperPicture.split(",");
                for(String ev : orgEnvironments){
                    CimOrgPicture pic = new CimOrgPicture();
                    if(handleMethod.equals("add")){
                        pic.setOrgNumber(String.format("OPEN_A_%s_WEB",orgNumber));
                    }else{
                        pic.setOrgNumber(orgNumber);
                    }
                    pic.setOrgPicture(ev);
                    pic.setOrgPictureType(1);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
                    pic.setSource(1); //1:移动端图片,2:PC端图片
                    pictures.add(pic);
                }
            }else{
                CimOrgPicture pic = new CimOrgPicture();
                if(handleMethod.equals("add")){
                    pic.setOrgNumber(String.format("OPEN_A_%s_WEB",orgNumber));
                }else{
                    pic.setOrgNumber(orgNumber);
                }
                pic.setOrgPicture(orgPaperPicture);
                pic.setOrgPictureType(1);//机构图片类型,1-机构证件照，2-办公环境照，3-荣誉证书
                pic.setSource(1); //1:移动端图片,2:PC端图片
                pictures.add(pic);
            }
        }
        return pictures;
    }

}
