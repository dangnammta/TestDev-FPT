<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Administrator</title>
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<!-- Bootstrap 3.3.6 -->
		<#include "./css.tpl">

	</head>
	<body class="hold-transition skin-blue sidebar-mini">
		<div class="wrapper">
			<header class="main-header">
				<!-- Logo -->
				<a class="logo" href="/user/list">
					<!-- mini logo for sidebar mini 50x50 pixels -->
					<span class="logo-mini">ADM</span>
					<!-- logo for regular state and mobile devices -->
					<span class="logo-lg"><b>Administrator</b></span>
				</a>

				<!-- Header Navbar -->
				<nav class="navbar navbar-static-top" role="navigation">
					<!-- Sidebar toggle button-->
					<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
						<span class="sr-only">Toggle navigation</span>
					</a>
					<!-- Navbar Right Menu -->
					<div class="navbar-custom-menu">
						<ul class="nav navbar-nav">
							<!-- User Account Menu -->
							<li class="dropdown user user-menu">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									<img src="/img/avatar.jpg" class="user-image" alt="User Image">
									<span class="hidden-xs">Quang Hòa</span><span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="#">Setting</a></li>
									<li><a href="#">Profile</a></li>
									<li role="separator" class="divider"></li>
									<li><a href="#">Log out</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</nav>
			</header>
			<!-- Left side column. contains the logo and sidebar -->
			<aside class="main-sidebar">
				<!-- sidebar: style can be found in sidebar.less -->
				<section class="sidebar">
					<!-- Sidebar user panel (optional) -->
					<div class="user-panel">
						<div class="pull-left image">
							<img src="/img/avatar.jpg" class="img-circle" alt="User Image">
						</div>
						<div class="pull-left info">
							<p>Quang Hòa</p>
							<!-- Status -->
							<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
						</div>
					</div>


					<!-- Sidebar Menu -->
					<ul class="sidebar-menu">
						<li class="header">HEADER</li>
						<li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>
						<li class="treeview">
							<a href="#"><i class="fa fa-link"></i> <span>User</span>
								<span class="pull-right-container">
									<i class="fa fa-angle-left pull-right"></i>
								</span>
							</a>
							<ul class="treeview-menu">
								<li><a href="/user/create">Add User</a></li>
								<li><a href="/user/list/1">List User</a></li>
							</ul>
						</li>
						<li class="treeview">
							<a href="#"><i class="fa fa-link"></i> <span>Role</span>
								<span class="pull-right-container">
									<i class="fa fa-angle-left pull-right"></i>
								</span>
							</a>
							<ul class="treeview-menu">
								<li><a href="/role/create">Add Role</a></li>
								<li><a href="/role/list">List Role</a></li>
							</ul>
						</li>
						<li class="treeview">
							<a href="#"><i class="fa fa-link"></i> <span>Role User</span>
								<span class="pull-right-container">
									<i class="fa fa-angle-left pull-right"></i>
								</span>
							</a>
							<ul class="treeview-menu">
								<li><a href="/roleuser/create">Add Role User</a></li>
								<li><a href="/roleuser/list">List Role User</a></li>
							</ul>
						</li>
						<li class="treeview">
							<a href="#"><i class="fa fa-link"></i> <span>Permission</span>
								<span class="pull-right-container">
									<i class="fa fa-angle-left pull-right"></i>
								</span>
							</a>
							<ul class="treeview-menu">
								<li><a href="/permission/create">Add Permission</a></li>
								<li><a href="/permission/list">List Permission</a></li>
							</ul>
						</li>
						<li class="treeview">
							<a href="#"><i class="fa fa-link"></i> <span>Permission User</span>
								<span class="pull-right-container">
									<i class="fa fa-angle-left pull-right"></i>
								</span>
							</a>
							<ul class="treeview-menu">
								<li><a href="/permissionuser/create">Add Permission User</a></li>
								<li><a href="/permissionuser/list">List Permission User</a></li>
							</ul>
						</li>
					</ul>
					<!-- /.sidebar-menu -->
				</section>
				<!-- /.sidebar -->
			</aside>