<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
 request.setAttribute("ctx", request.getContextPath());
%>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10">
			<form id="uploadZimg">
				<input id="lefile" type="file" name="userfile"
					onchange="uploadImage(this);" style="display: none">
			</form>
			<form id="advForm" action="<c:if test="${empty actionType}">rest/cms/activitylist/save</c:if><c:if test="${actionType eq 'edit'}">rest/cms/activitylist/update</c:if>" method="post">
			<c:if test="${actionType eq 'edit'}">
			<input type="hidden" name="id" value="${dtl.id}"/>
			</c:if>
				<%-- <div class="form-group">
					<label>图片</label>
					<div class="input-append">
						<input id="photoCover" class="input-large" type="text" placeholder="请选择要上传的图片" style="height:30px;">
						<button class="btn btn-primary" onclick="$('input[id=lefile]').click(); return false;">选择图片</button>
					</div>
				</div>
				<div class="form-group">
					<input name="imgUrl" type="hidden" value="${dtl.imgUrl}"/>
					<div class="row" id="images-list">
						<c:if test="${ !empty dtl.imgUrl}">
							<img src="${dtl.imgUrl}"  id="img-thumbnail" style="width: 300px;" />
						</c:if>
					</div>
				</div> --%>

				

				<div class="form-group">
					<label>名称</label> <input name="activityName" type="text" class="form-control"  maxlength="50"
						value="${dtl.activityName }" />
				</div>

				<div class="form-group" >
					<label>活动图标连接</label> <input name="activityImg" type="text" class="form-control"  maxlength="100"
						value="${dtl.activityImg }" />
				</div>

				<div class="form-group">
					<label>结束图片地址</label> <input name="activityEndImg" type="text" class="form-control"  maxlength="100"
						value="${dtl.activityEndImg }" />
				</div>
				
				<div class="form-group" >
					<label>活动连接</label> <input name="linkUrl"  class="form-control"  maxlength="100"
						value="${dtl.linkUrl }" />
				</div>

				<div class="form-group">
					<label>有效时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input name="startDate" class="Wdate"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})"
								style="width: 200px" value="<fmt:formatDate value="${dtl.startDate }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								id="startDate" />至
								<input name="endDate"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})"
								class="Wdate" style="width: 200px" value="<fmt:formatDate value="${dtl.endDate }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								id="endDate" />
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label>状态</label> <select id="status" class="form-control"
						name="status" >
						<option value="0"
							<c:if test="${dtl.status eq '0' }">selected</c:if>>启用</option>
						<option value="1"
							<c:if test="${dtl.status eq '1' }">selected</c:if>>禁用</option>
					</select>
				</div>
				
				<div class="form-group">
					<label>活动编码</label> <input name="activityCode" type="text" class="form-control"  maxlength="50"
						value="${dtl.activityCode }" />
				</div>
				
				<div class="form-group">
					<label>应用类别</label> <select id="appType" class="form-control"
						name="appType" >
						<option value="1"
							<c:if test="${dtl.appType eq '1' }">selected</c:if>>理财师端</option>
						<option value="2"
							<c:if test="${dtl.appType eq '2' }">selected</c:if>>金服端</option>
					</select>
				</div>
				
				
				
				<div class="form-group">
					<label>分享标题</label> <input name="shareTitle" type="text" class="form-control"  maxlength="64"
						value="${dtl.shareTitle }" />
				</div>
				
				<div class="form-group">
					<label>分享详情</label> <input name="shareDesc" type="text" class="form-control"  maxlength="128"
						value="${dtl.shareDesc }" />
				</div>
				
				<div class="form-group">
					<label>分享图标</label> <input name="shareIcon" type="text" class="form-control"  maxlength="128"
						value="${dtl.shareIcon }" />
				</div>
				
				<div class="form-group">
					<label>分享连接</label> <input name="shareLink" type="text" class="form-control"  maxlength="128"
						value="${dtl.shareLink }" />
				</div>

				<button type="submit" class="btn btn-default">保存</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	 jQuery.validator.addMethod("isNeedLinkUrl", function(value, element) {
		 if($("#pageLocation").val() =="app_home_page"){
			     return value.length>0;
		 }else{
			 return true;
		 }

		}, "首页banner需设置图片链接地址");   
	//控制图片链接显示
		$('#pageLocation').change(function(){ 
		var optVal = $(this).children('option:selected').val();
		if(optVal =='app_opening'){
			$('#imgLinkUrlDiv').css('display','none');
			$('#showIndexDiv').css('display','none');
		}else{
			$('#imgLinkUrlDiv').css('display','block');
			$('#showIndexDiv').css('display','block');
		}
		
		});
		var showImage =function (url) {
			$("#images-list").html('<img src="'+url+'" class="img-thumbnail"/>');
		}
		var uploadImage = function ($file) {
			var file_val = $($file).val();
			if(file_val.length>0){
				var fileData = new FormData(document.getElementById("uploadZimg"));
				$.ajax({
					type:'post',
					url: 'http://preimage.tophlc.com/',
					data: fileData,
					async: false,
					cache: false,
					contentType: false,
					processData: false,
					success:function (data) {
						if(data.indexOf("MD5")!=-1){
							var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(","));
							console.log(result);
							$('input[type=hidden][name=imgUrl]').val(result);
							showImage("http://preimage.tophlc.com/"+result+"?w=160&h=160");
						}
						else{
							console.log("upload fail.");
						}
					}
				});
			}
		}

		
			$("#advForm").validate({
				focusInvalid:false,
				errorElement: 'span',
				errorClass: 'help-block help-block-error',
				rules:{
					activityName:{
						required:true
					},
					activityImg:{
						required:true
					},
					linkUrl:{
						required:true
					},
					startDate:{
						required:true
					},
					endDate:{
						required:true
					},
					activityEndImg:{
						required:true
					}

				},
				messages:{
					activityName:{
						required:"名称不能为空"
					},
					activityImg:{
						required:"活动图片地址不能为空"
					},
					linkUrl:{
						required:"活动连接不能为空"
					},
					startDate:{
						required:"请选择发布开始时间"
					},
					endDate:{
						required:"请选择发布结束时间"
					},
					activityEndImg:{
						required:"活动结束图片地址不能为空"
					}


				},
				errorPlacement:function (error,element) {
					element.parent().addClass("has-error");
					error.appendTo(element.parent());
				},
				success:function (element) {
					element.closest('.form-group').removeClass('has-error');
					element.remove();
				},
				submitHandler:function (form) {
					$(form).ajaxSubmit({
						success:function (result) {
							if(result.isFlag){
								bootbox.alert(result.msg,function () {
									$.switchPage("广告列表","rest/cms/activitylist/list");
								});

							}
							else{
								bootbox.alert(result.msg);
							}

						}
					});
				}

			});
	</script>
</div>