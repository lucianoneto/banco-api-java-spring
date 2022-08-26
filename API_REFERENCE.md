
# Documentação da API

## Gerente

### Criar Gerentes

##### Cria um novo manager.

```http
  POST /gerentes
```
#### Requisição

#### Body

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `nome` | `string` | **Obrigatório**. Nome do novo manager. |
| `cpf` | `string` | **Obrigatório**. CPF do novo manager. |
| `email` | `string` | **Obrigatório**. E-mail do novo manager. |
| `telefone` | `string` | **Obrigatório**. Telefone do novo manager. |


#### Respostas

- 201 - Gerente criado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.


### Listar Clientes por Gerentes

##### Lista todos os clientes registrados por um manager específico.

```http
  GET /{gerente_id}/clientes
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `long` | **Obrigatório**. ID do manager. |


#### Respostas

- 200 - Gerente encontrado e clientes listados com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

### Listar todos Clientes

#### Lista todos os clientes registrados no banco.

```http
  GET /gerentes/clientes
```
#### Respostas

- 200 - Clientes listados com sucesso.

### Criar Clientes

##### Cria um novo client.

```http
  POST /gerentes/{gerente_id}/addCliente
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `long` | **Obrigatório**. ID do manager. |

#### Requisição

#### Body

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `nome` | `string` | **Obrigatório**. Nome do novo client. |
| `cpf` | `string` | **Obrigatório**. CPF do novo client. |
| `email` | `string` | **Obrigatório**. E-mail do novo client. |
| `telefone` | `string` | **Obrigatório**. Telefone do novo client. |
| `endereco` | `endereco` | **Obrigatório**. Endereço do novo client (tipo especificado no final da Documentação). |


#### Respostas

- 201 - Cliente criado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

### Inativar Clientes

##### Inativa um client existente.

```http
  PATCH /gerentes/{gerente_id}/inativarCliente/{cliente_id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `long` | **Obrigatório**. ID do manager. |
| `cliente_id`      | `long` | **Obrigatório**. ID do client. |

#### Respostas

- 200 - Cliente inativado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

### Ativar Clientes

##### Ativa um client existente inativado.

```http
  PATCH /gerentes/{gerente_id}/ativarCliente/{cliente_id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `long` | **Obrigatório**. ID do manager. |
| `cliente_id`      | `long` | **Obrigatório**. ID do client. |

#### Respostas

- 200 - Cliente ativado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

### Inativar Gerentes

##### Inativa um manager existente, onde os clientes do mesmo são transferidos para outro manager ativo existente.

```http
  PATCH /gerentes/{gerente_id}/inativar/{novoGerente_id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `long` | **Obrigatório**. ID do atual manager. |
| `novoGerente_id`      | `long` | **Obrigatório**. ID do novo manager. |

#### Respostas

- 200 - Gerente inativado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

### Ativar Gerentes

##### Ativa um manager existente inativado.

```http
  PATCH /gerentes/{gerente_id}/ativar
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `long` | **Obrigatório**. ID do atual manager. |

#### Respostas

- 200 - Gerente ativado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

## Contas

### Listar Extratos

##### Lista todas as transações efetuadas na account do client.

```http
  GET /contas/{conta_id}/extrato
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `conta_id`      | `long` | **Obrigatório**. ID da account. |

#### Respostas

- 200 - Conta encontrada e extrato listado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

## Realizar Depósitos

##### Realiza Depósitos em uma account.

```http
  POST /contas/{conta_id}/depositos
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `conta_id`      | `long` | **Obrigatório**. ID da account. |

#### Requisição

#### Body

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `float` | **Obrigatório**. Valor do depósito. |

#### Respostas

- 200 - Depósito realizado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

## Realizar Saques

##### Realiza saques em uma account.

```http
  POST /contas/{conta_id}/saques
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `conta_id`      | `long` | **Obrigatório**. ID da account. |

#### Requisição

#### Body

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `float` | **Obrigatório**. Valor do saque. |

#### Respostas

- 200 - Saque realizado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

## Realizar Transferências

##### Realiza transferências de uma account para outra.

```http
  POST /contas/{contaOrigem_id}/transferencias/{contaDestino_id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `contaOrigem_id`      | `long` | **Obrigatório**. ID da account de origem. |
| `contaDestino_id`      | `long` | **Obrigatório**. ID da account de destino. |

#### Requisição

#### Body

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `float` | **Obrigatório**. Valor da transferência. |

#### Respostas

- 200 - Transferência realizada com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

--------------------
## Tipos de Dados

#### Endereço

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cep` | `string` | **Obrigatório**. CEP do novo client. |
| `logradouro` | `string` | **Obrigatório**. Logradouro do novo client. |
| `numero` | `string` | **Obrigatório**. Numero do endereço do novo client. |
| `setor` | `string` | **Obrigatório**. Setor do endereço do novo client. |

-------------------
## Respostas Padrão

#### Status 400

Algum campo foi enviado de forma incorreta (faltando, em branco ou `null`).

#### Body 

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `status` | `string` | Status da requisição. |
| `dataHora` | `string` | Data e hora do erro. |
| `titulo` | `string` | Mensagem generalizada de erros. |
| `campo` | `string` | Mensagem específica de erros, diz o campo e qual o erro. |




