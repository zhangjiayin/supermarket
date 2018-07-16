var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];
var table;
$(document).ready(function() {
	
   var table = $('#dtable').DataTable({
    	//dom: "Bfrtip",
    	"dom" : '<"J_search_toolbar">frtip', //自定义工具类
        "processing": true,
        "serverSide": true,
        "searching": false, //关闭本地搜索
        "language": {
        	select: {
                rows: {
                    _: "已选择 %d 行",
                    1: "已选择 1 行"
                }
            },
        	"sProcessing":   "处理中...",
        	"sLengthMenu":   "显示 _MENU_ 项结果",
        	"sZeroRecords":  "没有匹配结果",
        	"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        	"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
        	"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        	"sInfoPostFix":  "",
        	"sSearch":       "机构名称搜索:",
        	"sUrl":          "",
        	"sEmptyTable":     "表中数据为空",
        	"sLoadingRecords": "载入中...",
        	"sInfoThousands":  ",",
        	"oPaginate": {
        		"sFirst":    "首页",
        		"sPrevious": "上页",
        		"sNext":     "下页",
        		"sLast":     "末页"
        	},
        	"oAria": {
        		"sSortAscending":  ": 以升序排列此列",
        		"sSortDescending": ": 以降序排列此列"
        	}
        },
        "ajax": {
            "url": "/rest/cim/cimproductunrecordinvest/list",
            "type": "POST",
            "data":function(d){
            	d.userName = $('.J_search_toolbar input[name=userName]').val(); //查询条件
            	d.userMobile = $('.J_search_toolbar input[name=userMobile]').val(); 
            	d.investTime = $('.J_search_toolbar input[name=investTime]').val(); 
            	d.uploadTitle = $('.J_search_toolbar input[name=uploadTitle]').val(); 
            	d.payStatus = $('#payStatusId').val();
//            	layer.alert(d.payStatus, {icon: 0});
            	return {'_dt_json':JSON.stringify(d)};//传递对象太多，json化
            	}
        },
        "order": [[ 9, 'desc' ]], //平台列表信息 按创建时间 降序排列
        "columns": [
            { "data": "id","orderable": false ,
            	"render":function (data,type,row) {
            		var result = '';
            		var a = '<input type="checkbox" id="checkboxId" name="tableId" value="' + $('<div/>').text(data).html() + '">';
            		if(row.feeStrategy==0){
            			return result;
            		}else if(row.payStatus == 1){
            			return result += a;
            		}else{
            			return result;
            		}
   				},},
			{ "data": "userName","orderable": false },
			{ "data": "userMobile","orderable": false },
			{ "data": "platfromName","orderable": false },
			{ "data": "productDeadLineValue","orderable": false },
			{ "data": "investAmt","orderable": false },
			{ "data": "feeStrategy" ,"orderable": false,"render": function ( data, type, row ) {
	           	   if(data=="1")
	           		   return "固定金额";
	           	   else if(data=="2")
	           		   return "固定比例";
	           	   else if(data=="3")
	           		   return "按期限固定比例";
	           	   else 
	           		return ""; 
	            }},
			{ "data": "feeRatio","orderable": false },//返现比例
			{ "data": "feeAmt","orderable": false },//返现金额
			{ "data": "investTime","orderable": false },//投资时间
			{ "data": "payStatus" ,"render": function ( data, type, row ) {
           	   if(data=="1")
           		   return "未发放";
           	   else if(data=="2")
           		   return "已发放";
           	   else if(data=="3")
           		   return "发放失败";
           	   else
           		   return "";
            }},
			{ "data": "uploadTitle","orderable": false },
			{ "data": "uploadTime","orderable": false },
			{ "data": "payTime","orderable": false },//发放返现时间
			{ "data": "uploadOperator","orderable": false },//操作人
			{ "data": "id" ,"orderable": false,"render": function ( data, type, row ) {
			  	var d = '<a href="javacript:void(0);" class="btn btn-default btn-sm J_pay_model" data-id="'+row.id+'">确认发放</a>&nbsp';
			  	var result = '';
  	            if(row.payStatus != '2' && row.feeStrategy != '0'){
  	            	result += d;
  	            }
			    return result;
			}},
        ],
        select: true
    });
   
	  
	   $("#dtable").on("click",".J_pay_model",function(){
	   	var dataId = $(this).attr("data-id");
	   	layer.confirm('确认发放返现？', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(index){
	   		if(index){
		   		$.ajax({
		            url : "/rest/cim/cimproductunrecordinvest/payById?dataId="+dataId,
		            type : 'GET',
		            success : function(data) {
		            	if(data.isFlag == false){
	 	 		    		layer.alert(data.msg, {icon: 0}); //失败
	 	 		    	}else{
	 	 		    		layer.msg(data.msg,{time: 2000,icon: 1}); //成功
	 	 		    		table.draw();
	 	 		    	}
		            }
		        });
	   		}
		});
	   });
    
  
    //包裹查询工具栏
    $(".J_search_toolbar").html($("#template-search").html());
    
    //查询
	$("button.J_search").click(function(e) {
		e.preventDefault();
		table.draw(); //重绘表格。执行比如添加、删除、改变排序、筛选或者分页这些操作时  会重新排序和过滤
	});

	//重置/取消
	$("#reset").click(function(){
			validator.resetForm();
			//clear error msg
			$('.form-group').removeClass('has-error');
	  });
	
	//监听modal的hidden 清空modal旧数据
	$("#recommendModal").on("hidden.bs.modal", function() {
		//console.info('推荐信息模态框关闭重置模态框..');
		validator.resetForm();
		$('.form-group').removeClass('has-error');
		$(this).removeData("bs.modal");
	});
	
	
	
	$('#investTime').daterangepicker({
		showDropdowns: true, //自定义可选择年、月
		singleDatePicker: true, //是否是单个时间选择器
		format: 'YYYY-MM-DD', //日期格式化
		startDate: moment().format('YYYY-MM-DD'), // 默认起始时间
		maxDate: moment().format('YYYY-MM-DD')	//可选最迟时间
	},
	function(start, end, label) { //回调
		$("#investTime").closest('.form-group').removeClass('has-error'); //删除验证错误样式
		$("#investTime-error").remove(); //移除jqueryValidate span错误提示标签
	   // alert('A date range was chosen: ' +start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
	  }
	);
	
	//判断表单的信息是否通过验证
	validator = $("#myForm").validate({
		//debug: true,//只验证不提交表单
	    //meta: "validate",//设置meta来封装验证规则
		//onkeyup:false, //是否在敲击键盘时验证
	    errorElement: 'span',
	    errorClass: 'help-block help-block-error',
	    focusInvalid: false, //当为false时，验证无效时，没有焦点响应
	    rules : {  
	    	homepageSort : {  
	    		required: true,
	    		checkOrgHomePageSort : true,
	    		number : true
            },  
            top : {  
            	//required: true,
            	checkOrgListSort : true,
            	number : true
            }
        },  
        messages : {  
        	homepageSort : {  
                required : "推荐排名不能为空！",
                number : "请输入合法的数字(含负数)！"
            },  
            top : {  
                //required : "列表排名不能为空！",
                number : "请输入合法的数字(含负数)！"
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
	        $("#recommendModal").modal("hide"); //隐藏模态框
	        $.ajax({
	        	//data:$(form).toFormJSON(), //表单对象数据转json字符串提交
	        	//contentType : 'application/json;charset=UTF-8', //设置请求头信息
	        	data:$(form).serialize(),
	        	dataType:'json',
	            type:'post',
	            url : '/rest/cim/cimorginfo/updateOrgRecommendInfo',
	            success: function (data) {
	                if (data.isFlag) {
	                    //保存成功  1.关闭弹出层，2.刷新表格数据
	                	//layer.msg(data.message,{time: 1000,icon: 0});
	                    showTips(data.msg);
	                    table.draw();//刷新表格数据
	                } else {
	                    showError(data.msg);
	                }
	            },
	            error:function(XmlHttpRequest,textStatus, errorThrown) {
	  			  	console.log(XmlHttpRequest.status);
	  			  	console.log(textStatus);
	  			  	showError(XmlHttpRequest.responseText+"推荐机构失败！");
	  		  	}
	          });
	    }
	});
	
	$('#example-select-all').on('click', function(){
	      var rows = table.rows({ 'search': 'applied' }).nodes();
	      $('input[type="checkbox"]', rows).prop('checked', this.checked);
	   });

	   $('#example tbody').on('change', 'input[type="checkbox"]', function(){
	      if(!this.checked){
	         var el = $('#example-select-all').get(0);
	         if(el && el.checked && ('indeterminate' in el)){
	            el.indeterminate = true;
	         }
	      }
	   });
	   
	   
	   $("body").on("click",'#payAuditButtonsss',function(e){	
		   if(!$("input[type='checkbox'][name='tableId']").is(':checked')){
				bootbox.alert("请选中相应的投资记录");
			} else {
				layer.confirm('确认批量返现？', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(index){
	          		var ids = $("input[type='checkbox'][name='tableId']:checked").serialize();
					 $.ajax({  
						  url: "rest/cim/cimproductunrecordinvest/payAudit",
						  type: 'POST',  
						  data: ids,
				          success: function (result) { 
				              if(result.isFlag == false){
			 	 		    		layer.alert(result.msg, {icon: 0}); 
			 	 		    	}else{
			 	 		    		layer.msg(result.msg,{time: 2000,icon: 1}); //成功
			 	 		    		$("#example-select-all").removeAttr("checked");
			 	 		    		 table.draw();
			 	 		    	}
				          },  
				          error:function(XmlHttpRequest,textStatus, errorThrown)
				    	  {
				    		  console.log(XmlHttpRequest.status);
				    		  console.log(textStatus);
				    		  showError(XmlHttpRequest.responseText);
				    	  } 
				     });
				});
			}
		});

    
});



