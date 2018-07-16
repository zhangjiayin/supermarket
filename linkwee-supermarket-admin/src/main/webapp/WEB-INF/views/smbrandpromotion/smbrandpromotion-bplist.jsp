<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div id="main-smbrandpromotion" class="container-fluid">
    <div class="table-responsive">
        <table id="smbrandpromotion_list" class="table table-bordered" data-xtoolbars="#template-search"  data-url="/rest/acc/smbrandpromotion/bplist_ajax" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" data-edit="id">序号</th>
                    <th data-name="appType">邀请/推荐类别</th>
                    <th data-name="showInx">海报排序</th>
                    <th data-name="image">图片</th>
                    <th data-name="createTime">添加时间</th>
                    <th data-name="creator">操作人</th>
                    <th data-name="id" data-format="id:smbrandpromotion_opts">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cums = function (data,type,full,meta) {
            return '否';
        }
        
        var smbrandpromotion_opts  = function (data,type,full,meta) {
              return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="编辑" data-url="/rest/acc/smbrandpromotion/bptoEdit?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-danger btn-icon J_smbrandpromotion_delete" data-id="'+data+'" href="javascript:;"><i class="fa fa-trash-o"></i>删除</a>';
        }

       var $db= $("#smbrandpromotion_list").linkweeTable();
        $("#main-smbrandpromotion").delegate("a.J_smbrandpromotion_delete","click",function () {
            var classId = $(this).attr("data-id");
            bootbox.confirm("确定要执行删除操作吗？",function (result) {
                if(result){
                    $.get("rest/acc/smbrandpromotion/del",{id:classId},function (result) {
                        if(result.isFlag){
                            $db.draw();
                        }
                    });
                }
            });
        });
    </script>
    <script type="text/linkwee-template" id="template-search">
        <form>
            <div class="row">
	                <label>邀请/推荐类别:</label>
					<select id="appType" class="easyui-combobox" name="appType" panelHeight="auto" style="width: 120px; height: 37px;">
                           <option value="">请选择</option>						
                           <option value="1">推荐理财师</option>
 						   <option value="2">邀请客户</option>
					</select>
 				<span>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="easyui-linkbutton J_search" role="button">查询</button>
							 <a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增海报" data-url="/rest/acc/smbrandpromotion/bptoEdit" role="button"><i class="fa fa-plus"></i>新增</a>
                </span>            
             </div>

        </form>
    </script>
</div>
