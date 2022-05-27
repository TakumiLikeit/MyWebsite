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
    <style type="text/css">
        .space-h {
            height: 25px;
        }

        .table-h {
            height: 50px;
        }

        th {
            font-size: 20px;
            text-align: center;
        }

        td {
            text-align: center;
        }
    </style>
</head>

<body>
    <!--mb-3でマージンを作成する-->
    <header class="mb-3">
        <!--headerの中に埋め込むイメージ-->
        <div class="container-fluid">
            <nav class="navbar navbar-dark bg-dark text-light">                
                     <div class="navbar-brand">
                        <h4>ユーザー更新画面</h4>
                    </div>
                <ul class="navbar-nav ml-auto">
	                <!--横並べにする-->
	                <div class="form-inline">
	                    <!--リスト、classはnav-item-->
	                    <li class="nav-item mr-2">
	                    	<a class="nav-link navbar-text" href="UserDetailServlet">${userInfo.name} さん</a>
	                        <!-- <span class="navbar-text">${userInfo.name} さん</span> -->
	                    </li>
	
	                    <li class="nav-item">
	                        <!--nav-linkにするとアンダーバーが表示されない-->
	                        <a class="nav-link text-danger" href="LogoutServlet">ログアウト</a>
	                    </li>
	                </div>
                </ul>                
            </nav>
        </div>
    </header>

    <!--<div class="space-h"></div>-->
    <!--headerの下に隙間を作りたかった-->

    <div class="container-fluid">
    	<c:if test="${errMsg!=null}">
        	<div class="alert alert-danger" role="alert">${errMsg}</div>
		</c:if>
    	<c:if test="${errMsgPassword!=null}">
        	<div class="alert alert-danger" role="alert">${errMsgPassword}</div>
		</c:if>
    	<c:if test="${errMsgLoginId!=null}">
        	<div class="alert alert-danger" role="alert">${errMsgLoginId}</div>
		</c:if>
		
        <div class="alert alert-danger" role="alert">
            <ol>
                <li>空欄項目があります</li>
                <li>同じログインIDの人がいます</li>
            </ol>
        </div>
        <div class="card mb-1">
            <!--cardはテーブルのようなボックスを作り出す-->
            <!--container-fluidは画面幅に応じてサイズが流動的に変動-->
            <div class="card-header bg-secondary text-white">
                <h5>ユーザー更新</h5>
            </div>

            <div class="space-h"></div>
            <div class="car-body">
                <!--とりあえずcontainerで囲む、offsetは必要なくなる?-->
                <!--myで上のマージンを追加-->
                <div class="col-8 offset-2 my-sm-3">
                    <!--全体をoffset-3で囲んでしまい、col-6をセットしてしまえば真ん中にフォーマットできる-->
                    <!--labelのforはあってもなくても、見た目は変わらないと思われる-->
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

        <!--containerの外cardの外-->
        <div class="mt-4">
            <a href="UserDetailServlet">戻る</a>
        </div>
    </div>

</body>

</html>