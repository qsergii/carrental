function addParameter(url, value) {
    if (url.indexOf('?') > -1) {
        url += '&';
    } else {
        url += '?';
    }
    url += value;
    return url;
}
// function setLanguage(){
//     let language = document.getElementById("language").value;
//     let url = window.location.pathname;
//     url = addParameter(url, "lang="+language);
//     window.location.href = url;
// }
// document.getElementById('language').addEventListener('change', setLanguage);