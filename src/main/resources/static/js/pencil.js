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
    if (event.target == updatesModal) {
        updatesModal.style.display = "none";
    }
}

// Show the add modal
btn.onclick = function () {
    modal.style.display = "block";
}


const updatesModal = document.getElementById('updatesModal');
const closeUpdates = document.getElementsByClassName("close-updates")[0];
const updateDatesList = document.getElementById('updateDatesList');

todos.forEach(todo => {
    todo.addEventListener('click', function() {
        fetch(`/api/todos/${todo.dataset.id}/updates`)
            .then(response => response.json())
            .then(data => {
                updateDatesList.innerHTML = ''; // Clear previous entries
                data.forEach(date => {
                    const li = document.createElement('li');
                    li.textContent = new Date(date).toLocaleDateString();
                    updateDatesList.appendChild(li);
                });
                updatesModal.style.display = 'block'; // Display the modal
            })
            .catch(error => console.error('Error fetching update dates:', error));
    });
});

// Close the updates modal
closeUpdates.onclick = function() {
    updatesModal.style.display = "none";
}
