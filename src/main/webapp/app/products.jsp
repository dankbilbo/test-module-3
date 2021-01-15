<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 1/15/2021
  Time: 2:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <a href="/app?action=add">Add new product</a>
</div>
<%--<div>--%>
<%--    <form action="post">--%>
<%--    Search :<input type="text" name="searchname">--%>
<%--    <input type="submit" value="search">--%>
<%--    </form>--%>
<%--</div>--%>
<form action="/app">
<table>
    <tr>
        <th>#</th>
        <th>Product Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Color</th>
        <th>Category</th>
        <th>Action</th>
    </tr>
<c:forEach items="${requestScope['listProduct']}" var="product">
    <tr>
        <td>${product.getId()}</td>
        <td>${product.getName()}</td>
        <td>${product.getPrice()}</td>
        <td>${product.getAmount()}</td>
        <td>${product.getColor()}</td>
        <td>${product.getCategoryId()}</td>
        <td><button ><a href="/app?action=edit&id=${product.getId()}">EDIT</a></button><br><button type="submit">delete</button></td>
    </tr>
</c:forEach>
</table>
</form>
</body>
</html>
