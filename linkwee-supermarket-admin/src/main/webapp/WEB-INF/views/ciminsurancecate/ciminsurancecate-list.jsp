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

<script type="text/javascript" src="app/ciminsurancecate/ciminsurancecate-list.js"></script>
<!-- 管理员才有下列权限 -->
<table id="dtable" class="table table-bordered">
        <thead>
            <tr>
            <th>分类id</th>
            <th>分类名称</th>
            <th>排序</th>
            <th>分类logo投资者端 </th>
            <th>分类logo猎才大师</th>
            <th>是否可用</th>
            <th>最近修改时间</th>
            <th>分类描述</th>
            <th>分类图片跳转链接</th>
            <th>分类说明</th>
            </tr>
        </thead>
 
    </table>
<!-- 图片服务器 -->
<input type="hidden" id="imgServerUrl" value="${img_server}"/>

