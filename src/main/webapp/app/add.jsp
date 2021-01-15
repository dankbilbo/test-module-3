<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 1/15/2021
  Time: 3:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <c:if test="${requestScope['message'] != null}">
    <p>${requestScope['message']}</p>
    </c:if>
</div>
<form   method="post">
    <table>
    <tr>
        <td>Name</td>
        <td><input type="text" name="name"></td>
    </tr>
    <tr>
        <td>Price</td>
        <td><input type="number" name="price"></td>
    </tr>
    <tr>
        <td>Amount</td>
        <td><input type="number" name="amount"></td>
    </tr>
    <tr>
        <td>Color</td>
        <td><input type="text" name="color"></td>
    </tr>
    <tr>
        <td>Description</td>
        <td><input type="text" name="description"></td>
    </tr>
    <tr>
        <td>Category</td>
        <td>
            <select name="categories" id="">
                <c:forEach items="${requestScope['allCategories']}" var="category">
                    <option value="${category.getId()}">${category.getName()}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center"><button type="submit" value="add">ADD</button></td>
    </tr>
    </table>
</form>
</body>
</html>
