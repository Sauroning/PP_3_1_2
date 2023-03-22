"use strict";

let deleteForm = document.forms["formDelete"]
deleteUser()

async function deleteModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#delete'));
    await openAndFillModal(deleteForm, modal, id);
}

function deleteUser() {
    deleteForm.addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8080/api/admin/" + deleteForm.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#closeDelete').click();
            getUserTable();
        });
    });
}