# Entrega Semana 8 – DSY2201 (Exp3_S8)

Proyecto consolida lo exigido por la guía de la Experiencia 2 semana 8:
- **Spring Boot** (tu proyecto original)
- **Documentación HATEOAS** (ya presente en tus controladores)
- **Pruebas unitarias JUnit** (≥2)
- **Contenedorización** con `Dockerfile` para despliegue en docker descktop
- **Colección Postman** para evidencias

## Ejecución local

```bash
mvn clean install
mvn spring-boot:run
```

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

> Para  **Oracle ADB con Wallet**, montar el wallet a `/app/wallet` o cópialo dentro de la imagen (ver comentarios en `Dockerfile`).

## Postman

Importa `Exp3_S8.postman_collection.json` y prueba:
- `GET /students`
- `GET /students/{id}`
- `GET /students/ganancias/dia?fecha=YYYY-MM-DD`
- `GET /students/ganancias/diarias?desde=YYYY-MM-DD&hasta=YYYY-MM-DD`

---
