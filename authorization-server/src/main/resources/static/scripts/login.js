window.addEventListener("load", init)

function init() {
    const button = document.querySelector("button")
    const inputs = document.querySelectorAll("form input")

    inputs.forEach(input => {
        input.addEventListener("input", () => {

            const username = document.getElementById("username").value
            const password = document.getElementById("password").value

            button.disabled = !inputsAreValid(username, password);

        })
    })
}

function inputsAreValid(username, password) {
    return username.trim() !== "" && passwordIsValid(password)
}

function passwordIsValid(password) {

    const lengthValid = password.length >= 8 && password.length <= 30;
    const hasUpperCase = /[A-Z]/.test(password);
    const hasDigit = /\d/.test(password);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>_\-\\[\]\/~`+=;']/g.test(password);
    const hasNoWhitespace = !/\s/.test(password);

    return lengthValid && hasUpperCase && hasDigit && hasSpecialChar && hasNoWhitespace;
}