function openModal(title, method, action, data) {
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
                    type="${input.type}" 
                    name="${input.name}" 
                    value="${input.value}" 
                    placeholder="${input.placeholder}" 
                />
            </label>
        `;
    }

    html += `
            <button type="submit">${data.button}</button>
        </form>
    `;

    container.innerHTML = html;
    overlay.appendChild(container);
    document.body.append(overlay);

    document.getElementById("modal-close").addEventListener("click", () => overlay.remove());
}
