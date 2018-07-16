package com.linkwee.web.controller.fee;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.linkwee.core.base.ResponseResult;
import com.linkwee.core.base.ReturnCode;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.util.DateUtils;
import com.linkwee.web.constant.ResponseConstant;
import com.linkwee.web.model.fee.FeePay;
import com.linkwee.web.model.fee.StatusSetReq;
import com.linkwee.web.request.FeeRequest;
import com.linkwee.web.service.FeePayService;

/**
 * Title: FeepayController Description: 佣金发放 Company: Copyright (c)
 * 深圳市前海领会科技有限公司-版权所有
 * 
 * @author jinbo.fu
 * @date 2016年5月17日上午10:32:45
 */
@Controller
@RequestMapping(value = "/feepay")
public class FeepayController {
	private static final Logger logger = LoggerFactory
			.getLogger(FeeDetailQueryController.class);
	@Resource
	private FeePayService feePayService;

	@RequestMapping(value = "init")
	public String init(Model model) {
		return "fee/fee-list";
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(FeeRequest feeRequest) {
		long start = System.currentTimeMillis();
		if (StringUtils.isEmpty(feeRequest.getMonth())) {
			String month=DateUtils.getNow("yyyyMM");
			feeRequest.setMonth(month);
		}
		DataTableReturn dataTableReturn  = feePayService.feePayResultQuery(feeRequest);
		logger.info("feepay/list time:" + (System.currentTimeMillis() - start)
				+ "ms");
		return dataTableReturn;
	}

	@RequestMapping(value = "/pay")
	@ResponseBody
	public Object feepay(String month) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		logsb.append("feepay|month=").append(month);
		try {
			if (StringUtils.isEmpty(month)) {
				result = new ResponseResult(ResponseConstant.FAILURE, "请选择年月");
				return result;
			}
			List<FeePay> list=feePayService.getUnPayList(month,null);
			feePayService.updateFeePayStatus(list); //把状态修改为正在发放
			ReturnCode srvResponse = feePayService.feePay(list);
			logsb.append("|returncode=").append(srvResponse.getCode())
					.append("|returnmsg=").append(srvResponse.getMessage());
			if (srvResponse.getCode() == 0) {
				// 操作成功
				result = new ResponseResult(ResponseConstant.SUCCESS, "请求受理成功");
			} else {
				result = new ResponseResult(ResponseConstant.FAILURE,
						srvResponse.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
			result = new ResponseResult(ResponseConstant.FAILURE, "操作失败");
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return result;
	}

	@RequestMapping(value = "/dataimport")
	@ResponseBody
	public Object dataImport(HttpServletRequest request) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		logsb.append("dataimport|");
		List<FeePay> datalist = new ArrayList<FeePay>(); // 待发放佣金
		try {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = mRequest.getFileMap();
			MultipartFile file = fileMap.get("file");
			if (null == file) {
				return new ResponseResult(ResponseConstant.FAILURE,
						"操作失败，导入文件不存在");
			}
			// 解析Excl文件，文件格式：部门/工号/手机号码/姓名/佣金/单据编号/年月
			InputStream input = file.getInputStream();
			Workbook workbook = null;
			if (file.getOriginalFilename().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(input);
			}
			else if (file.getOriginalFilename().endsWith("xls")) {
				workbook = new HSSFWorkbook(input);
			} else {
				return new ResponseResult(ResponseConstant.FAILURE,
						"操作失败，文件格式不对,要导入Excel格式文件。");
			}
			Sheet sheet = workbook.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			logsb.append("|hssfSheet.getLastRowNum()=").append(lastRowNum);
			for (int i = 1; i <= lastRowNum; i++) {
				logsb.append("|i=").append(i);
				Row row = sheet.getRow(i);
				String department = StringUtils.isEmpty(row.getCell(0)
						.getStringCellValue()) ? "" : row.getCell(0)
						.getStringCellValue().trim();
				String empno="";
				if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					empno = StringUtils.isEmpty(Double.toString(row
							.getCell(1).getNumericCellValue())) ? "" : Double
							.toString(row.getCell(1).getNumericCellValue());
				}
				else{
					empno = StringUtils.isEmpty(row.getCell(1)
							.getStringCellValue()) ? "" : row.getCell(1)
							.getStringCellValue().trim();
				}
				String mobile = "";
				if (row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					mobile = StringUtils.isEmpty(Double.toString(row
							.getCell(2).getNumericCellValue())) ? "" : Double
							.toString(row.getCell(2).getNumericCellValue());
				} else {
					mobile = StringUtils.isEmpty(row.getCell(2)
							.getStringCellValue()) ? "" : row.getCell(2)
							.getStringCellValue().trim();
				}
				String name = StringUtils.isEmpty(row.getCell(3)
						.getStringCellValue()) ? "" : row.getCell(3)
						.getStringCellValue().trim();
				double feeamount = (double) row.getCell(4)
						.getNumericCellValue();
				String billnumber = StringUtils.isEmpty(row.getCell(5)
						.getStringCellValue()) ? "" : row.getCell(5)
						.getStringCellValue().trim();
				String month="";
				if (row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					month = StringUtils.isEmpty(Double.toString(row
							.getCell(6).getNumericCellValue())) ? "" : Double
							.toString(row.getCell(6).getNumericCellValue());
				} else {
					month = StringUtils.isEmpty(getValue(row.getCell(6))) ? ""
							: getValue(row.getCell(6)).trim();
				}
				String saleuserno = "";
				if (!StringUtils.isEmpty(billnumber)) {
					saleuserno = billnumber.substring(0,
							billnumber.indexOf("_"));
				}
				if (StringUtils.isEmpty(mobile)) {
					logsb.append("|mobile is null,excel parsar end");
					break;
				}
				FeePay entity = new FeePay();
				entity.setDepartment(department);
				entity.setEmpno(empno);
				entity.setMobile(mobile);
				entity.setName(name);
				entity.setFeeamount(feeamount);
				entity.setBillnumber(billnumber);
				entity.setMonth(month);
				entity.setSaleuserno(saleuserno);
				entity.setCreatetime(new Timestamp(System.currentTimeMillis()));
				datalist.add(entity);
			}
			if (null != input) {
				input.close();
			}
			logger.info("|excl名称（" + workbook.getSheetName(0) + "）,总共有（"
					+ lastRowNum + "）行");

			// 调用数据导入接口
			ReturnCode srvResponse = feePayService.feeDataImport(datalist);
			logsb.append("|returncode=").append(srvResponse.getCode())
					.append("|returnmsg=").append(srvResponse.getMessage());
			if (srvResponse.getCode() == 0) {
				// 操作成功
				result = new ResponseResult(ResponseConstant.SUCCESS, "数据导入成功");
			} else {
				result = new ResponseResult(ResponseConstant.FAILURE,
						srvResponse.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
			logsb.append("|IOException e=" + e.getMessage());
			return new ResponseResult(ResponseConstant.FAILURE, "数据导入失败");
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=" + e.getMessage());
			return new ResponseResult(ResponseConstant.FAILURE, "数据导入失败");
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return result;
	}

	@RequestMapping(value = "/statusreset")
	@ResponseBody
	public Object statusReset(String month) {
		long start = System.currentTimeMillis();
		StringBuilder logsb = new StringBuilder();
		ResponseResult result = null;
		logsb.append("statusReset|month=").append(month);
		try {
			if (StringUtils.isEmpty(month)) {
				result = new ResponseResult(ResponseConstant.FAILURE, "请选择年月");
				return result;
			}
			StatusSetReq req = new StatusSetReq();
			req.setMonth(month);
			ReturnCode srvResponse = feePayService.statusReset(req);
			logsb.append("|returncode=").append(srvResponse.getCode())
					.append("|returnmsg=").append(srvResponse.getMessage());
			if (srvResponse.getCode() == 0) {
				// 操作成功
				result = new ResponseResult(ResponseConstant.SUCCESS, "操作成功");
			} else {
				result = new ResponseResult(ResponseConstant.FAILURE,
						srvResponse.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logsb.append("|Exception e=").append(e.getMessage());
			result = new ResponseResult(ResponseConstant.FAILURE, "操作失败");
		} finally {
			long end = System.currentTimeMillis();
			logsb.append("|totaltime=").append(end - start);
			logger.info(logsb.toString());
		}
		return result;
	}

	private String getValue(Cell cell) {
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			return String.valueOf(cell.getNumericCellValue());
		} else {
			// 返回字符串类型的值
			return String.valueOf(cell.getStringCellValue());
		}
	}
}
