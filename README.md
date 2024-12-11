# 🛠️ Conline Backend

Este es el backend de Conline, una aplicación de chat en tiempo real desarrollada con Spring Boot. Proporciona la lógica del servidor, manejo de WebSocket para la comunicación en tiempo real y autenticación mediante OAuth2 con Google.




## Características Principales


- **Comunicación en tiempo real:** Implementación de WebSocket para el envío y recepción de mensajes.

- **Autenticación con Google:** Seguridad implementada con Spring Security y OAuth2.

- **API REST:** Proporciona los endpoints necesarios para la comunicación con el frontend.

- **Arquitectura modular:** Código organizado en controladores, servicios y repositorios para facilitar la escalabilidad.
## 🛠️ Tecnologías Utilizadas

- Java 17
- Spring Boot 3.x
- Spring WebSocket
- Spring Security
- OAuth2 Google Login
- Maven
## 📂 Estructura del Proyecto

```bash
conline-backend/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.conline.DDP_CONLINE_Backend
│   │   │       ├── config/         
│   │   │       ├── controllers/     
│   │   │       ├── dtos/
|   |   |       ├── entities/         
│   │   │       ├── services/       
│   │   │       └── repositories/     
│   │   └── resources/
│   │       ├── application.properties         
│   └── test/
│       └── java/                     
│
├── pom.xml   

```
