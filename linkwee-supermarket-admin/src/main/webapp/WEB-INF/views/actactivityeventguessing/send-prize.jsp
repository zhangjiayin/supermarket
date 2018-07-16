<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <input type="hidden" id="guessId" name="guessId" value="${guessId}">
    <div class="row">
		<label class="col-sm-2 control-label">获胜方:</label>
		<div class="col-sm-2">
            <b>${winner}</b>
		</div>
	</div>
	<br>
	<div class="row">
		<label class="col-sm-2 control-label">实际投票数:</label>
		<div class="col-sm-2">
            <b>${voteTotal}</b>
		</div>
	</div>
	<br>
	<div class="row">
		<label class="col-sm-2 control-label">实际发放金额:</label>
		<div class="col-sm-2">
            <b>${prizeAmount}</b>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-sm-2 col-sm-offset-4">
			<a class="btn btn-danger J_sendPrize"  role="button" >确认发放</a>&nbsp;&nbsp;&nbsp;
			<a class="btn btn-default" data-dismiss="modal"  role="button" >关闭</a>
		</div>
	</div>
</div>
<script type="text/javascript">

$(document).ready(function() {

 	$(".J_sendPrize").click(function() {
 		var guessId = $("#guessId").val();
 		if(!guessId){
 			layer.msg('竞猜ID不能为空',{time: 1000,icon: 0});
 			return false;
 		}
        bootbox.confirm("确认发放本场竞猜的奖励？确认后直接发放到猎财余额！",function (result) {
            if(result){
                $.post("rest/act/actactivityeventguessing/sendPrize",{guessId:guessId},function (result) {
                    if(result.isFlag){
                        bootbox.alert("发放成功",function () {
                            $modal.modal('hide');
                            changeScoreTable.draw();
                        });
                    }
                    else{
                        bootbox.alert(result.msg);
                    }
                });
            }
        });
 	});

});
</script>


