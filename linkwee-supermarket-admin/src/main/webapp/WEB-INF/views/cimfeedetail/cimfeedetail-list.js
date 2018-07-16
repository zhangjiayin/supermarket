var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/cim/cimfeedetail/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/cim/cimfeedetail/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/cim/cimfeedetail/save',
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
                label: ":",
                name: "id"
                ,type:"hidden"
            }, 
         {
                label: "佣金计算编号:",
                name: "bizId"
            }, 
         {
                label: "投资订单编号:",
                name: "billId"
            }, 
         {
                label: "投资用户编号:",
                name: "investorId"
            }, 
         {
                label: "获利理财师编号:",
                name: "profitCfplannerId"
            }, 
         {
                label: "原始理财师编号:",
                name: "originCfplannerId"
            }, 
         {
                label: "产品所属机构编号:",
                name: "productOrgId"
            }, 
         {
                label: "产品编号:",
                name: "productId"
            }, 
         {
                label: "购买产品金额:",
                name: "productAmount"
            }, 
         {
                label: "年化金额:",
                name: "yearpurAmount"
            }, 
         {
                label: "描述:",
                name: "remark"
            }, 
         {
                label: "佣金费率:",
                name: "feeRate"
            }, 
         {
                label: "佣金:",
                name: "feeAmount"
            }, 
         {
                label: "佣金类型：1001=佣金|1002=推荐津贴|1003=新人津贴|1004=理财师职位津贴|1005=合伙人职位津贴|1006=季度奖励|1007=年度分红|1008=活动奖励|1009=销售奖励:",
                name: "feeType"
            }, 
         {
                label: "结算状态：0=未结算|1=结算中|2=结算成功|3=结算失败:",
                name: "balanceStatus"
            }, 
         {
                label: "结算单号:",
                name: "balanceNumber"
            }, 
         {
                label: "创建时间:",
                name: "createTime"
            }, 
         {
                label: "更新时间:",
                name: "updateTime"
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
    var shiro_admin = "disabled=true";
    if($('#shiro_admin')){
    	shiro_admin = "";
    }
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
            "url": "/rest/cim/cimfeedetail/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "id","orderable": false },
            { "data": "bizId","orderable": false },
            { "data": "billId","orderable": false },
            { "data": "investorId","orderable": false },
            { "data": "profitCfplannerId","orderable": false },
            { "data": "originCfplannerId","orderable": false },
            { "data": "productOrgId","orderable": false },
            { "data": "productId","orderable": false },
            { "data": "productAmount","orderable": false },
            { "data": "yearpurAmount","orderable": false },
            { "data": "remark","orderable": false },
            { "data": "feeRate","orderable": false },
            { "data": "feeAmount","orderable": false },
            { "data": "feeType","orderable": false },
            { "data": "balanceStatus","orderable": false },
            { "data": "balanceNumber","orderable": false },
            { "data": "createTime","orderable": false },
            { "data": "updateTime","orderable": false },
			    
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
    
} );
