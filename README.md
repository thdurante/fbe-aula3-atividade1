# fbe-aula3-atividade1
Repositório do curso de Fundamentos de Programação Back-end.

## Alunos
O Projeto será executado pelos seguintes alunos:
1. Thiago Durante Pires ([@thdurante](https://github.com/thdurante))
2. Gabriel Louzada Rascovit ([@grascovit](https://github.com/grascovit))
3. Paulo de Oliveira Neto ([@pauloXtr3m](https://github.com/pauloXtr3m))

## Backup
Primeiro crie o banco de dados pelo terminal:
```
sudo -u postgres psql
[após o login]                      
CREATE DATABASE funcionarios;
\q
```
Então execute a seguinte instrução na pasta raíz do projeto:
```
psql funcionarios -f database/funcionarios.sql -U postgres
```

## Run
Para rodar o projeto, siga os passos adiante:
```
Na pasta raiz do projeto:
$ java -jar fbe-aula3-atividade1-1.0-SNAPSHOT.jar
```
