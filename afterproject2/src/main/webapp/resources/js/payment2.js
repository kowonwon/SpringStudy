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
	});
};

button.addEventListener("click", onClickPay);

