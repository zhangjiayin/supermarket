<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 request.setAttribute("ctx", request.getContextPath());
%>

	<input id="path" type="hidden" value="${ctx}" />
    <table id="investedDg" title="他邀请的客户"  style="width:100%;" ></table>
    <div id="toolbar"style="display: none;">
        <div>
        	<form id="investedForm">
	        	注册时间：<input name="regTimeStart" class="easyui-datebox" style="width:110px"> 至
	        	<input name="regTimeEnd" class="easyui-datebox" style="width:110px">
	        	<input name="customer" type="hidden" value="${customer}"/>
		        <a href="#" id="queryButton" class="easyui-linkbutton" >&nbsp;&nbsp;查 询&nbsp;&nbsp;</a>
		        <a href="#" id="investedExcelBtn" class="easyui-linkbutton" iconCls="icon-search">导出EXCEL</a>
	        </form>
        </div>
    </div>
  

<script type="text/javascript" src="${ctx}/widget/js/common/util.js"></script>
<script type="text/javascript" src="${ctx}/widget/js/invest/investedList.js"></script>
