function reorderCar() {
    let carId = document.getElementById("carId").value;
    window.location = "cars?id="+carId;
}

document.getElementById("reorderCar").addEventListener("click", reorderCar)