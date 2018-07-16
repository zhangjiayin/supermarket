
$(document).ready(function() {
    
    var table = $('#addVoucher-list').DataTable( {
    	dom: '<"J_search_toolbar">frtip',
        "processing": true,
        "serverSide": true,
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
        	"sSearch":       "注册手机号码:",
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
            "url": "/rest/acc/actjobgradevoucher/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "mobile","orderable": false },
            { "data": "activityAttr","orderable": false },
            { "data": "jobGrade","orderable": false },
            { "data": "status","orderable": false,"render": function ( data, type, row ) {
            	if(data == 1){
            		return "未使用"
            	}else if(data == 2){
            		return "使用中";
            	}else if(data == 3){
            		return "已过期";
            	}else if(data == 4){
            		return "已失效";
            	}
           }  },
            { "data": "useTime","orderable": false },
            { "data": "expiresTime","orderable": false },
            { "data": "createTime","orderable": false },
            { "data": "operator","orderable": false },
			    
        ]
    } );
    $(".J_search_toolbar").html($("#template-tools").html());
} );
