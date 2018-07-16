<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="assets/plugins/easyui/easyloader.js"  ></script>
<script type="text/javascript">
easyloader.base = 'assets/plugins/easyui/';
easyloader.theme = 'bootstrap';
easyloader.locale = 'zh_CN';

easyloader.modules.common = {
	js: 'common.js'
};
easyloader.modules.my97DatePicker = {
		js: '../../My97DatePicker/WdatePicker.js'
};
</script>
    <table id="investProfitDg" title="销售与收益"  style="width:100%;" ></table>
    <div id="investProfitToolbar"style="display: none;">
        <div>
        	<form id="investProfitQueryForm">
	        	客户： <input class="easyui-textbox" name="customer" style="width:110px">
	        	注册时间：<input name="regTimeStart" class="easyui-datebox" style="width:110px"> 至
	        	<input name="regTimeEnd" class="easyui-datebox" style="width:110px">
		        <a href="#" id="investProfitQueryButton" class="easyui-linkbutton" >&nbsp;&nbsp;查 询&nbsp;&nbsp;</a>
		        
		        <shiro:hasAnyRoles name="super_admin">
		        <a href="#" id="investProfitExportBtn" class="easyui-linkbutton" >导出EXCEL</a>
		        </shiro:hasAnyRoles>
	        </form>
        </div>
    </div>
    <div id="customerWin" class="easyui-window"  data-options="iconCls:'icon-save',closed:true">

<script type="text/javascript" src="app/common/util.js"></script>
<script type="text/javascript" src="app/investor/investProfit.js"></script>
