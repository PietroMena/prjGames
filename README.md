Gerenciador de Jogos

Sistema desktop desenvolvido em Java Swing para organização e gerenciamento de coleções de jogos.

## Funcionalidades

- **Cadastro e Edição (CRUD):** Inclusão, busca, atualização e exclusão de jogos no banco de dados.
- **Status de Progresso:** Organização dos títulos entre "Quero Jogar", "Jogando" e "Zerado".
- **Ficha do Jogo:** Registro de gênero, plataforma, ano de lançamento e modo multiplayer.
- **Upload de Capas:** Anexo de imagens do computador com ajuste de tamanho automático para o formulário.
- **Visualização:** Tabela integrada para exibição de todos os itens cadastrados.

## Requisitos e Tecnologias

- **Linguagem:** Java (Swing)
- **IDE:** Apache NetBeans
- **Banco de Dados:** MySQL (XAMPP)
- **Driver JDBC:** MySQL Connector/J

## Passo a Passo para Execução

1. Abra o painel do XAMPP e ative o **MySQL**.
2. No phpMyAdmin (`http://localhost/phpmyadmin`), crie a base de dados importando o arquivo `prjGames.sql`.
3. Abra a pasta do projeto no NetBeans.
4. Verifique as dependências das bibliotecas (driver MySQL) e execute o projeto.
