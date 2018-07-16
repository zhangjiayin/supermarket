<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>




<div class="container-fluid">
	<div class="table-responsive">
		<table id="J-salelist" class="table table-bordered" data-url="rest/investor/getInvestorListNew" data-order="false" data-xtoolbars="#template-search" data-defer="true">
		<thead>
			<tr>
				<th data-name="id" data-format="id:serial">序号</th>
				<th data-name="userName" data-format='userName:nameFormat'>姓名</th>
				<th data-name="mobile">电话</th>
				
				
                <th data-name="totalInvestAmount" data-format="totalInvestAmount:linkFormat">投资总额(元)</th>
                <th data-name="totalProfit">收益总额(元)</th>
                <th data-name="investCount">投资笔数</th>
                <th data-name="currInvestAmount">在投总金额(元)</th>
			 	<th data-name="rectInvestTime">最后投资时间</th>
				
				<th data-name="cfplannerName" data-format='cfplannerName:nameFormat'>所属理财师</th>
				
				<th data-name="cfplannerMobile">理财师账号</th>
				<th data-name="cfplannerHeadImage" data-format="cfplannerHeadImage:imgFormat">理财师头像</th>
				<th data-name="freindsCount" data-format="freindsCount:linkFormat">邀请朋友</th>
				<th data-name="createTime">注册时间</th>
				<th data-name="mobile" data-format="mobile:linkFormat">操作</th>
			</tr>
		</thead>
	</table>
	</div>

<script type="text/javascript">
    var linkFormat = function (data,type,full,meta) {
        var num = Number(data);
        var title = "";
        if(!full){
            return "";
        }
        var url = "";
        switch (meta.col){

            case 3:
                num = new Number(data);
                title = "投资明细";
                url = 'rest/investorInvest/investRecordPage?userId='+full.userId;
                break;
            case 11:
                num = new Number(data);
                title = "邀请列表";
                url = 'rest/investor/freindsListPage?userId='+full.userId;
                break;
            case 13:
            	num = "详情";
                title = "投资者详情";
                url = 'rest/investor/getInvestorDetail?mobile='+data;
                break;
        }
        return data?'<a class="ui-redirect" data-title="'+title+'" data-url="'+url+'">'+num+'</a>':'0';
    }
   var $datatables =  $("#J-salelist").linkweeTable();
   
   var nameFormat = function (data,type,full,meta) {
   	if(data!=null && data !='') {
   		return data;
   	} else {
   		return "--";
   	} 
   }
   
   var imgFormat = function (data,type,full,meta) {
	   	if(data!=null && data !='') {
	   		return '<img src="'+data+'" data-action="zoom"  height="100" />';
	   	} else {
	   		return "--";
	   	} 
	   }
</script>

<script type="text/linkwee-template" id="template-search">
<form>
	 	<div class="input-group">
			<input name="nameOrmobile"  class="form-control" style="width:200px"  placeholder="输入姓名或手机号...">
			<button class="btn btn-default J_search" role="button">查询</button>
		</div>
</form>
</script>
</div>




