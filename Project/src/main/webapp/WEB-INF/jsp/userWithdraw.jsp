<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>退会画面</title>
    <!-- import Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style type="text/css">
    p {
        text-align:center;
    }

    label {
        text-align:center;
    }
    </style>
</head>

<body>
    <header class="mb-3">
		<nav class="navbar navbar-dark bg-dark text-light">
				<div class="container-fluid">
	            
	                <div class="navbar-brand">
	                    <h4>退会画面</h4>
	                </div>
	                <ul class="navbar-nav ml-auto">
		                <div class="form-inline">
		                    <li class="nav-item mr-2">
		                    	<a class="nav-link navbar-text" href="UserDetailServlet">${userInfo.name} さん</a>
		                    </li>
		
		                    <li class="nav-item">
		                        <a class="nav-link text-danger" href="LogoutServlet">ログアウト</a>
		                    </li>
		                </div>
	                </ul>   
        	</div>
		</nav>
    </header>
    <div class="container-fluid">

        <div class="card">
            <div class="card-header">
                <p class="mt-3">${userInfo.name} さん、本当に退会しますか？</p>
            </div>
            <div class="card-body my-sm-4">

                <div class="row">
                    <div class="offset-3 col-3">
                        <a href="UserDetailServlet" class="btn btn-primary btn-block">退会しない</a>
                    </div>
                    <div class="col-3">
                        <form action="UserWithdrawServlet" method="post">
                                <input type="submit" id="yes" name="yes" value="退会する" class="btn btn-danger  btn-block">
                        </form>
                    </div>
                </div>

            </div>
        </div>

        <!--containerの中、cardの外-->
        <div class="mt-4">
            <a href="UserDetailServlet">戻る</a>
        </div>
    </div>

</body>

</html>