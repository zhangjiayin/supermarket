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
    <div class="table-responsive">
        <table id="J-invest_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>姓名</th>
		            <th>手机号</th>
		            <th>最后投资日期</th>
		            <th>历史投资总额(万元)</th>
		            <th>在投金额(万元)</th>
		            <th>投资笔数</th>
		            <th>收益总额(元)</th>
		            <th>销售费用(元)</th>
                    <th> 操作</th>

                </tr>
            </thead>
        </table>

    </div>
    <script type="text/javascript">
        var $db = $("#J-invest_list").DataTable(
                {
                    ordering:false,
                    searching:false,
                    lengthChange:false,
                    paging:true,
                    select:true,
                    serverSide:true,
                    scrollX:true,
                    dom: '<"J_toolbar ux-toolber">frtip',
                    ajax:{
                        url:"rest/invest/queryCustomerInvestStatistics",
                        type:"POST",
                        dataSrc:function (result) {
                            return result.data;
                        },
                        data:function (d) {
                            d.nameOrMobile = $('#nameOrMobile').val();
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
                        {data:"name"},
                        {data:"mobile"},
                        {data:"rectInvestTime"},
                        {data:"totalAmt"},
                        {data:"investamt"},
                        {data:"investCount"},
                        {data:"profitamt"},
                        {data:"saleamt"},
                        {data:"userId",render: function ( data, type, row ) {
                            var url ='rest/invest/getCustomerInvestDetailPage?userId='+row.userId+'&name='+row.name+'&mobile='+row.mobile;
                            return '<a href="#" class="btn btn-link ui-redirect " data-title="投资详情" data-url="'+url+'">查看投资详情</a>';
                         }}
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
        $(".J_toolbar").html($("#template-search").html());

        $(".J_search").click(function () {
            $db.draw();
        });

    </script>
    <script type="text/linkwee-template" id="template-search">
		 <form>
            <div class="row">
                <div class="col-sm-2">
					<input  class="form-control" name="nameOrMobile" id="nameOrMobile" type="text" placeholder="输入手机号或姓名"/>
				</div>
				  <div class="col-sm-1">
					<a class="btn btn-default J_search" href="#" role="button">查询</a>
				</div>
   			</div>
		</form>
    </script>
</div>




