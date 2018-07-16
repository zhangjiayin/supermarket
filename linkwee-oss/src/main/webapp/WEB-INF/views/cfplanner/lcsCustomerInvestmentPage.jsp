<%@ page language="java" pageEncoding="utf-8"%>


<div style="width: 100%;height: 100%">
<table id="lcsCustomerInvestmentDg" title="数据列表"/>
</div>

  <div id="lcsCustomerInvestmentToolbar"style="display: none;">
        <div>
        	<form id="lcsCustomerInvestmentSearch">   
		        <input name="mobile"  class="easyui-textbox" style="width:200px" value="${mobile}" prompt="理财师手机号码">
		        <a href="javascript:;" class="easyui-linkbutton J_search" >&nbsp;&nbsp;查询&nbsp;&nbsp;</a>
		        <a  href="javascript:;" class="easyui-linkbutton J_export" >导出EXCEL</a>
	        </form>
        </div>
    </div>

<script src="app/cfplanner/lcsSalesAndEarning/model/lcsCustomerInvestmentModel.js"></script>
<script src="app/cfplanner/lcsSalesAndEarning/lcsCustomerInvestment.js"></script>