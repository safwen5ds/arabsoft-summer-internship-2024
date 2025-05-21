document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('#attestationTable').addEventListener('click', async function(event) {
        if (event.target.classList.contains('update-button')) {
            const tin = event.target.getAttribute('data-tin');
            await handleUpdate(tin);
        }
    });

    document.querySelector('#attestationTable').addEventListener('click', async function(event) {
        if (event.target.classList.contains('delete-button')) {
            const tin = event.target.getAttribute('data-tin');
            await handleDelete(tin);
        }
    });

    document.getElementById('searchInput').addEventListener('keyup', function() {
        let filter = this.value.toLowerCase();
        let rows = document.querySelectorAll('#attestationTable tbody tr');

        rows.forEach(row => {
            let cells = row.querySelectorAll('td');
            let match = Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(filter));
            row.style.display = match ? '' : 'none';
        });
    });

    document.querySelectorAll('#attestationTable th').forEach(header => {
        header.addEventListener('click', function() {
            let table = header.parentElement.parentElement.parentElement;
            let tbody = table.querySelector('tbody');
            let rows = Array.from(tbody.querySelectorAll('tr'));
            let column = header.getAttribute('data-column');
            let order = header.getAttribute('data-order');
            let newOrder = order === 'asc' ? 'desc' : 'asc';

            rows.sort((a, b) => {
                let aText = a.querySelector(`[data-label="${header.textContent}"]`).textContent.trim();
                let bText = b.querySelector(`[data-label="${header.textContent}"]`).textContent.trim();

                return (aText > bText ? 1 : -1) * (newOrder === 'asc' ? 1 : -1);
            });

            tbody.innerHTML = '';
            rows.forEach(row => tbody.appendChild(row));
            header.setAttribute('data-order', newOrder);
        });
    });
});

async function handleUpdate(tin) {
    console.log('handleUpdate called with tin:', tin);
    const row = document.querySelector(`tr[data-tin="${tin}"]`);
    if (!row) {
        console.error('Row not found for tin:', tin);
        return;
    }
    const data = {
        org: row.querySelector('[data-label="Organization"]').textContent.trim(),
        natureDeLaDemande: row.querySelector('[data-label="Nature of Demand"]').textContent.trim(),
        codeDemande: row.querySelector('[data-label="Request Code"]').textContent.trim(),
        etatDemande: row.querySelector('[data-label="Status"]').textContent.trim(),
        dateDebutValidite: row.querySelector('[data-label="Start Date"]').textContent.trim(),
        dateFinValidite: row.querySelector('[data-label="End Date"]').textContent.trim(),
        objetDemande: row.querySelector('[data-label="Request Object"]').textContent.trim(),
        observation: row.querySelector('[data-label="Observation"]').textContent.trim(),
        tin: tin,  // Ensure TIN is included in the payload
        nomSociete: row.querySelector('[data-label="Company Name"]').textContent.trim(),
        prenomBusiness: row.querySelector('[data-label="First Name"]').textContent.trim(),
        date: row.querySelector('[data-label="Date"]').textContent.trim(),
        editeur: row.querySelector('[data-label="Editor"]').textContent.trim()
    };

    try {
        const response = await fetch(`http://localhost:8080/controlleurdb/attestationupdate/${tin}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert('Update successful!');
        } else {
            const errorText = await response.text();
            alert(`Failed to update: ${errorText}`);
        }
    } catch (error) {
        alert(`Error: ${error.message}`);
    }
}


async function handleDelete(tin) {
    try {
        const response = await fetch(`http://localhost:8080/controlleurdb/delete/${tin}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            document.querySelector(`tr[data-tin="${tin}"]`).remove();
            alert('Delete successful!');
        } else {
            const errorText = await response.text();
            alert(`Failed to delete: ${errorText}`);
        }
    } catch (error) {
        alert(`Error: ${error.message}`);
    }
}
