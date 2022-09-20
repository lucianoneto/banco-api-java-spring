
# API Documentation

## Manager

### Create Manager

##### Creates a new manager.

```http
  POST /managers
```
#### Request

#### Body

| Parameter   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **Required**. New manager name. |
| `cpf` | `string` | **Required**. New manager CPF. |
| `email` | `string` | **Required**. New manager e-mail. |
| `phone` | `string` | **Required**. New manager phone. |


#### Responses

- 201 - Manager created successfully.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.


### List Clients by Managers

##### Lists all clients registered by a specific manager.

```http
  GET /{managerId}/clients
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `managerId`      | `long` | **Required**. Manager ID. |


#### Responses

- 200 - Manager found and clients listed successfully.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

### List all Clients

#### Lists all clients registered in the bank.

```http
  GET /managers/clients
```
#### Responses

- 200 - Clients listed successfully.

### Create Clients

##### Creates a new Client.

```http
  POST /managers/{managerId}/addClient
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `managerId`      | `long` | **Required**. Manager ID. |

#### Request

#### Body

| Parameter   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | **Required**. New client name. |
| `cpf` | `string` | **Required**. New client CPF. |
| `email` | `string` | **Required**. New client e-mail. |
| `phone` | `string` | **Required**. New client phone. |
| `address` | `address` | **Required**. New client address (type specified at the end of the Documentation). |


#### Responses

- 201 - Client created successfully.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

### Update Clients

##### Updates an existent Client.

```http
  PATCH /managers/{managerId}/{clientId}
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `managerId`      | `long` | **Required**. Manager ID. |
| `clientId`   |   `long` |  **Required**. Client ID.              |

#### Request

#### Body

| Parameter   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `string` | **Optional**. Updated client e-mail. |
| `phone` | `string` | **Optional**. Updated client phone. |
| `address` | `address` | **Optional**. Updated client address (type specified at the end of the Documentation). |

#### Responses

- 201 - Client updated successfully.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

### Inactivate Clients

##### Inactivates a existent client.

```http
  PATCH /managers/{managerId}/clients/{clientId}/inactivate
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `managerId`      | `long` | **Required**. Manager ID. |
| `clientId`      | `long` | **Required**. Client ID. |

#### Responses

- 200 - Client inactivated successfully.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

### Activate Clients

##### Activates an existing inactivated client.

```http
  PATCH /managers/{managerId}/clients/{clientId}/activate
```
| Parameter    | Type       | Description                                   |
|:-------------| :--------- | :------------------------------------------ |
| `managerId` | `long` | **Required**. Manager ID. |
| `clientId`  | `long` | **Required**. Client ID. |

#### Responses

- 200 - Client activated successfully.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

### Inactivate Managers

##### Inactivates an existing manager, where its clients are transferred to another existing active manager.

```http
  PATCH /managers/{managerId}/inactivate/{newManagerId}
```
| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `managerId`      | `long` | **Required**. Current manager ID. |
| `newManagerId`      | `long` | **Required**. New manager ID. |

#### Responses

- 200 - Manager inactivated successfuly.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

### Activate Managers

#####  Actives an existing manager inactivated

```http
  PATCH /managers/{managerId}/activate
```
| Parameter   | Type       | Description                                 |
| :---------- | :--------- | :------------------------------------------ |
| `managerId`      | `long` | **Required**. Manager ID. |

#### Responses

- 200 - Manager activated successfuly.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

## Accounts

### List Statement

##### Lists all performed transactions on client's account.

```http
  GET /accounts/{accountId}/statement
```
| Parameter   | Type       | Description                            |
| :---------- | :--------- | :------------------------------------------ |
| `accountId`      | `long` | **Required**. Account ID. |

#### Responses

- 200 - Account found and statement listed successfully.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

## Make Deposits

##### Make deposits into an account.

```http
  POST /accounts/{accountId}/deposit
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `accountId`      | `long` | **Required**. Account ID. |

#### Request

#### Body

| Parameter   | Type       | Description                         |
| :---------- | :--------- | :---------------------------------- |
| `value` | `float` | **Required**. Deposit value. |

#### Responses

- 200 - Deposit made successfuly.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

## Make Withdraw

##### Make withdraws into an account.

```http
  POST /accounts/{accountId}/withdraw
```

| Parameter   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `accountId`      | `long` | **Required**. Account ID. |

#### Request

#### Body

| Parameter   | Type       | Description                         |
| :---------- | :--------- | :---------------------------------- |
| `value` | `float` | **Required**. Withdraw value. |

#### Responses

- 200 - Withdraw made successfuly.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

## Make Tranfers

##### Make transfers from one account to another.

```http
  POST /accounts/{originAccountId}/transfer/{destinyAccountId}
```

| Parameter   | Type       | Description                               |
| :---------- | :--------- | :------------------------------------------ |
| `originAccountId`      | `long` | **Required**. Origin account ID. |
| `destinyAccountId`      | `long` | **Required**. Destiny account ID. |

#### Responses

#### Body

| Parameter   | Type       | Description                         |
| :---------- | :--------- | :---------------------------------- |
| `value` | `float` | **Required**. Transfer value. |

#### Responses

- 200 - Transfer made successfuly.
- 400 - Response in the "Default Responses" topic at the end of the Documentation.

--------------------
## Data Types

#### Address

| Parameter   | Type       | Description                         |
| :---------- | :--------- | :---------------------------------- |
| `cep` | `string` | **Required**. New client CEP. |
| `street` | `string` | **Required**. New client street. |
| `number` | `string` | **Required**. New client address number. |
| `district` | `string` | **Required**. New client distrit. |

-------------------
## Default Responses

#### Status 400

Some field was sent incorrectly (missing, blank or `null`).

#### Body 

| Parameter   | Type       | Description                         |
| :---------- | :--------- | :---------------------------------- |
| `status` | `string` | Request status. |
| `dateTime` | `string` | Error date and time. |
| `title` | `string` | Error generic message. |
| `field` | `string` | Error specific message, inform the field and which error is. |
