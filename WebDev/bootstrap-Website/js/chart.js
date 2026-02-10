var chartData = {
    labels: ["Photo 1", "Photo 2", "Photo 3"],
    datasets: [{
      data: [589, 245, 383],
    }]
  };
  
  var chLine = document.getElementById("chLine");
  if (chLine) {
    new Chart(chLine, {
    type: 'bar',
    data: chartData,
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
    });
  }