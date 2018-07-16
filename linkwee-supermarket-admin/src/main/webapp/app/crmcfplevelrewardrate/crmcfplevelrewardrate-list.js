var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/cim/crmcfplevelrewardrate/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/cim/crmcfplevelrewardrate/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/cim/crmcfplevelrewardrate/save',
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
                label: "编号:",
                name: "id"
                ,type:"hidden"
            }, 
         {
                label: "职级:",
                name: "levelCode",
                type:  "select",
                options: [
                    { label: "见习", value: "TA" },
                    { label: "顾问",  value: "SM1" },
                    { label: "经理", value: "SM2" },
                    { label: "总监",  value: "SM3" }
                ],
            }, 
         {
                label: "基础佣金占比（%）:",
                name: "baseFeeRate"
            }, 
         {
                label: "推荐奖励占比（%）:",
                name: "recommendRate"
            }, 
         {
                label: "直接管理津贴占比（%）:",
                name: "childAllowanceRate"
            }, 
         {
                label: "团队管理津贴百占比（%）:",
                name: "teamAllowanceRate"
            }, 
        ]
    } );
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
    	if ( action == 'edit' ) {
    		return confirm("\t您修改的内容为将会牵扯到移动端APP上的页面文案修改和奖励/津贴金额的计算。\n\t请在修改前，务必与产品、运营等相关人员沟通，并获得最高领导批准的情况下才能更改，否则后果自负！\n\t如以上已明确无误，请点击下方确认键。",
	    		function(result){
	    			if(result){
	    				return true;
	    			}else{
	    				return false;
	    			}
	    		}
    		);
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
            "url": "/rest/cim/crmcfplevelrewardrate/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "id","orderable": true },
            { "data": "levelCode","orderable": false,
            	"render":function(data,type,row){          		
            		if(data=="TA"){
            			return "见习";
            		}else if(data == "SM1"){
            			return "顾问";
            		}else if(data == "SM2"){
            			return "经理";
            		}else if(data == "SM3"){
            			return "总监";
            		}
            	}
            },
            { "data": "baseFeeRate","orderable": false },
            { "data": "recommendRate","orderable": false },
            { "data": "childAllowanceRate","orderable": false,
            	"render":function(data,type,row){          		
            		if(data=="0"){
            			return "-";
            		}else{
            			return data;
            		}
            	}
            },
            { "data": "teamAllowanceRate","orderable": false,
            	"render":function(data,type,row){          		
            		if(data=="0"){
            			return "-";
            		}else{
            			return data;
            		}
            	}
            },
			    
        ],
        select: true,
        buttons: [
//            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
//            { extend: "remove", editor: editor }
        ]
    } );
    
} );
