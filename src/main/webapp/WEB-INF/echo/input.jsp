<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>입력화면</h2>
<form:form modelAttribute="echoForm">
    <div>텍스트를 입력해주세요</div>
    <div>
        <form:input path="text" />
        <form:errors path="text" />
        <form:input path="password" />
        <form:errors path="password" />
        <form:input path="reEnteredPassword" />
        <form:errors path="reEnteredPassword" />
        <form:checkbox path="roles" value="1"/>이용자
        <form:checkbox path="roles" value="2"/>승인자
        <form:checkbox path="roles" value="3"/>관리자
        <form:input path="card.no" />
        <form:errors path="card.no" />
        <form:input path="card.validMonth" />
        <form:errors path="card.validMonth" />
    </div>
    <div>카드정보1</div>
    <div>
        <form:input path="cards[0].no" />
        <form:input path="cards[0].validMonth" />
    </div>
    <div>카드정보1</div>
    <div>
        <form:input path="cards[1].no" />
        <form:input path="cards[1].validMonth" />
    </div>
    </div>
    <div>
        <form:button>전송</form:button>
    </div>
</form:form>

</body>
</html>