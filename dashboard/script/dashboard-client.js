'use stricts';

const token = localStorage.getItem('token');

const iconPlus = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square-fill" viewBox="0 0 16 16">
<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm6.5 4.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3a.5.5 0 0 1 1 0z"/>
</svg>`;

const tbody = document.querySelector('tbody');
const dataContainer = $('#data-container');
const pagination = $('#pagination-container');

document.querySelector('#createBill').addEventListener('click', () => {
  const clientId = document.getElementById('clientId').value;
  const monthYear = document.getElementById('monthYear').value;
  const meterConsum = document.getElementById('meterConsum').value;

  if (!monthYear) {
    document.querySelector('#monthYear-error').style.display = 'block';
    return;
  } else {
    document.querySelector('#monthYear-error').style.display = 'none';
  }

  if (!meterConsum) {
    document.querySelector('#meter-error').style.display = 'block';
    return;
  } else {
    document.querySelector('#meter-error').style.display = 'none';
  }

  let tmp = monthYear.split('-');

  const data = {
    client_id: clientId,

    month: tmp[1],
    year: tmp[0],
    meter_consum: meterConsum,
  };

  fetch('http://127.0.0.1:8080/api/v1/bills', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      console.log(data);
      if (data.statusCode === 400) throw new Error('error');
      document.querySelector('#close').click();
      alert('Thêm thành công!');
    })
    .catch((error) => {
      alert(error);
    });
});

const cb = (item) => {
  console.log(item);

  document.querySelector('#clientId').value = item;
};

const template = (data) => {
  var html = '';
  $.each(data, function (index, item) {
    html += `
      <tr>
      <td>${index + 1}</td>
      <td>${item.client_id}</td>
      <td>${item.lastname + ' ' + item.firstname}</td>
      <td>${item.phone}</td>
      <td>${item.email}</td>
      <td>${item.address}</td>
      <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" style="padding: 4px 6px !important" onclick=${'"'} cb('${
      item.client_id
    }') ${'"'}>
        ${iconPlus}
        </button></td>
      </tr>
      `;
  });
  return html;
};

const fetchClients = async (page = 1, size = 10) => {
  const token = localStorage.getItem('token');

  if (token) {
    //verify token
    try {
      const response = await fetch(
        'http://127.0.0.1:8080/api/v1/verify_token',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.status === 403)
        window.location.href = 'http://127.0.0.1:5500/dashboard/forbidden.html';

      const jsonObj = await response.json();

      console.log('login');

      console.log(jsonObj);
      const role = jsonObj.data.role.name;
      console.log(role);

      document.querySelector('#adminInfo').textContent = `Xin chào, ${
        jsonObj.data.lastname + ' ' + jsonObj.data.firstname
      }`;
    } catch (err) {
      window.location.href = 'http://127.0.0.1:5500/dashboard/error.html';
      console.log(err);
    }
    pagination.pagination({
      dataSource: `http://localhost:8080/api/v1/clients`,
      alias: {
        pageNumber: 'page',
        pageSize: 'size',
      },
      locator: 'data.content',
      totalNumberLocator: function (response) {
        return response.data.totalElements;
      },

      // pageNumber k hoat dong voi totalNumberLocator (luon bang 1)
      // pageNumber: page,
      pageSize: size,

      ajax: {
        beforeSend: function (xhr) {
          xhr.setRequestHeader('Authorization', `Bearer ${token}`);
          $('#loading').html(`<h4 style="text-align:center">...</h4>`);
        },
        complete: function (jqXHR, textStatus) {
          if (
            jqXHR.status === 200 ||
            jqXHR.readyState == 0 ||
            jqXHR.status == 0
          ) {
            return false; // do nothing
          } else if (
            (jqXHR && jqXHR.status === 403) ||
            (jqXHR && jqXHR.status === 401)
          ) {
            window.location.href =
              'http://127.0.0.1:5500/dashboard/forbidden.html';
          } else {
            window.location.href = 'http://127.0.0.1:5500/dashboard/error.html';
          }
        },
      },
      callback: function (data, pagination) {
        var html = template(data);
        dataContainer.html(html);
        $('#loading').html('');
      },
      className: 'paginationjs-theme-blue paginationjs-big',
    });
  } else {
    window.location.href = 'http://127.0.0.1:5500/dashboard/forbidden.html';
  }
};

fetchClients();
