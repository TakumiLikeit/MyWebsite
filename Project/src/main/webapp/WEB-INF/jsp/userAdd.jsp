<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>新規登録画面</title>
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
                    <div class="offset-1">
                        <h4>新規登録画面</h4>
                    </div>
                
            </nav>
        </div>
    </header>

    <!--<div class="space-h"></div>-->
    <!--headerの下に隙間を作りたかった-->

    <div class="container-fluid">
    	<c:if test="${errMsg!=null}">
        	<div class="alert alert-danger" role="alert">${errMsg} 空欄項目があります</div>
		</c:if>
    	<c:if test="${errMsgPrice!=null}">
        	<div class="alert alert-danger" role="alert">${errMsgLoginId} 同じログインIDの人がいます</div>
		</c:if>
        <div class="card mb-1">
            <!--cardはテーブルのようなボックスを作り出す-->
            <!--container-fluidは画面幅に応じてサイズが流動的に変動-->
            <div class="card-header bg-secondary text-white">
                <h5>新規登録</h5>
            </div>

            <div class="space-h"></div>
            <div class="car-body">
                <!--とりあえずcontainerで囲む、offsetは必要なくなる?-->
                <!--myで上のマージンを追加-->
                <div class="col-8 offset-2 my-sm-3">
                    <!--全体をoffset-3で囲んでしまい、col-6をセットしてしまえば真ん中にフォーマットできる-->
                    <!--labelのforはあってもなくても、見た目は変わらないと思われる-->
                    <form action="UserAddServlet" method="post">

                        <div class="form-group row">
                            <label for="exspense-name" class="col-lg-2 col-form-label">ログインID</label>
                            <div class="col-lg-9">
                                <input id="login-id" name="login-id" type="text" class="form-control" value="${loginId} admin">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="exspense-name" class="col-lg-2 col-form-label">パスワード</label>
                            <div class="col-lg-9">
                                <input id="password" name="password" type="password" class="form-control" value="${password} password">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="exspense-name" class="col-lg-2 col-form-label">パスワード確認</label>
                            <div class="col-lg-9">
                                <input id="password-confirm" name="password-confirm" type="password" class="form-control">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="exspense-name" class="col-lg-2 col-form-label">ユーザー名</label>
                            <div class="col-lg-9">
                                <input id="name" name="name" type="text" class="form-control" value="${userName} 管理者">
                            </div>
                        </div>

                        <button class="btn btn-primary offset-9 col-2" type="submit">登録</button>
                    </form>
                </div>
            </div>
        </div>

        <!--containerの外cardの外-->
        <div class="mt-4">
            <a href="LoginServlet">ログイン画面へ戻る</a>
        </div>
    </div>
    </div>

</body>

</html>