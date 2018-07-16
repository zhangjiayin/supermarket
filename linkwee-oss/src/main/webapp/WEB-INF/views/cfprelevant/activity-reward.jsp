<%--
  Created by IntelliJ IDEA.
  User: lenli
  Date: 2016/5/31
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<div id="main-activity" class="container-fluid">
    <div class="row">
        <div class="col-lg-4">
            <c:if test="${!empty cfp}"><p id="cfpinfo">理财师：${cfp.name} ${cfp.mobile}<input type="hidden" name="org_mobile" value="${cfp.mobile}"/></c:if> </p>
        </div>
    </div>

    <div class="table-responsive">
        <table id="J-activity" class="table table-bordered">
            <thead>
            <tr>
                <td>序号</td>
                <td>活动名称</td>
                <td>奖励时间</td>
                <td>产生奖励（元）</td>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <th colspan="3" style="text-align: right;">合计：</th>
                <th><span id="jamount">0.00</span></th>
            </tr>
            </tfoot>
        </table>
    </div>

    <script type="text/javascript">
        var $db = $("#J-activity").DataTable(
                {
                    ordering:false,
                    searching:false,
                    lengthChange:false,
                    paging:true,
                    select:true,
                    serverSide:true,
                    dom: '<"J_toolbar ux-toolber">frtip',
                    ajax:{
                        url:"rest/lcsSalesAndEarningList/getLcsActivityProfitDetailList",
                        type:"POST",
                        dataSrc:function (result) {
                            return result.data;
                        },
                        data:function (d) {
                            var $nameOrmobile = $('input[name=nameOrmobile]').val();
                            var $orgMobile  = $("input[name=org_mobile]").val();
                            d.mobile =$orgMobile;
                            d.nameOrmobile = $nameOrmobile;
                            d.pageIndex = (d.start / d.length)+1;
                            d.pageSize =10;
                            d.startDate = $('input[name=startDate]').val();
                            d.endDate = $('input[name=endDate]').val();
                        }
                    },
                    columns:[
                        {data:"id"},
                        {data:"activityName"},
                        {data:"issueTime"},
                        {data:"profit"}
                    ],
                    columnDefs:[
                        {
                            targets:0,
                            data:"id",
                            render:function (data,type,full,meta) {
                                var start = (meta.settings.oAjaxData.start / meta.settings.oAjaxData.length) +1;
                                return (meta.row+1)+(start-1)*10;
                            }
                        },
                        {
                            targets:1,
                            data:"activityName",
                            render:function (data,type,full,meta) {
                                return data != null?data:"--";
                            }
                        },
                        {
                            targets:3,
                            data:"profit",
                            render:function (data,type,full,meta) {
                                var nu = Number(data);
                                return nu.toFixed(2);
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

        var loadRecommendTotal = function () {
            var url = "rest/lcsSalesAndEarningList/getLcsActivityProfitDetailTotal";
            var $nameOrmobile = $('input[name=nameOrmobile]').val();
            var $orgMobile  = $("input[name=org_mobile]").val();
            var startDate = $('input[name=startDate]').val();
            var endDate = $('input[name=endDate]').val();
            var search = $nameOrmobile?$nameOrmobile: $orgMobile;
            $.post(url,{mobile:search,startDate:startDate,endDate:endDate},function (result) {
                $("span#jamount").html(Number(result).toFixed(2));
            },'json');
        }
        var updateCFPInfo = function () {
            var url = "rest/cfprelevant/recommend_profit_json";
            var $nameOrmobile = $('input[name=nameOrmobile]').val();
            if($nameOrmobile == undefined || $nameOrmobile == null || $nameOrmobile.length<=0){
                return false;
            }
            $.post(url,{mobile:$nameOrmobile},function (result) {
                console.log(result);
                if(result){
                    $("#cfpinfo").html('理财师：'+result.name+'&nbsp;&nbsp;'+result.mobile+' <input type="hidden" name="org_mobile" value="'+result.mobile+'"/>')
                }

            },'json');
        }
        loadRecommendTotal();

        $(".J_toolbar").html($("#template-search").html());

        $("#main-activity .J_search").click(function () {
            $db.draw();
            loadRecommendTotal();
            updateCFPInfo();
        });


    </script>

    <script type="text/linkwee-template" id="template-search">
        <input name="nameOrmobile"  class="easyui-textbox" style="width:200px"  placeholder="姓名或者手机号码">
        日期从: <input id="lcsListStartDate" name="startDate" class="Wdate" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'lcsListEndDate\')}'})"/>
        到: <input id="lcsListEndDate" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'lcsListStartDate\')}'})"/>
        <a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
    </script>

</div>