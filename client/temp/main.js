const serverIP = location.hostname;
const token = uuidv4();

const socket = new WebSocket("ws://planetbuilder.de:9000/" + token);
socket.onopen = () => {
    console.log("open!");
}

var gotdata = false;

var groupNames = [
    'minimum',
    '1st quartile',
    'median',
    '3rd quartile',
    'maximum',
    '5% low'
];
var showGroup = [
    false,
    true,
    true,
    true,
    false,
    true
];
var minimumValue = [
    0,0,0,0,0,0
]
var maximumValue = 10000000
var container
var graph;
var dataset;
var groups = new vis.DataSet().add(
    {
        id: 0,
        content: 'minimum'
    },
    {
        id: 1,
        content: '1st quartile'
    },
    {
        id: 2,
        content: 'median'
    },
    {
        id: 3,
        content: '3rd quartile'
    },
    {
        id: 4,
        content: 'maximum'
    },
    {
        id: 5,
        content: '5% low'
    });
var options;

var data;

socket.onmessage = (msg) => {
    if (gotdata) return;
    if (typeof msg.data === "object") return;
    gotdata = true;
    data = JSON.parse(msg.data);


    container = document.getElementById('graph');
    options = {
        defaultGroup: '',
        legend: true,
        drawPoints: false,
        start: data[data.length - 1].timestamp - 2400000,
        end: data[data.length - 1].timestamp + 1200000,
        min: data[0].timestamp,
        max: data[data.length - 1].timestamp + 1200000,
        showCurrentTime: false
    };
    

    dataset = new vis.DataSet(getItems());
    graph = new vis.Graph2d(container, dataset, groups, options);
    updateGraph();
}

function getItems() {
    var items = []
    var itemsIndex = 0
    for (let index = 0; index < data.length; index++) {
        for (let index2 = 0; index2 < 6; index2++) {
            if (data[index].boxplotValues[index2] >= minimumValue[index2]) {
                items[itemsIndex] = { x: data[index].timestamp, y: data[index].boxplotValues[index2], group: groupNames[index2] };
                itemsIndex++;
            }
        }
    }
    return items;
}

function updateGraph() {
    dataset = new vis.DataSet(getItems())
    document.getElementById('graph').remove();

    container = document.createElement("div");
    container.id = "graph"
    container.style = "position: relative;"
    document.body.insertBefore(container, document.getElementById('buttons'))
    graph = new vis.Graph2d(container, dataset, groups, options)
}

document.getElementsByClassName('minimumValue').onclick = function() {
    var value = document.getElementsByClassName('').value;
    alert(value);
}

function checkIfEnterThenMinimum(event,i) {
    console.log("hi")
    if (event.keyCode === 13) {
        setMinimumValue(i)
    }
}

function setMinimumValue(i) {
    let value = document.getElementById("minimumValue" + i + "").value;
    console.log(i);
    console.log("minimumValue" + i);
    console.log(value)
    if (!isNaN(value)) {
        minimumValue[i] = value;
    }
    updateGraph();
}

function uuidv4() {
    return ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c =>
        (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );
}

