<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div id="main-classroom" class="container-fluid">
    <div class="table-responsive">
        <table id="classroom_list" class="table table-bordered" data-xtoolbars="#template-search"  data-url="/rest/acc/smclassroom/list_ajax" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" data-edit="id">序号</th>
                    <th data-name="label">类别</th>
                    <th data-name="title">标题</th>
                    <th data-name="showInx">显示排序</th>
                    <th data-name="createTime">添加时间</th>
                    <th data-name="creator">操作人</th>
                    <th data-name="id" data-format="id:classroom_opts">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cums = function (data,type,full,meta) {
            return '否';
        }
        
        var classroom_opts  = function (data,type,full,meta) {
              return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="编辑" data-url="/rest/acc/smclassroom/toEdit?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-danger btn-icon J_news_delete" data-id="'+data+'" href="javascript:;"><i class="fa fa-trash-o"></i>删除</a>';
        }

       var $db= $("#classroom_list").linkweeTable();
        $("#main-classroom").delegate("a.J_news_delete","click",function () {
            var classId = $(this).attr("data-id");
            bootbox.confirm("确定要执行删除操作吗？",function (result) {
                if(result){
                    $.get("rest/acc/smclassroom/del",{id:classId},function (result) {
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
                <div class="col-sm-2">
					<select  name="label" class="form-control">
						<option value="今日财经早知道">今日财经早知道</option>
						<option value="新版猎财攻略">新版猎财攻略</option>
					</select>
				</div>
				<div class="col-sm-3">
                	<div class="input-group">                 
						<input name="title"  id="title" class="form-control" style="width:250px"  placeholder="请输入标题关键字">
                  	</div>  
                </div>
 				<span class="input-group-btn">
                             <button class="btn btn-default J_search" role="button">查询</button>
							 <a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增精选推荐" data-url="/rest/acc/smclassroom/toEdit" role="button"><i class="fa fa-plus"></i>新增</a>
                </span>            
             </div>

        </form>
    </script>
</div>
