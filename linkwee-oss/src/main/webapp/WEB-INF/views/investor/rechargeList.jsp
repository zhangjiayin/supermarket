<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
</script>
    <table id="rechargeDg"  style="width:600px;height:550px" ></table>
    <div id="rechargeToolbar"style="display: none;">
        <div>
        	<form id="rechargeQueryForm">
	        	客户： <input class="easyui-textbox" name="customer" style="width:110px" value="${customer}" />
	        	
		        <a href="#" id="rechargeQueryButton" class="easyui-linkbutton" >&nbsp;&nbsp;查 询&nbsp;&nbsp;</a>
	        </form>
        </div>
    </div>

<script type="text/javascript" src="app/common/util.js"></script>
<script type="text/javascript" src="app/investor/rechargeList.js"></script>
