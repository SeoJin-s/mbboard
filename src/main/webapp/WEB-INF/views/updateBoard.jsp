<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 수정</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">게시글 수정</h2>
    <form action="/updateBoard" method="post">
        <input type="hidden" name="boardNo" value="${board.boardNo}" />

        <div class="mb-3">
            <label class="form-label">제목</label>
            <input type="text" name="boardTitle" value="${board.boardTitle}" class="form-control" required />
        </div>

        <div class="mb-3">
            <label class="form-label">내용</label>
            <textarea name="boardContent" class="form-control" rows="6" required>${board.boardContent}</textarea>
        </div>

        <div class="text-end">
            <button type="submit" class="btn btn-primary">수정 완료</button>
            <a href="/boardOne?boardNo=${board.boardNo}" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>
</body>
</html>
