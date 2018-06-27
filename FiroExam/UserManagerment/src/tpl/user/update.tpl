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
<script type="text/javascript">
	$(document).ready(function () {
		var $uploadCrop;

		function readFile(input) {
			if (input.files && input.files[0]) {
				$('#myModal').modal()
				var reader = new FileReader();
				reader.onload = function (e) {
					$uploadCrop.croppie('bind', {
						url: e.target.result
					});
					$('.upload-demo').addClass('ready');
				}
				reader.readAsDataURL(input.files[0]);
			}
		}

		$uploadCrop = $('#upload-demo').croppie({
			viewport: {
				width: 250,
				height: 250
			},
			boundary: {
				width: 400,
				height: 400
			}
		});

		$('#upload').on('change', function () {
			readFile(this);
		});
		$('.upload-result').on('click', function (ev) {
			$uploadCrop.croppie('result', {
				type: 'canvas',
				size: 'original'
			}).then(function (resp) {
				$('#imagebase64').val(resp);
				//$('#form').submit();
			});
		});

		$('.commit').on('click', function (ev) {
			$('#form1').submit();
		});

		$("#upload").change(function () {
			$(".modal").show();
		});

	});
</script>
<div class="content-wrapper">
    <section class="content">
		<style>
			.inputWrapper > input
			{
				display: none;
			}
		</style>
		<script type="text/javascript">
			$(document).ready(function () {
				$(".alert-dange").hide();
				$(".alert-succes").hide();
			});

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
		</script>

		<h1>Update profile</h1>
		<div class="box" style="min-height: 600px">
			<div class="col-md-12">
				<div class="aler alert-dange" style="background-color:#f2dede;border-color: #ebccd1;color: #a94442;padding: 15px;margin-bottom: 20px;margin-top: 20px;border: 1px solid transparent;border-radius: 4px;text-align:center">
					Update false 
				</div>
				<div class="aler alert-succes"style="background-color:#d9edf7;border-color: #bce8f1;color: #31708f;padding: 15px;margin-bottom: 20px;margin-top: 20px;border: 1px solid transparent;border-radius: 4px;text-align:center">
					Update Success
				</div>

				<div class="box-body">


					<form method='post' action="/user/update" id="form1" runat="server">
						<div class="col-md-4">
							<div class="inputWrapper" style="width: 200px;height: 250px;margin-top: 20px">

								<input type="file" id="upload" value="Choose a file">
								<img class="img-responsive" alt="Responsive image" id="blah" src="/img/${obj.avatar}" alt="your image" style="width: 200px;height: 200px" />
								<label for="upload" style="float: right">
									<img src="/img/camera.png" style="width: 50px;height: 50px;cursor: pointer;bottom: 0px;right: 0px;z-index: 10000000"/>
								</label>
							</div>
						</div>
						<div class="col-md-8" style="margin-top: 20px">
							<#if obj.id??>
							<input type="hidden" name="id" value="${obj.id}">
							</#if>
							<div class="form-group" >
								<label for="username">Username</label>
								<input type="text" class="form-control" name="username" id="username" placeholder="Username" value="${obj.username}" style="width: 500px;">
							</div>
							<div class="form-group">
								<label for="email">Email</label>
								<input type="email" class="form-control" name="email" id="email" placeholder="Email" value="${obj.email}" style="width: 500px;">
							</div>
							<div class="form-group">
								<label for="fullname">Fullname</label>
								<input type="text" class="form-control" name="fullname" id="fullname" placeholder="Fullname" value="${obj.fullname}" style="width: 500px;">
							</div>
							<div class="form-group" style="width: 500px;">
								<label for="birthday">Birthday</label>
								<p><input type="text" class="form-control" name="birthday" id="datetime" placeholder="Birthday" value="${obj.birthday?date}" style="width: 500px;"></p>
							</div>
						</div>
						<input class="commit btn btn-default btn-lg" type="submit" value="Update"style="width: 200px;background-color:#80AF9B;color: white;margin-left: 15px">
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										<h4 class="modal-title" id="myModalLabel">Modal title</h4>
									</div>
									<div class="modal-body">
										<div id="upload-demo"></div>
										<input type="hidden" id="imagebase64" name="imagebase64">

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										<a class="upload-result btn btn-primary" data-dismiss="modal">Crop image</a>
									</div>
								</div>
							</div>
						</div>

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
		</div>
    </section>
	<!-- jQuery 2.2.3 -->
</div>
<#include "../base/footer.tpl">
