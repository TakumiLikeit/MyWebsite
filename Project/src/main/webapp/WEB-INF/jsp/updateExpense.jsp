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
</head>

<body>
    <header class="mb-3">
        <nav class="navbar navbar-dark bg-dark text-light">
            <div class="container-fluid">
                <div class="navbar-brand">
                    <h4>出費編集画面</h4>
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
                <h5>出費編集</h5>
            </div>

            <div class="car-body mt-4">
                <div class="col-8 offset-2 my-sm-3">
                    <form action="UpdateExpenseServlet" method="post">

						<!-- expenseIdを返さなければならない -->
						<input id="id" name="id" type="hidden" value="${expense.id!=null?expense.id:expenseId}">
						
                        <div class="form-group row">
                            <label for="exspense-name" class="col-lg-2 col-form-label">出費名</label>
                            <div class="col-lg-9">
                                <input id="expense-name" name="expense-name" type="text" class="form-control" value="${expense.name!=null?expense.name:expenseName}">
                            </div>
                        </div>


                        <div class="form-group row">
                            <label for="price" class="col-lg-2 col-form-label">値段</label>
                            <div class="col-lg-9">
                                <input id="price" name="price" type="text" class="form-control" value="${expense.price!=null?expense.price:price}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="category" class="col-lg-2 col-form-label">カテゴリ</label>
                            <div>
                                <!--なぜかoffset-1を追加しないと、頭が合わなかった-->
                                <select id="category" name="category" class="form-control offset-1">
                                    <option ${expense.categoryId==0||categoryId.equals("")?"selected":""} value="">選択する</option>
                                    <option ${expense.categoryId==1||categoryId.equals("1")?"selected":""} value="1">食費</option>
                                    <option ${expense.categoryId==2||categoryId.equals("2")?"selected":""} value="2">娯楽</option>
                                    <option ${expense.categoryId==3||categoryId.equals("3")?"selected":""} value="3">レストラン</option>
                                    <option ${expense.categoryId==4||categoryId.equals("4")?"selected":""} value="4">旅・レジャー</option>
                                    <option ${expense.categoryId==5||categoryId.equals("5")?"selected":""} value="5">交通</option>
                                    <option ${expense.categoryId==6||categoryId.equals("6")?"selected":""} value="6">医療</option>
                                    <option ${expense.categoryId==7||categoryId.equals("7")?"selected":""} value="7">パーソナルケア</option>
                                    <option ${expense.categoryId==8||categoryId.equals("8")?"selected":""} value="8">教育</option>
                                    <option ${expense.categoryId==9||categoryId.equals("9")?"selected":""} value="9">電子機器</option>
                                    <option ${expense.categoryId==10||categoryId.equals("10")?"selected":""} value="10">スマホ・通信</option>
                                    <option ${expense.categoryId==11||categoryId.equals("11")?"selected":""} value="11">住宅</option>
                                    <option ${expense.categoryId==12||categoryId.equals("12")?"selected":""} value="12">水道光熱費</option>
                                    <option ${expense.categoryId==13||categoryId.equals("13")?"selected":""} value="13">衣類</option>
                                    <option ${expense.categoryId==14||categoryId.equals("14")?"selected":""} value="14">その他</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="expense-date" class="control-label col-2">日付</label>
                            <div class="col-lg-4">
                                <input id="expense-date" name="expense-date" type="date" class="form-control" value="${expense.expenseDate!=null?expense.expenseDate:expenseDate}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="note" class="col-lg-2 col-form-label">メモ</label>
                            <div class="col-lg-9">
                                <textarea id="note" name="note" class="form-control" rows="4" cols="50">${expense.note!=null?expense.note:note}</textarea>
                            </div>
                        </div>

                        <button class="btn btn-primary offset-7 col-2" type="submit">更新</button>
                        <a href="DeleteExpenseServlet?id=${expense.id}" class="btn btn-danger col-2">削除する</a>
                    </form>
                </div>
            </div>
        </div>

        <!--containerの中、cardの外-->
        <div class="mt-4">
            <a href="ExpenseListServlet">出費一覧へ戻る</a>
        </div>
    </div>
    </div>

</body>

</html>