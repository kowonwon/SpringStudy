$(function() {
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
	
	$("#id").on("keyup", function() {
		var regExp = /[^A-Za-z0-9]/gi;
		if(regExp.text($(this).val())) {
			alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
			$(this).val($(this).val().replace(regExp, ""));
		}
	});
	
	$("#pass1").on("keyup", inputCharReplace);
	$("#pass2").on("keyup", inputCharReplace);
	$("#emailid").on("keyup", inputCharReplace);
	$("#emailDomain").on("keyup", inputEmailDomainReplace);
	
	$("#btnOverlapId").on("click", function() {
		var id = $("#id").val();
		url="overlapIdCheck?id=" + id;
		
		if(id.length == 0) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if(id.length < 5) {
			alert("아이디는 5자 이상 입력해주세요.");
			return false;
		}
		
		window.open(url, "idCheck", "toolbar=no, location=no, status=no, menubar=no, width=400, height=200");
	});
	
	$("#idCheckForm").on("submit", function() {
		var id = $("#checkId").val();
		if(id.length == 0) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if(id.length < 5) {
			alert("아이디는 5자 이상 입력해주세요.");
			return false;
		}
	})
	
	$("#btnIdCheckClose").on("click", function() {
		var id = $(this).attr("data-id-value");
		opener.document.joinForm.id.value = id;
		opener.document.joinForm.isIdCheck.value = true;
		window.close();
	})
	
	$("#btnZipcode").click(findZipcode);
	
	$("#selectDomain").on("change", function() {
		var str = $(this).val();
		
		if(str == "직접입력") {
			$("#emailDomain").val("");
			$("#emailDomain").prop("readonly", false);
		} else if(str == "네이버") {
			$("#emailDomain").val("naver.com");
			$("#emailDomain").prop("readonly", true);
		} else if(str == "다음") {
			$("#emailDomain").val("daum.net");
			$("#emailDomain").prop("readonly", true);
		} else if(str == "한메일") {
			$("#emailDomain").val("hanmail.net");
			$("#emailDomain").prop("readonly", true);
		} else if(str == "구글") {
			$("#emailDomain").val("gmail.com");
			$("#emailDomain").prop("readonly", true);
		}
	});
	
	$("#joinForm").on("submit", function() {
		return joinFormCheck();
	});
	
	function inputCharReplace() {
		var regExp = /[%A-Za-z0-9]/gi;
		if(regExp.test($(this).val())) {
			alert("영문 대소문자, 숫자만 입력할 수 있습니다.");
			$(this).val($(this).val().replace(regExp, ""));
		}
	}
	
	function inputEmailDomainReplace() {
		var regExp = /[^a-z0-9\.]/gi;
		if(regExp.test($(this).val())) {
			alert("이메일 도메인은 영문 소문자, 숫자, 점(.)만 입력할 수 있습니다.");
			$(this).val($(this).val().replace(regExp, ""));
		}
	}
	
	function joinFormCheck() {
		var name = $("#name").val();
		var id= $("#id").val();
		var pass1= $("#pass1").val();
		var pass2 = $("#pass2").val();
		var zipcode = $("#zipcode").val();
		var address1 = $("#address1").val();
		var emailId = $("#emailId").val();
		var emailDomain = $("#emailDomain").val();
		var mobile2 = $("#mobile2").val();
		var mobile3 = $("#mobile3").val();
		var isIdCheck = $("#isIdCheck").val();
		
		if(name.length == 0) {
			alert("이름을 입력해주세요.");
			return false;
		}
		if(id.length < 5) {
			alert("아이디는 5자 이상입니다.");
			return false;
		}
		if(pass1.length == 0) {
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		if(pass2.length == 0) {
			alert("비밀번호 확인을 입력해주세요.");
			return false;
		}
		if(pass1 != pass2) {
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return false;
		}
		if(zipcode.length == 0) {
			alert("우편번호를 입력해주세요.");
			return false;
		}
		if(address1.length == 0) {
			alert("주소를 입력해주세요.");
			return false;
		}
		if(emailId.length == 0) {
			alert("이메일 아이디를 입력해주세요.");
			return false;
		}
		if(emailDomain.length == 0) {
			alert("이메일 도메인을 입력해주세요.");
			return false;
		}
		if(mobile2.length == 0 || mobile3.length == 0) {
			alert("휴대폰 번호를 입력해주세요.");
			return false;
		}
	}
	
	function findZipcode() {
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
                addr = data.roadAddress;
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
                // 조합된 참고항목을 상세주소에 추가한다..
                addr += extraAddr;
            }

            // 우편번호와 주소 정보를 해당 입력상자에 출력한다.
            $("#zipcode").val(data.zonecode);
            $("#address1").val(addr);
            
            // 커서를 상세주소 필드로 이동한다.
            $("#address2").focus();
        }
    }).open();
}
	
	
	
	
});