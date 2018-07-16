<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="com.linkwee.xoss.rbac.PermissionSign" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" src="app/actaddfeecoupon/actaddfeecoupon-list.js"></script>


<table id="addFeeCoupon-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>加拥券来源</th>
            <th>加拥券类型</th>
            <th>使用时间</th>
            <th>过期时间</th>
            <th>添加时间</th>
            <th>操作人</th>
            <th>操作</th>
            </tr>
        </thead>
    </table>

	<script type="text/linkwee-template" id="template-tools">
		<shiro:hasPermission name="<%=PermissionSign.ADDFEECOUPON_EDIT%>">
            <div class="row">
                <div class="col-sm-3">
                    <div class="input-group">
                       <a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增加拥券" data-url="/rest/act/actaddfeecoupon/addPage" role="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;新增</a>
                    </div>
                </div>
            </div>
		</shiro:hasPermission>
    </script>

