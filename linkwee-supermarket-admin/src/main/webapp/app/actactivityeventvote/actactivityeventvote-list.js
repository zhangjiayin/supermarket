var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/act/actactivityeventvote/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/act/actactivityeventvote/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/act/actactivityeventvote/save',
                 data:function(d){return {'rows':JSON.stringify(d)};}
            }
        },
        table: "#dtable",
        idSrc:  'id',
        i18n: {
            "create": {
                "button": "新增",
                "title":  "创建新的实体",
                "submit": "确定"
            },
            "edit": {
                "button": "编辑",
                "title":  "编辑实体",
                "submit": "确定"
            },
            "remove": {
                "button": "删除",
                "title":  "删除",
                "submit": "确定",
                "confirm": {
                    "_": "确定要删除选择的 %d 行数据吗?",
                    "1": "确定要删除选择的 1 行数据吗?"
                }
            },
         
            "error": {
                "system": "发生系统错误 (More information)"
            },
         
            "multi": {
                "title": "多个值",
                "info": "选择的内容中当前输入框包含不同的值. 把他们设置成相同的值, 点击这里, 否则它们仍然保留不同的值.",
                "restore": "取消改动"
            },
         
            "datetime": {
                "previous": '前',
                "next":     '后',
                "months":   [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                "weekdays": [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
                "amPm":     [ '上午', '下午' ],
                "unknown":  '-'
            }
        },
        fields: [
         {
                label: "自增长主键:",
                name: "id"
                ,type:"hidden"
            }, 
         {
                label: "投票id:",
                name: "voteId"
            }, 
         {
                label: "竞猜id:",
                name: "guessId"
            }, 
         {
                label: "用户编码:",
                name: "userId"
            }, 
         {
                label: "手机号码:",
                name: "mobile"
            }, 
         {
                label: "支持投票方:",
                name: "supportVote"
            }, 
         {
                label: "消耗的投票次数:",
                name: "consumeTimes"
            }, 
         {
                label: "是否虚拟数据 1：是 0：否:",
                name: "isVirtual"
            }, 
         {
                label: "票数:",
                name: "voteNumber"
            }, 
         {
                label: "投票时间:",
                name: "voteTime"
            }, 
         {
                label: "奖励:",
                name: "reward"
            }, 
         {
                label: "是否发放（0：未发放，1：已发放）:",
                name: "issued"
            }, 
         {
                label: "发放者:",
                name: "sendOperator"
            }, 
         {
                label: "扩展字段1:",
                name: "extends1"
            }, 
         {
                label: "扩展字段2:",
                name: "extends2"
            }, 
         {
                label: "扩展字段3:",
                name: "extends3"
            }, 
        ]
    } );
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        if ( action !== 'remove' ) {
            var name = editor.field( 'name' );
 
            // Only validate user input values - different values indicate that
            // the end user has not entered a value
            if ( ! name.isMultiValue() ) {
                if ( ! name.val() ) {
                	name.error( 'A activity name must be given' );
                }
                 
                if ( name.val().length <= 2 ) {
                	name.error( 'The activity name length must be more that 2 characters' );
                }
            }
 
            // ... additional validation rules
 
            // If any error was reported, cancel the submission so it can be corrected
            if ( this.inError() ) {
                return false;
            }
        }
    } );

    var table = $('#dtable').DataTable( {
    	dom: "Bfrtip",
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
        	"sSearch":       "搜索:",
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
            "url": "/rest/act/actactivityeventvote/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "id","orderable": false },
            { "data": "voteId","orderable": false },
            { "data": "guessId","orderable": false },
            { "data": "userId","orderable": false },
            { "data": "mobile","orderable": false },
            { "data": "supportVote","orderable": false },
            { "data": "consumeTimes","orderable": false },
            { "data": "isVirtual","orderable": false },
            { "data": "voteNumber","orderable": false },
            { "data": "voteTime","orderable": false },
            { "data": "reward","orderable": false },
            { "data": "issued","orderable": false },
            { "data": "sendOperator","orderable": false },
            { "data": "extends1","orderable": false },
            { "data": "extends2","orderable": false },
            { "data": "extends3","orderable": false },
			    
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
    
} );
