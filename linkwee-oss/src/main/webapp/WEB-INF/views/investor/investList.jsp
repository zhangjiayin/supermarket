<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
    <table id="investListDg" title="投资记录"  style="width:100%;height:550px" ></table>
    <div id="investRecordToolbar" style="display: none;">
        <div>
        	<form id="investRecordQueryForm">
	        	客户： <input class="easyui-textbox" name="customer" style="width:110px" value="${customer}">
	        	投资时间：<input name="regTimeStart" class="easyui-datebox" style="width:110px"> 至
	        	<input name="regTimeEnd" class="easyui-datebox" style="width:110px">
	        	
		        <a href="#" id="investRecordQueryButton" class="easyui-linkbutton" >&nbsp;&nbsp;查 询&nbsp;&nbsp;</a>
	        </form>
        </div>
    </div>

<script type="text/javascript" src="app/common/util.js"></script>
<script type="text/javascript" src="app/investor/invest.js"></script>
