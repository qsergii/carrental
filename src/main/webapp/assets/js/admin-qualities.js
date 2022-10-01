function deleteBrand() {
    let id = parseInt(document.getElementById("id").value);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "qualities");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.onload = () => {
        answer = xhr.responseText;
        if (answer === "ok") {
            window.location = "qualities";
        } else {
            alert(answer);
        }
    };
    let data = "id=" + id + "&action=delete";
    xhr.send(data);
}

document.getElementById("deleteButton").addEventListener("click", deleteBrand);

if (document.getElementById("id").value === ""
    || document.getElementById("id").value === "0") {
    document.getElementById("deleteButton").style.display = 'none';
}
console.log(document.getElementById("id").value);
