<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      <%
  request.setCharacterEncoding("UTF-8") ;
 %>



<div class="page-header">
			<h4>可派发红包、销售及收益状况</h4>
		</div>
		<div class="uk-blank"></div>
		
		<div class="row">
			<div class="col-sm-8">可派发红包状况：</div>
		</div>	
<br>
		<table style="border:1px solid #dcdcdc;">
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="120px">红包金额（元）</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">投资金额限制（元）</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">适用平台</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">适用产品</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">首投限制</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">红包过期时间</td>
		  </tr>
		  <c:forEach  items="${dtl.hongbaoList}" var="item">
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="120px">${item.hongbaoAmount}</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">${item.investLimit}</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">${item.platform}</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">${item.product}</td>
		   	<td style="border:1px solid #dcdcdc;" width="120px">${item.firstInvestLimit}</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">${item.expireTime}</td>
		  </tr>
		  </c:forEach>
		</table>
		
		<br>
<div class="row">
			<div class="col-sm-8">理财师销售状况：</div>
		</div>	
<br>
		<table style="border:1px solid #dcdcdc;">
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="120px">累计销售额（元）</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">累计销售笔数（笔）</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">客户在投总额（元）</td>
		  </tr>
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="120px">${dtl.totalSaleAmount}</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">${dtl.totalSaleCount}</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">${dtl.currInvestAmount}</td>
		  </tr>
		</table>
		<br>
	<div class="row">
			<div class="col-sm-8">理财师历史累计收益：</div>
		</div>	

<br>
		<table style="border:1px solid #dcdcdc;">
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="120px">客户销售佣金（元）</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">理财师推荐奖励（元）</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">活动奖励（元）</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">leader奖励（元）</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">累计收益（元）</td>
		  </tr>
		  <tr style="border:1px solid #dcdcdc;">
		    <td style="border:1px solid #dcdcdc;" width="120px">${dtl.fee}</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">${dtl.allowance}</td>
		     <td style="border:1px solid #dcdcdc;" width="120px">${dtl.activityReward}</td>
		    <td style="border:1px solid #dcdcdc;" width="120px">${dtl.leaderReward}</td>
		   	<td style="border:1px solid #dcdcdc;" width="120px">${dtl.totalProfit}</td>
		  </tr>
		</table>





