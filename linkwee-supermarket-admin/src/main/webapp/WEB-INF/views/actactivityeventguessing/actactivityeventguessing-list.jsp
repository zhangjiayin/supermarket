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

<script type="text/javascript" src="app/actactivityeventguessing/actactivityeventguessing-list.js"></script>

<table id="event_guessing_list" class="table table-bordered">
        <thead>
            <tr>
            <th>ID</th>
            <th>竞猜名称</th>
            <th>奖励发放状态</th>
            <th>发放奖励需要的得分</th>
            <th>奖池</th>
            <th>比赛方A</th>
            <th>比赛方B</th>
            <th>A方得分</th>
            <th>B方得分</th>
            <th>A方支持票数</th>
            <th>B方支持票数</th>
            <th>A方支持目标票数</th>
            <th>B方支持目标票数</th>
            <th>A方支持票数增长率</th>
            <th>B方支持票数增长率</th>
            <th>下一场开赛时间</th>
            <th>投票开始时间</th>
            <th>投票结束时间</th>
            <th>操作</th>
            </tr>
        </thead>
 
    </table>


