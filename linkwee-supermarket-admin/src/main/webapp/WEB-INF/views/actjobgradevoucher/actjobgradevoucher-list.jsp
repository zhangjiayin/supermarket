<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="com.linkwee.xoss.rbac.PermissionSign" %>
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

<script type="text/javascript" src="app/actjobgradevoucher/actjobgradevoucher-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
 
<table id="addVoucher-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>注册手机号码</th>
            <th>活动属性</th>
            <th>职级</th>
            <th>状态</th>
            <th>使用时间</th>
            <th>过期时间</th>
            <th>添加时间</th>
            <th>操作人</th>
            </tr>
        </thead>
    </table>

	<script type="text/linkwee-template" id="template-tools">
            <div class="row">
                <div class="col-sm-3">
                    <div class="input-group">
  <shiro:hasPermission name="<%=PermissionSign.JOB_GRADE_VOUCHER_ADD%>">
                       <a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增职级体验券" data-url="/rest/acc/actjobgradevoucher/addPage" role="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;新增</a>
  </shiro:hasPermission>	
				</div>
                </div>
            </div>
    </script>

