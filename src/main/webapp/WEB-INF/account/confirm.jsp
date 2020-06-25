<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>계정등록완료</h2>
    <div>
        <span>회원구분: <c:out value="${accountCreateForm.type}" /></span>
        <span>카드번호: <c:out value="${accountCreateForm.cardNo}" /></span>
    </div>
    <div>
        <a href="<c:url value='/'/>">상위 페이지로 이동</a>
    </div>
</body>
</html>