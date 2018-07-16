package com.linkwee.web.controller.act;

import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.exception.ServiceException;
import com.linkwee.core.util.JsonUtils;
import com.linkwee.core.util.StringUtils;
import com.linkwee.web.model.ActPersonAddfeeTicket;
import com.linkwee.web.model.CimOrginfo;
import com.linkwee.web.model.User;
import com.linkwee.web.request.act.AddFeeTicketRequest;
import com.linkwee.web.service.ActPersonAddfeeTicketService;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.web.service.CimProductService;
import com.linkwee.xoss.helper.DateUtils;
import com.linkwee.xoss.interceptors.DateConvertEditor;
import com.linkwee.xoss.util.RequestLogging;
import com.linkwee.xoss.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Set;

/**
*
* @描述： ActPersonAddfeeTicketController控制器
*
* @创建人： liqimoon
*
* @创建时间：2018年04月12日 17:24:11
*
* Copyright (c) 深圳领会科技有限公司-版权所有
*/
@Controller
@RequestMapping(value = "cim/actpersonaddfeeticket")
@RequestLogging("ActPersonAddfeeTicketController控制器")
public class ActPersonAddfeeTicketController {

   private static final Logger LOGGER = LoggerFactory.getLogger(ActPersonAddfeeTicketController.class);

   @Resource
   private ActPersonAddfeeTicketService actPersonAddfeeTicketService;

    @Autowired
    private CimProductService productService;

    @Autowired
    private CimOrginfoService cimOrginfoService;

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
     * 查看列表
     */
    @RequestMapping(value="initPage")
    public String initPage(Model model) {
        return "actpersonaddfeeticket/addfeeticket-page";
    }

    /**
     * 添加页面
     */
    @RequestMapping(value="addPage")
    public String getAddfeeTicketAddPage(Model model) {
        CimOrginfo req = new CimOrginfo();
        req.setStatus(1);
        model.addAttribute("platformList",cimOrginfoService.selectListByCondition(req));
        return "actpersonaddfeeticket/addfeeticket-add-page";
    }

    /**
     * 修改页面
     */
    @RequestMapping(value="{ticketId}/editPage")
    public String getAddfeeTicketEditPage(@PathVariable("ticketId")String ticketId,Model model) {
        try {
            if(StringUtils.isNotBlank(ticketId)){
                ActPersonAddfeeTicket addfeeTicket = new ActPersonAddfeeTicket();
                addfeeTicket.setTicketId(ticketId);
                addfeeTicket = actPersonAddfeeTicketService.selectOne(addfeeTicket);
                CimOrginfo req = new CimOrginfo();
                req.setStatus(1);
                model.addAttribute("platformList",cimOrginfoService.selectListByCondition(req));
                model.addAttribute("ticketId", ticketId);
                model.addAttribute("addfeeTicket",  addfeeTicket);
            }

        }catch (ServiceException e) {
            LOGGER.error("addfeeTicketEdit exception : {}", e.getMessage());
            model.addAttribute("errorMgs",e.getMessage());
        }  catch (Exception e) {
            LOGGER.error("addfeeTicketEdit exception : {}", e.getMessage());
            model.addAttribute("errorMgs","查询个人加拥券失败");
        }
        return "actpersonaddfeeticket/addfeeticket-edit-page";
    }

    /**
     * 发放个人加拥券页面
     * @param
     * @return
     */
    @RequestMapping(value="sendAddfeeTicketPage")
    public Object getSendAddfeeTicketPage(Model model) {
        return "actpersonaddfeeticket/send-addfeeticket-page";
    }

    @RequestMapping(value="list")
    @ResponseBody
    @RequestLogging("查看列表")
    public Object getAddFeeTickets(@RequestParam String  _dt_json) {
        DataTable dataTable = JsonUtils.fromJsonToObject(_dt_json, DataTable.class);
        return actPersonAddfeeTicketService.getPersonAddfeeTicketList(dataTable);
    }

    @RequestMapping(value="add")
    @ResponseBody
    @RequestLogging("添加加拥券")
    public Object addFeeTicketAdd(@Valid AddFeeTicketRequest ticketRequest, BindingResult bindResult, HttpSession session) {
        if(ResponseUtil.existsParamsError(bindResult)) {
            return ResponseUtil.getErrorParams(bindResult);
        }
        try {
            User user = (User) session.getAttribute("userInfo");
            ticketRequest.setOperator(user.getUsername());
            actPersonAddfeeTicketService.insertAddFeeTicket(ticketRequest);
            return new ResponseResult(true,"添加成功");
        } catch (Exception e) {
            LOGGER.error("addFeeTicketAdd exception : {}", e.getMessage());
        }
        return new ResponseResult(false,"添加失败");
    }


    @RequestMapping(value="edit")
    @ResponseBody
    @RequestLogging("编辑加拥券")
    public Object addFeeTicketEdit(@Valid AddFeeTicketRequest ticketRequest,BindingResult bindResult,HttpSession session) {
        if(ResponseUtil.existsParamsError(bindResult)) {
            return ResponseUtil.getErrorParams(bindResult);
        }
        try {
            if(StringUtils.isBlank(ticketRequest.getTicketId()))return new ResponseResult(true,"不存在的加拥券");
            User user = (User) session.getAttribute("userInfo");
            ticketRequest.setOperator(user.getUsername());
            actPersonAddfeeTicketService.updateAddFeeTicket(ticketRequest);
            return new ResponseResult(true,"更新成功");
        }catch (ServiceException e) {
            LOGGER.error("addFeeTicketEdit exception : {}", e.getMessage());
            return new ResponseResult(false,e.getMessage());
        } catch (Exception e) {
            LOGGER.error("addFeeTicketEdit exception : {}", e.getMessage());
        }
        return new ResponseResult(false,"更新失败");
    }


    /**
     * 下载导入模板
     * @param response
     * @param request
     */
    @RequestMapping(value = "downloadImportTemplate")
    public void downloadImportTemplate(HttpServletResponse response, HttpServletRequest request) {
        LOGGER.info("下载导入发放红包模板");
        // 下载本地文件
        String fileName = "sendRedPacket.xls"; // 文件的默认保存名letter
        // 读到流中
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/xls/template/sendRedPacket.xls");
        InputStream inStream=null;
        OutputStream outStream=null;
        try {
            inStream = new FileInputStream(path);// 文件的存放路径
            response.reset();
            /*设置头信息以及编码*/
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("UTF-8");
            /*设置下载时的文件名*/
            response.addHeader("Content-Disposition", "attachment; filename=\""+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
            outStream=response.getOutputStream();
            byte[] b = new byte[100];
            int len;
            while ((len = inStream.read(b)) > 0)
                outStream.write(b, 0, len);
        } catch (IOException e) {
            LOGGER.error("下载导入模板出现异常",e);
        }finally{
            try {
                if(inStream!=null){
                    inStream.close();
                }
                if(outStream!=null){
                    outStream.close();
                }
            } catch (IOException e) {
                LOGGER.error("下载导入模板关闭输入流时出现异常",e);
            }
        }
    }

    @RequestMapping(value="sendAddFeeTicket")
    @ResponseBody
    @RequestLogging("发放个人加拥券")
    public Object sendAddFeeTicket(HttpServletRequest request,HttpSession session) {
        try {
            User user = (User) session.getAttribute("userInfo");
            MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
            MultipartFile file  =  multipartRequest.getFile("file");
            String ticketId = multipartRequest.getParameter("ticketId");
            String startTime = multipartRequest.getParameter("startDate");
            String endTime = multipartRequest.getParameter("endDate");
            String remark = multipartRequest.getParameter("remark");
            if(StringUtils.isBlank(startTime) ){
                return new ResponseResult(false,"生效日期不能为空");
            }
            if(StringUtils.isBlank(endTime) ){
                return new ResponseResult(false,"过期日期不能为空");
            }
            Date startDate = DateUtils.parse(startTime,DateUtils.FORMAT_SHORT);
            Date endDate = DateUtils.parse(endTime,DateUtils.FORMAT_SHORT);

            Set<String> msg = actPersonAddfeeTicketService.sendAddFeeTicket(file,ticketId,startDate,endDate,user.getUsername(),remark);
            return new ResponseResult(true,"发送成功",msg);
        }catch (ServiceException e) {
            LOGGER.error("redpacketEdit exception : {}", e.getMessage());
            return new ResponseResult(false,e.getMessage());
        } catch (Exception e) {
            LOGGER.error("redpacketEdit exception : {}", e.getMessage());
        }
        return new ResponseResult(false,"发送失败");
    }

}
