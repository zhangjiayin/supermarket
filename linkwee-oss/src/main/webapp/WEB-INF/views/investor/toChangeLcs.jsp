<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 request.setAttribute("ctx", request.getContextPath());
%>

	<input id="path" type="hidden" value="${ctx}" />
	<input id="mobile" type="hidden" value="${customerName}" />


<div>
<p>提示</p>
<br/>
<br/>
<br/>
<p align="center">
该操作将使理财师 ${lcsName} 与客户 ${customerName} 解除关系，请选择解绑后的客户归属
</p>
<table style="width: 100%;line-height: 30px;">
<tbody>

<tr></tr>

<tr>
<td align="right"><input type="radio" name="changeType"   value="1" checked="checked" />指定新理财师</td>
<td><input type="text" name="lcsMobile" maxlength="11" id='lcsMobile' /></td>
</tr>

<tr></tr>

<tr>
<td align="right"><input type="radio" name="changeType" value="2"/>变为自由用户</td>
</tr>

<tr>
<td align="center" colspan="2">
<input type="button" id="btnSure" value="确定"/>
<input type="button" id="btnCancel" value="取消" />
</td>
</tr>

</tbody>
</table>
</div>

<script type="text/javascript" src="app/common/util.js"></script>
<script type="text/javascript" >
$('#btnCancel').click(function() {
	var $window = self.parent.$("#investorWin").window();
	$window.window('close');
});
var infoString = '';
using(["messager"],function(){
	$('#btnSure').click(function() {
		var newpwd = $("#lcsMobile").val();
		if($("input[name='changeType']:checked").val() == 1){
			if(newpwd.length != 11){
				validated = false;
				$.messager.alert('提示', '电话号码必须为11位！', 'info');
				return;
			}
			infoString = '客户'+ $("#mobile").val()+ '将绑定为'+ $("#lcsMobile").val()+ '的客户，确认操作吗？';
		}else{
			infoString = '客户'+ $("#mobile").val()+ '将成为自由客户，确认操作吗？';
		}
		$.messager.confirm('提示', infoString, function(r) {
			if (r) {
				var validated = true;
				if(validated){
					$.messager.progress({
						title : '请稍后',
						msg : '玩命加载中...'
					}); 
				$.ajax({
					type : 'post',
					url :  'rest/customerfix/changeLcs',
					data : 'mobile=' + '${customerName}'+'&lcsMobile='+$("#lcsMobile").val()+'&changeType='+$("input[name='changeType']:checked").val(),
					dataType : 'json',
					success : function(result) {
						$.messager.progress('close');
						if (!result.isFlag) {
							if(result.msg){
								$.messager.alert('系统提示', result.msg, 'error');
							}else{
								$.messager.alert('系统提示', "操作失败!", 'error');
							}
						}
						var $window = self.parent.$("#investorWin").window();
						$window.window('close');
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						$.messager.alert('提示', '服务异常！', 'error');
					}
				});
				}
			}
		});
	});

});


</script>
