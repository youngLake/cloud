$.ajax(
    {
        url:"basicCount",
        async:true,
        type:"POST",
        data:{},
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function (data) {
            console.log(data);
            if(data.returnCode=="1"){
                $("#userNumber").text(data.data.userNumber);
                $("#orderAmount").text('￥ '+data.data.orderAmount);
                $("#orderAmount1").text('￥ '+data.data.orderAmount);
                $("#projectNumber").text(data.data.projectNumber);
                $("#orderNumber").text(data.data.orderNumber);
            }
        }
    }
);

$.get({
    url:"/orderAnalysis",
    dataType:"json",
    success:function (data) {
        if (data.returnCode=="1"){
            debugger;
            console.log(data.data.top4);
            console.log(data.data.customers);
            console.log(data.data.totalAmount);
            console.log(data.data.workOrders);
        }
    }
});