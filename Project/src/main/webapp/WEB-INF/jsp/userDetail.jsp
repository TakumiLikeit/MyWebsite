<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>ユーザー詳細画面</title>
    <!-- import Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <header class="mb-3">
		<nav class="navbar navbar-dark bg-dark text-light">   
			<div class="container-fluid">
                         
                    <div class="navbar-brand">
                        <h4>ユーザー詳細画面</h4>
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
        <div class="card mb-1">
            <div class="card-header bg-secondary text-white">
                <h5>ユーザー詳細</h5>
            </div>

            <div class="car-body mt-4">
                <div class="col-8 offset-4 my-sm-3">

                    <div class="form-group row">
                        <label for="login-id" class="col-lg-2">ログインID:</label>
                        <div class="col-lg-9">
                            <p id="login-id">${userInfo.loginId}</p>
                        </div>
                    </div>

                    <!--パスワードは表示したり、非表示にしたりできるようにしたい-->
                    <!-- 
                    <div class="form-group row">
                        <label for="password" class="col-lg-2">パスワード:</label>
                        <div class="col-lg-9">
                            <p id="login-id">${userInfo.password}</p>
                        </div>
                    </div>
                     -->

                    <div class="form-group row">
                        <label for="user-name" class="col-lg-2">ユーザー名:</label>
                        <div class="col-lg-9">
                            <p id="user-name">${userInfo.name}</p>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-3">
                            <a href="UserUpdateServlet" type="button" class="btn btn-primary btn-block">編集する</a>
                        </div>
                        <div class="col-3">
                            <a href="UserWithdrawServlet" type="button" class="btn btn-danger btn-block">退会する</a>
                        </div>
                    </div>
                        
                </div>
            </div>
        </div>

        <!--containerの中、cardの外-->
        <div class="mt-4">
            <a href="ExpenseListServlet">戻る</a>
        </div>
    </div>

</body>

</html>