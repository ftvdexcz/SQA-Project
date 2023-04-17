'''
{
    "address": "Truong Chinh, Dong Da, Ha Noi",
    "user": {
        "username": "namdh",
        "password": "123456",
        "firstname": "nam",
        "lastname": "dinh",
        "phone": "0362957747",
        "email": "namdh.ptit@gmail.com"
    }
}
'''

import json
import random
import string

number_street = random.randint(1, 1000)

address = ['Trường Chinh', 'Giải Phóng', 'Võ Chí Công', 'Khương Trung', 'Khương Thượng', 'Láng Hạ', 'Thái Hà', 'Chùa Bộc', 'Xã Đàn', 'Lê Duẩn', 'Lò Đúc', 'Hoàng Mai', 'Đại La', 'Tôn Thất Tùng', 'Bạch Mai']

lastname = ['Nguyễn', 'Đặng', 'Trần', 'Phạm', 'Đỗ', 'Vũ', 'Lê', 'Trần', 'Bùi']
firstname = ['Phương Thảo', 'Hoài Nam', 'Tuấn Dũng', 'Phương Linh', 'Thùy Dương', 'Khánh Vân', 
             'Tuấn Hải', 'Đức Anh', 'Anh Tú', 'Mạnh Dương', 'Lan Anh', 'Thùy Linh', 'Công Hải', 'Văn Tuấn', 'Tùng Dương', 'Phú Quý']

for l in lastname:
    for f in firstname:
        tmp = f.split(' ')
        username = l[0] + tmp[0][0] + tmp[1][0] +  str(random.randint(1000, 9999)) # dql1234
        password = ''.join(random.choices(string.ascii_letters + string.digits, k=8)) # 8 ki tu random
        phone  = '09' + ''.join([str(random.randint(0, 9)) for _ in range(8)])
        email = f'{username}.example@gmail.com'
        _address = str(random.randint(1, 1000)) + ' ' + address[random.randint(0, len(address) -1 )] + ', Hà Nội'

        res = {
        "address": _address,
        "user": {
            "username": username,
            "password": password,
            "firstname": f,
            "lastname": l,
            "phone": phone,
            "email": email
        }
        }

        jsonRes = json.dumps(res)
        print(res)
        print(",")
        

        
