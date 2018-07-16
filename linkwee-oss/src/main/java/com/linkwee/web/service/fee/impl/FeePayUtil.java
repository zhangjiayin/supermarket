package com.linkwee.web.service.fee.impl;
//package com.linkwee.fee.service.impl;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import com.linkwee.web.model.fee.FeePay;
//import com.linkwee.web.model.fee.FeePayReq;
//
//public class FeePayUtil {
//	private static Logger logger = Logger.getLogger(FeePayUtil.class);
//	
//	/**
//	 * 查询待发放佣金数据
//	 * @param req
//	 * @return
//	 */
//	public synchronized static List<FeePay> getFeePayEntityList(FeePayReq req) {
//		long start = System.currentTimeMillis();
//		StringBuilder logsb = new StringBuilder();
//		List<FeePay> entitylist = new ArrayList<FeePay>();
//		StringBuilder idsb = new StringBuilder("(-1");
//		String month = req.getMonth();
//		List<String> saleuserlist = req.getSaleuserlist();
//		logsb.append("getFeePayEntityList|month").append(month);
//		String sql = getFeePayQuerySql(month, saleuserlist);
//		logger.debug("getFeePayEntityList|sql=" + sql);
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				int fid = rs.getInt("fid");
//				String f_department = rs.getString("f_department");
//				String f_emp_no = rs.getString("f_emp_no");
//				String f_mobile = rs.getString("f_mobile");
//				String f_name = rs.getString("f_name");
//				double f_fee_amount = rs.getDouble("f_fee_amount");
//				String f_bill_number = rs.getString("f_bill_number");
//				String f_month = rs.getString("f_month");
//				String f_sale_user_no = rs.getString("f_sale_user_no");
//				Timestamp f_create_time = rs.getTimestamp("f_create_time");
//				
//				FeePay entity = new FeePay();
//				entity.setId(fid);
//				entity.setDepartment(f_department);
//				entity.setEmpno(f_emp_no);
//				entity.setMobile(f_mobile);
//				entity.setName(f_name);
//				entity.setFeeamount(f_fee_amount);
//				entity.setBillnumber(f_bill_number);
//				entity.setMonth(f_month);
//				entity.setSaleuserno(f_sale_user_no);
//				entity.setCreatetime(f_create_time);
//				entitylist.add(entity);
//				
//				idsb.append(", ").append(fid);
//			}
//			idsb.append(")");
//			
//			//更新记录状态为发放中
//			StringBuilder sqlsb = new StringBuilder();
//			sqlsb.append("update t_fee_pay set f_status = 1 where fid in ").append(idsb);
//			logger.debug("|sql=" + sqlsb.toString());
//			pstmt.executeUpdate(sqlsb.toString());
//		} catch (SQLException e) {
//			e.printStackTrace();
//			logsb.append("|SQLException e=" + e.getMessage());
//		} finally {
//			DBUtil.close(pstmt, conn);
//			long end = System.currentTimeMillis();
//			logsb.append("|totaltime=").append(end - start);
//			logger.info(logsb.toString());
//		}
//		return entitylist;
//	}
//	
//	private static String getFeePayQuerySql(String month, List<String> saleuserlist) {
//		StringBuilder sqlsb = new StringBuilder();
//		sqlsb.append("select fid, f_department, f_emp_no, f_mobile, ");
//		sqlsb.append("f_name, f_fee_amount, f_bill_number, f_month, ");
//		sqlsb.append("f_sale_user_no, f_create_time ");
//		sqlsb.append("from t_fee_pay ");
//		sqlsb.append("where f_month = '").append(month).append("' ");
//		if (saleuserlist != null && saleuserlist.size() > 0) {
//			StringBuilder saleusercon = new StringBuilder("(");
//			int size = saleuserlist.size();
//			for (int i = 0; i < size; i++) {
//				if (i > 0) {
//					saleusercon.append(", '").append(saleuserlist.get(i)).append("'");
//				} else {
//					saleusercon.append("'").append(saleuserlist.get(i)).append("'");
//				}
//			}
//			saleusercon.append(")");
//			sqlsb.append("and f_sale_user_no in ").append(saleusercon).append(" ");
//		}
//		sqlsb.append("and f_status = 0 ");
//		sqlsb.append("limit 1000");
//		
//		return sqlsb.toString();
//	}
//}