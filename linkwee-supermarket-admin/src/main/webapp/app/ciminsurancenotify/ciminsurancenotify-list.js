$(function(){
	
	//产品编辑按钮
    $("#J-newslist").on("click",'.J_insurance_audit',function(event){
    	//点击到图标上面  则不响应
    	if($(event.target).context.localName == 'i'){
    		return false;
        }
 	    var tableId = $(event.target).attr("data-id");
 		$("#insuranceNotifyIdForEdit").val(tableId);
 		$("#auditModal").modal('show');
    });
    
    //批量审核保存
    $("body").on("click",'#auditSave',function(event){
       $(event.target).attr('disabled',"true");
 	   var id = $("#insuranceNotifyIdForEdit").val();;
 	   var auditStatus = $("input[name='auditStatus']:checked").val();;
 		$.ajax({
 			   type: "POST",
 			   url: "rest/cim/ciminsurancenotify/audit",
 			   data: {"id":id,"auditStatus":auditStatus},
 			   success: function(message){
 				   if(message == "success"){
 					   $("#auditModal").modal('hide');
 					   $("button.J_search").trigger('click');
 				   } else {
 					   $("#auditError").html("审核失败["+message+"]");	
 				   }
 				  $(event.target).removeAttr("disabled");
 			   },
 			   error: function (XMLHttpRequest, textStatus, errorThrown) {
 				   $("#auditError").html("审核异常");
 				   $(event.target).removeAttr("disabled");
 			}
 		});
 	});
    
});