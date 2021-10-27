<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Приложение Cars - Главная</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/script.js"></script>
</head>
<body>
<div class="container-fluid">
    <h1>Cars Application</h1>
    <p>Интернет приложение по продаже автомобилей.</p>
</div>
<div class="container-fluid mt-3 mb-3">
    <div class="row border bg-light">
        <ul class="nav">
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login">Войти</a>
                </li>
            </c:if>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/cabinet">Личный кабинет</a>
                </li>
                <li class="nav-item">
                    <i class="nav-link"><c:out value="${user.name}"/></i>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout">Выйти</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<div class="container-fluid" id="itemsList">
    <h2>Список объявлений</h2>
    <ul class="list-group" id="itemsListUl">
        <c:forEach items="${items}" var="item">
            <li class="list-group-item">
                <img src="<c:url value='/downloadPhoto?name=${item.user.id}${item.id}'/>" width="100px" height="100px"/><br>
                <i>Категория: </i><c:out value="${item.category.name}" /><br>
                <i>Описание: </i><c:out value="${item.description}" /><br>
                <i>Статус: </i>
                    <c:choose>
                        <c:when test="${item.sold}">Продано</c:when>
                        <c:when test="${!item.sold}">В продаже</c:when>
                    </c:choose><br>
                <i>Модель: </i><c:out value="${item.brand.name}" /><br>
                <i>Кузов: </i><c:out value="${item.body.name}" /><br>
                <i>Автор: </i><c:out value="${item.user.name}" /><br>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>