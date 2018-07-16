<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <form id="changeScoreForm">
    <input type="hidden" id="guessId" name="guessId" value="${eventGuess.id}">
    <div class="row">
		<label class="col-sm-2 control-label">下一场开赛时间:</label>
		<div class="col-sm-2">
			<input id="nextStartTime" placeholder="${eventGuess.nextStartTime}" name="nextStartTime" class="form-control" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d}'})">
		</div>
	</div>
	<br>
	<div class="row">
		<label class="col-sm-2 control-label">A方(${eventGuess.competitionPartyA})得分:</label>
		<div class="col-sm-2">
			<input id="scoreA" placeholder="${eventGuess.scoreA}" name="scoreA" class="form-control" type="text" >
		</div>
	</div>
	<br>
	<div class="row">
		<label class="col-sm-2 control-label">B方(${eventGuess.competitionPartyB})得分:</label>
		<div class="col-sm-2">
			<input id="scoreB" placeholder="${eventGuess.scoreB}" name="scoreB" class="form-control" type="text" >
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-sm-2 col-sm-offset-4">
			<a class="btn btn-danger J_changeScore"  role="button" >确认修改</a>&nbsp;&nbsp;&nbsp;
			<a class="btn btn-default" data-dismiss="modal"  role="button" >关闭</a>
		</div>
	</div>
    </form>
</div>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

$(document).ready(function() {

 	$(".J_changeScore").click(function() {
 		var nextStartTime = $("#nextStartTime").val();
 		var scoreA = $("#scoreA").val();
        var scoreB = $("#scoreB").val();

 		if(!nextStartTime && (scoreA != 4 && scoreB != 4)){
 			layer.msg('竞猜结束前，下一场开赛时间不能为空',{time: 1000,icon: 0});
 			return false;
 		}
        if(scoreA<0 || scoreA>4){
            layer.msg('A方得分请输入[0-4]数字',{time: 1000,icon: 0});
            return false;
        }
        if(scoreB <0 || scoreB>4){
            layer.msg('B方得分请输入[0-4]数字',{time: 1000,icon: 0});
            return false;
        }
        var guessId = $("#guessId").val();
        bootbox.confirm("您将比分调整为：<b>"+"(${eventGuess.competitionPartyA})"+scoreA+":"+"(${eventGuess.competitionPartyB})"+scoreB+"</b>,点击确认后将立即生效！",function (result) {
            if(result){
                $.post("rest/act/actactivityeventguessing/changeScore",{guessId:guessId,nextStartTime:nextStartTime,scoreA:scoreA,scoreB:scoreB},function (result) {
                    if(result.isFlag){
                        bootbox.alert("操作成功",function () {
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


