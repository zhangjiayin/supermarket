<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<style type="text/css">
	.dashboard-stat .detailsDef .number {    
	  /* padding-top: 15px; */
	  text-align: center;
	  font-size: 34px;
	  /* line-height: 34px;
	  letter-spacing: -1px;
	  margin-bottom: 5px; */
	  font-weight: 300;
	  color: #fff;
	}
	.more{
		text-align: center;
	}
	.page-title {
		font-size: 20px !important;
	}
	.xxx{
		float:right;
		font-size: 16px !important;
	} 
	.nav-tabs {
	    border-bottom: 0px !important;
	}
</style>
<div class="container-fluid">
	
	<div class="row">
		<div style="float:left;margin:10px 5px 10px 25px;width:100px;">时间段:</div>
		<div class="col-sm-6" style="min-width: 30px;">
			<div class=""> <!-- Only required for left/right tabs -->		
			  <ul class="nav nav-tabs">
			    <li class="active"><a class="tag btn btn-primary" href="#tab1" data-toggle="tab" id="today" name="tag" value="1">今天</a></li>
	            <li><a class="tag btn btn-primary" href="#tab2" data-toggle="tab" id="yestoday" name="tag" value="0">昨天</a></li>
	            <li ><a class="tag btn btn-primary" href="#tab3" data-toggle="tab" id="lastWeek" name="tag" value="2">本月</a></li>
	            <li><a class="tag btn btn-primary" href="#tab4" data-toggle="tab" id="lastMonth" name="tag" value="3">历史累计</a></li>
			  </ul>
			</div>       
        </div>       
    	<div class="col-sm-4" style="min-width: 100px;">
           <form id="dataStatisticsViewSearh" style="top: 15px;"> 
			日期: <input id="custDataViewStartDate" name="startTime" class="easyui-datebox" style="width:110px" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'custDataViewEndDate\')}'})"> 至
        	<input id="custDataViewEndDate" name="endTime" class="easyui-datebox" style="width:110px" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'custDataViewStartDate\')}'})">
			<a href="javascript:void(0)"  id="dataStatisticsViewSearhBtn" class="easyui-linkbutton btn btn-default" >搜索</a>
		</form>
       </div>
    </div>
    
    <div class="row">
    	<div style="float:left;margin:10px 5px 10px 25px;width:100px;">猎财首/复投：</div>
		<div class="col-sm-6" style="min-width: 30px;">
			<div class=""> <!-- Only required for left/right tabs -->		
			  <ul class="nav nav-tabs">
			    <li class="active"><a class="btnTag btn btn-primary firstInvest" data-toggle="tab" name="tag" value="2">全部</a></li>
	            <li><a class="btnTag btn btn-primary firstInvest"  data-toggle="tab" name="tag" value="1">首投</a></li>
	            <li ><a class="btnTag btn btn-primary firstInvest" data-toggle="tab" name="tag" value="0">复投</a></li>
			  </ul>
			</div>       
        </div>
    </div>
    
    <div class="row">
   		<div style="float:left;margin:10px 5px 10px 25px;width:100px;">团队层级：</div>
		<div class="col-sm-6" style="min-width: 30px;">
			<div class=""> <!-- Only required for left/right tabs -->		
			  <ul class="nav nav-tabs">
			    <li class="active"><a class="btnTag btn btn-primary teamLevel" data-toggle="tab" name="tag" value="0">全部</a></li>
			    <c:forEach var="i" begin="1" end="${teamMaxDepth}">
			    	<c:choose> 				
					    <c:when test="${i == 1}">	
					    	<li><a class="btnTag btn btn-primary teamLevel" data-toggle="tab" name="tag" value="${i}">直推</a></li>		
					 	</c:when>      				
					    <c:otherwise> 
							<li><a class="btnTag btn btn-primary teamLevel" data-toggle="tab" name="tag" value="${i}">${i}级</a></li>
					  	</c:otherwise> 					
					</c:choose>
			    </c:forEach>
			  </ul>
			</div>       
        </div>
    </div>
    
    <div class="row page-title"><div class="xxx">以下金额单位：元 ，产品期限单位：天，人数单位：人</div></div>
       	
	<div class="table-responsive">
        <table id="J_team_invest_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>姓名</th>
		            <th>手机号</th>
		            <th>出单平台</th>
		            <th>出单金额</th>
		            <th>产品期限</th>
		            <th>我的佣金</th>
		            <th>出单时间</th>
		            <th>团队关系</th>
		            <th>猎财首/复投</th>
		            <th>操作</th>
                </tr>
            </thead>
        </table>
    </div>  
    
</div>

 <script type="text/javascript" src="app/common/util.js"></script>
 <script type="text/javascript" > 
 	Date.prototype.format = function(format) {
	   var date = {
	          "M+": this.getMonth() + 1,
	          "d+": this.getDate(),
	          "h+": this.getHours(),
	          "m+": this.getMinutes(),
	          "s+": this.getSeconds(),
	          "q+": Math.floor((this.getMonth() + 3) / 3),
	          "S+": this.getMilliseconds()
	   };
	   if (/(y+)/i.test(format)) {
	          format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
	   }
	   for (var k in date) {
	          if (new RegExp("(" + k + ")").test(format)) {
	                 format = format.replace(RegExp.$1, RegExp.$1.length == 1
	                        ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
	          }
	   }
	   return format;
	};
		
	 function fillTimeWithTag(tag){
		 var startTime = new Date(); 
		 var endTime = new Date(); 
		 var nowTime = new Date(); 
		 if(tag == 1){
			 startTime = nowTime.format('yyyy-MM-dd');
			 endTime = nowTime.format('yyyy-MM-dd');
		 }else if(tag == 0){
			 startTime.setTime(nowTime.getTime()-24*60*60*1000);
			 startTime = startTime.format('yyyy-MM-dd');
			 endTime.setTime(nowTime.getTime()-24*60*60*1000);
			 endTime = endTime.format('yyyy-MM-dd');
		 }else if(tag == 2){
			 startTime = nowTime.format('yyyy-MM')+"-01";
			 endTime = nowTime.format('yyyy-MM-dd');
		 }else if(tag == 3){
			 startTime = "";
			 endTime = nowTime.format('yyyy-MM-dd');
		 }
		 $("#custDataViewStartDate").val(startTime); 
		 $("#custDataViewEndDate").val(endTime);
	 }
	 
	 $(document).ready(function() {
		fillTimeWithTag(1);
	 }); 
	 
	 
	 
	 function drawTable(isFirstInvest,teamLevel){
		 
	 }
	 	
	var isFirstInvest = $(".active .firstInvest").attr("value"); 
	var teamLevel = $(".active .teamLevel").attr("value");
	var $db = $("#J_team_invest_list").DataTable(
            {
                ordering:false,
                searching:false,
                lengthChange:false,
                paging:true,
                select:true,
                serverSide:true,
                scrollX:true,
                
                ajax:{
                    url:"rest/statistics/lcs/teamSaleRecord/list",
                    type:"POST",
                    dataSrc:function (result) {
                        return result.data;
                    },
                    data:function (d) {
                    	d.startTime = $("#custDataViewStartDate").val();
                        d.endTime = $("#custDataViewEndDate").val();
                        d.isFirstInvest = isFirstInvest;//$(".active .firstInvest").attr("value");                      
                        d.teamLevel = teamLevel;//$(".active .teamLevel").attr("value");
                        d.columns = [];
                        d.search = {};
                    }
                },
                columns:[
                    {data:"userName"},
                    {data:"mobile",render: function ( data, type, row ) {
                    	return data.replace(/^(\d{3})\d{4}(\d+)/,"$1****$2");
                	}},
                    {data:"orgName"},
                    {data:"saleAmount"},
                    {data:"productDeadLineMinValue"},
                    {data:"myFeeAmount"},
                    {data:"saleTime"},
                    {data:"teamLevel",render: function ( data, type, row ) {
                    	if(data == 1){
                    		return "直推";
                    	}else{
                    		return data + "级";
                    	}
                	}},
                    {data:"isFirstInvest",render: function ( data, type, row ) {
                    	if(data == 1){
                    		return "首投";
                    	}else{
                    		return "复投";
                    	}
                	}},
                	{data:"mobile",render: function ( data, type, row ) {
                		return '<a class="btn btn-sm btn-primary btn-icon ui-redirect" href="javascript:;" data-title="了解详情" data-url="/rest/statistics/lcs/detail?mobileOrName='+data+'" ><i class="fa fa-edit"></i>了解详情</a>';                
                	}}
                ],
                columnDefs:[  ],
                language:{
                    "emptyTable":"没有数据",
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
	
	$('#dataStatisticsViewSearhBtn').click(function() {
		isFirstInvest = $(".active .firstInvest").attr("value"); 
		teamLevel = $(".active .teamLevel").attr("value");
		$db.draw();
	});
	$('.tag').click(function(e) {
		var tag = $(e.target).attr("value");
		fillTimeWithTag(tag);
		isFirstInvest = $(".active .firstInvest").attr("value"); 
		teamLevel = $(".active .teamLevel").attr("value");
		$db.draw();
	});
	
	$('.firstInvest').click(function(e) {
		var tag = $(e.target).attr("value");
		isFirstInvest = tag; 
		teamLevel = $(".active .teamLevel").attr("value");
		$db.draw();
	});
	
	$('.teamLevel').click(function(e) {
		var tag = $(e.target).attr("value");
		isFirstInvest = $(".active .firstInvest").attr("value"); 
		teamLevel = tag;
		$db.draw();
	});
 </script>