<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link herf="<c:url value='/static/css/app.css'/>"	 type="text/css" rel="stylesheet" />
</head>
<body>
    <h2>계정생성화면</h2>
<form:form modelAttribute="accountCreateForm">
    <div>유형</div>
    <div>
        <form:radiobutton path="type" value="1"/>무료회원
        <form:radiobutton path="type" value="2"/>유료회원
        카드번호 : <form:input path="cardNo" />
        <form:errors path="cardNo" />
        <spring:message code="title.home" />
    </div>
        <form:button name="confirm">전송</form:button>
    </div>
</form:form>

</body>
</html>