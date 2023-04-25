'use stricts';

const verify = async () => {
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

      const log = document.querySelector('#log');

      document.querySelector('#back-btn').addEventListener('click', (e) => {
        window.location.href =
          'http://127.0.0.1:5500/dashboard/dashboard-client.html';
      });

      document
        .querySelector('#signup-btn')
        .addEventListener('click', async (e) => {
          e.preventDefault();

          try {
            log.style.display = 'none';
            const lastname = document.querySelector('#lastname').value.trim();
            const firstname = document.querySelector('#firstname').value.trim();
            const email = document.querySelector('#email').value.trim();

            const phone = document.querySelector('#phone').value.trim();
            const address = document.querySelector('#address').value.trim();
            const username = document.querySelector('#username').value.trim();
            const password = document.querySelector('#inputPassword').value;

            if (lastname === '') throw new Error('Vui lòng điền họ');
            if (firstname === '') throw new Error('Vui lòng điền tên');

            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(email)) throw new Error('Email không hợp lệ');

            const phoneRegex = /^\d{10}$/;
            if (!phoneRegex.test(phone))
              throw new Error('Số điện thoại không hợp lệ');

            if (address === '') throw new Error('Vui lòng nhập địa chỉ');
            if (username === '') throw new Error('Vui lòng nhập tên tài khoản');
            if (password === '') throw new Error('Vui lòng nhập mật khẩu');

            // console.log(lastname, firstname, email, phone, address, username, password);

            const data = {
              address,
              user: {
                username,
                password,
                firstname,
                lastname,
                phone,
                email,
              },
            };

            const response = await fetch(
              'http://127.0.0.1:8080/api/v1/clients/signup',
              {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json',
                  Authorization: `Bearer ${localStorage.getItem('token')}`,
                },
                body: JSON.stringify(data),
              }
            );

            console.log(response);

            if (response.status !== 201)
              throw new Error('Tạo tài khoản thất bại');

            const resData = await response.json();
            alert('Tạo tài khoản thành công! Về trang chủ');

            window.location.href =
              'http://127.0.0.1:5500/dashboard/dashboard-client.html';
          } catch (err) {
            log.textContent = err.message;
            log.style.display = 'block';
          }
        });
    } catch (err) {
      window.location.href = 'http://127.0.0.1:5500/dashboard/error.html';
    }
  } else {
    window.location.href = 'http://127.0.0.1:5500/dashboard/forbidden.html';
  }
};

verify();
