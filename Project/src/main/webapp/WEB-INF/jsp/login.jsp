<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>ログイン画面</title>
    <!-- import Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <header>
        <nav class="navbar navbar-dark bg-dark text-light mb-4">
            <div class="container-fluid">
                <div class="navbar-brand">
                	<h4>ログイン画面</h4>
                </div>
            </div>
        </nav>
    </header>

    <div class="container-fluid">
        <div class="col-6 offset-3">
        
        	<c:if test="${errMsg != null}">
				<div class="alert alert-danger" role="alert">${errMsg}</div>
			</c:if>
			
            <form action="LoginServlet" method="post">
                <div class="form-group row">
                    <label for="loginId" class="col-lg-3 col-form-label">ログインID</label>
                    <div class="col-lg-9">
                        <input id="login-id" name="login-id" type="text" class="form-control" value="${loginId}">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="password" class="col-lg-3 col-form-label">パスワード</label>
                    <div class="col-lg-9">
                        <input id="password" name="password" type="password" class="form-control" value="${password}">
                    </div>
                </div>
                <button class="btn btn-secondary btn-block" type="submit">ログイン</button>
            </form>
        </div>

        <div class="container-fluid">
            <div class="offset-9">
                <label for="userAdd"></label>
                <div>
                    <a id="userAdd" href="UserAddServlet">新規登録</a>
                </div>
            </div>
        </div>

    </div>

</body>

</html>