import csv
import sys
import requests
from bs4 import BeautifulSoup

headers = {
        'authority': 'm.imbd.com',
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

    def __init__(self, titulo, ano, duracao, idade, nota):
        self.titulo = titulo
        self.ano = ano
        self.duracao = duracao
        self.idade = idade
        self.nota = nota

    def row(self):
        return [self.titulo, self.ano, self.duracao, self.idade,
                self.nota]


req = requests.get('https://m.imdb.com/chart/top/?ref_=nv_mv_250')
if req.status_code != 200:
    print(req.status_code)
    sys.exit(1)

soup = BeautifulSoup(req.content, 'html.parser')

query = '#__next > main > div > div.ipc-pidade-content-container.\
ipc-pidade-content-container--center > section > div > div.ipc-pidade-grid.\
ipc-pidade-grid--bias-left > div > ul > li'

top = soup.select(query)

sel_titulo = 'div.ipc-metadata-list-summary-item__c > div > div > \
div.ipc-titulo.ipc-titulo--base.ipc-titulo--titulo.ipc-titulo-\
link-no-icon.ipc-titulo--on-textPrimary.sc-479faa3c-9.dkLVoC.cli-titulo \
> a > h3'
sel_ano = 'div.ipc-metadata-list-summary-item__c > div > div > \
div.sc-479faa3c-7.jXgjdT.cli-titulo-metadata > span:nth-child(1)'
sel_duracao = 'div.ipc-metadata-list-summary-item__c > div > div > \
div.sc-479faa3c-7.jXgjdT.cli-titulo-metadata > span:nth-child(2)'
sel_idade = 'div.ipc-metadata-list-summary-item__c > div > div > \
div.sc-479faa3c-7.jXgjdT.cli-titulo-metadata > span:nth-child(3)'
sel_nota = 'div.ipc-metadata-list-summary-item__c > div > div > span > \
div > span'

filmes = []
for i in top:
    titulo = i.select(sel_titulo)[0].get_text()
    ano = i.select(sel_ano)[0].get_text()
    duracao = i.select(sel_duracao)[0].get_text()
    idade = i.select(sel_idade)[0].get_text()
    nota = i.select(sel_nota)[0].get_text()

    # '2h 9m'
    # '2h 9'
    # ['2' '9']
    # 2*60 + 9
    # 129 minutos
    duracao = duracao[:-1].split['h ']
    duracao = int(duracao[0]) * 60 + int(duracao[1])

    idade = 0 if idade == 'Livre' else idade

    filmes.append(filme(titulo, ano, duracao, idade, nota))


with open('filmes.csv', 'w', newline='') as csvfile:
    spamwriter = csv.writer(csvfile, delimiter='|',
                            quotechar='\\', quoting=csv.QUOTE_MINIMAL)
    spamwriter.writerow(['titulo', 'ano', 'duracao', 'idade', 'nota'])
    for i in filmes:
        spamwriter.writerow(i.row())

print('filmes.csv')
