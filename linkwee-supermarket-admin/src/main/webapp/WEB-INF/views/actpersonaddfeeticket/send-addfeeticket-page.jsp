<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
<form role="form"  method="post" id="sendAddFeeTicketFrom">
	<input type="hidden" id="ticketId" value="${param.ticketId}">
    <div class="row">
        <label class="col-sm-2 control-label">有效日期:</label>
        <div class="col-sm-6">
            <input id="startDate" placeholder="生效日期" name="startTime" class="easyui-datebox" style="width:110px" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})"> 至
            <input id="endDate" placeholder="过期日期" name="endTime" class="easyui-datebox" style="width:110px" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})">
        </div>
    </div>
    <br>
	<div class="row">
		<label class="col-sm-2 control-label">备注:</label>
		<div class="col-sm-6">
			<input type="text" name="remark" id="remark">
		</div>
	</div>
	<br>
	<div class="row">
        <label class="col-sm-2 control-label">理财师名单:</label>
	</div>
	<br>
	<div class="row">
		 <div class="col-sm-3">
		  	 <input  id="datafile" class="form-control" type="file" name="file" />
		</div>
		<label class="col-sm-2 control-label">
			 <span style="color: red;font-size: 10px;">请点击下载<a href="/rest/cim/actpersonaddfeeticket/downloadImportTemplate">《模板》</a>，仅支持xls文件，最大10000条.</span>
		</label>
	</div>
	<br>
	<div class="row">
		 <div class="col-sm-2 col-sm-offset-4">
		 	<a class="btn btn-danger J_sendAddFeeTicket"  role="button" >发放</a>&nbsp;&nbsp;&nbsp;
		 	<a class="btn btn-default" data-dismiss="modal"  role="button" >关闭</a>
		</div>
	</div>
  </form>
</div>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

$(document).ready(function() {

	//正式发放个人加拥券
 	$(".J_sendAddFeeTicket").click(function() {

 		var startDate = $("#startDate").val();
 		var endDate = $("#endDate").val();
 		var remark = $("#remark").val();

 		if(!startDate){
 			layer.msg('生效日期不能为空',{time: 1000,icon: 0});
 			return false;
 		}
        if(!endDate){
            layer.msg('过期日期不能为空',{time: 1000,icon: 0});
            return false;
        }

 		var file = $('#datafile').val(); //获取文件名
 		var ext = file.substring(file.lastIndexOf(".")+1).toLowerCase(); //获取文件后缀名
 		if(ext != "xls"){
 			layer.msg("不是xls结尾的文件！",{time: 2000,icon: 0});
			return;
 		}
 		var formData = new FormData();
 		formData.append('file', $('#datafile')[0].files[0]);
 		formData.append('ticketId',$("#ticketId").val());
 		formData.append('startDate',startDate);
 		formData.append('endDate',endDate);
        formData.append('remark',remark);
 		$.ajax({
 	 		    url: './rest/cim/actpersonaddfeeticket/sendAddFeeTicket',
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
						//
						console.log(data.data);
						var alertMsg = "";
						for(var i in data.data){
						    if(data.data[i] != "用户不存在_null"){
                                alertMsg += data.data[i].split("_")[1] +"<br/>";
							}
						}
						if(alertMsg){
                            layer.alert("除下列用户外，其余发放成功。<br/>不存在用户:<br/>"+alertMsg, {icon: 0});
						}
 	 		    	}
 	 		    	$('#publicModal').modal("hide"); //隐藏
				},
		 		error: function (xhr, textStatus, errorThrown) {
		            //错误信息处理
		 			console.info(errorThrown);
		        }
 	 		});
 	 		return;
 	});

});
</script>


