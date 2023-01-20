# **Sobre o Projeto**
O projeto consiste em uma continuação do meu projeto anterior do jogo da velha, adicionando algumas funcionalidades que aprendi utilizando a Java Persistence API como forma de fixar os conceitos de gerenciamento de dados relacionais em aplicações Java.

# **Descrição do projeto**
A nova funcionalidade dessa versão do projeto consiste em um sistema "ranqueado" do jogo da velha, onde agora é possível escolher o nome do jogador, no qual suas estatísticas (vitórias, derrotas e taxa de vitórias) ficarão salvas em um banco de dados próprio do projeto.  
Ao iniciar a aplicação, o usuário poderá escolher entre jogar uma partida casual (na qual não precisará ter um nome de jogador) ou uma partida ranqueada (na qual jogarão dois usuários de nomes distintos e o resultado da partida será armazenado no final do jogo).

![Opções](https://i.imgur.com/ulz1uDe.png "O 'Consultar Ranque' é explicado mais abaixo")

Ao iniciar a partida ranqueada, ambos os usuários que irão jogar poderão cadastrar o seu nome ou utilizar um nome já existente na base de dados. Ao final da partida, caso não dê empate, as estatísticas de cada jogador serão atualizadas no banco de dados.

![Fim de jogo](https://i.imgur.com/dUbprnl.png "Tela exibida no final de um jogo ranqueado")

As informações das estatísticas dos jogadores locais são armazenadas no banco de dados da aplicação e poderão ser consultadas no início do jogo, ao selecionar a opção **"(3) Consultar Ranque"**, como demonstrado na figura abaixo.

![Ranking](https://i.imgur.com/FENIfNd.png "Ranking local dos jogadores")

# **Status do Projeto**  
🚧🚧 **Em Desenvolvimento** 🚧🚧

No momento, como ainda estou aprendendo a utilizar o JPA, foram desenvolvidas apenas questões básicas para o projeto através da classe **'RepositorioJogador'**, que possui como atributos o *EntityManager* (usado para realizar as operações de persistência de dados) e o *EntityManagerFactory* (responsável por criar instâncias do EntityManager).  
Os métodos que a classe **'RepositorioJogador'** possui são todas relacionadas com as operações de dados da classe **Jogador**, como o CRUD (As quatro operações básicas de Banco de Dados: **Create, Read, Update Delete**) e operações para criar consultas no Banco de Dados para ações específicas (como mostrar o ranking, por exemplo).


# **Tecnologias utilizadas**

* **Eclipse IDE**;  
* **Maven:** É uma ferramenta de gerenciamento e construção baseado em um modelo de projeto para Java e que permite gerar as dependências, plugins e configurações de construção da comunicação entre a aplicação e o banco de dados;  
* **JPA (Java Persistence API):** É a especificação utilizada no projeto para o acesso e gerenciamento de dados, responsável por fazer a tradução automática entre os objetos da classe *'Jogador'* e a tabela *'jogador'* do Banco de Dados;  
* **Hibernate:** é um framework ORM (Object-Relational Mapping) responsável por traduzir automaticamente entre os objetos Java e tabelas do banco de dados, permitindo que os objetos sejam manipulados como se fossem registros do banco de dados;
* **XAMPP:** é um software livre que possui o MariaDB (uma versão do MySQL), que foi utiliado para criar o banco de dados e a tabela utilizada no projeto.

# **Como utilizar**
## Requisitos:
* Java 18 ou superior;
* Ter o XAMPP com o Apache e o MySQL instalados na máquina (você pode fazer o download através desse [link](https://www.apachefriends.org/pt_br/index.html);  

Na versão atual desse projeto, alguns requisitos são necessários para a aplicação funcionar corretamente. Para rodar o programa, siga os passos abaixo:

```bash
1. Clone o repositório através de um terminal:
git clone https://github.com/GuilhermeVaiano/jogoDaVelhaRanqueado.git

```

2. Ligue o Apache e o MySQL do XAMPP, acesse o *localhost*;
3. Vá em phpMyAdmin, clique em 'SQL', realize o seguinte comando e clique em "Continuar";

```sql
CREATE DATABASE jogadores;
```

4. Após criar o Banco de Dados, clique em 'Importar' e selecione o arquivo SQL que está em \src\main\resources da pasta do projeto;
5. Depois, basta abrir o projeto Maven no Eclipse IDE ou no NetBeans IDE e executar a classe main (Main.java).

**Obs:** Para o sistema funcionar, o nome do usuario e senha do Banco de Dados devem estar de acordo com as credenciais do MySQL do computador. Caso a senha e o usuario nunca foram alterados, nao é necessário alterar, pois deixei a padrao. Caso pelo menos uma das credenciais foi alterada, coloque as informações corretas no arquivo *persistence.xml* presente em main/java/resources/meta-inf/

Como ainda estou aprendendo a utilizar o JPA e o Hibernate, estou procurando um meio de facilitar esse processo, de modo que qualquer usuário possa baixar o projeto e já executá-lo em qualquer lugar, como por exemplo, em um terminal, sem a necessidade de precisar instalar o XAMPP. Qualquer sugestão ou dica será bem-vinda.


# Autor
**Guilherme Vaiano Nogueira Mendonça**  
LinkedIn: https://www.linkedin.com/in/guilherme-mendon%C3%A7a-12a83720b/  
