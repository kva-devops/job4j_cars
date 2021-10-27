<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Приложение Cars - Новое объявление</title>
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
                    <a class="nav-link" href="<%=request.getContextPath()%>/all">На главную</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/cabinet">Назад</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/add">Добавить объявление</a>
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
<div class="container-fluid">
    <div class="card" style="width: 100%">
        <div class="card-header">
            <h2>Новое объявление</h2>
        </div>
        <div class="card-body">
            <form action="<%=request.getContextPath()%>/add" method="post">
                <div class="form-group col-3 p-1">
                    <label>Описание</label>
                    <textarea class="form-control" name="descriptionItem" rows="5" placeholder="Введите описание"></textarea>
                </div>
                <div class="form-group col-3 p-1">
                    <label>Категория</label>
                    <select class="form-select" name="categories" size="5" id="categoryId">
                        <c:forEach items="${categories}" var="categoryCar" >
                            <option value='<c:out value="${categoryCar.id}"/>'>
                                <c:out value="${categoryCar.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-3 p-1">
                    <label>Марка</label>
                    <select class="form-select" name="brands" size="5" id="brandId">
                        <c:forEach items="${brands}" var="brand" >
                            <option value='<c:out value="${brand.id}"/>'>
                                <c:out value="${brand.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-3 p-1">
                    <label>Кузов</label>
                    <select class="form-select" name="bodies" size="5" id="bodyId">
                        <c:forEach items="${bodies}" var="body" >
                            <option value='<c:out value="${body.id}"/>'>
                                <c:out value="${body.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-3 p-1 mt-2">
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
