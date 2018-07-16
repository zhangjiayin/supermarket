<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <%
  request.setCharacterEncoding("UTF-8") ;
 %>
	<input type="hidden" id="department" value="${param.department}">
	<div style="padding-top: 10px;padding-left:10px;text-align: center;vertical-align: middle;position: absolute;left:20%;top:20%; ">
	<div >
		理财师性质 ：<select id="cc"  style="width: 200px;">
					    	<option <c:if test="${ param.department=='理财师'}">selected="selected"</c:if>>自由理财师</option>
							<option <c:if test="${ param.department!='理财师'}">selected="selected"</c:if>>新财富理财师</option>
						</select>
	</div>
	<br>
	<div id="orgList" <c:if test="${ param.department=='理财师'}">style="display: none;width:100%;"</c:if><c:if test="${ param.department!='理财师'}">style="display: block;width:100%;"</c:if>>
		所属公司 : <input id="org" name="org" style="width: 200px;">
	</div>
	<br>
	<div style="text-align: center;">
		<a href="javascript:;" class="easyui-linkbutton" id="exitLcs">提交</a>
	</div>
	</div>
	

<script type="text/javascript">
using(["common",'combobox','combotree','messager'],function(){
	$('#cc').combobox({
		onSelect: function(record){
			if(record.text=='自由理财师'){
				$("#orgList").hide();
			}else{
				$('#org').combotree("reload",'../json/orgJson.json');
				if(($("#department").val()!='理财师')){
					$('#org').combotree('setValue', $("#department").val());
				}
				$("#orgList").show();
			}
		}
	});
	$('#org').combotree({
	    url: '../json/orgJson.json',
	    valueField:"id",
	    textField: "text"
	});
	
	if(($("#department").val()!='理财师')){
		$('#org').combotree('setValue', $("#department").val());
	}

	
	lh.on("click").addHandler({"selector":'#exitLcs',"handler" : function(e){
		   var v;
		   if($("#orgList").is(":hidden")){
			   if($("#department").val()=='理财师'){
				    $.messager.alert('警告',"理财师机构未改变");
		    		return false;
			   }
			   v='99999999';
		   }else{
			   var tree = $('#org').combotree('tree').tree('getSelected');
			   if(tree){
			   		var  department=tree.text;
			   		if(department==$("#department").val()){
						   $.messager.alert('警告',"理财师机构未改变");
				    		return false;
					 }
					 v =$("#orgList input[name='org']").val();
			   }else{
			   $.messager.alert('警告',"理财师机构未改变");
	    		return false;
			   }
		   }
		  
			$.messager.confirm('系统提示','该操作将改变该用户的所属机构,确认该操作吗?',function(r){
			    if (r){
			    	if(!v){$.messager.alert('警告',"理财师机构不能为空"); return false;}
			    	lh.post('../lcsList/replaceLcs.htm',{'department':v,lcsNumber:'${param.lcsNumber}'},function(data){
			    		$("#win").window("close");
			    		var $datagrid=$('#lcsDg');
			    		var options =  $datagrid.datagrid('getPager').data("pagination").options;
			    		lh.post("../lcsList/getLcsList.htm",{pageIndex:options.pageNumber,pageSize:options.pageSize},function(data){
			    			  $datagrid.datagrid('loadData',  {rows:data.datas,total:data.totalCount});
			    		 });
			    	 });
			    }else{$("#win").window("close");}
			    return false;
			});
			return false;
		}
	});

	
	
});


</script>
