<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container" >
		  <div class="row">
			 <label class="col-sm-3 control-label">本次${year}年${month}月佣金发放的总体状况:</label>
			 <div class="col-sm-2">
			 	<a class="btn btn-default J_fee_download" <c:if test="${payStatus eq 0 }">style="display: none;"</c:if>  href="/rest/fee/feeDownload" role="button"><i class="fa fa-download"></i> 导出佣金计算结果</a>
			 </div>
          </div>
          <br>
           <div class="row">
           <div class="col-sm-5">
	          <table class="table"  width="100%">

		          	<tr class="row">
		          		<td class="col-sm-4"></td>
		          		<td class="col-sm-3">
		          			<label class="control-label">金额(元)</label>
		          		</td>
		          		<td class="col-sm-2">
		          			<label class="control-label">人数</label>
		          		</td>
		          	</tr>

	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">销售佣金:</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.feeProfit}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.feeProfitNumber}</label>
	          		</td>
	          	</tr>
	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">推荐奖励:</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.recommendProfit}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.recommendProfitNumer}</label>
	          		</td>
	          	</tr>
	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">直接管理津贴:</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.childManagementProfit}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.childManagementProfitNumber}</label>
	          		</td>
	          	</tr>
	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">团队管理津贴:</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.teamManagementProfit}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.teamManagementProfitNumer}</label>
	          		</td>
	          	</tr>
	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">基础加拥(平台加佣):</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.feeProfitAdd}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.feeProfitAddNumber}</label>
	          		</td>
	          	</tr>
	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">推荐加拥(平台加佣):</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.recommendProfitAdd}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.recommendProfitAddNumer}</label>
	          		</td>
	          	</tr>
	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">基础加拥(个人加佣):</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.personFeeAdd}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.personFeeAddNumber}</label>
	          		</td>
	          	</tr>	          	
	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">保险销售佣金:</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.insuranceFeeProfit}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.insuranceFeeProfitNumber}</label>
	          		</td>
	          	</tr>
	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">保险推荐奖励:</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.insuranceRecommendProfit}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.insuranceRecommendProfitNumer}</label>
	          		</td>
	          	</tr>
	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">保险直接管理津贴:</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.insuranceChildManagementProfit}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.insuranceChildManagementProfitNumber}</label>
	          		</td>
	          	</tr>
	          	<tr class="row">
	          		<td class="col-sm-4">
	          			  <label class="control-label">保险团队管理津贴:</label>
	          		</td>
	          		<td class="col-sm-3">
	          			<label class="control-label">${summary.insuranceTeamManagementProfit}</label>
	          		</td>
	          		<td class="col-sm-2">
	          			<label class="control-label">${summary.insuranceTeamManagementProfitNumer}</label>
	          		</td>
	          	</tr>	          		          		          		          		          		          	

		          	<tr class="row">
		          		<td class="col-sm-4">
		          			  <label class="control-label">总计:</label>
		          		</td>
		          		<td class="col-sm-3">
		          			<label class="control-label">${summary.totalProfit}</label>
		          		</td>
		          		<td class="col-sm-2">
		          			<label class="control-label">${summary.totalNumber}</label>
		          		</td>
		          	</tr>

	          </table>
	          </div>
          </div>
         <%--  <div class="row">
              <label class="col-sm-2 control-label">佣金总额（元）:</label>
               <label class="col-sm-2 control-label totalFeeAmt">${summary.totalProfit}</label>
          </div>
          <br>
          <div class="row">
               <label class="col-sm-2 control-label">佣金发放总人数（人）:</label>
               <label class="col-sm-2 control-label totalNumber">${summary.totalNumber}</label>
          </div> --%>
          <br>
		<c:if test="${payStatus==0}">
	           <div class="row J_msg">
					 <label class="col-sm-2 control-label">正在计算佣金...请稍后!</label>
	          </div>
	          <br>
		</c:if>

		<c:if test="${payStatus==1}">
			<shiro:hasPermission name="fee:pay">
	          <div class="row">
	              <div class="col-sm-5">


		              	<a class="btn btn-danger J_fee_pay"  href="javascript:;" role="button"><i class="fa fa-list"></i> 以上金额及人数确认OK，发放佣金</a>
						<script type="text/javascript">


						      //佣金发放
						    $("a.J_fee_pay").click(function() {
						    	layer.confirm('发放完成后理财师即可提现，确认佣金发放？', {btn: ['确定','取消'],title:'系统提示',icon: 3}, function(index){
                                                     var $modal = $('#fee_pay_modal');
						    		 layer.close(index);
						    		 $("a.J_fee_pay").hide();
						    		 $.post('/rest/fee/feePay',{"reqPayFeeUid":'${payFeeUid}'}, function(data, textStatus, xhr) {
						                  if(!data.isFlag){
						                      layer.alert(data.msg, {icon: 0}); //失败
						                  }else{
						                	  layer.alert(data.msg, {icon: 1}); //成功

	                                          $.ajax({
	                                  			url : '/rest/fee/feeModelPage',
	                                  			type : 'GET',
	                                  			success : function(data) {

	                                  				$modal.find(".modal-body").html(data);
	                                  			}
	                                  		});
						                  }

						          });
								});
						    });
							</script>

	              </div>
	          </div>
	          </shiro:hasPermission>
          </c:if>
          <c:if test="${payStatus==2}">
	           <div class="row J_msg">
					 <label class="col-sm-4 control-label"><font color="red">本次${year}年${month}月佣金已发放!</font></label>
	          </div>
	          <br>
		</c:if>
</div>
<c:if test="${payStatus==0}">
<script type="text/javascript">
$(function(){

$.post('/rest/fee/prePayFee', {"reqPrePayFeeUid":'${prePayFeeUid}'},function(result, textStatus, xhr) {

    if(result.isFlag){
    	$.ajax({
			url : '/rest/fee/feeModelPage',
			type : 'GET',
			success : function(data) {
				var $modal = $('#fee_pay_modal');
				$modal.find(".modal-body").html(data);
			}
		});
	    /*  var data = result.data;
         $(".totalFeeAmt").text(data.totalFeeAmt);
         $(".totalNumber").text(data.totalNumber);
         $(".J_fee_download").show();
         $(".J_msg").hide();
         var $feepay = $(".J_fee_pay");
         if($feepay){
             $feepay.show();
         } */
      }else{
    	  $('#J_msg').html("佣金计算失败!");
    	  layer.alert(result.msg, {icon: 0});
      }
  });
});

</script>
</c:if>

