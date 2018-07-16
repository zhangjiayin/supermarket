var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];
var table;
$(document).ready(function() {
	
   var table = $('#dtable').DataTable({
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
            "url": "/rest/cim/cimproductunrecordinvest/logslist",
            "type": "POST",
            "data":function(d){
            	d.investTime = $('.J_search_toolbar input[name=investTime]').val(); 
            	d.uploadTitle = $('.J_search_toolbar input[name=uploadTitle]').val(); 
            	return {'_dt_json':JSON.stringify(d)};//传递对象太多，json化
            	}
        },
        "order": [[ 2, 'desc' ]], //平台列表信息 按创建时间 降序排列
        "columns": [
            { "data": "uploadBatchNo","orderable": false },
			{ "data": "uploadTitle","orderable": false },
			{ "data": "uploadRemark","orderable": false },
			{ "data": "uploadTime","orderable": false },
			{ "data": "operator","orderable": false },//操作人
        ],
        select: true
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
    
});



