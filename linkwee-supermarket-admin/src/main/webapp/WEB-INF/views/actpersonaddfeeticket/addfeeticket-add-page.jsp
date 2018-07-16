<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
<form id="addfeeTicketFrom">
			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">个人加拥券基本信息</p></small></h4>
					</div>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">加拥券类型:</label>
				<div class="col-md-4">
					<select name="type">
						<option value="1">个人加拥券</option>
					</select>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">加拥券名称:</label>
				<div class="col-md-4">
					  <input type="text" class="form-control" name="name" autocomplete="off" placeholder="加拥券名称" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">加拥（%）:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="rate" autocomplete="off" placeholder="加拥比率" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">加拥券描述:</label>
				<div class="col-md-4">
					 <input type="text" class="form-control" name="remark" autocomplete="off" placeholder="加拥券描述" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-10 col-sm-offset-2 control-label">
					<span style="color: red;font-size: 14px;">描述必须以英文逗号分隔,逗号之前为期限描述,之后为金额描述.如:'180天期以上,10000元起'</span>
				</label>
			</div>
			<div class="page-header">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">使用条件</p></small></h4>
					</div>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">适用平台:</label>
				<div class="col-md-2">
					 <select name="platformLimit" id="platformLimit" onchange ="changePlatformLimit()" class="form-control">
					 	<option value="0">不限</option>
					 	<option value="1">限制</option>
					 </select>
				</div>
				<div class="col-md-2">
					 <select class="form-control" id="platformLimitOrgNumber" name="platformLimitOrgNumber">
					 	<option value="all">选择平台</option>
					 	<c:forEach var="platform" items="${platformList}" >
									<option value="${platform.orgNumber}" >${platform.name}</option>
							</c:forEach>
					 </select>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">投资形式:</label>
				<div class="col-md-2">
					 <select name="investLimit" class="form-control">
					 	<option value="0">不限</option>
					 	<option value="1">用户首投</option>
					 	<option value="2">平台首投</option>
					 </select>
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">投资金额限制:</label>
				<div class="col-md-2">
					 <select name="amountLimit" id="amountLimit" onchange ="changeAmountLimit()" class="form-control">
					 	<option value="0">不限</option>
					 	<option value="1">大于</option>
					 	<option value="2">大于等于</option>
					 </select>
				</div>
				<div class="col-md-2">
					 <input type="text" class="form-control" name="amount" id="amount" autocomplete="off" placeholder="投资金额" />
				</div>

			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">投资期限:</label>
				<div class="col-md-2">
					 <select name="productLimit" id="productLimit" onchange ="changeProductLimit()" class="form-control">
						<option value="1000">不限</option>
					 	<option value="1002">等于</option>
					 	<option value="1003">大于等于</option>
					 </select>
				</div>
				<div class="col-md-2">
					<input type="text" class="form-control" name="productLimitDeadline" id="productLimitDeadline" autocomplete="off" placeholder="投资期限  (单位 ：天)" />
				</div>
			</div>
			<br>
			<div class="row">
				<label class="col-sm-2 control-label">加拥天数:</label>
				<div class="col-md-2">
					<select name="addFeeLimit" id="addFeeLimit" onchange ="changeAddFeeLimit()" class="form-control">
						<option value="0">不限</option>
						<option value="1">限制</option>
					</select>
				</div>
				<div class="col-md-2">
					<input type="text" id="addfeeDays" class="form-control" name="addFeeLimitDay" autocomplete="off" placeholder="加拥天数" />
				</div>
			</div>
			<br>
			<div class="row">
				 <div class="col-md-2 col-md-offset-4">
				 	<a class="btn btn-danger active J_addFeeTicket"  role="button"  >保存</a>
				 	&nbsp;&nbsp;&nbsp;&nbsp;
				 	<a class="btn btn-default active J_goback" role="button"  >返回</a>
				 </div>
			</div>
</form>
</div>

<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" >

$(document).ready(function() {
    $(".J_addFeeTicket").on("click", function(event) {
    	var data =milo.jsonFromt($("form").serializeArray());
    	if(!data.name){
             layer.msg('加拥券名称不能为空',{time: 1000,icon: 0});
             return false;
    	}
    	if( !$.isNumeric(data.rate) || data.rate>100 ){
          layer.msg('加拥比率必须为小于等于100的数值',{time: 1000,icon: 0});
          return false;
      	}
    	if(!data.remark){
             layer.msg('加拥券描述不能为空',{time: 1000,icon: 0});
            return false;
    	}
        if(data.platformLimit==1){
			  if(!data.platformLimitOrgNumber){
				   layer.msg('平台不能为空',{time: 1000,icon: 0});
				  return false;
			  }
		}
	  	if(data.amountLimit==1 || data.amountLimit==2){
			  if(!data.amount || !$.isNumeric(data.amount) || data.amount<1){
				  layer.msg('购买产品金额必须为大于等于1的数值',{time: 1000,icon: 0});
				  return false;
			  }
	  	}
		if(data.productLimit==1002 || data.productLimit==1003){
			if(!data.productLimitDeadline || !$.isNumeric(data.productLimitDeadline) || data.productLimitDeadline<=0){
				 layer.msg('产品期限必须为大于0的数值',{time: 1000,icon: 0});
				return false;
			}
		}
        if(data.addFeeLimit==1){
            if(!data.addFeeLimitDay || !$.isNumeric(data.addFeeLimitDay) || data.addFeeLimitDay<=0){
                layer.msg('加拥天数必须为大于0的数值',{time: 1000,icon: 0});
                return false;
            }
        }
    	$.post('rest/cim/actpersonaddfeeticket/add', data, function(data, textStatus, xhr) {
            layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
            if( data.isFlag)$.switchPage("个人加拥券管理","rest/cim/actpersonaddfeeticket/initPage"); //跳到红包列表页面
        });
        return false;
    });

    $(".J_goback").on("click", function(event) {
        $.switchPage("个人加拥券管理","rest/cim/actpersonaddfeeticket/initPage"); //跳到红包列表页面
        return false;
    });

});

function changeAddFeeLimit(){
    var addFeeLimit = document.getElementById("addFeeLimit");
    var isLimit = addFeeLimit.options[addFeeLimit.selectedIndex].value;
    if(isLimit == 0){
        $("#addfeeDays").val(0);
    }
}

function changeProductLimit(){
    var productLimit = document.getElementById("productLimit");
    var isLimit = productLimit.options[productLimit.selectedIndex].value;
    if(isLimit == 1000){
        $("#productLimitDeadline").val(0);
    }
}

function changeAmountLimit() {
    var amountLimit = document.getElementById("amountLimit");
    var isLimit = amountLimit.options[amountLimit.selectedIndex].value;
    if(isLimit == 0){
        $("#amount").val(0);
    }
}

function changePlatformLimit() {
    var platformLimit = document.getElementById("platformLimit");
    var isLimit = platformLimit.options[platformLimit.selectedIndex].value;
    if(isLimit == 0){
        document.getElementById("platformLimitOrgNumber").options[0].selected = true;
    }
}
</script>
