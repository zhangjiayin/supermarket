package com.linkwee.web.controller.cfplanner;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.request.CfpCommonRequest;
import com.linkwee.web.response.CfpCommissionListResp;
import com.linkwee.web.response.LcsDetailResp;
import com.linkwee.web.service.CfplannerService;
import com.linkwee.web.service.LcsListService;
import com.linkwee.web.service.LcsSalesAndEarningService;
import com.linkwee.web.util.RequestLogging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by lenli on 2016/5/31.
 *理财师相关功能控制层实层-包括相关的收益时细，客户，团队列表详情
 * @Author Libin
 * @Date 2016/5/31
 */
@Controller
@RequestMapping("/cfprelevant")
@RequestLogging("理财师收益与客户详情")
public class CfpRelevantController {

    private final static Logger logger = LoggerFactory.getLogger(CfpRelevantController.class);

    @Resource
    private LcsListService lcsListService;

    @Resource
    private CfplannerService cfplannerService;

    @Resource
    private LcsSalesAndEarningService lcsSalesAndEarningService;
    /**
     *理财师-团队列表清单
     * @return
     * @throws Exception
     */
    @RequestLogging("团队列表清单")
    @RequestMapping(value = "/teamlist")
    public ModelAndView findCFPTeamInfo(String mobile) throws Exception{
        ModelAndView modelAndView = new ModelAndView("cfprelevant/team-list");
        LcsDetailResp detailResp = lcsListService.queryLcsInfo(mobile);
        modelAndView.addObject("cfp",detailResp);
        return modelAndView;
    }

    /**
     * 理财师-团队列表清单数据
     * @param searchtext
     * @param dataTable
     * @return
     * @throws Exception
     */
    @RequestLogging("团队数据列表清单")
    @RequestMapping("/teamlist_json")
    @ResponseBody
    public DataTableReturn  findCFPTeamInfoJson(String searchtext,String mobile,DataTable dataTable) throws Exception{
        LcsDetailResp detailResp =null;
        if(StringUtils.isNotBlank(mobile)){
            detailResp = lcsListService.queryLcsInfo(mobile);
            detailResp.setSearchText(searchtext);
        }
        return cfplannerService.queryCfpTeamList(detailResp,dataTable);
    }

    /**
     *理财师-客户列表清单
     * @return
     * @throws Exception
     */
    @RequestLogging("客户列表清单")
    @RequestMapping(value ="/customelist/{mobile}")
    public ModelAndView  findCFPCustomerInfo(@PathVariable(value = "mobile") String mobile) throws Exception{
        LcsDetailResp detailResp = lcsListService.queryLcsInfo(mobile);
        ModelAndView modelAndView = new ModelAndView("cfprelevant/custome-list");
        modelAndView.addObject("mobile",mobile);
        modelAndView.addObject("cfp",detailResp);
        return modelAndView;
    }

    /**
     * 理财师-客户列表清单数据
     * @param searchtext  = mobile || name
     * @return
     * @throws Exception
     */
    @RequestLogging("客户列表数据清单")
    @RequestMapping(value ="/customelist_json")
    @ResponseBody
    public DataTableReturn findCFPCustomerInfoJson(String mobile,String searchtext,DataTable dataTable) throws Exception{
        LcsDetailResp detailResp = null;
        if(StringUtils.isNotBlank(mobile)){
            detailResp = lcsListService.queryLcsInfo(mobile);
            detailResp.setSearchText(searchtext);
        }
        return cfplannerService.queryCfpCustomerProfitList(detailResp,dataTable);
    }


    /**
     *销售与收益列表
     * @return
     * @throws Exception
     */
    @RequestLogging("销售与收益列表")
    @RequestMapping(value ="/salelist")
    public ModelAndView  findCFPSaleList() throws Exception{
        ModelAndView modelAndView = new ModelAndView("cfprelevant/sale-list");
        return modelAndView;
    }

    @RequestLogging("销售与收益列表数据")
    @RequestMapping(value = "salelist_json")
    @ResponseBody
    public DataTableReturn findCFPSaleListAjax(String mobile, int draw,int start,int length) throws Exception{
        DataTable dataTable = new DataTable();
        dataTable.setDraw(draw);
        dataTable.setLength(length);
        dataTable.setStart(start);
        if(StringUtils.isBlank(mobile)){
            DataTableReturn dataTableReturn = new DataTableReturn();
            dataTableReturn.setData(new ArrayList<Object>());
            dataTableReturn.setDraw(0);
            dataTableReturn.setRecordsFiltered(0);
            dataTableReturn.setRecordsTotal(0);
            return dataTableReturn;
        }
        return cfplannerService.findCfpSaleList(mobile,dataTable);
    }


    /**
     *销售佣金明细
     * @param mobile
     * @return
     * @throws Exception
     */
    @RequestLogging("销售佣金明细")
    @RequestMapping(value ="/commission")
    public ModelAndView  findCFPCommissionList(String mobile) throws Exception{
        ModelAndView modelAndView = new ModelAndView("cfprelevant/commission");
        if(StringUtils.isNotBlank(mobile)){
            modelAndView.addObject("cfp",lcsListService.queryLcsInfo(mobile));
        }
        return modelAndView;
    }
    @RequestLogging("销售佣金明细数据")
    @RequestMapping(value ="/commission_json")
    @ResponseBody
    public DataTableReturn findCFPCommissionJson(CfpCommonRequest<CfpCommissionListResp> cfpCommonRequest,CfpCommissionListResp cfpCommissionListResp) throws Exception{
        if(StringUtils.isNotBlank(cfpCommissionListResp.getCustomerName())){
            cfpCommonRequest.setParams(cfpCommissionListResp);
        }
        return lcsSalesAndEarningService.findCfpCommissionDetailList(cfpCommonRequest);
    }


    /**
     * 理财师佣金收益合计
     * @param cfpCommonRequest
     * @return
     * @throws Exception
     */
    @RequestLogging("理财师佣金收益合计")
    @RequestMapping(value ="/commission_total_json")
    @ResponseBody
    public Double findCFPCommissionTotalJson(CfpCommonRequest<CfpCommissionListResp> cfpCommonRequest,CfpCommissionListResp cfpCommissionListResp) throws Exception{
        if(StringUtils.isNotBlank(cfpCommissionListResp.getCustomerName())){
            cfpCommonRequest.setParams(cfpCommissionListResp);
        }
        return lcsSalesAndEarningService.findCfpCommissionDetailTotalAmount(cfpCommonRequest);
    }




    /**
     * 理财师推荐收益明细
     * @param mobile
     * @return
     * @throws Exception
     */
    @RequestLogging("理财师推荐收益明细")
    @RequestMapping(value ="/recommend_profit")
    public ModelAndView  findRecommendProfitList(String mobile) throws Exception{
        ModelAndView modelAndView = new ModelAndView("cfprelevant/recommend-profit");
        if(StringUtils.isNotBlank(mobile)){
            modelAndView.addObject("cfp",lcsListService.queryLcsInfo(mobile));
        }
        return modelAndView;
    }

    @RequestLogging("理财师推荐收益明细数据")
    @RequestMapping(value ="/recommend_profit_json")
    @ResponseBody
    public LcsDetailResp findRecommendProfitListJson(String mobile) throws Exception{
        if(StringUtils.isNotBlank(mobile)){
           return  lcsListService.queryLcsInfo(mobile);
        }
        return null;
    }

    /**
     * 活动奖励明细
     * @param mobile
     * @return
     * @throws Exception
     */
    @RequestLogging("活动奖励明细")
    @RequestMapping(value ="/activity_reward")
    public ModelAndView  findActivityReward(String mobile) throws Exception{
        ModelAndView modelAndView = new ModelAndView("cfprelevant/activity-reward");
        if(StringUtils.isNotBlank(mobile)){
            modelAndView.addObject("cfp",lcsListService.queryLcsInfo(mobile));
        }
        return modelAndView;
    }


    /**
     *当前客户在投
     * @param mobile
     * @return
     * @throws Exception
     */
    @RequestLogging("当前客户在投")
    @RequestMapping(value ="/current_investor")
    public ModelAndView  finCurrentInvestor(String mobile) throws Exception{
        ModelAndView modelAndView = new ModelAndView("cfprelevant/current-investor");
        if(StringUtils.isNotBlank(mobile)){
            modelAndView.addObject("cfp",lcsListService.queryLcsInfo(mobile));
        }
        return modelAndView;
    }

    /**
     * 当前客户在投数据列表
     * @param mobile
     * @return
     * @throws Exception
     */
    @RequestLogging("当前客户在投数据列表")
    @RequestMapping("/current_investor_json")
    @ResponseBody
    public DataTableReturn findCurrentInvestor(String mobile,String searchText,int draw,int start,int length) throws Exception{
        DataTableReturn dataTableReturn = new DataTableReturn();
        dataTableReturn.setDraw(0);
        dataTableReturn.setData(new ArrayList<Object>());
        dataTableReturn.setRecordsFiltered(0);
        dataTableReturn.setRecordsTotal(0);
        if(StringUtils.isBlank(mobile)){
            return  dataTableReturn;
        }
        DataTable dataTable = new DataTable();
        dataTable.setStart(start);
        dataTable.setLength(length);
        dataTable.setDraw(draw);
        dataTableReturn = cfplannerService.findCurrentInvertorList(mobile,searchText,dataTable);
        return dataTableReturn;
    }

    /**
     * 当前客户在投数据合计
     * @param mobile
     * @return
     * @throws Exception
     */
    @RequestLogging("当前客户在投数据合计")
    @RequestMapping("/current_investor_amout_json")
    @ResponseBody
    public Double findCurrentInvestorAmout(String mobile,String searchText) throws Exception{
        if(StringUtils.isBlank(mobile)){
            return  0.00;
        }
       return cfplannerService.findCurrentInvertorAmount(mobile,searchText);
    }










}
