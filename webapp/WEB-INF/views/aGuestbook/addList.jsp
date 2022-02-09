<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<!-- header.jsp -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>


		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					
					<%-- <form action="${pageContext.request.contextPath }/guestbook/write" method="get"> --%>
					
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center">
										<button id="btnSubmit2" type="submit">등록</button>
									</td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->


					<!-- </form> -->

					<div id="listArea">
						<!-- 리스트 출력할곳 -->
					</div>

				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>


	</div>
	<!-- //wrap -->


<!-- --------------------------------------------------------------------------------------------------- -->
<!-- 삭제 모달창 -->

<div id="delModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">비밀번호 입력 모달창</h4>
      </div>
      <div class="modal-body">
       
        비밀번호:
      	<input id="modalPassword" type="password" name="password" value=""><br>
        <input id="modalNo" type="text" name="no" value="">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        <button id="modalBtnDel" type="button" class="btn btn-danger">삭제</button>
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- //삭제 모달창 -->
</body>

<script type="text/javascript">
	/* 로딩되기전에  돔이 완성되었을때*/
	$(document).ready(function() {

		/* 리스트 그리기 */
		fetchList();

	});

	//저장버튼이 클릭될때 - 파라미터 방식 요청
	$("#btnSubmit").on("click", function(){
		console.log("클릭");
		
		//폼에 데이터를 모아야한다
		var name = $("#input-uname").val();  //이름
		var password = $("#input-pass").val(); //패스워드
		var content = $("[name='content']").val() //컨텐츠
		
		//객체 만들기
		var guestbookVo = {
			name: name,
			password: password,
			content: content
		};
		console.log(guestbookVo); //확인
		
		//요청 파라미디터 방식
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/write",		
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,   //{name: name, password: password, content: content}
  
			dataType : "json",
			success : function(guestbookVo){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, "up");
				
				//입력화면 초기화
				$("#input-uname").val("");  
				$("#input-pass").val("");
				$("[name='content']").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	
	
	//저장버튼이 클릭될때 - json 방식 요청	
	$("#btnSubmit2").on("click", function(){
		console.log("json 전송클릭");
		
		//폼에 데이터를 모아야한다
		var name = $("#input-uname").val();  //이름
		var password = $("#input-pass").val(); //패스워드
		var content = $("[name='content']").val() //컨텐츠
		
		//객체 만들기
		var guestbookVo = {
			name: name,
			password: password,
			content: content
		};
		console.log(guestbookVo); //확인
		
		//요청 파라미디터 방식
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/write2",		
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(guestbookVo), //자바스크립트 객체를 json형식으로 변경
  
			dataType : "json",
			success : function(guestbookVo){
				/*성공시 처리해야될 코드 작성*/
				
				console.log(guestbookVo);
				render(guestbookVo, "up");
				
				//입력화면 초기화
				$("#input-uname").val("");  
				$("#input-pass").val("");
				$("[name='content']").val("");
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	
	
	
	//삭제팝업 버튼을 눌렀을때
	$("#listArea").on("click", ".btnDelPop", function(){
		//데이타수집
		var $this = $(this);
		//$this -> javascript varaible, $() -> json 
		var no = $this.data("no");
		console.log(no);
		
		//초기화
		$("#modalPassword").val("");
		$("#modalNo").val(no);
		
		
		$("#delModal").modal('show');
		
	});
	
	//모달창의 삭제버튼을 클릭했을때
	$("#modalBtnDel").on("click", function(){
		console.log("모달창 삭제버튼 클릭");
		
		//데이타 수집
		var no = $("#modalNo").val();
		var pw = $("#modalPassword").val();
		
		var delInfoVo = {
			no: no,
			password: pw
		}
		//객체 -> 키:값 
		
		console.log(delInfoVo);
		
		
		//ajax 요청  no password
		$.ajax({
		
			url : "${pageContext.request.contextPath }/api/guestbook/remove",		
			type : "post",
			//contentType : "application/json",
			data : delInfoVo,

			dataType : "json",
			success : function(state){
				console.log(state);
				
				if(state==='success'){
					//   해당 테이블html 삭제	
					$("#t"+no).remove();
					//   모달창 닫기
					$("#delModal").modal('hide');
				}else {
					$("#delModal").modal('hide');
					alert("비밀번호를 확인하세요");
				}
				
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		

	});
	
	
	
	//리스트 출력 
	function fetchList() {
		$.ajax({

			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: "홍길동"},

			dataType : "json",
			success : function(guestbookList) { //json --> js
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookList);

				for (var i = 0; i < guestbookList.length; i++) {
					render(guestbookList[i], "down"); //방명록리스트 그리기
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	}

	
	//리스트 그리기
	function render(guestbookVo, updown) {

		var str = '';
		str += '<table id="t' + guestbookVo.no + '" class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + guestbookVo.no + '</td>';
		str += '		<td>' + guestbookVo.name + '</td>';
		str += '		<td>' + guestbookVo.regDate + '</td>';
		str += '		<td><button class="btnDelPop" type="button" data-no="'+guestbookVo.no+'">삭제</button></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan="4" class="text-left">' + guestbookVo.content
				+ '</td>';
		str += '	</tr>';
		str += '</table>';

		if(updown == 'down'){
			$("#listArea").append(str);
		}else if(updown == 'up'){
			$("#listArea").prepend(str);
		}else {
			console.log("방향오류");
		}
		
		
	};
</script>








</html>