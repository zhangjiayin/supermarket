<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css" />
<script type="text/javascript"
	src="assets/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>
<div class="container">
	<c:if test="${empty redpaperInfo}">
		<div class="row">
			<div class="col-md-6">
				<h4>
					<small><p class="text-muted">查询红包信息失败</p></small>
				</h4>
			</div>
		</div>
	</c:if>
	<c:if test="${!empty redpaperInfo}">
	<input type="hidden" id="activityId" value="${param.activityId}">
	<input type="hidden" id="redpacketTypeId" value="${param.redpacketTypeId}">
		<div class="view">
			<div class="row-fluid clearfix">
				<div class="span12 column ui-sortable">
					<div class="box box-element ui-draggable" style="display: block;">

						<div class="view">
							<form class="form-horizontal" id="redpaperForm" >
							
								<div class="control-group">
									<label class="control-label" contenteditable="true"
										for="redpaperType">红包类型</label>
									<div class="controls">
										<input id="redpaperType" placeholder="红包类型" type="text"
											disabled="disabled" value="投资返现红包" />
									</div>
								</div>

								<div class="control-group">
									<label class="control-label" contenteditable="true" for="name">红包名称</label>
									<div class="controls">
										<input id="name" name="name" placeholder="红包名称" type="text"
											value="${redpaperInfo.name}" />
									</div>
								</div>

								<div class="control-group">
									<label class="control-label" contenteditable="true"
										for="remark">红包描述</label>
									<div class="controls">
										<input id="remark" name="remark" placeholder="红包描述"
											type="text" value="${redpaperInfo.remark}" />
									</div>
									<p style="color: red;">描述必须以英文逗号分隔,逗号之前为期限描述,逗号之后为使用金额描述.如:'180天期以上,10000元以上'</p>
								</div>

								<div class="control-group">
									<label class="control-label" contenteditable="true" for="money">红包金额</label>
									<div class="controls">
										<input id="money" name="money" placeholder="红包金额" type="text"
											value="${redpaperInfo.money}" />
									</div>
								</div>

								<div class="control-group">
									<label class="control-label" contenteditable="true" for="money">有效日期</label>
									<div class="controls">
										<input id="validDate" placeholder="有效日期" name="validDate" class="Wdate" type="text" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${redpaperInfo.validDate}" />" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d {%H+1}:%m:%s'})">



										<%-- <input id="validDate" name="validDate" placeholder="有效日期"
											type="text" />${redpaperInfo.validDate} --%></div>
								</div>

								<c:set var="isLimitMoney" value="${redpaperInfo.limitMoney==1}" />
								<div class="control-group">
									<label class="control-label" contenteditable="true"
										for="limitMoney">投资金额限制</label>
									<div class="controls">
										<select id="limitMoney" name="limitMoney">
											<option value="0"
												<c:if test="${!isLimitMoney}">selected="selected"</c:if>>不限</option>
											<option value="1"
												<c:if test="${isLimitMoney}">selected="selected"</c:if>>金额限制</option>
										</select>


									</div>
								</div>

								<div class="control-group" id="minMoneyDiv"
									<c:if test="${!isLimitMoney}">style="display: none;"</c:if>>
									<label class="control-label" contenteditable="true"
										for="minMoney">最小金额</label>
									<div class="controls">
										<div class="controls">
											<input id="minMoney" name="minMoney" placeholder="最小金额"
												type="text" value="${redpaperInfo.minMoney}" />
										</div>
										<p style="color: red;">0 表示不限最小投资金额</p>
									</div>
								</div>
								<div class="control-group" id="maxMoneyDiv"
									<c:if test="${!isLimitMoney}">style="display: none;"</c:if>>
									<label class="control-label" contenteditable="true"
										for="maxMoney">最大金额</label>
									<div class="controls">
										<div class="controls">
											<input id="maxMoney" name="maxMoney" placeholder="最大金额"
												type="text" value="${redpaperInfo.maxMoney}" />
										</div>
										<p style="color: red;">0 表示不限最大投资金额</p>
									</div>
								</div>


								<div class="control-group">
									<label class="control-label" contenteditable="true"
										for="limitInvestUser">投资用户限制</label>
									<div class="controls">
										<select id="limitInvestUser" name="limitInvestUser">
											<option value="0">不限</option>
										</select>
									</div>
								</div>

								<div class="control-group">
									<label class="control-label" contenteditable="true"
										for="limitInvestProduct">投资产品限制</label>
									<div class="controls">
										<select id="limitInvestProduct" name="limitInvestProduct">
											<option value="0"
												<c:if test="${redpaperInfo.limitInvestProduct==0}">selected="selected"</c:if>>不限产品</option>
											<option value="1"
												<c:if test="${redpaperInfo.limitInvestProduct==1}">selected="selected"</c:if>>按产品期限限制</option>
											<option value="2"
												<c:if test="${redpaperInfo.limitInvestProduct==2}">selected="selected"</c:if>>按产品期限限制</option>
										</select>
									</div>
								</div>

								<div class="control-group" id="operatorDiv"
									<c:if test="${redpaperInfo.limitInvestProduct==0 || redpaperInfo.limitInvestProduct==2}">style="display: none;"</c:if>>
									<label class="control-label" contenteditable="true"
										for="operator">期限关系</label>
									<div class="controls">
										<select id="operator" name="operator">
											<option value="0"
												<c:if test="${redpaperInfo.operator==0}">selected="selected"</c:if>>等于</option>
											<option value="1"
												<c:if test="${redpaperInfo.operator==1}">selected="selected"</c:if>>大于等于</option>
										</select>
									</div>
								</div>
								<div class="control-group" id="deadlineDiv"
									<c:if test="${redpaperInfo.limitInvestProduct==0 || redpaperInfo.limitInvestProduct==2}">style="display: none;"</c:if>>
									<label class="control-label" contenteditable="true"
										for="deadline">期限</label>
									<div class="controls">
										<div class="controls">
											<input id="deadline" name="deadline" placeholder="期限"
												type="text" value="${redpaperInfo.deadline}" />
										</div>
									</div>
								</div>
								<div class="control-group" id="plistDiv"
									<c:if test="${redpaperInfo.limitInvestProduct==0 || redpaperInfo.limitInvestProduct==1}">style="display: none;"</c:if>>
									<label class="control-label" contenteditable="true" for="plist">产品列表</label>
									<div class="controls">
										<div class="controls">
											<div id="plist"></div>
										</div>
										<script type="text/javascript">
											var pids = '${redpaperInfo.pids}';
											window['rproductIds']=pids.split(',');
											  $.ajax({
										            async :false,
										            url: './rest/redpaper/getNoSendRedpaperBindingProdcutPage?activityId='+'${param.activityId}'+'&redpacketTypeId='+'${param.redpacketTypeId}',
										            type: 'get',
										            dataType: 'html',
										            success:function(data){
										            	 $('#plist').html(data);
										            	 if('${redpaperInfo.limitInvestProduct}'==2){
										            	 	$('a.J_redpaperProductList_search').trigger('click');
										            	 }
										            }
										        });
										</script>

									</div>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br/>
		<br/>
		<div class="row">
			<div class="col-sm-2">
				<a class="btn btn-primary" id="submit-btn" href="javascript:;">更新</a>
			</div>
		</div>
	</c:if>

	<script type="text/javascript">
		$(document).ready(function() {

			$("#limitMoney").on("change",function(event){
				var selected = $(this).children('option:selected').val();
				if(selected==1){
					$("#minMoneyDiv").show();
					$("#maxMoneyDiv").show();
				}else{
					$("#minMoneyDiv").hide();
					$("#maxMoneyDiv").hide();
				}
			});


			$("#limitInvestProduct").on("change",function(event){
				var selected = $(this).children('option:selected').val();
				if(selected==1){
					$("#operatorDiv").show();
					$("#deadlineDiv").show();
					$("#plistDiv").hide();
				} else if(selected==2){
					$("#plistDiv").show();
					$('a.J_redpaperProductList_search').trigger('click');
					$("#operatorDiv").hide();
					$("#deadlineDiv").hide();
				}else{
					$("#plistDiv").hide();
					$("#operatorDiv").hide();
					$("#deadlineDiv").hide();
				}
			});

			$("#submit-btn").on('click', function () {
		    	var data = milo.jsonFromt($('#redpaperForm').serializeArray());
		    	if(!data.name){
		             layer.msg('红包名称不能为空',{time: 1000,icon: 0});
		             return false;
		    	}
		    	if(!data.remark){
		             layer.msg('红包描述不能为空',{time: 1000,icon: 0});
		            return false;
		    	}
		         if( !$.isNumeric(data.money) || data.money<=0 ){
		            layer.msg('红包金额必须为大于0的数值',{time: 1000,icon: 0});
		            return false;
		         }

		         if(!data.validDate){
		            layer.msg('有效日期不能为空',{time: 1000,icon: 0});
		           return false;
		        }
		        if(data.limitMoney==1){
		            if(!data.minMoney || !$.isNumeric(data.minMoney) || data.minMoney<0){
		                 layer.msg('购买产品金额最小值必须为大于等于0的数值',{time: 1000,icon: 0});
		                return false;
		            }
		            if(!data.maxMoney || !$.isNumeric(data.maxMoney) || data.maxMoney<0){
		               layer.msg('购买产品金额最大值必须为大于等于0的数值',{time: 1000,icon: 0});
		               return false;
		            }
		            if(data.maxMoney!=0 && data.minMoney>=data.maxMoney){
		            	layer.msg('购买产品金额最大值必须为大于最小值',{time: 1000,icon: 0});
		                return false;
		            }
		        }

		        if(data.limitInvestProduct==1){
		            if(!data.deadline || !$.isNumeric(data.deadline) || data.deadline<=0){
		                 layer.msg('产品期限必须为大于0的数值',{time: 1000,icon: 0});
		                return false;
		            }

		        }
		        if(data.limitInvestProduct==2){
		    		var rproductIds= window['rproductIds'];
		    		var notEmpty = milo.getLength(rproductIds)>0;
		            	if(notEmpty){
		            		data.pids = rproductIds.join(",");
		            	}else{
		            		layer.msg('请至少绑定一个产品！',{time: 1000,icon: 0});
		            		return false;
		            	}
		    	};
		    	delete data.pname;
		    	delete data.pdeadline;
		    	data =  milo.serialize(data);
		    	$.post('rest/redpaper/update?activityId='+$("#activityId").val()+'&redpacketTypeId='+$("#redpacketTypeId").val(), data, function(data, textStatus, xhr) {
		              layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
		              if(data.isFlag){
		            	  $.switchPage('红包列表 ','rest/redpaper/initRedpaperList');
		              }
		             
		        });
		        //refreshPage();
		        return false;
		    });
		});

	</script>

</div>
