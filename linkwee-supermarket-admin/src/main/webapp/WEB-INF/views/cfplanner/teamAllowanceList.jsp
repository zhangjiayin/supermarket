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
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/lcsList/getTeamAllowanceList?userId=${userId}" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号aa</th>
                    <th data-name="userName" >姓名</th>
                    <th data-name="mobile" >电话</th>
                    <th data-name="relLevel" >关系</th>
                    <th data-name="jobGrade" >当前职级</th>
                    <th data-name="salesYearAmount" >销售年化</th>
                    <th data-name="directChildren">直接下级人数</th>
                    <th data-name="allowance">推荐奖励</th>
                    <th data-name="directAllowance">直接管理津贴(元)</th>
                    <th data-name="teamAllowance">团队管理津贴(%)</th>
                    <th data-name="createTime">绑定时间</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

        var cumsId = function (data,type,full,meta) {
        	return meta.row+1;
        }

       var $db= $("#J-newslist").linkweeTable();
    </script>
    <script type="text/linkwee-template" id="template-search">
        <form>
       关系：<select  name="status" id="status">
				 	<option value="">请选择</option>
					<option value="0">直接推荐理财师</option>
					<option value="1">间接推荐理财师</option>
					<option value="2">三级推荐理财师</option>
				 </select>
        <button class="btn btn-default btn-sm J_search" role="button">查询</button>
        </form>
    </script>
</div>
    
    
    


