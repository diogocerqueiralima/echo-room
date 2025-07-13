window.addEventListener('load', init)

function init() {

    let state = true
    const options = document.querySelectorAll('.minimize');
    const close = document.getElementById("close")
    const header = document.querySelector('header')

    close.addEventListener('click', () => {

        if (state) {
            header.style.width = '100px'
            options.forEach(item => item.style.display = "none")
            close.classList.remove("bx-x")
            close.classList.add("bx-menu")
        } else {
            header.style.width = '350px'
            options.forEach(item => item.style.display = "flex")
            close.classList.remove("bx-menu")
            close.classList.add("bx-x")
        }

        state = !state
    })

}