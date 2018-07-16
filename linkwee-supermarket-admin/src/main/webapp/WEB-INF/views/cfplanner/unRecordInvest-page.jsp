<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<!-- layer弹层组件 -->
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" src="app/cfplanner/unRecordInvest/unRecordInvest-page.js"></script>

<table id="unRecordInvest-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
	           <th>客户姓名</th>
	           <th>手机号码</th>
	           <th>投资平台</th>
	           <th>购买金额</th>
	           <th>购买时间</th>
	           <th>期限</th>
	           <th>佣金(元)</th>
	           <th>佣金率(%)</th>
	           <th>截图</th>
	           <th>审核描述</th>
	           <th>报单时间</th> 
	           <th>审核时间</th>
				<th>晒单状态</th>
				<th>审核状态</th>
				<th>点赞次数</th>
	           <th>操作</th>
            </tr>
        </thead>
    </table>

	<script type="text/linkwee-template" id="template-tools">	
            <div class="row">
                <div class="col-sm-2">
                    <div class="input-group">
                        <input name="investorsUserName" id="investorsUserName" class="form-control"   placeholder="请输入投资者姓名" autocomplete="off">
                    </div>
                </div>
				<div class="col-sm-2">
                    <div class="input-group">                 	
						<input name="investorsMobiel" id="investorsMobiel" class="form-control"   placeholder="请输入投资者手机号码" autocomplete="off">	
                    </div>
                </div>
				<div class="col-sm-4">
					日期: <input id="startTime" name="startTime" class="easyui-datebox" style="width:110px" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})"> 至
					<input id="endTime" name="endTime" class="easyui-datebox" style="width:110px" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})">
				</div>
 				<div class="col-sm-1">
                    <div class="input-group">
						<select id="status" name="status" class="form-control">
							<option value="" selected="selected">全部</option>
							<option value="0">未审核</option>
			            	<option value="1">审核通过</option>
			            	<option value="2">审核未通过</option>
			        	</select>
                    </div>
                </div>
				<div class="col-sm-1">
                    <div class="input-group">
						<span class="input-group-btn">
                             <button class="btn btn-default J_search" role="button"><i class="fa fa-search"></i> 查询</button>
                        </span>
                    </div>
                </div>
            </div>
    </script>

