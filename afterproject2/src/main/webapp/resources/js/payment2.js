IMP.init("imp62227326");

const price = document.querySelector("#price").value;
const lectureId = document.querySelector("#lectureId").value;

const button = document.querySelector("button");
const onClickPay = () => {
	IMP.request_pay({
		pg: "kakaopay",
		pay_method: "card",
		amount: price,
		name: "라면",
		lectureId: lectureId,
		buyer_email: "gildong@gmail.com",
    buyer_name: "홍길동"
	},
	async (response) => {
		if(response.error_code != null) {
			return alert('결제에 실패했습니다. 에러 내용:')
		}
		
		var result = {
			imp_uid: response.imp_uid,
			merchant_uid: response.merchant_uid,
			lectureId: response.lectureId,
			price: response.price
		}

		$.ajax({
			url: 'insertPayment',
			type: 'POST',
			conttentType: 'application/json',
			data: JSON.stringify(result),
			success: function (resoponse) {
				location.href=response;
			},
			error: function(err) {
				console.log(err);
			}
		});
	});
};

button.addEventListener("click", onClickPay);