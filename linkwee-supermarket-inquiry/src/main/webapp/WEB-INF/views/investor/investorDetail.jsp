<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>

<div id="main-adv" class="container-fluid">
    <input type="hidden" id="userId" value="${param.userId}">
	 <div class="row">
        <div class="col-sm-2">
			<h4><small>姓名:  ${param.name} </small></h4>
		</div>
		  <div class="col-sm-3">
			<h4><small>手机号: ${param.mobile}</small></h4>
		</div>
	 </div>

    <div class="table-responsive">
        <table id="J-investDetail_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>投资产品</th>
		            <th>预期年化率</th>
		            <th>投资额（万元）</th>
		            <th>预计收益（元）</th>
		            <th>销售费用（元）</th>
		            <th>投资时间</th>
		            <th>起息时间</th>
		            <th>到期时间</th>
                    <th> 状态</th>

                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var $db = $("#J-investDetail_list").DataTable(
                {
                    ordering:false,
                    searching:false,
                    lengthChange:false,
                    paging:true,
                    select:true,
                    serverSide:true,
                    scrollX:true,
                    ajax:{
                        url:"rest/invest/queryCustomerInvestDetail",
                        type:"POST",
                        dataSrc:function (result) {
                            return result.data;
                        },
                        data:function (d) {
                            d.userId = $('#userId').val();
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
                        {data:"productName"},
                        {data:"rate"},
                        {data:"investAmt"},
                        {data:"profit"},
                        {data:"feeAmt"},
                        {data:"startTime"},
                        {data:"startTime"},
                        {data:"endTime"},
                        {data:"status"}
                    ],
                    columnDefs:[  ],
                    language:{
                        "emptyTable":"没有数据表",
                        info:"显示第 _START_  至 _END_  项结果，共 _TOTAL_ 项",
                        infoEmpty:"",
                        paginate:{
                            "first":"首页",
                            "next":"下一页",
                            "previous":"上一页"
                        }
                    }
                }
        );

    </script>

</div>




