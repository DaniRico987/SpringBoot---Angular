# Sistema de Gestión de Productos

Aplicación full-stack para la gestión de productos desarrollada con **Spring Boot** (Backend) y **Angular** (Frontend).

## 📁 Estructura del Proyecto

Este es un monorepo que contiene tanto el backend como el frontend en el mismo repositorio:

```
SpringBoot & Angular/
├── backend/              # Backend Spring Boot
│   └── backend/         # Proyecto Maven
│       ├── src/
│       ├── pom.xml
│       └── ...
├── frontend/            # Frontend Angular
│   ├── src/
│   ├── package.json
│   └── ...
└── README.md           # Este archivo
```

## 🚀 Requisitos Previos

- **Java 25** o superior
- **Maven 3.6+**
- **Node.js 18+** y **npm**
- **PostgreSQL** (base de datos)

## ⚙️ Configuración

### Backend (Spring Boot)

1. Navega a la carpeta del backend:
```bash
cd backend/backend
```

2. Configura la base de datos en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/product_db_devsenior
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña
```

3. Ejecuta el backend:
```bash
./mvnw spring-boot:run
# O en Windows:
mvnw.cmd spring-boot:run
```

El backend estará disponible en `http://localhost:8080`

### Frontend (Angular)

1. Navega a la carpeta del frontend:
```bash
cd frontend
```

2. Instala las dependencias:
```bash
npm install
```

3. Ejecuta el servidor de desarrollo:
```bash
ng serve
# O
npm start
```

El frontend estará disponible en `http://localhost:4200`

## 📚 Tecnologías Utilizadas

### Backend
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

### Frontend
- **Angular 20**
- **TypeScript**
- **RxJS**
- **Angular Reactive Forms**

## 🎯 Funcionalidades

- ✅ Listar productos
- ✅ Crear productos
- ✅ Editar productos
- ✅ Eliminar productos
- ✅ Validación de formularios
- ✅ Diseño responsive
- ✅ CORS configurado

## 🔧 API Endpoints

El backend expone los siguientes endpoints:

- `GET /api/productos` - Obtener todos los productos
- `GET /api/productos/{id}` - Obtener un producto por ID
- `POST /api/productos` - Crear un nuevo producto
- `PUT /api/productos/{id}` - Actualizar un producto
- `DELETE /api/productos/{id}` - Eliminar un producto

## 📝 Notas de Desarrollo

- El backend está configurado para permitir peticiones desde `http://localhost:4200`
- La base de datos se actualiza automáticamente con `spring.jpa.hibernate.ddl-auto=update`
- El frontend usa routing para navegar entre crear y editar productos

## 👨‍💻 Autor

Desarrollado como proyecto de aprendizaje

