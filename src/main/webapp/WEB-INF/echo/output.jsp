<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>출력화면</h2>
    <div>
        입력한 테스트는 '<span><c:out value="${echoForm.text}" /></span>'입니다.<br/>
        <span>영문숫자조합: <c:out value="${echoForm.alphaNum}" /></span>
        <form:form modelAttribute="echoForm">
            <form:checkbox path="roles" value="1" disabled="true"/>이용자
            <form:checkbox path="roles" value="2"/>승인자
            <form:checkbox path="roles" value="3"/>관리자
        </form:form>
        <span><c:out value="${echoForm.roles}" /></span>

        <span>카드번호: <c:out value="${echoForm.card.no}" /></span><br/>
        유효일:<fmt:formatDate value="${echoForm.card.validMonth}" pattern="yyy/MM/dd"/><br/>
        유효일:<span><c:out value="${echoForm.card.validMonth}" /></span><br/>
        <div>카드정보1</div>
        <div>
            <span>카드번호: <c:out value="${echoForm.cards[0].no}" /></span><br/>
            유효일:<fmt:formatDate value="${echoForm.cards[0].validMonth}" pattern="yyy/MM/dd"/><br/>
        </div>
        <div>카드정보2</div>
        <div>
            <span>카드번호: <c:out value="${echoForm.cards[1].no}" /></span><br/>
            유효일:<fmt:formatDate value="${echoForm.cards[1].validMonth}" pattern="yyy/MM/dd"/><br/>
        </div>
    </div>
    <div>
        <a href="<c:url value='/'/>">상위 페이지로 이동</a>
    </div>
</body>
</html>