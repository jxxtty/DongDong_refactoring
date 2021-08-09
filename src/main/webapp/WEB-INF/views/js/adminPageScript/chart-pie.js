// Set new default font family and font color to mimic Bootstrap's default styling
// Pie Chart Example
var ctx = document.getElementById("txPieChart");
var txPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: ["글 등록","거래","가입","신고"],
    datasets: [{
      data: [],
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc','#757575'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf','#616161'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});

function txPieChartUpdate(data) {
	removeTXPieChartData();
	txPieChart.data.datasets.forEach((dataset)=>{
        dataset.data = data;
    });
	txPieChart.update();
}

function removeTXPieChartData() {
	txPieChart.data.datasets.forEach((dataset) => {
        dataset.data.pop();
    });
	txPieChart.update();
}
