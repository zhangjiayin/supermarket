<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="com.linkwee.xoss.rbac.PermissionSign" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />

<!-- Editor -->
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Buttons/js/dataTables.buttons.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Buttons/css/buttons.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Select/css/select.dataTables.min.css"  />

<script type="text/javascript" src="app/cimorginfounrecord/cimorginfo-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<style type="text/css">
.table th, .table td{
	vertical-align: middle !important; /*表格内容优先垂直居中*/
}
</style>
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
	<thead>
		<tr>
		<th>机构编码</th>
		<th>机构名称</th>
		<th>安全评级</th>
		<th>合作状态</th>
		<th>灰度模式</th>
		<th>机构排序</th>
		<th>创建时间</th>
		<th>操作</th>
		</tr>
	</thead>
</table>

 <script type="text/template" id="template-search">
        <form>
            <div class="row">
                
                <div class="col-sm-2">
                    <div class="input-group">
                        <input name="orgname" class="form-control" placeholder="请输入机构名称" autocomplete="off">
                        <span class="input-group-btn">
                             <button class="btn btn-default J_search" role="button"><i class="fa fa-search"></i> 查询</button>
                        </span>
                    </div>
                </div>
				<shiro:hasPermission name="<%=PermissionSign.ORG_A_DETAIL_EDIT%>">
					<div class="col-sm-1" style="margin-left: -25px;">
						<span class="input-group-btn">
							<a class="btn btn-default btn-icon ui-redirect" href="javacript:void(0);" data-title="新增机构" data-url="/rest/cim/cimorginfoa/toEdit" role="button"><i class="fa fa-plus"></i> 新增</a>
					 	</span>
					</div>
				</shiro:hasPermission>

            </div>

        </form>
    </script>

 