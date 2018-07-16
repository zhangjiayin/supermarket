<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/cms/activitylist/list" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
		            <th data-name="activityName">活动名称</th>
		            <!-- <th data-name="activityImg">活动图标</th> -->
		            <th data-name="startDate">开始时间</th>
		            <th data-name="endDate">结束时间</th>
		            <th data-name="status" data-format="status:cumsStatus">活动状态</th>
		            <th data-name="activityCode">活动编码</th>
		            <th data-name="appType" data-format="status:cumsType">活动类别</th>
		            <th data-name="id" data-format="id:cumsDetail">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cumsStatus = function (data,type,full,meta) {
        	if(data==0) {
        		return "启用";
        	} else if(data == 1) {
        		return "禁用";
        	} 
        }
        
        var cumsType = function (data,type,full,meta) {
        	if(data==0) {
        		return "公共";
        	} else if(data == 1) {
        		return "理财师";
        	} else if(data == 2) {
        		return "金服";
        	} 
        }
        
        var cumsId = function (data,type,full,meta) {
        	return meta.row+1;
        }
        
        var cumsDetail = function (data,type,full,meta) {
        	return '<a class="btn btn-sm btn-default btn-icon ui-redirect" href="javascript:;" data-title="资讯编辑" data-url="/rest/cms/activitylist/toEdit?id='+data+'" ><i class="fa fa-edit"></i>编辑</a>';
        }

       var $db= $("#J-newslist").linkweeTable();
    </script>
    <script type="text/linkwee-template" id="template-search">
        <form>
        <input name="actitityName"  class="easyui-textbox" style="width:200px"  placeholder="名称">
&nbsp;&nbsp;&nbsp;&nbsp;

        <a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
&nbsp;&nbsp;&nbsp;&nbsp;
 <a class="btn btn-default btn-sm ui-redirect" href="javascript:;" data-title="新增" data-url="/rest/cms/activitylist/toSave" role="button">新增</a>
        </form>
    </script>
</div>
    
    
    


