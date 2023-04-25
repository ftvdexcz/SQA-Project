'use stricts';

//HAmFxiVJ
//VHN4001

// const userData = JSON.parse(localStorage.getItem('user'));
const token = localStorage.getItem('token');

if (token) {
  //verify token
  fetch('http://127.0.0.1:8080/api/v1/verify_token', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => {
      if (response.status === 403) throw new Error('');

      return response.json();
    })
    .then((jsonObj) => {
      console.log('login');

      console.log(jsonObj);
      const role = jsonObj.data.role.name;
      if (role === 'ROLE_ADMIN') {
        window.location.href =
          'http://127.0.0.1:5500/dashboard/dashboard-bill.html';
      } else if (role === 'ROLE_CLIENT') {
        window.location.href = 'http://127.0.0.1:5500/dashboard/client.html';
      }
    })
    .catch((err) => {});
}

const signinBtn = document.querySelector('#signin-btn');
const usernameInput = document.querySelector('#username');
const passwordInput = document.querySelector('#password');
const loginMessage = document.querySelector('#login-message');
const log_err = document.querySelector('#log-err');

signinBtn.addEventListener('click', async (e) => {
  try {
    e.preventDefault();
    loginMessage.style.display = 'none';

    username = usernameInput.value.trim();
    password = passwordInput.value;

    if (username == '') throw new Error('Tên tài khoản không được để trống');

    if (password == '') throw new Error('Mật khẩu không được để trống');

    const response = await fetch('http://127.0.0.1:8080/api/v1/signin', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username,
        password,
      }),
    });

    const jsonObj = await response.json();

    console.log(jsonObj);

    if (response.status === 400) {
      throw new Error('Tài khoản hoặc mật khẩu không hợp lệ');
    }

    if (response.status === 200) {
      const data = jsonObj.data;
      localStorage.setItem('token', data.token);

      const role = data.role;

      if (role === 'ROLE_ADMIN') {
        window.location.href =
          'http://127.0.0.1:5500/dashboard/dashboard-bill.html';
      } else if (role === 'ROLE_CLIENT') {
        window.location.href = 'http://127.0.0.1:5500/dashboard/client.html';
      }
    }
  } catch (err) {
    loginMessage.style.display = 'block';
    log_err.textContent = err.message;
  }
});
