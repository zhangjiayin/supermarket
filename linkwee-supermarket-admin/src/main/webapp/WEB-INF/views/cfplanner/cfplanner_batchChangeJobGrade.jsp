<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
<form id="redpacketFrom">
			
			<div class="row">
				<label class="col-sm-2 control-label">导入理财师职级列表:</label>
				 <div class="col-sm-3">
				  	 <input  id="datafile" class="form-control" type="file" name="file" />
				</div>
				<label class="col-sm-2 control-label">
					 <span style="color: ;font-size: 10px;">请点击下载<a href="/rest/lcsList/downloadExcelTemplate">《模板》</a>，仅支持xls文件，最大10000条.</span>
				</label>
			</div>
			
			<br>
			<div class="row">
			  <label class="col-sm-2 control-label">
			            备注:<br>
			     EXCEL数据格式如下：          
			     <br>
                                                      总监:   13500001111    SM3
                                                      经理:   13500001111    SM2
                                                      顾问:   13500001111    SM1
                                                      见习:   13500001111    TA
			  </label>
			</div>
			<br>
			<div class="row">
				 <div class="col-md-2 col-md-offset-4">
				 	<a class="btn btn-danger J_changeGrade"  role="button" >导入</a>&nbsp;&nbsp;&nbsp;
				 	<a class="btn btn-default active J_changeGradeback" role="button"  >返回</a>
				 </div>
			</div>
</form>
</div>



 <!-- 模态框（Modal）-->
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" >

$(document).ready(function() {

	//正式发送红包
 	$(".J_changeGrade").click(function() {

 		
 		var file = $('#datafile').val(); //获取文件名
 		var ext = file.substring(file.lastIndexOf(".")+1).toLowerCase(); //获取文件后缀名
 		if(ext != "xls"){
 			layer.msg("不是xls结尾的文件！",{time: 2000,icon: 0});
			return;
 		}
 		var formData = new FormData();
 		formData.append('file', $('#datafile')[0].files[0]);
 		$.ajax({
 	 		    url: './rest/lcsList/importChangeGradeData',
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
 	 		    		$.switchPage("基本信息","rest/lcsList/lcsListPage");
 	 		    	}
				},
		 		error: function (xhr, textStatus, errorThrown) {
		            //错误信息处理
		 			console.info(errorThrown);
		        }
 	 		});
 	 		return;
 	});
	
    $(".J_changeGradeback").on("click", function(event) {
        $.switchPage("基本信息","rest/lcsList/lcsListPage"); 
        return false;
    });

} );
</script>
