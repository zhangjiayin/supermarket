<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>

<div class="container-fluid">
	<div class="table-responsive">
	<table id="feeDetail-list-table" class="table table-bordered" style="width: 100%;">
		<thead>
			<tr>
				<th>单据编号 </th>
			    <th>客户名称 </th>
			    <th>客户手机号码  </th>
			    <th>客户投资产品</th>
			    <th>客户投资额 </th>
			    <th>业务时间</th>
			    <th>佣金金额 </th>
			    <th>机构名称</th>
			    <th>业务员名称 </th>
			    <th>业务员手机号码  </th>
			</tr>
		</thead>
	</table>
	</div>
	<script type="text/javascript">
		var $dataTable = $("#feeDetail-list-table").DataTable({
			ordering:false,
			searching:false,
			lengthChange:false,
			paging:true,
			select:true,
			serverSide:true,
			dom: '<"J_toolbar ux-toolber">frtip',
			ajax:{
				url:"rest/feedetail/list",
				type:"POST",
				dataSrc:function (result) {
					return result.data;
				},
				data:function (d) {
					d.pageIndex = (d.start / d.length)+1;
					d.pageSize = d.length;
					d.saleUser = $('input[name=saleUser]').val();
				}
			},
			columns:[
				{data:"billnumber"},
				{data:"customername"},
				{data:"customermobile"},
				{data:"productname"},
				{data:"puramount"},
				{data:"bizdateformat"},
				{data:"feeamount"},
				{data:"orgname"},
				{data:"saleusername"},
				{data:"saleusermobile"}
			],
			columnDefs:[],
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

		});



		$(".J_toolbar").html($("#template-search").html());


		$("a.J_search").click(function () {
			$dataTable.draw();
			console.log($dataTable);
		});
	</script>

	<script type="text/linkwee-template" id="template-search">
		<input name="saleUser"  class="easyui-textbox" style="width:200px"  placeholder="业务员手机号码">
		<a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
	</script>
</div>
