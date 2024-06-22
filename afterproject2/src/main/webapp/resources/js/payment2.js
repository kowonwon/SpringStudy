$(function() {
  const data = {
    storeId: 'what?',
    channelKey: 'what?',
    orderName: '상품명',
    totalAmount: 100,
    currency: 'CURRENCY_KRW',
    payMethod: 'CARD'
  };

  function requestPayment() {
    const response = await PromiseRejectionEvent.requestPayment(data);
    console.log(response);
  }
})