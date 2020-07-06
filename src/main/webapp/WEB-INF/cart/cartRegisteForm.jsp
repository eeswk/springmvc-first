<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>상품구매</h2>
<form:form modelAttribute="item" action="/cart/create">
    <div>유형</div>
    <div>
        아이템번호 : <form:input path="id" />
        상품명: <form:input path="name" />
    </div>
        <form:button name="confirm">저장</form:button>
    </div>
</form:form>

</body>
</html>