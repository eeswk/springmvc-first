<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>출력화면</h2>
    <div>
        입력한 테스트는 '<span><c:out value="${echoForm.text}" /></span>'입니다.
    </div>
    <div>
        <a href="<c:url value='/'/>">상위 페이지로 이동</a>
    </div>
</body>
</html>