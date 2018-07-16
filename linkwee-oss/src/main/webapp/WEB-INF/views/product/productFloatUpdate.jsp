<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <style>
.prdAdd{
	width: 800px;
}
.leftsp{
	width: 200px;
	text-align: right;
	float: left;
	height: 40px;
	font-weight: bolder;
}
.rightsp{
	width: 600px;
	text-align: left;
	float: right;
	height: 40px;
}
input[type="text"] {
	width: 220px;
}
select{
	width: 100px;
}
</style> 
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<div >
	<form  id="productAaddForm" method="post">
	<input name="productId" value="${dtl.productId }" type="hidden">
	 <fieldset>
    <legend>基本信息</legend>
    	<div class="prdAdd">
			<span class="leftsp">产品类型：</span>
			 <span class="rightsp">
			<select name="productCate" id="productCate" style="width:120px; ">
			<%-- <c:forEach items="${proCateList}"  var="item" >
			<option value="${item.cateId }">${item.cateName }</option>
			</c:forEach> --%>
			<option value="1002">浮动收益</option>
			</select> 
			</span>
		</div>
		<div class="prdAdd" style="margin-top: 40px">
			<span class="leftsp">产品名称：</span>
			<span class="rightsp"><input  class="easyui-validatebox" type="text" id="productName" name="productName" data-options="required:true" id="productName" value="${dtl.productName }"><div id="proNameValidDiv" style="display: inline;"></div></span>
		</div>

<!-- 		<div class="prdAdd">
			<span class="leftsp">起息日：</span>
			<span class="rightsp"><input class="easyui-validatebox"  name="validBeginDate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:150px" data-options="required:true">
			</span>
		</div> -->
		<div class="prdAdd">
			<span class="leftsp">产品总额：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" name="buyTotalMoney" data-options="required:true,precision:2" value="<fmt:formatNumber value="${dtl.buyTotalMoney }" pattern="0.00"/>">
			<form:errors path="buyTotalMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">起投金额：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" name="buyMinMoney"  missingMessage="" data-options="required:true,precision:2" value="<fmt:formatNumber value="${dtl.buyMinMoney }" pattern="0.00"/>">
			<form:errors path="buyMinMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">递增金额：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" name="buyIncreaseMoney"  data-options="required:true,precision:2" value="<fmt:formatNumber value="${dtl.buyIncreaseMoney }" pattern="0.00"/>">
			<form:errors path="buyIncreaseMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">单用户购买上限：</span>
			<span class="rightsp"><input class="easyui-numberbox" type="text" name="custBuyMaxMoney" data-options="precision:2" value="<fmt:formatNumber value="${dtl.custBuyMaxMoney }" pattern="0.00"/>">
			<form:errors path="custBuyMaxMoney" cssClass="error" /> 
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">投资期限：</span>
			<span class="rightsp">
			<input class="easyui-numberbox" type="text" name="collectLineMinValue" id="collectLineMinValue" data-options="required:true" value="${dtl.collectLineMinValue }"> ~ <input class="easyui-numberbox" type="text" name="collectLineMaxValue" id="collectLineMaxValue" data-options="required:true" value="${dtl.collectLineMaxValue }">天
			<form:errors path="custBuyMaxMoney" cssClass="error" /> 
			</span>
		</div>

		<div class="prdAdd">
			<span class="leftsp">收益方式：</span>
			<span class="rightsp">
				<select name="repaymentWay">
					<option value="1" <c:if test="${dtl.repaymentWay eq 1}">selected="selected"</c:if>>一次性到期</option>
					<%-- <option value="2" <c:if test="${dtl.repaymentWay eq 2}">selected="selected"</c:if>>一次性按日</option>
					<option value="3" <c:if test="${dtl.repaymentWay eq 3}">selected="selected"</c:if>>一次性按月</option>
					<option value="4" <c:if test="${dtl.repaymentWay eq 4}">selected="selected"</c:if>>一次性按季</option>
					<option value="5" <c:if test="${dtl.repaymentWay eq 5}">selected="selected"</c:if>>等额本息(按月)</option>
					<option value="6" <c:if test="${dtl.repaymentWay eq 6}">selected="selected"</c:if>>等额本息(按季)</option> --%>
				</select>
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">产品说明页：</span>
			<span class="rightsp"><input class="easyui-validatebox" type="text" name="productIllustrationUrl"  id="productIllustrationUrl" data-options="required:true" value="${dtl.productIllustrationUrl }">
			<input type="button" value="添加" id="dtlPageAddBtn">
			<div id="dtlPageDiv" style="display: inline;">
			</div>
			</span>
			
		</div>
		<div class="prdAdd">
			<span class="leftsp">安全保障页：</span>
			<span class="rightsp"><input class="easyui-validatebox" type="text" name="securityGuaranteeUrl" id="securityGuaranteeUrl" data-options="required:true" value="${dtl.securityGuaranteeUrl }">
			<input type="button" value="添加"  id="securePageAddBtn">
			<div id="securePageDiv" style="display: inline;">
			</div>
			</span>
			
		</div>
		<div class="prdAdd">
			<span class="leftsp">金服首页推荐：</span>
			<span class="rightsp">
				<input name="isRecommended" type="radio"  <c:if test="${!dtl.canShowIndexRecomonded}">disabled="disabled"</c:if>
				 value="2" <c:if test="${dtl.isRecommended eq 2}">checked="checked"</c:if> >否 <input name="isRecommended" <c:if test="${dtl.isRecommended eq 1}">checked="checked"</c:if> type="radio" <c:if test="${!dtl.canShowIndexRecomonded}">disabled="disabled"</c:if> value="1" id="indexRecoment"> 是
				&nbsp;*仅在售和预售状态中的产品能设置首页推荐
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">分类排序：</span>
			<span class="rightsp">
				<input  name="cateSort" type="text" class="easyui-numberbox" style="width:120px; " value="${dtl.cateSort}" />
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">列表推荐：</span>
			<span class="rightsp">
				<input name="isListRecommended" type="radio"  value="0" <c:if test="${dtl.isListRecommended eq 0}">checked="checked"</c:if>>否 <input name="isListRecommended" type="radio"  value="1" id="isListRecoment" <c:if test="${dtl.isListRecommended eq 1}">checked="checked"</c:if>> 是
					<div id="ListRecomentDiv" style="display: <c:if test="${dtl.isListRecommended eq 0 }">none</c:if><c:if test="${dtl.isListRecommended eq 1 }">inline</c:if>;">
			<input  name="listRecommendedSort" type="text" class="easyui-numberbox" style="width:120px; " value="${dtl.listRecommendedSort}" />
			推荐语：<input type="text" style="width:120px; " name="listRecomendedStr" value="${dtl.listRecomendedStr }"  id="listRecomendedStr" />最多15个字符，留空则不显示
			</div>
			</span>
		
		</div>
		</fieldset>

		<!-- 募集信息 -->
		<fieldset>
    	<legend>募集信息</legend>
	
		<!-- <div class="prdAdd">
			 <span class="leftsp">是否进行募集：</span> 
			<span class="rightsp">
				<input name="isCollect" type="radio"  value="1" checked="checked" >不进行募集 &nbsp;&nbsp;<input name="isCollect" type="radio"   value="2" > 进行限期募集
			</span>
		</div> -->
		<input name="isCollect" type="hidden"  value="3" />
		<div class="prdAdd">
			<span class="leftsp">起息方式：</span>
			<span class="rightsp">
			<%-- <input name="interestWayType" type="radio"  value="1" <c:if test="${ empty dtl.validBeginDate }">checked="checked"</c:if>> --%>募集完成后
			<select name="interestWay">
			<option value='2' <c:if test="${dtl.interestWay eq 2}">selected="selected"</c:if> >T+1</option>
			</select>起息&nbsp;&nbsp;
			<%--  <input name="interestWayType" type="radio"  value="2" id="indexRecoment" <c:if test="${! empty dtl.validBeginDate }">checked="checked"</c:if>> 固定日期起息
			 <input class="easyui-validatebox"  name="validBeginDate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:150px" value="<fmt:formatDate value="${dtl.validBeginDate }" pattern="yyyy-MM-dd" />" > --%>
			</span>
		</div>

		
		<div class="prdAdd">
			<span class="leftsp">募集有效期到：</span>
			<span class="rightsp"><input class="easyui-validatebox"  name="collectEndTime" id="collectEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:150px" data-options="required:true" value="<fmt:formatDate value="${dtl.collectEndTime }" pattern="yyyy-MM-dd" />">
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">投资客户募集期间收益：</span>
			<span class="rightsp"><input name="hasCollectRate" type="radio"  value="1" <c:if test="${!empty dtl.collectRate && dtl.collectRate != 0 }">checked="checked"</c:if>>募集期间有收益<input type="text" style="width:80px; " name="collectRate" id="collectRate" value="<c:if test="${ dtl.collectRate > 0 }"><fmt:formatNumber value="${dtl.collectRate }" pattern="0.00"/></c:if>"/>%
			&nbsp;&nbsp;<input name="hasCollectRate" type="radio"  value="0" <c:if test="${ empty dtl.collectRate ||  dtl.collectRate==0}">checked="checked"</c:if>>募集期间无收益
			</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">理财师募集期间佣金：</span>
			<span class="rightsp"><input name="hasCollectRatio" type="radio"  value="1" <c:if test="${! empty dtl.collectRatio && dtl.collectRatio != 0  }">checked="checked"</c:if>>募集期间有佣金<input type="text" style="width:80px; " name="collectRatio" id="collectRatio" value="<c:if test="${dtl.collectRatio > 0  }"><fmt:formatNumber value="${dtl.collectRatio }" pattern="0.00"/></c:if>" />%
			&nbsp;&nbsp;<input name="hasCollectRatio" type="radio"  value="0" <c:if test="${empty dtl.collectRatio ||  dtl.collectRatio==0}">checked="checked"</c:if> >募集期间无佣金
			</span>
		</div>

  </fieldset>


		<fieldset>
    <legend>金服端信息</legend>
    	<div class="prdAdd">
			<span class="leftsp">收益模板：</span>
			<span class="rightsp" id="productTypeAddSpan">
			<select name="productTypeId" id="productTypeId" style="width:120px; ">
			<c:forEach items="${proTypeList}"  var="item" >
			<option value="${item.id }" <c:if test="${dtl.productTypeId == item.id }">selected="selected"</c:if> >${item.typeName }</option>
			</c:forEach>
			</select> 
			<input type="button" value="新增收益模板" id="productTypeAddBtn"> 
			<input type="button" value="模板详情" id="productTypeDtlBtn"> 
			<input type="hidden" id="f_line_min_value" />
			<input type="hidden" id="f_line_max_value" />
			</span>
		</div>
		<div id="productTypeAddDiv" style="display: none;text-align: center;padding: 20px;" ></div>
		<div id="productTypeDtlDiv" style="display: none;text-align: center;padding: 10px;" ></div>
		<div class="prdAdd">
			<span class="leftsp">显示标签：</span>
			<span class="rightsp"><!-- <input class="easyui-validatebox"  type="text" name="invCornerIco" style="width:120px; " placeholder="标签1（角标）"> -->
			<input  class="easyui-validatebox"  type="text" name="invLabel1" value="${dtl.invLabel1 }" style="width:120px; " placeholder="标签1">
			<input  class="easyui-validatebox"  type="text" name="invLabel2" value="${dtl.invLabel2 }" style="width:120px; " placeholder="标签2"></span>
		</div>

		<!-- <div class="prdAdd">
			<span class="leftsp">详情页推荐语：</span>
			<span class="rightsp">
				<input  class="easyui-validatebox"  type="text" name="invDtlPageDes" >最多15个字符，留空则不显示
			</span>
		</div> -->
		<div class="prdAdd">
			<span class="leftsp">产品购买协议：</span>
			<span class="rightsp">
				<input  class="easyui-validatebox"  type="text" name="ransferProtocalName"  id="ransferProtocalName" placeholder="协议名称" value="${dtl.ransferProtocalName }" data-options="required:true">
				<input  class="easyui-validatebox"  type="text" name="ransferProtocalUrl"  id="ransferProtocalUrl" placeholder="协议地址" value="${dtl.ransferProtocalUrl }" data-options="required:true">
				<input type="button" value="添加" id="ransferProtocalUrlAddBtn"> 
				<div id="ransferProtocalUrlDiv" style="display: inline;"></div>
			</span>
			
			
		</div>
		<div class="prdAdd">
			<span class="leftsp">收益转让协议：</span>
			<span class="rightsp">
			<%-- <input  class="easyui-validatebox"  type="text" name="productProtocalName" data-options="required:true"  id="productProtocalName" placeholder="协议名称" value="${dtl.productProtocalName }"> --%>
				<%-- <input  class="easyui-validatebox"  type="text" name="productProtocalId"   id="productProtocalId" placeholder="协议地址" value="${dtl.productProtocalId }"> --%>
				<select name="productProtocalId" id="productProtocalId" style="width: 450px;">
				<option value=""  >协议地址</option>
				<c:forEach items="${proProtocalList }" var="item">
			    <option value="${item.id }" <c:if test="${dtl.productProtocalId == item.id }"> selected</c:if>>${item.protocalFileUrl }</option>
				</c:forEach>
				</select>
				<input type="button" value="添加" id="productProtocalAddBtn"> 
				<div id="productProtocalDiv" style="display: inline;"></div>
			</span>
			
		</div>
	</fieldset>
	<fieldset>
    <legend>理财师端信息</legend>
    	<div class="prdAdd">
			<span class="leftsp">年化佣金：</span>
			<span class="rightsp"><input  class="easyui-numberbox"  type="text" name="feeRatio" data-options="min:0,precision:2,required:true,validType:'number'" value="<fmt:formatNumber value="${dtl.feeRatio }" pattern="0.00"/>">%*支持两位小数</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">销售奖励：</span>
			<span class="rightsp"><input  class="easyui-numberbox"  type="text" name="saleReward" data-options="min:0,precision:2,validType:'number'" value="<fmt:formatNumber value="${dtl.saleReward }" pattern="0.00"/>">% *支持两位小数</span>
		</div>
		<div class="prdAdd">
			<span class="leftsp">显示标签：</span>
			<!-- <span class="rightsp"><input  class="easyui-validatebox"  type="text" name="lcsCornerIco" style="width:120px; " placeholder="标签1（角标）"> -->
			<input  class="easyui-validatebox"  type="text" name="lcsLabel1" style="width:120px; " placeholder="标签1" value="${dtl.lcsLabel1 }">
			<input  class="easyui-validatebox"  type="text" name="lcsLabel2" style="width:120px; " placeholder="标签2" value="${dtl.lcsLabel2 }"></span>
		</div>

		<!-- <div class="prdAdd">
			<span class="leftsp">详情页推荐语：</span>
			<span class="rightsp">
				<input  class="easyui-validatebox"  type="text" name="lcsDtlPageDes" >最多15个字符，留空则不显示
			</span>
		</div> -->
	</fieldset>
	
	<fieldset>
    <legend>开售设置</legend>
            <div class="prdAdd">
			<span class="leftsp">类型：</span>
			<span class="rightsp">
			<input name="beginSaleType" type="radio"  value="1" <c:if test="${ dtl.beginSaleType eq 1 }">checked="checked"</c:if> >即时
			<input name="beginSaleType" type="radio"  value="2" id="isListRecoment" <c:if test="${ dtl.beginSaleType eq 2 }">checked="checked"</c:if> > 定时
			
			<div id="fixSaleTimeDiv" style="display: <c:if test="${ dtl.beginSaleType eq 2 }">inline</c:if><c:if test="${ dtl.beginSaleType ne 2 }">none</c:if>;">
				<input  name="beginSaleTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:150px" id="fixSaleTimeInput" <c:if test="${ dtl.beginSaleType ne 2 }">disabled="disabled"</c:if> value="<fmt:formatDate value="${dtl.beginSaleTime }" pattern="yyyy-MM-dd HH:mm:ss" />" >
			</div>
			<div id="preSaleDiv" style="display: <c:if test="${ dtl.beginSaleType eq 3 }">inline</c:if><c:if test="${ dtl.beginSaleType ne 3 }">none</c:if>;">
			<input name="beginSaleType" type="radio"  value="3" id="isListRecoment" <c:if test="${ dtl.beginSaleType eq 3 }">checked="checked"</c:if>> 预售
			<div id="preSaleTimeDiv" style="display: <c:if test="${ dtl.beginSaleType eq 3 }">inline</c:if><c:if test="${ dtl.beginSaleType ne 3 }">none</c:if>;">
				<input  name="beginSaleTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:150px" id="preSaleTimeInput" <c:if test="${ dtl.beginSaleType ne 3 }">disabled="disabled"</c:if> value="<fmt:formatDate value="${dtl.beginSaleTime }" pattern="yyyy-MM-dd HH:mm:ss" />" >
			</div>
			</div>
				
			</span>
	</div>
    <div class="prdAdd">
			<span class="leftsp">下架时间：</span>
			<span class="rightsp"><input  name="endSaleTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:150px"  value="<fmt:formatDate value="${dtl.endSaleTime }" pattern="yyyy-MM-dd HH:mm:ss" />">
			</span>
	</div>
    </fieldset>

	
		<div style="clear: both;"></div>																		
		<!-- <div style="padding-top: 60px;text-align:center;">
			<span>-------------------------------------产品佣金配置------------------------------------------------</span>
		</div> -->
	</form>
	
	<div class="prdAdd" style="margin-top: 50px;margin-bottom: 100px;">
		<span style="margin-left: 300px"> <input type="button" id="productUpdate" value="保存" style="width: 80px;height: 30px"/></span>
		<span style="margin-left: 50px"> <input type="button"  id="productAddcacl" value="取消" style="width: 80px;height: 30px"/></span>
	</div>
	
</div>

<script type="text/javascript" src="app/product/productAdd.js"></script>
<script type="text/javascript" src="app/product/productFloatAdd.js"></script>
