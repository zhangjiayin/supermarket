<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>

<div id="main-news" class="container-fluid">
    <div class="table-responsive">
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/cim/cimproduct/list" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="productName" >产品名称</th>
                    <th data-name="productTypeText">产品类型</th>
                    <th data-name="productRateText">收益率</th>
                    <th data-name="deadLineValueText">产品期限</th>
                    <th data-name="buyTotalMoney">产品总额度（元）</th>
                    <th data-name="saleStartTime">产品销售开始时间</th>
                    <th data-name="statusText">产品状态</th>
                    <th data-name="auditStatusText">审核状态</th>
                    <th data-name="auditPassTime">上架时间</th>
                    <th  data-format="productId:doFCT">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    
    <script type="text/javascript">
        var doFCT = function (data,type,full,meta) {
        	return '<button class="ui-redirect" data-title="查看详情" href="javascript:;" data-url="rest/cim/cimproduct/detail?productId='+data+'">查看详情</button>';
        }
       var $db= $("#J-newslist").linkweeTable();
    </script>
    
    <script type="text/linkwee-template" id="template-search">
        <form>
        	<input name="productName"  class="easyui-textbox" style="width:200px"  placeholder="产品名称">
			产品状态:
			<select id="status" name="status">
				<option value="99" selected="selected">全部</option>
				<option value="1">在售</option>
            	<option value="2">售罄</option>
            	<option value="3">募集失败</option>
        	</select>
			审核状态:
			<select id="auditStatus" name="auditStatus">
				<option value="99" selected="selected">全部</option>
				<option value="1">审核通过</option>
            	<option value="2">待审核</option>
            	<option value="3">审核未通过</option>
         	</select>
        	<a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
        </form>
    </script>
</div>
