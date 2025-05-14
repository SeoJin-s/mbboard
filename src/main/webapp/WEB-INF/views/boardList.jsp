<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .banner {
            background: url('/img/banner.png') no-repeat center center;
            background-size: cover;
            height: 150px;
        }
        .pagination-custom ul {
            gap: 8px;
        }
        .page-num a {
            display: inline-block;
            padding: 6px 12px;
            border-radius: 6px;
            text-decoration: none;
            color: #111;
            font-weight: 500;
            transition: background-color 0.2s;
        }
        .page-num a:hover {
            background-color: #f0f0f0;
        }
        .page-num.active a {
            background-color: #e0e0e0;
            font-weight: bold;
            color: black;
        }
        .page-num .arrow {
            font-weight: bold;
        }
		         /* 제목 셀 padding 보정 */
		table td:nth-child(2) {
		    padding-left: 16px !important;
		}
		
		/* 행 hover 효과 */
		table tbody tr:hover td {
		    background-color: #f9f9f9;
		}
		
		/* 아이콘 오른쪽 간격 */
		.bi-person-circle {
		    margin-right: 4px;
		}
		
		/* 전체 행 높이 여유롭게 (전역적용도 가능) */
		table tbody tr {
		    height: 52px;
		}
		table tbody tr.notice-row {
  			background-color: #e9e9e9 !important;
		}
		
    </style>
</head>

<body style="background-color: #ffffff;">

<!-- 상단 네비게이션 -->
<div class="nav-topbar bg-white border-bottom px-3 py-2 d-flex justify-content-between align-items-center">
    <div><img src="/img/naver.png" alt="NAVER" style="height: 36px;"></div>
       <!-- 네이버 카페 상단 풀 메뉴바 -->
	  <div class="d-flex align-items-center gap-3 text-secondary">
	    <a href="#" class="text-decoration-none text-dark">카페홈</a>
	    <a href="#" class="text-decoration-none text-dark">이웃</a>
	    <a href="#" class="text-decoration-none text-dark">가입카페 ▾</a>
	    <span class="text-muted">|</span>
	    <a href="#" class="text-decoration-none text-dark">새글</a>
	    <a href="#" class="text-decoration-none text-danger position-relative fw-semibold">
	      내소식
	      <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger" style="font-size: 0.6rem;">
	        5
	      </span>
	    </a>
	    <a href="#" class="text-decoration-none text-dark">채팅</a>
	    <a href="#" class="text-decoration-none text-dark">구디아카데미 ▾</a>
	    <i class="bi bi-grid fs-5 text-muted"></i>
	    <i class="bi bi-three-dots-vertical fs-6 text-muted"></i>
	  </div>
	</div>

  <!-- 배너 영역 -->
  <div class="row">
    <div class="col-12 p-0">
      <div class="banner"></div>
    </div>
  </div>

  <!-- 사이드바 + 본문 -->
  <div class="row gx-0">
    <div class="col-auto bg-white p-3" style="width: 300px; min-height: 100vh;">
  <img src="/img/pk.png" style="width: 90%; height: auto; margin-left: 40px;" alt="사이드 이미지">
  </div>
    <div class="col p-4">

        <!-- 오른쪽 본문 -->
        <div class="col p-4">

            <!-- 카테고리 상단바 -->
<div class="d-flex justify-content-between align-items-center mb-3 border-bottom pb-2">
    <div class="fs-5 fw-bold">┗ 과제게시판 <span class="text-muted">(2012~)</span></div>
    
    <!-- 오른쪽 옵션들 -->
    <div class="d-flex align-items-center gap-3 small text-secondary">

        <!-- 새글 구독 토글 -->
        <div class="form-check form-switch mb-0">
            <input class="form-check-input" type="checkbox" id="subscribeSwitch" style="cursor: pointer;">
            <label class="form-check-label ms-1" for="subscribeSwitch" style="cursor: pointer;">새글 구독</label>
        </div>

        <!-- 공지 숨기기 체크박스 -->
        <div class="form-check mb-0">
            <input class="form-check-input" type="checkbox" id="hideNotice">
            <label class="form-check-label ms-1" for="hideNotice">공지 숨기기</label>
        </div>

        <!-- 보기 형태 아이콘 -->
        <div class="d-flex align-items-center gap-1">
            <i class="bi bi-list fs-5" style="cursor: pointer;"></i>
            <i class="bi bi-grid-3x3-gap fs-5" style="cursor: pointer;"></i>
            <i class="bi bi-list-task fs-5 text-success" style="cursor: pointer;"></i>
        </div>

        <!-- 개수 드롭다운 -->
        <select class="form-select form-select-sm w-auto">
            <option>15개씩</option>
            <option>30개씩</option>
            <option>50개씩</option>
        </select>
    </div>
</div>


        <!-- 게시글 테이블 -->
<div class="bg-white rounded p-3">
    <table class="table table-hover" style="border-collapse: collapse;">
        <thead class="text-center border-bottom text-secondary">
            <tr class="fw-bold" style="font-size: 0.95rem;">
                <th style="width: 6%;">번호</th>
                <th style="width: 40%;">제목</th>
                <th style="width: 15%;">작성자</th>
                <th style="width: 20%;">작성일</th>
                <th style="width: 9%;">조회수</th>
                <th style="width: 9%;">좋아요</th>
            </tr>
        </thead>
        <tbody>
            <tr class="notice-row">
			    <td class="text-center"><span class="badge bg-danger">공지</span></td>
			    <td style="padding-left: 16px;">
			        <a href="#" class="text-danger fw-bold text-decoration-none">
			            새싹멤버 → 일반멤버 등업 방법 [2333]
			        </a>
			    </td>
			    <td class="text-center">
			        <i class="bi bi-person-circle me-1"></i> zeroDay <span class="text-danger">🧧</span>
			    </td>
			    <td class="text-center">2013.11.26.</td>
			    <td class="text-end">3,321</td>
			    <td class="text-end">0</td>
			</tr>

            <c:forEach var="board" items="${boardList}">
                <tr style="height: 52px;"> <!-- 여유롭게 보기 좋게 -->
                    <td class="text-center">${board.boardNo}</td>
                    <td style="padding-left: 16px;">
                        <a href="/boardOne?boardNo=${board.boardNo}" class="text-decoration-none text-dark">
                            ${board.boardTitle}
                        </a>
                    </td>
                    <td class="text-center">
                        <i class="bi bi-person-circle me-1"></i> ${board.boardUser}
                    </td>
                    <td class="text-center">${board.createdate}</td>
                    <td class="text-end">${board.viewCount}</td>
                    <td class="text-end">${board.likeCount}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<!-- 검색창 form 바로 아래 추가 -->
<div class="d-flex justify-content-end mt-3">
    <a href="/boardForm" class="btn btn-outline-success btn-sm px-3 py-1" style="background-color: #e8f7ef;">
        <i class="bi bi-pencil-fill me-1"></i> 글쓰기
    </a>
</div>

            <!-- 페이징 -->
            <div class="pagination-custom d-flex justify-content-center my-4">
                <ul class="d-flex list-unstyled mb-0">
                    <li class="page-num">
                        <a href="?currentPage=${startPage - 1}" class="arrow">&lt;</a>
                    </li>
                    <c:forEach var="i" begin="${startPage}" end="${endPage}">
                        <li class="page-num <c:if test='${i == currentPage}'>active</c:if>'">
                            <a href="?currentPage=${i}&searchWord=${searchWord}">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-num">
                        <a href="?currentPage=${endPage + 1}" class="arrow">&gt;</a>
                    </li>
                </ul>
            </div>
            
<!-- 검색 페이징 -->
<form method="get" class="row justify-content-center g-2 mt-4">
    <div class="col-auto">
        <select name="period" class="form-select form-select-sm">
            <option value="">전체기간</option>
            <option value="7d">최근 7일</option>
            <option value="30d">최근 30일</option>
        </select>
    </div>

    <div class="col-auto">
        <select name="type" class="form-select form-select-sm">
            <option value="all">글 + 댓글</option>
            <option value="post">글만</option>
            <option value="comment">댓글만</option>
        </select>
    </div>

    <div class="col-md-4 col-sm-6">
        <input type="text" name="searchWord" class="form-control form-control-sm"
               placeholder="검색어를 입력해주세요"
               value="${searchWord != null ? searchWord : ''}">
    </div>

    <div class="col-auto">
        <button class="btn btn-success btn-sm" type="submit">
            <i class="bi bi-search"></i>
        </button>
    </div>
</form>


        </div><!-- 오른쪽 본문 col 끝 -->
    </div><!-- row 끝 -->
</div><!-- container-fluid 끝 -->

</body>
</html>
