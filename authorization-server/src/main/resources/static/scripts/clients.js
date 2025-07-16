window.addEventListener("load", init)

function init() {

    const createBtn = document.getElementById("create")
    const csrfToken = createBtn.dataset.csrfToken

    createBtn.addEventListener("click", (e) => {

        openModal(
            "Create Client",
            "post",
            "/admin/clients",
            {
                button: "Create",
                inputs: [
                    {
                        title: "",
                        name: "_csrf",
                        value: csrfToken,
                        type: "hidden",
                        placeholder: "",
                        validate: () => true
                    },
                    {
                        title: "Client Name",
                        name: "clientName",
                        value: "",
                        type: "text",
                        placeholder: "Restaurant Application",
                        validate: value => value.trim() !== ""
                    },
                    {
                        title: "Client ID",
                        name: "clientId",
                        value: "",
                        type: "text",
                        placeholder: "restaurant_application",
                        validate: value => value.trim() !== ""
                    },
                    {
                        title: "Scopes",
                        name: "scopes",
                        value: "",
                        type: "text",
                        placeholder: "openid, read, write",
                        validate: value => value.trim() !== ""
                    },
                    {
                        title: "Authorization Grant Types",
                        name: "authorizationGrantTypes",
                        value: "",
                        type: "text",
                        placeholder: "AUTHORIZATION_CODE, REFRESH_TOKEN",
                        validate: value => value.trim() !== ""
                    },
                    {
                        title: "Redirect URIs",
                        name: "redirectUris",
                        value: "",
                        type: "text",
                        placeholder: "http://localhost/redirect",
                        validate: value => value.trim() !== ""
                    }
                ]
            }
        )

    })

    const copySecretBtn = document.getElementById("copySecretBtn")

    copySecretBtn.addEventListener('click', () => {

        const secretInput = document.getElementById('secretInput');
        secretInput.select();
        secretInput.setSelectionRange(0, 99999);

        navigator.clipboard.writeText(secretInput.value).then(() => {
            copySecretBtn.style.backgroundColor = "var(--green)"
            copySecretBtn.style.cursor = "default"
            copySecretBtn.textContent = "Client Secret copied!"
        })
    });

}