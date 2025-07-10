window.addEventListener("load", init)

const EMAIL_REGEX = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

function init() {
    const button = document.querySelector("button")
    const inputs = document.querySelectorAll("form input")

    inputs.forEach(input => {
        input.addEventListener("input", () => {

            const firstName = document.getElementById("firstName").value
            const lastName = document.getElementById("lastName").value
            const username = document.getElementById("username").value
            const email = document.getElementById("email").value
            const password = document.getElementById("password").value
            const confirmPassword = document.getElementById("confirmPassword").value
            const agreement = document.getElementById("agreement").checked

            button.disabled = !inputsAreValid(firstName, lastName, username, email, password, confirmPassword, agreement);

        })
    })
}

function inputsAreValid(firstName, lastName, username, email, password, confirmPassword, agreement) {
    return firstName.trim() !== "" && lastName.trim() !== "" && username.trim() !== "" && EMAIL_REGEX.test(email) &&
        password.trim() !== "" && password === confirmPassword && agreement
}