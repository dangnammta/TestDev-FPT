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
<link href="/css/croppie.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/croppie.js"></script>
<script src="/js/moment.js"></script>
<script src="/js/gonrin.datetimepicker.js"></script>
<script>
	$(function () {
		$('#datetime').datetimepicker({
			textFormat: 'YYYY/MM/DD',
			extraFormats: ['YYYYMMDD'],
			format: 'YYYY/MM/DD'
		});
		$('#datetime').on('change.gonrin', function (e) {
			console.log(e.date);
		})
	});
	$.validator.setDefaults({
		submitHandler: function () {
			alert("submitted!");
		}
	});
	function add() {
		$("#signupForm").validate({
			rules: {
				username: {
					required: true,
					minlength: 2
				},
				password: {
					required: true,
					minlength: 5
				},
				confirm_password: {
					required: true,
					minlength: 5,
					equalTo: "#password"
				},
				email: {
					required: true,
					email: true
				},
				datepicker: {
					required: true,
					date: true
				}
			},
			messages: {
				username: {
					required: "Please enter a username",
					minlength: "Your username must consist of at least 2 characters"
				},
				password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long"
				},
				confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
				},
				email: "Please enter a valid email address",
				datepicker: {
					required: "Please provide a birthday",
					date: "Invalid format"
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
		<h1>Add user</h1>
		<script type="text/javascript">
			$(document).ready(function () {
				$(".alert-dange").hide();
				$(".alert-succes").hide();
			});
		</script>
		<div class="box" style="min-height: 600px">
			<div class="col-md-12">
				<div class="aler alert-dange" style="background-color:#f2dede;border-color: #ebccd1;color: #a94442;padding: 15px;margin-bottom: 20px;margin-top: 20px;border: 1px solid transparent;border-radius: 4px;text-align:center">
					Add false 
				</div>
				<div class="aler alert-succes"style="background-color:#d9edf7;border-color: #bce8f1;color: #31708f;padding: 15px;margin-bottom: 20px;margin-top: 20px;border: 1px solid transparent;border-radius: 4px;text-align:center">
					Add Success
				</div>
			</div>
			<!-- /.box-header -->
			<div class="box-body">
				<!--<a onclick="save();">Add</a>-->
				<script>
					function save() {
						var username = $("#username").val();
						$.getJSON("/user/add",
								{
									name: username,
									city: "Duckburg"
								},
								function (data) {
									if (data.result == 0) {
										window.location.replace("http://" + window.location.hostname + ":4567" + "/user/add");
									} else {
										alert("Add false");

									}
								});
					}
				</script>
				<form id="signupForm" action="/user/save" method="post">
					<div class="col-md-12">


						<div class="col-md-6">

							<input type="hidden" name="id" value="">
							<div class="form-group">
								<label for="username">Username</label>
								<input type="text" class="form-control" name="username" id="username" placeholder="Username" value="" style="width: 300px;">
							</div>
							<div class="form-group">
								<label for="fullname">Password</label>
								<input type="password" class="form-control" name="password" id="password" placeholder="Password" value="" style="width: 300px;">
							</div>
							<div class="form-group">
								<label for="fullname">Confirm password</label>
								<input type="password" class="form-control" name="confirm_password" id="confirm_password" placeholder="Confirm password" value="" style="width: 300px;">
							</div>
							<div class="form-group">
								<label for="email">Email</label>
								<input type="email" class="form-control" name="email" id="email" placeholder="Email" value="" style="width: 300px;">
							</div>
							<div class="form-group" style="width: 300px">
								<label for="birthday">Birthday</label>
								<p><input type="text" class="form-control" name="birthday" id="datetime" placeholder="Birthday" value="" style="width: 300px;"></p>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="fullname">Fullname</label>
								<input type="text" class="form-control" name="fullname" id="username" placeholder="Fullname" value="" style="width: 300px;">
							</div>
							<div class="form-group">
								<label for="phone">Phone</label>
								<input type="text" class="form-control"  name="phone" id="phone" placeholder="Phone" value="" style="width: 300px;">
							</div><div class="form-group">
								<label for="gender">Gender</label>
								<input type="text" class="form-control" name="gender" id="gender" placeholder="Gender" value="" style="width: 300px;">
							</div>
							<div class="form-group">
								<label for="status">Status</label>
								<input type="text" class="form-control" name="status" id="status" placeholder="Status" value="" style="width: 300px;">
							</div>
						</div>
					</div>
					<input class="btn btn-default btn-lg" onclick="add();" type="submit" value="Create"style="width: 200px;background-color:#80AF9B;color: white; margin-left: 30px">
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
