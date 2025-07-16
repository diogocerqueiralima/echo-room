function openModal(title, method, action, data) {

    if (document.querySelector(".modal-overlay"))
        return;

    const overlay = document.createElement("div");
    overlay.classList.add("modal-overlay");

    const container = document.createElement("div");
    container.classList.add("modal");

    let html = `
        <div class="modal-header">
            <h6>${title}</h6>
            <i id="modal-close" class='bx bx-x'></i>
        </div>
        
        <form method="${method}" action="${action}">
    `;

    for (let input of data.inputs) {
        html += `
            <label>
                <span>${input.title}</span>
                <input
                    id="${input.name}"
                    class="modal-input"
                    type="${input.type}" 
                    name="${input.name}" 
                    value="${input.value}" 
                    placeholder="${input.placeholder}" 
                />
            </label>
        `;
    }

    html += `
            <button id="modal-submit" type="submit">${data.button}</button>
        </form>
    `;

    container.innerHTML = html;
    overlay.appendChild(container);
    document.body.append(overlay);

    document.getElementById("modal-close").addEventListener("click", () => overlay.remove());
    document.querySelectorAll(".modal-input").forEach(input => {
        input.addEventListener("input", validateAll);
    });

    validateAll();

    function validateAll() {

        let valid = true;
        for (let option of data.inputs) {

            const value = document.getElementById(option.name).value;
            if (!option.validate(value)) {
                valid = false;
                break;
            }

        }

        document.getElementById("modal-submit").disabled = !valid;
    }

}
