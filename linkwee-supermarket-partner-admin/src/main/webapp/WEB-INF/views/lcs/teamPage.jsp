<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>

<div id="main-adv" class="container-fluid">
    <div class="table-responsive">
        <table id="J_teamList_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>理财师姓名</th>
		            <th>理财师手机</th>
		            <th>所在城市</th>
		            <th>投资人姓名</th>
		            <th>投资人手机</th>
		            <th>投资金额</th>
		            <th>投资平台</th>
		            <th>投资时间</th>
                </tr>
            </thead>
        </table>

    </div>
<script type="text/javascript">
	var init = false;
    var $db = $("#J_teamList_list").DataTable(
            {
                ordering:false,
                searching:false,
                lengthChange:false,
                paging:true,
                select:true,
                serverSide:true,
                scrollX:true,
                dom: '<"J_toolbar ux-toolber">frtip',
                ajax:{
                    url:"rest/statistics/lcs/team/lists",
                    type:"POST",
                    dataSrc:function (result) {
                    	//$("#context").html("查询区间内 : 成交  "+result.total_data.investCount+" 单 总额 "+result.total_data.totalAmt+" 元  理财师平均销售 "+result.total_data.avgAmt+" 元");
                        return result.data;;
                    },
                    data:function (d) {                  	
                        d.city = $('#city').val();
                        d.platfrom = $('#platfrom').val();
                        d.nameOrMobile = $('#nameOrMobile').val()  || (init ? "" : '${param.mobile}');
                        d.startDate = $('#startDate').val() || '${startDate}' ;
                        d.endDate = $('#endDate').val() || '${endDate}';
                        d.columns = [];
                        d.search = {};
                        $.post('rest/statistics/lcs/team/total',d, function(data) {
                        	$("#investCount").html(data.investCount+"单");
                        	$("#totalAmt").html(data.totalAmt);
                        	$("#totalYearpurAmt").html(data.totalYearpurAmt);
                        	$("#avgAmt").html(data.avgAmt);
                        	$("#totalFeeAmt").html(data.totalFeeAmt);
                        	$("#stockAmt").html(data.stockAmt);
                        	$("#stockYearpurAmt").html(data.stockYearpurAmt);
                        });
                        init = true;
                    }
                },
                columns:[
                    {data:"cfpName"},
                    {data:"cfpMobile"},
                    {data:"city"},
                    {data:"customerName"},
                    {data:"customerMobile"},
                    {data:"investAmt"},
                    {data:"platfromName"},
                    {data:"time"}
                ],
                columnDefs:[  ],
                language:{
                    "emptyTable":"没有数据表",
                    info:"显示第 _START_  至 _END_  项结果，共 _TOTAL_ 项",
                    infoEmpty:"",
                    paginate:{
                        "first":"首页",
                        "next":"下一页",
                        "previous":"上一页"
                    }
                }
            }
    );
    $(".J_toolbar").html($("#template-search").html());
    $(".J_search").click(function () {
         if(!$('#startDate').val() && init){
        	showError("销售开始日期不能为空");
      		return false;
         }
         if(!$('#endDate').val() && init){
        	showError("销售结束日期不能为空");
      		return false;
         }
        $db.draw();
    });

</script>

<script type="text/linkwee-template" id="template-search">
<div class="container-fluid">
 <div class="row">
	<div class="col-md-6 col-md-offset-6">
		<label  class="col-md-12 control-label"><font color="red"> 销售开始时间与结束时间不能为空，默认查询本月 1号 至 当日 数据</font></label>
	</div>
	<br>
	<br>
 	<div class="col-md-2 form-group">
 		<label for="city" class="col-md-3 control-label">城市:</label>
 		 <div class="col-md-9">
	      	<select name="city" id="city" class="form-control">
				<option value="">全部</option>
				<option value="北京市">北京</option>
           		<option value="上海市">上海</option>
				<option value="广州市">广州</option>
				<option value="深圳市">深圳</option>
				<option value="天津市">天津</option>
				<option value="重庆市">重庆</option>
				<option value="杭州市">杭州</option>
				<option value="南京市">南京</option>
				<option value="成都市">成都</option>
				<option value="武汉市">武汉</option>
			</select>
	    </div>
	</div>
	
	<div class="col-md-2 form-group">
 		<label for="platfrom" class="col-md-3 control-label">平台:</label>
 		 <div class="col-md-9">
	      	<select name="platfrom" id="platfrom" class="form-control">
				<option value="">全部</option>
				<c:forEach items="${platfroms}"  var="platfrom" >
					<option value="${platfrom.number }">${platfrom.name }</option>
				</c:forEach>
			</select>
	    </div>
	</div>
	
	<div class="col-md-2 form-group">
		<label for="nameOrMobile" class="col-md-4 control-label">理财师:</label>
 		 <div class="col-md-8">
	      	<input  class="form-control" name="nameOrMobile" id="nameOrMobile" value="${param.mobile}" type="text" placeholder="输入手机号或姓名"/>
	    </div>
	</div>
	
	<div class="col-md-4 form-group">
		<label  class="col-md-3 control-label">销售时间:</label>
		<div class="col-md-4">
			<input id="startDate" name="startDate" value=${startDate} class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})"/> 
	    </div>
	    <label  class="col-md-1 control-label"> - </label>
	    <div class="col-md-4">
	    	<input id="endDate" name="endDate" value=${endDate}
				class="Wdate"
				onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})"/>
				 
	    </div>
	</div>
	<div class="col-md-1">
		<a class="btn btn-default J_search" href="javascript:;" role="button">查询</a>
	</div>
</div>
<br>
<div class="row">
	<div class="col-md-12" >查询区间数据汇总： </div>
</div>
<br>
<div class="row">
	<div class="col-md-12" >
		共成交： &nbsp;&nbsp;<strong id="investCount">0单</strong>&nbsp;&nbsp;&nbsp;&nbsp;  投资总额：&nbsp;&nbsp;<strong id="totalAmt">0元</strong>&nbsp;&nbsp;&nbsp;&nbsp;     年化投资额：&nbsp;&nbsp;<strong id="totalYearpurAmt">0元</strong> &nbsp;&nbsp;&nbsp;&nbsp;  理财师平均销售额：&nbsp;&nbsp;<strong id="avgAmt">0元 </strong>&nbsp;&nbsp;&nbsp;&nbsp;   销售佣金总计：&nbsp;&nbsp;<strong id="totalFeeAmt">0元</strong>   
	</div>
</div>
<br>
<div class="row">
	<div class="col-md-12">
		存量投资额：&nbsp;&nbsp;<strong id="stockAmt">0元</strong>   &nbsp;&nbsp;&nbsp;&nbsp; 存量年化投资额：&nbsp;&nbsp;<strong id="stockYearpurAmt">0元</strong>     
	</div>
</div>
</div> 
<br>
<br>  
</script>
</div>

<!-- -->


