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
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/cfplannerSale/getFeeDetailList?userId=${userId}" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="userName" >客户姓名</th>
                    <th data-name="mobile" >电话</th>
                    <th data-name="productName" >销售产品</th>
                    <th data-name="platfromName">产品所属机构</th>
                    <th data-name="deadLine">投资期限</th>
                    <th data-name="saleAmount">销售金额(元)</th>
                    <th data-name="feeRate">佣金率(%)</th>
                    <th data-name="createTime">销售日期</th>
                    <th data-name="feeAmount">产生佣金(元)</th>
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
        <input name="name"  class="easyui-textbox" style="width:200px"  placeholder="输入姓名或手机号...">
&nbsp;&nbsp;&nbsp;&nbsp;
		投资时间
		<input id="startTimeForSearch" name="startTimeForSearch" class="Wdate" type="text" value ="" onfocus="WdatePicker()" />
		至
		<input id="endTimeForSearch" name="endTimeForSearch" class="Wdate" type="text" value ="" onfocus="WdatePicker()" />
&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-default btn-sm J_search" role="button">查询</button>
        </form>
    </script>
</div>
    
    
    


