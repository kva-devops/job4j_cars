function validate() {
    let elements = document.forms[0].elements;
    for (let i = 0; i < elements.length - 1; i++) {
        if ($(elements[i]).val() === '') {
            alert($(elements[i]).attr('title'));
            return false;
        }
    }
    return true;
}

window.onload = function () {
    let currentPath = document.baseURI;
    let indexPath = document.location.protocol + "//" + window.location.host + "/cars/";
    console.log(currentPath);
    console.log(indexPath);
    if (currentPath === indexPath) {
        console.log(true);
        return window.location.href = "/cars/all";
    }
}