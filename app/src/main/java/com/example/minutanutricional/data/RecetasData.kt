package com.example.minutanutricional.data

import com.example.minutanutricional.model.Receta

object RecetasData {
    val recetasSemana: List<Receta> = listOf(
        Receta(
            dia = "Lunes",
            nombre = "Ensalada de quinoa y verduras",
            recomendacion = "Alta en fibra y proteína vegetal; ideal para saciedad."
        ),
        Receta(
            dia = "Martes",
            nombre = "Pollo al horno con verduras",
            recomendacion = "Proteína magra + vegetales; moderar sal y preferir especias."
        ),
        Receta(
            dia = "Miércoles",
            nombre = "Lentejas guisadas",
            recomendacion = "Legumbres ricas en hierro y fibra; acompaña con ensalada."
        ),
        Receta(
            dia = "Jueves",
            nombre = "Pescado a la plancha con arroz integral",
            recomendacion = "Omega-3 y carbohidrato complejo; evita frituras."
        ),
        Receta(
            dia = "Viernes",
            nombre = "Tortilla de verduras",
            recomendacion = "Opción simple y nutritiva; agrega variedad de vegetales."
        )
    )
}
