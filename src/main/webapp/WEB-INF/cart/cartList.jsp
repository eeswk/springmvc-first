<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2>장바구니 정보</h2>
<div>
    <spring:eval expression="@cart" var="cart"/>
    <c:forEach var="cartItem" items="${cart.cartItems}">
        <c:out value="${cartItem.id}" />
        <c:out value="${cartItem.name}" />
    </c:forEach>
</div>

</div>
<div>
    <a href="<c:url value='/'/>">상위 페이지로 이동</a>
</div>
</body>
</html>