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

<div style="width: 100%;height: 100%">
<table id="lcsSalesAndEarningDg" title="数据列表"/>
</div>



  <div id="lcsSalesAndEarningToolbar"style="display: none;">
        <div>
        	<form id="lcsSalesAndEarningSearch">
        		<input name="nameOrmobile"  class="easyui-textbox" style="width:200px"  prompt="姓名或者手机号码">
        		日期从: <input id="lcsSalesAndEarningStartDate" name="startDate" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'lcsSalesAndEarningEndDate\')}'})"/>
				到: <input id="lcsSalesAndEarningEndDate" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'lcsSalesAndEarningStartDate\')}'})"/>
		        <a href="javascript:;" class="easyui-linkbutton J_search" >&nbsp;&nbsp;查询&nbsp;&nbsp;</a>
		    <!--     <a  href="javascript:;" class="easyui-linkbutton J_export" >导出EXCEL</a> -->
	        </form>
        </div>
    </div>
    

<script src="app/cfplanner/lcsSalesAndEarning/model/lcsSalesAndEarningModel.js"></script>
<script src="app/cfplanner/lcsSalesAndEarning/lcsSalesAndEarning.js"></script>