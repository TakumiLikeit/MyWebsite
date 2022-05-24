<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>出費一覧画面</title>
    <!-- import Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style type="text/css">
        .space-h {
            height: 20px;
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

                <div class="navbar-brand">
                    <h4>出費一覧画面</h4>
                </div>

                
                <ul class="navbar-nav ml-auto">
                    <!--横並べにする-->
                    <div class="form-inline">
                        <!--リスト、classはnav-item-->
                        <li class="nav-item mr-2">
                            <span class="navbar-text">${userInfo.name} さん</span>
                        </li>

                        <li class="nav-item">
                            <!--nav-linkにするとアンダーバーが表示されない-->
                            <a class="nav-link text-danger" href="LogoutServlet">ログアウト</a>
                        </li>
                    </div>
                </ul>

            </div>
        </nav>
    </header>

    <!--<div class="space-h"></div>-->
    <!--headerの下に隙間を作りたかった-->

    <div class="container-fluid">
        <div class="alert alert-danger" role="alert">該当する出費はありません。</div>

        <!--my-1で上下にマージン追加-->
        <div class="offset-10 my-2">
            <a href="AddExpenseServlet">出費を追加する</a>
        </div>

        <div class="card mb-1">
            <!--cardはテーブルのようなボックスを作り出す-->
            <!--container-fluidは画面幅に応じてサイズが流動的に変動-->
            <div class="card-header bg-secondary text-white">
                <h5>検索条件</h5>
            </div>
            <div class="space-h"></div>
            <div class="car-body">
                <!--とりあえずcontainerで囲む、offsetは必要なくなる?-->
                <!--my-smで上下のマージンを追加-->
                <div class="col-8 offset-2 my-sm-3">
                    <!--全体をoffset-3で囲んでしまい、col-6をセットしてしまえば真ん中にフォーマットできる-->
                    <!--labelのforはあってもなくても、見た目は変わらないと思われる-->
                    <form action="ExpenseListServlet" method="post">

                        <div class="form-group row">
                            <!--入力部品をform-groupで囲み-->
                            <!--rowを入れることでformを横並びに-->
                            <!--control-labelがcol-form-lableに改名されたらしい-->
                            <label for="exspense-name" class="col-lg-2 col-form-label">商品名</label>
                            <div class="col-lg-9">
                                <!--ここのdivにもclassを付与して良い-->
                                <input id="expense-name" type="text" class="form-control">
                                <!--入力部分をform-controlで囲む-->
                            </div>
                        </div>

                        <!--カテゴリはcheckbox,select,radioなどがあるが、selectが良い-->
                        <div class="form-group row">
                            <label for="category" class="col-lg-2 col-form-label">カテゴリ</label>
                            <div>
                                <!--なぜかoffset-1を追加しないと、頭が合わなかった-->
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
                                <input id="duration1" name="start-date" type="date" class="form-control">
                            </div>
                            <div class="col-1" style="text-align:center;">~</div>
                            <div class="col-lg-4">
                                <input id="duration2" name="end-date" type="date" class="form-control">
                            </div>
                        </div>

                        <button class="btn btn-primary offset-9 col-2" type="submit">検索</button>
                    </form>
                </div>
            </div>


        </div>
    </div>
    </div>

    <!--cardは2つに分けなくてもいいのだろうけど、card-headerを追加したいため作成する-->
    <div class="container-fluid mb-5">
        <div class="card">
            <!--cardはテーブルのようなボックスを作り出す-->
            <!--container-fluidは画面幅に応じてサイズが流動的に変動-->
            <div class="card-header bg-secondary text-white">
                <h5>出費一覧</h5>
            </div>
            <!--最悪bodyを無くして、フォーマットを整える-->
            <div class="offset-10 my-3">
                <a href=#expenseTotal id="jump">合計金額へ飛ぶ</a>
            </div>
                
                <!--table-stripedで白黒のしましまが入り見やすくなる-->
                <table class="table table-striped mb-1">
                    <!--theadでtableのheader-->
                    <thead>
                        <tr>
                            <th class="table-h col-3">日付</th>
                            <th class="table-h col-3">カテゴリ</th>
                            <th class="table-h col-3">出費名</th>
                            <th class="table-h col-3">金額</th>
                        </tr>
                    </thead>
                    <!--tbodyでtableのbody-->
                    <tbody>
                        <tr>
                            <td class="table-h col-3">2022年4月1日</td>
                            <td class="table-h col-3">食費</td>
                            <td>
	                            <a href="UpdateExpenseServlet?id=1" class="table-h col-3">りんご</a>
                            </td>
                            <td class="table-h col-3">120</td>
                        </tr>
                        <tr>
                            <td class="table-h col-3">2022年4月2日</td>
                            <td class="table-h col-3">娯楽</td>
                            <td><a href="UpdateExpenseServlet?id=2" class="table-h col-3">温泉</a></td>
                            <td class="table-h col-3">2000</td>
                        </tr>

                        <tr>
                            <th style="text-align:right" class="table-h" colspan="3" id="expenseTotal">合計金額：</th>
                            <td style="font-size:20px" class="table-h">2120円</td>
                        </tr>

                    </tbody>
                </table>            

        </div>
    </div>
    </div>


</body>

</html>