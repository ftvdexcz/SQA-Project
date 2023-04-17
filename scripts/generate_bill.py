'''
{
    "month": 2,
    "year": 2023,
    "meter_consum": 9,
    "client_id": "KH1681564208"
}
'''

import random
import pandas as pd 

df = pd.read_csv('abc.csv')

client_ids = df['id'].to_list()

for id in client_ids:
  month = random.randint(1, 12)
  year = random.randint(2018, 2023)
  meter_consum = random.randint(5, 30)
  res = {
    "month": month,
    "year": year,
    "meter_consum": meter_consum,
    "client_id": id
  }
  print(res)
  print(",")