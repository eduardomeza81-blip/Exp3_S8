# Entrega Semana 8 – DSY2201 (Exp3_S8)

Proyecto consolida lo exigido por la guía de la Experiencia 2 semana 8:
- **Spring Boot** (proyecto original)
- **Documentación HATEOAS** (presente   controladores)
- **Pruebas unitarias JUnit** (≥2)
- **Contenedorización** con `Dockerfile` para despliegue en docker descktop
- **Colección Postman**  evidencias de funcionamiento

## Ejecución local

```bash
mvn clean install
mvn spring-boot:run
```

## Docker
# Valida que el YAML esté OK
docker compose config

# Recrea desde cero
```bash
docker compose down -v
docker compose up --build -d
```
> Para  **Oracle ADB con Wallet**, montar el wallet a `/app/wallet` o cópialo dentro de la imagen (ver comentarios en `Dockerfile`).

## Postman

Importa `Exp3_S8.postman_collection.json` y prueba:
- `GET /students`
- `GET /students/{id}`
- `GET /students/ganancias/dia?fecha=YYYY-MM-DD`
- `GET /students/ganancias/diarias?desde=YYYY-MM-DD&hasta=YYYY-MM-DD`

---
