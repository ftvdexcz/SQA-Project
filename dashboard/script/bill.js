'use stricts';

const token = localStorage.getItem('token');

const tbody = document.querySelector('tbody');
const dataContainer = $('#data-container');

const pagination_container = $('#pagination-container');

const template = (data) => {
  let html = '';
  $.each(data, function (index, item) {
    let status = item.status;

    html += `
    <tr>
    <td>${index + 1}</td>
    <td>${item.client_id}</td>
    <td>${item.meter_consum}</td>
    <td>${item.month}</td>
    <td>${item.year}</td>
    <td>${item.amount.toFixed(3)}</td>
    <td>${item.tax.toFixed(3)}</td>
    <td>${item.environment.toFixed(3)}</td>
    <td>${item.total_amount.toFixed(3)}</td>
    <td>${item.payment_date ? item.payment_date : ''}</td>
    <td class=${'"'}${status ? 'isPay' : 'notPay'}${'"'}>
      <span>
        ${status ? 'Đã TT' : 'Chưa TT'}
      </span>
    </td>
    </tr>
      `;
  });
  return html;
};

const fetchBills = async (page = 1, size = 10) => {
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

      document.querySelector('#adminInfo').textContent = `Administrator, ${
        jsonObj.data.lastname + ' ' + jsonObj.data.firstname
      }`;
    } catch (err) {
      window.location.href = 'http://127.0.0.1:5500/dashboard/error.html';
      console.log(err);
    }

    pagination_container.pagination({
      dataSource: `http://localhost:8080/api/v1/bills`,
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

fetchBills();
