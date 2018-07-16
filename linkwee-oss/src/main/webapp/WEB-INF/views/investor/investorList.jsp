<%@ page language="java" pageEncoding="utf-8"%>
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
    <table id="dg" title="投资客户列表"  style="width:100%;" ></table>
    <div id="investorToolbar"style="display: none;">
        <div>
        	<form id="investorQueryForm">
	        	客户： <input placeholder="姓名或手机号码"  name="customer" style="width:110px">
	        	<!-- 注册时间：<input name="regTimeStart" class="easyui-datebox" style="width:110px"  > 至
	        	<input name="regTimeEnd" class="easyui-datebox" style="width:110px" > -->
		         <a href="#" id="queryButton" class="easyui-linkbutton" >&nbsp;&nbsp;查 询&nbsp;&nbsp;</a>
		        <!--  <a href="#" id="excelInvestorButton" class="easyui-linkbutton" >导出EXCEL</a> -->
	        </form>
        </div>
    </div>
    <div id="investorWin" class="easyui-window"  data-options="iconCls:'icon-save',closed:true">

<script type="text/javascript" src="app/common/util.js"></script>
<script type="text/javascript" src="app/investor/investor.js"></script>

