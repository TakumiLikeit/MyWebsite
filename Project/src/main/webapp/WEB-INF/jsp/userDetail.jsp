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
                        <h4>ユーザー詳細画面</h4>
                    </div>
                
            </nav>
        </div>
    </header>

    <!--<div class="space-h"></div>-->
    <!--headerの下に隙間を作りたかった-->

    <div class="container-fluid">
        <div class="card mb-1">
            <!--cardはテーブルのようなボックスを作り出す-->
            <!--container-fluidは画面幅に応じてサイズが流動的に変動-->
            <div class="card-header bg-secondary text-white">
                <h5>ユーザー詳細</h5>
            </div>

            <div class="space-h"></div>
            <div class="car-body">
                <!--とりあえずcontainerで囲む、offsetは必要なくなる?-->
                <!--myで上のマージンを追加-->
                <div class="col-8 offset-4 my-sm-3">
                    <!--全体をoffset-3で囲んでしまい、col-6をセットしてしまえば真ん中にフォーマットできる-->
                    <!--labelのforはあってもなくても、見た目は変わらないと思われる-->
                    


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
                            <a href="UserWithDrawServlet" type="button" class="btn btn-danger btn-block">退会する</a>
                        </div>
                    </div>
                        
                </div>
            </div>
        </div>

        <!--containerの外cardの外-->
        <div class="mt-4">
            <a href="ExpenseListServlet">戻る</a>
        </div>
    </div>
    </div>

</body>

</html>