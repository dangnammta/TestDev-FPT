<#include "../base/header.tpl">
<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

		<h1>List user</h1>
		<div class="box">
			<!-- /.box-header -->
			<div class="box-body">
				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>id</th>
							<th>Username</th>
							<th>Email</th>
							<th>Created date</th>
							<th>Actived date</th>
							<th>Updated date</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${obj.id}</td>
							<td>${obj.username}</td>
							<td>${obj.email}</td>
							<td>${obj.createdDate}</td>
							<td>${obj.activedDate}</td>
							<td>${obj.updatedDate}</td>
							<td>${obj.status}</td>
							<td>
								<a>Update</a>/<a>Delete</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<!-- jQuery 2.2.3 -->
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
</div>
<#include "../base/footer.tpl">
