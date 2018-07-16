var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/cim/ciminsurancecate/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/cim/ciminsurancecate/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/cim/ciminsurancecate/save',
                 data:function(d){return {'rows':JSON.stringify(d)};}
            }
        },
        table: "#dtable",
        idSrc:  'cateId',
        i18n: {
            "create": {
                "button": "新增",
                "title":  "创建新的保险种类",
                "submit": "确定"
            },
            "edit": {
                "button": "编辑",
                "title":  "编辑保险种类",
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
                label: "分类id",
                name: "cateId"
                ,type:"hidden"
            }, 
         {
                label: "*分类名称",
                name: "cateName"
            }, 
         {
                label: "排序",
                name: "orderNum"
            },
        {
            label: "分类logo 投资者端",
            name: "cateLogoInvestor",
            ajax: "rest/common/datatablesEditor/fileUpload",
            clearText: "清除",
            type: "upload",
            display: function ( fileMd5 ) {
                return '<img src="'+$("#imgServerUrl").val()+fileMd5+'"/>';
            },            
            uploadText:"上传文件",
            dragDropText:"拖放文件到此处",
            clearText: "清空",
            noFileText: "没有文件",
            noImageText: '没有图片'            
         },
        {
            label: "分类logo 猎才大师",
            name: "cateLogoChannel",
            ajax: "rest/common/datatablesEditor/fileUpload",
            clearText: "清除",
            type: "upload",
            display: function ( fileMd5 ) {
                return '<img src="'+$("#imgServerUrl").val()+fileMd5+'"/>';
            },            
            uploadText:"上传文件",
            dragDropText:"拖放文件到此处",
            clearText: "清空",
            noFileText: "没有文件",
            noImageText: '没有图片'            
         },
        {
            label: "*是否可用",
            name: "disabled",
            type:  "select",
            options: [
                { label: "可用 ", value: 0 },
                { label: "不可用", value: 1 }
            ],
            "default": 0
         },
         {
                label: "分类描述:",
                name: "description"
            }, 
         {
                label: "分类图片跳转链接:",
                name: "urlLink"
            }, 
         {
                label: "分类说明:",
                name: "cateDeclare"
            }, 
        ]
    } );
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {
        var cateName = editor.field( 'cateName' );
        // the end user has not entered a value
        if ( cateName.val() == null || cateName.val() == '' ) {
        	cateName.error( '分类名称不能为空' );
        }
    } );
    var shiro_admin = "disabled=true";
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
            "url": "/rest/cim/ciminsurancecate/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "cateId","orderable": false },
            { "data": "cateName","orderable": false },
            { "data": "orderNum","orderable": false },
            {
            	"data": "cateLogoInvestor",
                "orderable": false ,
                "render": function ( fileMd5 ) {
                	//console.log(fileMd5);
                    return fileMd5 ? '<img src="'+$("#imgServerUrl").val()+fileMd5+'" class="file-preview-image" data-action="zoom" alt="图片" title="上传图片" style="width: 100px;"/>' : null;
                },
                "defaultContent": "",
            },
            {
            	"data": "cateLogoChannel",
                "orderable": false ,
                "render": function ( fileMd5 ) {
                	//console.log(fileMd5);
                    return fileMd5 ? '<img src="'+$("#imgServerUrl").val()+fileMd5+'" class="file-preview-image" data-action="zoom" alt="图片" title="上传图片" style="width: 100px;"/>' : null;
                },
                "defaultContent": "",
            },
            { "data": "disabled","orderable": true 
            	// 是否可用：0-可用 1-不可用
            	,"render": function (data, type, row ) {
	            	if(data =='0'){
	  	            	var bind = '可用';
	  	            } else if(data =='1'){
	  	            	var bind = '不可用';
	  	            }
  	            return bind;
  	            }
            },            
            { "data": "modifyTime","orderable": false },
            { "data": "description","orderable": false },
            { "data": "urlLink","orderable": false },
            { "data": "cateDeclare","orderable": false },
			    
        ],
        select: true,
        "fnPreDrawCallback": function( oSettings ) {
            $('.dataTables_filter input').attr({'name':'search','placeholder': '分类名称或代码'});//提示
        },        
        "order": [[ 5, 'asc' ]],        
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor }
        ]
    } );
    
} );
