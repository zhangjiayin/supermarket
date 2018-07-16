<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
 request.setAttribute("ctx", request.getContextPath());
%>
<%--  <link href="assets/plugins/bootstrap-daterangepicker/css/daterangepicker.css" rel="stylesheet" type="text/css" />--%>
<input id="path"  type="hidden"  value="${ctx}" />
<script type="text/javascript">
	window.UEDITOR_HOME_URL = '${ctx}/assets/plugins/ueditor/';
</script>
<script src="assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<%--<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/js/moment.min.js"  ></script>--%>
<%--<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/js/daterangepicker.js"  ></script>--%>
<%--<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>--%>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.zimg.js"></script>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10">
		    <form id="uploadZimg">
				<input id="lefile" type="file" name="userfile" onchange="uploadSubmit(this);" style="display:none">
			</form>
			<form id="uploadZicon">
				<input id="iconfile" type="file" name="userfile" onchange="uploadIcon(this);" style="display: none" />
			</form>
			<form id="editSmbrandpromotionForm" action="rest/acc/smbrandposters/save" method="post">
				<c:if test="${!empty smBrandPosters}">
					<input name="id" type="hidden" value="${smBrandPosters.id}"/>
				</c:if>
				
				<div class="form-group">
					<label>海报类型</label>
					<select id="typeValue" class="form-control" name="typeValue">
            			<c:forEach items="${typeList}" var="type">
							<option value="${type.typeId}" <c:if test="${type.typeId eq smBrandPosters.typeValue }">selected</c:if> >${type.posterName}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<label>列表图片</label>
					<div class="input-append">
						<a class="form-control" href="javascript:;" onclick="$('input[id=lefile]').click(); return false;" style="width: 500px; text-decoration: none; background-color: #1fa67a; color: #ffffff;">点击这里选择图片并上传,图片分辨率​为259 * 149</a>
					</div>
				</div>
				<div class="form-group">
					<input name="image" type="hidden"/>
					<div class="row" id="images-list">
						<c:if test="${ !empty smBrandPosters.image}">
							<img src="${smBrandPosters.image}" id="img-thumbnail" class="image thumbnail" onerror="javascript:$(this).remove();" style="width: 300px;" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group">
					<label>缩略图标</label>
					<div class="input-append">
						<a class="form-control" href="javascript:;" onclick="$('input[id=iconfile]').click(); return false;" style="width: 500px; text-decoration: none; background-color: #1fa67a; color: #ffffff;">点击这里选择图片并上传,图片分辨率​为259 * 149</a>
					</div>
				</div>
				<div class="form-group">
					<input name="smallImage" type="hidden"/>
					<div class="row" id="images-showIcon">
						<c:if test="${ !empty smBrandPosters.smallImage}">
							<img src="${smBrandPosters.smallImage}" id="img-thumbnail" class="image thumbnail" onerror="javascript:$(this).remove();" style="width: 300px;" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group">
					<label>发布时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input id="validBegin" name="validBegin" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'validEnd\')}'})"  style="width:150px" value="<fmt:formatDate value="${smBrandPosters.validBegin }" pattern="yyyy-MM-dd HH:mm:ss"/> " />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label>到期时间</label>
					<div class="row">
						<div class="col-sm-6">
							<input id="validEnd" name="validEnd" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'validBegin\')}'})"  style="width:150px" value="<fmt:formatDate value="${smBrandPosters.validEnd }" pattern="yyyy-MM-dd HH:mm:ss"/>" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label>显示顺序</label>
					<input id="showInx" name="showInx" class="form-control"  value="${smBrandPosters.showInx }" />
				</div>
                  
				<button type="submit" class="btn btn-default">发布</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
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
						var pim = document.createElement("image");
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
		
		var showIcon =function (url) {
			$("#images-showIcon").html('<img src="'+url+'" class="img-thumbnail"/>');
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
		var uploadSubmit = function () {
			var file_val = $("#lefile").val();
//			debugger;
			if(file_val.length>0){
				var fileData = new FormData(document.getElementById("uploadZimg"));
				console.log("fileData",fileData);
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
							console.log("upload ok");
							var result =  data.substring(data.indexOf("MD5:")+4,data.indexOf(","));
							$('input[type=hidden][name=image]').val(result);
							showImage(zimgUrl+result+"?w=160&h=160");
						}
						else{
							console.log("upload fail.");
						}
					}
				});
			}
		}

		var uploadIcon = function ($file) {
			var fileSize=$file.files[0].size;
			 var maxSize = 100*1024;
			 if(fileSize > maxSize){
				 bootbox.alert("请上传大小在100K以下的图片");
			    $("#iconfile").val("");
			    return;
			 }
			var file_val = $($file).val();
			if(file_val.length>0){
				var fileData = new FormData(document.getElementById("uploadZicon"));
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
							console.log(result);
							$('input[type=hidden][name=smallImage]').val(result);
							showIcon(zimgUrl+result+"?w=160&h=160");
						}
						else{
							console.log("uploadIcon upload fail.");
						}
					}
				});
			}
		}

			$("#editSmbrandpromotionForm").validate({
				ignore: "",
				focusInvalid:false,
				errorElement: 'span',
				errorClass: 'help-block help-block-error',
				rules:{
					showInx:{
						required:true
					},

				},
				messages:{
					showInx:{
						required:"显示顺序不能为空",
						zn:'只能输入数字'
					},

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
					uploadSubmit();
					$(form).ajaxSubmit({
						success:function (result) {
							console.log("result.isFlag",result.isFlag);
							if(result.isFlag){
								bootbox.alert(result.msg,function () {
									$.switchPage("4.5.3推广海报 ","/rest/acc/smbrandposters/init");
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

