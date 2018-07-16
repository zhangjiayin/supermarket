<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
<form id="redpacketFrom">
<input type="hidden" id="salesOrgId">
			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">上传投资记录(excel)</p></small></h4>
					</div>
				</div>
			</div>
			
			<div class="row">
				<label class="col-sm-2 control-label">文件名称:</label>
				<div class="col-md-4">
				<input  type="text" name="fileName" id="fileName"  />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">备注:</label>
				<div class="col-md-4">
					<textarea name="fileRemark" id="fileRemark" autocomplete="off" style="width: 250px;height: 100px;" class="form-control" placeholder="描述"></textarea>
				</div>
			</div>
			<br>
			<div class="row">
				 <div class="col-sm-3">
				  	 <input  id="datafile" class="form-control" type="file" name="file" />
				</div>
				<label class="col-sm-2 control-label">
					 <span style="color: ;font-size: 10px;">请点击下载<a href="/rest/cim/cimproductunrecordinvest/downloadExcelTemplate">《模板》</a>，仅支持xls文件，最大10000条.</span>
				</label>
			</div>
			
			<br>
			<div class="row">
				 <div class="col-md-2 col-md-offset-4">
				 	<a class="btn btn-danger J_sendRedpacket"  role="button" >导入</a>&nbsp;&nbsp;&nbsp;
				 	<a class="btn btn-default active J_goback" role="button"  >返回</a>
				 </div>
			</div>
</form>
</div>



 <!-- 模态框（Modal）-->
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" >

$(document).ready(function() {

	//正式发送红包
 	$(".J_sendRedpacket").click(function() {

 		if($('#fileName').val() == '') {
 			layer.msg("请输入文件名称！",{time: 2000,icon: 0});
 			return;
 		}
// 		if($('#fileRemark').val() == '') {
// 			layer.msg("请填写备注！",{time: 2000,icon: 0});
// 			return;
//  		}
 		var file = $('#datafile').val(); //获取文件名
 		var ext = file.substring(file.lastIndexOf(".")+1).toLowerCase(); //获取文件后缀名
 		if(ext != "xls"){
 			layer.msg("不是xls结尾的文件！",{time: 2000,icon: 0});
			return;
 		}
 		var formData = new FormData();
 		formData.append('file', $('#datafile')[0].files[0]);
 		formData.append('fileRemark',$("#fileRemark").val());
 		formData.append('fileName',$("#fileName").val());
 		$.ajax({
 	 		    url: './rest/cim/cimproductunrecordinvest/importRewardData',
 	 		    type: 'POST',
 	 		    cache: false,
 	 		    data: formData,
 	 		    processData: false,
 	 		    contentType: false,
 	 		    success: function(data) {
 	 		    	if(data.isFlag == false){
 	 		    		layer.alert(data.msg, {icon: 0}); //失败
 	 		    	}else{
 	 		    		layer.msg(data.msg,{time: 2000,icon: 1}); //成功
 	 		    		$.switchPage("A专区投资列表","rest/cim/cimproductunrecordinvest/list");
 	 		    	}
				},
		 		error: function (xhr, textStatus, errorThrown) {
		            //错误信息处理
		 			console.info(errorThrown);
		        }
 	 		});
 	 		return;
 	});
	
    $(".J_goback").on("click", function(event) {
        $.switchPage("A专区投资列表","rest/cim/cimproductunrecordinvest/list"); //跳到机构列表页面
        return false;
    });

} );
</script>
