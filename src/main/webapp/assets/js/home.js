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
    var url = window.location.href;
    if (url.indexOf('?') > -1){
        url += '&param=1'
    }else{
        url += '?param=1'
    }
    window.location.href = url;
}