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
        <!--headerの中に埋め込むイメージ-->
        <nav class="navbar navbar-dark bg-dark text-light">
            <!--navってなんだ-->
            <!--navをいれるとちょっと下がる-->
            <div class="container-fluid">
                <div class="col-6 offset-1">ログイン画面</div>
            </div>
        </nav>
    </header>

    <div style="height:10px"></div><!--headerの下に隙間を作りたかった-->

    <div class="container-fluid">
        <!--container-fluidは画面幅に応じてサイズが流動的に変動-->

        <!--とりあえずcontainerで囲む、offsetは必要なくなる?-->
        <!--全体をoffset-3で囲んでしまい、col-6をセットしてしまえば真ん中にフォーマットできる-->
        <div class="col-6 offset-3">
        
        	<c:if test="${errMsg != null}">
				<div class="alert alert-danger" role="alert">${errMsg}</div>
			</c:if>
			
            <form action="LoginServlet" method="post">
                <div class="form-group row">
                    <!--入力部品をform-groupで囲み-->
                    <!--rowを入れることでformを横並びに-->
                    <label for="loginId" class="col-lg-3 col-form-label">ログインID</label>
                    <div class="col-lg-9">
                        <!--ここのdivにもclassを付与して良い-->
                        <input id="login-id" name="login-id" type="text" class="form-control" value="${loginId}">
                        <!--入力部分をform-controlで囲む-->
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
                <!--単純にここをdivで囲むだけで、スペースを作ることができる-->
                <div>
                    <a id="userAdd" href="UserAddServlet">新規登録</a>
                </div>
            </div>
        </div>

    </div>
    </div>

</body>

</html>