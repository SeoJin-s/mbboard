<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 변경</title>
</head>
<body>
    <h1>비밀번호 변경</h1>
    <form action="/rechangeMemberPw" method="post">
        <div>
            아이디 <input type="text" name="memberId" required>
        </div>
        <div>
            메일로 받은 비밀번호 <input type="password" name="memberPw" required>
        </div>
        <div>
            새 비밀번호 <input type="password" name="newMemberPw" required>
        </div>
        <button type="submit">패스워드 변경</button>
    </form>
</body>
</html>
