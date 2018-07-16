<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="com.linkwee.xoss.rbac.PermissionSign" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />

<!-- Editor -->
<script type="text/javascript" src="assets/plugins/data-tables/extensions/Buttons/js/dataTables.buttons.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Buttons/css/buttons.dataTables.min.css"  />

<script type="text/javascript" src="assets/plugins/data-tables/extensions/Select/js/dataTables.select.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/extensions/Select/css/select.dataTables.min.css"  />

<script type="text/javascript" src="app/cimorginfo/cimorginfo-list.js"></script>
<!-- 管理员才有下列权限 -->
<shiro:hasRole name="admin">
<input type="hidden" id="shiro_admin" />
</shiro:hasRole>
<style type="text/css">
.table th, .table td{
vertical-align: middle !important; /*表格内容优先垂直居中*/
}
</style>
<table id="dtable" class="table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
        <!-- <th>序号</th> -->
            <th>机构编码</th>
            <th>机构名称</th>
      <!--  <th>后台账户</th> -->
            <th>注册资本(万元)</th>	
        <!-- <th>上线时间</th> -->
            <th>创建时间</th>
        <!-- <th>所在城市</th> -->
        <!-- <th>法人代表</th> -->
        <!-- <th>联系方式</th> -->
            <th>安全评级</th>
            <th>首页推荐</th>
            <th>列表推荐</th>
            <th>推荐排名</th>
<!--             <th>平台排名</th> -->
            <th>合作状态</th>
            <th>灰度模式</th>
            <th>操作</th>
            </tr>
        </thead>
 
    </table>
    
    
 <!-- 推荐信息 :模态框（Modal）-->
<div class="modal fade" id="recommendModal" tabindex="-1" role="dialog" aria-hidden="true" style="top: 200px">
                   <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">排名/推荐</h4>
                            </div>
                            
                             <form  id="myForm">
                            	<div class="modal-body">
                                	<div class="row">
                                	
                                		<div class="col-md-12">
				                            <div class="form-group">
				                                <b style="font-size:18px">平台排名</b><b>（所有平台排名）</b>
				                            </div>
				                        </div>
                                	
                                		<div class="col-md-12">
				                            <div class="form-group">
				                                <label class="control-label col-md-3"><b style="color:red">*</b><b>平台排名：</b></label>
				                                <div class="col-md-7">
				                                     <input type="text" class="form-control" id="orgListSort" name="top" autocomplete="off" placeholder="请输入排名">
				                                </div>
				                            </div>
				                        </div>
				                        
				                        <br/><br/><br/><br/><br/>
				                        
				                        <div class="col-md-12">
				                            <div class="form-group">
				                                <b style="font-size:18px">推荐及推荐排名</b>
				                            </div>
				                        </div>
                                		
		                                <div class="form-group" style="margin-left: 20px">
		                                	<div>
				                                <b>首页推荐（为首页热门产品设置主推平台）</b>
				                            </div>
											  <label>
											     <input type="radio" name="recommend" value="1" id="homePageRecommend">推荐
											  </label>
											  <label>	
										     	<input type="radio"  name="recommend" value="0" id="homePagenoRecommend">不推荐
										     </label>
										</div>
										
										<div class="form-group" style="margin-left: 20px">
		                                	<div>
				                                <b>列表推荐（为平台列表设置热门平台）</b>
				                            </div>
											  <label>
											     <input type="radio" name="listRecommend" value="1" id="listRecommend">推荐
											  </label>
											  <label>	
										     	<input type="radio"  name="listRecommend" value="0" id="listnoRecommend">不推荐
										     </label>
										</div>
										
										<div class="form-group" style="margin-left: 20px">
		                                	<div>
				                                <b>推荐排名（首页推荐，平台推荐共用）</b>
				                            </div>
											<div class="col-md-7">
			                                     <input type="text" class="form-control" id="recommendSort" name="homepageSort" autocomplete="off" placeholder="请输入排名">
			                                </div>
										</div>	                              
				                    </div>
	                            </div>
	                            <!-- 表单提交传入后台机构id -->
	             				<input type="hidden" id="orgNumber" name="orgNumber" />
	                            <div class="modal-footer">
	                                <button type="submit" class="btn btn-primary">保存</button>
	                                <button id="reset" type="reset" class="btn btn-default" data-dismiss="modal">取消</button>
	                            </div>
                            </form>
                        </div>
                 </div>
    </div>
    
  <!-- 佣金设置 :模态框（Modal）-->   
 <div class="modal fade" id="orgFeeRatioModal" tabindex="-1" role="dialog" aria-hidden="true">
 </div>
 
 <!-- layer弹层组件 -->
 <script type="text/javascript" src="assets/plugins/layer/layer.js"></script>   
 <script type="text/javascript" src="app/cimorginfo/formdata-convert-tojson.js"></script>
           <!-- /rest/news/tosave -->
 <script type="text/template" id="template-search">
        <form>
            <div class="row">
                
                <div class="col-sm-2">
                    <div class="input-group">
                        <input name="orgname" class="form-control"   placeholder="请输入机构名称" autocomplete="off">
                        <span class="input-group-btn">
                             <button class="btn btn-default J_search" role="button"><i class="fa fa-search"></i> 查询</button>
                        </span>
                    </div>
                </div>
				<shiro:hasPermission name="<%=PermissionSign.ORG_DETAIL_EDIT%>">
					<div class="col-sm-1" style="margin-left: -25px;">
						<span class="input-group-btn">
							<a class="btn btn-default btn-icon ui-redirect" href="javacript:void(0);" data-title="新增机构" data-url="/rest/cim/cimorginfo/toAdd" role="button"><i class="fa fa-plus"></i> 新增</a>
					 	</span>
					</div>
				</shiro:hasPermission>

            </div>

        </form>
    </script>

 