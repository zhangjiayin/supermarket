<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>

<style type="text/css">
	table.dataTable thead th, table.dataTable thead td {
	    padding: 10px 18px;
	    border-bottom: 0px solid #111 !important;
	}
	
	table.dataTable.no-footer {
	    border-bottom: 1px solid #d6cfcf !important;
	}
	
	.dataTables_wrapper .dataTable thead tr th{ background-color: #f5f5f6; font-weight: 700; position: relative; }
	.dataTables_wrapper .dataTable tr.selected,.dataTables_wrapper .dataTable tr.odd{ background-color:#f9f9f9; }
	.dataTables_wrapper .dataTables_info{ float: left;}
	.dataTables_wrapper .dataTables_paginate{ border: 1px solid #ebebeb; padding: 0 !important; margin: 0px !important; float: right; border-radius: 3px !important;}
	.dataTables_wrapper .dataTables_paginate .paginate_button {
	    cursor: pointer;
	    padding:6px 10px;
	    background-color: #fff;
	    border: 0;
	    margin: 0 !important;
	    color: #949494 !important;
	    display: inline-block;
	    outline: 0;
	    text-decoration: none;
	    -webkit-border-radius: 0 !important;
	    -webkit-background-clip: padding-box !important;
	    -moz-border-radius: 0 !important;
	    -moz-background-clip: padding !important;
	    border-radius: 0 !important;
	    background-clip: padding-box !important;
	    border-right: 1px solid #ebebeb;
	}
	.dataTables_wrapper .dataTables_paginate span.ellipsis{
	    padding: 0 10px; display: inline-block;
	}
	.dataTables_wrapper .dataTables_paginate span.ellipsis + .paginate_button {
	    border-left: 1px solid #ebebeb !important;
	
	}
	.dataTables_wrapper .dataTables_paginate .next{ border: 0 !important;}
	.dataTables_wrapper .dataTables_paginate .current{ background: #fafafa !important;}
	.dataTables_wrapper .dataTables_paginate span .paginate_button.current {
	    background: #262b34 !important;
	    color: #fff !important;
	}
	 
</style>

<div id="main-levellist" class="container-fluid">

    <div class="table-responsive">
    <div class="ux-toolber">
        <div class="row">
            <div class="col-lg-4">
                <c:if test="${!empty cfp}"><p id="cfpinfo">理财师：${cfp.userName} ${cfp.mobile}</c:if> </p>
            </div>
        </div>
    </div>

    <table id="level-list-table" class="table table-bordered">
        <thead>
        <tr>
            <th>序号</th>
            <th>职级（变更前）</th>
            <th>职级（变更后）</th>
            <th>变更时间</th>
        </tr>
        </thead>
    </table>
    </div>
    <script type="text/javascript">
        var $dataTable = $("#level-list-table").DataTable({
            ordering:false,
            searching:false,
            lengthChange:false,
            paging:true,
            select:true,
            serverSide:true,
            ajax:{
                url:'rest/lcsList/levellist_json',
                type:"POST",
                dataSrc:function (result) {
                    return result.data;
                },
                data:function (d) {
                    var mobile = '${cfp.mobile}';
                    d.searchtext ="";
                    d.mobile = mobile;
                    d.columns = [];
                    d.search ={};
                }
            },
            columns:[
                {data:"id"},
                {data:"preLevel"},
                {data:"curLevel"},
                {data:"createTime"}
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
                    data:"preLevel",
                    render:function (data,type,full,meta) {
                    	if(data=="TA"){
                			return "见习";
                		}else if(data == "SM1"){
                			return "顾问";
                		}else if(data == "SM2"){
                			return "经理";
                		}else if(data == "SM3"){
                			return "总监";
                		}
                    }
                },
                {
                    targets:2,
                    data:"curLevel",
                    render:function (data,type,full,meta) {
                    	if(data=="TA"){
                			return "见习";
                		}else if(data == "SM1"){
                			return "顾问";
                		}else if(data == "SM2"){
                			return "经理";
                		}else if(data == "SM3"){
                			return "总监";
                		}
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
    </script>
</div>
