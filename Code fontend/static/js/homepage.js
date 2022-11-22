if (getCookie("USER") == "") {
  window.location = "login.html";
}
loadData()

// get data
function getPump() {
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));

  var requestOptions = {
    method: "GET",
    headers: myHeaders,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/get-pump", requestOptions)
    .then((response) => response.json())
    .then((result) => {
      if (result == "0") document.getElementById("ct2p").checked = false;
      else document.getElementById("ct2p").checked = true;
    })
    .catch((error) => console.log("error", error));
}
function getTresholdSoilHuminity() {
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));

  var requestOptions = {
    method: "GET",
    headers: myHeaders,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/get-treshold-hum", requestOptions)
    .then((response) => response.text())
    .then((result) => {
      document.getElementById("inpRangeValue").value = result;
      document.getElementById("rangeValue").innerHTML = result;
    })
    .catch((error) => console.log("error", error));
}
function getAutoMode() {
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));

  var requestOptions = {
    method: "GET",
    headers: myHeaders,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/get-auto-mode", requestOptions)
    .then((response) => response.text())
    .then((result) => {
      if (result == "0") document.getElementById("ct1at").checked = false;
      else document.getElementById("ct1at").checked = true;
    })
    .catch((error) => console.log("error", error));
}
function getSoilHuminity() {
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));

  var requestOptions = {
    method: "GET",
    headers: myHeaders,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/get-soil-hum", requestOptions)
    .then((response) => response.json())
    .then((result) => {
      document.getElementById("soilHum").innerHTML = result + "%";
    })
    .catch((error) => console.log("error", error));
}
function getHuminity() {
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));

  var requestOptions = {
    method: "GET",
    headers: myHeaders,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/get-hum", requestOptions)
    .then((response) => response.json())
    .then((result) => {
      document.getElementById("hum").innerHTML = result + "%";
    })
    .catch((error) => console.log("error", error));
}
function getTemperature() {
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));

  var requestOptions = {
    method: "GET",
    headers: myHeaders,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/get-tem", requestOptions)
    .then((response) => response.json())
    .then((result) => {
      document.getElementById("tem").innerHTML = result + "°C";
    })
    .catch((error) => console.log("error", error));
}
function getRainForecast() {
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));

  var requestOptions = {
    method: "GET",
    headers: myHeaders,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/get-rain-forecast", requestOptions)
    .then((response) => response.json())
    .then((result) => {
      document.getElementById("rainF").innerHTML =
        result.percent.toFixed(2) + "% " + result.lable;
    })
    .catch((error) => console.log("error", error));
}
function getDataHHT() {
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));

  var requestOptions = {
    method: "GET",
    headers: myHeaders,
    redirect: "follow",
  };
  fetch("http://localhost:8080/api/get-data", requestOptions)
    .then((response) => response.json())
    .then((result) => {
      var dt = [];
      for (let i in result) {
        dt[i] = new Array(
          formatDate(result[i].date),
          result[i].soilHuminity,
          result[i].huminity,
          result[i].temperature
        );
      }
      google.charts.load("current", { packages: ["line"] });
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn("string", "Thời gian");
        data.addColumn("number", "Độ ẩm đất                ");
        data.addColumn("number", "Độ ẩm                          ");
        data.addColumn("number", "Nhiệt độ          ");
        data.addRows(dt);

        var options = {
          chart: {
            title: "Bảng dữ liệu độ ẩm, nhiệt độ, độ ẩm đất trong ngày",
            subtitle: "",
          },
          width: 900,
          height: 500,
        };

        var chart = new google.charts.Line(
          document.getElementById("linechart_material")
        );

        chart.draw(data, google.charts.Line.convertOptions(options));
      }
    })
    .catch((error) => console.log("error", error));
}
function getTimer() {
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));

  var requestOptions = {
    method: "GET",
    headers: myHeaders,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/get-timer", requestOptions)
    .then((response) => response.json())
    .then((result) => {
      henGioTC = document.getElementById("henGioTC");
      html = "";
      for (let r of result) {
        html += "<tr><td>" + r.hour + "</td>";
        if (r.repeats) {
          html +=
            '<td>Mỗi ngày</td><td><button class="qbtn"><i class="ti-trash" onclick="deleteTimer(' +
            r.id +
            ')"></i></button></td></tr>';
        } else {
          html +=
            "<td>" +
            formatDay(r.day) +
            '</td><td><button class="qbtn" onclick="deleteTimer(' +
            r.id +
            ')"><i class="ti-trash"></i></button></td></tr>';
        }
      }
      henGioTC.innerHTML = html;
    })
    .catch((error) => console.log("error", error));
}

function logout() {
  document.cookie = "USER= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
  location.reload();
}
function deleteTimer(id) {
  var requestOptions = {
    method: "DELETE",
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/del-timer?id=" + id, requestOptions)
    .then((response) => response.text())
    .then((result) => {
      if (result) {
        getTimer();
      } else {
        alert("Lỗi xoá hẹn giờ tưới cây! Vui lòng thử lại!");
      }
    })
    .catch((error) => console.log("error", error));
}
function getCookie(cname) {
  let name = cname + "=";
  let ca = document.cookie.split(";");
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == " ") {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
function formatDate(da) {
  var d = new Date(da);
  return (
    (d.getHours() < 10 ? "0" + d.getHours() : d.getHours()) +
    ":" +
    (d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes())
  );
}
function formatDay(da) {
  var d = da.split("-");
  return d[2] + "/" + d[1] + "/" + d[0];
}
function chooseTypeTimer(elm) {
  if (elm.value == "1")
    document.getElementById("oneDay").style.display = "block";
  else document.getElementById("oneDay").style.display = "none";
}
function closeFormHG() {
  document.getElementById("formHenGioTC").style.display = "none";
}
function openFormHG() {
  document.getElementById("formHenGioTC").style.display = "block";
}
function saveFormHG() {
  timeHG = document.getElementById("timeHG").value;
  dateHG = document.getElementById("dateHG").value;
  if (timeHG == "") {
    alert("Vui lòng nhập giờ tưới cây!");
  } else if (
    document.getElementsByName("typeTimer")[1].checked &&
    dateHG == ""
  ) {
    alert("Vui lòng nhập ngày tưới cây!");
  } else {
    var myHeaders = new Headers();
    myHeaders.append("account_id", getCookie("USER"));
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      day: dateHG,
      hour: timeHG + ":00",
      repeats: document.getElementsByName("typeTimer")[0].checked,
      account: null,
    });

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    console.log(raw);

    fetch("http://localhost:8080/api/set-timer", requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log(result);
        getTimer();
        closeFormHG();
      })
      .catch((error) => console.log("error", error));
  }
}
function CT1(elm) {
  var vl;
  if (elm.checked) {
    vl = 1;
  } else {
    vl = 0;
  }
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    name: "Sensor.Parameter2",
    value: vl,
  });

  var requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/set-parameter", requestOptions)
    .then((response) => response.text())
    .then((result) => console.log(result))
    .catch((error) => console.log("error", error));
}
function CT2(elm) {
  var vl;
  if (elm.checked) {
    vl = 1;
  } else {
    vl = 0;
  }
  if(vl == 1 && checkDataRainF()){
    if(!confirm("Có thể có mưa trong 1 giờ tới. Bạn có chắc chắn muốn tới cây?")){
      return;
    }
  }
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    name: "Sensor.Parameter3",
    value: vl,
  });

  var requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/set-parameter", requestOptions)
    .then((response) => response.text())
    .then((result) => console.log(result))
    .catch((error) => console.log("error", error));
}
function rangeSlide(value) {
  document.getElementById("rangeValue").innerHTML = value;
  var myHeaders = new Headers();
  myHeaders.append("account_id", getCookie("USER"));
  myHeaders.append("Content-Type", "application/json");

  var raw = JSON.stringify({
    name: "Sensor.Parameter1",
    value: value,
  });

  var requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: raw,
    redirect: "follow",
  };

  fetch("http://localhost:8080/api/set-parameter", requestOptions)
    .then((response) => response.text())
    .then((result) => {
      // console.log(result);
    })
    .catch((error) => console.log("error", error));
}
function checkDataRainF(){
  var dt = document.getElementById("rainF").innerHTML.split('% ')
  if(dt[1] != 'Không mưa' && dt[0] >= 90)
    return true
  else
    return false
}
function loadData(){
  getSoilHuminity();
  getHuminity();
  getTemperature();
  getRainForecast();
  getTimer();
  getDataHHT();
  getPump();
  getAutoMode();
  getTresholdSoilHuminity();
  setInterval(loadData1s, 1000);
  setInterval(loadData1m, 60000);
  setInterval(loadData1h, 3600000);
}
function loadData1s(){
  getPump();
}
function loadData1m(){
  getSoilHuminity();
  getHuminity();
  getTemperature();
}
function loadData1h(){
  getRainForecast();
}