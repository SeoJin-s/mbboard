<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 상세</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">${board.boardTitle}</h2>
    <p class="text-muted">
        작성자: ${board.boardUser} |
        작성일: ${board.createdate} |
        수정일: ${board.updatedate}
    </p>
    <hr>
    <p>${board.boardContent}</p>
    <hr>
    <div class="mt-4 text-end">
        <a href="/updateBoard?boardNo=${board.boardNo}" class="btn btn-primary btn-sm">수정</a>
        <form action="/removeBoard" method="post" class="d-inline">
		    <input type="hidden" name="boardNo" value="${board.boardNo}" />
		    <button type="submit" class="btn btn-danger btn-sm"
		        onclick="return confirm('정말 삭제하시겠습니까?');">삭제</button>
		</form>
        <a href="/boardList" class="btn btn-secondary btn-sm">목록</a>
    </div>
</div>
</body>
</html>
