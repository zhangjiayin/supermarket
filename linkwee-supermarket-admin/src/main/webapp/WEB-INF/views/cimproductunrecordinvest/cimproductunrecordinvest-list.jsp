<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<!-- Editor -->
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Editor/js/dataTables.editor.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Editor/css/editor.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Buttons/js/dataTables.buttons.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Buttons/css/buttons.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Select/css/select.dataTables.min.css"  />

<script type="text/javascript" src="app/cimproductunrecordinvest/cimproductunrecordinvest-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<form name="pay-example" id="pay-example">
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th data-name="id" data-format="id:tableIdFormat"><input name="select_all" value="1" id="example-select-all" type="checkbox"></th>
            <th>用户姓名</th>
            <th>用户手机</th>
            <th>投资平台</th>
            <th>产品期限(天)</th>
            <th>投资金额</th>
            <th>返现类型</th>
            <th>返现比例</th>
            <th>返现金额</th>
<!--             <th>注册时间</th> -->
            <th>投资时间</th>
            <th>返现状态</th>
            <th>上传文件名称</th>
            <th>上传投资记录时间</th>
            <th>发放返现时间</th>
            <th>返现操作人</th>
            <th>操作</th>
            </tr>
        </thead>
 
    </table>
</form>
<!-- layer弹层组件 -->
 <script type="text/javascript" src="assets/plugins/layer/layer.js"></script>   
 <script type="text/javascript" src="app/cimorginfo/formdata-convert-tojson.js"></script>
           <!-- /rest/news/tosave -->
 <script type="text/template" id="template-search">
		<div class="row">
          	  <div class="col-xs-8 ">
			        <form>
			        	<input name="userName"  class="easyui-textbox" style="width:120px"  placeholder="请输入投资者姓名">
						<input name="userMobile"  class="easyui-textbox" style="width:140px"  placeholder="请输入投资者手机号码">
                        <input type="text" name="investTime" id="investTime" style="width:130px" class="easyui-textbox" placeholder="请选择投资时间" />
						<input name="uploadTitle"  class="easyui-textbox" style="width:130px"  placeholder="请输入上传文件名称">
						<select id="payStatusId" name="payStatus">
							<option value="" selected="selected">全部</option>
							<option value="1">未返现</option>
			            	<option value="2">返现成功</option>
			            	<option value="3">返现失败</option>
			        	</select>
			        	<button class="btn btn-default btn-sm J_search" role="button">查询</button>
			        </form>
			  </div>
          	  <div class="col-xs-4 text-right">
<%-- 				<shiro:hasPermission name="<%=PermissionSign.PRODUCT_BATCH_CHECK%>"> </shiro:hasPermission>--%>
<a class="btn btn-info ui-redirect" href="javacript:void(0);" data-title="上传日志" data-url="/rest/cim/cimproductunrecordinvest/logs" role="button"><i class="fa fa-plus"></i>上传日志</a>
					<%--<button type="button" title="上传日志" id="partAuditButton" class="btn btn-info ">上传日志</button>--%>
					<button type="button" title="批量返现" id="payAuditButtonsss" class="btn btn-info Pay_audit_button">批量返现</button>
					<a class="btn btn-info ui-redirect" href="javacript:void(0);" data-title="上传投资记录(excel)" data-url="/rest/cim/cimproductunrecordinvest/importPage" role="button"><i class="fa fa-plus"></i>上传投资记录(excel)</a>
		      </div>
         </div>
    </script>
    
      <script type="text/javascript">
    
	    var tableIdFormat= function (data,type,full,meta) {
	        return '<input type="checkbox" name="tableId" value="' + data + '">';
	    }
	   
    </script>

 
