# Programação 3 (Segunda Metade)
## Projeto Prog 3 N2

### Tecnologias utilizadas


| Java (JDK 11)      | Maven | JavaFX(Scene Builder) | PostgresSQL | DBeaver
| :-----------: | :-----------: | :-----------: | :-----------: |  :-----------:
| <a href="https://jdk.java.net/11/" target="_blank"><img src="https://img.icons8.com/color/48/000000/java-coffee-cup-logo--v1.png" target="_blank"/> </a>   | <a href="https://maven.apache.org/download.cgi" target="_blank"> <img src="https://img.icons8.com/ios/50/e74c3c/maven-ios.png" width="48px"/> </a>      | <a href="https://www.oracle.com/java/technologies/install-javafx-sdk.html" target="_blank"> <img src="https://img.icons8.com/nolan/64/scene-builder.png" width="48px"/> </a> | <a href="https://www.postgresql.org/download/" target="_blank"> <img src="https://img.icons8.com/color/48/000000/postgreesql.png"/></a> | <a href="https://dbeaver.io/download/" target="_blank"> <img src="https://img.icons8.com/dusk/64/000000/dbeaver.png" width="48"/> </a>


## Projeto definido para o dia 6/12 as 18:00
---

## Enunciado
A Biblioteca de uma faculdade deseja informatizar o controle de empréstimos de seus livros. Para
cada livro cadastrado a biblioteca pode possuir um ou mais exemplares para empréstimo. Tanto os
livros quanto seus exemplares devem estar cadastrados no sistema para que possam ser emprestados
e sobre os livros são guardados os seguintes dados: Código, Título, Autor e Ano. Para os exemplares
apenas são guardados o código correspondente e a data de aquisição. O responsável pelo
cadastramento dos livros e dos exemplares é o bibliotecário e somente pode ser cadastrado
exemplares para livros já cadastrados.
O atendente da Biblioteca somente empresta exemplares de livros a leitores previamente cadastrados
para os quais são armazenados os seguintes dados (Código, Nome, Endereço, Telefone). Só existem
dois tipos de leitores, os professores e os alunos. Dos professores, é armazenada a disciplina que
leciona, enquanto dos alunos é armazenada seu número de matrícula. Quem cadastra os leitores é o
atendente.
Cada livro pode possuir zero ou mais autores, e cada autor pode ter escrito um ou mais livros. O
bibliotecário insere no sistema o código, nome e nacionalidade dos autores.
Atenção que somente podem ser cadastrados livros se os autores já estiverem no sistema.
Os livros devem ser devolvidos pelos leitores ao atendente em no máximo quinze dias a partir da data
de empréstimo se forem alunos e trinta dias se forem professores. Portanto a data de devolução não
pode ultrapassar estes prazos e, para controlar os atrasos, diariamente o bibliotecário tira uma listagem
dos leitores em atraso.
<br>
<br>
<br>
<div style="text-align:center; font-size:16px; border-bottom:3px solid">
Os requisitos do projeto estão listados abaixo
</div>
<br>
<br>

Classes mapeadas para o Java | Interface gráfica usando JavaFX | Persistência usando JPA e banco de dados PostgreSQL 
:------------: | :-------------: | :-------------: |
![50%]( https://progress-bar.dev/85/?scale=100&width=145&color=babaca) | ![50%](https://progress-bar.dev/75/?scale=100&width=150&color=babaca) |  ![50%](https://progress-bar.dev/85/?scale=100&width=140&color=babaca) |

<br>
<br>

Nota obtida | Nota maxima
:-----: | :------:
N/A | 10
