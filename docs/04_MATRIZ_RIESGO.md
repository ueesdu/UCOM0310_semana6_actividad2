# Fase J | Matriz de riesgo

| Cambio candidato | Probabilidad de romper | Impacto si rompe | Riesgo | Cómo reducirlo |
|---|---|---|---|---|
| Extraer clase de notificación | Media. Hay que mover el mensaje sin cambiar el texto ni el orden | Alto. Es un efecto externo: un correo que no sale o que sale en un caso inválido | Alto | Prueba que capture la consola en casos válidos e inválidos, y commit antes del cambio |
| Introducir Correo | Media. Cambia cómo se construye la reserva y tienta a "mejorar" la regla del @ | Medio. Puede aceptar o rechazar correos distintos a los de hoy | Medio | Conservar exactamente `contains("@")` y probar correo inválido y nulo |
| Introducir PeriodoReserva | Media. Toca el constructor de `Reserva` y el caso fin igual a inicio | Medio. Un periodo inválido podría pasar o lanzar excepción | Medio | Mantener el constructor público y probar fin igual y fin anterior al inicio |
| Simplificar validaciones | Media. Es fácil invertir mal una condición o cambiar `<` por `<=` | Alto. Cambia qué reservas se confirman, que es el contrato principal | Alto | Probar los límites de 1 y 2 horas, la reserva nula y cada regla por separado |
| Separar cálculo VIP | Baja. Es un cambio local dentro del mismo método | Medio. Cambia el monto que se cobra | Bajo | Probar NORMAL = 40 y VIP = 34 antes y después |

## Escala

- **Bajo:** cambio local, comportamiento bien entendido y prueba fácil de crear.
- **Medio:** afecta varias decisiones o requiere adaptar construcción de objetos.
- **Alto:** puede alterar contrato observable, flujos de error o efectos externos.
