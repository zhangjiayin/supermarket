<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="assets/plugins/echarts/echarts.min.js"  ></script>
<script type="text/javascript" src="app/crmcfplevelrecord/crmcfplevelrecord-list.js"></script>

<div class="container-fluid">
	<div class="row">
	  <div class="col-md-4 "><h3 style="color: red;"><strong>职级、获得奖励及津贴的人数情况</strong></h3></div>
	</div>
	<div class="row" style="padding-top: 20px">
	  <div class="col-md-8">
	  		<strong> 时间筛选：</strong>
	  		<input id="startTime" placeholder="开始日期" name="startTime" class="Wdate"  style="margin-left: 20px;"  onfocus="WdatePicker({dateFmt:'yyyyMM',maxDate:'#F{$dp.$D(\'endTime\');}'})">
	  		 至
	  	 	<input id="endTime" placeholder="结束日期" name="endTime" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyyMM',minDate:'#F{$dp.$D(\'startTime\');}'})">
	  	 	<button id="queryList" class="btn btn-default btn-sm J_search" role="button">查询</button>
	  </div>
	</div>
	<div class="row" style=" margin-top: 30px;">
		<div class="col-md-10 col-md-offset-1">
			<table id="cfpLevelTable" class="table table-bordered">
		        <thead>
			        <tr>
			       		<th>月份</th>
			            <th>获销售佣金（人数）</th>
			            <th>获推荐奖励（人数）</th>
			            <th>获直接管理津贴（人数）</th>
			            <th>获团队管理津贴（人数）</th>
			            <th>总监（人数）</th>
			            <th>经理（人数）</th>
			            <th>顾问（人数）</th>
			            <th>见习（人数）</th>
			        </tr>
		        </thead>
		        <tbody>
		        </tbody>
		     </table>
		</div>
	</div>
	<div class="row" style=" margin-top: 50px;">
		<div class="col-md-6">
			<div class="row text-center">
				<h4><strong>每月获得奖励及津贴的人数</strong></h4>
			</div>
			<div id="awardEveryMonth" class="row text-center" style="width: 600px;height:400px;margin: 20px 10px 0 150px;">
			</div>
		</div>
		<div class="col-md-6">
			<div class="row text-center">
				<h4><strong>每月各职级人数情况</strong></h4>
			</div>
			<div id="levelEveryMonth" class="row text-center" style="width: 600px;height:400px;margin: 20px 10px 0 150px;">
			</div>
		</div>
	</div>
</div>
