<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" >  window['rproductIds']  = [];</script>
<div class="container">
<c:if test="${!empty errorMgs }">
				<div class="row">
					<div class="col-md-8">
						<h4><small><p class="text-muted">errorMgs</p></small></h4>
					</div>
				</div>
</c:if>

<c:if test="${empty errorMgs }">



<form id="addFeeCouponFrom">	
	<input type="hidden" name="couponId" value="${couponId}">
			
	<div class="page-header">
		<div class="row">
			<div class="col-md-8">
				<h4><small><p class="text-muted">加拥券基本信息</p></small></h4>
			</div>
		</div>
	</div>
	<br>
	<div class="row">
		<label class="col-sm-2 control-label">加拥券类型:</label>
		<div class="col-md-4">
			<select name="type">
				<option <c:if test="${addFeeCoupon.type==1}">selected="selected"</c:if> value="1">加拥券</option>
				<option <c:if test="${addFeeCoupon.type==2}">selected="selected"</c:if> value="2">奖励券</option>
			</select>
		</div>
	</div>
	<br>
	<div class="row">
		<label class="col-sm-2 control-label">加拥券来源:</label>
		<div class="col-md-4">
			  <input type="text" class="form-control" name="source" value="${addFeeCoupon.source}"  autocomplete="off" placeholder="加拥券来源" />
		</div>
	</div>
	<br>
	<div class="row">
		<label class="col-sm-2 control-label">加佣（%）:</label>
		<div class="col-md-4">
			 <input type="text" class="form-control" name="rate" value="${addFeeCoupon.rate}" autocomplete="off" placeholder="加拥比率" />
		</div>
	</div>
	<br>
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
			 <select name="platformLimit" class="form-control">
			 	<option <c:if test="${addFeeCoupon.platformLimit==0}">selected="selected"</c:if> value="0">不限</option>
			 	<option <c:if test="${addFeeCoupon.platformLimit==1}">selected="selected"</c:if> value="1">限制</option>
			 </select>
		</div>
		<div class="col-md-2">
			 <select class="form-control" id="platformId" name="platformId"> 
			 	<option value="">选择平台</option>
			 	<c:forEach var="platform" items="${platformList}" >
							<option value="${platform.orgNumber}" <c:if test="${addFeeCoupon.platformId==platform.orgNumber }">selected="selected"</c:if> >${platform.name}</option>
					</c:forEach>
			 </select>
		</div>
	</div>
	<br>
	<div class="row">
		<label class="col-sm-2 control-label">投资形式:</label>
		<div class="col-md-2">
			 <select name="investLimit" class="form-control">
			 	<option <c:if test="${addFeeCoupon.investLimit==0}">selected="selected"</c:if> value="0">不限</option>
			 	<option <c:if test="${addFeeCoupon.investLimit==1}">selected="selected"</c:if> value="1">用户首投</option>
			 	<option <c:if test="${addFeeCoupon.investLimit==2}">selected="selected"</c:if> value="2">平台首投</option>
			 </select>
		</div>
	</div>
	<br>
	<div class="form-group">
		<label>使用时间</label>
		<div class="row">
			<div class="col-sm-6">
				<input id="addFeeStartDate" name="validBeginTime" value="${addFeeCoupon.validBeginTime}" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'addFeeEndDate\')}'})"  style="width:150px" />
			</div>
		</div>
	</div>
	<br>
	<div class="form-group">
		<label>过期时间</label>
		<div class="row">
			<div class="col-sm-6">
				<input id="addFeeEndDate" name="validEndTime" value="${addFeeCoupon.validEndTime}" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'addFeeStartDate\')}'})"  style="width:150px" />
			</div>
		</div>
	</div>
	<br>
	<div class="row">
		 <div class="col-md-2 col-md-offset-4">
		 	<a class="btn btn-danger active J_updateAddFeeCoupon"  role="button"  >更新</a>
		 	&nbsp;&nbsp;&nbsp;&nbsp;
		 	<a class="btn btn-default active J_goback" role="button"  >返回</a>
		 </div>
	</div>
</form>
</c:if>
</div>
<c:if test="${empty errorMgs }">
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" >

$(document).ready(function() {
    $(".J_updateAddFeeCoupon").on("click", function(event) {
    	
    	var data =milo.jsonFromt($("form").serializeArray());
    	if(!data.source){
             layer.msg('加拥券来源不能为空',{time: 1000,icon: 0});
             return false;
    	}
    	if( !$.isNumeric(data.rate)){
          layer.msg('加拥券加拥比率必须为数值',{time: 1000,icon: 0});
          return false;
      	}
        if(data.limitPlatform==1){
          if(!data.platformId){
               layer.msg('平台不能为空',{time: 1000,icon: 0});
              return false;
          }
      	}
        if(!data.validBeginTime){
            layer.msg('加拥券使用时间不能为空',{time: 1000,icon: 0});
            return false;
   		}
        if(!data.validEndTime){
            layer.msg('加拥券过期时间不能为空',{time: 1000,icon: 0});
            return false;
   		}
    	$.post('rest/act/actaddfeecoupon/edit', data, function(data, textStatus, xhr) {
            layer.msg(data.msg,{time: 1000,icon: data.isFlag?1:0});
            if( data.isFlag)$.switchPage("加拥券管理","rest/act/actaddfeecoupon/initPage"); //跳到红包列表页面
        });
        return false;
    });

    $(".J_goback").on("click", function(event) {
        $.switchPage("加拥券管理","rest/act/actaddfeecoupon/initPage"); 
        return false;
    });

} );
</script>
</c:if>