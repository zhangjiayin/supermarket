

$(document).ready(function() {
   
     table = $('#redpacket-list').DataTable( {
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
        	"sSearch":     "注册手机号:",
        	"oPaginate": {
        		"sFirst":    "首页",
        		"sPrevious": "上页",
        		"sNext":     "下页",
        		"sLast":     "末页"
        	}
        },
        "ajax": {
            "url": "/rest/redpacket/list",
            "type": "POST",
            "data":function(d){
            	d.orgName = $('.J_search_toolbar input[name=redpacketName]').val(); //查询条件
            	return {'_dt_json':JSON.stringify(d)};
            	
            }//传递对象太多，json化
        },
        "columns": [          
            { "data": "name" ,"orderable": false},
            { "data": "money","orderable": false },
            { "data": "sendCount","orderable": false},
            { "data": "sendTime","orderable": false },
            { "data": "sendNum" ,"orderable": false},
            { "data": "minExpiresTime" ,"orderable": false},
            { "data": "maxExpiresTime","orderable": false},
            { "data": "createTime" ,"orderable": false},
            { "data": "status" ,"orderable": false},
            { "data": "operator","orderable": false },
            { "data": "redpacketId","orderable": false,"render": function ( data, type, row ) {
            	//渲染 把数据源中的标题和url组成超链接
            	var result = '';
            	if(row.redpacketEditPermission == '1'){
            		var a = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="红包编辑" data-url="/rest/redpacket/'+data+'/editPage">红包789编辑</a>&nbsp;&nbsp;';
            		result += a;
            	}
            	if(row.redpacketSendPermission == '1'){
            		var b = '<a href="javascript:;" class="btn btn-default btn-sm J_send_redpacket" data-redpacektId="'+data+'">红包发放</a>';
            		result += b;
            	}      
                return result;
           } }
			    
        ]
    } );
    //查询工具栏
    $(".J_search_toolbar").html($("#template-tools").html());
    
    //查询
	$("button.J_search").click(function(e) {
		e.preventDefault();
		table.draw(); //重绘表格。执行比如添加、删除、改变排序、筛选或者分页这些操作时  会重新排序和过滤
	});
    var $modal = $('#publicModal');
    $("#redpacket-list").on("click",".J_send_redpacket",function(){
		$.ajax({
			url : "/rest/redpacket/sendRedpacketPage?redpacketId="+$(event.target).attr("data-redpacektId"),
			type : 'GET',
			success : function(data) {
				$modal.find(".modal-footer").hide();
				$modal.find(".modal-title").html("发放红包");
				$modal.find(".modal-body").html(data);
				$modal.modal('show');
			}
		});
    });
} );
