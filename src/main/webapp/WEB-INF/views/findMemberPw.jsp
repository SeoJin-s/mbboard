<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
	<h1>비밀번호 찾기</h1>
	<form method="post" action="findMemberPw">
		<div>
			ID : <input type="text" name="memberId" required><br><br>
			Email : <input type="email" name="email" required><br><br>
			<button type="submit">비밀번호 찾기</button>
		</div>
	</form>
</body>
</html>
