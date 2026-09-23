# Fase L | Plan priorizado de refactorización

No se implementa todavía.

| Orden | Cambio | Por qué primero / después | Pruebas requeridas | Dependencias |
|---:|---|---|---|---|
| 1 | Caracterizar los seis escenarios con JUnit 5 y AAA | Sin red de seguridad cualquier cambio posterior es a ciegas | Las seis de la línea base más reserva nula | Ninguna |
| 2 | Extraer el cálculo de tarifa a un método con intención | Es el cambio de menor riesgo y deja el descuento VIP en un solo lugar | NORMAL = 40 y VIP = 34 | 1 |
| 3 | Extraer la validación a una clase propia y darle nombre a cada regla | Saca la mayor cantidad de decisiones del servicio con pruebas ya claras | Correo, periodo, 1h, 2h y nula | 1 |
| 4 | Introducir `PeriodoReserva` para inicio y fin | Cambia datos y construcción, por eso va después de tener la validación aislada | Periodo inválido (fin igual y fin anterior) | 1 y 3 |
| 5 | Separar notificación y persistencia en clases propias | Toca efectos externos, es el cambio de mayor riesgo y va al final | Válida se confirma y mensajes solo en casos válidos | 1 |

## Razonamiento

Primero se protege, después se hace el cambio más barato y al final lo más
riesgoso. Los Value Objects van después de aislar la validación porque cambian
cómo se construyen los objetos. La notificación va al último porque es un efecto
externo y hoy solo se puede observar leyendo la consola.
