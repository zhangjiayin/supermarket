<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>

<%--<script type="text/javascript" src="assets/plugins/easyui/easyloader.js"  ></script>--%>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<%--<script type="text/javascript">--%>
<%--easyloader.base = 'assets/plugins/easyui/';--%>
<%--easyloader.theme = 'bootstrap';--%>
<%--easyloader.locale = 'zh_CN';--%>

<%--easyloader.modules.common = {--%>
	<%--js: 'common.js'--%>
<%--};--%>
<%--easyloader.modules.my97DatePicker = {--%>
		<%--js: '../../My97DatePicker/WdatePicker.js'--%>
<%--};--%>
<%--</script>--%>
<%--<script src="app/cfplanner/lcs/model/lcsListModel.js"></script>--%>
<%--<script src="app/cfplanner/lcs/lcs.js"></script>--%>




<div class="container-fluid">
	<div class="table-responsive">
	<table id="cfp-list-table" class="table table-bordered" style="width: 100%;">
		<thead>
			<tr>
				<th>序号</th>
				<th>姓名</th>
				<th>认证</th>
				<th>电话</th>
				<th>所属机构</th>
				<th>职级</th>
				<th>上级理财师</th>
				<th>上级账号</th>
				<th>团队人数</th>
				<th>客户人数</th>
				<th>注册时间</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
	</div>
	<script type="text/javascript">

		var $dataTable = $("#cfp-list-table").DataTable({
			ordering:false,
			searching:false,
			lengthChange:false,
			paging:true,
			select:true,
			serverSide:true,
			deferLoading:false,
			dom: '<"J_toolbar ux-toolber">frtip',
			ajax:{
				url:"rest/lcsList/getLcsList",
				type:"POST",
				dataSrc:function (result) {``
					return result.data;
				},
				data:function (d) {

					d.pageIndex = (d.start / d.length)+1;
					d.pageSize = d.length;
					d.nameOrmobile = $('input[name=nameOrmobile]').val();
//					d.startDate = $('input[name=startDate]').val();
//					d.endDate = $('input[name=endDate]').val();
				}
			},
			columns:[
				{data:"id"},
				{data:"name"},
				{data:"rz"},
				{data:"mobile"},
				{data:"department"},
				{data:"cfplevel"},
				{data:"parentName"},
				{data:"parentMobile"},
				{data:"team"},
				{data:"customer"},
				{data:"userRegTime"},
				{data:"mobile"}
			],
			columnDefs:[
				{
					targets:0,
					data:"id",
					render:function (data,type,full,meta) {
						var start = (meta.settings.oAjaxData.start / meta.settings.oAjaxData.length) +1;
						return (meta.row+1)+(start-1)*10;
					}
				},
				{
					targets:1,
					data:"name",
					render:function (data, type, full, meta) {
						return data == null || data.length<=0 ?"--":data;
					}
				},
				{
					targets:2,
					data:"name",
					render:function (data,type,full,meta) {
						return full.name?"已认证":'<a style="color: red;">未认证</a>';
					}
				},

				{
					targets:6,
					data:"parentName",
					render:function (data,type,full,meta) {
						return data?data:"--";
					}

				},
				{
					targets:7,
					data:"parentMobile",
					render:function (data,type,full,meta) {
						return data?data:"--";
					}

				},{
					targets:8,
					data:"team",
					render:function (data,type,full,meta) {
						return data && parseInt(data)>0?'<a class="ui-redirect" data-debug="0" data-title="团队详情" data-url="rest/cfprelevant/teamlist?mobile='+full.mobile+'">'+data+'</a>':0;
					}
				},
				{
					targets:9,
					data:"customer",
					render:function (data,type,full,meta) {
						return data && parseInt(data)>0?'<a class="ui-redirect" data-debug="0" data-title="客户详情" data-url="rest/cfprelevant/customelist/'+full.mobile+'">'+data+'</a>':0;
					}
				},

				{
					targets:11,
					data:"mobile",
					render:function (data,type,full,meta) {
						return data?'<a class="ui-redirect btn btn-primary btn-sm btn-icon" data-title="理财师详情" href="javascript:;" data-url="rest/lcsList/getLcsDetail?mobile='+data+'"><i class="fa fa-info"></i>详情</a>':"--";
					}

				}


			],
			"language": {
				select: {
					rows: {
						_: "已选择 %d 行",
						1: "已选择 1 行"
					}
				},
				"sProcessing":   "处理中...",
				"sLengthMenu":   "显示 _MENU_ 项结果",
				"sZeroRecords":  "没有匹配结果",
				"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
				"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix":  "",
				"sSearch":       "搜索:",
				"sUrl":          "",
				"sEmptyTable":     "表中数据为空",
				"sLoadingRecords": "载入中...",
				"sInfoThousands":  ",",
				"oPaginate": {
					"sFirst":    "首页",
					"sPrevious": "上页",
					"sNext":     "下页",
					"sLast":     "末页"
				},
				"oAria": {
					"sSortAscending":  ": 以升序排列此列",
					"sSortDescending": ": 以降序排列此列"
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
		<input name="nameOrmobile"  class="easyui-textbox" style="width:200px"  placeholder="姓名或者手机号码">
		<a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
		<shiro:hasAnyRoles name="super_admin">
			<a  href="javascript:;" class="btn btn-default btn-sm J_export" >导出EXCEL</a>
		</shiro:hasAnyRoles>
	</script>
</div>




