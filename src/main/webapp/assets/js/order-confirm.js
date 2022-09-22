Date.prototype.addDays = function(days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}

function checkPassportDate() {
    let element = document.getElementById('passportValid');
    let selectedText = element.value;
    let selectedDate = new Date(selectedText);
    let now = new Date();
    if (selectedDate < now) {
        alert("Date must be in the future");
        element.value = "";
        return false;
    }
    return true;
}
function lease_beginChange(){
    let element = document.getElementById('lease_begin');
    let selectedDate = parseDateValue('lease_begin');
    let now = getDateNow();
    if (selectedDate < now) {
        alert("Date must be in the future");
        element.value = "";
    }else{
        let lease_finish = parseDateValue('lease_finish');
        if(lease_finish <= lease_begin) {
            alert("Finish rent must be after begin rent");
            document.getElementById('lease_finish').value = '';
        }
    }
    countDaysRent();
}
function lease_finishChange(){
    let element = document.getElementById('lease_finish');

    let lease_begin = parseDateValue('lease_begin');
    let lease_finish = parseDateValue('lease_finish');
    let now = getDateNow();
    if (lease_finish < now) {
        alert("Date must be in the future");
        element.value = "";
    }else if(lease_finish <= lease_begin) {
        alert("Finish rent must be after begin rent");
        element.value = "";
    }
    countDaysRent();
}
function parseDateValue(elementId){
    let element = document.getElementById(elementId);
    let selectedText = element.value;
    return new Date(selectedText);
}
function getDateNow(){
    return new Date().setUTCHours(0,0,0,0);
}
function datediff(first, second) {
    // Take the difference between the dates and divide by milliseconds per day.
    // Round to nearest whole number to deal with DST.
    return Math.round((second-first)/(1000*60*60*24));
}
function countDaysRent(){
    let lease_begin = parseDateValue('lease_begin');
    let lease_finish = parseDateValue('lease_finish');
    document.getElementById("lease_term").value = datediff(lease_begin, lease_finish);
    lease_termChange();
}
function valueFormatDate(date){
    const yyyy = date.getFullYear();
    let mm = date.getMonth() + 1; // Months start at 0!
    let dd = date.getDate();
    if (dd < 10) dd = '0' + dd;
    if (mm < 10) mm = '0' + mm;
    return yyyy + '-' + mm + '-' + dd;
}
function lease_termChange(){
    element = document.getElementById("lease_term");
    days = parseInt(element.value);
    document.getElementById('lease_finish').value =
        valueFormatDate(new Date().addDays(days));
    document.getElementById('ammount').value =
        (parseFloat(document.getElementById('price').value) * days)
            .toFixed(2);
}
function afterLoad(){
    element = document.getElementById("lease_begin");
    element.setAttribute("value", valueFormatDate(new Date()));
    element.setAttribute("min", valueFormatDate(new Date()));
    delete element;

    element = document.getElementById("lease_finish");
    element.setAttribute("value", valueFormatDate((new Date()).addDays(1)));
    element.setAttribute("min", valueFormatDate((new Date()).addDays(1)));
    element.setAttribute("max", valueFormatDate((new Date()).addDays(30)));
    countDaysRent();

}

document.getElementById('passportValid').addEventListener('change', checkPassportDate);
document.getElementById("lease_begin").addEventListener("change", lease_beginChange);
document.getElementById("lease_finish").addEventListener("change", lease_finishChange);
document.getElementById("lease_term").addEventListener("change", lease_termChange);

afterLoad();