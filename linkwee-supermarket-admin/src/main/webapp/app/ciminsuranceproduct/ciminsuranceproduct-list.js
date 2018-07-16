var editor; // use a global for the submit and return data rendering in the examples
var permissionList=[];

$(document).ready(function() {
    editor = new $.fn.dataTable.Editor( {
    	ajax: {
            create: {
                type: 'POST',
                url:  'rest/cim/ciminsuranceproduct/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            edit: {
                type: 'POST',
                url:  'rest/cim/ciminsuranceproduct/save',
                data:function(d){return {'rows':JSON.stringify(d)};}
            },
            remove: {
            	 type: 'POST',
                 url:  'rest/cim/ciminsuranceproduct/save',
                 data:function(d){return {'rows':JSON.stringify(d)};}
            }           
        },
        table: "#dtable",
        idSrc:  'id',
        i18n: {
            "create": {
                "button": "新增",
                "title":  "创建新的保险",
                "submit": "确定"
            },
            "edit": {
                "button": "编辑",
                "title":  "编辑保险",
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
                label: "序号",
                name: "id",
                "default": null,
                type:"hidden"
            }, 
         {
                label: "*机构编码",
                name: "orgNumber",
                "default": "OPEN_QIXIN_WEB"
            }, 
         {
                label: "*方案代码",
                name: "caseCode"
            }, 
         {
                label: "*产品名称",
                name: "productName"
            }, 
         {
                label: "*产品特色",
                name: "fullDescription"
            },
         {
            label: "*分类",
            name: "fristCategory",
            type:  "select",
            "default": 0
         },
        {
            label: "*产品状态",
            name: "state",
            type:  "select",
            options: [
                { label: "待审", value: 0 },
                { label: "上架", value: 1 },
                { label: "下架", value: 2 },
                { label: "测试", value: 3 },
                { label: "停售", value: 4 }
            ],
            "default": 0
         },            
         {
                label: "*价格（分）",
                name: "price"
            },
         {
            label: "*背景图片",
            name: "productBakimg",
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
             label: "*犹豫期(单位:天):",
             name: "hesitateDate",
             "default": 30
         },          
         {
        	 label: "*佣金率:",
        	 name: "feeRatio"
         },             
         {
             label: "排序:",
             name: "orderSort",
             "default": 0
         }, 
         {
             "label": "推荐",
             "name": "cimInsuranceRecommendNameList[].recommendType",
             "type": "checkbox"
         },         
        ]
    } );
    
    //创建模式
    editor.on('initCreate',function(e,o,action){
    	editor.field('orgNumber').enable();
    	editor.field('caseCode').enable();
    });
    
    //编辑模式
    editor.on('initEdit',function(e,o,action){
    	editor.field('orgNumber').disable();
    	editor.field('caseCode').disable();
    });
    
    //前端校验
    editor.on( 'preSubmit', function ( e, o, action ) {

        var feeRatio = editor.field( 'feeRatio' );
        var hesitateDate = editor.field( 'hesitateDate' );
        var orgNumber = editor.field( 'orgNumber' );
        var caseCode = editor.field( 'caseCode' );
        var productName = editor.field( 'productName' );
        var fullDescription = editor.field( 'fullDescription' );
        var price = editor.field( 'price' );
 
            // Only validate user input values - different values indicate that
        // the end user has not entered a value
        if ( feeRatio.val() == null || feeRatio.val() == '' ) {
        	feeRatio.error( '佣金率不能为空' );
        }
        if ( hesitateDate.val() == null || hesitateDate.val() == '' ) {
        	hesitateDate.error( '犹豫期不能为空' );
        }
        if ( orgNumber.val() == null || orgNumber.val() == '' ) {
        	orgNumber.error( '机构编码不能为空' );
        }
        if ( caseCode.val() == null || caseCode.val() == '' ) {
        	caseCode.error( '方案代码不能为空' );
        }
        if ( productName.val() == null || productName.val() == '' ) {
        	productName.error( '产品名称不能为空' );
        }
        if ( fullDescription.val() == null || fullDescription.val() == '' ) {
        	fullDescription.error( '产品特色不能为空' );
        }
        if ( price.val() == null || price.val() == '' ) {
        	price.error( '价格（分）不能为空' );
        }
        if ( this.inError() ) {
            return false;
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
            "url": "/rest/cim/ciminsuranceproduct/list",
            "type": "POST",
            "data":function(d){return {'_dt_json':JSON.stringify(d)};}//传递对象太多，json化
        },
        "columns": [
            { "data": "id","orderable": false },
            { "data": "orgNumber","orderable": false },
            { "data": "caseCode","orderable": false },
            { "data": "productName","orderable": false },
            { "data": "fullDescription","orderable": false },
            { "data": "fristCategory","orderable": false
            	//"一级分类   1-意外险  2-旅游险 3-家财险  4-医疗险 5-重疾险 6-年金险:"
            	,"render": function (data, type, row ) {
	            	if(data =='1'){
	  	            	var bind = '意外险';
	  	            } else if(data =='2'){
	  	            	var bind = '旅游险';
	  	            } else if(data =='3'){
	  	            	var bind = '家财险';
	  	            } else if(data =='4'){
	  	            	var bind = '医疗险';
	  	            } else if(data =='5'){
	  	            	var bind = '重疾险';
	  	            } else if(data =='6'){
	  	            	var bind = '年金险';
	  	            } else if(data =='7'){
	  	            	var bind = '寿险';
	  	            } else if(data =='8'){
	  	            	var bind = '少儿险';
	  	            } else if(data =='9'){
	  	            	var bind = '老年险';
	  	            }  else {
	  	            	var bind = '';
	  	            }
  	            return bind;
  	            }            	
            },
            { "data": "state","orderable": true 
            	// 产品状态 0：待审 1：上架 2：下架 3：测试 4：停售:",
            	,"render": function (data, type, row ) {
	            	if(data =='0'){
	  	            	var bind = '待审';
	  	            } else if(data =='1'){
	  	            	var bind = '上架';
	  	            } else if(data =='2'){
	  	            	var bind = '下架';
	  	            } else if(data =='3'){
	  	            	var bind = '测试';
	  	            } else if(data =='4'){
	  	            	var bind = '停售';
	  	            } else {
	  	            	var bind = '';
	  	            }
  	            return bind;
  	            }
            },
            { "data": "price","orderable": false },
            {
                data: "productBakimg",
                "orderable": false ,
                render: function ( fileMd5 ) {
                	//console.log(fileMd5);
                    return fileMd5 ? '<img src="'+$("#imgServerUrl").val()+fileMd5+'" class="file-preview-image" data-action="zoom" alt="图片" title="上传图片" style="width: 100px;"/>' : null;
                },
                defaultContent: "",
                title: "图片"
            },      
            { "data": "orderSort","orderable": false },
            { "data": "feeRatio","orderable": false },
            { "data": "hesitateDate","orderable": false },
            { 
            	"data": "cimInsuranceRecommendNameList",
            	 "orderable": false,
            	 render: "[, ].recommendTypeName"
            },	
        ],
        select: true,
        "fnPreDrawCallback": function( oSettings ) {
            $('.dataTables_filter input').attr({'name':'search','placeholder': '保险名称或方案代码'});//提示
        },        
        "order": [[ 6, 'asc' ]],
        buttons: [
            { extend: "create", editor: editor },
            { extend: "edit",   editor: editor }
        ]
    } );
    
} );
