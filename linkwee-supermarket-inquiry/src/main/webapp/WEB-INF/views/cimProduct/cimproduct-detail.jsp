<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript">
	function showDesc() {
		if ($("#desc").css("display") == "none") {
			$("#desc").css("display", "inline");
			$("#sign").text("收起");
		} else {
			$("#desc").css("display", "none");
			$("#sign").text("展开");
		}
		;
	}
	$(document).ready(function() {
		$("#showDesc").click(showDesc);
	})
</script>
 <div class="row">
   <div class="col-md-4 col-md-offset-4">
   	<h4 class="modal-title" id="gridSystemModalLabel">基本信息</h4>
   </div>
 </div>
 <hr>
<div>
	<span style="float: left; margin-left: 200px;">
		<dl class="dl-horizontal">
			<dt>产品名称:</dt><dd>${productDT.productName}</dd>
			<dt>产品类型:</dt><dd>${productDT.productTypeText}</dd>
			<dt>还本付息方式:</dt><dd>${productDT.repaymentWayText}</dd>
			<dt>产品销售开始时间:</dt><dd><fmt:formatDate value="${productDT.saleStartTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<dt>产品销售结束时间:</dt><dd><fmt:formatDate value="${productDT.saleEndTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<dt>是否浮动利率:</dt><dd>${productDT.isFlowText}</dd>
			<dt>浮动最小利率:</dt><dd>${productDT.flowMinRate}</dd>
			<dt>浮动最大利率:</dt><dd>${productDT.flowMaxRate}</dd>
			<dt>加息利率:</dt><dd>${productDT.addRate}</dd>
			<dt>是否固定期限:</dt><dd>${productDT.isFixedDeadlineText}</dd>
			<dt>产品最小期限天数:</dt><dd>${productDT.deadLineMinValue}</dd>
			<dt>产品最大期限天数:</dt><dd>${productDT.deadLineMaxValue}</dd>
			<dt>产品最小期限天数[自定义]:</dt><dd>${productDT.deadLineMinSelfDefined}</dd>
			<dt>产品最大期限天数[自定义]:</dt><dd>${productDT.deadLineMaxSelfDefined}</dd>
			<dt>起息方式:</dt><dd>${productDT.interestWayText}</dd>
			<dt>产品起息日:</dt><dd><fmt:formatDate value="${productDT.validBeginDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<dt>产品到期日:</dt><dd><fmt:formatDate value="${productDT.validEndDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<dt>产品单笔购买最小额度:</dt><dd>${productDT.buyMinMoney}</dd>
			<dt>产品单笔购买最大额度:</dt><dd>${productDT.buyMaxMoney}</dd>
			<dt>产品描述:</dt><dd><div id="showDesc"><button id="sign">展开</button></div><div id="desc" style="display:none">${productDT.productDesc}</div></dd>
		</dl>
	</span>
	<span style="float: left; margin-left: 200px;">
		<dl class="dl-horizontal">
			<dt>产品总额度:</dt><dd>${productDT.buyTotalMoney}</dd>
			<dt>是否拥有产品进度:</dt><dd>${productDT.isHaveProgressText}</dd>
			<dt>是否可赎回可转让:</dt><dd>${productDT.isRedemptionText}</dd>
			<dt>可赎回天数:</dt><dd>${productDT.redemptionTime}</dd>
			<dt>可转让天数:</dt><dd>${productDT.assignmentTime}</dd>
			<dt>货币类型:</dt><dd>${productDT.moneyTypeText}</dd>
			<c:if test="${productDT.riskControlType != null}">
				<dt>风控类型:</dt><dd>${productDT.riskControlTypeText}</dd>
			</c:if>
			<c:if test="${productDT.riskLevel != null}">
			  	<dt>风险级别:</dt><dd>${productDT.riskLevelText}</dd>
			</c:if>
			<dt>产品状态:</dt><dd>${productDT.statusText}</dd>
			<dt>创建者用户名:</dt><dd style="font-style: italic">${productDT.creator}</dd>
			<dt>创建时间:</dt><dd><fmt:formatDate value="${productDT.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<dt>最后一次修改者用户名:</dt><dd style="font-style: italic">${productDT.updater}</dd>
			<dt>最后一次修改时间:</dt><dd><fmt:formatDate value="${productDT.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<dt>修改或审核操作的说明:</dt><dd>${productDT.remark}</dd>
			<dt>机构编码:</dt><dd style="font-style: italic">${productDT.orgNumber}</dd>
			<dt>机构产品ID:</dt><dd>${productDT.thirdProductId}</dd>
			<dt>是否限额产品:</dt><dd>${productDT.isQuotaText}</dd>
			<dt>购买递增金额:</dt><dd>${productDT.buyIncreaseMoney}</dd>
			<dt>审核状态:</dt><dd>${productDT.auditStatusText}</dd>
			<dt>审核时间:</dt><dd><fmt:formatDate value="${productDT.auditTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
		</dl>
	</span>
</div>
<div style="clear: both;"></div>
 <div class="row">
   <div class="col-md-4 col-md-offset-4">
   	<h4 class="modal-title" id="gridSystemModalLabel">产品统计信息</h4>
   </div>
 </div>
 <hr>
 <div>
	<span style="float: left; margin-left: 200px;">
	 <dl class="dl-horizontal">
		<dt>产品被投资总额:</dt><dd>${productDT.buyedTotalMoney}</dd>
		<dt>产品已投资人数:</dt><dd>${productDT.buyedTotalPeople}</dd>
		<dt>最后一次更新时间:</dt><dd><fmt:formatDate value="${productDT.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
	 </dl>
	</span>
</div>
<div style="clear: both;"></div>
<c:if test="${productDT.isCollect == 2}" >
 <div class="row">
   <div class="col-md-4 col-md-offset-4">
   	<h4 class="modal-title" id="gridSystemModalLabel">募集信息</h4>
   </div>
 </div>
 <hr>
 <div>
	<span style="float: left; margin-left: 200px;">
	 <dl class="dl-horizontal">
		<dt>是否需要募集时间:</dt><dd>${productDT.isCollectText}</dd>
		<dt>募集开始时间:</dt><dd><fmt:formatDate value="${productDT.collectBeginTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
		<dt>募集截止时间:</dt><dd><fmt:formatDate value="${productDT.collectEndTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
	 </dl>
	</span>
</div>
</c:if>
