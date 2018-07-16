<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
      <%
  request.setCharacterEncoding("UTF-8") ;
 %>
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<div class="container">
	
	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
		<div class="row">
			<div class="col-lg-4">
				<div class="input-group">
					<span class="input-group-btn">
						<label for="fourtuneAmount">增加幸运值数量：</label>
						<input type="text" class="form-control" name="fourtuneAmount" id="fourtuneAmount" placeholder="幸运值数量">
					 </span>			  
				</div>
			</div>
		</div>
		<br/>
		<div class="row">
			<div class="col-lg-4">
				<div class="input-group">
					<button class="btn btn-danger J_add_fourtune" type="button" style="margin-left: 120px">确认增加</button>			  
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 table-responsive">
		<table id="J-fourtune-add-record" class="table table-bordered" data-xtoolbars="#template-search"  data-url="/rest/act/actoneyuandrawrecord/addFourtuneHistory" data-order="false" data-paging="true" data-size="10">
	        <thead>
	            <tr>
		            <th data-name="id" data-edit="id" data-format="id:idFormat">ID</th>
		            <th data-name="addFourtune">发放幸运值数量</th>
		            <th data-name="operator">操作者</th>
		            <th data-name="createTime" data-format='createTime:dateFormat'>发放时间</th>
	            </tr>
	        </thead>
	    </table>
	</div>
	
	<script type="text/javascript">
		$("button.J_add_fourtune").click(function () {
			var fourtuneAmount = $("#fourtuneAmount").val();
			bootbox.confirm("确认增加幸运值吗？",function (result) {
				if(result){
					$.get("rest/act/actoneyuandrawrecord/addfourtune",{fourtuneAmount:fourtuneAmount},function ($result) {
						if($result.isFlag){
							bootbox.alert($result.msg);
							$("#payment_amount").val("");
							$db.draw();
						} else {
							bootbox.alert($result.msg);
						}
					});

				}
			});
		});
		
		var idFormat = function (data,type,full,meta) {
	        var start = (meta.settings.oAjaxData.start / meta.settings.oAjaxData.length) +1;
	        return (meta.row+1)+(start-1)*10;
	    }
	    
	    var dateFormat = function (data,type,full,meta) {
	      	return new Date(data).Format("yyyy-MM-dd hh:mm:ss"); 
	    }
	          
        var $db= $("#J-fourtune-add-record").linkweeTable();
        
	</script>
	
	<script type="text/linkwee-template" id="template-search">
        <form>
            <div class="row">
                <div class="col-sm-2">
                   <div class="input-group">	
						<span class="input-group-btn">
							<input id="startTime" name="startTime" class="Wdate"  placeholder="开始时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:100px" />
                        </span>
						<span class="input-group-btn">
							&#126;&#126;
						</span>
						<span class="input-group-btn">                    
							<input id="endTime" name="endTime" class="Wdate"  placeholder="结束时间"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})"  style="width:100px"/>
                        </span>
											
                        <span class="input-group-btn">
                             <a class="btn btn-default J_search parent_search" id="searchXX" style="margin-left: 20px" role="button"><i class="fa fa-search"></i> 查询</a>
                        </span>
                    </div>
                </div>
				
            </div>

        </form>
    </script>
	
</div>


