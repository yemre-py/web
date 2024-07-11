document.addEventListener("DOMContentLoaded", function () {

    const todos = document.querySelectorAll('.todos')

    todos.forEach(todo => {

        const pencil = document.querySelectorAll('.pencil')

        todo.addEventListener('mouseover', () => {
            pencil.forEach(pencil => {
                pencil.style.display = 'block'
            })
        })
        todo.addEventListener('mouseout', () => {
            pencil.forEach(pencil => {
                pencil.style.display = 'none'
            })
        })
    })

})

