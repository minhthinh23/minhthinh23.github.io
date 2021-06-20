
new Glide('#topStories', {
    type: 'carousel',
    focusAt: 'center',
    perView: 6,
    gap: 15,
    breakpoints: {
        1024: {
            perView: 5,
            gap: 15
        },
        800: {
            perView: 4,
            gap: 15
        },
        600: {
            perView: 3,
            gap: 15
        },
        400: {
            perView: 1,
            gap: 15
        }
    }
}).mount();


new Glide('#news', {
    type: 'carousel',
    focusAt: 'center',
    perView: 5,
    gap: 15,
    breakpoints: {
        1024: {
            perView: 4,
            gap: 15
        },
        800: {
            perView: 3,
            gap: 15
        },
        600: {
            perView: 2,
            gap: 15
        },
        400: {
            perView: 1,
            gap: 15
        }
    }
}).mount();


var ctx = document.getElementById('overviewGraph');
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        tension: 0.7,
        plugins: {
            title: {
                display: true,
                text: 'Chart.js Line Chart - Cubic interpolation mode'
            },
        },
        interaction: {
            intersect: false,
        }
    },
});