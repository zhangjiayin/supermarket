<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      <%
  request.setCharacterEncoding("UTF-8") ;
 %>
<link rel="stylesheet" type="text/css" href="app/css/eshop4j.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="app/js/jquery.eshop4j.js"></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<div class="container">
	
	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
		<div class="row">
			<div class="col-lg-4">
				<div class="input-group">
					<span class="input-group-btn">
						<label for="payment_amount">货款发放(元)：</label>
						<input type="text" class="form-control" name="amount" id="payment_amount" placeholder="发放金额">
					 </span>			  
				</div>
			</div>
		</div>
		<br/>
		<div class="row">
			<div class="col-lg-4">
				<div class="input-group">
					 <span class="input-group-btn">
						<label for="consumerId">收&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人：</label>
						<input type="text" class="form-control" name="consumerId" id="consumerId" placeholder="收款人">
					 </span>
				</div>
			</div>
		</div>
		<br/>
		<div class="row">
			<div class="col-lg-4">
				<div class="input-group">
					 <span class="input-group-btn">
						<label for="remark">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
						<input type="text" class="form-control" name="remark" id="remark" placeholder="备注">
					 </span>
				</div>
			</div>
		</div>
		<br/>
		<div class="row">
			<div class="col-lg-4">
				<div class="input-group">
					<button class="btn btn-danger J_send_payment" type="button" style="margin-left: 120px">发放</button>			  
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 table-responsive">
		<table id="J-credit-send" class="table table-bordered" data-xtoolbars="#template-search"  data-url="/rest/biz/bizcredit/sendCredit/list_ajax" data-order="false" data-paging="true" data-size="10">
	        <thead>
	            <tr>
		            <th data-name="id" data-edit="id" data-format="id:idFormat">ID</th>
		            <th data-name="credit" data-format='credit:creditFormat'>发放金额（元）</th>
		            <th data-name="wechatNickname" >收款人</th>
		            <th data-name="createTime" data-format='createTime:dateFormat'>发放时间</th>
		            <th data-name="remark">备注</th>
	            </tr>
	        </thead>
	 
	    </table>
	</div>

	<script type="text/javascript">
		$("button.J_send_payment").click(function () {
			var amount = $("#payment_amount").val();
			var consumerId = $("#consumerId").val();
			var remark = $("#remark").val();
			bootbox.confirm("确认发放货款吗？",function (result) {
				if(result){
					$.get("rest/biz/bizcredit/sendPayment",{amount:amount,consumerId:consumerId,remark:remark},function ($result) {
						if($result.isFlag){
							bootbox.alert($result.msg);
							$("#payment_amount").val("");
							$("#consumerId").val("");
							$("#remark").val("");
							$db.draw();
							loadHeadDate();
						} else {
							bootbox.alert($result.msg);
						}
					});

				}
			});
		});
		
	 	var idFormat = function (data,type,full,meta) {
	        var start = (meta.settings.oAjaxData.start / meta.settings.oAjaxData.length) +1;
	        return (meta.row+1)+(start-1)*10;
	    }
	 	  
	    var creditFormat = function (data,type,full,meta) {
	      	return Number(data/100).toFixed(2); 
	    }
	    
	    var dateFormat = function (data,type,full,meta) {
	      	return new Date(data).Format("yyyy-MM-dd hh:mm:ss"); 
	    }
	          
        var $db= $("#J-credit-send").eshop4jTable();
        
        function loadHeadDate(){
        	var startTime = $("#startTime").val(); 
			var endTime = $("#endTime").val();
        	$.ajax({
        		url :"/rest/biz/bizcredit/sendPaymentStatistics",
        		type : 'post',
        		data:{startTime:startTime,endTime:endTime},					
        		dataType : "json",
        		success : function(data) {
        			$("#sendPaymentAmount").html("<font color='red'>累计发放："+data.sendPaymentAmount+"元</font>");
        		},
         		error: function (xhr, textStatus, errorThrown) {
                    //错误信息处理
         			console.info(errorThrown);
                }
        	});	
        }
        
        loadHeadDate();
        
        setTimeout(function(){
        	$(".J_search").click(function () {
        		loadHeadDate();
        	});
        },2000);
       
	</script>
	
	<script type="text/linkwee-template" id="template-search">
        <form>
            <div class="row">
                <div class="col-sm-2">
                   <div class="input-group">	
						<span class="input-group-btn">
							<input id="startTime" name="startTime" class="Wdate"  placeholder="开始时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:100px" />
                        </span>
						<span class="input-group-btn">
							&#126;&#126;
						</span>
						<span class="input-group-btn">                    
							<input id="endTime" name="endTime" class="Wdate"  placeholder="结束时间"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})"  style="width:100px"/>
                        </span>
											
                        <span class="input-group-btn">
                             <a class="btn btn-default J_search parent_search" id="searchXX" style="margin-left: 20px" role="button"><i class="fa fa-search"></i> 查询</a>
                        </span>
					    <div id = "sendPaymentAmount" style="width: 200px;display: inline-block;text-align: center;"></div>
                    </div>
                </div>
				
            </div>

        </form>
    </script>
</div>


