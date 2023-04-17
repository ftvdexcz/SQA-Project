'use stricts';

const token =
  'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY4MTc0NDM3NiwiZXhwIjoxNjgyMzQ5MTc2fQ.vlgZA9ciX20aJ2mfN7Tqq82b6TLI-IRvwCmsTfXV0kY';

const tbody = document.querySelector('tbody');
const dataContainer = $('#data-container');

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
    <td>${item.amount}</td>
    <td>${item.tax}</td>
    <td>${item.environment}</td>
    <td>${item.total_amount}</td>
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
  $('#pagination-container').pagination({
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
        } else if (jqXHR && jqXHR.status === 403) {
          window.location.href =
            window.location.href.split('/').slice(0, 3).join('/') + '/login';
        } else {
          alert('error');
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
};

fetchBills();
