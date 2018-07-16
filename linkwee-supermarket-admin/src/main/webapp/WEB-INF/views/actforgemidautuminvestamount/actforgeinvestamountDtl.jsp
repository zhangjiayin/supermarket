<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
 request.setAttribute("ctx", request.getContextPath());
%>
<input id="path" type="hidden" value="${ctx}" />
<script type="text/javascript">
	window.UEDITOR_HOME_URL = '${ctx}/assets/plugins/ueditor/';
</script>
<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.zimg.js"></script>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10">
			<form id="uploadZimg">
				<input id="lefile" type="file" name="userfile11" onchange="uploadSubmit(this);" style="display: none"/>
			</form>
			<form id="xforgeInvestAmountForm" action="rest/act/actforgemidautuminvestamount/save" method="post">
				<c:if test="${!empty actForgeMidautumInvestAmount}">
					<input type="hidden" name="id" value="${actForgeMidautumInvestAmount.id}"/>
				</c:if>
				<div class="form-group">
					<label>姓名</label>
					<input name="userName" type="text" class="form-control" value="${actForgeMidautumInvestAmount.userName }"  placeholder="请输入姓名" />
				</div>
				<div class="form-group">
					<label>电话</label>
					<input name="mobile" type="text" class="form-control" value="${actForgeMidautumInvestAmount.mobile}" placeholder="请输入电话"  />
				</div>
				<div class="form-group">
					<label>总投资额</label>
					<input name="investAmt" type="text" class="form-control" value="${actForgeMidautumInvestAmount.investAmt}" placeholder="请输入总投资额"  />
				</div>
				<div class="form-group">
					<label>图像</label>
					<div class="input-append">
						<%--<input id="photoCover" autocomplete="off" class="form-control" type="text" placeholder="点击这里选择图片并上传要上传的图片,图片分辨率​259 * 149" readonly="readonly">--%>
						<a class="form-control" href="javascript:;" onclick="$('input[id=lefile]').click(); return false;" style="width: 500px; text-decoration: none; background-color: #1fa67a; color: #ffffff;">点击这里选择图片并上传</a>
					</div>
				</div>
				<div class="form-group">
					<input name="headImage" type="hidden"/>
					<div class="row" id="images-list">
						<c:if test="${ !empty actForgeMidautumInvestAmount.headImage}">
							<img src="${actForgeMidautumInvestAmount.headImage}" id="img-thumbnail" class="image thumbnail" onerror="javascript:$(this).remove();" style="width: 300px;" />
						</c:if>
					</div>
				</div>
										
				<button type="submit" class="btn btn-default">发布</button>
				
			</form>
		</div>
	</div>
	<script type="text/javascript">
		
	jQuery.validator.addMethod("checkPicSize", function(value,element) {
	    var fileSize=element.files[0].size;
	    var maxSize = 100*1024;
	    if(fileSize > maxSize){
	        return false;
	    }else{
	        return true;
	    }
	}, "请上传大小在100K以下的图片");
	
		var zimgUrl = "${img_server}";

		var previewImage = function (evt) {
			if (!window.FileReader) return;
			var files = evt.target.files;
			for (var i = 0, f; f = files[i]; i++) {
				if (!f.type.match('image.*')) {
					continue;
				}
				var reader = new FileReader();
				reader.onload = (function(theFile) {
					return function(e) {
						// img 元素
						$("#images-list").html("");
						var im = document.getElementById("#images-list");
						var pim = document.createElement("img");
						pim.setAttribute("class","img-thumbnail");
						pim.src = e.target.result;
						pim.setAttribute("style","max-width:300px;");
						im.appendChild(pim);
						$("#images-list").html(im);

					};
				})(f);
				reader.readAsDataURL(f);
			}
		}

		jQuery.validator.addMethod("zn",function (value, element) {
			value = value.replace(/\s/g);
			if(value.length>0){
				return /^[\u4e00-\u9fa5a-z_A-Z0-9]*$/.test(value);
			}
			return true;
		});
		var showImage =function (url) {
			$("#images-list").html('<img src="'+url+'" class="img-thumbnail"/>');
		}
		
		var uploadImage = function ($file) {
			var fileSize=$file.files[0].size;
			 var maxSize = 100*1024;
			 if(fileSize > maxSize){
			    bootbox.alert("请上传大小在100K以下的图片");
			    $("#lefile").val("");
			    return;
			 }
			var file_val = $($file).val();
			if($file.files.length>0){
				$("#photoCover").val($file.files[0].name);
			}
			return false;
		};
		
		var uploadSubmit = function ($file) {
			var file_val = $("#lefile").val();
					
			var fileSize=$file.files[0].size;
			 var maxSize = 100*1024;
			 if(fileSize > maxSize){
			    bootbox.alert("请上传大小在100K以下的图片");
			    $("#lefile").val("");
			    return;
			 }  
			 		
//			debugger;
			if(file_val.length>0){
				var fileData = new FormData(document.getElementById("uploadZimg"));
				$.ajax({
					type:'post',
					url: zimgUrl,
					data: fileData,
					async: false,
					cache: false,
					contentType: false,
					processData: false,
					success:function (data) {
						if(data.indexOf("MD5")!=-1){
							var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(","));
							$('input[type=hidden][name=headImage]').val(result);
							showImage(zimgUrl+result+"?w=160&h=160");
						}
						else{
							console.log("upload fail.");
						}
					}
				});
			}
		}
		
			$("#xforgeInvestAmountForm").submit(function () {
	
			}).validate({
				ignore: "",
				focusInvalid:false,
				errorElement: 'span',
				errorClass: 'help-block help-block-error',
				rules:{
					mobile:{
						required:true
					},
					investAmt:{
						required:true
					}
				},
				messages:{
					mobile:{
						required:"请填写手机号"
					},
					investAmt:{
						required:"请填写投资总额"
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
									$.switchPage("数据列表","rest/act/actforgemidautuminvestamount/list");
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

