<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- DataTables -->
<link rel="stylesheet" type="text/css" href="app/css/linkwee.tables.css"  />
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<script type="text/javascript" src="app/js/jquery.linkwee.js"></script>
<script type="text/javascript" src="assets/plugins/My97DatePicker/WdatePicker.js"  ></script>
<div id="main-prizeRecord" class="container-fluid">
    <div class="table-responsive">
		<table id="J-prizeRecord" class="table table-bordered" data-xtoolbars="#template-search"  data-url="rest/act/actoneyuandrawrecord/list_ajax" data-order="false" data-paging="true" data-size="10">
	        <thead>
	            <tr>
		            <th data-name="id" data-edit="id" data-format='id:idFormat'>ID</th>
		            <th data-name="mobile">客户手机号</th>
		            <th data-name="orderDesc">奖品</th>
		            <th data-name="issued" data-format='issued:sendFormat'>发放状态</th>
		            <th data-name="isFourtune" data-format='isFourtune:fourtuneFormat'>是否幸运奖</th>
		            <th data-name="receivingUserName">收货人姓名</th>
		            <th data-name="receivingMobile">收货人号码</th>
		            <th data-name="receivingAddress">收货地址</th>
		            <th data-name="thirdAccount">第三方账号</th>
		            <th data-name="typeName">类型</th>          
		            <th data-name="id" data-format="id:prize_opts">操作</th>
	            </tr>
	        </thead>
	 
	    </table>
	</div>
	
	<div class="modal fade" id="prize_send_modal" tabindex="-2" role="dialog"
		aria-hidden="true" style="top: 100px">
		<div class="modal-dialog" style="">
			<div class="modal-content">
				<div class="modal-header">

					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						<span aria-hidden="true">&times;</span>
					</button>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	 </div>
	
    <script type="text/javascript">
        
      $db= $("#J-prizeRecord").linkweeTable();
      
      var idFormat = function (data,type,full,meta) {
	      var start = (meta.settings.oAjaxData.start / meta.settings.oAjaxData.length) +1;
	      return (meta.row+1)+(start-1)*10;
	  }
       
      var fourtuneFormat = function (data,type,full,meta) {
        	if(data == 1) {
        		return "幸运奖";
        	} else {
        		return "普通奖";
        	} 
      }
      
      var sendFormat = function (data,type,full,meta) { 	  
	      	if(data == 0){
	      		return "未发放";
	      	}else if(data == 1){
	      		return "已发放";
	      	}
	  }
      
      var prize_opts  = function (data,type,full,meta) {
    	  if(full.issued == 1){
    		  return '<a disabled="disabled" class="btn btn-sm btn-default btn-icon ui-redirect" data-id="'+data+'" href="javascript:;"><i class="fa fa-edit"></i>已发放</a>';
    	  }else {
    		  return '<a class="btn btn-sm btn-success btn-icon J_prize_send" data-id="'+data+'" type-id="'+full.isFourtune+'" href="javascript:;"><i class="fa fa-edit"></i>发放</a>';
    	  } 	   
	  }
      
      $("#main-prizeRecord").delegate("a.J_prize_send","click",function () {
          var iid = $(this).attr("data-id");
          var typeId = $(this).attr("type-id");
          bootbox.confirm("确认发放奖品吗？",function (result) {
				if(result){
					$.get("rest/act/actoneyuandrawrecord/sendPrize",{prizeId:iid,typeId:typeId},function ($result) {
						if($result.isFlag){
							bootbox.alert($result.msg);
							$db.draw(false);
						} else {
							bootbox.alert($result.msg);
						}
					});
				}
			}); 
           				
       });
      
    </script>
    <script type="text/linkwee-template" id="template-search">
		<form>
            <div class="row">
                <div class="col-sm-8">
                   <div class="input-group">	
                        <span class="input-group-btn">
                            <input type="text" name="mobile" class="form-control" style="width: 150px;margin-right: 20px"  placeholder="客户手机号" autocomplete="off" />
                        </span>
						<span class="input-group-btn">
							<input id="startTime" name="startTime" class="Wdate" placeholder="开始时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:150px" />
                        </span>
						<span class="input-group-btn">
							&#126;&#126;
						</span>
						<span class="input-group-btn">
							<input id="endTime" name="endTime" class="Wdate" placeholder="结束时间" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\')}'})"  style="width:150px"/>
                        </span>

						<span>
							<select id="issued" name="issued" class="form-control" style="margin-left: 20px; width: 150px; display: inline-block;" >
								<option value="">请选择发放状态</option>                        
								<option value="0">未发放</option> 
								<option value="1">已发放</option>                                    	      
                        	</select>
						</span>
											
                        <span >
                             <a class="btn btn-default J_search parent_search" role="button"><i class="fa fa-search"></i> 查询</a>
                        </span>
					   
                    </div>
                </div>	

				<div class="col-sm-2">   
					<span class="input-group-btn" style="float:right"> 
						<a class="btn btn-default btn-icon ui-redirect" href="javascript:;" data-title="增加幸运值" data-url="/rest/act/actoneyuandrawrecord/toaddfourtune" role="button"><i class="fa fa-plus"></i>增加幸运值</a>             
					</span>            
                </div>			
            </div>
		</form>
    </script>
    
</div>