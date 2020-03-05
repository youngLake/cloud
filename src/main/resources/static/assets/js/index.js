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
            $("#orderAmount1").text(''+data.data.totalAmount);
            for (var index=0;index<data.data.top4.length;index++){
                $("#projectRatio").append('<div class="progress-card">\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t<div class="d-flex justify-content-between mb-1">\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t<span class="text-muted">'+data.data.top4[index].projectName+'</span>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t<span class="text-muted fw-bold"> '+data.data.top4[index].projectCount+'</span>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t<div class="progress mb-2" style="height: 7px;">\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t\t<div class="progress-bar bg-success" role="progressbar" style="width: '+data.data.top4[index].projectCount+'%" aria-valuenow="'+data.data.top4[index].projectCount+'" aria-valuemin="0" aria-valuemax="100000000000" data-toggle="tooltip" data-placement="top" title="78%"></div>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                    '\t\t\t\t\t\t\t\t\t\t</div>');
            }
            $("#customers").text(data.data.customers);
            $("#workOrders").text(data.data.workOrders);
            var ratios=[];
            var genders=[];
            for (var index=0;index<data.data.userStatics.length;index++){
                var gender=data.data.userStatics[index].split('-')[0];
                var ratio=data.data.userStatics[index].split('-')[1];
                ratios[index]=ratio;
                genders[index]=gender+' : '+ratio+'%';
            }
            Chartist.Pie('#monthlyChart', {
                labels: genders,
                series: ratios
            }, {
                plugins: [
                    Chartist.plugins.tooltip()
                ]
            });
            var months=[];
            var nums=[];
            var nums1=[];
            for (var index=0;index<data.data.months.length;index++){
                var month=data.data.months[index].split("===")[0];
                var num=data.data.months[index].split("===")[1];
                months[index]=month;
                nums[index]=num;
                nums1[index]=0;
            }

            dataSales = {
                labels: months,
                series: [
                    nums,
                    nums1
                ]
            }
            Chartist.Bar('#salesChart', dataSales, optionChartSales, responsiveChartSales);
        }
    }
});