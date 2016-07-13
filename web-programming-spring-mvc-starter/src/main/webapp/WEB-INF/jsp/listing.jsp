<%--
  Created by IntelliJ IDEA.
  User: Darko
  Date: 2/21/2016
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <form method="POST" action="./listing" enctype="multipart/form-data">
    <input name="title" placeholder="Here goes title of listing">
    <br>
    <textarea name="content" placeholder="Here write the content of the listing"></textarea>
    <br>
    <input multiple type = "file" name = "file" />
    <button type="submit">Create</button>
  </form>
</body>
</html>
