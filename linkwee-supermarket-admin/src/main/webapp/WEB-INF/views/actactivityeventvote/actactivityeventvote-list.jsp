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

<script type="text/javascript" src="app/actactivityeventvote/actactivityeventvote-list.js"></script>

<table id="dtable" class="table table-bordered">
        <thead>
            <tr>
            <th>自增长主键:</th>
            <th>投票id:</th>
            <th>竞猜id:</th>
            <th>用户编码:</th>
            <th>手机号码:</th>
            <th>支持投票方:</th>
            <th>消耗的投票次数:</th>
            <th>是否虚拟数据 1：是 0：否:</th>
            <th>票数:</th>
            <th>投票时间:</th>
            <th>奖励:</th>
            <th>是否发放（0：未发放，1：已发放）:</th>
            <th>发放者:</th>
            <th>扩展字段1:</th>
            <th>扩展字段2:</th>
            <th>扩展字段3:</th>
            </tr>
        </thead>
 
    </table>


