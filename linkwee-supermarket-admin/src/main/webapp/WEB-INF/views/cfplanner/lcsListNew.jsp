<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>

<div class="container-fluid">
	<div class="table-responsive">
	 <table id="J-salelist" class="table table-bordered" data-url="rest/lcsList/getLcsListNew" data-order="false" data-xtoolbars="#template-search" data-defer="true">
		<thead>
			<tr>
				<th data-name="id" data-format="id:serial">序号</th>
				<th data-name="userName" data-format='userName:nameFormat'>姓名</th>
				<th data-name="mobile">电话</th>
				<th data-name="jobGrade" data-format='jobGrade:linkFormat'>本月职级</th>
				
				<th data-name="totalSales" data-format="investmentTotalAmount:float"> 本月销售年化</th>
                <!-- <th data-name="countSales">销售笔数</th> -->
                
                <th data-name="fee" data-format="fee:linkFormat">本月 基础佣金(元)</th>
                <th data-name="allowance" data-format="allowance:linkFormat"> 本月推荐奖励(元)</th>
                <th data-name="directAllowance" data-format="directAllowance:linkFormat"> 本月直接管理津贴(元)</th>
                <th data-name="teamAllowance" data-format="teamAllowance:linkFormat"> 本月团队管理津贴(元)</th>
                <!-- <th data-name="activityReward" data-format="activityReward:linkFormat">活动奖励(元)</th>
                <th data-name="currInvestAmount" data-format="currInvestAmount:linkFormat">客户在投(元)</th> -->
                
				<th data-name="teamMemberCount" data-format="teamMemberCount:linkFormat">历史团队人数</th>
				<th data-name="customerCount" data-format="customerCount:linkFormat" >历史客户人数</th>
				<!-- <th data-name="createTime">注册时间</th> -->
				<th data-name="mobile" data-format="mobile:linkFormat">奖励及津贴</th>
				<th data-name="mobile" data-format="mobile:linkFormat">团队情况</th>
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
	            	num = data;
	        		title = "职级历史";
	        		url = 'rest/lcsList/levellist?mobile='+full.mobile;
	        		break;
	            case 5:
	                num = new Number(full.fee);
	               title = "销售佣金明细";
	               url = 'rest/cfplannerSale/feeDetailList?userId='+full.userId;
	               break;
	           case 6:
	                num = new Number(full.allowance);
	               title = "推荐收益明细";
	               url = 'rest/cfplannerSale/allowanceDetailList?userId='+full.userId;
	               break;
	           case 7:
	                num = new Number(full.directAllowance);
	               title = "直接管理津贴明细";
	               url = 'rest/lcsList/teamAllowanceList?userId='+full.userId;
	               break;
	           case 8:
	                num = new Number(full.teamAllowance);
	               title = "团队管理津贴明细";
	               url = 'rest/lcsList/teamAllowanceList?userId='+full.userId;
	               break;
	          /*  case 7:
	                num = new Number(full.activityReward);
	               title = "活动奖励明细";
	               url = 'rest/cfplannerSale/activityRewardList?userId='+full.userId;
	               break;
	           case 8:
	                num = new Number(full.currInvestAmount);
	               title = "当前客户在投";
	               url = 'rest/cfplannerSale/customerInvestList?userId='+full.userId;
	               break; */
	               
                case 9:
                     num = new Number(data);
                    title = "团队列表";
                    url = 'rest/lcsList/teamlist?mobile='+full.mobile;
                    break;
                case 10:
                     num = new Number(data);
                    title = "客户列表";
                    url = 'rest/lcsList/customelist/'+full.mobile;
                    break;
                case 11:
                	num = "奖励及津贴";
                    title = "奖励及津贴";
                    url = 'rest/lcsList/getRewardAllowance?mobile='+data;
                    break;
                case 12:
                	num = "团队详情";
                    title = "团队详情";
                    url = 'rest/lcsList/getLcsTeamDetail?mobile='+data;
                    break;
                case 13:
                	num = "详情";
                    title = "理财师详情";
                    url = 'rest/lcsList/getLcsDetailNew?mobile='+data;
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
       
       var jobGradeFormat = function (data,type,full,meta) {
          	if(data = "TA") {
          		return "见习";
          	} else if(data = "SM1") {
          		return "顾问";
          	} else if(data = "SM2") {
          		return "经理";
          	} else if(data = "SM3") {
          		return "总监";
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




