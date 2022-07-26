#language: pt
#Author: Vinicius França
#Version: 1.0
#Encoding: UTF-8

@Gorest @regressivo
Funcionalidade: Criar e editar contas de usuarios
  Eu como administrador do sistema
  Quero cadastrar e editar e excluir usuários do sistema

  @post
  Cenario: Cadastrar novo usuário API Gorest
    Dado que possuo token Gorest valido
    Quando envio um request de cadastro de usuario com dados validos
    Entao o usuario deve ser criado corretamente
    E o status code do request deve ser 201

  @get
  Cenario: Buscar um usuário existente na API Gorest
    Dado que possuo token Gorest valido
    E existe um usuario cadastrado na API
    Quando buscar esse usuário
    Então os dados do usuario devem ser retornados
    E o status code do request deve ser 200

  @put
  Cenario: Alterar um usuário existente na API Gorest PUT
    Dado que possuo token Gorest valido
    E existe um usuario cadastrado na API
    Quando altero os dados do usuario
    Então o usuario deve ser alterado com sucesso
    E o status code do request deve ser 200

  @patch
  Cenario: Alterar um usuário existente na API Gorest PATCH
    Dado que possuo token Gorest valido
    E existe um usuario cadastrado na API
    Quando altero um ou mais dados do usuario
    Então o usuario deve ser alterado com sucesso
    E o status code do request deve ser 200

  @delete
  Cenario: Deletar um usuário existente na API Gorest PATCH
    Dado que possuo token Gorest valido
    E existe um usuario cadastrado na API
    Quando deleto um ou dados do usuario
    Então o usuario deve ser deletado com sucesso
    E o status code do request deve ser 404
