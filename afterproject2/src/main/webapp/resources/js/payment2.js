IMP.init("imp62227326");

const button = document.querySelector("button");
const onClickPay = () => {
	IMP.request_pay({
		pg: "kakaopay",
		pay_method: "card",
		amount: "5000",
		name: "라면",
		buyer_email: "gildong@gmail.com",
    buyer_name: "홍길동"
	},
	async (response) => {
		if(response.error_code != null) {
			return alert('결제에 실패했습니다. 에러 내용:')
		}
	});
};

button.addEventListener("click", onClickPay);

