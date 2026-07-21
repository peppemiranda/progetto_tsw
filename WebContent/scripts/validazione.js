
const patternNome = /^[A-Za-z]+$/;
const patternEmail = /^\S+@\S+\.\S+$/;
const patternTelefono = /^([0-9]{3}-[0-9]{7})$/;

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


function validaRegistrazione(form) {
    let valid = true;
    
    if (!validateFormElem(form.nome, patternNome, document.getElementById("errorNome"), "Solo lettere")) {
        valid = false;
    }
    
    return valid;
}