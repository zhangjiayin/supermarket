<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css">
	.span-hint{
		padding-top: 10px;
		display: inline-block;
		color: blue;
		font-size: 10px;
	}
	.span-unit{
		padding-top: 10px;
		display: inline-block;
		font-size: 10px;
	}

</style>
<input type="hidden" id="cpaFeeTypeAttr" value="${cpaFeeAttr}" />
<div class="container">
	<form id="orgFeeForm" action="/rest/cim/cimorginfoa/updateFeeStrategy" method="post">
		<input type="hidden" name="orgNumber" value="${orgNumber}" /><!-- 机构编码 -->
		<!-- 收费模式记录主键id -->
		<c:if test="${cpaFeeAttr == 1 or cpaFeeAttr == 2}">
			<input type="hidden" name="id" value="${feeStrategyId}" />
		</c:if>

		<div class="row">
			<div class="page-header">
				<span  style="color: blue;font-size: 15px;">该机构旗下的所有产品都按以下方式对用户进行返现。</span>
			</div>

			<div class="row" id="cpaMenu" name="cpaMenu">
				<div class="col-md-12">
					<label class="col-md-2"></label>
					<div class="col-md-10">
						<div class="row">
							<label class="col-md-3 form-group radio">
								<input type="radio" name="cpaFeeAttr" id="fixed" value="1" />首投固定猎财返现：
							</label>
							<div id="fixedContent">
								<span  class="span-unit">元（固定猎财返现金额）</span>
								<div class="form-group col-md-2" style="margin-left: -60px">
									<input type="text" class="form-control" name="fixedMoney" value="<fmt:formatNumber value="${cpaFeeVal}" pattern="#" />" autocomplete="off"  />
								</div>
							</div>
						</div>

						<div class="row">
							<label class="col-md-4 form-group radio">
								<input type="radio" name="cpaFeeAttr" id="propertion" value="2" />首投金额固定返现比例：
							</label>
							<div id="propertionContent">
								<span  class="span-unit">%（猎财返现金额 = 首投金额 * 返现比例）</span>
								<div class="form-group col-md-2" style="margin-left: -100px">
									<input type="text" class="form-control" name="fixedMoneyRatio" value="<fmt:formatNumber value="${cpaFeeRatio}" pattern="0.00" minFractionDigits="0" />" autocomplete="off"  />
								</div>
							</div>
						</div>

						<div class="row">
							<label class="col-md-3 form-group radio">
								<input type="radio" name="cpaFeeAttr" id="float_fixed" value="3">
								首投期限固定返现比例，填写以下表格：
							</label>
						</div>
						<div class="row" id="firstInvestTab">
							<div class="col-md-9">
								<table class="table table-bordered table-hover" id="firstInvestTable">
									<span style="color:red;">*请输入合法的首投期限区间,否则会导致收费计算异常。</span>
									<thead>
									<tr class="success">
										<th class="text-center">输入首投期限区间&nbsp(天)</th>
										<th class="text-center">输入返现比例&nbsp(%)</th>
										<th class="text-center">操作</th>
									</tr>
									</thead>
									<tbody>
									<!-- 判断是否为 cpa按首投金额区间收费 -->
									<c:forEach var="feeRecord" items="${orgFeeStrategyList}" varStatus="idx">
										<c:if test="${feeRecord.feeStrategy == 3}">
											<tr id="feeRecords-${idx.count}">
												<input type="hidden" name="orgFeeRecords_feeRecords${idx.count}.id" value="${feeRecord.id}"/><!-- A专区机构佣金计算策略主键id -->
												<td class="col-md-8">
													<div class="form-group col-md-4" style="margin: 10px 0px 0px 50px;">
														<!-- maxFractionDigits="0"(不保留小数) pattern="#.00"(保留两位小数) pattern="#"(去掉数字1,000默认的逗号","分隔符 )-->
														<input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords${idx.count}.intervalMinVal" value="<fmt:formatNumber value="${feeRecord.intervalMinVal}" maxFractionDigits="0" pattern="#" />" autocomplete="off" required="required"/>
													</div>
													<div class="col-md-1" style="margin-top: 18px;">
														&#126;
													</div>
													<div class="form-group col-md-4" style="margin-top: 10px;">
														<input type="text" class="form-control text-center" min="1" digits="true" name="orgFeeRecords_feeRecords${idx.count}.intervalMaxVal" value="<fmt:formatNumber value="${feeRecord.intervalMaxVal}" maxFractionDigits="0" pattern="#" />" autocomplete="off" />
													</div>
												</td>
												<td>
													<div class="form-group" style="margin-top: 10px;">
														<input type="text" class="form-control text-center" min="0" number="true" name="orgFeeRecords_feeRecords${idx.count}.feeRatio" value="<fmt:formatNumber value="${feeRecord.feeRatio}" pattern="#.00" minFractionDigits="0" />" autocomplete="off"  required="required"/>
													</div>

												</td>
												<td>
													<div style="margin-top: 10px;">
														<button type="button" class="btn btn-default btn-danger" onclick="deleteFirstInvestRow(this);" data-feerowid="feeRecords-${idx.count}" data-feeid="${feeRecord.id}"><i class="fa fa-trash-o"></i> 删除</button>
													</div>
												</td>
											</tr>
										</c:if>
									</c:forEach>
									</tbody>
								</table>
							</div>

							<div class="row" id="addFirstInvest">
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-md-4"></label>
										<div class="col-md-8">
											<button type="button" class="btn btn-default" onclick="addFirstInvestRow(this);"><i class="fa fa-plus"></i> 新增</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row" style="padding-top: 100px;">
			<div class="col-md-12">
				<div class="form-group">
					<label class="col-md-5"></label>
					<div class="col-md-7">
						<button type="submit" class="btn btn-primary"><i class="fa fa-check"></i> 保存</button>
						&nbsp&nbsp&nbsp
						<button type="button" class="btn btn-default" onclick='javascript:$.switchPage("A专区平台","rest/cim/cimorginfoa/list");'><i class="fa fa-arrow-left"></i> 返回</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript" src="app/cimorginfounrecord/formdata-convert-tojson.js"></script>
<script type="text/javascript" src="app/cimorginfounrecord/cimfeerecord-edit.js"></script>