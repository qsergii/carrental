function checkPassportDate() {
    var element = document.getElementById('passportValid');
    var selectedText = element.value;
    var selectedDate = new Date(selectedText);
    var now = new Date();
    if (selectedDate < now) {
        alert("Date must be in the future");
        element.value = "";
        return false;
    }
    return true;
}
document.getElementById('passportValid')
    .addEventListener('change', checkPassportDate);
// $('form').submit(function (){
//     var element = document.getElementById('passportValid');
//     var selectedText = element.value;
//     var selectedDate = new Date(selectedText);
//     var now = new Date();
//     if (selectedDate < now) {
//         alert("Date must be in the future");
//         element.value = "";
//         return false;
//     }
//     return true;
// });