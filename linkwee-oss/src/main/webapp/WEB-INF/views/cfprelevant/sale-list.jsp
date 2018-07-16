<%--
  Created by IntelliJ IDEA.
  User: lenli
  Date: 2016/5/31
  Time: 10:09
  To change this template use File | Settings | File Templates.
  理财师销售与收益列表
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>


<div id="main-sale" class="container-fluid">
    <div class="table-responsive">
        <table id="J-salelist" class="table table-bordered" data-url="rest/cfprelevant/salelist_json" data-order="false" data-xtoolbars="#template-search" data-defer="false">
            <thead>
            <tr>
                <th data-name="id" data-format="id:serial">序号</th>
                <th data-name="number">ID</th>
                <th data-name="name"> 理财师</th>
                <th data-name="mobile"> 电话号码</th>
                <th data-name="investmentTotalAmount" data-format="investmentTotalAmount:float"> 累计销售</th>
                <th data-name="investmentCount">销售笔数</th>
                <th data-name="tfee" data-format="tfee:linkFormat">佣金(元)</th>
                <th data-name="recommendedAmount" data-format="recommendedAmount:linkFormat"> 推荐收益(元)</th>
                <th data-name="profitTotalAmount" data-format="profitTotalAmount:linkFormat">活动奖励(元)</th>
                <th data-name="amountTotal" data-format="amountTotal:linkFormat">客户在投(元)</th>
            </tr>
            </thead>
        </table>
    </div>
    <script type="text/javascript">
        var linkFormat = function (data,type,full,meta) {
            var num = Number(data);
            var title = "销售佣金明细";
            if(!full){
                return "";
            }
            var url = 'rest/cfprelevant/commission?mobile='+full.mobile;
            switch (meta.col){

                case 6:
                     num = new Number(full.tfee);
                    title = "销售佣金明细";
                    url = 'rest/cfprelevant/commission?mobile='+full.mobile;
                    break;
                case 7:
                     num = new Number(full.recommendedAmount);
                    title = "推荐收益明细";
                    url = 'rest/cfprelevant/recommend_profit?mobile='+full.mobile;
                    break;
                case 8:
                     num = new Number(full.profitTotalAmount);
                    title = "活动奖励明细";
                    url = 'rest/cfprelevant/activity_reward?mobile='+full.mobile;
                    break;
                case 9:
                     num = new Number(full.amountTotal);
                    title = "当前客户在投";
                    url = 'rest/cfprelevant/current_investor?mobile='+full.mobile;
                    break;
            }
            return data?'<a class="ui-redirect" data-title="'+title+'" data-url="'+url+'">'+num.toFixed(2)+'</a>':'0';
        }
       var $datatables =  $("#J-salelist").linkweeTable();
    </script>

    <script type="text/linkwee-template" id="template-search">
        <form>
            <input name="mobile"  class="easyui-textbox" style="width:200px"  placeholder="输入电话或姓名">
            <a class="btn btn-default btn-sm J_search" href="#" role="button">查询</a>
        </form>
    </script>
</div>
