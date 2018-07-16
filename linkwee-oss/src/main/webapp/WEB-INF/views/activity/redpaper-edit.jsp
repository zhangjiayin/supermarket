<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<div class="container">
	<c:if test="${empty redpaperInfo}">
		<div class="row">
			<div class="col-md-6">
				<h4><small><p class="text-muted">查询红包信息失败</p></small></h4>
			</div>
			<!-- <div class="col-md-6">
				<a  href="javascript:;" class="btn btn-link ui-redirect" data-title="列表" data-url="rest/redpaper/initRedpaperList">返回</a>
			</div> -->
		</div>
	</c:if>
	<c:if test="${!empty redpaperInfo}">
	<div class="row">
		<div class="col-md-12">

			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">红包统计</p></small></h4>
					</div>
					<!-- <div class="col-md-6">
						<a  href="javascript:;" class="btn btn-link ui-redirect" data-title="列表" data-url="rest/redpaper/initRedpaperList">返回</a>
					</div> -->
				</div>
			</div>
			<div class="row">
				<label class="col-sm-2 control-label">红包类型:</label>
				<div class="col-md-6">
					 <p class="form-control-static">投资返现红包</p>
				</div>
			</div>
			<div class="row">
				<label class="col-sm-2 control-label">红包名称:</label>
				<div class="col-md-6">
					 <p class="form-control-static">${redpaperInfo.name}</p>
				</div>
			</div>
			<div class="row">
				<label class="col-sm-2 control-label">红包描述:</label>
				<div class="col-md-6">
					 <p class="form-control-static">${redpaperInfo.remark}</p>
				</div>
			</div>
			<div class="row">
				<label class="col-sm-2 control-label">红包金额:</label>
				<div class="col-md-6">
					 <p class="form-control-static">${redpaperInfo.money}</p>
				</div>
			</div>
			<div class="row">
				<label class="col-sm-2 control-label">过期时间:</label>
				<div class="col-md-6">
					 <p class="form-control-static"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${redpaperInfo.validDate}" /></p>
				</div>
			</div>
		</div>
		<div class="uk-blank" />

		<div class="col-md-12">

			<div class="page-header">
				<h4><small><p class="text-muted">红包使用规则</p></small></h4>
			</div>
			<div class="row">

				<label class="col-sm-2 control-label">金额限制:</label>
				<div class="col-md-6">
					<p class="form-control-static">
					<c:if test="${redpaperInfo.limitMoney==0}">不限</c:if>
					<c:if test="${redpaperInfo.limitMoney==1}">
						最小金额:<c:if test="${redpaperInfo.minMoney==0}">不限</c:if><c:if test="${redpaperInfo.minMoney!=0}">${redpaperInfo.minMoney} </c:if>,
						最大金额:<c:if test="${redpaperInfo.maxMoney==0}">不限 </c:if><c:if test="${redpaperInfo.maxMoney!=0}">${redpaperInfo.maxMoney}</c:if>
					</c:if>
					 </p>
				</div>


				<!-- <div class="col-md-2"><h5><p class="text-muted">金额限制:</p></h5></div>
				<div class="col-md-6">
					<c:if test="${redpaperInfo.limitMoney==0}"><h5>不限</h5></c:if>
					<c:if test="${redpaperInfo.limitMoney==1}">
						<h5>最小金额:<c:if test="${redpaperInfo.minMoney==0}">不限</c:if><c:if test="${redpaperInfo.minMoney!=0}">${redpaperInfo.minMoney} </c:if>,
						最大金额:<c:if test="${redpaperInfo.maxMoney==0}">不限 </c:if><c:if test="${redpaperInfo.maxMoney!=0}">${redpaperInfo.maxMoney}</c:if></h5>
					</c:if>
				</div> -->
			</div>
			<div class="row">


				<label class="col-sm-2 control-label">投资用户限制:</label>
				<div class="col-md-6">
					 <p class="form-control-static">
					 	<c:if test="${redpaperInfo.limitInvestUser==0}">不限</c:if>
						<c:if test="${redpaperInfo.limitInvestUser==1}">首次投资用户使用</c:if>
					 </p>
				</div>


			<!-- 	<div class="col-md-2"><h5><p class="text-muted">投资用户限制:</p></h5></div>
				<div class="col-md-6">
					<c:if test="${redpaperInfo.limitInvestUser==0}"><h5>不限</h5></c:if>
					<c:if test="${redpaperInfo.limitInvestUser==1}"><h5>首次投资用户使用</h5></c:if>
				</div> -->
			</div>
			<div class="row">
				<label class="col-sm-2 control-label">投资产品限制:</label>

				<div class="col-md-6">
					 <p class="form-control-static">
					 		<c:if test="${redpaperInfo.limitInvestProduct==0}">不限</c:if>
							<c:if test="${redpaperInfo.limitInvestProduct==1}">
								<c:if test="${redpaperInfo.operator==0}">等于 ${redpaperInfo.deadline}天期</c:if>
								<c:if test="${redpaperInfo.operator==1}">大于等于 ${redpaperInfo.deadline}天期</c:if>
							</c:if>
							<c:if test="${redpaperInfo.limitInvestProduct==2}">产品编号</c:if>
					 </p>
				</div>

			<!-- 	<div class="col-md-2"><h5><p class="text-muted">投资产品限制:</p></h5></div>
				<div class="col-md-6">
					<c:if test="${redpaperInfo.limitInvestProduct==0}"><h5>不限</h5></c:if>
					<c:if test="${redpaperInfo.limitInvestProduct==1}">
						<c:if test="${redpaperInfo.operator==0}"><h5>等于 ${redpaperInfo.deadline}天期</h5></c:if>
						<c:if test="${redpaperInfo.operator==1}"><h5>大于等于 ${redpaperInfo.deadline}天期</h5></c:if>
					</c:if>
					<c:if test="${redpaperInfo.limitInvestProduct==2}"><h5>产品编号</h5></c:if>
				</div> -->
			</div>

			<c:if test="${redpaperInfo.limitInvestProduct==2}">
				<div class="row">
					<div class="col-md-8">
						<div id="productList"/>
					</div>
					<script type="text/javascript"  >
						var pids = '${redpaperInfo.pids}';
						window['bindignProductIds']=pids.split(',');
						  $.ajax({
					            async :false,
					            url: './rest/redpaper/getBindingProdcutPage?activityId='+'${param.activityId}'+'&redpacketTypeId='+'${param.redpacketTypeId}',
					            type: 'get',
					            dataType: 'html',
					            success:function(data){
					            	 $('#productList').html(data);
					            }
					        });
					</script>
				</div>
			</c:if>
		</div>
	</div>

	</c:if>

</div>
