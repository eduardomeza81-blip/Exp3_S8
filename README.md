# Entrega Semana 8 – DSY2201 (Exp3_S8)

Este proyecto consolida lo exigido por la guía de la **Semana 8**:
- **Spring Boot** (tu proyecto original)
- **Documentación HATEOAS** (ya presente en tus controladores)
- **Pruebas unitarias JUnit** (≥2)
- **Base de datos Oracle** con scripts:
  - `src/main/resources/schema.sql`
  - `src/main/resources/data.sql` (≥3 registros)
- **Contenedorización** con `Dockerfile` para despliegue en Cloud
- **Colección Postman** para evidencias

## Ejecución local

```bash
mvn clean install
mvn spring-boot:run
```

Configura en tu `application.properties` el uso de variables de entorno (ejemplo):

```properties
spring.datasource.url=${DATASOURCE_URL}
spring.datasource.username=${DATASOURCE_USERNAME}
spring.datasource.password=${DATASOURCE_PASSWORD}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```

Crea un archivo `.env` (basado en `.env.example`) con tus credenciales reales.

## Scripts Oracle

- `schema.sql` crea la tabla base de ejemplo **STUDENT** (ajústala si tu dominio real difiere).
- `data.sql` inserta **≥3** registros de prueba.

> Puedes ejecutar estos scripts manualmente o dejar que Spring los ejecute según tu configuración.

## Docker

1. Empaquetar:
```bash
mvn -DskipTests package
```

2. Construir imagen:
```bash
docker build -t exp3_s8:latest .
```

3. Ejecutar contenedor (usando `.env`):
```bash
docker run --rm -p 8080:8080 --env-file .env exp3_s8:latest
```

> Si usas **Oracle ADB con Wallet**, monta el wallet a `/app/wallet` o cópialo dentro de la imagen (ver comentarios en `Dockerfile`).

## Postman

Importa `Exp3_S8.postman_collection.json` y prueba:
- `GET /students`
- `GET /students/{id}`
- `GET /students/ganancias/dia?fecha=YYYY-MM-DD`
- `GET /students/ganancias/diarias?desde=YYYY-MM-DD&hasta=YYYY-MM-DD`

---

_Consolidado el: 2025-10-05T21:06:59_
