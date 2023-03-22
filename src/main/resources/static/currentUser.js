"use strict";

getCurrentUser()

function getCurrentUser() {
    fetch("http://localhost:8080/api/user")
        .then(res => res.json())
        .then(js => {
            let roles = [];
            for (let role of js.roles) {
                roles.push(" " + role.name.toString().replaceAll("ROLE_", ""))
            }
            $('#emailCurrentUser').append(`<span>${js.username}</span>`)
            $('#roleCurrentUser').append(`<span>with roles: ${roles}</span>`)
            const user = `$(
                    <tr>
                        <td>${js.id}</td>
                        <td>${js.name}</td>
                        <td>${js.lastName}</td>
                        <td>${js.age}</td>
                        <td>${js.username}</td>
                        <td>${roles}</td>
                    </tr>)`;
            $('#oneUser').append(user)
        })
}
