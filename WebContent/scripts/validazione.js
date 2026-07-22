
const patternNomeCognome = /^[A-Za-z\s]+$/;
const patternEmail = /^\S+@\S+\.\S+$/;


function validateFormElem(formElem, pattern, spanId, errorMessage) {
	
    let span = document.getElementById(spanId);
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
    
    if (!validateFormElem(form.nome, patternNomeCognome, "errorNome", "Inserisci solo lettere")) valid = false;
    if (!validateFormElem(form.cognome, patternNomeCognome, "errorCognome", "Inserisci solo lettere")) valid = false;
    if (!validateFormElem(form.email, patternEmail, "errorEmail", "Formato email non valido (manca @ o punto)")) valid = false;
    
    let spanPwd = document.getElementById("errorPassword");
    if (form.password.value.length < 8) {
        form.password.classList.add("error");
        spanPwd.innerHTML = "La password deve essere lunga almeno 8 caratteri";
        spanPwd.style.color = "red";
        valid = false;
    } else {
        form.password.classList.remove("error");
        spanPwd.innerHTML = "";
    }
    
    return valid; 	//Se è false, il form NON viene inviato alla Servlet
}