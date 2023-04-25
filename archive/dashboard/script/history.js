'use stricts';

const tbody = document.querySelector('tbody');
const dataContainer = $('#data-container');
const pagination_container = $('#pagination-container');

const iconPlus = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square-fill" viewBox="0 0 16 16">
<path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm6.5 4.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3a.5.5 0 0 1 1 0z"/>
</svg>`;

const template = (data) => {
  let html = '';
  $.each(data, function (index, item) {
    html += `
    <tr>
    <td>${index + 1}</td>
    <td>${item.month}</td>
    <td>${item.year}</td>
    <td>${item.meter_consum}</td>
    <td>${item.amount}</td>
    <td>${item.tax}</td>
    <td>${item.environment}</td>
    <td>${item.total_amount}</td>
    // fake 
    <td>${item.payment_date || new Date().toISOString().slice(0, 10)}</td>
    </tr>
      `;
  });
  return html;
};

const fetchBills = async (size = 10) => {
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

      if (role === 'ROLE_CLIENT') {
        const username = jsonObj.data.username;

        const response2 = await fetch(
          `http://127.0.0.1:8080/api/v1/clients/${username}`
        );

        if (response.status === 404) throw new Error();
        const jsonObj2 = await response2.json();

        const clientId = jsonObj2.id;
        console.log(jsonObj2);

        document.querySelector('#clientInfo').textContent = `${
          jsonObj2.user.lastname + ' ' + jsonObj2.user.firstname
        } (${clientId})`;

        pagination_container.pagination({
          dataSource: `http://localhost:8080/api/v1/bills/${clientId}?status=1`,
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
                console.log('ok');
                return false; // do nothing
              } else if (
                (jqXHR && jqXHR.status === 403) ||
                (jqXHR && jqXHR.status === 401)
              ) {
                console.log('vkl');
                window.location.href =
                  'http://127.0.0.1:5500/dashboard/forbidden.html';
              } else {
                window.location.href =
                  'http://127.0.0.1:5500/dashboard/error.html';
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
    } catch (err) {
      window.location.href = 'http://127.0.0.1:5500/dashboard/error.html';
      console.log(err);
    }
  } else {
    window.location.href = 'http://127.0.0.1:5500/dashboard/forbidden.html';
  }
};

fetchBills();
