<%@ page language="java" pageEncoding="utf-8"%>

<div style="width: 100%;height: 100%">
<table id="lcsActivityProfitDetailDg" title="数据列表"/>
</div>

  <div id="lcsActivityProfitDetailToolbar"style="display: none;">
        <div>
        	<form id="lcsActivityProfitDetailSearch">   
        		日期从: <input id="lcsActivityProfitDetailStartDate" name="startDate" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'lcsActivityProfitDetailEndDate\')}'})"/>
				到: <input id="lcsActivityProfitDetailEndDate" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'lcsActivityProfitDetailStartDate\')}'})"/>
		        <input name="mobile"  class="easyui-textbox" style="width:200px" value="${mobile}" prompt="理财师手机号码">
		        <a href="javascript:;" class="easyui-linkbutton J_search" >&nbsp;&nbsp;查询&nbsp;&nbsp;</a>
		        <a  href="javascript:;" class="easyui-linkbutton J_export" >导出EXCEL</a>
	        </form>
        </div>
    </div>
    

<script src="app/cfplanner/lcsSalesAndEarning/model/lcsActivityProfitDetailModel.js"></script>
<script src="app/cfplanner/lcsSalesAndEarning/lcsActivityProfitDetail.js"></script>