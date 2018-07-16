<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>

<div class="row">
	<div class="col-sm-12">理财师总数 : ${totalCount}</div>
	<br>
	<br>
	<div class="col-sm-12">累计销售额 : ${totalAmount}</div>
</div>
<br>
<br>
<div id="main-adv" class="container-fluid">
    <div class="table-responsive">
        <table id="J_lcsList_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>理财师姓名</th>
		            <th>手机号</th>
		            <th>所在城市</th>
		            <th>名下客户数量</th>
		            <th>绑定时间</th>
		            <th>累计销售额(元)</th>
		            <th>累计销售佣金(元)</th>
		            <th>本月销售额(元)</th>
		            <th>本月销售佣金(元)</th>
                </tr>
            </thead>
        </table>

    </div>
    <script type="text/javascript">
        var $db = $("#J_lcsList_list").DataTable(
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
                        url:"rest/statistics/lcs/lists",
                        type:"POST",
                        dataSrc:function (result) {
                            return result.data;
                        },
                        data:function (d) {
                            d.nameormobile = $('#nameOrMobile').val();
                            d.city = $('#city').val();
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
                        {data:"name"},
                        {data:"mobile"},
                        {data:"city"},
                        {data:"customers",render: function ( data, type, row ) {
                            var url ='rest/statistics/lcs/customerPage?name='+row.name+'&mobile='+row.mobile;
                            return '<a href="javascript:;" class="btn btn-link ui-redirect " data-title="客户列表" data-url="'+url+'">'+data+'</a>';
                        }},
                        {data:"time"},
                        {data:"totalAmount",render: function ( data, type, row ) {
                            var url ='rest/statistics/lcs/teamPage?mobile='+row.mobile;
                            return '<a href="javascript:;" class="btn btn-link ui-redirect " data-title="销售业绩" data-url="'+url+'">'+data+'</a>';
                        }},
                        {data:"totalFee"},
                        {data:"monthAmount"},
                        {data:"monthFee"}
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
		
            <div class="row">
				<div class="col-sm-2 form-group">
 		<label for="city" class="col-sm-3 control-label">城市:</label>
 		 <div class="col-sm-9">
	      	<select name="city" id="city" class="form-control">
				<option value="">全部</option>
				<option value="北京市">北京</option>
           		<option value="上海市">上海</option>
				<option value="广州市">广州</option>
				<option value="深圳市">深圳</option>
				<option value="天津市">天津</option>
				<option value="重庆市">重庆</option>
				<option value="杭州市">杭州</option>
				<option value="南京市">南京</option>
				<option value="成都市">成都</option>
				<option value="武汉市">武汉</option>
			</select>
	    </div>
	</div>
               <div class="col-sm-2 form-group">
		 <label for="nameOrMobile" class="col-sm-4 control-label">理财师:</label>
 		 <div class="col-sm-8">
	      	<input  class="form-control" name="nameOrMobile" id="nameOrMobile" value="${param.mobile}" type="text" placeholder="输入手机号或姓名"/>
	    </div>
	</div>
				  <div class="col-sm-1">
					<a class="btn btn-default J_search" href="javascript:;" role="button">查询</a>
				</div>
   			</div>
	
    </script>
</div>




