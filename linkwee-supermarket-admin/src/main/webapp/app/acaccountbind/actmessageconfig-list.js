
$(document).ready(function() {
    
    $(".J_cpa_seting").on("click",function(event){
    	 layer.confirm('确定切换短信通道?', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(){
  		   $.post('rest/messageConfit/setMessagePlatform', {'messageId': $("#messageId").val()}, function(data, textStatus, xhr) {
  			   layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
  	         });
  			
             });
    	
    });
} );
