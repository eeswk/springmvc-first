<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>파일업로드</h2>
<form:form modelAttribute="fileUploadForm" enctype="multipart/form-data">
        파일: <form:input path="file" type="file" /><br/>
        <form:button>업로드</form:button>
</form:form>

</body>
</html>