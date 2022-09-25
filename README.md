# TodoListProject

A Todo List Backend Assignment

## To run project:

```bash
docker-compose up
```

To see swagger ui documentation go to:
>http://host.docker.internal:8080/swagger-ui/index.html

## Exposed APIs:

### User APIs:

Path: **POST
/api/user/register**

Example:
```bash
curl --location --request POST 'host.docker.internal:8080/api/user/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"salma",
    "password":"salma",
    "name":"Salma"
}'
```
status: `201 Created`

Output:
```json
{
  "id": 1,
  "name": "Salma",
  "username": "salma"
}
```

Path: **POST
/api/login**

Example:
```bash
curl --location --request POST 'host.docker.internal:8080/api/login' \
--header 'Authorization: Basic YWhtZWQ6YWhtZWQ='
```

Output:
```json
{
  "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjU0ODN9.rHTKVt8T_QaBUi42axQvUqzonWJXnvUDTyxDGNEM8OI",
  "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjkwMjN9.paW38MoDVJnGQ6RKiJFLjyKDx-zVuT60_c2N-jpZ9Pw"
}
```

Path: **GET
/api/users**

Example:
```bash
curl --location --request GET 'host.docker.internal:8080/api/users' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjUzMTV9.Hlk_ebyoT5VnEZeeINI_GUs7QdTOiH1gvJuG8dprrQs'
```

Output:
```json
[
  {
    "id": 1,
    "name": "Salma",
    "username": "salma"
  }
]
```



Path: **GET
/api/user/{username}**

Example:
```bash
curl --location --request GET 'host.docker.internal:8080/api/user/salma' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjUzMTV9.Hlk_ebyoT5VnEZeeINI_GUs7QdTOiH1gvJuG8dprrQs'
```

Output:
```json
{
  "id": 1,
  "name": "Salma",
  "username": "salma"
}
```

Path: **GET
/api/token/refresh**

Example:
```bash
curl --location --request GET 'host.docker.internal:8080/api/token/refresh' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjkwMjN9.paW38MoDVJnGQ6RKiJFLjyKDx-zVuT60_c2N-jpZ9Pw'```
```

Output:

```json
{
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdG9rZW4vcmVmcmVzaCIsImV4cCI6MTY2NDA2NjM2Nn0.mePPJj5ymq1SYBKsFxWiGe69R4GxFAbt1pmWtWqNYIA",
    "refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjk0MDB9.vfO3AVQF0QbYV30vheg2XjOFwbe6sFM3rmAeZaUBXPI"
}
```

### Item APIs:

Path: **POST
/api/item/save**

Example:
```bash
curl --location --request POST 'host.docker.internal:8080/api/item/save' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjU5MDl9.k6jOSSTtuQiuWgcIIB-tlyl7hdX3_SFGkHEQQWKnRBY' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "Clean",
    "description":"Remove clothes from floor"
}'
```

Output:
```json
{
  "id": 2,
  "title": "Clean",
  "description": "Remove clothes from floor",
  "done": false,
  "user": {
    "id": 1,
    "name": "Salma",
    "username": "salma"
  }
}
```

Path: **PUT**
**/api/item/update/{id}**

Example:
```bash
curl --location --request PUT 'host.docker.internal:8080/api/item/update/2' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjU5MDl9.k6jOSSTtuQiuWgcIIB-tlyl7hdX3_SFGkHEQQWKnRBY' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "Clean room"
}'
```

Output:
```json
{
  "id": 2,
  "title": "Clean room",
  "description": "Remove clothes from floor",
  "done": false,
  "user": {
    "id": 1,
    "name": "Salma",
    "username": "salma"
  }
}
```
Path: **GET
/api/items**

Example:
```bash
curl --location --request GET 'host.docker.internal:8080/api/items' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjI4MTJ9.QLnV8o29ZumfyAXK7n80556GZ3GLPxqznrFi6My_Ygo' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "Clean room",
    "description":"Remove clothes from floor"
}'
```

Output:
```json
{
  "content": [
    {
      "id": 2,
      "title": "Clean room",
      "description": "Remove clothes from floor",
      "done": false,
      "user": {
        "id": 1,
        "name": "Salma",
        "username": "salma"
      }
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 4,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 1,
  "totalPages": 1,
  "size": 4,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 1,
  "empty": false
}
```

Path: **GET
/api/item/{id}**

Example:
```bash
curl --location --request GET 'host.docker.internal:8080/api/item/2' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjUzMTV9.Hlk_ebyoT5VnEZeeINI_GUs7QdTOiH1gvJuG8dprrQs' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": Clean room",
    "description":"Remove clothes from floor"
}'
```

Output:
```json
{
  "id": 2,
  "title": "Clean room",
  "description": "Remove clothes from floor",
  "done": false,
  "user": {
    "id": 1,
    "name": "Salma",
    "username": "salma"
  }
}
```

Path: **PUT
/api/item/done/{id}**

Example:
```bash
curl --location --request PUT 'host.docker.internal:8080/api/item/done/2' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjU5MDl9.k6jOSSTtuQiuWgcIIB-tlyl7hdX3_SFGkHEQQWKnRBY'
```

Output:
```json
{
  "id": 2,
  "title": "Clean room",
  "description": "Remove clothes from floor",
  "done": true,
  "user": {
    "id": 1,
    "name": "Salma",
    "username": "salma"
  }
}
```

Path: **GET
/api/items?done=true**

Example:
```bash
curl --location --request GET 'host.docker.internal:8080/api/items' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjI4MTJ9.QLnV8o29ZumfyAXK7n80556GZ3GLPxqznrFi6My_Ygo' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "Clean room",
    "description":"Remove clothes from floor"
}'
```

Output:
```json
{
  "content": [
    {
      "id": 2,
      "title": "Clean room",
      "description": "Remove clothes from floor",
      "done": true,
      "user": {
        "id": 1,
        "name": "Salma",
        "username": "salma"
      }
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 4,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 1,
  "totalPages": 1,
  "size": 4,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 1,
  "empty": false
}
```


Path: **DELETE
/api/item/{id}**

Example:
```bash
curl --location --request DELETE 'host.docker.internal:8080/api/item/2' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZCIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvbG9naW4iLCJleHAiOjE2NjQwNjUzMTV9.Hlk_ebyoT5VnEZeeINI_GUs7QdTOiH1gvJuG8dprrQs'
```

status: `204 No Content`

## Steps:
1. Register
2. Login
3. Copy access_token from response body
4. Add access_token in postman's Authentication type Bearer token to authenticate requests

## Could be improved:
1. Username and password validation
2. Increase unit testcases coverage
