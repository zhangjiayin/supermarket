<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<script type="text/javascript" src="app/acaccountbind/actmessageconfig-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>


	<div class="page-header">
		<div class="row">
			<div class="col-md-3">
				 		<select class="form-control" id="messageId" >
				 			   <option value="0" <c:if test="${messageVal eq 0 }">selected</c:if>>梦网科技</option>
                               <option value="1" <c:if test="${messageVal eq 1 }">selected</c:if>>聚合数据</option>
					 	</select>
			</div>
			<div class="col-md-3">
			<div class="input-group">
			  <shiro:hasPermission name="<%=PermissionSign.CHANGE_MESSAGE_CHANNEL%>">
                       <a class="btn btn-default btn-icon J_cpa_seting" href="javascript:;" role="button"><i class="fa fa-plus"></i>&nbsp;&nbsp;切换短信通道</a>
              </shiro:hasPermission>	
                    </div>
			</div>
			</div>
	</div>
