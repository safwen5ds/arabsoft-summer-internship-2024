const form = document.getElementById('uploadForm');
const fileInput = document.getElementById('chooseFile');
const openFilePicker = document.getElementById('openFilePicker');
const loadingButton = document.getElementById('loadingButton');

openFilePicker.addEventListener('click', () => {
  fileInput.click();
});


fileInput.addEventListener('change', () => {
  if (fileInput.files.length > 0) {
    openFilePicker.classList.add('hidden');
    loadingButton.classList.remove('hidden');
    
    const formData = new FormData(form);
    
    fetch('http://localhost:8080/upload', {
      method: 'POST',
      body: formData,
    })
    .then(response => {
      return response.text(); 
    })
    .then(data => {
      loadingButton.classList.add('hidden');
      openFilePicker.classList.remove('hidden');
      alert('Reponse Spring Boot : ' + data);
    })
    .catch(error => {
      loadingButton.classList.add('hidden');
      openFilePicker.classList.remove('hidden');
      alert('failed: ' + error.message);
    });
  }
});

form.addEventListener('dragover', (e) => {
  e.preventDefault();
});

form.addEventListener('drop', (e) => {
  e.preventDefault();
  if (e.dataTransfer.files.length > 0) {
    fileInput.files = e.dataTransfer.files;
    openFilePicker.classList.add('hidden');
    loadingButton.classList.remove('hidden');

    const formData = new FormData(form);

    fetch('http://localhost:8080/upload', {
      method: 'POST',
      body: formData,
    })
    .then(response => {
      return response.text();
    })
    .then(data => {
      loadingButton.classList.add('hidden');
      openFilePicker.classList.remove('hidden');
      alert("Reponse Spring Boot : "+data);
    })
    .catch(error => {
      loadingButton.classList.add('hidden');
      openFilePicker.classList.remove('hidden');
      alert('failed: ' + error.message);
    });
  }
});
