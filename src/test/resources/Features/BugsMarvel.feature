#language: pt
#Author: Vinicius Franca
#Version: 1.0
#Encoding: UTF-8


@marvelBug
Funcionalidade: Validar erros
  Eu, como analista de qualidade
  Quero garantir a qualidade da API Marvel
  Capturando as mensagens de erro

  @personagemDuplicado
  Cenario: Personagem duplicado
    Dado que possuo um token de acesso válido
    Quando tento cadastrar um personagem que já existe
    Então devo visualizar a mensagem de erro "Duplicate character"
    E o status code da requisição deve ser 400

  @semNome
  Cenario: Personagem sem nome
    Dado que possuo um token de acesso válido
    Quando tento cadastrar um personagem sem preencher o campo nome
    Então devo visualizar uma mensagem de erro "\"name\" is not allowed to be empty"
    E o status code da requisição deve ser 400

  @semAlias
  Cenario: Personagem sem alias
    Dado que possuo um token de acesso válido
    Quando tento cadastrar um personagem sem preencher o campo alias
    Então devo visualizar uma mensagem de erro "\"alias\" is not allowed to be empty"
    E o status code da requisição deve ser 400

  @semTeam
  Cenario: Personagem sem team
    Dado que possuo um token de acesso válido
    Quando tento cadastrar um personagem sem preencher o campo team
    Então devo visualizar uma mensagem de erro "\"team\" must be an array"
    E o status code da requisição deve ser 400

  @semActive
  Cenario: Personagem sem active
    Dado que possuo um token de acesso válido
    Quando tento cadastrar um personagem sem preencher o campo active
    Então devo visualizar uma mensagem de erro "\"active\" must be a boolean"
    E o status code da requisição deve ser 400







