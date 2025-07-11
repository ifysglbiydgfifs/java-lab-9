async function fetchItems() {
    const res = await fetch('/items');
    const items = await res.json();
    const list = document.getElementById('itemsList');
    list.innerHTML = '';
    items.forEach(item => {
        const li = document.createElement('li');
        li.textContent = item.name + (item.purchased ? " (✓)" : "");
        li.style.textDecoration = item.purchased ? "line-through" : "none";
        li.onclick = () => toggleItem(item.id);

        const delBtn = document.createElement('button');
        delBtn.textContent = "X";
        delBtn.onclick = (e) => {
            e.stopPropagation();
            deleteItem(item.id);
        };
        li.appendChild(delBtn);
        list.appendChild(li);
    });
}

async function addItem() {
    const name = document.getElementById('itemInput').value;
    if (!name.trim()) return;
    await fetch('/items', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name })
    });
    document.getElementById('itemInput').value = '';
    fetchItems();
}

async function deleteItem(id) {
    await fetch('/items/' + id, { method: 'DELETE' });
    fetchItems();
}

async function toggleItem(id) {
    await fetch(`/items/${id}/toggle`, { method: 'PUT' });
    fetchItems();
}

document.addEventListener('DOMContentLoaded', fetchItems);
