import random
import csv


class usuario():

    def __init__(self, nome, idade, senha, email, dataRegistro):
        self.nome = nome
        self.idade = idade
        self.senha = senha
        self.email = email
        self.dataRegistro = dataRegistro

    def row(self):
        return [self.nome, self.senha, self.idade, self.email,
                self.dataRegistro]


prenomes = []
with open('nomes.txt') as file:
    prenomes = [line.rstrip() for line in file]

sobrenomes = []
with open('sobrenomes.txt') as file:
    sobrenomes = [line.rstrip() for line in file]

nomes = []
for i in range(200):
    prenome = prenomes[random.randint(0, len(prenomes)-1)]
    sobrenome = sobrenomes[random.randint(0, len(sobrenomes)-1)]

    nomes.append(prenome + sobrenome + str(random.randint(0, 999)))


emails = [
        "hotmail.com",
        "yahoo.com.br",
        "gmail.com",
        "outlook.com",
        "uol.com.br",
        "protonmail.br",
        ]


usuarios = []
for nome in nomes:

    idade = random.randint(10, 45)
    senha = random.randint(10000000, 99999999)
    email = nome + '@' + emails[random.randint(0, len(emails)-1)]

    # algum dia entre primeiro de janeiro de 2017
    # e hoje, 18 de novembro de 2023
    dataRegistro = 1483236000 + random.randint(0, 217099345)

    usuarios.append(usuario(nome, idade, senha, email, dataRegistro))

with open('usuarios.csv', 'w', newline='') as csvfile:
    spamwriter = csv.writer(csvfile, delimiter=',',
                            quotechar='|', quoting=csv.QUOTE_MINIMAL)
    rows = ['nome', 'idade', 'senha', 'email', 'dataRegistro']
    spamwriter.writerow(rows)
    for i in usuarios:
        spamwriter.writerow(i.row())

print('usuario.csv')
