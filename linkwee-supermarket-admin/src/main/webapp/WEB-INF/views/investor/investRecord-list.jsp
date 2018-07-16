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
        <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/investorInvest/getInvestRecord?userId=${userId}" data-order="false" data-paging="true" data-size="10">
            <thead>
                <tr>
                    <th data-name="id" date-order="true" data-edit="id" data-format="id:cumsId">序号</th>
                    <th data-name="productName" >产品名称</th>
                    <th data-name="platfromName" >产品所属机构</th>
                    <th data-name="saleProfix" >销售收入</th>
                    <th data-name="investAmt">投资额(元)</th>
                    <th data-name="rate">预期年化率(%)</th>
                    <th data-name="profit">预计收益(元)</th>
                    <th data-name="bizTime">业务日期</th>
                    <th data-name="startTime">投资时间</th>
                    <th data-name="endTime">到期日期</th>
                    <th data-name="isPlatfromFirstInvest" data-format="isPlatfromFirstInvest:plat">是否平台首投</th>
                    <th data-name="status" data-format="status:cums">状态</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">

	    var plat = function (data,type,full,meta) {
	    	if(data==1) {
	    		return "是";
	    	} else if(data == 0) {
	    		return "否";
	    	}
	    }
    
        var cums = function (data,type,full,meta) {
        	if(data==1) {
        		return "投资中";
        	} else if(data == 3) {
        		return "已赎回";
        	} else if(data == 2) {
        		return "回款中";
        	}
        }
        
        var cumsId = function (data,type,full,meta) {
        	return meta.row+1;
        }

       var $db= $("#J-newslist").linkweeTable();
    </script>
    <script type="text/linkwee-template" id="template-search">
		<div class="input-group">
<a>以下为  <c:if test="${!empty investor}">${investor.userName} (${investor.mobile})</c:if>  的投资明细</a>
<p></p>
</div>

        <form>
        <input name="name"  class="easyui-textbox" style="width:200px"  placeholder="产品名称">
&nbsp;&nbsp;&nbsp;&nbsp;
		投资时间
		<input id="startTimeForSearch" name="startTimeForSearch" class="Wdate" type="text" value ="" onfocus="WdatePicker()" />
		至
		<input id="endTimeForSearch" name="endTimeForSearch" class="Wdate" type="text" value ="" onfocus="WdatePicker()" />
&nbsp;&nbsp;&nbsp;&nbsp;
		<select id="status" name="status">
			<option value="">状态</option>
            <option value="1">投资中</option>
            <option value="2">回款中</option>
			<option value="3">已赎回</option>
         </select>
        <button class="btn btn-default btn-sm J_search" role="button">查询</button>
        </form>
    </script>
</div>
    
    
    


