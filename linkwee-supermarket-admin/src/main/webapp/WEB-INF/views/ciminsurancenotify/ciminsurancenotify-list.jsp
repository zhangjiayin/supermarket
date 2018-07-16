<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<!-- Editor -->
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Editor/js/dataTables.editor.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Editor/css/editor.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Buttons/js/dataTables.buttons.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Buttons/css/buttons.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Select/css/select.dataTables.min.css"  />
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>

   <table id="J-newslist" class="table table-bordered" data-defer="false" data-xtoolbars="#template-search" data-url="rest/cim/ciminsurancenotify/auditList" data-order="true" data-paging="true" data-size="10" data-cols="false" data-order="true">
        <thead>
            <tr >
                <th data-name="userName" data-format="userName:linkFormat">出单人</th>
                <th data-name="mobile" data-format="mobile:linkFormat">手机号</th>
            	<th data-name="productName" data-format="productName:linkFormat">产品名称</th>
                <th data-name="insureNum" data-format="insureNum:linkFormat">投保单号</th>
                <th data-name="price" data-format="price:linkFormat">支付金额</th>
                <th data-name="feeRatio" data-format="feeRatio:linkFormat">佣金率</th>
                <th data-name="fee" data-format="fee:linkFormat">订单佣金</th>
                <th data-name="startDate" data-format="startDate:linkFormat">电子保单生成时间</th>
                <th data-name="hesitateDate" data-format="hesitateDate:linkFormat">犹豫期</th>
                <th data-name="clearingStatus" data-format="clearingStatus:linkFormat">系统结算</th>
                <th data-name="auditStatus" data-format="auditStatus:linkFormat">审核状态</th>
            </tr>
        </thead>
    </table>
      
    <script type="text/javascript">
    
        var linkFormat = function (data,type,full,meta) {
            if(!full){
                return "";
            }
            
            var content = data;
          //  console.log(data);
            
            switch (meta.col){
	            case 4:
	            	content = data/100;
	                break;
	            case 5:
	            	content = data + '%';
	                break;	                
	            case 6:
	            	content = data/100;
	                break;
	            case 8:
	            	content = data + "天";
	                break;
	            case 9:
	            	if(data == 0){
	            		content = '<p style="color: #41bd6e; font-size: 15px;font-weight: bold;">待结算</p>';
	            	}else if(data == 1){
	            		content = '<p style="color: #1d51ea; font-size: 14px;font-weight: bold;">成功</p>';
	            	} else if(data == 2){
	            		content = '<p style="color: #211e29; font-size: 14px;font-weight: bold;">失败</p>';
	            	}
	                break;
	            case 10:
	            	if(data == 0 ){
	            		if(full.ifShowAuditButton == 0){	
	            			content = '<button class="btn btn-sm  btn-success btn-icon J_insurance_audit" data-title="保单审核" data-id="'+full.id+'" ><i class="fa fa-edit" ></i>审核</button>';
	            		} else {
	            			content = '待审核';
	            		}
	            	} else if(data == 1){
	            		content = "系统审核通过";
	            	} else if(data == 2){
	            		content = "系统审核失败";
	            	} else if(data == 3){
	            		content = "管理员审核通过";
	            	} else if(data == 4){
	            		content = "管理员审核失败";
	            	}
	                break;	                
              }
            
            return content;
        }
       var $db= $("#J-newslist").linkweeTable();
    </script>
    <script type="text/javascript" src="app/ciminsurancenotify/ciminsurancenotify-list.js"></script>
    <script type="text/linkwee-template" id="template-search">
         <div class="row">
			  <form>
          	  		<div class="col-xs-8 ">
			        	<input name="userName"  class="easyui-textbox" style="width:200px"  placeholder="理财师姓名">
						<input name="mobile"  class="easyui-textbox" style="width:200px"  placeholder="手机号">
						<input name="insureNum"  class="easyui-textbox" style="width:200px"  placeholder="投保单号">

						审核状态:
						<select name="auditStatus">
							<option value="99" selected="selected">全部</option>
							<option value="1">系统审核通过</option>
			            	<option value="2">系统审核失败</option>
			            	<option value="3">管理员审核通过</option>
							<option value="4">管理员审核失败</option>
			        	</select>
			        	<button class="btn btn-default btn-sm J_search" role="button">查询</button>
			 	 	</div>
					<div class="col-xs-1 col-xs-offset-3">
						<button type="reset" class="btn btn-primary" >重置</button>
					</div>
			  </form>
         </div>
    </script>
    
    
<!-- 保险订单审核:模态框（Modal）-->
<div class="modal fade" id="auditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">保险订单审核 </h4>
      </div>
      <div class="modal-body">
          <div class="form-group">
			<div class="row">
			  <div class="col-xs-6 text-center">
				<label class="radio-inline text-left">
					  <input type="radio" name="auditStatus"  value="3"> 审核通过
				</label>
			  </div>
			  <div class="col-xs-6 text-center">
				<label class="radio-inline text-left">
					  <input type="radio" name="auditStatus"  value="4" checked="checked"> 审核不通过
				</label>
			  </div>			  
			</div>
          </div>   
      </div>
      <div class="row text-center">
      		<p id="auditError"  style="color: red;font-weight: bolder;"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="auditSave" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
  <input type="hidden" id="insuranceNotifyIdForEdit" name="insuranceNotifyIdForEdit">
</div>

