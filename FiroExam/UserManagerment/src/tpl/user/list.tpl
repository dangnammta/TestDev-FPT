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
<script type="text/javascript">
	function  searchData() {
		var num = $("#selectNum").val();
		$.post("/user/list/${page}",
				{
					perpage: num,
				},
				function (data, status) {
					window.location.replace("http://" + window.location.hostname + ":4567" + "/user/list/${page}");
				});
	}

	function  previous() {
		var num = $("#selectNum").val();
		$.post("/user/list/${page-1}",
				{
					perpage: num,
				},
				function (data, status) {
				});
	}

	function nexts() {
		var num = $("#selectNum").val();
		$.post("/user/list/${page+1}",
				{
					perpage: num,
				},
				function (data, status) {

				});
	}
</script>
<div class="content-wrapper">
    <!-- Main content -->
    <section class="content">
		<div class="box-body" >
			<div class="col-md-4">
				<h1>List user</h1>
			</div>
			<div style="float:right ;margin-top: 30px">
				<form action="/user/list/1" method="post" class="form-inline">
					<div class="form-group">
						<label class="sr-only" for="search">Password</label>
						<input type="text" value="${searchCondition}" class="form-control" name="search" id="search" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default ">Search</button>
				</form>

			</div>
		</div>
		<div class="box" id="boxe">
            <div class="box-body">
				<select onchange="changeNumperPage();" class="form-control" id="selectNum" style="width: 75px;margin-bottom: 20px">
					<option>${rowPage}</option>
					<option>5</option>
					<option>10</option>
					<option>20</option>
					<option>50</option>
					<option>100</option>
				</select>
				<script type="text/javascript">
					$(document).ready(function () {
						if (${page} === ${allpage} + 1) {
							document.getElementById("next").disabled = true;
						}
						if (${page} === 1) {
							document.getElementById("previous").disabled = true;
						}
					});
					function changeNumperPage() {
						var num = $("#selectNum").val();
						$.post("/user/list/${page}",
								{
									perpage: num,
								},
								function (data, status) {
									window.location.replace("http://" + window.location.hostname + ":4567" + "/user/list/${page}");
								});
					}
					function show(){
						alert("ok");
					}
				</script>
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
						<#list objs as user>
						<tr onclick="show();">
							<td>
								<#if user.id??>
								${user.id}
								</#if>
							</td>
							<td>
								<#if user.username??>
								${user.username}
								</#if>
							</td>
							<td>
								<#if user.email??>
								${user.email}
								</#if>
							</td>
							</td>
							<td>
								<#if user.createdDate??>
								${user.createdDate?date}
								</#if>
							</td>
							<td>
								<#if user.activedDate??>
								${user.activedDate?date}
								</#if>
							</td>
							<td>
								<#if user.updatedDate??>
								${user.updatedDate?date}
								</#if>
							</td>
							<td>
								<#if user.status??>
								${user.status}
								</#if>
							</td>
							<td>
								<a class="glyphicon glyphicon-pencil" title="Update item" href="/user/update/${user.id}"></a>   /   <a class="glyphicon glyphicon-trash" data-toggle="modal" data-target=".bs-example-modal-sm${user.id}" title="Delete item"></a>
							</td>
						</tr>
					<div class="modal fade bs-example-modal-sm${user.id}" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
						<div style="background-color: white; margin-top: 100px" class="modal-dialog modal-sm" role="document">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title" style="color: red">Warning</h4>
							</div>
							<div class="modal-body">
								Are you want to delete this item have id is ${user.id}
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								<a class="btn btn-primary"  data-dismiss="modal" onclick="del(${user.id})">Delete</a>
							</div>
						</div>
					</div>
					</#list>
					</tbody>
				</table>
				<script type="text/javascript">
					function del(id) {
						var iduser = id;
						$.getJSON("/user/delete/" + iduser,
								{
									id: iduser,
								},
								function (data) {
									if (data.result == 0) {
										window.location.replace("http://" + window.location.hostname + ":4567" + "/user/list");
										//$(".alert-succes").show();
									} else {
										window.location.replace("http://" + window.location.hostname + ":4567" + "/user/list");
										//$(".alert-dange").show();
									}
								});
					}

				</script>

				<ul class = "pagination">
					<li><a href = "/user/list/${page-1}" id="previous" title="Previous">&laquo;</a></li>
					<li class="active"><a>${page}</a></li>
					<li><a href = "/user/list/${page+1}" id="next" class="disabled" title="Next">&raquo;</a></li>
				</ul>


            </div>
		</div>
    </section>
	<!-- jQuery 2.2.3 -->

</div>
<#include "../base/footer.tpl">