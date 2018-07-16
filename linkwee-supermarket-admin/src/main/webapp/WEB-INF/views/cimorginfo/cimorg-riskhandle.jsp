<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("ctx", request.getContextPath());
%>
<input id="path" type="hidden" value="${ctx}" />
<script type="text/javascript">
    window.UEDITOR_HOME_URL = '${ctx}/assets/plugins/ueditor/';
</script>
<!-- ueditor -->
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.zimg.js"></script>
<script type="text/javascript" src="app/cimorginfo/ueditor-plug.js"></script>
<script type="text/javascript" src="app/cimorginfo/cimorg-riskhandle.js"></script>
<!-- 图片服务器 -->
<input type="hidden" id="imgServerUrl" value="${img_server}"/>
<div class="container">
	<div class="row text-center">
 		<h4><strong>机构名称：${riskManage.orgName }</strong></h4>
	</div>
	<div class="row">
		<div class="page-header">
			<h5><strong>合规进度</strong></h5>
		</div>
	</div>
	<form id="riskhandleform" action="/rest/cim/cimorginfo/riskHandleSave" method="post">
		<div class="row">
			 <textarea name="complianceProgress" rows="1" cols="100" style="width: 1173px;height: 120px;" placeholder="合规进度   #区分   按照顺序显示">${riskManage.complianceProgress}</textarea>
		</div>
		<div class="row">
			<div class="page-header">
				<h5><strong>FRES评分体系</strong></h5>
			</div>
		</div>
		<c:forEach items="${gradeTypeList}" var="gradeTypeList" varStatus="idx">
			<div class="row" style="margin-top: 50px">
				<div class="col-md-2">
					<strong>${ gradeTypeList.fresName}</strong>
				</div>
			</div>
			<div class="row">
			  <div class="col-md-2 text-right">背景实力总分</div>
	 		  <div class="col-md-4"><input  value="${ gradeTypeList.fresFullScore}"  readonly="readonly"></div>
			  <div class="col-md-2 text-right">背景实力得分</div>
	 		  <div class="col-md-4"><input name="gradeTypes_${idx.count}.fresScore" value="${ gradeTypeList.fresScore}" type="number" required="required" max="${ gradeTypeList.fresFullScore}" min="0"></div> 		
			</div>
			<div class="row" style="margin-top: 20px">
				<div class="col-md-2 text-right">指标得分</div>
	 		  	<div class="col-md-10" >
					<textarea  id="scoreDetail_${ gradeTypeList.fresNumber}"  name="gradeTypes_${idx.count}.scoreDetail" class="ueditorPlug"  placeholder="指标得分" style="width: 740px;height: 200px;" required="required">${gradeTypeList.scoreDetail}</textarea> 		  	
	 		  	</div>
			</div>
			<input type="hidden" name="gradeTypes_${idx.count}.fresName" value="${ gradeTypeList.fresName}"/>
			<input type="hidden" name="gradeTypes_${idx.count}.fresNumber" value="${ gradeTypeList.fresNumber}"/>
		</c:forEach>
		
		
		<div class="row">
			<div class="page-header">
			</div>
		</div>	
		<div class="row" style="margin-top: 50px">
			  <div class="col-md-2 text-right">综合得分</div>
	 		  <div class="col-md-4"><input id="totalScore" name="totalScore" value="${riskManage.totalScore }"  readonly="readonly"></div>
			  <div class="col-md-2 text-right">综合评级</div>
	 		  <div class="col-md-4"><input id="gradeStr" name="gradeStr" value="${riskManage.gradeStr}"  readonly="readonly"></div> 		  
		</div>
		<input type="hidden" name="orgNumber" value="${ orgNumber}"/>

		<div class="row" style="margin-top: 50px">
			<div class="col-md-6 text-center">
				<button type="submit"  class="btn btn-success" style="width: 200px">确认</button>
			</div>
			<div class="col-md-6 text-center">
				<button type="button" class="btn btn-default" onclick='javascript:$.switchPage("合作机构列表","rest/cim/cimorginfo/list");'><i class="fa fa-arrow-left"></i> 返回</button>
			</div>
		</div>
	 </form>
</div>
<script type="text/javascript" src="app/cimorginfo/formdata-convert-tojson.js"></script>
