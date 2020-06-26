<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>스프링 폼 샘플</h2>
    <ul>
        <spring:eval expression="@hobbyCodeList" var="hobbyCodeList"/>
<%--        <form:radiobuttons path="hobbys" items="${hobbyCodeList}" />--%>
    </ul>

</body>
</html>