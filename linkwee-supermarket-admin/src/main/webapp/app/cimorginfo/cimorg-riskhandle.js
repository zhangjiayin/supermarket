$(function(){
	
	//背景实力得分
	$('input[name$="fresScore"]').change(function(){
		var totalScore = 0;
		var gradeStr = "B";
		
		$('input[name$="fresScore"]').each(function(){
			var score = $(this).val();
			totalScore += eval(score == ''?0:score);
		});

		if(totalScore >= 90){
			gradeStr = "AAA";
		} else if(totalScore >= 85 &&  totalScore < 90){
			gradeStr = "AA";
		} else if(totalScore >= 80 &&  totalScore < 85){
			gradeStr = "A";
		} else if(totalScore >= 75 &&  totalScore < 80){
			gradeStr = "BBB";
		} else if(totalScore >= 70 &&  totalScore < 75){
			gradeStr = "BB";
		} else if(totalScore >= 65 &&  totalScore < 70){
			gradeStr = "B";
		}
		
		$('#totalScore').val(totalScore);
		$('#gradeStr').val(gradeStr);
	});
	
	/**
	 * 判断表单的信息是否通过验证
	 */
	$("#riskhandleform").validate({
		//debug: true, //只验证不提交表单
	    //meta: "validate",//设置meta来封装验证规则
		//onkeyup:false, //是否在敲击键盘时验证
		ignore: "", //验证隐藏域
	    errorElement: 'span',
	    errorClass: 'help-block help-block-error',
	    focusInvalid: true, //当为false时，验证无效时，没有焦点响应
	    rules : {  
	    	complianceProgress:"required", //单个条件验证
			totalScore:{
	    		min:65 //输入值不能小于 65
			}		
	    },  
	    messages : {
	    	complianceProgress:{
				required:"合规进度不能为空！"
			},
			totalScore:{
				min:"综合得分不能低于65分",
			}			
	    },  
	    highlight: function (element) {
	    	//可以给未通过验证的元素加效果、闪烁等
	        $(element).closest('.form-group').addClass('has-error');
	    },
	    success: function (label) {
	        label.closest('.form-group').removeClass('has-error');
	        label.remove();
	    },
	    errorPlacement: function (error, element) {
	        element.parent('div').append(error);
	    },
	    submitHandler: function (form) {
	    	//console.info($(form).toFormJSON());
	    	$.ajax({
	        	data:$(form).toFormJSON(), //表单对象数据转json字符串提交
	        	contentType : 'application/json;charset=UTF-8', //设置请求头格式
	            dataType:'json',
	            type:'post',
	            url : form.action,
	            success: function (result) {
	            	if(result.isFlag){
	            		//编辑机构信息成功
						layer.alert(result.msg,{icon: 6,shift: 4,title:'提示'},function(index){
							layer.close(index);
							//debugger;
							$.switchPage("合作机构列表","rest/cim/cimorginfo/list"); //跳到机构列表页面
						});
					}else{
						//编辑机构信息失败
						layer.alert(result.msg,{icon: 5,shift: 6,title:'提示'});
					}
	            },
	            error:function(XmlHttpRequest,textStatus, errorThrown) {
	  			  	console.log(XmlHttpRequest.status);
	  			  	console.log(textStatus);
	  			  	showError(XmlHttpRequest.responseText+"更新机构风控信息失败！");
	  		  	}
	          });
	    }
	});
});