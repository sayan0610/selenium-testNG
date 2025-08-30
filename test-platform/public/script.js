const taskForm = document.getElementById('task-form');
const taskInput = document.getElementById('task-input');
const taskList = document.getElementById('task-list');

const API_URL = 'http://localhost:3000/api/tasks';

// Fetch and display tasks
async function fetchTasks() {
  const response = await fetch(API_URL);
  const tasks = await response.json();
  taskList.innerHTML = '';
  tasks.forEach((task) => {
    const li = document.createElement('li');
    li.innerHTML = `
      <span style="text-decoration: ${task.completed ? 'line-through' : 'none'}" id="task-title-${task.id}">
        ${task.title}
      </span>
      <button onclick="toggleTask(${task.id}, ${task.completed})">
        ${task.completed ? 'Undo' : 'Complete'}
      </button>
      <button onclick="showEditInput(${task.id}, '${task.title.replace(/'/g, "\'")}')">Edit</button>
      <button onclick="deleteTask(${task.id})">Delete</button>
      <span id="edit-input-${task.id}" style="display:none;"></span>
    `;
    taskList.appendChild(li);
  });
// Show inline input for editing a task
window.showEditInput = function(id, currentTitle) {
  const inputSpan = document.getElementById(`edit-input-${id}`);
  inputSpan.style.display = 'inline';
  inputSpan.innerHTML = `
    <input type="text" id="edit-field-${id}" value="${currentTitle}" style="margin-left:8px;" />
    <button onclick="submitEdit(${id})">Save</button>
    <button onclick="cancelEdit(${id})">Cancel</button>
  `;
}

window.submitEdit = async function(id) {
  const input = document.getElementById(`edit-field-${id}`);
  const newTitle = input.value;
  await fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title: newTitle }),
  });
  fetchTasks();
}

window.cancelEdit = function(id) {
  const inputSpan = document.getElementById(`edit-input-${id}`);
  inputSpan.style.display = 'none';
  inputSpan.innerHTML = '';
}
}

// Add a new task
taskForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const title = taskInput.value;
  await fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title }),
  });
  taskInput.value = '';
  fetchTasks();
});

// Toggle task completion
async function toggleTask(id, completed) {
  await fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ completed: !completed }),
  });
  fetchTasks();
}

// Delete a task
async function deleteTask(id) {
  await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
  fetchTasks();
}

// Initial fetch
fetchTasks();