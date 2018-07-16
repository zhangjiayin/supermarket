<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>

<div id="main-handbookclassify" class="container-fluid">
    <div class="table-responsive">
        <table id="J-handbookclassifylist" class="table table-bordered" data-xtoolbars="#template-search"  data-url="rest/sm/smgrowthhandbookclassify/list_ajax" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" data-edit="id">序号</th>
                    <th data-name="name">类别</th>
                    <th data-name="showIndex">类别排序</th>
                    <th data-name="description">类别描述</th>
                    <th data-name="id" data-format="id:handbookclassify_opts">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">
	    var handbookclassify_opts  = function (data,type,full,meta) {	    	
	    	return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="类别编辑" data-url="/rest/sm/smgrowthhandbookclassify/tosave?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-danger btn-icon J_handbookclassify_delete" data-id="'+data+'" href="javascript:;"><i class="fa fa-trash-o"></i>删除</a>';              	    	
	    }
	
	   var $db= $("#J-handbookclassifylist").linkweeTable();
	    $("#main-handbookclassify").delegate("a.J_handbookclassify_delete","click",function () {
	        var iid = $(this).attr("data-id");
	        bootbox.confirm("确定要执行删除操作吗？",function (result) {
	            if(result){
	                $.get("rest/sm/smgrowthhandbookclassify/del",{id:iid},function (result) {
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
				<div class="col-sm-12">   
					<span class="input-group-btn" style="float:right">  
						<a class="btn btn-default btn-icon ui-redirect" href="javascript:;" style="float:right" data-title="新增" data-url="/rest/sm/smgrowthhandbookclassify/tosave" role="button"><i class="fa fa-plus"></i>新建类别</a>                                	      
					</span>            
                </div>
            </div>
        </form>
    </script>
</div>
