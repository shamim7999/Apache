<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="merge-pdfs-into-one" method="post">
    Source Pdf Files Directory Path: <input type="text" name="sourcePath" /><br/>
    Merged Pdf File Directory Path: <input type="text" name="destinationPath" /><br/>
    <input type="submit" value="Submit" />
</form>
</body>
</html>