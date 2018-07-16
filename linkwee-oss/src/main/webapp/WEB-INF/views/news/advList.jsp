<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js" ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>


<div id="main-adv" class="container-fluid">
    <div class="table-responsive">
        <table id="J-advlist" class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>显示位置</th>
                    <th>位置描述</th>
                    <th>图片</th>
                    <th>链接地址</th>
                    <th>显示顺序</th>
                    <th>显示状态</th>
                    <th>应用类别</th>
                    <th>上架时间</th>
                    <th>下架时间</th>
                    <th>操作</th>
                </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">
        var $db = $("#J-advlist").DataTable(
                {
                    ordering:false,
                    searching:false,
                    lengthChange:false,
                    paging:true,
                    select:true,
                    serverSide:true,
                    scrollX:true,
                    dom: '<"J_toolbar ux-toolber">frtip',
                    ajax:{
                        url:"rest/adv/list_json",
                        type:"POST",
                        dataSrc:function (result) {
                            return result.data;
                        },
                        data:function (d) {
                            console.log();

                            var pageIndexDesc =$('#pageIndexDesc').val();
                            var app_type =$('#news_app_type').val();
                            var title = $.trim($('input[name=search_title]').val());
                            d.pageIndexDesc = pageIndexDesc == undefined?'':pageIndexDesc;
                            d.appType = app_type == undefined?1:app_type;
                            d.columns = [];
                            d.search = {};
                        }
                    },
                    columns:[
                        {data:"id"},
                        {data:"pageIndex"},
                        {data:"pageIndexDesc",width:"120px;"},
                        {data:"imgUrl"},
                        {data:"linkUrl"},
                        {data:"showIndex",width:"120px;"},
                        {data:"status",width:"120px;"},
                        {data:"appType",width:"120px;"},
                        {data:"validBeginDate",width:"120px;"},
                        {data:"validEndDate",width:"120px;"},
                        {data:"id",width:"120px;"}
                    ],
                    columnDefs:[
						{
						    targets:3,
						    data:"imgUrl",
						    render:function (data,type,full,meta) {
						    	if(data){
						    		return '<img src="'+data+'" width="100" height="100" />';
						    	}
						    	return '';
						    	
						        
						    }
						},
						{
						    targets:4,
						    data:"linkUrl",
						    render:function (data,type,full,meta) {
						    	if(data){
						    		return '<a  target="_blank" href="'+data+'" title="'+data+'" >链接地址</a>';
						    	}
						    	return '';
						        
						    }
						},
                        {
                            targets:6,
                            data:"status",
                            render:function (data,type,full,meta) {
                            	if(data=='0'){
                            		return '显示';
                            	}else{
                            		return '不显示';
                            	}
                                
                            }
                        },
                        {
                            targets:7,
                            data:"appType",
                            render:function (data,type,full,meta) {
                            	if(data=='0'){
                            		return '公用';
                            	}else if(data =='1'){
                            		return '理财师端';
                            	}else if(data =='2'){
                            		return '金服端';
                            	}
                                
                            }
                        },
                        {
                            targets:10,
                            data:"id",
                            render:function (data,type,full,meta) {
                                return '<a class="btn btn-primary ui-redirect" href="javascript:;" data-title="编辑广告" data-url="/rest/adv/toupdate?id='+data+'" >编辑</a> &nbsp;&nbsp;<a class="btn btn-primary J_adv_delete" data-id="'+data+'" href="javascript:;">删除</a>';
                            }
                        }
                    ],
                    language:{
                        "emptyTable":"没有数据表",
                        info:"显示第 _START_  至 _END_  项结果，共 _TOTAL_ 项",
                        infoEmpty:"",
                        paginate:{
                            "first":"首页",
                            "next":"下一页",
                            "previous":"上一页"
                        }
                    }
                }
        );
        $(".J_toolbar").html($("#template-search").html());

        $("#main-adv").delegate("a.J_adv_delete","click",function () {
            var iid = $(this).attr("data-id");
            bootbox.confirm("确定要执行删除操作吗？",function (result) {
                if(result){
                    $.get("rest/adv/del",{id:iid},function (result) {
                        if(result.isFlag){
                            $db.draw();
                        }
                    });
                }
            });
        });

        $(".J_search").click(function () {
            $db.draw();
        });

    </script>
    <script type="text/linkwee-template" id="template-search">
        显示位置描述： <input class="easyui-textbox" id="pageIndexDesc" name="pageIndexDesc" style="width:110px">

        <select id="news_app_type" name="appType">
            <option value="1">理财师</option>
            <option value="2">金服</option>
         </select>
        <a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
        <a class="btn btn-default btn-sm ui-redirect" href="javascript:;" data-title="新增广告" data-url="/rest/adv/tosave" role="button">新增</a>
    </script>
</div>


