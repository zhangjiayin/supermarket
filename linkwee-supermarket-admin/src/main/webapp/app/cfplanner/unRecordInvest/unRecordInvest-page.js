$(document).ready(function() {
    table = $('#unRecordInvest-list').DataTable( {
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
            "url": "/rest/unRecordInvest/list",
            "type": "POST",
            "data":function(d){
            	d.investorsUserName = $('#investorsUserName').val();
            	d.investorsMobiel = $('#investorsMobiel').val();
                d.startTime = $('#startTime').val();
                d.endTime = $('#endTime').val();
            	d.status = $('#status').val();
            	d.columns = [];
                d.search = {};
            	return {'_dt_json':JSON.stringify(d)};
            }//传递对象太多，json化
        },
        "columns": [
            { "data": "name"},
            { "data": "mobile"},
            { "data": "platfromName"},
            { "data": "investAmt"},
            { "data": "investTime"},
            { "data": "deadLine"},
            { "data": "feeAmt"},
            { "data": "feeRate"},
            { "data": "img","render": function ( data, type, row ) {
            	if(data){
            	    var images = data.split(",");
            	    var result = '';
            	    for(var i in  images){
                        result += '<img src="'+images[i]+'" data-action="zoom"  height="100" />';
                    }
            		return result;
		    	}
		    	return '';
           }},
            { "data": "remark"},
            { "data": "time"},
            { "data": "shTime"},
            { "data": "shareStatus","render": function ( data, type, row ) {
                if(data == 1){
                    return '已同意晒单';
                }
                return '未同意晒单';
            }},
            { "data": "status","render": function ( data, type, row ) {
                if(data == 0){
                    return '未审核';
                }else if(data == 1){
                    return '审核通过';
                }else if(data == 2){
                    return '审核未通过';
                }
                return '';
            }},
            { "data": "thumbsUpTimes"},
            { "data": "id","render": function ( data, type, row ) {
                var a='';
            	if(row.status==0){
                    a = '<a href="#" class="btn btn-default btn-sm J_update" data-title="更新审核状态" data-url="/rest/unRecordInvest/updateStatusPage?id='+row.id+' "><i class="fa fa-edit"></i> 更新审核状态</a>';
                }else if(row.status==1){
                    a = '审核通过';
                }else if(row.status==2){
                    a = '审核未通过';
                }
            	return a;
           } }
        ]
    } );
    //查询工具栏
    $(".J_search_toolbar").html($("#template-tools").html());
    //查询
   $("button.J_search").click(function() {
    	table.draw(); //重绘表格。执行比如添加、删除、改变排序、筛选或者分页这些操作时  会重新排序和过滤
    });

    var $modal = $('#publicModal');
    $("#unRecordInvest-list").on("click",".J_update",function(event){
        var url = $(event.target).attr("data-url");
        $.ajax({
            url : url,
            type : 'GET',
            success : function(data) {
                $modal.find(".modal-footer").hide();
                $modal.find(".modal-title").html("更新审核状态");
                $modal.find(".modal-body").html(data);
                $modal.modal('show');
            }
        });
    });
} );
