<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="utf-8"%>

<input id="mobile" type="hidden" value="${saleUserName}" />
<input id="oldParentMobile" type="hidden" value="${parentName}" />

<div>
<p>提示</p>
<br/>
<br/>
<br/>
<p align="center">该操作将使理财师${saleUserName}与上级${parentName}解除关系，请选择解绑后理财师归属</p>
<table style="width: 100%;line-height: 30px;">
<tbody>

<tr></tr>

<tr>
<td align="right"><input type="radio" name="changeType"   value="1" checked="checked" />指定新上级</td>
<td><input type="text" name="parentMobile" maxlength="11" id='parentMobile' /></td>
</tr>

<tr></tr>

<tr>
<td align="right"><input type="radio" name="changeType" value="2"/>变为一级理财师</td>
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
	var $window = self.parent.$("#win").window();
	$window.window('close');
});
var infoString = '';
using(["messager"],function(){
	$('#btnSure').click(function() {
		var newpwd = $("#parentMobile").val();
		if($("input[name='changeType']:checked").val() == 1){
			if(newpwd.length != 11){
				validated = false;
				$.messager.alert('提示', '电话号码必须为11位！', 'info');
				return;
			}
			infoString = '客户'+ $("#mobile").val()+ '将绑定为'+ $("#parentMobile").val()+ '的客户，确认操作吗？';
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
					url :  'rest/lcsList/changeParent',
					data : 'mobile=' + '${saleUserName}'+'&parentMobile='+$("#parentMobile").val()+'&changeType='+$("input[name='changeType']:checked").val(),
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
						var $window = self.parent.$("#win").window();
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
