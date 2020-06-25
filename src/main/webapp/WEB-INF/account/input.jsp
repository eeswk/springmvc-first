<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
    </div>
        <form:button name="confirm">전송</form:button>
    </div>
</form:form>

</body>
</html>