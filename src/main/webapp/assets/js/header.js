function addParameter(url, value) {
    if (url.indexOf('?') > -1) {
        url += '&';
    } else {
        url += '?';
    }
    url += value;
    return url;
}

function replaceUrlParam(url, paramName, paramValue) {
    if (paramValue == null) {
        paramValue = '';
    }
    var pattern = new RegExp('\\b(' + paramName + '=).*?(&|#|$)');
    if (url.search(pattern) >= 0) {
        return url.replace(pattern, '$1' + paramValue + '$2');
    }
    url = url.replace(/[?#]$/, '');
    return url + (url.indexOf('?') > 0 ? '&' : '?') + paramName + '=' + paramValue;
}

function setLanguage() {
    const language = document.getElementById("languageAncore");
    let newLanguage = language.dataset.lang;
    let url = window.location.href;
    // url = addParameter(url, "lang=" + newLanguage);
    url = replaceUrlParam(url, "lang", newLanguage);
    window.location.href = url;
}

document.getElementById('language').addEventListener('click', setLanguage);
