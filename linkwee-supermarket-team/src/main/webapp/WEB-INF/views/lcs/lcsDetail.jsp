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
	.fouse {
	    float: right;
	}
</style>

<input type="hidden" value="${detailResp.mobile}" name="mobile"/>

<div class="row page-title">基本信息<div class="btn btn-primary fouse" value="${detailResp.fouseStatus}">${detailResp.fouseStatusDesc}</div></div>
<div class="row">
	<div class="col-sm-3">姓名：${detailResp.userName}</div>
	<div class="col-sm-3">手机号码：${detailResp.secretMobile}</div>
	<div class="col-sm-3">当前职级：${detailResp.level}</div>
	<div class="col-sm-3">在投金额：${detailResp.investingAmount}</div>
	<div class="col-sm-3">注册时间：${detailResp.regTime}</div>
	<div class="col-sm-3">首投时间：${detailResp.firstInvestTime}</div>
	<div class="col-sm-3">最近登陆时间：${detailResp.lastLoginTime}</div>
	<div class="col-sm-3">上级理财师：${detailResp.parentUserName}</div>
	<div class="col-sm-3">上级手机号：${detailResp.parentMobile}</div>
	<div class="col-sm-3">团队关系： ${detailResp.teamDepth}</div>
</div>

<br/><br/>

<div class="row page-title">出单信息<div class="xxx"></div></div>

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
    	<div style="float:left;margin:10px 5px 10px 25px;width:100px;">出单状态：</div>
		<div class="col-sm-6" style="min-width: 30px;">
			<div class=""> <!-- Only required for left/right tabs -->		
			  <ul class="nav nav-tabs">
			    <li class="active"><a class="btnTag btn btn-primary productStatus" data-toggle="tab" name="tag" value="0">全部</a></li>
	            <li><a class="btnTag btn btn-primary productStatus"  data-toggle="tab" name="tag" value="1">已回款</a></li>
	            <li ><a class="btnTag btn btn-primary productStatus" data-toggle="tab" name="tag" value="2">待回款</a></li>
	            <li ><a class="btnTag btn btn-primary productStatus" data-toggle="tab" name="tag" value="3">部分回款</a></li>
			  </ul>
			</div>       
        </div>
    </div>
     
    <div class="row page-title"><div class="xxx">以下金额单位：元 ，产品期限单位：天，人数单位：人</div></div>
    
    <div class="row">
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat blue">
	            <div class="detailsDef">
	                <div id="saleAmount" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">出单金额
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat green">
	            <div class="detailsDef">
	                <div id="saleYearAmount" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">出单年化
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat red">
	            <div class="detailsDef">
	                <div id="myFeeAmount" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">为我贡献佣金
	            </a>
	        </div>
	    </div>
	    
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat blue">
	            <div class="detailsDef">
	                <div id="directNumber" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">直推成员
	            </a>
	        </div>
	    </div>    
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat green">
	            <div class="detailsDef">
	                <div id="profitNumber" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">三级团队
	            </a>
	        </div>
	    </div>	        
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat yellow">
	            <div class="detailsDef">
	                <div id="teamNumber" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">所有下级
	            </a>
	        </div>
	    </div>	            
	</div>
       	
	<div class="table-responsive">
        <table id="J_sale_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>出单平台</th>
		            <th>出单金额</th>
		            <th>产品期限</th>
		            <th>我的佣金</th>
		            <th>出单时间</th>
		            <th>预期到期时间</th>
		            <th>产品名称</th>
		            <th>投资状态</th>
                </tr>
            </thead>
        </table>
    </div>  
    
</div>

 <script type="text/javascript" src="app/common/util.js"></script>
 <script type="text/javascript" > 
	var user_mobile = "${detailResp.mobile}";
	
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
	 
	 function loadView(path,params){	
		 $.getJSON(path,params, function (json) {			 
			 $('#saleAmount').text(json.data.saleAmount);
			 $('#saleYearAmount').text(json.data.saleYearAmount);
			 $('#myFeeAmount').text(json.data.myFeeAmount);
			 $('#directNumber').text(json.data.directNumber);
			 $('#profitNumber').text(json.data.profitNumber);	
			 $('#teamNumber').text(json.data.teamNumber);
		 });
	 }
	 
	 var productStatus = $(".active .productStatus").attr("value"); 	
	
	var $db = $("#J_sale_list").DataTable(
            {
                ordering:false,
                searching:false,
                lengthChange:false,
                paging:true,
                select:true,
                serverSide:true,
                scrollX:true,
                
                ajax:{
                    url:"rest/statistics/lcs/detail/saleList",
                    type:"POST",
                    dataSrc:function (result) {
                        return result.data;
                    },
                    data:function (d) {
                    	d.startTime = $("#custDataViewStartDate").val();
                        d.endTime = $("#custDataViewEndDate").val();
                        d.productStatus = productStatus; 
                        d.lcsMobile = user_mobile;
                        d.columns = [];
                        d.search = {};
                    }
                },
                columns:[
                    {data:"orgName"},
                    {data:"saleAmount"},
                    {data:"productDeadLineMinValue"},
                    {data:"myFeeAmount"},
                    {data:"saleTime"},
                    {data:"saleEndTime"},
                    {data:"productName"},
                    {data:"investStatus",render: function ( data, type, row ) {
                    	if(data == 1 || data == 2){
                    		return "待回款";
                    	}else if(data == 3){
                    		return "已回款";
                    	}else if(data == 4){
                    		return "部分回款";
                    	}
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
		var params=jsonFromt($('#dataStatisticsViewSearh').serializeArray());	
		params.productStatus = productStatus;
		params.lcsMobile = user_mobile;
		var path = 'rest/statistics/lcs/detail/saleInfo';
		loadView(path,params);
		$db.draw();
	});
	$('.tag').click(function(e) {
		var tag = $(e.target).attr("value");
		fillTimeWithTag(tag);
		var params=jsonFromt($('#dataStatisticsViewSearh').serializeArray());
		params.productStatus = productStatus;
		params.lcsMobile = user_mobile;
		var path = 'rest/statistics/lcs/detail/saleInfo';
		loadView(path,params);	
		$db.draw();
	});
	 
	 $(document).ready(function() {
		fillTimeWithTag(1);
		var params=jsonFromt($('#dataStatisticsViewSearh').serializeArray());
		params.productStatus = productStatus;
		params.lcsMobile = user_mobile;
		var path = 'rest/statistics/lcs/detail/saleInfo';
		loadView(path,params);	
		$db.draw();
	 }); 
		
	$('.productStatus').click(function(e) {
		var tag = $(e.target).attr("value");
		productStatus = tag;
		var params=jsonFromt($('#dataStatisticsViewSearh').serializeArray());
		params.productStatus = productStatus;
		params.lcsMobile = user_mobile;
		var path = 'rest/statistics/lcs/detail/saleInfo';
		loadView(path,params);	
		$db.draw();
	});
	
	$('.fouse').click(function(e) {
		var status = $(e.target).attr("value");
		$.get("rest/statistics/lcs/fouse/record",{cfpMobile:user_mobile,status:status},function (result) {
			if(status == 1){
				$(".fouse").attr("value",0)
				$(".fouse").html("取消关注");
			}else{
				$(".fouse").attr("value",1)
				$(".fouse").html("加入关注");
			}
        });
	});
 </script>