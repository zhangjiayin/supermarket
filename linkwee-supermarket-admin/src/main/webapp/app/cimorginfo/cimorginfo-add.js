$(function(){
	/**
	 * 初始化bootstrapSwitch插件
	 */
	$(".bootstrapSwitch").bootstrapSwitch();
	var $switch = $('body').find('.bootstrapSwitch');
    if ($switch) {
    	$switch.each(function(index, element) {
    		initBootStrapSwitch($(element).attr('id'),$(element).attr('data-name'));
      });
    }
    
	
	//console.info(moment().format('YYYY-MM-DD HH:mm:ss')); //获取系统当前时间
	/**
	 * 初始化日期插件
	 */
	$('#upTime').daterangepicker({
		showDropdowns: true, //自定义可选择年、月
		singleDatePicker: true, //是否是单个时间选择器
		format: 'YYYY-MM-DD', //日期格式化
		startDate: '2015-01-01', // 默认起始时间
		maxDate: moment().format('YYYY-MM-DD')	//可选最迟时间
	},
	function(start, end, label) { //回调
		$("#upTime").closest('.form-group').removeClass('has-error'); //删除验证错误样式
		$("#upTime-error").remove(); //移除jqueryValidate span错误提示标签
	   // alert('A date range was chosen: ' +start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
	  }
	);
	/**
	 * 判断表单的信息是否通过验证
	 */
	var validator =  $("#orgForm").validate({
		//debug: true, //只验证不提交表单
	    //meta: "validate",//设置meta来封装验证规则
		//onkeyup:false, //是否在敲击键盘时验证
		ignore: "", //验证隐藏域
	    errorElement: 'span',
	    errorClass: 'help-block help-block-error',
	    focusInvalid: true, //当为false时，验证无效时，没有焦点响应
	    rules : {
	    	orgNumber:{
	    		checkOrgExist:true  
	    	},
	    	//capital:"required", //单个条件验证
	    	capital:{ //多条件验证
	    		//digits:true, //必须输入整数 包括0
	    		required:true,
	    		number:true, //必须输入数字包含负数,小数
	    		min:1 //输入值不能小于 1
			},
			orgFeeRatio:{
				required:true,
				number:true, //必须输入数字包含负数,小数
				min:0
			},
			upTime:{
				//dateISO:true, //必须输入正确格式的日期（ISO），例如：2009-06-23 不验证日期合法性
				isDate:true,   //自定义日期验证 验证日期合法和格式2009-06-23
				required:true
			},
			diffFeeRatio:{
				number:true, //必须输入数字包含负数,小数
				min:0
			}
	    },  
	    messages : { 
	    	/*
	    	capital:{
	    		number:"请输入合法的数字",
	    		min:"输入值不能小于1"
			}
	    	*/
	    	orgFeeRatio:{
				required:"理财师佣金率不能为空！",
				number:"请输入合法的数字",
				min:"输入值不能小于{0}"	
			},
			upTime:{
				required:"机构上线时间不能为空！"
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
	        	contentType : 'application/json;charset=UTF-8', //设置请求头信息
	            dataType:'json',
	            type:'post',
	            url : form.action,
	            success: function (result) {
	            	if(result.isFlag){
	            		//新增成功
						layer.alert(result.msg,{icon: 6,shift: 4,title:'提示'},function(index){
							layer.close(index);
							$.switchPage("合作机构列表","rest/cim/cimorginfo/list"); //跳到机构列表页面
						});
					}else{
						//新增失败
						layer.alert(result.msg,{icon: 5,shift: 6,title:'提示'});
						//showError(result.msg);
					}
	            },
	            error:function(XmlHttpRequest,textStatus, errorThrown) {
	  			  	console.log(XmlHttpRequest.status);
	  			  	console.log(textStatus);
	  			  	showError(XmlHttpRequest.responseText+"新增机构失败！");
	  		  	}
	          });
	    }
	});
	
	
	
	
});
	
	/**
	 * 初始化BootStrapSwitch插件
	 * @param id
	 * @param name
	 */
	function initBootStrapSwitch(id,name){
		$("#"+id).on('switch-change', function (e, data) {
			var $el = $(data.el); //获取复选框
			if(data.value == true){
				//$el.val(1); //是
				$('input[name="'+name+'"]').val(1);
			}else{
				//$el.val(0); //不是
				$('input[name="'+name+'"]').val(0);
			}
			//console.log($el[0]);
			
		});
	}

	/**
	 * 校验此机构是否存在
	 */
	jQuery.validator.addMethod("checkOrgExist",function(value,element){
	    var orgNumber = value;
	   // var res = false;
	    $.ajax({
	        type:"GET",
	        //async:false, 
	        url:"/rest/cim/cimorginfo/checkOrgExist",
	        data:"orgNumber="+orgNumber,
	        success:function(response){
	        	//console.info(response.isFlag);
	            if(response.isFlag){ //此机构已经存在
	                res = false;
	            }else{
	                res = true;
	            }
	        }
	    });
	    return res;
	},"此机构编码已存在");

	/**
	 * 验证: isDate:true
	 * 可以验证 2011-07-07和2011/07/07两种短横线和斜杠格式的
	 */	
	jQuery.validator.addMethod("isDate", function(value, element){
		var ereg = /^(\d{1,4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})$/;
		var r = value.match(ereg);
		if (r == null) {
			return false;
		}
		var d = new Date(r[1], r[3] - 1, r[5]);
		var result = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[5]);
		return this.optional(element) || (result);
	}, "请输入正确的日期");
		
	/*	
	$("p").on("click", function(){
		alert( $(this).text() );
		});	
	*/
	
	/**
	 * 根据机构名称生成机构编码(汉字→全拼大写转换)
	 */
	function builderOrgNumber() {
		if($("input[name='orgName']").val()){ //如果机构名称不为空 则进行汉字→全拼大写转换
			 $.ajax({
			        type:"GET",
			        //async:false, 
			        dataType:'json',
			        url:"/rest/cim/cimorginfo/orgNameConverterToSpell",
			        data:"orgName="+$("input[name='orgName']").val(),
			        success:function(pinyin){
			        	if(pinyin.indexOf(",") == -1){ //没有多音字
			        		$("input[name='orgNumber']").val(pinyin.toUpperCase());
			        		$("input[name='orgNumber']").focus(); //定位焦点 防止jquery验证失效
			        	}else{ //碰到多音字
			        		$("input[name='orgNumber']").val(""); //首先清空机构编码
			        		var pinyins = pinyin.split(","); //获取逗号分隔的字符串数组
			        		var htmlContent = builderHtml(pinyins,$("input[name='orgName']").val()); //生成html
				        	//出现多音字
				        	layer.open({
				        			  type: 1, //0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
				        			  title: "自动生成机构编码-多音字选择", //标题
				        			  btn: ['确定', '取消'],
				        			  closeBtn: 2, //可通过配置1和2来展示，如果不显示，则closeBtn: 0
				        			  skin: "layui-layer-lan",  //layui-layer-lan  layui-layer-molv
				        			  area: ['600px', '360px'], //在默认状态下，layer是宽高都自适应的 默认：'auto' 
				        			  offset: '40px', //弹出层只定义top坐标，水平保持居中
				        			  shadeClose: false, //点击遮罩不能关闭
				        			  content: htmlContent,
				        			  
				        			  //按钮回调
				        			  yes: function(index, layero){ //该回调携带两个参数，分别为当前层索引、当前层DOM对象
				        				  if(!$('input[name="pinYinRdo"]').is(':checked')) { //判断单选框是否被选中
				        					  layer.alert('亲，请选中一个哦！！！', {
				        						    icon: 5,
				        						    title: "提示", //标题
				        						    skin: 'layui-layer-lan',
				        						    offset: '120px',
				        						    closeBtn: 0			        						    
				        						  });
				        					  return;
				        					}
				        				  	$("input[name='orgNumber']").val($('input[name="pinYinRdo"]:checked').val().toUpperCase()); //机构编码转换为大写并赋值
				        				  	$("input[name='orgNumber']").focus(); //定位焦点 防止jquery验证失效
				        				  	layer.close(index); //关闭模态框,如果设定了yes回调,需进行手工关闭
				        				  	//console.info(validator.form()); //false
				        				    //validator.resetForm();
				        				  },
				        			  btn2: function(index, layero){
				        				    //按钮【取消】的回调
				        				  	//layer.close(index);
				        				  },
				        			  cancel: function(){ 
				        					//右上角关闭回调
				        				  }
				        				  
				        			  });
			        		
			        		}
			        	
			        },
		            error:function(XmlHttpRequest,textStatus, errorThrown) {
		  			  	console.log(XmlHttpRequest.status);
		  			  	console.log(textStatus);
		  			  	showError(XmlHttpRequest.responseText+"汉字→全拼大写转换失败！");
		  		  	}
			    });
		}
	}
	
	/**
	 * 生成机构名称多音字全拼HTML
	 * @param pinyins
	 * @param orgName
	 * @returns
	 */
	function builderHtml(pinyins,orgName){
		 var htmlContent = [];
		 htmlContent.push('<font color="red">*&nbsp;呃，碰到多音字系统懵逼了(⊙v⊙)<br />*&nbsp;亲，考验您小学语文成绩的时候到了，请谨慎选择。 举个栗子：E周行 = Ezhouxing / Ezhouhang</font><div style="padding: 20px 80px;">');
		 htmlContent.push('<p style="font-size: 18px;">新机构：<b style="color: blue;">'+orgName+'</b>&nbsp;多音字全拼有以下<b style="color: blue;">'+pinyins.length+'个</b>结果：</p>');
		
		 htmlContent.push('<div class="row" style="margin: 10px">'); //新建一行
         for (var i = 0;i < pinyins.length;i+=2){
        	 // pinyins[i] = pinyins[i].toUpperCase(); //大小写转换
        	 // 最低有2条数据
        	 htmlContent.push('<div class="row" style="margin: 10px">');
        	 
        	 htmlContent.push('<div class="col-md-12">');
        	 
        	 htmlContent.push('<label class="radio-inline col-md-6">'+ (i+1)+"：");
        	 htmlContent.push('<input type="radio" name="pinYinRdo" value="' + pinyins[i] + '" />&nbsp;&nbsp;' + pinyins[i]);
        	 htmlContent.push('</label>');
        	 
        	if(i+1 < pinyins.length){ //判断是否有第二列
        		 htmlContent.push('<label class="radio-inline col-md-5">'+ (i+2)+"：");
            	 htmlContent.push('<input type="radio" name="pinYinRdo" value="' + pinyins[i+1] + '" />&nbsp;&nbsp;' + pinyins[i+1]);
            	 htmlContent.push('</label>');
        	 }
        	
        	 htmlContent.push('</div>');
        	 
        	 htmlContent.push('</div>');
         }
         htmlContent.push('</div>'); //新建一行
         
         htmlContent.push('</div>');
     
         return htmlContent.join(''); //指定使用的分隔符
		
	}
	
	
	/**
	 * 删除团队成员
	 */
	function deleteTeam(delBtn){
		//询问框
		layer.confirm('确定删除此成员？', {btn: ['确定','取消'],title:'提示',shift: 6,icon: 3}, function(){
		  	layer.msg('删除成功！', {icon: 1,time:500});
		  	 //layer.load(0, {time: 1000}); //又换了种风格，并且设定最长等待10秒 
		  	//layer.close(index); 
			var rowid = $(delBtn).attr("data-teamrowid"); //获取团队成员的行id
			$("#"+rowid).remove(); //删除此行
		});
		
	}
	
	
	
	/**
	 * 添加团队成员
	 */
	function addTeam(addBtn){
		var team_index = parseInt($(addBtn).attr("data-index")); //指定团队成员下标 默认：NaN 
		if(team_index >= 3){
			team_index = team_index + 1;
		}else{
			team_index = 3; //成员下标默认从3开始
		}
		$(addBtn).attr("data-index",team_index); //设置团队成员下标
		
		$.ajax({
		        //async :false,
		        url: '/rest/cim/cimorginfo/toTeamTemplate?teamindex='+team_index,
		        type: 'get',
		        dataType: 'html',
		        success:function(html){
					//截取html
					//var result =  html.substring(html.indexOf('<div class="form-group">'),html.lastIndexOf("</div>")+6);
		        	$("#teamList").append(html); //新增团队成员
		        	$("#templatejs").remove(); //删除初始化头像时的js标签
		        	//$("#team-3").focus(); //div获取焦点时 需设置tabindex="0"
		        }
		    });
		
	}
	
	
	
