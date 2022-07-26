#language: pt
#Author: Vinicius Franca
#Version: 1.0
#Encoding: UTF-8


@Marvel
Funcionalidade: Criar, editar e excluir personagens.
  Eu, como administrador do sistema
  Quero incluir, editar e excluir personagens na API Marvel

  @getPersonagem
  Cenario: Listando personagens existentes
    Dado que possuo um token de acesso
    Quando envio um request para listar os personagens existentes
    Então o status code da request deve ser 200

  @postPersonagem
  Cenario: Incluindo novo personagem
    Dado que possuo um token de acesso
    Quando envio um request de cadastro de personagem valido
    Então devo visualizar o personagem criado
    E o status code da request deve ser 200

  @deletePersonagem
  Cenario: Deletando um personagem existente
    Dado que possuo um token de acesso
    E existe um personagem cadastrado na API
    Quando deleto o personagem
    Então o personagem deve ser deletado com sucesso
    E o status code da request deve ser 204


#    @putPersonagem
#    Cenario: Editar um personagem existente na API Marvel
#      Dado que possuo um token de acesso
#      E existe um personagem cadastrado na API
#      Quando altero os dados do personagem
#      Então o personagem deve ser alterado com sucesso
#      E o status code do request deve ser 200