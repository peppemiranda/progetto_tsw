
const patternNome = /^[A-Za-z]+$/; 

function validateFormElem(formElem, pattern, span, errorMessage) {
    if (formElem.value.match(pattern)) {
        formElem.classList.remove("error");
        span.style.color = "black";
        span.innerHTML = "";
        return true;
    } else {
        formElem.classList.add("error");
        span.innerHTML = errorMessage;
        span.style.color = "red";
        return false;
    }
}