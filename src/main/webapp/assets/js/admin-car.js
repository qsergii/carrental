function deleteCar() {
    let id = parseInt(document.getElementById("id").value);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "cars");
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.onload = ()=>{
        // console.log(xhr.responseText)
        answer = xhr.responseText;
        if(answer === "ok"){
            window.location = "cars";
        }else{
            alert(answer);
        }
    };
    let data = `id=`+id+"&action=delete";
    xhr.send(data);
}

document.getElementById("deleteCar").addEventListener("click", deleteCar);