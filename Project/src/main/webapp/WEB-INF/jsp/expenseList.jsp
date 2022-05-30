<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>出費一覧画面</title>
    <!-- import Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style type="text/css">

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
    <header class="mb-3">
        <nav class="navbar navbar-dark bg-dark text-light">
            <div class="container-fluid">

                <div class="navbar-brand">
                    <h4>出費一覧画面</h4>
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
        	<div class="alert alert-danger" role="alert">${errMsg} 該当する出費はありません。</div>
		</c:if>

        <div class="offset-10 my-2">
            <a href="AddExpenseServlet">出費を追加する</a>
        </div>

        <div class="card mb-1">
            <div class="card-header bg-secondary text-white">
                <h5>検索条件</h5>
            </div>
            
            <div class="car-body mt-4">
                <div class="col-8 offset-2 my-sm-3">
                    <form action="ExpenseListServlet" method="post">

                        <div class="form-group row">
                            <label for="exspense-name" class="col-lg-2 col-form-label">商品名</label>
                            <div class="col-lg-9">
                                <input id="expense-name" name="expense-name" type="text" class="form-control" value="${expenseName}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="category" class="col-lg-2 col-form-label">カテゴリ</label>
                            <div>
                                <select id="category" name="category" class="form-control offset-1">
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
                                <input id="duration1" name="start-date" type="date" class="form-control" value="${startDate}">
                            </div>
                            <div class="col-1" style="text-align:center;">~</div>
                            <div class="col-lg-4">
                                <input id="duration2" name="end-date" type="date" class="form-control" value="${endDate}">
                            </div>
                        </div>

                        <button class="btn btn-primary offset-9 col-2" type="submit">検索</button>
                    </form>
                </div>
            </div>


        </div>
    </div>

    <div class="container-fluid mb-5">
        <div class="card">
            <div class="card-header bg-secondary text-white">
                <h5>出費一覧</h5>
            </div>
            <div class="offset-10 my-3">
                <a href=#totalExpense id="jump">合計金額へ飛ぶ</a>
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
                    	<c:forEach  var="expense" items="${expenseList}">
		                        <tr>
		                            <td class="table-h col-3"><fmt:formatDate value="${expense.expenseDate}" pattern="yyyy年MM月dd日" /></td>
		                            <td class="table-h col-3">${expense.categoryName}</td>
		                            <td>
			                            <a href="UpdateExpenseServlet?id=${expense.id}" class="table-h col-3">${expense.name}</a>
		                            </td>
		                            <td class="table-h col-3">${expense.price}</td>
		                        </tr>
						</c:forEach>

                        <tr>
                            <th style="text-align:right" class="table-h" colspan="3" id="totalExpense">合計金額：</th>
                            <td style="font-size:20px" class="table-h">${totalExpense}</td>
                        </tr>
                    </tbody>
                </table>            

        </div>
    </div>


</body>

</html>