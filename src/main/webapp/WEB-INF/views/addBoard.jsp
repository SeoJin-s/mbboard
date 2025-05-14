<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>등록 완료</title>
    <script>
        let countdown = 3; // 시작 시간
        function startCountdown() {
            const countEl = document.getElementById("countdown");
            const interval = setInterval(() => {
                countdown--;
                countEl.textContent = countdown;
                if (countdown <= 0) {
                    clearInterval(interval);
                    location.href = '/boardList'; // 자동 이동
                }
            }, 1000);
        }

        window.onload = startCountdown;
    </script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-lg text-center p-5" style="max-width: 500px;">
        <h3 class="mb-3 text-success">등록 되었습니다.</h3>
        <p class="fs-5">게시글이 성공적으로 등록되었습니다.</p>
        <p class="text-muted">목록 페이지로 <span id="countdown">3</span>초 후 이동합니다...</p>
        <a href="/boardList" class="btn btn-outline-primary mt-3">지금 이동하기</a>
    </div>
</div>
</body>
</html>
