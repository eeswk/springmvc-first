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
    </div>
    <div>
        <form:button>전송</form:button>
    </div>
</form:form>

</body>
</html>