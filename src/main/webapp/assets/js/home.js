document.getElementById('filter_brand').addEventListener('change', reloadWithParams);
document.getElementById('filter_quality').addEventListener('change', reloadWithParams);
document.getElementById('sort_by').addEventListener('change', reloadWithParams);
Array.from(document.getElementsByClassName('page_change')).forEach(
    function setEventListener(element) {
        element.addEventListener('click', reloadWithParams);
        element.style.cursor = 'pointer';
    }
);

function reloadWithParams(event) {

    let brand_id = document.getElementById("filter_brand").value;
    let quality_id = document.getElementById("filter_quality").value;
    let sort_id = document.getElementById("sort_by").value;

    // var url = window.location.href;
    var url = window.location.pathname;
    if (brand_id) {
        url = addParameter(url, "brand=" + brand_id);
    }
    if (quality_id) {
        url = addParameter(url, "quality=" + quality_id);
    }
    if (sort_id) {
        url = addParameter(url, "sort=" + sort_id);
    }

    let page = undefined;
    let params = new URLSearchParams(window.location.search);
    if (this.hasAttribute("aria-label")) {
        let value = this.getAttribute("aria-label");
        if (value === 'Previous') {
            page = params.get('page');
            if (page !== undefined) {
                page = Math.max(parseInt(page) - 1, 1);
            } else {
                page = 1;
            }
        } else if (value === 'Next') {
            page = params.get('page');
            if (page !== undefined) {
                page = parseInt(page) + 1;
            } else {
                page = 1;
            }
        }

    } else {
        // Page
        params = new URLSearchParams(window.location.search);
        if (this.classList.contains("page-link")) {
            page = this.text;
        } else if (params.has('page')) {
            // page = params.get('page');
            page = 1;
        }
    }
    if (page !== undefined) {
        url = addParameter(url, "page=" + page);
    }

    window.location.href = url;
}

function addParameter(url, value) {
    if (url.indexOf('?') > -1) {
        url += '&';
    } else {
        url += '?';
    }
    url += value;
    return url;
}