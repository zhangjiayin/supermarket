var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];
var ux=null;



$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/cms/msg/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/cms/msg/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/cms/msg/save',
                 data:function(d){return {'rows':JSON.stringify(d)};}
            }
        },


        table: "#dtable",
        idSrc:  'msgId',
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
                label: "序号:",
                name: "msgId"
                ,type:"hidden"
            }, 
         {
                label: "消息内容:",
                name: "content"
            }, 
         {
                label: "链接:",
                name: "link"
            }, 
         {
                label: "状态:",
                name: "status",
                type:  "select",
                options: [
                    { label: "发布", value: 0 },
                    { label: "删除",  value: 1 }
                ],
                "default": 0
            }, 
            {
                label: "应用类别:",
                name: "appType",
                type:  "select",
                options: [
                    { label: "理财师", value: 1 },
                    { label: "投资者",  value: 2 }
                ],
                "default": 1
            }, 
         {
                label: "生效时间:",
                name: "startTime",
                type:"datetime",
                format: 'YYYY-MM-DD hh:mm:ss'
            },
            {
                id:"umessage",
                label:"消息内容",
                name:"message",
                type:"todo"
            }
        ]
    } );
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        if ( action !== 'remove' ) {
            console.log("----------------------------");
            for (var na in o.data){
                if(typeof o.data[na] =="object"){
                    for(var kname in o.data[na]){
                        if(kname == "message"){
                            if($(".J_ueditor").size()>0){
                                var currentParnet = uex.container.parentNode.parentNode;
                                var c = uex.getContent();
                                $(currentParnet).parent().find(".editor-content").val(c);
                                o.data[na][kname] = $(currentParnet).parent().find(".editor-content").val();
                            }
                        }
                    }
                }
            }




            var content = editor.field( 'content' );

            // Only validate user input values - different values indicate that
            // the end user has not entered a value
            if ( ! content.isMultiValue() ) {
                if ( ! content.val() ) {
                	content.error( 'A  content must be given' );
                }
                 
                if ( content.val().length <= 2 ) {
                	content.error( 'The content length must be more that 2 characters' );
                }
            }
 
            // ... additional validation rules
 
            // If any error was reported, cancel the submission so it can be corrected
            if ( this.inError() ) {
                return false;
            }
        }
    } );

    editor.on("open",function () {
        var content = $(".J_ueditor").find("textarea").text();
        var bo = $(".J_ueditor").find(".editor-body");
        bo.html("");
        bo.append(uex.container.parentNode);
        uex.reset();
        setTimeout(function () {
            uex.setContent(content);
        },200);
    });

    editor.on("close",function () {
        var currentParnet = uex.container.parentNode.parentNode;
        var c = uex.getContent();
        $("#hidue").html("");
        $("#hidue").append(uex.container.parentNode);
        uex.reset();
        $(currentParnet).parent().find(".editor-content").text(c);
    });
    
    editor.on("setData",function (ename,full,meta,acname) {
        if(acname == "edit"){

            var currentParnet = uex.container.parentNode.parentNode;
            // var c = uex.getContent();
            // $(currentParnet).parent().find(".editor-content").val(c);
            meta.message = $(currentParnet).parent().find(".editor-content").val();
            console.log("000000000000000000000");
            console.log(meta);

        }
        return true;
    });

    editor.on("initEdit",function ($e,$row,$full) {
        editor.show();

    });
    
    var shiro_admin = "disabled=true";
    if($('#shiro_admin')){
    	shiro_admin = "";
    }
    var table = $('#dtable').DataTable( {
    	dom: "Bfrtip",
        "processing": true,
        "serverSide": true,
        "scrollX": true,
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
            "url": "/rest/cms/msg/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "order": [[ 6, 'desc' ]],
        "columns": [
            { "data": "msgId" },
            { "data": "content" },
            { "data": "link" },
            { "data": "status",render:function ( data, type, row ) {
            	//(0发布,1删除)
           	   if(data==0)return "发布"
           	   if(data==1)return "删除"
              }},
            { "data": "appType",render:function ( data, type, row ) {
         	   if(data==1)return "理财师"
         	   if(data==2)return "投资者"
              }},
            { "data": "startTime" },
            { "data": "crtTime" },
            { "data": "modifyTime" },
			    
        ],
        select: true,
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor },
            { extend: "remove", editor: editor }
        ]
    } );
    
} );
