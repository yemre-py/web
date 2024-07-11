const todos = document.querySelectorAll('.todos');
const modal = document.getElementById('todoModal');
const btn = document.getElementById('myBtn');
const span = document.getElementsByClassName("close")[0];
const editModal = document.getElementById('editTodoModal');
const spanEdit = document.getElementsByClassName("close-edit")[0];

// Show pencil icon on hover and handle edit click
[todos].forEach(section => {
    section.forEach(item => {
        const pencil = item.querySelector('.pencil');

        item.addEventListener('mouseover', (e) => {
            pencil.style.display = 'block';
        });

        item.addEventListener('mouseout', (e) => {
            pencil.style.display = 'none';
        });

        pencil.addEventListener('click', (e) => {
            const todo = {
                id: pencil.getAttribute('data-id'),
                title: pencil.getAttribute('data-title'),
                description: pencil.getAttribute('data-description'),
                status: pencil.getAttribute('data-status')
            };
            openEditModal(todo);
        });
    });
});

function openEditModal(todo) {
    document.getElementById('edit-id').value = todo.id;
    document.getElementById('edit-title').value = todo.title;
    document.getElementById('edit-description').value = todo.description;
    document.querySelector(`input[name="status"][value="${todo.status}"]`).checked = true;

    editModal.style.display = "block";
}

// Hide modals
span.onclick = function () {
    modal.style.display = "none";
}
spanEdit.onclick = function () {
    editModal.style.display = "none";
}
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
    if (event.target == editModal) {
        editModal.style.display = "none";
    }
}

// Show the add modal
btn.onclick = function () {
    modal.style.display = "block";
}
