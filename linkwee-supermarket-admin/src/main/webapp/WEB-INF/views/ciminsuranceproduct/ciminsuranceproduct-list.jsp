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

<script type="text/javascript" src="app/ciminsuranceproduct/ciminsuranceproduct-list.js"></script>
<!-- 管理员才有下列权限 -->
<table id="dtable" class="table table-bordered">
        <thead>
            <tr>
	            <th>序号</th>
	            <th>机构编码</th>
	            <th>方案代码</th>
	            <th>产品名称</th>
	            <th>产品特色</th>
	            <th>分类</th>
	            <th>产品状态</th>
	            <th>价格（分）</th>
	            <th>背景图片</th>
	            <th>排序</th>
	            <th>佣金率</th>
	            <th>犹豫期(天)</th>
	            <th>推荐</th>
            </tr>
        </thead>
 
    </table>

<!-- 图片服务器 -->
<input type="hidden" id="imgServerUrl" value="${img_server}"/>
