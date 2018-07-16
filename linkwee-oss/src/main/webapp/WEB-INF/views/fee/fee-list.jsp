<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<%--<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />--%>

<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.js"  ></script>
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<script type="text/javascript" src="app/fee/fee-list.js"></script>

<div class="container-fluid">
	<div class="table-responsive">
		<table class="table table-bordered"  id="feePay-list-table" data-url="rest/feepay/list" data-xtoolbars="#feepay-search" data-order="false"  data-cols="true">
	            <thead>
	            <tr>
	                <th data-name="name">姓名 </th>
				    <th data-name="mobile">手机号码</th>
				    <th data-name="empno">工号 </th>
				    <th data-name="department">部门</th>
				    <th data-name="feeamount">佣金</th>
				    <th data-name="billnumber">单据编号</th>
				    <th data-name="month">年月</th>
				    <th data-name="status" data-format="sourceType:status_format">状态</th>
				    <th data-name="resultmsg">描述 </th>
	            </tr>
	            </thead>
	     </table>
	</div>
	<script type="text/javascript">

	var feePayTable = $("#feePay-list-table").DataTable({
		ordering:false,
		searching:false,
		lengthChange:false,
		paging:true,
		select:false,
		serverSide:true,
		dom: '<"J_toolbar ux-toolber">frtip',
		ajax:{
			url:"rest/feepay/list",
			type:"POST",
			dataSrc:function (result) {
				return result.data;
			},
			data:function (d) {
				d.pageIndex = (d.start / d.length)+1;
				d.pageSize = d.length;
				d.month = $('input[name=month]').val();
                     d.saleUser = $('input[name=saleUser]').val();
                     d.querytype = $('select[name=querytype]').val();
			}
		},
		columns:[
			{data:"name"},
			{data:"mobile"},
			{data:"empno"},
			{data:"department"},
			{data:"feeamount"},
			{data:"billnumber"},
			{data:"month"},
                {data:"status","render": function ( data, type, row ) {
                 if ('0' == data) {
                      return '未发放';
                    } else if ('1' == data) {
                      return '发放执行中';
                    } else if ('2' == data) {
                      return '发放成功';
                    } else if ('3' == data) {
                      return '发放失败';
                   } else {
                      return value;
                    }
               }},
             {data:"resultmsg"}
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
          var re = new RegExp("^[1-9]{1}[0-9]{3}((0[1-9]{1})|(1[0-2]{1}))$");
          var searchMonth=$("#searchMonth").val();
          if(!searchMonth){
            showTips('请输入年月');
            return false;
          }
          if(!re.test(searchMonth)){
            showTips('请输入正确的6位年月');
            return false;
          }
          feePayTable.draw();
	});

	</script>
</div>

<script type="text/handlebars-template" id="template-search">
<form id="searchForm" action="javascript:void(0);">
	<div class="row" >
		<div class="col-md-6 col-sm-6 col-xs-6">
			<div class="form-group">
				<!-- Button trigger modal -->
				<button class="btn btn-primary" data-toggle="modal" type="button"
				   data-target="#importDataModal"> <i class='fa fa-edit'></i>数据导入
				</button>
				<button class="btn btn-primary" data-toggle="modal" type="button"
				   data-target="#feePayModal"> <i class='fa fa-edit'></i>佣金发放
				</button>
				<button class="btn btn-primary" data-toggle="modal" type="button"
				   data-target="#statRetModal"> <i class='fa fa-edit'></i>状态重置
				</button>
			</div>
		</div>
	</div>

    <div class="row" >
      	&nbsp;&nbsp;&nbsp;&nbsp;年月(YYYYMM)：<input type="text" id="searchMonth" name="month"  placeholder="请输入年月">
      	手机号码：<input type="text"  id="saleUser" name="saleUser">
      	查询类型：<select  id="querytype" name="querytype">
        	 	<option value="-1">全部</option>
        	 	<option value="2">发放成功</option>
         		<option value="3">发放失败</option>
         		<option value="0">未发放</option>
         		<option value="1">发放中</option>
      		   </select>
	  	<a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
    </div>
</form>
</script>


<!--------------------------数据导入的弹出层开始---------------------------->
<div class="modal fade" id="importDataModal" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                <i class="icon-pencil"></i><span id="lblAddTitle" style="font-weight:bold">数据导入</span>
            </h4>
         </div>
            <form class="form-horizontal form-row-strippe" id="importData" >
                <div class="modal-body">
                    <div class="row">
                    	 <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-3">Excel文件:</label>
                                <div class="col-md-7">
                                	<input type="file" name="file" id="file">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- HIDDEN FILEDS -->
	             <input type="hidden" id="id" name="id" />
                <!-- HIDDEN FILEDS END -->
	         <div class="modal-footer">
	                    <button type="submit" class="btn blue" id="importDataSubmit">提交</button>
	                    <button id="reset" type="reset" class="btn green" data-dismiss="modal">取消</button>
	         </div>
         </form>
      </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--------------------------数据导入的弹出层结束---------------------------->

<!--------------------------佣金发放的弹出层开始---------------------------->
<div class="modal fade" id="feePayModal" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                <i class="icon-pencil"></i><span id="lblAddTitle" style="font-weight:bold">佣金发放</span>
            </h4>
         </div>
            <form class="form-horizontal form-row-strippe monthClass" id="feePay" >
                <div class="modal-body">
                    <div class="row">
                    	 <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-4">年月(YYYYMM):</label>
                                <div class="col-md-6">
                                    <input id="month" name="month" type="text" for="username" class="form-control"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- HIDDEN FILEDS -->
	             <input type="hidden" id="id" name="id" />
                <!-- HIDDEN FILEDS END -->
	         <div class="modal-footer">
	                    <button type="submit" class="btn blue" id="feePaySubmit">提交</button>
	                    <button id="reset" type="reset" class="btn green" data-dismiss="modal">取消</button>
	         </div>
         </form>
      </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--------------------------佣金发放的弹出层结束---------------------------->


<!--------------------------状态重置的弹出层开始---------------------------->
<div class="modal fade" id="statRetModal" tabindex="-1" role="dialog"
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                <i class="icon-pencil"></i><span id="lblAddTitle" style="font-weight:bold">状态重置</span>
            </h4>
         </div>
            <form class="form-horizontal form-row-strippe monthClass" id="statRet" >
                <div class="modal-body">
                    <div class="row">
                    	 <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label col-md-4">年月(YYYYMM):</label>
                                <div class="col-md-6">
                                    <input id="month" name="month" type="text" for="username" class="form-control"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- HIDDEN FILEDS -->
	             <input type="hidden" id="id" name="id" />
                <!-- HIDDEN FILEDS END -->
	         <div class="modal-footer">
	                    <button type="submit" class="btn blue" id="statRetSubmit">提交</button>
	                    <button id="reset" type="reset" class="btn green" data-dismiss="modal">取消</button>
	         </div>
         </form>
      </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--------------------------状态重置的弹出层结束---------------------------->
