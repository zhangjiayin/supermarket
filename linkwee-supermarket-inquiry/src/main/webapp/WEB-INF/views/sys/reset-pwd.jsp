<%@ page language="java" pageEncoding="utf-8"%>
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="app/lib/security/sha256.js" type="text/javascript"></script>
<script type="text/javascript">
function reset_pwd(){
	if($("#password").val()==$('#new_password').val()){
		showError("新旧输入密码一样!");
		return;
	}
	if($("#rpassword").val()!=$('#new_password').val()){
		showError("两次输入密码不一致!");
		return;
	}else{
		$('#new_password').val(sha256_digest($('#new_password').val()));
		$('#password').val(sha256_digest($('#password').val()));
		$('#rpassword').val(sha256_digest($('#rpassword').val()));
		$.ajax({
			url : 'rest/auth/resetpwd',
			data:{'new_password':$('#new_password').val(),'password':$('#password').val()},
			type:'json',
			type : 'POST',
			success : function(result) {
				 if (result.success) {
	                 //保存成功  1.关闭弹出层，2.刷新表格数据
	                 showTips(result.message);
	             }else {
	                 showError(result.message);
	             }
			},
		 //async :false,
		  error:function(XmlHttpRequest,textStatus, errorThrown)
		  {
			  showError(XmlHttpRequest.responseText);
		  }
		});
	}
}
</script>
<!-- END PAGE LEVEL SCRIPTS -->
<p>
			 请输入新密码信息:
		</p>
		<form id="reset_frm" action="#" method="post">
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="password" placeholder="原密码" name="password"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="new_password" placeholder="新密码" name="new_password"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">再次输入密码</label>
			<div class="controls">
				<div class="input-icon">
					<i class="fa fa-check"></i>
					<input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="rpassword" placeholder="再次输入新密码" name="rpassword"/>
				</div>
			</div>
		</div>
		<div class="form-actions">
			<button  type="reset" class="btn">
			 清空 </button>
			<button type="button"  onclick="reset_pwd();" class="btn blue pull-right">
			提交 
			</button>
		</div>
		</form>