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

## Setup
Altere as configurações relativas à conexão com o BD na classe `ConnectionManager` como no exemplo a seguir:
```
public class ConnectionManager {

    public Connection getConnection() {
        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/funcionarios";
            String usr = "<usuario>";
            String pswrd = "<senha>";
            return DriverManager.getConnection(url, usr, pswrd);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
```

## Deploy
1. Faça download do [Gradle](https://gradle.org)
2. Gere o arquivo `.war`:
    ```
    Na pasta raiz do projeto:
    $ gradle clean
    $ gradle war
    ```
3. Copie o arquivo `build/libs/fbe-aula3-atividade1-1.0-SNAPSHOT.war` gerado no passo anterior para `[glassfish-installation-directory]/domains/domain1/autodeploy`.
4. Faça o [deploy](https://dzone.com/articles/how-deploy-war-file-using) do arquivo `.war` com o glassfish:
    ```
    No diretório de instalação do glassfish:
    $ bin/asadmin
    $ start-domain domain1
    ```
5. Para verificar se está funcionando, abra no navegador: `http://localhost:8080/fbe-aula3-atividade1-1.0-SNAPSHOT/`
