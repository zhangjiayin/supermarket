<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 request.setAttribute("ctx", request.getContextPath());
%>

	<input id="path" type="hidden" value="${ctx}" />
    <table id="investListDg" title="投资记录"  style="width:600px;height:550px" ></table>
    <div id="toolbar"style="display: none;">
        <div>
        	<form id="queryForm">
	        	客户： <input class="easyui-textbox" name="customer" style="width:110px">
	        	注册时间：<input name="" class="easyui-datebox" style="width:110px"> 至
	        	<input name="" class="easyui-datebox" style="width:110px">
	        	
		        <a href="#" id="queryButton" class="easyui-linkbutton" >&nbsp;&nbsp;查 询&nbsp;&nbsp;</a>
	        </form>
        </div>
    </div>

<script type="text/javascript" src="${ctx}/widget/js/common/util.js"></script>
<script type="text/javascript" src="${ctx}/widget/js/invest/invest.js"></script>
