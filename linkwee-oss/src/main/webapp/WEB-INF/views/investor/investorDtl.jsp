<%@page import="org.springframework.context.annotation.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
 request.setAttribute("ctx", request.getContextPath());
%>

<input id="path" type="hidden" value="${ctx}" />
<input id="customerMobile" type="hidden" value="${dtl.customerMobile}" />
 <div>

<table style="width: 100%;line-height: 30px;">
<tbody>
<tr>
<td align="right">姓名：</td>
<td>
<c:if test="${ empty dtl.customerName}" ><font color='red'>未认证</font></c:if>
<c:if test="${ !empty dtl.customerName}" >${dtl.customerName}</c:if>
 </td>
</tr>

<tr>
<td align="right">注册时间：</td>
<td>${dtl.regTime}</td>
</tr>

<tr>
<td align="right">电话：</td>
<td>${dtl.customerMobile}</td>
</tr>

<tr>
<td align="right">身份证号码：</td>
<td>
<c:if test="${ empty dtl.centNum}" ><font color='red'>暂未绑定</font></c:if>
<c:if test="${! empty dtl.centNum}" >${fn:replace(dtl.centNum,fn:substring(dtl.centNum,fn:length(dtl.centNum)-4,fn:length(dtl.centNum)), '****')}</c:if>



</td>
</tr>

<tr>
<td align="right">银行卡：</td>
<td>
<c:if test="${ empty dtl.cardNo}" ><font color='red'>暂未绑定</font></c:if>
<c:if test="${! empty dtl.cardNo}" >${dtl.cardBankName}&nbsp;&nbsp;&nbsp;&nbsp;${dtl.cardNo}</c:if>
</td>
</tr>

<tr>
<td align="right">邀请人：</td>
<td>${dtl.investedNumber}</td>
</tr>
<tr>
<td align="right">注册来源：</td>
<td>
<c:if test="${dtl.regSource eq '0'}">领会金服</c:if>
<c:if test="${dtl.regSource eq '1'}">钱罐子</c:if>
<c:if test="${dtl.regSource eq '2'}">投筹</c:if>
<c:if test="${dtl.regSource eq '3'}">领会金服</c:if>
<c:if test="${dtl.regSource eq '4'}">领会金服</c:if>

</td>
</tr>
<tr>
<td align="right">归属理财师：</td>
<td>
<c:if test="${ empty dtl.currentSaleName}" ><font color='red'>-</font></c:if>
<c:if test="${ !empty dtl.currentSaleName}" >${dtl.currentSaleName}</c:if>

</td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="button" id="btnCancel" value="取&nbsp;&nbsp;&nbsp;&nbsp;消" onclick="closeWin()"/>
<c:if test="${  dtl.isCfp == 0 }">
<input type="button" id="btnUnbundling" value="解&nbsp;&nbsp;&nbsp;&nbsp;绑" />
</c:if>
<c:if test="${ empty dtl.currentSaleUser}">
<input type="button" id="btnbundling" value="绑&nbsp;&nbsp;&nbsp;&nbsp;定" />
</c:if>

</td>
</tr>
<tr>
<td colspan="2" align="center">
<font style="font-size: 12px;">变更历史</font>
</td>
</tr>

<tr>
<td colspan="2"  style="height: 300px;">
<table id="hislogDg" style="height: 300px;"></table>
</td>
</tr>

</tbody>
</table>
</div> 
<script type="text/javascript" >
function closeWin(){
	var $window = self.parent.$("#investorWin").window();
	$window.window('close');
}
$('#btnCancel').click(function() {
	closeWin();
});
using(["messager","datagrid"],function(){
	$('#btnUnbundling').click(function() {
		$.messager.confirm('提示', '确定解绑？'	, function(r) {
			if (r) {
				$.messager.progress({
					title : '请稍后',
					msg : '玩命加载中...'
				});
			
				$.ajax({
					type : 'post',
					url : 'rest/customerfix/unwrap',
					data : 'customerMobile=' + $("#customerMobile").val(),
					dataType : 'json',
					success : function(result) {
						$.messager.progress('close');
						if (result.isFlag) {
							$.messager.alert('提示', result.msg, 'info');
							//关闭模式窗口 刷新列表
							var $window = self.parent.$("#investorWin").window();
							$window.window('refresh','rest/invest/investdtl?mobile=${dtl.customerMobile}');
							//self.parent.$("#investorWin").window('close');
							self.parent.$('#dg').datagrid('reload');
						} else {
							$.messager.alert('提示', result.msg, 'error');
						}
						//closeWin();
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.progress('close');
						$.messager.alert('提示', '服务异常！', 'error');
					}
				});
			}
		});
	});
	//重新绑定
	$('#btnbundling').click(function() {
		var $window = self.parent.$("#investorWin").window();
		//$window.window('title','重新绑定理财师');
		$window.window('refresh','rest/customerfix/tobound?customerMobile=${dtl.customerMobile}');
		
		$window.window('open');
	});
	//变更历史
	var $datagrid = $('#hislogDg').datagrid({	
				url:"rest/customerfix/hisrec?customerMobile=${dtl.customerMobile}",
		        rownumbers: true,				
				fit:true,						
				columns:[[
								{
									field : 'optType',
									title : '关系变更记录',
									width : 200,
									align : 'left',
									formatter : function(value, row, index) {
										if (1 == value) {
											return '理财师回退业务员';
										} else if (2 == value) {
											return '业务员注册理财师';
										} else if (3 == value) {
											return "用户变更理财师";
										} else if (4 == value) {
											return "转换为自由理财师";
										} else if(5 == value){
											return "转换为新财富理财师";
										}else if(6 == value){
											return "解绑为自由客户";
										}else if(7 == value){
											return "新用户注册理财师";
										}else if(8 == value){
											return "理财师升级";
										}else if(9 == value){
											return "理财师等级调整";
										}else{
											return value;
										}
									}
								},{
									field : 'cfpMobile',
									title : '理财师',
									width : 150,
									align : 'left',
								},
								{
									field : 'customerMobile',
									title : '客户',
									width : 150,
									align : 'left',
								},
								{
									field : 'optUserName',
									title : '操作人',
									width : 150,
									align : 'left',
								},
								
								{
									field : 'crtTime',
									title : '变更时间',
									width : 150,
									align : 'left',
									formatter:function(value,row,index){  
				                         var date = new Date(value);  
				                         return formateDate1(date);  
				                     }
								}		
								
								 ] ]
			});
	

});

</script>
