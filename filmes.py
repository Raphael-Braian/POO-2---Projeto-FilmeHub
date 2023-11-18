import csv
import requests
from bs4 import BeautifulSoup

headers = {
        'authority': 'www.olx.com.br',
        'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,\
image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;\
q=0.9',
        'cache-control': 'no-cache',
        'dnt': '1',
        'pragma': 'no-cache',
        'sec-ch-ua': '"Not?A_Brand";v="8", "Chromium";v="108", \
"Google Chrome";v="108"',
        'sec-ch-ua-mobile': '?0',
        'sec-ch-ua-platform': '"Linux"',
        'sec-fetch-dest': 'document',
        'sec-fetch-mode': 'navigate',
        'sec-fetch-site': 'none',
        'sec-fetch-user': '?1',
        'upgrade-insecure-requests': '1',
        'user-agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 \
(KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36', }


class filme():

    def __init__(self, title, year, duration, age, rating):
        self.title = title
        self.year = year
        self.duration = duration
        self.age = age
        self.rating = rating

    def row(self):
        return [self.title, self.year, self.duration, self.age,
                self.rating]


req = requests.get('https://m.imdb.com/chart/top/?ref_=nv_mv_250',
                   headers=headers)
soup = BeautifulSoup(req.content, 'html.parser')

query = '#__next > main > div > div.ipc-page-content-container.\
ipc-page-content-container--center > section > div > div.ipc-page-grid.\
ipc-page-grid--bias-left > div > ul > li'

top = soup.select(query)

sel_title = 'div.ipc-metadata-list-summary-item__c > div > div > div.ipc-title\
.ipc-title--base.ipc-title--title.ipc-title-link-no-icon.ipc-title--on-\
textPrimary.sc-479faa3c-9.dkLVoC.cli-title > a > h3'
sel_year = 'div.ipc-metadata-list-summary-item__c > div > div > \
div.sc-479faa3c-7.jXgjdT.cli-title-metadata > span:nth-child(1)'
sel_duration = 'div.ipc-metadata-list-summary-item__c > div > div > \
div.sc-479faa3c-7.jXgjdT.cli-title-metadata > span:nth-child(2)'
sel_age = 'div.ipc-metadata-list-summary-item__c > div > div > \
div.sc-479faa3c-7.jXgjdT.cli-title-metadata > span:nth-child(3)'
sel_rating = 'div.ipc-metadata-list-summary-item__c > div > div > span > \
div > span'

filmes = []
for i in top:
    title = i.select(sel_title)[0].get_text()
    year = i.select(sel_year)[0].get_text()
    duration = i.select(sel_duration)[0].get_text()
    age = i.select(sel_age)[0].get_text()
    rating = i.select(sel_rating)[0].get_text()

    filmes.append(filme(title, year, duration, age, rating))


with open('filmes.csv', 'w', newline='') as csvfile:
    spamwriter = csv.writer(csvfile, delimiter='|',
                            quotechar='\\', quoting=csv.QUOTE_MINIMAL)
    spamwriter.writerow(['title', 'year', 'duration', 'age', 'rating'])
    for i in filmes:
        spamwriter.writerow(i.row())

print('filmes.csv')
