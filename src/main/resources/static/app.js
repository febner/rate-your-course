var stompClient = null;

var graph;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        // console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/ratings', function (event) {
            let rating = JSON.parse(event.body);
            let items = {
                time: Date.now(),
                y: rating.current
            };
            graph.push([items]);
        });
    });
}

function sendRating() {
    stompClient.send("/app/student/cheer", {}, JSON.stringify({'type': 'up'}));
}

function showRating(message) {
    $("#ratings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    $(document).keyup(function () {
        sendRating();
    });

    connect();

    graph = $('#lineChart').epoch({
        type: 'time.line',
        data: [{
            label: "Students",
            values: []
        }],
        axes: ['left', 'bottom'],
        ticks: {
            left: 10
        },
        range: {
            left: [0, 100]
        }
    });

});

