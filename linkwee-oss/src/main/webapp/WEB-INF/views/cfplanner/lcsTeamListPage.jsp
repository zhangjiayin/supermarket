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
<table id="lcsTeamDg" title="数据列表"/>
</div>



  <div id="lcsTeamToolbar"style="display: none;">
        <div>
        	<form id="lcsTeamSearch">   
        		日期从: <input id="lcsTeamStartDate" name="startDate" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'lcsTeamEndDate\')}'})"/>
				到: <input id="lcsTeamEndDate" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'lcsTeamStartDate\')}'})"/>
		        <input name="lcsNumber"  type="hidden" value="${lcsNumber}">
		        <input name="lcsMobile"  type="hidden" value="">
		        <a href="javascript:;"  class="easyui-linkbutton J_search" >&nbsp;&nbsp;查询&nbsp;&nbsp;</a>
		       <!--  <a  href="javascript:;" class="easyui-linkbutton J_export" >导出EXCEL</a> -->
	        </form>
        </div>
    </div>
<script src="app/cfplanner/lcs/model/lcsTeamModel.js"></script>
<script src="app/cfplanner/lcs/lcsTeam.js"></script>