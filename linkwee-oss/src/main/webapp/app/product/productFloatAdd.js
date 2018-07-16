/**
 * 浮动产品
 */
	
$(function(){ 
	loadProDaysByTypeId($('#productTypeId').val());
}); 

	function loadProDaysByTypeId(typeId){
		$.ajax({
			type : "get",
			url : "rest/prorule/loaddays?typeId="+typeId,			
			dataType : 'json',
			success: function(data){
					 $("#f_line_min_value").val(data.data.linMinValue);
					 $("#f_line_max_value").val(data.data.linMaxValue);
			   },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	}
	//productTypeDtlBtn  模板详情
	$("#productTypeDtlBtn").click(function(){
		var parentdiv=$('#productTypeDtlDiv');
		var	temp =	parentdiv.is(":hidden");//是否隐藏
		if(temp){//div隐藏
			$("#productTypeDtlBtn").text("隐藏详情");
			parentdiv.css('display','block');
			parentdiv.css('clear','both');
			
			var typeId = $("#productTypeId").val();
			$.ajax({
				type : "get",
				url : "rest/prorule/loadruledtl?typeId="+typeId,			
				dataType : 'json',
				success: function(data){
					var table=$("<table border=\"1\" id=\"profitDtlTab\" style=\"width:80%;\" >");
				     table.appendTo(parentdiv);
				       //行
				       var tr=$("<tr></tr>");
				       tr.appendTo(table);
				       //列
			           var td=$("<td>产品期限</td>");
			           td.css("text-align","center");
			           td.appendTo(tr);
			           td=$("<td>年化收益</td>");
			           td.css("text-align","center");
			           td.appendTo(tr);
			           
			           //数据输入行
			           for(var i = 0; i < data.length; i++){
					        var tr=$("<tr></tr>");
					        tr.appendTo(table);
					       //列
				          var td=$('<td> '+data[i].lineMinValue+'~'+data[i].lineMaxValue+' 日</td>');
				          td.appendTo(tr);
				          
				          td=$('<td>'+data[i].fixRate+'%</td>');
				          td.appendTo(tr);
			           }
			            
			         parentdiv.append("</table>");
					
				   },
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		}else{
			$("#productTypeDtlBtn").text("模板详情");
			parentdiv.empty();
			parentdiv.css('display','none');
		}
	
		
		
          
	});

//添加  收益模板
	$("#productTypeAddBtn").click(function(){
		var parentdiv=$('#productTypeAddDiv');
		parentdiv.css('display','block');
		parentdiv.css('clear','both');
		
		if(!($("#profitModleForm").length > 0)){
			
		var form = $('<form id="profitModleForm"></form>');  
         // 设置属性  
         //form.attr('action', action);  
         form.attr('method', 'post');  
         form.appendTo(parentdiv);
         
         var productTypeNameInp =  $("<input type='text' name='productTypeName' id='productTypeName' placeholder='输入模板名称' >");
         
         var saveModleBtn =  $("<input type='button' value='保存模板' id='saveModleBtn' onclick='savaModle()' style='margin:5px 10px;' >");
         var cancelModleBtn =  $(" <input type='button' value='取消' id='cancelModleBtn' onclick='cancelModelSave()' style='margin:5px 10px;' >");
         productTypeNameInp.appendTo(form);
         saveModleBtn.appendTo(form);   
         cancelModleBtn.appendTo(form);
        
         
		var table=$("<table border=\"1\" id=\"profitModleTab\" style=\"width:80%;\" >");
		var trIdx = 0;
	     table.appendTo(form);
	       //行
	       var tr=$("<tr></tr>");
	       tr.appendTo(table);
	       //列
           var td=$("<td>产品期限</td>");
           td.css("text-align","center");
           td.appendTo(tr);
           td=$("<td>年化收益</td>");
           td.css("text-align","center");
           td.appendTo(tr);
           td=$("<td>操作</td>");
           td.css("text-align","center");
           td.appendTo(tr);
           //数据输入行
            //行
            trIdx +=1;
	        var tr=$("<tr></tr>");
	        tr.appendTo(table);
	       //列
          var td=$('<td><input type="text" name="lineMin" style="width:100px;margin:5px 0px;"/> ~ <input type="text" name="lineMax" style="width:100px;margin:5px 0px;" />日</td>');
          td.appendTo(tr);
          
          td=$('<td><input type="text" name="fixRate" style="width:100px;margin:5px 0px;" />%</td>');
          td.appendTo(tr);
          
          td=$('<td><input type="button" value="添加"  onclick="addNew()" id="addTr'+trIdx+'" /></td>');
          td.appendTo(tr);
          
        
         parentdiv.append("</table>");
		
		}
          
	});
	
	function addNew() { 
		var trIdx = $("#profitModleTab tr").length;
		var tr=$("<tr></tr>");
		var td=$('<td><input type="text" name="lineMin" style="width:100px;margin:5px 0px;" /> ~ <input type="text" name="lineMax" style="width:100px;margin:5px 0px;" />日</td>');
		td.css("text-align","center");
          td.appendTo(tr);
          
          td=$('<td><input type="text" name="fixRate"  style="width:100px;margin:5px 0px;"/>%</td>');
          td.css("text-align","center");
          td.appendTo(tr);
          
          td=$('<td><input type="button" value="删除"  onclick="deleteTr(this)" id="delTr'+trIdx+'" /><input type="button" value="添加"  onclick="addNew()" id="addTr'+trIdx+'" /></td>');
          td.css("text-align","center");
          td.appendTo(tr);
          
		$("#profitModleTab tr:last").after(tr);
	} 
    function deleteTr(nowTr){
        $(nowTr).parent().parent().remove();
      } 
    
    //保存模板
    function savaModle(){
    	if($("#productTypeName").val().length<1){
    		$.messager.alert('提示','行模板名称不能为空 ','info');
			 return;
    	}
	var rulesArray = new Array();
	var tbl = $("#profitModleTab");
	var trlist = tbl.find("tr");

	for (var i = 1; i < trlist.length; i++)
	{
		var tr = $(trlist[i]);
		var lineMin = tr.find("INPUT[type ='text'][name='lineMin']").val();
		var lineMax = tr.find("INPUT[type ='text'][name='lineMax']").val();
		var fixRate = tr.find("INPUT[type ='text'][name='fixRate']").val();
		if(lineMin.length<1){
			 $.messager.alert('提示','输入项第'+i+'行  区间最小值不能为空 ','info');
			 return;
		}
		if(lineMax.length<1){
			 $.messager.alert('提示','输入项第'+i+'行  区间截止值不能为空 ','info');
			 return;
		}
		if(fixRate.length<1){
			 $.messager.alert('提示','输入项第'+i+'行  利率不能为空 ','info');
			 return;
		}
		var obj = new Object();
		obj.lineMinValue = lineMin;
		obj.lineMaxValue = lineMax;
		obj.fixRate = fixRate;
		rulesArray.push(obj);
	}
    	//
    	$.ajax({
			type : "post",
			url : "rest/prorule/save",
			data : "jsonStr="+JSON.stringify(rulesArray)+"&productTypeName="+$("#productTypeName").val(),
			dataType : 'json',
			success: function(result){
				   if(result.isFlag){				   
					   //关闭窗口,重新加载
					   $.messager.alert('提示',result.msg,'info');
					   cancelModelSave();
					   reFreshProfitModels();
				   } else  {
					   $.messager.alert('提示',"编辑失败,系统异常",'info');
				   }
			   },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				 $.messager.alert('提示',"编辑失败,系统异常",'error');
			}
		});
    }
	
    
    //
  function cancelModelSave(){
    	var parentdiv=$('#productTypeAddDiv');
    	parentdiv.css('display','none');
		parentdiv.empty();
		
    }
  //收益模块保存成功后重新刷新模板下拉框
  function reFreshProfitModels(){
	  	$.ajax({
			type : "get",
			url : "rest/prorule/loadtype",			
			dataType : 'json',
			success: function(data){
				 $("#productTypeId").empty();
				   $.each(data,function(index,value){
                       $("#productTypeId").append("<option value='"+value.id+"'>"+value.typeName+"</option>");
                   })
			   },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
		
  }
  //切换收益模板时 获取产品期限
  $('#productTypeId').change(function(){ 
	  
	  var typeId = $(this).children('option:selected').val();
	  loadProDaysByTypeId(typeId);
	  
	  }) 
