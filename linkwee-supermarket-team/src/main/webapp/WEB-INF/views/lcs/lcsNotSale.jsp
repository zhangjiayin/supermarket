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
	.col-xs-1, .col-sm-1, .col-md-1, .col-lg-1, .col-xs-2, .col-sm-2, .col-md-2, .col-lg-2, .col-xs-3, .col-sm-3, .col-md-3, .col-lg-3, .col-xs-4, .col-sm-4, .col-md-4, .col-lg-4, .col-xs-5, .col-sm-5, .col-md-5, .col-lg-5, .col-xs-6, .col-sm-6, .col-md-6, .col-lg-6, .col-xs-7, .col-sm-7, .col-md-7, .col-lg-7, .col-xs-8, .col-sm-8, .col-md-8, .col-lg-8, .col-xs-9, .col-sm-9, .col-md-9, .col-lg-9, .col-xs-10, .col-sm-10, .col-md-10, .col-lg-10, .col-xs-11, .col-sm-11, .col-md-11, .col-lg-11, .col-xs-12, .col-sm-12, .col-md-12, .col-lg-12 {
    position: relative !important;
    min-height: 1px !important;
    padding-right: 5px !important;
    padding-left: 5px !important;
}
</style>

<div id="main-search" class="container-fluid">

	<div class="row">
		<div style="float:left;margin:10px 5px 10px 25px;width:100px;">时间段:</div>
		<div class="col-sm-6" style="min-width: 30px;">
			<div class=""> <!-- Only required for left/right tabs -->		
			  <ul class="nav nav-tabs">
			    <li class="active"><a class="tag btn btn-primary" href="#tab1" data-toggle="tab" id="today" name="tag" value="1">近30天</a></li>
	            <li><a class="tag btn btn-primary" href="#tab4" data-toggle="tab" id="lastMonth" name="tag" value="2">注册至今</a></li>
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
    
	<div class="row page-title"><div class="xxx">以下金额单位：元 ，人数单位：人</div></div>
	<div class="table-responsive">
        <table id="J_unsale_info_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>姓名</th>
		            <th>手机号</th>
		            <th>团队关系</th>
		            <th>累计出单金额</th>
		            <th>在投金额</th>
		            <th>注册时间</th>
		            <th>最近登陆时间</th>
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
			 startTime.setTime(nowTime.getTime()-29*24*60*60*1000);
			 startTime = startTime.format('yyyy-MM-dd');
			 endTime = nowTime.format('yyyy-MM-dd');
		 }else if(tag == 2){
			 startTime = "";
			 endTime = nowTime.format('yyyy-MM-dd');
		 }
		 $("#custDataViewStartDate").val(startTime); 
		 $("#custDataViewEndDate").val(endTime);
	 }
	 
	 $(document).ready(function() {
		fillTimeWithTag(1);
	 }); 
 
	var $db = $("#J_unsale_info_list").DataTable(
            {
                ordering:false,
                searching:false,
                lengthChange:false,
                paging:true,
                select:true,
                serverSide:true,
                scrollX:true,               
                ajax:{
                    url:"rest/statistics/lcs/unsaleList",
                    type:"POST",
                    dataSrc:function (result) {
                        return result.data;
                    },
                    data:function (d) {
                    	d.unSaleStartTime = $("#custDataViewStartDate").val();
                        d.unSaleEndTime = $("#custDataViewEndDate").val();
                        d.columns = [];
                        d.search = {};
                    }
                },
                columns:[
                    {data:"userName",render: function ( data, type, row ) {
                    	if(data == ''){
                    		return "--";
                    	}else{
                    		return data;
                    	}
                	}},
                    {data:"mobile",render: function ( data, type, row ) {
                    	return data.replace(/^(\d{3})\d{4}(\d+)/,"$1****$2");
                	}},
                    {data:"teamDepth",render: function ( data, type, row ) {
                    	if(data == 1){
                    		return "直推";
                    	}else{
                    		return data+"级";
                    	}
                	}},
                    {data:"totalSaleAmount"},
                    {data:"investingAmount"},
                    {data:"regTime"},
                    {data:"lastLoginTime"},
                    {data:"mobile",render: function ( data, type, row ) {
                    	var operation = '<a class="btn btn-sm btn-primary btn-icon ui-redirect" href="javascript:;" data-title="了解详情" data-url="/rest/statistics/lcs/detail?mobileOrName='+data+'" ><i class="fa fa-edit"></i>了解详情</a>';    
                    	return operation;                  	
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
	$db.draw();
	
	$('#dataStatisticsViewSearhBtn').click(function() {
		$db.draw();
	});
	
	$('.tag').click(function(e) {
		var tag = $(e.target).attr("value");
		fillTimeWithTag(tag);
		$db.draw();
	});
		
 </script>