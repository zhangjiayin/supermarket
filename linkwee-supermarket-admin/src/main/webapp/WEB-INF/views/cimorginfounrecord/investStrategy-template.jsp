<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="form-group" id="investStrategy-${param.strategyindex}">
	<div class="row">
		<label class="control-label col-md-3">投资攻略：</label>
		<div id="investStrategy-errors-${param.strategyindex}" class="center-block" style="width:800px;display:none"></div>
    </div>
    <div class="row">
        <div class="col-md-8" style="">

            <div class="form-group col-md-6">
                <label class="control-label col-md-4">起投期限：</label>
                <input type="number" min="1" style="width: 60% !important;" class="form-control col-md-8" name="investStrategys_investStrategy${param.strategyindex}.minDeadLine" autocomplete="off" placeholder="请输入起投期限(天)" required />
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-4">起投金额：</label>
                <input type="number" min="0" style="width: 60% !important;" class="form-control col-md-8" name="investStrategys_investStrategy${param.strategyindex}.minInvestAmount" autocomplete="off" placeholder="请输入起投金额(元)" required />
            </div>

            <div class="form-group col-md-6">
                <label class="control-label col-md-4">官方红包：</label>
                <input type="number" min="0" style="width: 60% !important;" class="form-control col-md-8" name="investStrategys_investStrategy${param.strategyindex}.orgRedpacket" autocomplete="off" placeholder="请输入官方红包(元)" required />
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-4">理财利率：</label>
                <input type="number" min="0" style="width: 60% !important;" class="form-control col-md-8" name="investStrategys_investStrategy${param.strategyindex}.orgProductRatio" autocomplete="off" placeholder="请输入理财收益率(%)" required /> &nbsp;&nbsp;%
            </div>

            <div class="form-group col-md-6">
                <label class="control-label col-md-4">显示排序：</label>
                <input type="number" style="width: 60% !important;" class="form-control col-md-8" name="investStrategys_investStrategy${param.strategyindex}.strategyIndex" autocomplete="off" placeholder="排序序号" required />
            </div>
            <div class="form-group col-md-6">
                <label class="control-label col-md-4">是否推荐：</label>
                <div class="col-md-8" style="padding-left: 0 !important;">
                    <select id="recommend" name="investStrategys_investStrategy${param.strategyindex}.recommend" class="form-control" style="width: 100% !important;display: inline-block;" required="required">
                        <option value="0">否</option>
                        <option value="1">是</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="col-md-4" style="">
            <div class="row">
                <div class="col-md-4" style="">
                    <!-- return false 阻止表单提交 -->
                    <button type="button" class="btn btn-default btn-danger" onclick="deleteInvestStrategy(this);" data-investStrategyRowId="investStrategy-${param.strategyindex}"><i class="fa fa-trash-o"></i> 删除</button>
                </div>
            </div>
        </div>
    </div>
</div>