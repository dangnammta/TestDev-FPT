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
<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

		<h1>List user</h1>
		<div class="box">
            <script type="text/javascript">
				$(document).ready(function () {
					$(".alert-dange").hide();
					$(".alert-succes").hide();
				});
			</script>

            <div class="box-body">
				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>id</th>
							<th>Role code</th>
							<th>Name</th>
							<th>Description</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<#list objs as role>
						<tr>
							<td>
								<#if role.idRole??>
								${role.idRole}
								</#if>
							</td>
							<td>
								<#if role.role??>
								${role.role}
								</#if>
							</td>
							<td>
								<#if role.name??>
								${role.name}
								</#if>
							</td>
							<td>
								<#if role.description??>
								${role.description}
								</#if>
							</td>
							
							<td>
								<a class="glyphicon glyphicon-pencil" title="Update item" href="/role/update/${role.idRole}"></a>/<a class="glyphicon glyphicon-trash" data-toggle="modal" data-target=".bs-example-modal-sm${role.idRole}" title="Delete item"></a>
							</td>
						</tr>
					<div class="modal fade bs-example-modal-sm${role.idRole}" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
						<div style="background-color: white; margin-top: 100px" class="modal-dialog modal-sm" role="document">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title" style="color: red">Warning</h4>
							</div>
							<div class="modal-body">
								Are you want to delete this item have id is ${role.idRole}
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								<a class="btn btn-primary" href="/role/delete/${role.idRole}">Delete</a>
							</div>
						</div>
					</div>
					</#list>
					</tbody>
				</table>
				<script type="text/javascript">
					$(document).ready(function () {
						$(".alert-dange").hide();
						$(".alert-succes").hide();
					});
					function del(clicked_id) {
						var url = "http://" + window.location.hostname + "/user/delete/" + clicked_id;
						var chooseID = clicked_id;

						$.post(url,
								{
									"id": chooseID
								}, function (data) {
							alert(url);

						});
					}

				</script>
            </div>
		</div>
    </section>
	<!-- jQuery 2.2.3 -->

</div>
<#include "../base/footer.tpl">