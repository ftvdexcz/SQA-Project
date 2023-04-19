'use stricts';

const tbody = document.querySelector('tbody');
const dataContainer = $('#data-container');
const pagination_container = $('#pagination-container');

const iconPlus = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-paypal" viewBox="0 0 16 16">
<path d="M14.06 3.713c.12-1.071-.093-1.832-.702-2.526C12.628.356 11.312 0 9.626 0H4.734a.7.7 0 0 0-.691.59L2.005 13.509a.42.42 0 0 0 .415.486h2.756l-.202 1.28a.628.628 0 0 0 .62.726H8.14c.429 0 .793-.31.862-.731l.025-.13.48-3.043.03-.164.001-.007a.351.351 0 0 1 .348-.297h.38c1.266 0 2.425-.256 3.345-.91.379-.27.712-.603.993-1.005a4.942 4.942 0 0 0 .88-2.195c.242-1.246.13-2.356-.57-3.154a2.687 2.687 0 0 0-.76-.59l-.094-.061ZM6.543 8.82a.695.695 0 0 1 .321-.079H8.3c2.82 0 5.027-1.144 5.672-4.456l.003-.016c.217.124.4.27.548.438.546.623.679 1.535.45 2.71-.272 1.397-.866 2.307-1.663 2.874-.802.57-1.842.815-3.043.815h-.38a.873.873 0 0 0-.863.734l-.03.164-.48 3.043-.024.13-.001.004a.352.352 0 0 1-.348.296H5.595a.106.106 0 0 1-.105-.123l.208-1.32.845-5.214Z"/>
</svg>`;

const clientId = document.querySelector('#clientId');
const monthYear = document.querySelector('#monthYear');
const meterConsum = document.querySelector('#meterConsum');
const amount = document.querySelector('#amount');
const tax = document.querySelector('#tax');
const environment = document.querySelector('#environmentFee');

document.querySelector('#payment').addEventListener('click', async () => {
  const obj = {
    client_id: clientId.value,
    username: document.querySelector('#username').value,
    month: +document.querySelector('#month').value,
    year: +document.querySelector('#year').value,
    meter_consum: +meterConsum.value,
    amount: +amount.value,
    tax: +tax.value,
    environment: +environment.value,
    total_amount: +document.querySelector('#_totalAmount').value,
  };

  console.log(obj);

  try {
    const response = await fetch('http://127.0.0.1:8080/api/v1/payment/pay', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
      body: JSON.stringify(obj),
    });

    console.log(response);

    if (response.status !== 200) throw new Error('');

    const resData = await response.json();

    window.open(resData.data, '_blank');

    // document.querySelector('#close').click();

    console.log(resData);
  } catch (err) {
    window.location.href = 'http://127.0.0.1:5500/dashboard/error.html';
  }
});

const cb = (data, name) => {
  console.log(data, name);

  const arr = data.split(' ');
  clientId.value = arr[0];
  const username = arr[1];
  document.querySelector('#clientName').value = name;
  document.querySelector('#username').value = username;
  document.querySelector('#month').value = arr[2];
  document.querySelector('#year').value = arr[3];
  monthYear.value = `${arr[3]}-${arr[2] < 10 ? '0' + arr[2] : arr[2]}`;
  meterConsum.value = arr[4];
  amount.value = arr[5];
  tax.value = arr[6];
  environment.value = arr[7];
  const total_amount = arr[8];
  document.querySelector('#_totalAmount').value = total_amount;
  document.querySelector(
    '#totalAmount'
  ).textContent = `Tổng (vnđ): ${total_amount}`;
};

const template = (data, clientId, username, name) => {
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
    <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" style="padding: 4px 6px !important" onclick=${'"'} cb('${
      clientId +
      ' ' +
      username +
      ' ' +
      item.month +
      ' ' +
      item.year +
      ' ' +
      item.meter_consum +
      ' ' +
      item.amount +
      ' ' +
      item.tax +
      ' ' +
      item.environment +
      ' ' +
      item.total_amount
    }', '${name}') ${'"'}>
        ${iconPlus}
        </button></td>
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
          dataSource: `http://localhost:8080/api/v1/bills/${clientId}?status=0`,
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
            var html = template(
              data,
              clientId,
              username,
              jsonObj2.user.lastname + ' ' + jsonObj2.user.firstname
            );
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
