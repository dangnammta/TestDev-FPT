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
<script>
	$(function () {
		$("#datepicker").datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange: "-100:+0",
			dateFormat: "yy/mm/dd",
		});
	});</script>
<div class="content-wrapper">
    <section class="content">

		<h1>Update role</h1>
		<div class="box" style="min-height: 600px">
            <div class="box-body" style="margin-left: 20px">
				<form method='post' action="/role/update" id="form1" runat="server">
					<script type="text/javascript">
						$(document).ready(function () {
							$(".alert-dange").hide();
							$(".alert-succes").hide();
						});
					</script>

					<div class="aler alert-dange" style="background-color:#f2dede;border-color: #ebccd1;color: #a94442;padding: 15px;margin-bottom: 20px;margin-top: 20px;border: 1px solid transparent;border-radius: 4px;text-align:center">
						Update false
					</div>
					<div class="aler alert-succes"style="background-color:#d9edf7;border-color: #bce8f1;color: #31708f;padding: 15px;margin-bottom: 20px;margin-top: 20px;border: 1px solid transparent;border-radius: 4px;text-align:center">
						Update Success
					</div>
					<#if obj.idRole??>
					<input type="hidden" name="id" value="${obj.idRole}">
					</#if>
					<div class="form-group" >
						<label for="role"> Role code</label>
						<input type="text" class="form-control" name="role" id="role" placeholder="Role" value="${obj.role}" style="width: 500px;">
					</div>
					<div class="form-group">
						<label for="name">Name</label>
						<input class="form-control" name="name" id="email" placeholder="Name" value="${obj.name}" style="width: 500px;">
					</div>
					<div class="form-group">
						<label for="description">Description</label>
						<input type="text" class="form-control" name="description" id="description" placeholder="Description" value="${obj.description}" style="width: 500px;">
					</div>
					<input class="btn btn-default btn-lg" type="submit" value="Update"style="width: 200px;background-color:#80AF9B;color: white;">
				
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
