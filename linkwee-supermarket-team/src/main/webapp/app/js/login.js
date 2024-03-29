var Login = function() {

    var handleLogin = function() {
        $('.login-form').validate({
            errorElement : 'span', // default input error message container
            errorClass : 'help-block', // default input error message class
            focusInvalid : false, // do not focus the last invalid input
            rules : {
                orgAccount : {
                    required : true
                },
                orgPassword : {
                    required : true
                },
                remember : {
                    required : false
                }
            },

            messages : {
                loginName : {
                    required : "用户名不能为空."
                },
                orgPassword : {
                    required : "密码不能为空."
                }
            },

            invalidHandler : function(event, validator) { // display error
                // alert on form
                // submit
                $('.alert-danger', $('.login-form')).show();
            },

            highlight : function(element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set
                // error
                // class
                // to
                // the
                // control
                // group
            },

            success : function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement : function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler : function(form) {
                var passwordInput = $('[name="orgPassword"]');
                passwordInput.val(sha256_digest(passwordInput.val()));
                form.submit();
            }
        });

        $('.login-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                    $('.login-form').submit();
                }
                return false;
            }
        });
        
      //招聘广告
        console.log("是否对互联网金融感兴趣呢?来加入我们吧，老司机带你飞。");
        console.log("请将简历发送至 %ccaiwei4@tophlc.com （标题格式：“姓名-应聘XX职位-来自console”）","color:red;");
    }

    var handleForgetPassword = function() {
        $('.forget-form').validate({
            errorElement : 'span', // default input error message container
            errorClass : 'help-block', // default input error message class
            focusInvalid : false, // do not focus the last invalid input
            ignore : "",
            rules : {
                email : {
                    required : true,
                    email : true
                }
            },

            messages : {
                email : {
                    required : "Email is required."
                }
            },

            invalidHandler : function(event, validator) { // display error
                // alert on form
                // submit

            },

            highlight : function(element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set
                // error
                // class
                // to
                // the
                // control
                // group
            },

            success : function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement : function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler : function(form) {
                form.submit();
            }
        });

        $('.forget-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.forget-form').validate().form()) {
                    $('.forget-form').submit();
                }
                return false;
            }
        });

        jQuery('#forget-password').click(function() {
            jQuery('.login-form').hide();
            jQuery('.forget-form').show();
        });

        jQuery('#back-btn').click(function() {
            jQuery('.login-form').show();
            jQuery('.forget-form').hide();
        });

    }

    var handleRegister = function() {

        function format(state) {
            if (!state.id)
                return state.text; // optgroup
            return "<img class='flag' src='assets/img/flags/"
                    + state.id.toLowerCase() + ".png'/>&nbsp;&nbsp;"
                    + state.text;
        }

        $("#select2_sample4")
                .select2(
                        {
                            placeholder : '<i class="fa fa-map-marker"></i>&nbsp;Select a Country',
                            allowClear : true,
                            formatResult : format,
                            formatSelection : format,
                            escapeMarkup : function(m) {
                                return m;
                            }
                        });

        $('#select2_sample4').change(function() {
            $('.register-form').validate().element($(this)); // revalidate
            // the chosen
            // dropdown
            // value and
            // show error or
            // success
            // message for
            // the input
        });

        $('.register-form').validate({
            errorElement : 'span', // default input error message container
            errorClass : 'help-block', // default input error message class
            focusInvalid : false, // do not focus the last invalid input
            ignore : "",
            rules : {

                fullname : {
                    required : true
                },
                email : {
                    required : true,
                    email : true
                },
                address : {
                    required : true
                },
                city : {
                    required : true
                },
                country : {
                    required : true
                },

                orgAccount : {
                    required : true
                },
                password : {
                    required : true
                },
                rpassword : {
                    equalTo : "#register_password"
                },

                tnc : {
                    required : true
                }
            },

            messages : { // custom messages for radio buttons and checkboxes
                tnc : {
                    required : "Please accept TNC first."
                }
            },

            invalidHandler : function(event, validator) { // display error
                // alert on form
                // submit

            },

            highlight : function(element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set
                // error
                // class
                // to
                // the
                // control
                // group
            },

            success : function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement : function(error, element) {
                if (element.attr("name") == "tnc") { // insert checkbox
                    // errors after the
                    // container
                    error.insertAfter($('#register_tnc_error'));
                } else if (element.closest('.input-icon').size() === 1) {
                    error.insertAfter(element.closest('.input-icon'));
                } else {
                    error.insertAfter(element);
                }
            },

            submitHandler : function(form) {
                form.submit();
            }
        });

        $('.register-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.register-form').validate().form()) {
                    $('.register-form').submit();
                }
                return false;
            }
        });

        jQuery('#register-btn').click(function() {
            jQuery('.login-form').hide();
            jQuery('.register-form').show();
        });

        jQuery('#register-back-btn').click(function() {
            jQuery('.login-form').show();
            jQuery('.register-form').hide();
        });
    }

    return {
        // main function to initiate the module
        init : function() {
        	//让登录界面成为顶层界面,self==top在手机浏览器不起作用
        	if(window.location.href.indexOf('/page/login')==-1
        			&&window.location.href.indexOf('/auth/login')==-1
        			&&window.location.href.indexOf('/auth/logout')==-1){
        		bootbox.alert('会话已过期，您需要重新登录！',function(){
        			location.href = '/rest/page/login';
        		});
			}
            handleLogin();
            handleForgetPassword();
            handleRegister();

            /*$.backstretch([ "assets/img/bg/1.jpg", "assets/img/bg/2.jpg",
                    "assets/img/bg/3.jpg", "assets/img/bg/4.jpg" ], {
                fade : 1000,
                duration : 8000
            });*/
        }

    };

}();