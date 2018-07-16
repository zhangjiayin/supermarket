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

<script type="text/javascript" src="app/cimFeedetail/cimfeedetail-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
            <th>:</th>
            <th>佣金计算编号:</th>
            <th>投资订单编号:</th>
            <th>投资用户编号:</th>
            <th>获利理财师编号:</th>
            <th>原始理财师编号:</th>
            <th>产品所属机构编号:</th>
            <th>产品编号:</th>
            <th>购买产品金额:</th>
            <th>年化金额:</th>
            <th>描述:</th>
            <th>佣金费率:</th>
            <th>佣金:</th>
            <th>佣金类型：1001=佣金|1002=推荐津贴|1003=新人津贴|1004=理财师职位津贴|1005=合伙人职位津贴|1006=季度奖励|1007=年度分红|1008=活动奖励|1009=销售奖励:</th>
            <th>结算状态：0=未结算|1=结算中|2=结算成功|3=结算失败:</th>
            <th>结算单号:</th>
            <th>创建时间:</th>
            <th>更新时间:</th>
            </tr>
        </thead>
 
    </table>


