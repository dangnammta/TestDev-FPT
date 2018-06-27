<#include "../base/header.tpl">
<script src="/js/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/js/app.min.js"></script>
<!-- DataTables -->
<script src="/js/jquery.dataTables.js"></script>
<script src="/js/dataTables.bootstrap.js"></script>
<!-- SlimScroll -->
<script src="/js/jquery.slimscroll.js"></script>
<!-- FastClick -->
<script src="/js/fastclick.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/js/demo.js"></script>
<script src="/js/jquery-ui.js"></script>
<script src="/js/jquery.js"></script>
<script src="/js/jquery.validate.js"></script>
<script>
	function add() {
		$("#signupForm").validate({
			rules: {
				idRole: {
					required: true,
				},
				name: {
					required: true,
				}
			},
			messages: {
				idRole: {
					required: "Please enter a Id Role",
				},
				name: {
					required: "Please provide a name for name",
				}
			},
			submitHandler: function (form) {
				form.submit();
			}
		});
	}
</script>
<style>
	#signupForm {
		width: 1200px;
	}
	#signupForm label.error {
		margin-left: 10px;
		width: auto;
		display: inline;
	}
</style>
<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
		<h1>Add role</h1>
		<script type="text/javascript">
			$(document).ready(function () {
				$(".alert-dange").hide();
				$(".alert-succes").hide();
			});
		</script>
		<div class="box" style="min-height: 600px">
			<!-- /.box-header -->
			<div class="box-body" style="margin-left: 20px">
				<a onclick="save();">Add</a>
				<script>
					function save() {
						var idRole = $("#idRole").val();
						$.getJSON("/role/add",
								{
									name: idRole,
									city: "Duckburg"
								},
								function (data) {
									if (data.result == 0) {
										//window.location.replace("http://" + window.location.hostname + ":4567" + "/role/add");
										$(".alert-succes").show();
									} else {
										$(".alert-dange").show();
									}
								});
					}
				</script>
				<div class="aler alert-dange" style="background-color:#f2dede;border-color: #ebccd1;color: #a94442;padding: 15px;margin-bottom: 20px;margin-top: 20px;border: 1px solid transparent;border-radius: 4px;text-align:center">
					Add false 
				</div>
				<div class="aler alert-succes"style="background-color:#d9edf7;border-color: #bce8f1;color: #31708f;padding: 15px;margin-bottom: 20px;margin-top: 20px;border: 1px solid transparent;border-radius: 4px;text-align:center">
					Add Success
				</div>
				<form id="signupForm" action="/role/save" method="post">
					<div class="form-group">
						<label for="role">Role</label>
						<input class="form-control" name="role" id="role" placeholder="Role" value="" style="width: 500px;">
					</div>
					<div class="form-group">
						<label for="name">Name</label>
						<input type="text" class="form-control" name="name" id="name" placeholder="Name" value="" style="width: 500px;">
					</div>
					<div class="form-group">
						<label for="description">Description</label>
						<input type="text" class="form-control" name="description" id="description" placeholder="Description" value="" style="width: 500px;">
					</div>
					<input class="btn btn-default btn-lg" onclick="add();" type="submit" value="Create"style="width: 200px;background-color:#80AF9B;color: white;">
				</form>
				<script type="text/javascript">

					function update(id) {
						var url = "http://" + window.location.hostname + "/user/update";
						var username = $("#username").val();
						if (username.trim() === "") {
							$(".alert-dange").show();
							$(".alert-succes").hide();
							$(".alert-dange").fadeTo(5000, 1000).fadeOut(1000, function () {
								$(".alert-dange").fadeOut(1000);
							});
						} else {
							$.post(url,
									{
										"action": "Login",
										"uname": username,
									}, function (data) {
								alert(username);
								if (data.result == true) {

								} else {

								}

							});
						}
					}
					function del(id) {
						window.location = "/user/update/" + id;
						window.location.replace("http://" + window.location.hostname + ":4567" + "/user/get");
						alert(delete_id + "," + window.location.hostname + "," + window.location.port);
						$(".alert-succes").show();
						$(".alert-dange").hide();
						$(".alert-succes").fadeTo(5000, 1000).fadeOut(1000, function () {
							$(".alert-succes").fadeOut(1000);
						});
					}
				</script>
			</div>
		</div>
	</section>
	<!-- jQuery 2.2.3 -->
</div>
<#include "../base/footer.tpl">
