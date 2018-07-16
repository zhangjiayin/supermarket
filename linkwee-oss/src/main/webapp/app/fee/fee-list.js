$(function() {
	// 电话号码验证    
    jQuery.validator.addMethod("isYearMonth", function(value, element) {    
      var yearMonth = /^[1-9]{1}[0-9]{3}((0[1-9]{1})|(1[0-2]{1}))$/;    
      return this.optional(element) || (yearMonth.test(value));    
    }, "请正确填写年月");
	
	 $("#importData").validate({
        ignore: "",
        focusInvalid:false,
        errorElement: 'span',
        errorClass: 'help-block help-block-error',
        errorPlacement:function (error,element) {
            element.parent().addClass("has-error");
            error.appendTo(element.parent());
        },
        success:function (element) {
            element.closest('.form-group').removeClass('has-error');
            element.remove();
        },
        rules:{
            file:{
                required:true
            }
        },
        messages:{
        	file:{
                required:"上传文件不能为空"
            }
        },
        submitHandler:function () {
           return false;
        }
    });
	
	$("#statRet").validate({
        ignore: "",
        focusInvalid:false,
        errorElement: 'span',
        errorClass: 'help-block help-block-error',
        errorPlacement:function (error,element) {
            element.parent().addClass("has-error");
            error.appendTo(element.parent());
        },
        success:function (element) {
            element.closest('.form-group').removeClass('has-error');
            element.remove();
        },
        rules:{
        	month:{
                required:true,
                isYearMonth:true
            }
        },
        messages:{
        	month:{
                required:"年月不能为空",
                isYearMonth:"请填写正确的6位年月"
            }
        },
        submitHandler:function () {
           return false;
        }
    });
	
	$("#feePay").validate({
        ignore: "",
        focusInvalid:false,
        errorElement: 'span',
        errorClass: 'help-block help-block-error',
        errorPlacement:function (error,element) {
            element.parent().addClass("has-error");
            error.appendTo(element.parent());
        },
        success:function (element) {
            element.closest('.form-group').removeClass('has-error');
            element.remove();
        },
        rules:{
        	month:{
                required:true,
                isYearMonth:true
            }
        },
        messages:{
        	month:{
                required:"年月不能为空",
                isYearMonth:"请填写正确的6位年月"
            }
        },
        submitHandler:function () {
           return false;
        }
    });
	
	
	$("#importData").submit(function(){
		if($("#importData").validate().valid()){
			$(this).ajaxSubmit({
				url : 'rest/feepay/dataimport',
				type : "post",
				dataType : 'json',
				success : function(result) {
					if (result.isFlag) {
						showTips(result.msg);
					} else {
						showTips(result.msg);
					}
					$('#importDataModal').modal('hide');
					feePayTable.draw();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#importDataModal').modal('hide');
					showTips('服务异常！');
				}
			});
		}
		return false;
	});	
	
	$("#statRet").submit(function(){ 
		if($("#statRet").validate().valid()){
			$(this).ajaxSubmit({
				url : 'rest/feepay/statusreset',
				type : "post",
				dataType : 'json',
				success : function(result) {
					if (result.isFlag) {
						showTips(result.msg);
					} else {
						showTips(result.msg);
					}
					$('#statRetModal').modal('hide');
					feePayTable.draw();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#statRetModal').modal('hide');
					showTips('服务异常！');
				}
			});
		}	
		return false;
	});
	
	$("#feePay").submit(function(){ 
		if($("#feePay").validate().valid()){
			$(this).ajaxSubmit({
				url : 'rest/feepay/pay',
				type : "post",
				dataType : 'json',
				success : function(result) {
					if (result.isFlag) {
						showTips(result.msg);
					} else {
						showTips(result.msg);
					}
					$('#feePayModal').modal('hide');
					feePayTable.draw();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#feePayModal').modal('hide');
					showTips('服务异常！');
				}
			});
		}	
		return false;
	});
});




