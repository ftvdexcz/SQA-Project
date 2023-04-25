'use stricts';

document.querySelector('#logout-btn').addEventListener('click', async (e) => {
  e.preventDefault();
  console.log('test event');
  try {
    const response = await fetch('http://127.0.0.1:8080/api/v1/logout', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
    });

    console.log(response);

    if (response.status !== 200) throw new Error('');

    const resData = await response.json();

    console.log(resData);

    localStorage.setItem('token', resData); // set new token

    await new Promise((resolve) => {
      setTimeout(resolve, 2000);
    });

    window.location.href = 'http://127.0.0.1:5500/dashboard/signin.html';
  } catch (err) {
    console.log(err);
    window.location.href = 'http://127.0.0.1:5500/dashboard/error.html';
  }
});
