<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<!-- moment -->
<script type="text/javascript" src="assets/plugins/bootstrap-daterangepicker/moment.min.js"  ></script>
<!-- layer弹层组件 -->
<script type="text/javascript"  src="assets/plugins/layer/layer.js"></script>
<script type="text/javascript" src="app/fee/fee-page.js"></script>


<table id="fee-list" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
	            <th>理财师姓名</th>
	            <th>理财师手机号码</th>
	            <th>理财师总佣金</th>
	            <th>发放佣金次数</th>
	            <th>上次发放月份</th>
	            <th>上次发放时间</th>
	            <th>操作</th>
            </tr>
        </thead>

    </table>
    
     <!-- 模态框（Modal）-->
	<div class="modal fade" id="fee_pay_modal"  role="dialog" aria-hidden="true" style="top: 50px">
	<div class="modal-dialog" >
	                        <div class="modal-content">
	                            <div class="modal-header">

	                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	                                  <span aria-hidden="true">&times;</span></button>
	                                </button>
	                                <h4 class="modal-title"></h4>
	                            </div>
	                             <div class="modal-body">
	                              </div>
	                             <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							      </div>
	                        </div>
	                 	</div>
	</div>


	<script type="text/linkwee-template" id="template-tools">
			
            <div class="row">
                <div class="col-sm-3">
                    <div class="input-group">
                        <input name="mobile" class="form-control"   placeholder="请输入手机号码" autocomplete="off">
                        <span class="input-group-btn">
                             <button class="btn btn-default J_search" role="button"><i class="fa fa-search"></i></button>
                        </span>
                    </div>
                </div>
				<shiro:hasPermission name="fee:calc">
				<div class="col-sm-3 col-sm-offset-3">
						<a class="btn btn-danger J_fee_model" href="javascript:;" role="button"><i class="fa fa-list"></i> 去发放佣金</a>
                </div>
				</shiro:hasPermission>
			
            </div>
    </script>
    
    	<%-- <shiro:hasPermission name="fee:pay">
				<div class="col-sm-2 col-sm-offset-1">
                    <a class="btn btn-danger J_fee_pay" style="border-width:1px; border-style:solid; border-color:#c02121;" role="button"><i class="fa fa-play"></i> 发放佣金&nbsp;&nbsp;(不可逆)</a>
                </div>
				</shiro:hasPermission> --%>
    

