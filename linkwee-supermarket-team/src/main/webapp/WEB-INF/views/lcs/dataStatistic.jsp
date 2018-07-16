<%@ page language="java" pageEncoding="utf-8"%>
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
</style>
<div class="container-fluid">
	
	<div class="row">
		<div class="col-sm-6" style="min-width: 30px;">
			<div class="tabbable"> <!-- Only required for left/right tabs -->
			  <ul class="nav nav-tabs">
			    <li class="active"><a class="tag" href="#tab1" data-toggle="tab" id="today" name="tag" value="1">今天</a></li>
	            <li><a class="tag" href="#tab2" data-toggle="tab" id="yestoday" name="tag" value="0">昨天</a></li>
	            <li ><a class="tag" href="#tab3" data-toggle="tab" id="lastWeek" name="tag" value="2">本月</a></li>
	            <li><a class="tag" href="#tab4" data-toggle="tab" id="lastMonth" name="tag" value="3">历史累计</a></li>
			  </ul>
			  <div class="tab-content">
			    <div class="tab-pane active" id="tab1">
			      <p></p>
			    </div>
			    <div class="tab-pane active" id="tab2">
			      <p></p>
			    </div>	
			    <div class="tab-pane active" id="tab3">
			      <p></p>
			    </div>
			    <div class="tab-pane active" id="tab4">
			      <p></p>
			    </div>	    
			  </div>
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
    
    <div class="row page-title">总体情况<div class="xxx">以下金额单位：万元 ，人数单位：人</div></div>
    
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
	                <div id="firstInvestAmount" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">猎财首投金额
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat yellow">
	            <div class="detailsDef">
	                <div id="repInvestAmount" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">猎财复投金额
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat blue">
	            <div class="detailsDef">
	                <div id="investingAmount" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">在投金额
	            </a>
	        </div>
	    </div>
	    
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat green">
	            <div class="detailsDef">
	                <div id="myFeeAmount" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">我的佣金
	            </a>
	        </div>
	    </div>    
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat yellow">
	            <div class="detailsDef">
	                <div id="saleYearAmount" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">出单年化
	            </a>
	        </div>
	    </div>	        
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat blue">
	            <div class="detailsDef">
	                <div id="saleNumber" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">出单人数
	            </a>
	        </div>
	    </div>	        
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat green">
	            <div class="detailsDef">
	                <div id="firstInvestNumber" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">猎财首投人数
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat yellow">
	            <div class="detailsDef">
	                <div id="repInvestNumber" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">猎财复投人数
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat blue">
	            <div class="detailsDef">
	                <div id="investingNumber" class="number"></div>
	            </div>
	            <a class="more" href="javascript:;">在投人数
	            </a>
	        </div>
	    </div>
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat green">
	            <div class="detailsDef">
	                <div id="teamNumber" class="number">11</div>
	            </div>
	            <a class="more" href="javascript:;">团队注册人数
	            </a>
	        </div>
	    </div>	
	    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-4">
	        <div class="dashboard-stat yellow">
	            <div class="detailsDef">
	                <div id="profitTeamNumber" class="number">11</div>
	            </div>
	            <a class="more" href="javascript:;">三级团队人数
	            </a>
	        </div>
	    </div>	    
	</div>
	
	<div class="row page-title">平台出单情况</div>
	
	<div class="table-responsive">
        <table id="J_platform_invest_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>平台名称</th>
		            <th>出单金额</th>
		            <th>出单年化</th>
		            <th>平台首投金额</th>
		            <th>平台复投金额</th>
		            <th>平台首投年化</th>
		            <th>平台复投年化</th>
		            <th>在投金额</th>
		            <th>出单人数</th>
		            <th>在投人数</th>
                </tr>
            </thead>
        </table>
    </div>
    
    <div class="row page-title">团队各层级出单情况</div>
	
	<div class="table-responsive">
        <table id="J_level_invest_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>团队层级</th>
		            <th>出单金额</th>
		            <th>出单年化</th>
		            <th>猎财首投金额</th>
		            <th>猎财复投金额</th>
		            <th>猎财首投年化</th>
		            <th>猎财复投年化</th>
		            <th>在投金额</th>
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
		var params=jsonFromt($('#dataStatisticsViewSearh').serializeArray());
		var path = 'rest/statistics/lcs/dataStatistic/statistics';
		loadView(path,params);
	 }); 

	 function loadView(path,params){	
		 $.getJSON(path,params, function (json) {			 
			 $('#saleAmount').text(json.data.saleAmount);
			 $('#firstInvestAmount').text(json.data.firstInvestAmount);
			 $('#repInvestAmount').text(json.data.repInvestAmount);
			 $('#investingAmount').text(json.data.investingAmount);
			 $('#myFeeAmount').text(json.data.myFeeAmount);
			 $('#saleYearAmount').text(json.data.saleYearAmount);	
			 $('#saleNumber').text(json.data.saleNumber);
			 $('#firstInvestNumber').text(json.data.firstInvestNumber);
			 $('#repInvestNumber').text(json.data.repInvestNumber);
			 $('#investingNumber').text(json.data.investingNumber);
			 $('#teamNumber').text(json.data.teamNumber);
			 $('#profitTeamNumber').text(json.data.profitTeamNumber);
		 });
	 }
	 
	$('#dataStatisticsViewSearhBtn').click(function() {
		var params=jsonFromt($('#dataStatisticsViewSearh').serializeArray());
		var path = 'rest/statistics/lcs/dataStatistic/statistics';
		loadView(path,params);
		$db.draw();
		$db2.draw();
	});
	$('.tag').click(function(e) {
		var tag = $(e.target).attr("value");
		fillTimeWithTag(tag);
		var params=jsonFromt($('#dataStatisticsViewSearh').serializeArray());
		var path = 'rest/statistics/lcs/dataStatistic/statistics';
		loadView(path,params);	
		$db.draw();
		$db2.draw();
	});
	
	var $db = $("#J_platform_invest_list").DataTable(
            {
                ordering:false,
                searching:false,
                lengthChange:false,
                paging:true,
                select:true,
                serverSide:true,
                scrollX:true,
                
                ajax:{
                    url:"rest/statistics/lcs/dataStatistic/platformSale",
                    type:"POST",
                    dataSrc:function (result) {
                        return result.data;
                    },
                    data:function (d) {
                    	d.startTime = $("#custDataViewStartDate").val();
                        d.endTime = $("#custDataViewEndDate").val();
                        d.columns = [];
                        d.search = {};
                    }
                },
                columns:[
                    {data:"orgName"},
                    {data:"saleAmount"},
                    {data:"saleYearAmount"},
                    {data:"platformFirstInvestAmount"},
                    {data:"platformRepInvestAmount"},
                    {data:"platformFirstInvestYearAmount"},
                    {data:"platformRepInvestYearAmount"},
                    {data:"investingAmount"},
                    {data:"saleNumber"},
                    {data:"investingNumber"}
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
	
	var $db2 = $("#J_level_invest_list").DataTable(
            {
                ordering:false,
                searching:false,
                lengthChange:false,
                paging:true,
                select:true,
                serverSide:true,
                scrollX:true,
                
                ajax:{
                    url:"rest/statistics/lcs/dataStatistic/levelSale",
                    type:"POST",
                    dataSrc:function (result) {
                        return result.data;
                    },
                    data:function (d) {
                    	d.startTime = $("#custDataViewStartDate").val();
                        d.endTime = $("#custDataViewEndDate").val();
                        d.columns = [];
                        d.search = {};
                    }
                },
                columns:[
                    {data:"level",render: function ( data, type, row ) {
                    	if(data == 1){
                    		return "直推";
                    	}else{
                    		return data + "级";
                    	}
                	}},
                    {data:"saleAmount"},
                    {data:"saleYearAmount"},
                    {data:"firstInvestAmount"},
                    {data:"repInvestAmount"},
                    {data:"firstInvestYearAmount"},
                    {data:"repInvestYearAmount"},
                    {data:"investingAmount"}
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
	
	$db2.draw();
    
 </script>