<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>出費登録画面</title>
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
        <div class="container-fluid">
            <nav class="navbar navbar-dark bg-dark text-light">
                <h4>出費削除画面</h4>
            </nav>
        </div>
    </header>
    <div class="container-fluid">

        <div class="card">
            <div class="card-header">
                <p class="mt-3">以下の出費を削除しますか？</p>
            </div>
            <div class="card-body">
                <div class="col-8 offset-4 my-sm-3">
                    <form action="expenseDeleteServlet" method="post">
                        <div class="row">
                            <label for="exspense-name" class="col-lg-2">出費名:</label>
                            <div class="col-lg-3">
                                <!--ここのdivにもclassを付与して良い-->
                                <input type="hidden" id="expense-name" name="expense-name" value="りんご">
                                <p>りんご</p>
                                <!--入力部分をform-controlで囲む-->
                            </div>
                        </div>

                        <div class="row">
                            <label for="price" class="col-lg-2">値段:</label>
                            <div class="col-lg-3">
                                <input type="hidden" id="price" name="price" value="120">
                                <p>120</p>
                            </div>
                        </div>

                        <div class="row">
                            <label for="category" class="col-lg-2">カテゴリ:</label>
                            <div class="col-lg-3">
                                <input type="hidden" id="food" name="food" value="食費">
                                <p>食費</p>
                            </div>
                        </div>

                        <div class="row">
                            <label for="duration" class="col-lg-2">日付:</label>
                            <div class="col-lg-3">
                                <input type="hidden" id="date" name="date" class="form-control" value="2022-04-01">
                                <p>2022年4月1日</p>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="row">
                    <div class="offset-3 col-3">
                        <a href="expenseListServlet" class="btn btn-primary btn-block">いいえ</a>
                    </div>
                    <div class="col-3">
                        <form action="expenseDeleteServle" method="post">
                                <input type="button" id="yes" name="yes" value="はい" class="btn btn-danger  btn-block">
                        </form>
                    </div>
                </div>

            </div>
        </div>

        <!--containerの外cardの外-->
        <div class="mt-4">
            <a href="expenseList.html">出費一覧へ戻る</a>
        </div>
    </div>

</body>

</html>