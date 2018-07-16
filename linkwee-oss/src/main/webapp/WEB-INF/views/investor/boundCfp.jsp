<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 request.setAttribute("ctx", request.getContextPath());
%>

	<input id="path" type="hidden" value="${ctx}" />
<form id="DateForm">
<div>

<table style="width: 100%;line-height: 30px;">
<tbody>
<c:if test="${usercustomerrel !=null }">
<tr>
<td align="right">客户：</td>
<td> ${usercustomerrel.customermobile}<c:if test="${ !empty usercustomerrel.customername }">(${usercustomerrel.customername})</c:if></td>
</tr>
<input  type="hidden" id="customermobile" name ="customermobile"  value="${usercustomerrel.customermobile}" />
</c:if>

<c:if test="${empty usercustomerrel }">
<tr>
<td align="right">客户：</td>
<td><input  type="text" id="customermobile" name ="customermobile"  value="" /> </td>
</tr>
</c:if>



<tr>
<td align="right">绑定新理财师：</td>
<td><input type="text" size="20" id="currsaleuser" name ="currsaleuser"></td>
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
</form>
<script type="text/javascript" >
$('#btnCancel').click(function() {
	var $window = self.parent.$("#investorWin").window();
	$window.window('close');
});
using(["messager","datagrid","common"],function(){
	$('#btnSure').click(function() {

		if($("#currsaleuser").val() == ""){
			$.messager.alert('提示', '理财师手机号码不能为空！', 'warning');
		    return;
		}
		var phone = /^1([38]\d|4[57]|5[0-35-9]|7[06-8]|8[89])\d{8}$/;
		if(!phone.test($("#currsaleuser").val())){
			$.messager.alert('提示', '新绑定理财师手机号码录入错误！', 'warning');
		    return;
		}
		$.messager.confirm('提示', '确定重新绑定'	, function(r) {
			if (r) {
				$.messager.progress({
					title : '请稍后',
					msg : '玩命加载中...'
				});
				
				var currsaleuser = $("#currsaleuser").val();
				
				$.ajax({
					type : 'post',
					url : $('#path').val() + '/rest/customerfix/bound',
					data : jsonFromt($('#DateForm').serializeArray()),
					dataType : 'json',
					success : function(result) {
						$.messager.progress('close');
						if (result.isFlag) {
							$.messager.alert('提示', result.msg, 'info');
							//关闭模式窗口 刷新列表
							var $window = self.parent.$("#investorWin").window();
							$window.window('refresh',$('#path').val() + '/invest/investdtl.htm?mobile=${usercustomerrel.customermobile}');
							//self.parent.$("#investorWin").window('close');
							self.parent.$('#dg').datagrid('reload');
							
						} else {
							$.messager.alert('提示', result.msg, 'error');
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						$.messager.alert('提示', '服务异常！', 'error');
					}
				});
			}
		});
	});

});

</script>
