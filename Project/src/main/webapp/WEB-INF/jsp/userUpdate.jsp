<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>ユーザー更新画面</title>
    <!-- import Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <header class="mb-3">
        
		<nav class="navbar navbar-dark bg-dark text-light">      
            <div class="container-fluid">          
                     <div class="navbar-brand">
                        <h4>ユーザー更新画面</h4>
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
    	<c:if test="${errMsg!=null}">
        	<div class="alert alert-danger" role="alert">${errMsg}</div>
		</c:if>

        <div class="card mb-1">
            <div class="card-header bg-secondary text-white">
                <h5>ユーザー更新</h5>
            </div>

            <div class="car-body mt-4">
                <div class="col-8 offset-2 my-sm-3">
                    <form action="UserUpdateServlet" method="post">

                        <div class="row  mt-1">
                            <label for="login-id" class="col-lg-3">ログインID</label>
                            <div class="col-lg-9">
                                <p id="login-id">${userInfo.loginId}</p>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="password" class="col-lg-3 col-form-label">パスワード</label>
                            <div class="col-lg-9">
                                <input id="password" name="password" type="password" class="form-control" value="${password!=null?password :""}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="password-confirm" class="col-lg-3 col-form-label">パスワード確認</label>
                            <div class="col-lg-9">
                                <input id="password-confirm" name="password-confirm" type="password" class="form-control">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="user-name" class="col-lg-3 col-form-label">ユーザー名</label>
                            <div class="col-lg-9">
                                <input id="user-name" name="user-name" type="text" class="form-control" value="${userName!=null?userName:userInfo.name}">
                            </div>
                        </div>

                        <button class="btn btn-primary offset-9 col-2" type="submit">更新</button>
                    </form>
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