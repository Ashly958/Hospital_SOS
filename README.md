# 🏥 Hospital S.O.S - Sistema de Gestión Clínica

Este proyecto es una aplicación **Backend** desarrollada con **Spring Boot** para la gestión integral de un centro hospitalario. Permite administrar citas médicas, diagnósticos, historias clínicas y el proceso de cancelación, asegurando la integridad de los datos mediante reglas de negocio automatizadas.

---

## 🚀 Características Principales

* **Gestión de Diagnósticos:** Registro detallado de diagnósticos vinculados a citas médicas y pacientes.
* **Módulo de Cancelaciones:** Sistema de anulación de citas con registro de motivos y actualización automática de estados (`CANCELADA`).
* **Historias Clínicas:** Estructura robusta para el seguimiento médico de los pacientes.
* **Reglas de Negocio:** * Validación de existencia de registros (Citas, Pacientes).
    * Restricción de duplicidad (un solo diagnóstico o cancelación por cita).
    * Sincronización de estados en tiempo real.

---

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.x
* **Persistencia:** Spring Data JPA
* **Base de Datos:** MySQL 8.0
* **Gestión de Dependencias:** Maven
* **Gestor de base de datos:** Dbeaver
* **Prueba de APIs:** Postman
* **Arquitectura:** Basada en capas (Controller, Service, Repository, DTO, Entity).

---

## 📋 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalados:
* **JDK 17** o superior.
* **MySQL Server 8.0+**.
* **Maven**.

---

## ⚙️ Configuración e Instalación

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/ashly958/hospital-sos.git]
    ```

2.  **Configurar la base de datos:**
    En el archivo `src/main/resources/application.properties`, ajusta tus credenciales:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Ejecutar la aplicación:**
    Desde tu IDE (IntelliJ/Eclipse) o mediante consola:
    ```bash
    mvn spring-boot:run
    ```

---

## 📍 Endpoints Principales (API V1)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/v1/medicos` | Crea un nuevo diagnóstico médico. |
| `PUT` | `/api/v1/diagnosticos/{id}` | Actualiza un diagnóstico existente. |
| `POST` | `/api/v1/cancelaciones` | Registra la cancelación de una cita. |
| `DELETE` | `/api/v1/cancelaciones/{id}` | Elimina un registro de cancelación. |
| `GET` | `/api/v1/reportes/estadisticas` | Obtiene métricas generales del sistema. |

---

## 📸 Evidencias de Funcionamiento (Postman)

### Listado de Pacientes (GET)
Se verifica el correcto funcionamiento del endpoint para obtener la lista de pacientes registrados. El sistema devuelve un código de estado `200 OK` y el cuerpo en formato JSON con la información completa.

![Evidencia Postman](https://lh3.googleusercontent.com/u/0/d/1NJy1bOtN9mJ8xOdt4T-k3TKntKhPLAAa)

---

## 💻 Autor
**👩🏻‍🦰 Ashly** *Estudiante de Desarrollo de Software* *Proyecto Módulo Clínico - Hospital S.O.S*

---

### 💡 Nota Técnica para Sustentación
Este sistema utiliza **Transacciones Controladas** (`@Transactional`) para garantizar la consistencia de los datos. Se implementó el patrón **DTO** (Data Transfer Object) para asegurar una comunicación eficiente entre las capas y el uso de **Queries Nativos** para procesos críticos de eliminación, optimizando la persistencia en la base de datos.
