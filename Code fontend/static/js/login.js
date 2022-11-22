function login() {
  username = document.getElementById("username").value;
  password = document.getElementById("password").value;
  if (username == "" || password == "")
    alert("Vui lòng nhập tên đăng nhập và mật khẩu!");
  else {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      username: username,
      password: password
    });

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    fetch("http://localhost:8080/login", requestOptions)
      .then((response) => response.json())
      .then((result) => {
        if(result.code == "OK"){
            document.cookie = "USER=" + result.value
            window.location = 'homepage.html'
        } else {
            alert(result.value)
        }
      })
      .catch((error) => console.log("error", error));
  }
}
