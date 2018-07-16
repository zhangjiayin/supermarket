<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>

<div id="main-handbook" class="container-fluid">
    <div class="table-responsive">
        <table id="J-handbooklist" class="table table-bordered" data-xtoolbars="#template-search"  data-url="rest/sm/smgrowthhandbook/list_ajax" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" data-edit="id">ID</th>
                    <th data-name="typeName" >类别</th>
                    <th data-name="cfpLevel" data-format="cfpLevel:cfpLevel_format">文章适用职级</th>
                    <th data-name="title">标题</th>
                    <th data-name="readingAmount">阅读量</th>
                    <th data-name="crtTime">发布时间</th>
                    <th data-name="source">来源</th>
                    <th data-name="creator">操作人</th>
                    <th data-name="status" data-format="status:status_format">状态</th>
                    <th data-name="id" data-format="id:handbook_opts">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">
	    var cfpLevel_format = function (data,type,full,meta) {
	        if(data != null){
	        	return data.replace(/TA/, "见习").replace(/SM1/, "顾问").replace(/SM2/, "经理").replace(/SM3/, "总监");
	        }	 
	        return data;
	    }
    
	    var status_format = function (data,type,full,meta) {
	        if(data == 0){
	        	return '无效';
	        }else{
	        	return '有效';
	        }	        
	    }
    
        var handbook_opts  = function (data,type,full,meta) {
        	if(full.status == 0){
        		return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="编辑" data-url="/rest/sm/smgrowthhandbook/tosave?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-success btn-icon J_handbook_status_on" data-id="'+data+'" href="javascript:;"><i class="fa fa-hand-pointer-o"></i>上架</a>';              
        	}else{
        		return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="编辑" data-url="/rest/sm/smgrowthhandbook/tosave?id='+data+'" ><i class="fa fa-edit"></i>编辑</a> &nbsp;&nbsp;<a class="btn btn-sm btn-success btn-icon J_handbook_status_close" data-id="'+data+'" href="javascript:;"><i class="fa fa-hand-rock-o"></i>下架</a>';                
        	}
        }
        
        var $db= $("#J-handbooklist").linkweeTable();
        
        $("#main-handbook").delegate("a.J_handbook_status_on","click",function () {
            var iid = $(this).attr("data-id");          
            $.get("rest/sm/smgrowthhandbook/updateStatus",{id:iid,status:1},function (result) {
                if(result.isFlag){
                	bootbox.alert("操作成功",function () {
                		$db.draw();
					});
                }
            });
        });
        
        $("#main-handbook").delegate("a.J_handbook_status_close","click",function () {
            var iid = $(this).attr("data-id");          
            $.get("rest/sm/smgrowthhandbook/updateStatus",{id:iid,status:0},function (result) {
                if(result.isFlag){
                	bootbox.alert("操作成功",function () {
                		$db.draw();
					});
                }
            });
        });
    </script>
    <script type="text/linkwee-template" id="template-search">
        <form>
            <div class="row">
                <div class="col-sm-3">
					类别：
                    <select id="handbook_type_code" name="typeCode" class="form-control" style="width: 150px;display: inline-block;" >
                        <option value=" ">全部</option>
                        <c:forEach var="item" items="${growthHandbookClassifyList }" varStatus="dn">
                            <option value="${item.id }">${item.name }</option>
                        </c:forEach>
                    </select> 
					适用职级：
					<select id="handbook_app_type" name="cfpLevel" class="form-control" style="width: 94px; display: inline-block;" >
						<option value=" ">全部</option>                        
						<option value="TA">见习</option>
                        <option value="SM1">顾问</option>
						<option value="SM2">经理</option>
                        <option value="SM3">总监</option>
                    </select>               
                </div>

                <div class="col-sm-3">
                    <div class="input-group">
                        <input name="title" class="form-control" placeholder="请输入标题关键字" autocomplete="off">
                        <span class="input-group-btn">
                             <button class="btn btn-default J_search" role="button">查询</button>                          
                        </span>
                    </div>
                </div>

				<div class="col-sm-3">   
					<span class="input-group-btn" style="float:right"> 
						<a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="分类" data-url="/rest/sm/smgrowthhandbookclassify/list" role="button">类别管理</a>   
						<a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="新增" data-url="/rest/sm/smgrowthhandbook/tosave" role="button"><i class="fa fa-plus"></i>添加成长手册</a>                                	      
					</span>            
                </div>
            </div>
        </form>
    </script>
</div>
