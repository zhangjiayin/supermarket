<%--
  Created by IntelliJ IDEA.
  User: lenli
  Date: 2016/5/27
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

<div class="ux-toolber">
     <div class="row">
         <div class="col-lg-12">
             <c:if test="${!empty cfp}"><p id="cfpinfo">理财师${cfp.userName}(${cfp.mobile})本月职级为：<b>${cfp.jobGrade}</b></p></c:if> 
         </div>
     </div>
</div>

<input id="userId" name="userId" type="hidden" value="${cfp.userId}" />

<div class="text-left">
	<text>您要将该理财师的本月职级调整为：</text>
	<label><input name="jobGrade" type="radio" value="TA" />见习</label> 
	<label><input name="jobGrade" type="radio" value="SM1" />顾问 </label> 
	<label><input name="jobGrade" type="radio" value="SM2" />经理 </label> 
	<label><input name="jobGrade" type="radio" value="SM3" />总监</label> 
</div>
<div style="text-align:center;margin:0 auto;"><a class="btn btn-primary J_updateJobGrade" href="">确定</a></div>
<br/>
<div>备注：调整职级后，职级立即生效，且本月不受需要低一职级的最低人数和个人销售年化的考核标准限制，可享受调整后职级的奖励和津贴。调整职级后的下月，将按照用户上月的实际情况（人数、个人年化）进行考核定级，并按照实际情况享受职级的奖励和津贴。</div>
<br/>
<div class="table-responsive">	
	<div>职级变更记录：</div>
	<table id="jobGrade-list-table" class="table table-bordered">
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
	
	$("a.J_updateJobGrade").click(function (e) {
		e.preventDefault();
		var jobGrade = $('input[name="jobGrade"]:checked').val();
		var jobGradeDescription = function(){
			if(jobGrade == "TA"){
				return "见习";
			}else if(jobGrade == "SM1"){
				return "顾问";
			}else if(jobGrade == "SM2"){
				return "经理";
			}else if(jobGrade == "SM3"){
				return "总监";
			} 
		}();
		var userId = $("#userId").val();	
		bootbox.confirm("您将理财师职级调整为：<b>"+jobGradeDescription+"</b>,点击确认后将立即生效！",function (result) {
			if(result){
				$.get("rest/lcsList/updateJobGrade",{userId:userId,jobGrade:jobGrade},function (result) {
					if(result){
						bootbox.alert("操作成功",function () {
							$reload_page();
							cfpJobGradedialog.modal('hide');
						});
					}
					else{
						bootbox.alert("操作失败");
					}
				},"json");
			}
		});
	});

    var $dataTable = $("#jobGrade-list-table").DataTable({
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
