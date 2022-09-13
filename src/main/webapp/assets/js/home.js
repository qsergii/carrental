function filter_brand_change() {
    brand_id = document.getElementById("filter_brand").value;
    reloadWithParams();
}
document.getElementById('filter_brand').addEventListener('change', filter_brand_change);

function filter_quality_change() {
    d = document.getElementById("filter_quality").value;
    reloadWithParams();
}
document.getElementById('filter_quality').addEventListener('change', filter_quality_change);

function sort_by_change() {
    d = document.getElementById("sort_by").value;
    reloadWithParams();
}
document.getElementById('sort_by').addEventListener('change', sort_by_change);

function reloadWithParams(){

    brand_id = document.getElementById("filter_brand").value;
    quality_id = document.getElementById("filter_quality").value;
    sort_id = document.getElementById("sort_by").value;

    // var url = window.location.href;
    var url = window.location.pathname;
    if(brand_id){
        url = addParameter(url, "brand="+brand_id);
    }
    if(quality_id){
        url = addParameter(url, "quality="+quality_id);
    }
    if(sort_id){
        url = addParameter(url, "sort="+sort_id);
    }

    window.location.href = url;
}

function addParameter(url, value){
    if (url.indexOf('?') > -1){
        url += '&';
    }else{
        url += '?';
    }
    url += value;
    return url;
}