$(function() {
	
	// 우편번호 찾기 버튼이 클릭되면 - 다음 우편번호 찾기 실행
	#("#btnZipcode")
	
	// 아이디 중복 폼이 서브밋 될 때
	$("#idCheckForm").on("submit", function() {
		let id = $("#checkId").value();
		if(id.length == 0) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		if(id.length < 5) {
			alert("아이디는 5자 이상이어야 합니다.");
			return false;
		}
	});
	
	// id를 아이디로 사용하기 버튼이 클릭되면
	$("#btnIdCheckClose").on("click", function() {
		let id = $(this).attr("data-id-value");
		opener.document.joinForm.id.value=id;
		opener.document.joinForm.isIdCheck.value=true;
		window.close();
	});

	$("#btnOverlapId").on("click", function() {
		let id = $("#id").val();
		if(id.length == 0) {
			alert("아이디를 입력해 주세요.");
			return false;
		}
		if(id.length < 5) {
			alert("아이디는 5자 이상이어야 합니다.");
			return false;
		}
		
		let url = "overlapIdCheck?id=" + id;
		window.open(url, "_blank", "width=500, height=400");
	});
	
	$("#id").on("keyup", function() {
		let regExp = /[^A-Za-z0-9]/gi;
		if(regExp.test($(this).val())) { // 영문, 숫자가 아니면
			alert("아이디는 영문 대소문자와 숫자만 가능합니다.");
			$(this).val($(this).val().replace(regExp, ""));
		}
	});
	
	$("#pass1").on("keyup", inputCharReplace);
	$("#pass2").on("keyup", inputCharReplace);
	$("#emailId").on("keyup", inputCharReplace);
	$("#emailDomain").on("keyup", inputEmailDomainReplace);
	
	// 모달 로그인 폼
	$("#modalLoginForm").on("submit", function() {
		
		let id = $("#userId").val();
		let pass = $("#pass").val();
		
		if(id.length <= 0) {
			alert("아이디를 입력해주세요");
			$("#userId").focus();
			return false;
		}
		if(pass.length <= 0) {
			alert("비밀번호를 입력해주세요");
			$("#userPass").focus();
			return false;
		}
	});
	
	// 로그인 폼 유효성 검사
	$("#loginForm").on("submit", function() {
		
		let id = $("#userId").val();
		let pass = $("#userPass").val();
		
		if(id.length <= 0) {
			alert("아이디를 입력해주세요");
			$("#userId").focus();
			return false;
		}
		if(pass.length <= 0) {
			alert("비밀번호를 입력해주세요");
			$("#userPass").focus();
			return false;
		}
	});
});

function inputEmailDomainReplace() {
	let regExp = /[^a-z0-9\.]/g;
	if(regExp.test($(this).val())) { // 영문, 숫자가 아니면
		alert("이메일 도메인은 영문 소문자와 숫자, 점(.)만 가능합니다.");
		$(this).val($(this).val().replace(regExp, ""));
	}
}

function inputCharReplace() {
	let regExp = /[^A-Za-z0-9]/gi;
	if(regExp.test($(this).val())) { // 영문, 숫자가 아니면
		alert("영문 대소문자와 숫자만 가능합니다.");
		$(this).val($(this).val().replace(regExp, ""));
	}
}

function findZipCode() {
  new daum.Postcode({
    oncomplete: function(data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 각 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var addr = ''; // 주소 변수
      var extraAddr = ''; // 참고항목 변수

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
      if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
          addr = data.roadAddress;
      } else { // 사용자가 지번 주소를 선택했을 경우(J)
          addr = data.jibunAddress;
      }

      // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
      if(data.userSelectedType === 'R'){
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
            extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if(data.buildingName !== '' && data.apartment === 'Y'){
            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if(extraAddr !== ''){
            extraAddr = ' (' + extraAddr + ')';
        }
        // 조합된 참고항목을 해당 필드에 넣는다.
        document.getElementById("sample6_extraAddress").value = extraAddr;
      
      } else {
        document.getElementById("sample6_extraAddress").value = '';
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById('sample6_postcode').value = data.zonecode;
      document.getElementById("sample6_address").value = addr;
      // 커서를 상세주소 필드로 이동한다.
      document.getElementById("sample6_detailAddress").focus();
    }
  }).open();
}