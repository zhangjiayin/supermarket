<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 request.setAttribute("ctx", request.getContextPath());
%>

	<input id="path" type="hidden" value="${ctx}" />
	<input id="mobile" type="hidden" value="${dtl.customermobile}" />

<div>

<table style="width: 100%;line-height: 30px;">
<tbody>
<tr>
<td align="right">姓名：</td>
<td> ${dtl.customername}</td>
</tr>

<tr>
<td align="right">注册时间：</td>
<td><fmt:formatDate value="${dtl.regtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
</tr>

<tr>
<td align="right">电话：</td>
<td>${dtl.customermobile}</td>
</tr>

<tr>
<td align="right">新密码：</td>
<td><input type="text" size="20" name="newpwd" id="newpwd"></td>
</tr>

<tr>
<td align="right">确认密码：</td>
<td><input type="text" size="20" name="newPwdSure" id="newPwdSure"></td>
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
using(["messager"],function(){
	$('#btnSure').click(function() {
		$.messager.confirm('提示', '确定重置密码？'	, function(r) {
			if (r) {
				var validated = true;
				var newpwd = $("#newpwd").val();
				if(newpwd.length<6 || newpwd.length>20){
					validated = false;
					$.messager.alert('提示', '密码长度6-20！', 'info');
				}
				if(!vPass(newpwd)){
					validated = false;
					$.messager.alert('提示', '密码必须由数据、字母、特殊符号组成！', 'info');
				}
				
				if($("#newpwd").val() != $("#newPwdSure").val()){
					validated = false;
					$.messager.alert('提示', '两次密码输入不一致！', 'info');
				}
				
				if(validated){
					$.messager.progress({
						title : '请稍后',
						msg : '玩命加载中...'
					}); 
					
				$.ajax({
					type : 'post',
					url : 'rest/invest/resetpwd',
					data : 'mobile=' + $("#mobile").val()+'&newPwd='+$("#newPwdSure").val(),
					dataType : 'json',
					success : function(result) {
						console.info(result);
						$.messager.progress('close');
						if (result.returnCode = 0) {
							$.messager.alert('提示', result.returnMsg, 'info');
						} else {
							$.messager.alert('提示', result.returnMsg, 'info');
						}
						//$('#dg').datagrid('reload');
						var $window = self.parent.$("#win").window();
						$window.window('close');
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						$.messager.alert('提示', '服务异常！', 'error');
					}
				});
				return false;
				}
			}
		});
	});


});


	


</script>
