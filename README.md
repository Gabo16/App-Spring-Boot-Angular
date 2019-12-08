# App Spring Boot & Angular

Aplicación para gestionar facturas, clientes y productos 

![clientes-component_ROLE_ADMIN.png](images/clientes-component_ROLE_ADMIN.png "clientes-component_ROLE_ADMIN")

![clientes-detail-component_ROLE_ADMIN.png](images/clientes-detail-component_ROLE_ADMIN.png "clientes-detail-component_ROLE_ADMIN")

![productos-component_ROLE_ADMIN.png](images/productos-component_ROLE_ADMIN.png "productos-component_ROLE_ADMIN")

## Backend
---

### API REST


| METHOD | END POINT                                     | COMMMENT                                             |
| :----- | :-------------------------------------------- | :--------------------------------------------------- |
| POST   | /api/v1/productos                             | Agregar un producto                                  |
| GET    | /api/v1/productos?page={page}                 | Página de lista de productos                         |
| GET    | /api/v1/productos/{id}                        | Obtener un producto por id                           |
| PUT    | /api/v1/productos/{id}                        | Actualizar un producto                               |
| DELETE | /api/v1/productos/{id}                        | Eliminar un producto                                 |
| POST   | /api/v1/clientes                              | Agregar un cliente                                   |
| GET    | /api/v1/clientes/page/{page}                  | Página de lista de clientes                          |
| GET    | /api/v1/clientes/{id}                         | Obtener un cliente por id, con sus facturas          |
| PUT    | /api/v1/clientes/{id}                         | Actualizar un cliente                                |
| DELETE | /api/v1/clientes/{id}                         | Eliminar un cliente                                  |
| POST   | /api/v1/clientes/uploads                      | Agregar imagen a cliente                             |
| GET    | /api/v1/clientes/uploads/img/{nombreFoto:.+}  | Obtener la imagen del  cliente                       |
| POST   | /api/v1/facturas                              | Agregar una factura                                  |
| GET    | /api/v1/facturas/{id}                         | Obtener una factura por id                           |
| GET    | /api/v1/facturas/filtrado-productos/{termino} | Obtener una lista de productos filtrados por termino |
| DELETE | /api/v1/facturas/{id}                         | Eliminar una factura                                 |
| POST   | /api/v1/usuarios                              | Agregar un usuario                                   |
| GET    | /api/v1/usuarios?page={page}                  | Página de lista de usuarios                          |
| GET    | /api/v1/usuarios/{id}                         | Obtener un usuario por id                            |
| PUT    | /api/v1/usuarios/{id}                         | Actualizar un usuario                                |
| POST   | /oauth/token                                  | Obtener token                                        |



### Peticiones de ejemplo
---

#### POST  /api/v1/clientes

Cliente Creado

![POST_api-v1-clientes_created.png](images/POST_api-v1-clientes_created.png "POST  /api/v1/clientes_created")

Validación

![POST_api-v1-clientes_validation.png](images/POST_api-v1-clientes_validation.png "POST  /api/v1/clientes_validation")

#### GET  /api/v1/clientes?page={page} 

Obtener página de clientes

![GET_api-v1-clientes-page_ok.png](images/GET_api-v1-clientes-page_ok.png "GET  /api/v1/clientes?page={page}_ok")
```Json
{
    "number": 0,
    "last": false,
    "size": 5,
    "numberOfElements": 5,
    "totalPages": 3,
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 5,
        "unpaged": false,
        "paged": true
    },
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "content": [
        {
            "id": 1,
            "nombre": "Alejandra",
            "apellido": "Hernandez",
            "email": "alejandra@qwe.com",
            "foto": "noimage.png",
            "createdAt": "2019-12-05T18:43:57",
            "updatedAt": "2019-12-05T18:43:57",
            "facturas": [
                {
                    "id": 1,
                    "descripcion": "descripción de la factura",
                    "observacion": null,
                    "items": [
                        {
                            "id": 1,
                            "cantidad": 2,
                            "producto": {
                                "id": 1,
                                "nombre": "Samsung Galaxy M30 64gb",
                                "precio": 5799.0,
                                "createdAt": "2019-12-05T18:43:57",
                                "updatedAt": "2019-12-05T18:43:57"
                            },
                            "createdAt": "2019-12-05T18:43:57",
                            "updatedAt": "2019-12-05T18:43:57",
                            "importe": 11598.0
                        },
                        {
                            "id": 2,
                            "cantidad": 1,
                            "producto": {
                                "id": 3,
                                "nombre": "Celular Samsung Galaxy S9 64gb",
                                "precio": 18200.0,
                                "createdAt": "2019-12-05T18:43:57",
                                "updatedAt": "2019-12-05T18:43:57"
                            },
                            "createdAt": "2019-12-05T18:43:57",
                            "updatedAt": "2019-12-05T18:43:57",
                            "importe": 18200.0
                        },
                        {
                            "id": 3,
                            "cantidad": 2,
                            "producto": {
                                "id": 4,
                                "nombre": "Xiaomi Mi 9T Pro 128gb",
                                "precio": 9175.0,
                                "createdAt": "2019-12-05T18:43:57",
                                "updatedAt": "2019-12-05T18:43:57"
                            },
                            "createdAt": "2019-12-05T18:43:58",
                            "updatedAt": "2019-12-05T18:43:58",
                            "importe": 18350.0
                        },
                        {
                            "id": 4,
                            "cantidad": 1,
                            "producto": {
                                "id": 5,
                                "nombre": "Samsung Galaxy S10+ Dual SIM 128 GB",
                                "precio": 16700.0,
                                "createdAt": "2019-12-05T18:43:57",
                                "updatedAt": "2019-12-05T18:43:57"
                            },
                            "createdAt": "2019-12-05T18:43:58",
                            "updatedAt": "2019-12-05T18:43:58",
                            "importe": 16700.0
                        }
                    ],
                    "createdAt": "2019-12-05T18:43:57",
                    "updatedAt": "2019-12-05T18:43:57",
                    "total": 64848.0
                },
                {
                    "id": 2,
                    "descripcion": "***",
                    "observacion": "alguna nota importante",
                    "items": [
                        {
                            "id": 5,
                            "cantidad": 2,
                            "producto": {
                                "id": 6,
                                "nombre": "Huawei P30 lite Dual SIM 128GB",
                                "precio": 6299.0,
                                "createdAt": "2019-12-05T18:43:57",
                                "updatedAt": "2019-12-05T18:43:57"
                            },
                            "createdAt": "2019-12-05T18:43:58",
                            "updatedAt": "2019-12-05T18:43:58",
                            "importe": 12598.0
                        }
                    ],
                    "createdAt": "2019-12-05T18:43:58",
                    "updatedAt": "2019-12-05T18:43:58",
                    "total": 12598.0
                }
            ]
        },
        {
            "id": 2,
            "nombre": "Eduardo",
            "apellido": "Quinto",
            "email": "eduardo@qwe.com",
            "foto": "noimage.png",
            "createdAt": "2019-12-05T18:43:57",
            "updatedAt": "2019-12-05T18:43:57",
            "facturas": []
        },
        {
            "id": 3,
            "nombre": "Gabriela",
            "apellido": "Ramirez",
            "email": "gabriela@qwe.com",
            "foto": "noimage.png",
            "createdAt": "2019-12-05T18:43:57",
            "updatedAt": "2019-12-05T18:43:57",
            "facturas": []
        },
        {
            "id": 4,
            "nombre": "Hector",
            "apellido": "Lopez",
            "email": "hecor@qwe.com",
            "foto": "noimage.png",
            "createdAt": "2019-12-05T18:43:57",
            "updatedAt": "2019-12-05T18:43:57",
            "facturas": []
        },
        {
            "id": 5,
            "nombre": "Sandra",
            "apellido": "Lopez",
            "email": "sandra@qwe.com",
            "foto": "noimage.png",
            "createdAt": "2019-12-05T18:43:57",
            "updatedAt": "2019-12-05T18:43:57",
            "facturas": []
        }
    ],
    "first": true,
    "totalElements": 15,
    "empty": false
}
```

En caso de no haber clientes registrados en la base de datos

```Json
{
    "number": 0,
    "last": true,
    "size": 5,
    "numberOfElements": 0,
    "totalPages": 0,
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 5,
        "unpaged": false,
        "paged": true
    },
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "content": [],
    "first": true,
    "totalElements": 0,
    "empty": true
}
```

#### GET /api/v1/clientes/{id}

Obtener cliente por id

![GET_api-v1-clientes-id_ok](images/GET_api-v1-clientes-id_ok.png "GET  /api/v1/clientes/{id}_ok")

```Json
{
    "cliente": {
        "id": 1,
        "nombre": "Alejandra",
        "apellido": "Hernandez",
        "email": "alejandra@qwe.com",
        "foto": "noimage.png",
        "createdAt": "2019-12-05T20:08:02",
        "updatedAt": "2019-12-05T20:08:02",
        "facturas": [
            {
                "id": 1,
                "descripcion": "descripción de la factura",
                "observacion": null,
                "items": [
                    {
                        "id": 1,
                        "cantidad": 2,
                        "producto": {
                            "id": 1,
                            "nombre": "Samsung Galaxy M30 64gb",
                            "precio": 5799.0,
                            "createdAt": "2019-12-05T20:08:02",
                            "updatedAt": "2019-12-05T20:08:02"
                        },
                        "createdAt": "2019-12-05T20:08:02",
                        "updatedAt": "2019-12-05T20:08:02",
                        "importe": 11598.0
                    },
                    {
                        "id": 2,
                        "cantidad": 1,
                        "producto": {
                            "id": 3,
                            "nombre": "Celular Samsung Galaxy S9 64gb",
                            "precio": 18200.0,
                            "createdAt": "2019-12-05T20:08:02",
                            "updatedAt": "2019-12-05T20:08:02"
                        },
                        "createdAt": "2019-12-05T20:08:02",
                        "updatedAt": "2019-12-05T20:08:02",
                        "importe": 18200.0
                    },
                    {
                        "id": 3,
                        "cantidad": 2,
                        "producto": {
                            "id": 4,
                            "nombre": "Xiaomi Mi 9T Pro 128gb",
                            "precio": 9175.0,
                            "createdAt": "2019-12-05T20:08:02",
                            "updatedAt": "2019-12-05T20:08:02"
                        },
                        "createdAt": "2019-12-05T20:08:02",
                        "updatedAt": "2019-12-05T20:08:02",
                        "importe": 18350.0
                    },
                    {
                        "id": 4,
                        "cantidad": 1,
                        "producto": {
                            "id": 5,
                            "nombre": "Samsung Galaxy S10+ Dual SIM 128 GB",
                            "precio": 16700.0,
                            "createdAt": "2019-12-05T20:08:02",
                            "updatedAt": "2019-12-05T20:08:02"
                        },
                        "createdAt": "2019-12-05T20:08:02",
                        "updatedAt": "2019-12-05T20:08:02",
                        "importe": 16700.0
                    }
                ],
                "createdAt": "2019-12-05T20:08:02",
                "updatedAt": "2019-12-05T20:08:02",
                "total": 64848.0
            },
            {
                "id": 2,
                "descripcion": "***",
                "observacion": "alguna nota importante",
                "items": [
                    {
                        "id": 5,
                        "cantidad": 2,
                        "producto": {
                            "id": 6,
                            "nombre": "Huawei P30 lite Dual SIM 128GB",
                            "precio": 6299.0,
                            "createdAt": "2019-12-05T20:08:02",
                            "updatedAt": "2019-12-05T20:08:02"
                        },
                        "createdAt": "2019-12-05T20:08:02",
                        "updatedAt": "2019-12-05T20:08:02",
                        "importe": 12598.0
                    }
                ],
                "createdAt": "2019-12-05T20:08:02",
                "updatedAt": "2019-12-05T20:08:02",
                "total": 12598.0
            }
        ]
    }
}
```

#### PUT /api/v1/clientes/{id}

Actualizar cliente por id

![PUT_api-v1-clientes-id_ok.png](images/PUT_api-v1-clientes-id_ok.png "PUT  /api/v1/clientes/{id}_ok")

#### DELETE /api/v1/clientes/{id}

Eliminar cliente por id

![DELETE_api-v1-clientes-id_notContent.png](images/DELETE_api-v1-clientes-id_notContent.png "DELETE  /api/v1/clientes/{id}_notContent")


### Respuestas de error

#### End points en los que sucede en el ejemplo de la gestión de los clientes

* GET /api/v1/clientes/{id}
* PUT /api/v1/clientes/{id}
* DELETE /api/v1/clientes/{id}

No encontrado

```Json
{
    "message": "error al consultar cliente en la base de datos",
    "error": "el cliente no existe en la base de datos"
}
```

#### End points en los que sucede en el ejemplo de la gestión de los clientes

* POST /api/v1/clientes            
* GET /api/v1/clientes/page/{page}
* GET /api/v1/clientes/{id}       
* PUT /api/v1/clientes/{id}       
* DELETE /api/v1/clientes/{id}       

Token invalido

```Json
{
    "error": "invalid_token",
    "error_description": "Cannot convert access token to JSON"
}
```

No autorizado

```Json
{
    "error": "unauthorized",
    "error_description": "Full authentication is required to access this resource"
}
```

Acceso denegado

```Json
{
    "error": "access_denied",
    "error_description": "Access is denied"
}
```

El token ha expirado

```Json
{
    "error": "invalid_token",
    "error_description": "Access token expired: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsInVzZXJfbmFtZSI6Impvc2UiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiYXBlbGxpZG8iOiJIZXJuYW5kZXoiLCJleHAiOjE1NzM5MzA2MTAsIm5vbWJyZSI6Ikpvc2UiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYzYzM2YzOWMtY2ZlOS00OTEwLThlNzktOWI1NjZlZjgwZjA1IiwiZW1haWwiOiJqb3NlQHF3ZS5jb20iLCJjbGllbnRfaWQiOiJhbmd1bGFyYXBwIn0.oYnSb_SVAlPYw1w7Y4IOhdJzSyPpvYEoAQvfp_tWPMghJ4XCxYC204xpp1S8u6bbAxjS7vY7ofS_zi1uTQ5bT2_IQngOIBLAWbn2Fd25gLbhnC18SlYyo3pDgRixgXwuXp8ds9KKLTshkji6qJTSmiJHRhpW1a_wqdGDQXkvMjtRZuk4YQkGyz-9sEE4INtZdpXBWlrRZgP_jf-1dgfGAUm6PYoQ1R7z_-jhErjgr39ryoJ6UTWRHYI-exxe7mFIupNLKkapqZCc9Hu7lrFb6pgejxuxppAOnhHcZm3R6axN-2iP4spGa36m-HIhP9vFIo9UJvht6No8IY0NA665IQ"
}
```





## Frontend
---

### Vistas Públicas

Listado de productos

![productos-component.png](images/productos-component.png "productos-component")

Componente login

![login-component.png](images/login-component.png "login-component")

Ingreso fallido
![login-component-invalid-credentials-invalid-credentials.png](images/login-component-invalid-credentials.png "login-component-invalid-credentials")


### Vistas Privadas con ROLE_USER
---

Ingreso exitoso

![login-component-valid-credentials_ROLE_USER.png](images/login-component-valid-credentials_ROLE_USER.png "login-component-valid-credentials_ROLE_USER")

Listado de clientes

![clientes-component_ROLE_USER.png](images/clientes-component_ROLE_USER.png "clientes-component_ROLE_USER")

Detalle del cliente

![clientes-detail-component_ROLE_USER.png](images/clientes-detail-component_ROLE_USER.png "clientes-detail-component_ROLE_USER")

Agregar una factura al cliente

*Validaciones*

![facturas-form-component-validations_ROLE_USER.png](images/facturas-form-component-validations_ROLE_USER.png "facturas-form-component-validations_ROLE_USER")

*Factura*

![facturas-form-component_ROLE_USER.png](images/facturas-form-component_ROLE_USER.png "facturas-form-component_ROLE_USER")

*Factura agregada*

![facturas-form-component-success_ROLE_USER.png](images/facturas-form-component-success_ROLE_USER.png "facturas-form-component-success_ROLE_USER")

![clientes-detail-component-factura-agregada_ROLE_USER.png](images/clientes-detail-component-factura-agregada_ROLE_USER.png "clientes-detail-component-factura-agregada_ROLE_USER")

Detalle factura 

![facturas-detail-component_ROLE_USER.png](images/facturas-detail-component_ROLE_USER.png "facturas-detail-component_ROLE_USER")

Listado de productos

![productos-component_ROLE_USER.png](images/productos-component_ROLE_USER.png "productos-component_ROLE_USER")

### Vistas Privadas con ROLE_ADMIN
---

Ingreso exitoso

![login-component-valid-credentials_ROLE_ADMIN.png](images/login-component-valid-credentials_ROLE_ADMIN.png "login-component-valid-credentials_ROLE_ADMIN")

Listado de clientes

![clientes-component_ROLE_ADMIN.png](images/clientes-component_ROLE_ADMIN.png "clientes-component_ROLE_ADMIN")

Detalle del cliente

![clientes-detail-component_ROLE_ADMIN.png](images/clientes-detail-component_ROLE_ADMIN.png "clientes-detail-component_ROLE_ADMIN")

Eliminar factura

![clientes-detail-component-delete-factura_ROLE_ADMIN.png](images/clientes-detail-component-delete-factura_ROLE_ADMIN.png "clientes-detail-component-delete-factura_ROLE_ADMIN")

![clientes-detail-component-delete-factura-aceptar_ROLE_ADMIN.png](images/clientes-detail-component-delete-factura-aceptar_ROLE_ADMIN.png "clientes-detail-component-delete-factura-aceptar_ROLE_ADMIN")

Detalle cliente 

![clientes-detail-component_ROLE_ADMIN.png](images/clientes-detail-component_ROLE_ADMIN.png "clientes-detail-component_ROLE_ADMIN")

Listado de productos

![productos-component_ROLE_ADMIN.png](images/productos-component_ROLE_ADMIN.png "productos-component_ROLE_ADMIN")



