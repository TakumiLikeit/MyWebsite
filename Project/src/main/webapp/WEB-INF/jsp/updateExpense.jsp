<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
    	<c:if test="${errMsg!=null}">
        	<div class="alert alert-danger" role="alert">${errMsg}</div>
		</c:if>
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

						<!-- idをサーブレットに返さなければいけない -->
						<input id="expense-id" name="expense-id" type="hidden" value="${expense.id}">
						
                        <div class="form-group row">
                            <!--入力部品をform-groupで囲み-->
                            <!--rowを入れることでformを横並びに-->
                            <!--control-labelがcol-form-lableに改名されたらしい-->
                            <label for="exspense-name" class="col-lg-2 col-form-label">出費名</label>
                            <div class="col-lg-9">
                                <!--ここのdivにもclassを付与して良い-->
                                <input id="expense-name" type="text" class="form-control" value="${expense.name}">
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
                                <input id="price" type="text" class="form-control" value="${expense.price}">
                                <!--入力部分をform-controlで囲む-->
                            </div>
                        </div>

                        <!--カテゴリはcheckbox,select,radioなどがあるが、selectが良い-->
                        <div class="form-group row">
                            <label for="category" class="col-lg-2 col-form-label">カテゴリ</label>
                            <div>
                                <!--なぜかoffset-1を追加しないと、頭が合わなかった-->
                                <!--requestパラメターから値をゲットして、selectで初期化する方法を見つけなければならない-->
                                <select id="categoryName" name="category" class="form-control offset-1">
                                    <option ${categoryId.equals("")?"selected":""} value="">選択する</option>
                                    <option ${categoryId.equals("1")?"selected":""} value="1">食費</option>
                                    <option ${categoryId.equals("2")?"selected":""} value="2">娯楽</option>
                                    <option ${categoryId.equals("3")?"selected":""} value="3">レストラン</option>
                                    <option ${categoryId.equals("4")?"selected":""} value="4">旅・レジャー</option>
                                    <option ${categoryId.equals("5")?"selected":""} value="5">交通</option>
                                    <option ${categoryId.equals("6")?"selected":""} value="6">医療</option>
                                    <option ${categoryId.equals("7")?"selected":""} value="7">パーソナルケア</option>
                                    <option ${categoryId.equals("8")?"selected":""} value="8">教育</option>
                                    <option ${categoryId.equals("9")?"selected":""} value="9">電子機器</option>
                                    <option ${categoryId.equals("10")?"selected":""} value="10">スマホ・通信</option>
                                    <option ${categoryId.equals("11")?"selected":""} value="11">住宅</option>
                                    <option ${categoryId.equals("12")?"selected":""} value="12">水道光熱費</option>
                                    <option ${categoryId.equals("13")?"selected":""} value="13">衣類</option>
                                    <option ${categoryId.equals("14")?"selected":""} value="14">その他</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="duration" class="control-label col-2">期間</label>
                            <div class="col-lg-4">
                                <input id="duration1" name="start-date" type="date" class="form-control" value="${expense.startDate}">
                            </div>
                            <div class="col-1" style="text-align:center;">~</div>
                            <div class="col-lg-4">
                                <input id="duration2" name="end-date" type="date" class="form-control" value="${expense.endDate}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="note" class="col-lg-2 col-form-label">メモ</label>
                            <div class="col-lg-9">
                                <textarea id="note" name="note" class="form-control" rows="4" cols="50">${expense.note}</textarea>
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