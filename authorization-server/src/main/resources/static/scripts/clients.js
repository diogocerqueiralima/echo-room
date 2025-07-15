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
                        placeholder: ""
                    },
                    {
                        title: "Client Name",
                        name: "clientName",
                        value: "",
                        type: "text",
                        placeholder: "Restaurant Application"
                    },
                    {
                        title: "Client ID",
                        name: "clientId",
                        value: "",
                        type: "text",
                        placeholder: "restaurant_application"
                    },
                    {
                        title: "Client Secret",
                        name: "clientSecret",
                        value: "",
                        type: "text",
                        placeholder: "my_secret_code"
                    },
                    {
                        title: "Scopes",
                        name: "scopes",
                        value: "",
                        type: "text",
                        placeholder: "[openid, read, write]"
                    },
                    {
                        title: "Redirect URIs",
                        name: "redirectUris",
                        value: "",
                        type: "text",
                        placeholder: "[http://localhost/redirect]"
                    }
                ]
            }
        )

    })

}