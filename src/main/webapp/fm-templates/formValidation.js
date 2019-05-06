function checkEverything() {
    var res1 = validateFieldEmail();
    var res2 = validateFieldPassword();
    var res3 = validateConfirmFieldPassword();
    var res4 = validateNameField();
    var res5 = validateSurnameField();
    var res6 = validatePhoneNumberField();


    //if (res1 && res2 && res3 && res4 && res5 && res6) {
    document.getElementById('id1').submit();
    //}
}


//Check if the content of the field for e-mail is a valid e-mail address.
function validateFieldEmail() {
    let element = document.getElementById('email-error');
    if (validateEmail('register-email')) {
        element.innerText = "";
        return true;
    } else if (isEmpty('register-email')) {
        element.innerText = "Podaj swój adres e-mail."
        return false;
    } else {
        element.innerText = "Podany przez Ciebie adres e-mail jest nieprawidłowy.";
        return false;
    }
}

//Check if password has between 8 and 20 characters and does not contain whitespace characters.
function validateFieldPassword() {
    let expression = /\s+/;
    let password = document.getElementById('register-password').value;
    let element = document.getElementById('password-error');
    if (expression.test(password)) {
        element.innerHTML = "Hasło nie może zawierać spacji, tabulatorów ani innych 'białych znaków'";
        return false;
    } else if ((!validateMinLengthString('register-password', 8) || !validateMaxLengthString('register-password', 20))) {
        element.innerHTML = "Hasło musi zawierać pomiędzy 8 a 20 znaków.";
        return false;
    } else if (isEmpty('register-password')) {
        element.innerText = "Podaj hasło."
        return false;
    } else {
        element.innerHTML = "";
        return true;
    }

    if (!(document.getElementById('register-confirmPassword').value === "")) {
        validateConfirmFieldPassword();
    }

}

//Check if confirmPassword is the same as password.
function validateConfirmFieldPassword() {
    let element = document.getElementById('confirmPassword-error');
    let check = document.getElementById('register-password').value === document.getElementById('register-confirmPassword').value;
    if (!check) {
        element.innerHTML = "Hasło jest niezgodne.";
        return false;
    } else if (isEmpty('register-password')) {
        element.innerText = "Potwierdź hasło."
        return false;
    } else {
        element.innerHTML = "";
        return true;
    }
}


//Check if name contains only letters and whitespace characters
function validateNameField() {
    let htmlElement = document.getElementById('name-error');
    let valueOfField = document.getElementById('register-name').value;
    if (valueOfField.replace(/\s/g, '').length === 0) {
        htmlElement.innerHTML = "Wpisz imię.";
        return false;
    } else if (validateString(valueOfField)) {
        htmlElement.innerHTML = "";
        return true;
    } else {
        htmlElement.innerHTML = "Niepoprawne imię. Imię musi zawierać tylko litery.";
        return false;
    }
}


//Check if surname contains only letters and white space characters
function validateSurnameField() {
    let htmlElement = document.getElementById('surname-error');
    let valueOfField = document.getElementById('register-surname').value;

    if (valueOfField.replace(/\s/g, '').length === 0) {
        htmlElement.innerHTML = "Wpisz nazwisko.";
        return false;
    } else if (validateString(valueOfField)) {
        htmlElement.innerHTML = "";
        return true;
    } else {
        htmlElement.innerHTML = "Niepoprawne nazwisko. Nazwisko musi zawierać tylko litery.";
        return false;
    }
}


//Only nine digits telephone numbers starting from 0048 or +48 or just bare nine digits phone numbers are accepted.
function validatePhoneNumberField() {
    let expression = /^\d{9}$|^(0048)(\d{9})$|^(\+48)\d{9}$/;
    let phoneNumber = document.getElementById('register-phoneNumber').value.replace(/-|\s/g, "");
    let htmlElement = document.getElementById('phoneNumber-error');
    if (expression.test(phoneNumber)) {
        htmlElement.innerHTML = "";
        return true;
    } else if (isEmpty('register-phoneNumber')) {
        htmlElement.innerHTML = "Wpisz numer telefonu.";
        return false;
    } else {
        htmlElement.innerHTML = "Niepoprawny numer telefonu.";
        return false;
    }

}






//Checking if content of a given field is a valid e-mail address using regex expression from validate.js library
function validateEmail(fieldID) {
    let expression = /^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])$/i;
    let email = document.getElementById(fieldID).value;
    return expression.test(email.toLowerCase());
}


//Util functions
function validateString(valueOfField) {
    let letters = /^[a-zA-Z\s]+$/;
    return letters.test(String(valueOfField));
}

function validateMaxLengthString(fieldID, maxLength) {
    let name = document.getElementById(fieldID).value.replace(/\s/g, '');
    return name.length <= maxLength;
}

function validateMinLengthString(fieldID, minLength) {
    let name = document.getElementById(fieldID).value.replace(/\s/g, '');
    return name.length >= minLength;
}

function isEmpty(fieldID) {
    let len = document.getElementById(fieldID).value.replace(/\s/g, '').length;
    return len === 0;
}
