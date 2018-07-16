<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div id="main-forgeinvestamount" class="container-fluid">
    <div class="table-responsive">
        <table id="J-forgeinvestamountList" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/act/actforgeinvestamount/list_ajax" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="userName">姓名</th>
                    <th data-name="mobile">电话</th>
		            <th data-name="headImage" data-format="headImage:imgFormat">头像</th>
		            <th data-name="investAmt">总投资额(元)</th>
		            <th data-name="updateTime" >最后一次编辑时间</th>
		            <th data-name="id" data-format="id:cumsDetail">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">
	    var imgFormat = function (data,type,full,meta) {
		   	if(data!=null && data !='') {
		   		return '<img src="'+data+'" data-action="zoom"  height="100" />';
		   	} else {
		   		return "--";
		   	} 
	    }
           
        var cumsId = function (data,type,full,meta) {
        	return meta.row+1;
        }
        
        var cumsDetail = function (data,type,full,meta) {
        	return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="资讯编辑" data-url="/rest/act/actforgeinvestamount/tosave?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-danger btn-icon J_forgeinvestamount_delete" data-id="'+data+'" href="javascript:;"><i class="fa fa-trash-o"></i>删除</a>'; 
        }
        
        $("#main-forgeinvestamount").delegate("a.J_forgeinvestamount_delete","click",function () {
            var iid = $(this).attr("data-id");
            bootbox.confirm("确定要执行删除操作吗？",function (result) {
                if(result){
                    $.get("rest/act/actforgeinvestamount/del",{id:iid},function (result) {
                        if(result.isFlag){
                            $db.draw();
                        }
                    });
                }
            });
        });

        var $db= $("#J-forgeinvestamountList").linkweeTable();
    </script>
    <script type="text/linkwee-template" id="template-search">
		<form>
            <div class="row">
                <div class="col-sm-3">
                    <div class="input-group">
                        <span class="input-group-btn">                           
                            <a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="添加数据" data-url="/rest/act/actforgeinvestamount/tosave" role="button"><i class="fa fa-plus"></i>添加数据</a>
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </script>
</div>