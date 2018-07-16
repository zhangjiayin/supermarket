

$(document).ready(function() {
   
     table = $('#addFeeCoupon-list').DataTable( {
    	dom: '<"J_search_toolbar">frtip',
    	ordering:false,
		searching:false,
		lengthChange:false,
		paging:true,
		select:true,
		serverSide:true,
		//deferLoading:false,
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
        	"sEmptyTable":     "数据为空",
        	"sLoadingRecords": "载入中...",
        	"sInfoThousands":  ",",
        	"oPaginate": {
        		"sFirst":    "首页",
        		"sPrevious": "上页",
        		"sNext":     "下页",
        		"sLast":     "末页"
        	}
        },
        "ajax": {
            "url": "/rest/act/actaddfeecoupon/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [          
            { "data": "source" ,"orderable": false},
            { "data": "type","orderable": false,"render": function ( data, type, row ) {
            	if(data == 1){
            		return "加拥券"
            	}else if(data == 2){
            		return "奖励券";
            	}
           }  },
            { "data": "validBeginTime","orderable": false},
            { "data": "validEndTime","orderable": false },
            { "data": "createTime" ,"orderable": false},
            { "data": "operator","orderable": false },
            { "data": "couponId","orderable": false,"render": function ( data, type, row ) {
            	//渲染 把数据源中的标题和url组成超链接
            	var result = '';
            	if(row.addFeeEditPermission == '1'){
            		var a = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="加拥券编辑" data-url="/rest/act/actaddfeecoupon/'+data+'/editPage">加拥券编辑</a>';
            		result += a;
            	}      
                return result;
           } }
			    
        ]
    } );
    //查询工具栏
    $(".J_search_toolbar").html($("#template-tools").html());
} );
