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
            "url": "/rest/cim/cimorginfoa/list",
            "type": "POST",
            "data":function(d){
            	d.orgName = $('.J_search_toolbar input[name=orgname]').val(); //查询条件
            	return {'_dt_json':JSON.stringify(d)};//传递对象太多，json化
            	}
        },
        "order": [[ 3, 'desc' ]], //平台列表信息 按创建时间 降序排列
        "columns": [
            { "data": "orgNumber","orderable": false },
            { "data": "name","orderable": false },
            { "data": "grade" ,"render": function ( data, type, row ) {//1:B,2:BB,3:BBB,4:A,5:AA,6:AAA'
           	   if(data=="1")
           		   return "B";
           	   else if(data=="2")
           		   return "BB";
	           else if(data=="3")
	        	   return "BBB";
	           else if(data=="4")
	        	   return "A";
	           else if(data=="5")
	        	   return "AA";
	           else if(data=="6")
	           	   return "AAA";
	           else
	           	   return "未定义";
            }},
		   { "data": "status" ,"render": function ( data, type, row ) {
			   if(data=="1")
				   return "合作中";
			   else if(data=="0")
				   return "合作结束";
			   else
				   return "待上线";
		  }},
		  { "data": "orgGrayStatus" ,"render": function ( data, type, row ) {
			   if(data=="1")
				   return "开启";
			   else if(data=="0")
				   return "关闭";
			   else
				   return null;
		  }},
		  { "data": "top","orderable": true },
		  { "data": "createTime","orderable": false },
		  { "data": "id" ,"orderable": false,"render": function ( data, type, row ) {
				//渲染 把数据源中的标题和url组成超链接
                var a = '<a href="javacript:void(0);" class="btn btn-default btn-sm ui-redirect" data-title="返现策略" data-url="/rest/cim/cimorginfoa/toOrgFeeStrategy?orgNumber='+row.orgNumber+'">返现策略</a>&nbsp';
				var e = '<a href="javacript:void(0);" class="btn btn-sm btn-default btn-icon ui-redirect" data-title="机构编辑" data-url="/rest/cim/cimorginfoa/toEdit?orgNumber='+row.orgNumber+'"><i class="fa fa-edit"></i>编辑</a>';
				var result = '';
				if(row.detailEditPermission == '1'){
                      result += a;
				}
				if(row.detailEditPermission == '1'){
					  result += e;
				}
				return result;
		   }}
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

});



