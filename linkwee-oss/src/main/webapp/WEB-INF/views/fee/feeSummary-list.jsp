<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>

<div class="container-fluid">
	<div class="table-responsive">
	<table id="feeDetail-list-table" class="table table-bordered" style="width: 100%;">
		<thead>
			<tr>
				<th>业务员姓名 </th>
			    <th>业务员手机号码  </th>
			    <th>机构名称</th>
			    <th>佣金总额   </th>
			    <th>推荐用户数</th>
			    <th>客户投资金额  </th>
			    <th>年月</th>
			    <th>发放状态  </th>
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
				url:"rest/feesummary/list",
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
				{data:"saleusername"},
				{data:"saleusermobile"},
				{data:"orgname"},
				{data:"feeamount"},
				{data:"recommendpeople"},
				{data:"recommendinvest"},
				{data:"month"},
				{data:"billstatus"}
			],
			columnDefs:[
				{
					targets:7,
					data:"billstatus",
					render:function (data,type,full,meta) {
						if(data==0)
							return '未发放';
						if(data==1)
							return '已发放';
					}
				
				}            
			],
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
