'use stricts';

const token =
  'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4MTY2MzEyNiwiZXhwIjoxNjgyMjY3OTI2fQ.qRjSTxitWEtKPM5RVaVIWHNuQjOlfJTXP5xBupWmnkM';

const iconPlus = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square-fill" viewBox="0 0 16 16">
<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm6.5 4.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3a.5.5 0 0 1 1 0z"/>
</svg>`;

const tbody = document.querySelector('tbody');

const fetchClients = async (page = 0, size = 10) => {
  const response = await fetch(
    `http://localhost:8080/api/v1/clients?page=${page}&size=${size}`,
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );

  const clientData = await response.json();

  console.log(clientData);

  const data = clientData.data;

  const content = data.content;
  const totalElements = data.totalElements;
  const totalPages = data.totalPages;

  console.log(content, totalElements, totalPages);

  content.forEach((val, idx) => {
    const clientHtml = `
    <tr>
    <td>${idx + 1}</td>
    <td>${val.client_id}</td>
    <td>${val.lastname + ' ' + val.firstname}</td>
    <td>${val.phone}</td>
    <td>${val.email}</td>
    <td>${val.address}</td>
    <td>${iconPlus}</td>
    </tr>
    `;

    tbody.insertAdjacentHTML('beforeend', clientHtml);
  });
};

fetchClients();
