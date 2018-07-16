<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
<form id="redpacketFrom">
<input type="hidden" id="salesOrgId" >
			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">新增职级体验券</p></small></h4>
					</div>
				</div>
			</div>
			
			<div class="row">
				<label class="col-sm-2 control-label">活动属性:</label>
				<div class="col-md-4">
				<input  type="text" name="activityAttr" id="activityAttr"  />
				</div>
			</div>
			<br>
			
			<div class="row">
				<label class="col-sm-2 control-label">使用时间:</label>
				<div class="col-md-4">
				<input id="useTime" name="useTime" class="Wdate" type="text" value ="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'expiresTime\')}'})" />
				</div>
			</div>
			<br>
			
			<div class="row">
				<label class="col-sm-2 control-label">过期时间:</label>
				<div class="col-md-4">
				<input id="expiresTime" name="expiresTime" class="Wdate" type="text" value ="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'useTime\')}'})" />
				</div>
			</div>
			<br>
			
			
			<div class="row">
				<label class="col-sm-2 control-label">职级</label>
				<div class="col-md-4">
				<select name="jobGrade" id="jobGrade">
					<option value="">请选择</option>
					<option value="SM2">经理</option>
					<option value="SM3">总监</option>
				</select>
				</div>
			</div>
			<br>
			
			<div class="row">
				 <div class="col-sm-3">
				  	 <input  id="datafile" class="form-control" type="file" name="file" />
				</div>
				<label class="col-sm-2 control-label">
					 <span style="color: ;font-size: 10px;">请点击下载<a href="/rest/acc/actjobgradevoucher/downloadExcelTemplate">《模板》</a>，仅支持xls文件，最大10000条.</span>
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
    
 		if($('#activityAttr').val() == '') {
 			layer.msg("请输入活动属性！",{time: 2000,icon: 0});
 			return;
 		}
		if($('#useTime').val() == '') {
			layer.msg("请选择使用时间！",{time: 2000,icon: 0});
			return;
 		}
		if($('#expiresTime').val() == '') {
			layer.msg("请选择过期时间！",{time: 2000,icon: 0});
			return;
 		}
		
		if($('#useTime').val().substring(5,7)!=$('#expiresTime').val().substring(5,7)){
			layer.msg("请选择同一月份时间！",{time: 2000,icon: 0});
			return;
		}
		if($('#jobGrade').val() == '') {
			layer.msg("请选择职级！",{time: 2000,icon: 0});
			return;
 		}
 		var file = $('#datafile').val(); //获取文件名
 		var ext = file.substring(file.lastIndexOf(".")+1).toLowerCase(); //获取文件后缀名
 		if(ext != "xls"){
 			layer.msg("不是xls结尾的文件！",{time: 2000,icon: 0});
			return;
 		}
 		var formData = new FormData();
 		formData.append('file', $('#datafile')[0].files[0]);
 		formData.append('activityAttr',$("#activityAttr").val());
 		formData.append('jobGrade',$("#jobGrade").val());
 		formData.append('useTime',$("#useTime").val());
 		formData.append('expiresTime',$("#expiresTime").val());
 		$.ajax({
 	 		    url: './rest/acc/actjobgradevoucher/importJobGradeVoucherData',
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
 	 		    		$.switchPage("职级体验券列表","rest/acc/actjobgradevoucher/list"); //职级体验券列表页面
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
        $.switchPage("机构列表","rest/acc/actjobgradevoucher/list"); //跳到机构列表页面
        return false;
    });

} );
</script>
