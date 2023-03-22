"use strict";
const tbody = $("#AllUsers");
getUserTable()

function getUserTable() {
    tbody.empty()
    fetch("http://localhost:8080/api/admin")
        .then(res => res.json())
        .then(js => {
            js.forEach(item => {
                let roles = [];
                for (let role of item.roles) {
                    roles.push(" " + role.name.toString().replaceAll("ROLE_", ""))
                }
                const users = `$(
                    <tr>
                        <td class="pt-3" id="userID">${item.id}</td>
                        <td class="pt-3" >${item.name}</td>
                        <td class="pt-3" >${item.lastName}</td>  
                        <td class="pt-3" >${item.age}</td>
                        <td class="pt-3" >${item.username}</td>
                        <td class="pt-3" >${roles}</td>
                        <td>
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#edit" onclick="editModal(${item.id})">
                            Edit
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete" onclick="deleteModal(${item.id})">
                                Delete
                            </button>
                        </td>
                    </tr>)`;
                tbody.append(users)
            })
        })
}