
# Documentação da API

## Gerente

### Criar Gerentes

##### Cria um novo gerente.

```http
  POST /gerentes
```
#### Requisição

#### Body

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `nome` | `string` | **Obrigatório**. Nome do novo gerente. |
| `cpf` | `string` | **Obrigatório**. CPF do novo gerente. |
| `email` | `string` | **Obrigatório**. E-mail do novo gerente. |
| `telefone` | `string` | **Obrigatório**. Telefone do novo gerente. |


#### Respostas

- 201 - Gerente criado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.


### Listar Clientes por Gerentes

##### Lista todos os clientes registrados por um gerente específico.

```http
  GET /{gerente_id}/clientes
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `Long` | **Obrigatório**. ID do gerente. |


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

##### Cria um novo cliente.

```http
  POST /gerentes/{gerente_id}/addCliente
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `Long` | **Obrigatório**. ID do gerente. |

#### Requisição

#### Body

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `nome` | `string` | **Obrigatório**. Nome do novo cliente. |
| `cpf` | `string` | **Obrigatório**. CPF do novo cliente. |
| `email` | `string` | **Obrigatório**. E-mail do novo cliente. |
| `telefone` | `string` | **Obrigatório**. Telefone do novo cliente. |
| `endereco` | `endereco` | **Obrigatório**. Endereço do novo cliente (tipo especificado no final da Documentação). |


#### Respostas

- 201 - Cliente criado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

### Inativar Clientes

##### Inativa um cliente existente.

```http
  PATCH /gerentes/{gerente_id}/inativarCliente/{cliente_id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `Long` | **Obrigatório**. ID do gerente. |
| `cliente_id`      | `Long` | **Obrigatório**. ID do cliente. |

#### Respostas

- 200 - Cliente inativado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

### Ativar Clientes

##### Ativa um cliente existente inativado.

```http
  PATCH /gerentes/{gerente_id}/ativarCliente/{cliente_id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `Long` | **Obrigatório**. ID do gerente. |
| `cliente_id`      | `Long` | **Obrigatório**. ID do cliente. |

#### Respostas

- 200 - Cliente ativado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

### Inativar Gerentes

##### Inativa um gerente existente, onde os clientes do mesmo são transferidos para outro gerente ativo existente.

```http
  PATCH /gerentes/{gerente_id}/inativar/{novoGerente_id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `Long` | **Obrigatório**. ID do atual gerente. |
| `novoGerente_id`      | `Long` | **Obrigatório**. ID do novo gerente. |

#### Respostas

- 200 - Gerente inativado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

### Ativar Gerentes

##### Ativa um gerente existente inativado.

```http
  PATCH /gerentes/{gerente_id}/ativar
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `gerente_id`      | `Long` | **Obrigatório**. ID do atual gerente. |

#### Respostas

- 200 - Gerente ativado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

## Contas

### Listar Extratos

##### Lista todas as transações efetuadas na conta do cliente.

```http
  GET /contas/{conta_id}/extrato
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `conta_id`      | `Long` | **Obrigatório**. ID da conta. |

#### Respostas

- 200 - Conta encontrada e extrato listado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

## Realizar Depósitos

##### Realiza Depósitos em uma conta.

```http
  POST /contas/{conta_id}/depositos
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `conta_id`      | `Long` | **Obrigatório**. ID da conta. |

#### Requisição

#### Body

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `float` | **Obrigatório**. Valor do depósito. |

#### Respostas

- 200 - Depósito realizado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

## Realizar Saques

##### Realiza saques em uma conta.

```http
  POST /contas/{conta_id}/saques
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `conta_id`      | `Long` | **Obrigatório**. ID da conta. |

#### Requisição

#### Body

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `float` | **Obrigatório**. Valor do saque. |

#### Respostas

- 200 - Saque realizado com sucesso.
- 400 - Presente no tópico "Respostas Padrão" no final da Documentação.

## Realizar Transferências

##### Realiza transferências de uma conta para outra.

```http
  POST /contas/{contaOrigem_id}/transferencias/{contaDestino_id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `contaOrigem_id`      | `Long` | **Obrigatório**. ID da conta de origem. |
| `contaDestino_id`      | `Long` | **Obrigatório**. ID da conta de destino. |

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
| `cep` | `string` | **Obrigatório**. CEP do novo cliente. |
| `logradouro` | `string` | **Obrigatório**. Logradouro do novo cliente. |
| `numero` | `string` | **Obrigatório**. Numero do endereço do novo cliente. |
| `setor` | `string` | **Obrigatório**. Setor do endereço do novo cliente. |

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




