<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/asideBoard.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="read">

					<!-- 작성자 -->
					<div class="form-group">
						<span class="form-text">작성자</span>
						<span class="form-value">${boardVo.userName }</span>
					</div>
					
					<!-- 조회수 -->
					<div class="form-group">
						<span class="form-text">조회수</span>
						<span class="form-value">${boardVo.hit }</span>
					</div>
					
					<!-- 작성일 -->
					<div class="form-group">
						<span class="form-text">작성일</span>
						<span class="form-value">${boardVo.regDate }</span>
					</div>
					
					<!-- 제목 -->
					<div class="form-group">
						<span class="form-text">제 목</span>
						<span class="form-value">${boardVo.title }</span>
					</div>
				
					<!-- 내용 -->
					<div id="txt-content">
						<span class="form-value" >
						${boardVo.content }
						</span>
					</div>
					
					<c:if test="${boardVo.userNo == authUser.no }">
						<a id="btn_modify" href="${pageContext.request.contextPath }/board/modifyForm?no=${boardVo.no }">수정</a>
					</c:if>
					<a id="btn_list" href="${pageContext.request.contextPath }/board/list">목록</a>
					
				</div>
				<!-- //read -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>