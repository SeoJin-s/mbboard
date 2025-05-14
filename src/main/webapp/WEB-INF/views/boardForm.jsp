<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">게시글 작성</h2>

    <form action="/addBoard" method="post">
        <div class="mb-3">
            <label for="boardTitle" class="form-label">제목</label>
            <input type="text" class="form-control" id="boardTitle" name="boardTitle" required>
        </div>

        <div class="mb-3">
            <label for="boardContent" class="form-label">내용</label>
            <textarea class="form-control" id="boardContent" name="boardContent" rows="6" required></textarea>
        </div>

        <div class="mb-3">
            <label for="boardUser" class="form-label">작성자</label>
            <input type="text" class="form-control" id="boardUser" name="boardUser" required>
        </div>

        <div class="text-end">
            <button type="submit" class="btn btn-primary">등록</button>
            <a href="/boardList" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>
</body>
</html>
