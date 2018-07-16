<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    request.setAttribute("ctx", request.getContextPath());
%>
<input id="path" type="hidden" value="${ctx}" />
<script type="text/javascript">
    window.UEDITOR_HOME_URL = '${ctx}/assets/plugins/ueditor/';
</script>
<!-- ueditor -->
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="assets/plugins/ueditor/ueditor.zimg.js"></script>


 <style type="text/css">
	div.col-md-8.fill{
		margin-top: 10px;
	}
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
	.span-required{
		padding-top: 10px;
		display: inline-block;
		color: red;
		font-size: 15px;
	}
	/*头像上传样式*/
	.kv-avatar .file-preview-frame,.kv-avatar .file-preview-frame:hover {
        margin: 0;
        padding: 0;
        border: none;
        box-shadow: none;
        text-align: center;
	}
	.kv-avatar .file-input {
	    display: table-cell;
	    max-width: 220px;
	}
</style>
<!-- 图片服务器 -->
<input type="hidden" id="imgServerUrl" value="${img_server}"/>
<div class="container">
	<form id="orgForm" action="/rest/cim/cimorginfoa/update" method="post">
		<div class="row">
			<div class="page-header">
					<h4><strong>基本信息-</strong>(含<span class="span-required">*</span>标示为必填项)</h4>
			</div>
			<div class="col-sm-10"><!-- 小屏幕  ≥768px-->
                <input type="hidden" name="id" value="${orgInfo.id}" /><!-- 机构主键id -->
                <input type="hidden" name="status" value="${orgInfo.status}" /> <!-- 机构合作状态 -->
                <input type="hidden" name="orgGrayStatus" value="${orgInfo.orgGrayStatus}" /> <!-- 是否灰度机构 -->
                <input type="hidden" name="orgRegisterStatus" value="${orgInfo.orgRegisterStatus}" /> <!-- 一键注册开启状态 -->

                <div class="col-md-8 fill"><!-- 中等屏幕 ≥992px -->
                    <div class="form-group">
                        <label class="control-label col-md-3">机构名称：</label>
                        <span  class="span-required">*</span>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="name"  id="name" value="${orgInfo.name}" autocomplete="off" placeholder="请输入机构名称" required />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">机构编码：</label>

                        <c:choose>
                            <c:when test="${orgInfo.id == null}">
                                <button type="button" onclick="builderOrgNumber();" style="display: inline-block;margin-top: 5px;">生成编码</button>
                            </c:when>
                            <c:otherwise>
                                <span  class="span-hint">设置后不能修改</span>
                            </c:otherwise>
                        </c:choose>

                        <div class="col-md-5">
                            <input type="text" class="form-control required" id="orgNumber" name="orgNumber" value="${orgInfo.orgNumber}" autocomplete="off" placeholder="机构编码根据机构名称全拼生成" readonly="readonly" />
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">机构灰度模式：</label>
                        <span  class="span-required">*</span>
                        <span  class="span-hint">设置机构为灰度模式需开启(ON)</span>
                         <div class="switch col-md-3">
                            <input type="checkbox" id="orgGrayStatus" data-name="orgGrayStatus" class="bootstrapSwitch" <c:if test="${orgInfo.orgGrayStatus == 1}">checked</c:if> />
                        </div>
                    </div>
                </div>
				    
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">合作状态：</label>
                        <span  class="span-required">*</span>
                        <span  class="span-hint">只有已合作(ON)的机构才会在APP和PC端显示</span>
                         <div class="switch col-md-3">
                            <input type="checkbox" id="status" data-name="status" class="bootstrapSwitch" <c:if test="${orgInfo.status == 1}">checked</c:if>  />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">一键注册状态：</label>
                        <span  class="span-required">*</span>
                        <span  class="span-hint">A专区对接了注册时才需要开启</span>
                        <div class="switch col-md-3">
                            <input type="checkbox" id="orgRegisterStatus" data-name="orgRegisterStatus" class="bootstrapSwitch" <c:if test="${orgInfo.orgRegisterStatus == 1}">checked</c:if>  />
                        </div>
                    </div>
                </div>

                <div class="col-md-8 fill">
                    <div class="form-group">
                        <label class="control-label col-md-3">用户校验地址：</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="orgUserExistUrl" value="${orgInfo.orgUserExistUrl}" autocomplete="off" placeholder="请输入用户校验地址" style="width: 600px;" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8 fill">
                    <div class="form-group">
                        <label class="control-label col-md-3">绑定用户地址：</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="orgBindUserUrl" value="${orgInfo.orgBindUserUrl}" autocomplete="off" placeholder="请输入绑定用户地址" style="width: 600px;" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">机构排序：</label>
                        <span  class="span-required">*</span>
                        <span  class="span-hint">&nbsp;列表显示顺序</span>
                        <div class="col-md-2">
                            <input type="number" name="top" class="form-control" value="${orgInfo.top}" autocomplete="off" placeholder="机构列表显示顺序" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8 fill">
                    <div class="form-group">
                        <label class="control-label col-md-3">注册资本：</label>
                        <span  class="span-required">*</span>
                        <span  class="span-unit">万元</span>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="capital" value="${orgInfo.capital}" autocomplete="off" placeholder="请输入机构注册资本" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">上线时间：</label>
                        <span  class="span-required">*</span>
                        <div class="col-md-5">
                            <input type="text" name="upTime" id="upTime" value="<fmt:formatDate value="${orgInfo.upTime}" pattern="yyyy-MM-dd" />" class="form-control" autocomplete="off" placeholder="请输入机构上线时间" readonly="readonly" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">所在城市：</label>
                        <span  class="span-required">*</span>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="city" value="${orgInfo.city}" autocomplete="off" placeholder="请输入机构所在城市" required="required"/>
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">ICP备案：</label>
                        <span  class="span-required">*</span>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="icpFiling" value="${orgInfo.icpFiling}" autocomplete="off" placeholder="请输入机构icp备案" required="required"/>
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">客服电话：</label>
                        <span  class="span-required">*</span>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="contact" value="${orgInfo.contact}" autocomplete="off" placeholder="请输入机构联系电话" required="required"/>
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                      <div class="form-group">
                          <label class="control-label col-md-3">机构佣金率：</label>
                          <span  class="span-unit">%</span>
                          <span  class="span-hint">&nbsp;用户计算返现，请谨慎修改(新版本请调整返现策略)</span>
                          <div class="col-md-2">
                               <input type="text" name="orgFeeRatio" class="form-control" value="${orgInfo.orgFeeRatio}" autocomplete="off" placeholder="机构所属产品给理财师返佣比例" />
                          </div>
                      </div>
                </div>

                <div class="col-md-8">
                      <div class="form-group">
                          <label class="control-label col-md-3">安全评级：</label>
                          <span  class="span-required">*</span>
                          <div class="col-md-4">
                               <select id="security_grade" name="grade" class="form-control" style="width: 150px;display: inline-block;" required="required">
                                    <option value="">请选择安全评级</option>
                                    <option value="6" <c:if test="${orgInfo.grade == 6}">selected="selected"</c:if>>AAA</option>
                                    <option value="5" <c:if test="${orgInfo.grade == 5}">selected="selected"</c:if>>AA</option>
                                    <option value="4" <c:if test="${orgInfo.grade == 4}">selected="selected"</c:if>>A</option>
                                    <option value="3" <c:if test="${orgInfo.grade == 3}">selected="selected"</c:if>>BBB</option>
                                    <option value="2" <c:if test="${orgInfo.grade == 2}">selected="selected"</c:if>>BB</option>
                                    <option value="1" <c:if test="${orgInfo.grade == 1}">selected="selected"</c:if>>B</option>
                                </select>
                          </div>
                      </div>
                </div>

                <div class="col-md-8 fill">
                      <div class="form-group">
                          <label class="control-label col-md-3">机构亮点：</label>
                          <span  class="span-required">*</span>
                          <span  class="span-hint">有多个以英文逗号分隔(例：1,2)</span>
                          <div class="col-md-5">
                               <input type="text" class="form-control" name="orgAdvantage" value="${orgInfo.orgAdvantage}" autocomplete="off" placeholder="请输入机构亮点介绍" required="required" />
                          </div>
                      </div>
                </div>

                <div class="col-md-8 fill">
                    <div class="form-group">
                        <label class="control-label col-md-3">机构自定义标签：</label>
                        <span  class="span-required">*</span>
                        <span  class="span-hint">有多个以英文逗号分隔(例：1,2)</span>
                        <div class="col-md-5">
                            <input type="text" class="form-control" name="orgTag" value="${orgInfo.orgTag}" autocomplete="off" placeholder="请输入机构机构自定义标签" required="required" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">最小年化：</label>
                        <span  class="span-unit">%</span>
                        <span  class="span-hint">&nbsp;机构产品最小年化收益</span>
                        <div class="col-md-2">
                            <input type="text" name="minProfit" class="form-control" value="${orgInfo.minProfit}" autocomplete="off" placeholder="机构产品最小年化收益" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">最大年化：</label>
                        <span  class="span-unit">%</span>
                        <span  class="span-hint">&nbsp;机构产品最大年化收益</span>
                        <div class="col-md-2">
                            <input type="text" name="maxProfit" class="form-control" value="${orgInfo.maxProfit}" autocomplete="off" placeholder="机构产品最大年化收益" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8 fill">
                    <div class="form-group">
                        <label class="control-label col-md-3">机构跳转地址：</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="orgUrl" value="${orgInfo.orgUrl}" autocomplete="off" placeholder="请输入机构跳转地址" style="width: 600px;" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8 fill">
                    <div class="form-group">
                        <label class="control-label col-md-3">PC端机构跳转地址：</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="pcOrgJumpUrl" value="${orgInfo.pcOrgJumpUrl}" autocomplete="off" placeholder="请输入PC端机构跳转地址" style="width: 600px;" />
                        </div>
                    </div>
                </div>

                <!-- 上传图片 -->
                <div class="col-md-8 fill">
                    <div class="form-group">
                        <label class="control-label col-md-3">移动端-机构显示Logo：</label>
                        <span  class="span-required">*</span>
                        <span  class="span-hint">132*132 px,透明背景</span>
                        <div class="col-md-6">
                            <input class="uploadImg"  type="file" id="platformIco" data-md5="${orgInfo.platformIco}" />
                            <!-- 上传成功存入隐藏域中 -->
                            <input name="platformIco" type="hidden" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">

                    </div>
                </div>

                <!-- 上传图片 -->
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">移动端-产品列表中机构Logo：</label>
                        <span  class="span-required">*</span>
                        <span  class="span-hint">198*60 px,透明背景</span>
                        <div class="col-md-6">
                            <input class="uploadImg"  type="file" id="platformlistIco" data-md5="${orgInfo.platformlistIco}" />
                            <!-- 上传成功存入隐藏域中 -->
                            <input name="platformlistIco" type="hidden" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                    </div>
                </div>

                <!-- 上传图片 -->
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label col-md-3">风控简报图片：</label>
                        <span  class="span-required">*</span>
                        <span  class="span-hint">198*60 px,透明背景</span>
                        <div class="col-md-6">
                            <input class="uploadImg"  type="file" id="riskManagementImg" data-md5="${orgInfo.riskManagementImg}" />
                            <!-- 上传成功存入隐藏域中 -->
                            <input name="riskManagementImg" type="hidden" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">
                    </div>
                </div>

                <div class="col-md-8 fill">
                    <div class="form-group">
                        <label class="control-label col-md-3">奖励规则：</label>
                        <div class="col-md-5">
                            <textarea id="rewardRule" class="ueditorPlug" name="rewardRule" style="width: 800px;">${orgInfo.rewardRule}</textarea>
                        </div>
                    </div>
                </div>

                <div class="col-md-8 fill">
                    <div class="form-group">
                        <label class="control-label col-md-3">分享图标：</label>
                        <span  class="span-hint"></span>
                        <div class="col-md-6">
                            <input class="uploadImg"  type="file" id="shareIcon" data-md5="${orgInfo.shareIcon}" />
                            <!-- 上传成功存入隐藏域中 -->
                            <input name="shareIcon" type="hidden" />
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="form-group">

                    </div>
                </div>
	                  
                <div class="col-md-8 fill">
                      <div class="form-group">
                          <label class="control-label col-md-3">分享标题：</label>
                          <div class="col-md-9">
                               <input type="text" class="form-control" name="shareTitle" value="${orgInfo.shareTitle}" autocomplete="off"  style="width: 600px;" />
                          </div>
                      </div>
                </div>
	                  
                <div class="col-md-8 fill">
                      <div class="form-group">
                          <label class="control-label col-md-3">分享链接：</label>
                          <div class="col-md-9">
                               <input type="text" class="form-control" name="shareLink" value="${orgInfo.shareLink}" autocomplete="off"  style="width: 600px;" />
                          </div>
                      </div>
                </div>
	                  
                <div class="col-md-8 fill">
                      <div class="form-group">
                          <label class="control-label col-md-3">分享描述：</label>
                          <div class="col-md-9">
                               <input type="text" class="form-control" name="shareDesc" value="${orgInfo.shareDesc}" autocomplete="off"  style="width: 600px;" />
                          </div>
                      </div>
                </div>
			 </div> <!--小屏幕宽度限制-->
			
		</div>

        <div class="row">
            <div class="page-header">
                <h4><strong>平台介绍-</strong>(含<span class="span-required">*</span>标示为必填项)</h4>
            </div>
            <div class="col-sm-10"><!-- 小屏幕  ≥768px-->
                <div class="col-md-8 fill">
                    <div class="form-group">
                        <label class="control-label col-md-3">机构简介<span style="color: red;">*</span>：</label>
                        <div class="col-md-5">
                            <textarea name="orgProfile" style="width: 600px;height: 100px;">${orgInfo.orgProfile}</textarea>
                        </div>
                    </div>
                </div>
            </div> <!--小屏幕宽度限制-->

        </div>
        <br/>
        <div class="row" style="width: 100%;">
            <div>
                <div class="form-group">
                    <label class="control-label col-md-3">移动端-办公环境：</label>
                    <span  class="span-hint">支持多图上传</span>
                    <div>
                        <input id="orgEnvironmentPicture" type="file" multiple class="uploadMultipleImg" data-md5="${orgEnvironments}" />
                        <!-- 上传成功存入隐藏域中 -->
                        <input name="orgEnvironmentPicture" type="hidden" />
                    </div>
                </div>
            </div>

            <div>
                <div class="form-group">
                </div>
            </div>
        </div>

        <div class="row" style="width: 100%;">
            <div>
                <div class="form-group">
                    <label class="control-label col-md-3">移动端-营业执照及其他资格证：</label>
                    <span  class="span-hint">支持多图上传</span>
                    <div>
                        <input id="orgPaperPicture" type="file" multiple class="uploadMultipleImg" data-md5="${orgPapers}" />
                        <!-- 上传成功存入隐藏域中 -->
                        <input name="orgPaperPicture" type="hidden" />
                    </div>
                </div>
            </div>

            <div>
                <div class="form-group">
                </div>
            </div>
        </div>

        <div class="row" id="investStrategyInfo">
            <div class="page-header">
                <h4><strong>投资攻略</strong></h4>
                <span  style="color: blue;font-size: 15px;">注意：只能推荐一个投资攻略，用于平台列表显示。</span>
                <label class="control-label col-md-2"></label>
            </div>
            <div class="col-sm-12" id="investStrategyList">
                <c:forEach var="investStrategy" items="${orgInfo.investStrategys}" varStatus="idx">
                    <div class="form-group" id="investStrategy-${idx.count}">
                        <div class="row">
                            <label class="control-label col-md-3">投资攻略：</label>
                            <div id="investStrategy-errors-${idx.count}" class="center-block" style="width:800px;display:none"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-8" style="">
                                <input type="hidden" name="investStrategys_investStrategy${idx.count}.id" value="${investStrategy.id}" /><!-- 机构攻略主键id -->

                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-4">起投期限：</label>
                                    <input type="number" min="1" style="width: 60% !important;" class="form-control col-md-8" name="investStrategys_investStrategy${idx.count}.minDeadLine" value="${investStrategy.minDeadLine}" autocomplete="off" placeholder="请输入起投期限(天)" required />
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-4">起投金额：</label>
                                    <input type="number" min="0" style="width: 60% !important;" class="form-control col-md-8" name="investStrategys_investStrategy${idx.count}.minInvestAmount" value="${investStrategy.minInvestAmount}" autocomplete="off" placeholder="请输入起投金额(元)" required />
                                </div>

                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-4">官方红包：</label>
                                    <input type="number" min="0" style="width: 60% !important;" class="form-control col-md-8" name="investStrategys_investStrategy${idx.count}.orgRedpacket" value="${investStrategy.orgRedpacket}" autocomplete="off" placeholder="请输入官方红包(元)" required />
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-4">理财利率：</label>
                                    <input type="number" min="0" style="width: 60% !important;" class="form-control col-md-8" name="investStrategys_investStrategy${idx.count}.orgProductRatio" value="${investStrategy.orgProductRatio}" autocomplete="off" placeholder="请输入理财收益率(%)" required /> &nbsp;&nbsp;%
                                </div>

                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-4">显示排序：</label>
                                    <input type="number" style="width: 60% !important;" class="form-control col-md-8" name="investStrategys_investStrategy${idx.count}.strategyIndex" value="${investStrategy.strategyIndex}" autocomplete="off" placeholder="排序序号" required />
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label col-md-4">是否推荐：</label>
                                    <div class="col-md-8" style="padding-left: 0 !important;">
                                        <select id="recommend" name="investStrategys_investStrategy${idx.count}.recommend" class="form-control" style="width: 100% !important;display: inline-block;" required="required">
                                            <option value="0" <c:if test="${investStrategy.recommend == 0}">selected="selected"</c:if>>否</option>
                                            <option value="1" <c:if test="${investStrategy.recommend == 1}">selected="selected"</c:if>>是</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4" style="">
                                <div class="row">
                                    <div class="col-md-4" style="">
                                        <!-- return false 阻止表单提交 -->
                                        <button type="button" class="btn btn-default btn-danger" onclick="deleteInvestStrategy(this);" data-investStrategyRowId="investStrategy-${idx.count}" data-investStrategyId="${investStrategy.id}" data-orgNumber="${investStrategy.orgNumber}" ><i class="fa fa-trash-o"></i> 删除</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>

            <div class="row" id="addInvestStrategyInfo">
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-md-4"></label>
                        <div class="col-md-8">
                            <!-- return false 阻止表单提交 -->
                            <button type="button" class="btn btn-default" onclick="addInvestStrategy(this);"><i class="fa fa-plus"></i> 新增投资攻略</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" id="teamInfo">
            <div class="page-header">
                <h4><strong>团队信息</strong></h4>
                <span  style="color: blue;font-size: 15px;">注意：团队成员头像建议不小于200*200的半身照。</span>
                <label class="control-label col-md-2"></label>
            </div>
            <div class="col-sm-12" id="teamList">
                <c:forEach var="team" items="${orgInfo.teams}" varStatus="idx">
                    <div class="form-group" id="team-${idx.count}">
                        <div class="row">
                            <label class="control-label col-md-3">团队成员：</label>
                            <input type="hidden" name="teams_team${idx.count}.id" value="${team.id}"/><!-- 团队成员主键id -->
                            <div id="team-errors-${idx.count}" class="center-block" style="width:800px;display:none"></div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">
                                <div class="kv-avatar center-block" style="width:200px">
                                    <input type="file" class="uploadHead" data-md5="${team.orgHeadImg}?f=png" data-errors="team-errors-${idx.count}" data-validateerrors="team-head-img-${idx.count}" />
                                </div>
                                <div class="form-group" style="text-align: center;">
                                    <input id="team-head-img-${idx.count}" name="teams_team${idx.count}.orgHeadImg" type="hidden" value="${team.orgHeadImg}" required="required"/>
                                </div>
                            </div>

                            <div class="col-md-8" style="margin:40px 0px 0px 50px;">
                                <div class="row">
                                    <div class="form-group col-md-4">
                                        <input type="text" class="form-control" name="teams_team${idx.count}.orgMemberName" value="${team.orgMemberName}" autocomplete="off" placeholder="请输入成员姓名" required />
                                    </div>
                                    <div class="form-group col-md-4">
                                        <input type="text" class="form-control" name="teams_team${idx.count}.orgMemberGrade" value="${team.orgMemberGrade}" autocomplete="off" placeholder="请输入成员职位" required />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-md-8">
                                        <textarea name="teams_team${idx.count}.orgDescribe" style="width: 600px;height: 100px;" placeholder="个人简介(建议300字以内)" required>${team.orgDescribe}</textarea>
                                    </div>
                                    <div class="col-md-4" style="padding:20px 0px 0px 130px">
                                        <!-- return false 阻止表单提交 -->
                                        <button type="button" class="btn btn-default btn-danger" onclick="deleteTeam(this);" data-teamrowid="team-${idx.count}" data-teamid="${team.id}" data-orgNumber="${orgInfo.orgNumber}"><i class="fa fa-trash-o"></i> 删除</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>

            <div class="row" id="addTeamInfo">
                <div class="col-md-12">
                    <div class="form-group">
                        <label class="col-md-4"></label>
                        <div class="col-md-8">
                            <!-- return false 阻止表单提交 -->
                            <button type="button" class="btn btn-default" onclick="addTeam(this);"><i class="fa fa-plus"></i> 新增成员</button>
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
	                               <button type="button" class="btn btn-default" onclick='javascript:$.switchPage("合作机构列表","rest/cim/cimorginfoa/list");'><i class="fa fa-arrow-left"></i> 返回</button>
	                          </div>
	                      </div>
	             </div>
	    </div>              	
	
	</form>	
	
</div>

<script type="text/javascript" src="app/cimorginfounrecord/multipleimgupload.js"></script>
<script type="text/javascript" src="app/cimorginfounrecord/ueditor-plug.js"></script>
<script type="text/javascript" src="app/cimorginfounrecord/imgupload.js"></script>
<script type="text/javascript">
	/*延迟1秒加载图像初始化插件*/
	setTimeout(function(){
		$("<script>").attr({type:"text/javascript",src:"app/cimorginfounrecord/headupload.js"}).appendTo("body");
	},1000);
</script>
<script type="text/javascript" src="app/cimorginfounrecord/formdata-convert-tojson.js"></script>
<script type="text/javascript" src="app/cimorginfounrecord/cimorginfo-edit.js"></script>