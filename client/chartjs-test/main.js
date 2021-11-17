//https://www.chartjs.org/docs/latest/



const myChart = new Chart(document.getElementById('myChart'), {
  type: 'line',
  data: {
    datasets: [{
      data: [
        { x: '2016-12-25', y: 20 }, 
        { x: '2016-12-26', y: 10 }, 
        { x: '2016-12-27', y: 30 },
        { x: '2016-12-28', y: 30 },
        { x: '2016-12-30', y: 30 }
      ]
    }],
  },
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
});