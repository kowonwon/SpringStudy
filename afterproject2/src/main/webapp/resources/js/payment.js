function kg_request_pay() {
  // 전달할 데이터(view에서 가져올 것)
  var lectureName = document.querySelector("#lectureName").value;
  var lecturePrice = document.querySelector("#lecturePrice").value;
  var lectureId = document.querySelector("#lectureId").value;
  
  // 유효성검사
  // 유효성검사 끝

  // 결제고유번호 생성
  var today = new Date();
  var hours = today.getHours(); // 시
  var minutes = today.getMinutes();  // 분
  var seconds = today.getSeconds();  // 초
  var milliseconds = today.getMilliseconds();
  var makeMerchantUid = `${hours}` + `${minutes}` + `${seconds}` + `${milliseconds}`;

  // KakaoPay, PortOne에서 제공하는 코드
  var IMP = window.IMP;
  IMP.init('imp62227326'); // 가맹점 식별코드

  IMP.request_pay({ // 결제창 호출
    pg: 'kakaopay.TC0ONETIME', // 카카오페이에서 제공
    pay_method: 'card', // 결제 방식(불필요?)
    merchant_uid: "IMP" + makeMerchantUid, // 결제고유번호
    name: '상품명(db)', // 상품명
    amount: 100, // 가격
    buyer_email: `email(db)`, // 구매자 정보
    buyer_name: `이름(db)`, // 구매자 정보
  }, function(rsp) {
    if(rsp.sucess) { // 결제 성공시
      var msg = '결제 완료';
      var result = {
        "merchant_uid" : rsp.merchant_uid,

      }
    }
  })

}