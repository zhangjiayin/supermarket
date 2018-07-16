$(document).ready(function() {

    table = $('#personaddfeeticket-list').DataTable( {
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
            "url": "/rest/cim/actpersonaddfeeticket/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "name" ,"orderable": false},
            { "data": "type","orderable": false },
            { "data": "rate","orderable": false},
            { "data": "createTime" ,"orderable": false},
            { "data": "operator","orderable": false },
            { "data": "ticketId","orderable": false,"render": function ( data, type, row ) {
                    //渲染 把数据源中的标题和url组成超链接
                    var result = '';
                    if(row.addfeeTicketEditPermission == '1'){
                        var a = '<a href="#" class="btn btn-default btn-sm ui-redirect" data-title="加拥券编辑" data-url="/rest/cim/actpersonaddfeeticket/'+data+'/editPage">加拥券编辑</a>&nbsp;&nbsp;';
                        result += a;
                    }
                    if(row.addfeeTicketSendPermission == '1'){
                        var b = '<a href="javascript:;" class="btn btn-default btn-sm J_send_addfeeticket" data-ticketId="'+data+'">加拥券发放</a>';
                        result += b;
                    }
                    return result;
                } }

        ]
    } );
    //查询工具栏
    $(".J_search_toolbar").html($("#template-tools").html());
    var $modal = $('#publicModal');
    $("#personaddfeeticket-list").on("click",".J_send_addfeeticket",function(){
        $.ajax({
            url : "/rest/cim/actpersonaddfeeticket/sendAddfeeTicketPage?ticketId="+$(event.target).attr("data-ticketId"),
            type : 'GET',
            success : function(data) {
                $modal.find(".modal-footer").hide();
                $modal.find(".modal-title").html("个人加拥券发放");
                $modal.find(".modal-body").html(data);
                $modal.modal('show');
            }
        });
    });
} );