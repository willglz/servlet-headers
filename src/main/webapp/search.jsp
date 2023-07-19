<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Search a product</title>
  <link rel="stylesheet" type="text/css" th:href="@{/webjars/bootstrap/css/bootstrap.min.css}"/>
</head>
<body>
<h1>Search a product</h1>
<form action="/cabecerashttp/search-product" method="post">
    <div>
        <label for="product">Name</label>
        <input type="text" id="product" name="product" />
    </div>
    <div>
        <input type="submit" value="Search" />
    </div>
</form>
</body>
</html>