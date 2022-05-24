<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>出費編集画面</title>
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
        <nav class="navbar navbar-dark bg-dark text-light">
            <!--navってなんだ-->
            <!--navをいれるとちょっと下がる-->
            <div class="container-fluid">
                <div class="col-6 offset-1">
                    <h4>出費編集画面</h4>
                </div>
            </div>
        </nav>
    </header>

    <!--<div class="space-h"></div>-->
    <!--headerの下に隙間を作りたかった-->

    <div class="container-fluid">
        <div class="alert alert-danger" role="alert">入力されたデータに誤りがあります.</div>

        <div class="card mb-1">
            <!--cardはテーブルのようなボックスを作り出す-->
            <!--container-fluidは画面幅に応じてサイズが流動的に変動-->
            <div class="card-header bg-secondary text-white">
                <h5>出費編集</h5>
            </div>

            <div class="space-h"></div>
            <div class="car-body">
                <!--とりあえずcontainerで囲む、offsetは必要なくなる?-->
                <!--myで上のマージンを追加-->
                <div class="col-8 offset-2 my-sm-3">
                    <!--全体をoffset-3で囲んでしまい、col-6をセットしてしまえば真ん中にフォーマットできる-->
                    <!--labelのforはあってもなくても、見た目は変わらないと思われる-->
                    <form action="ExpenseListServlet" method="post">

                        <div class="form-group row">
                            <!--入力部品をform-groupで囲み-->
                            <!--rowを入れることでformを横並びに-->
                            <!--control-labelがcol-form-lableに改名されたらしい-->
                            <label for="exspense-name" class="col-lg-2 col-form-label">出費名</label>
                            <div class="col-lg-9">
                                <!--ここのdivにもclassを付与して良い-->
                                <input id="expense-name" type="text" class="form-control" value="りんご">
                                <!--入力部分をform-controlで囲む-->
                            </div>
                        </div>


                        <div class="form-group row">
                            <!--入力部品をform-groupで囲み-->
                            <!--rowを入れることでformを横並びに-->
                            <!--control-labelがcol-form-lableに改名されたらしい-->
                            <label for="price" class="col-lg-2 col-form-label">値段</label>
                            <div class="col-lg-9">
                                <!--ここのdivにもclassを付与して良い-->
                                <input id="price" type="text" class="form-control" value="120">
                                <!--入力部分をform-controlで囲む-->
                            </div>
                        </div>

                        <!--カテゴリはcheckbox,select,radioなどがあるが、selectが良い-->
                        <div class="form-group row">
                            <label for="category" class="col-lg-2 col-form-label">カテゴリ</label>
                            <div>
                                <!--なぜかoffset-1を追加しないと、頭が合わなかった-->
                                <!--requestパラメターから値をゲットして、selectで初期化する方法を見つけなければならない-->
                                <select id="category" name="category" class="form-control offset-1">
                                    <option value="">選択する</option>
                                    <option value="food">食費</option>
                                    <option value="leisure">娯楽</option>
                                    <option value="restaurant">レストラン</option>
                                    <option value="travel">旅・レジャー</option>
                                    <option value="transport">交通</option>
                                    <option value="medical">医療</option>
                                    <option value="personal-care">パーソナルケア</option>
                                    <option value="education">教育</option>
                                    <option value="gadget">電子機器</option>
                                    <option value="communication">スマホ・通信</option>
                                    <option value="housing">住宅</option>
                                    <option value="heat-light-water">水道光熱費</option>
                                    <option value="clothing">衣類</option>
                                    <option value="others">その他</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="duration" class="control-label col-2">期間</label>
                            <div class="col-lg-4">
                                <input id="duration1" name="start-date" type="date" class="form-control" value="2022-04-01">
                            </div>
                            <div class="col-1" style="text-align:center;">~</div>
                            <div class="col-lg-4">
                                <input id="duration2" name="end-date" type="date" class="form-control" value="2022-04-01">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="note" class="col-lg-2 col-form-label">メモ</label>
                            <div class="col-lg-9">
                                <textarea id="note" name="note" class="form-control" rows="4" cols="50">お腹がすいたため</textarea>
                            </div>
                        </div>

                        <button class="btn btn-primary offset-7 col-2" type="submit">更新</button>
                        <a href="DeleteExpenseServlet" class="btn btn-danger col-2">削除する</a>
                    </form>
                </div>
            </div>
        </div>

        <!--containerの外cardの外-->
        <div class="mt-4">
            <a href="ExpenseListServlet">出費一覧へ戻る</a>
        </div>
    </div>
    </div>

</body>

</html>