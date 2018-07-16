<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="com.linkwee.xoss.rbac.PermissionSign" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/cfpAchievement/getCfpAchievement" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="cfpName" >理财师姓名</th>
                    <th data-name="cfpMobile" data-format="cfpMobile:cumsMobile">理财师手机</th>
                    <th data-name="city" >所在城市</th>
                    <th data-name="investorName">投资人姓名</th>
                    <th data-name="investorMobile"  data-format="investorMobile:cumsMobile">投资人手机号码</th>
                    <th data-name="investAmount">投资金额</th>
                    <th data-name="platfromName">投资平台</th>
                    <th data-name="bizTime">投资时间</th>
                    <th data-name="platformFirstInvest" data-format="platformFirstInvest:cumsFirstInvest">投资平台首投</th>
                    <th data-name="toobeiFirstInvest" data-format="toobeiFirstInvest:cumsFirstInvest">T呗首投</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cumsId = function (data,type,full,meta) {
        	return meta.row+1;
        }
        
        var cumsMobile = function (data,type,full,meta) {
        	return data?data.substring(0,data.length-8)+'****'+data.substring(data.length-4,data.length):"--";
        }
        
        var cumsFirstInvest = function (data,type,full,meta) {
        	if(data == 1){
        		return '是';
        	} else {
        		return '否';
        	}
        	
        }

       var $db= $("#J-newslist").linkweeTable();
       
       function exportCfpAchievement(){
    	   $form = $("#exportCfpAchievement").attr("action","rest/cfpAchievement/exportCfpAchievement");
   		$form[0].submit();
   		return false;
       }
    </script>
    <script type="text/linkwee-template" id="template-search">
        <form id="exportCfpAchievement">
        城市<select id="name" name="name">
			<option value="">全部</option>
			<option value="长沙">长沙</option>
            <option value="北京">北京</option>
            <option value="上海">上海</option>
			<option value="广州">广州</option>
			<option value="深圳">深圳</option>
			<option value="天津">天津</option>
			<option value="重庆">重庆</option>
			<option value="杭州">杭州</option>
			<option value="南京">南京</option>
			<option value="成都">成都</option>
			<option value="武汉">武汉</option>
         </select>
&nbsp;&nbsp;&nbsp;&nbsp;
		投资时间
		<input id="startTimeForSearch" name="startTimeForSearch" class="Wdate" type="text" value ="" onfocus="WdatePicker()" />
		至
		<input id="endTimeForSearch" name="endTimeForSearch" class="Wdate" type="text" value ="" onfocus="WdatePicker()" />
&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-default btn-sm J_search" role="button">查询</button>
&nbsp;&nbsp;&nbsp;&nbsp;
<shiro:hasPermission name="<%=PermissionSign.LCS_ACHIEVEMENT_EXPORT%>">
 <a class="btn btn-default btn-sm  " href="javascript:exportCfpAchievement();"  data-title="导出数据" role="button"> 导出数据</a>
</shiro:hasPermission>
        </form>
 
    </script>
</div>
    
    
    


