package com.linkwee.web.controller;

import com.alibaba.fastjson.JSON;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.CimOrgFeeStrategyA;
import com.linkwee.web.model.CimOrgPicture;
import com.linkwee.web.model.User;
import com.linkwee.web.model.cim.*;
import com.linkwee.web.request.CimOrgAFeeStrategyRequest;
import com.linkwee.web.request.orgInfo.CimOrgFeeRequest;
import com.linkwee.web.response.CimOrginfoAWeb;
import com.linkwee.web.service.*;
import com.linkwee.xoss.constant.WebConstants;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.Pinyin4jUtil;
import com.linkwee.xoss.util.RequestLogging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
*
* @描述： CimOrgInfoAController控制器
*
* @创建人： Hxb
*
* @创建时间：2018年05月09日 18:01:30
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Controller
@RequestMapping(value = "cim/cimorginfoa")
@RequestLogging("CimOrgInfoAController控制器")
public class CimOrgInfoAController {

   private static final Logger LOGGER = LoggerFactory.getLogger(CimOrgInfoAController.class);

   @Resource
   private CimOrgInfoAService cimOrgInfoAService;

   @Resource
   private SysConfigService sysConfigService;

   @Resource
   private CimOrgInvestStrategyAService investStrategyAService;

   @Resource
   private CimOrgMemberinfoAService memberinfoAService;

   @Resource
   private CimOrgPictureService cimOrgPictureService; //平台图片 与S平台共用表

   @Resource
   private CimOrgFeeStrategyAService orgFeeStrategyAService;

   /**
    * 转换器
    *
    * @param binder
    */
   @InitBinder
   public void initBinder(WebDataBinder binder) {
       binder.registerCustomEditor(Date.class, new DateConvertEditor());
       binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
   }

    /**
     *
     * @return
     */
    @RequestMapping("/ueditor_config")
    @ResponseBody
    public Object ueditorConfig(){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("imageActionName","zimg");
        result.put("imageFieldName","userfile");
        result.put("imageAllowFiles",new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
        return result;
    }

    /**
     * 机构列表页面
     */
    @RequestMapping(value="/list",   method=RequestMethod.GET)
    @RequestLogging("跳到机构列表页面")
    public String toCimOrgInfoListView(Model model) {
        return "cimorginfounrecord/cimorginfo-list";
    }

    /**
     * 机构列表
     * @return
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("查看机构列表")
    public DataTableReturn cimOrgInfoList(@RequestParam String  _dt_json) {
        LOGGER.debug("CimOrginfo list _dt_json={}", _dt_json);
        CimOrginfoDataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, CimOrginfoDataTable.class);
        String conditions = dataTable.getSearch().getValue(); //查询条件
        if(StringUtils.isNotBlank(dataTable.getOrgName()) && StringUtils.isBlank(conditions)){
            dataTable.getSearch().setValue(dataTable.getOrgName());
        }
        dataTable.initOrders();
        DataTableReturn tableReturn = cimOrgInfoAService.selectByDatatables(dataTable);
        return tableReturn;
    }

    /**
     * 跳到编辑页面
     * @param orgNumber
     * @param model
     * @return
     */
    @RequestMapping(value="/toEdit",method=RequestMethod.GET)
    @RequestLogging("跳到机构编辑页面")
    public String toEditView(String orgNumber, ModelMap model) {
        LOGGER.debug("机构信息编辑请求参数 orgNumber = {}" ,orgNumber);
        String imgServerUrl = sysConfigService.getValuesByKey(WebConstants.IMAGE_SERVER_URL);//图片服务器地址
        if(StringUtils.isNotBlank(orgNumber)){
            CimOrginfoAWeb orgInfo = cimOrgInfoAService.findWebOrgInfo(orgNumber); //WEB端 平台信息详情
            List<CimOrgPicture> orgPapersList = cimOrgPictureService.queryOrgPictureList(orgNumber,1,1); //移动端-公司证件照
            List<CimOrgPicture> orgEnvironmentList = cimOrgPictureService.queryOrgPictureList(orgNumber,2,1); //移动端-办公环境照
            if(orgPapersList != null){
                StringBuilder orgPapers  = new StringBuilder();
                for(CimOrgPicture pic : orgPapersList){
                    orgPapers.append(pic.getPid()).append("-").append(pic.getOrgPicture()).append(",");
                }
                model.addAttribute("orgPapers",orgPapers);
            }
            if(orgEnvironmentList != null){
                StringBuilder orgEnvironments  = new StringBuilder();
                for(CimOrgPicture pic : orgEnvironmentList){
                    orgEnvironments.append(pic.getPid()).append("-").append(pic.getOrgPicture()).append(",");
                }
                model.addAttribute("orgEnvironments",orgEnvironments);
            }
            model.addAttribute("orgInfo",orgInfo);
        }
        model.addAttribute("img_server",imgServerUrl);
        return "cimorginfounrecord/cimorginfo-edit";
    }

    /**
     * 跳到投资攻略模板页面
     * @return
     */
    @RequestMapping(value="/toInvestStrategyTemplate",method=RequestMethod.GET)
    @RequestLogging("跳到投资攻略模板页面")
    public ModelAndView toInvestStrategyTemplate(){
        ModelAndView modelAndView = new ModelAndView("cimorginfounrecord/investStrategy-template");
        return modelAndView;
    }

    /**
     * 删除投资攻略
     * @return
     */
    @RequestMapping(value = "/deleteInvestStrategy", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("删除投资攻略")
    public Object deleteInvestStrategy(Long id,String orgNumber,HttpSession session){
        LOGGER.debug("删除投资攻略请求参数 id = {}",JSON.toJSONString(id));
        long start = System.currentTimeMillis();
        StringBuilder logsb = new StringBuilder();
        ResponseResult result = null;
        try {
            //删除投资攻略
            if(null != id && id > 0){
                int deleteRows = investStrategyAService.delete(id);
                if(deleteRows > 0){
                    User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
                    CimOrginfoAWeb org = new CimOrginfoAWeb();
                    org.setOrgNumber(orgNumber);
                    org.setOrgUpdater(user.getUsername()); //更新操作用户
                    org.setUpdateTime(new Date()); //更新时间
                    cimOrgInfoAService.updateByOrgNumber(org); //更新机构表信息
                    result = new ResponseResult(true, "删除投资攻略成功！");
                    logsb.append("删除投资攻略成功！ success");
                }
            }
        } catch (Exception e) {
            logsb.append("删除投资攻略失败！ fail");
            LOGGER.error("删除投资攻略失败！", e);
            result = new ResponseResult(false, "删除投资攻略失败！");
        }
        long end = System.currentTimeMillis();
        logsb.append("删除投资攻略总耗时 |totaltime=").append(end - start).append("ms");
        LOGGER.info(logsb.toString());
        return result;
    }

    /**
     * 机构名称转换为大写全拼
     * @param orgName
     * @return
     */
    @RequestMapping(value="/orgNameConverterToSpell")
    @RequestLogging("机构名称转换为大写全拼")
    @ResponseBody
    public String orgNameConverterToSpell(String orgName){
        String pinyin = "NONE";
        if(StringUtils.isNotBlank(orgName)){
            pinyin = Pinyin4jUtil.converterToSpell(orgName);
        }
        return pinyin;
    }

    /**
     * 更新机构
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("更新机构信息")
    public Object updateCimOrgInfo(@RequestBody CimOrginfoAWeb request, HttpSession session) {
        LOGGER.info("机构更新请求参数 CimOrginfoWeb = {}", JSON.toJSONString(request));
        long start = System.currentTimeMillis();
        StringBuilder logsb = new StringBuilder();
        ResponseResult result = null;
        try {
            User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
            request.setOrgUpdater(user.getUsername()); //获取修改人用户名
            cimOrgInfoAService.updateOrgFullInfo(request);
            result = new ResponseResult(true, "更新机构信息成功！");
            logsb.append("更新机构信息 success");
        } catch (Exception e) {
            logsb.append("更新机构信息 fail");
            LOGGER.error("更新机构信息失败！", e);
            result = new ResponseResult(false, "更新机构信息失败！");
        }
        long end = System.currentTimeMillis();
        logsb.append("更新机构信息总耗时 |totaltime=").append(end - start).append("ms");
        LOGGER.info(logsb.toString());
        return result;
    }

    /**
     * 跳到团队成员模板页面
     * @return
     */
    @RequestMapping(value="/toTeamTemplate",method=RequestMethod.GET)
    @RequestLogging("跳到团队成员模板页面")
    public ModelAndView toTeamTemplate(){
        ModelAndView modelAndView = new ModelAndView("cimorginfounrecord/team-template");
        return modelAndView;
    }

    /**
     * 删除团队成员
     * @return
     */
    @RequestMapping(value = "/deleteTeam", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("删除团队成员")
    public Object deleteTeam(Long id,String orgNumber,HttpSession session){
        LOGGER.debug("删除团队成员请求参数 id = {}",JSON.toJSONString(id));
        long start = System.currentTimeMillis();
        StringBuilder logsb = new StringBuilder();
        ResponseResult result = null;
        try {
            //删除团队成员
            if(null != id && id > 0){
                int deleteRows = memberinfoAService.delete(id);
                if(deleteRows > 0){
                    User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
                    CimOrginfoAWeb org = new CimOrginfoAWeb();
                    org.setOrgNumber(orgNumber);
                    org.setOrgUpdater(user.getUsername()); //更新操作用户
                    org.setUpdateTime(new Date()); //更新时间
                    cimOrgInfoAService.updateByOrgNumber(org); //更新机构表信息
                    result = new ResponseResult(true, "删除此团队成员成功！");
                    logsb.append("删除此团队成员成功！ success");
                }
            }
        } catch (Exception e) {
            logsb.append("删除此团队成员失败！ fail");
            LOGGER.error("删除此团队成员失败！", e);
            result = new ResponseResult(false, "删除此团队成员失败！");
        }
        long end = System.currentTimeMillis();
        logsb.append("删除此团队成员总耗时 |totaltime=").append(end - start).append("ms");
        LOGGER.info(logsb.toString());
        return result;
    }

    /**
     * 跳到机构返现策略页面
     * @return
     */
    @RequestMapping(value="/toOrgFeeStrategy",method=RequestMethod.GET)
    @RequestLogging("跳到机构返现策略页面")
    public ModelAndView toOrgFeeStrategy(String orgNumber){
        LOGGER.debug("跳到机构返现策略页面请求参数 orgNumber = {}" ,orgNumber);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orgNumber",orgNumber); //机构编码
        List<CimOrgFeeStrategyA> orgFeeStrategyList = orgFeeStrategyAService.queryOrgFeeStrategy(orgNumber);
        modelAndView.addObject("orgFeeStrategyList",orgFeeStrategyList);
        for(CimOrgFeeStrategyA strategyA : orgFeeStrategyList){
            if(strategyA.getFeeStrategy() == 1){ //固定猎财返现金额
                modelAndView.addObject("cpaFeeAttr",1);
                modelAndView.addObject("cpaFeeVal",strategyA.getFeeVal());
                modelAndView.addObject("feeStrategyId",strategyA.getId());
            }else if(strategyA.getFeeStrategy() == 2){ //首投金额固定返现比例
                modelAndView.addObject("cpaFeeAttr",2);
                modelAndView.addObject("cpaFeeRatio",strategyA.getFeeRatio());
                modelAndView.addObject("feeStrategyId",strategyA.getId());
            }else if(strategyA.getFeeStrategy() == 3){ //首投期限固定返现比例
                modelAndView.addObject("cpaFeeAttr",3);
            }
        }
        modelAndView.setViewName("cimorginfounrecord/cimfeerecord-edit");
        return modelAndView;
    }

    /**
     * 更新机构返现策略
     * @return
     */
    @RequestMapping(value = "/updateFeeStrategy", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("更新机构返现策略")
    public Object updateFeeStrategy(@RequestBody CimOrgAFeeStrategyRequest request, HttpSession session){
        LOGGER.debug("更新机构返现策略请求参数 CimOrgFeeRequest = {}",JSON.toJSONString(request));
        long start = System.currentTimeMillis();
        StringBuilder logsb = new StringBuilder();
        ResponseResult result = null;
        try {
            User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
            request.setUpdater(user.getUsername());//获取系统当前用户

            List<CimOrgFeeStrategyA> feeRecordList = convertToCimOrgFeeStrategy(request); //封装请求参数
            //更新机构返现策略 删除之前的返现策略 选中的全部新增
            orgFeeStrategyAService.updateBatchFee(feeRecordList,request.getOrgNumber());
            result = new ResponseResult(true, "更新机构返现策略成功！");
            logsb.append("更新机构返现策略 success");
        } catch (Exception e) {
            logsb.append("更新机构返现策略 fail");
            LOGGER.error("更新机构返现策略失败！", e);
            result = new ResponseResult(false, "更新机构返现策略失败！");
        }
        long end = System.currentTimeMillis();
        logsb.append("更新机构返现策略总耗时 |totaltime=").append(end - start).append("ms");
        LOGGER.info(logsb.toString());
        return result;
    }

    private List<CimOrgFeeStrategyA> convertToCimOrgFeeStrategy(CimOrgAFeeStrategyRequest request) {

        List<CimOrgFeeStrategyA> strategyASList = new ArrayList<CimOrgFeeStrategyA>();

        if(request.getCpaFeeAttr() == 1){ //首投固定猎财返现
            CimOrgFeeStrategyA strategyA = new CimOrgFeeStrategyA();
            strategyA.setOrgNumber(request.getOrgNumber());
            strategyA.setFeeStrategy(1);
            strategyA.setFeeVal(request.getFixedMoney());
            strategyA.setUpdateTime(new Date());
            strategyA.setUpdater(request.getUpdater());
            strategyASList.add(strategyA);
        }else if(request.getCpaFeeAttr() == 2){ //首投金额固定返现比例
            CimOrgFeeStrategyA strategyA = new CimOrgFeeStrategyA();
            strategyA.setOrgNumber(request.getOrgNumber());
            strategyA.setFeeStrategy(2);
            strategyA.setFeeRatio(request.getFixedMoneyRatio());
            strategyA.setUpdateTime(new Date());
            strategyA.setUpdater(request.getUpdater());
            strategyASList.add(strategyA);
        }else if(request.getCpaFeeAttr() == 3){ //首投期限固定返现比例
            for(CimOrgFeeStrategyA strategyA : request.getOrgFeeRecords()){
                strategyA.setOrgNumber(request.getOrgNumber());
                strategyA.setFeeStrategy(3);
                strategyA.setUpdateTime(new Date());
                strategyA.setUpdater(request.getUpdater());
                strategyA.setIntervalUnit("天");
            }
            strategyASList = request.getOrgFeeRecords();
        }
        return strategyASList;
    }

    /**
     * 删除机构返现策略
     * @return
     */
    @RequestMapping(value = "/deleteFeeStrategy", method = RequestMethod.POST)
    @ResponseBody
    @RequestLogging("删除机构返现策略")
    public Object deleteFeeStrategy(Long id,HttpSession session){
        LOGGER.debug("删除机构返现策略 id = {}",JSON.toJSONString(id));
        long start = System.currentTimeMillis();
        StringBuilder logsb = new StringBuilder();
        ResponseResult result = null;
        try {
            //删除机构收费模式区间记录
            if(null != id && id > 0){
                int deleteRows = orgFeeStrategyAService.delete(id);
                if(deleteRows > 0){
                    //User user = (User) session.getAttribute("userInfo"); // 获取登录用户信息
                    result = new ResponseResult(true, "删除机构返现策略成功！");
                    logsb.append("删除机构返现策略成功！ success");
                }
            }
        } catch (Exception e) {
            logsb.append("删除机构返现策略失败！ fail");
            LOGGER.error("删除机构返现策略失败！", e);
            result = new ResponseResult(false, "删除机构返现策略失败！");
        }
        long end = System.currentTimeMillis();
        logsb.append("删除机构返现策略总耗时 |totaltime=").append(end - start).append("ms");
        LOGGER.info(logsb.toString());
        return result;
    }

}
