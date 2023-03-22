async function openAndFillModal(form, modal, id) {
    modal.show();
    let user = await getOneUser(id);
    form.id.value = user.id;
    form.name.value = user.name;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.username.value = user.username;
    form.password.value = user.password;
    form.roles.value = user.roles;
}