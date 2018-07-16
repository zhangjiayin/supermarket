<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<style type="text/css">
	.dashboard-stat .detailsDef .number {    
	  /* padding-top: 15px; */
	  text-align: center;
	  font-size: 34px;
	  /* line-height: 34px;
	  letter-spacing: -1px;
	  margin-bottom: 5px; */
	  font-weight: 300;
	  color: #fff;
	}
	.more{
		text-align: center;
	}
	.page-title {
		font-size: 20px !important;
	}
	.xxx{
		float:right;
		font-size: 16px !important;
	} 
	.nav-tabs {
	    border-bottom: 0px !important;
	}
</style>

<div class="container-fluid">
    	
	<div class="table-responsive">
        <table id="J_unrepaymnet_invest_list" class="table table-bordered">
            <thead>
                <tr>
                    <th>姓名</th>
		            <th>手机号</th>
		            <th>出单平台</th>
		            <th>出单金额</th>
		            <th>产品期限</th>
		            <!-- <th>我的佣金</th> -->
		            <th>出单时间</th>
		            <th>上次复投时间</th>
		            <th>团队关系</th>
		            <!-- <th>猎财首/复投</th> -->
		            <th>操作</th>
                </tr>
            </thead>
        </table>
    </div>  
    
</div>

<script type="text/javascript" src="app/common/util.js"></script>
<script type="text/javascript" > 
	var $db = $("#J_unrepaymnet_invest_list").DataTable(
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
                    url:"rest/statistics/lcs/flowDeadLine/unRepaymentList",
                    type:"POST",
                    dataSrc:function (result) {
                        return result.data;
                    },
                    data:function (d) {
                    	d.nameOrMobile = $('#nameOrMobile').val();
                        d.columns = [];
                        d.search = {};
                        $.post('rest/statistics/lcs/flowDeadLine/unRepaymentList/total',d, function(data) {
                        	$("#investAmount").html(data.investAmount+"元");
                        });
                    }
                },
                columns:[
                    {data:"userName"},
                    {data:"mobile",render: function ( data, type, row ) {
                    	return data.replace(/^(\d{3})\d{4}(\d+)/,"$1****$2");
                	}},
                    {data:"orgName"},
                    {data:"saleAmount"},
                    {data:"productDeadLineMinValue"},
                    /* {data:"myFeeAmount"}, */
                    {data:"saleTime"},
                    {data:"lastReinvestTime"},
                    {data:"teamLevel",render: function ( data, type, row ) {
                    	if(data == 1){
                    		return "直推";
                    	}else{
                    		return data + "级";
                    	}
                	}},
                    /* {data:"isFirstInvest",render: function ( data, type, row ) {
                    	if(data == 1){
                    		return "首投";
                    	}else{
                    		return "复投";
                    	}
                	}}, */
                	{data:"mobile",render: function ( data, type, row ) {
                		return '<a class="btn btn-sm btn-primary btn-icon ui-redirect" href="javascript:;" data-title="了解详情" data-url="/rest/statistics/lcs/detail?mobileOrName='+data+'" ><i class="fa fa-edit"></i>了解详情</a>';                
                	}}
                ],
                columnDefs:[  ],
                language:{
                    "emptyTable":"没有数据",
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
	
	//$db.draw();
	
	$(".J_toolbar").html($("#template-search").html());
	
	$(".J_search").click(function () {
        $db.draw();
    });
	
 </script>

<script type="text/linkwee-template" id="template-search">		
<div class="row">
	<div class="col-md-2 form-group">
		<label for="nameOrMobile" class="col-md-4 control-label">理财师:</label>
 		 <div class="col-md-8">
	      	<input  class="form-control" name="nameOrMobile" id="nameOrMobile" type="text" placeholder="输入手机号或姓名"/>
	    </div>
	</div>
	<div class="col-md-1">
		<a class="btn btn-default J_search" href="javascript:;" role="button">查询</a>
	</div>

	<div class="col-md-2" >
		累计复投金额： &nbsp;&nbsp;<strong id="investAmount">0.00元</strong>&nbsp;&nbsp;
	</div>
	<br/><br/>
	<div class="row page-title"><div class="xxx">以下金额单位：元 ，产品期限单位：天，人数单位：人</div></div>
</div>
</script>