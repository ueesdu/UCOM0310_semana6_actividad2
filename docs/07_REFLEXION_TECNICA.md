# Reflexión técnica final

El problema que más riesgo genera es que `ServicioReservas.procesar` hace todo
en el mismo método: valida, calcula el precio, guarda, envía el correo y
confirma. Si mañana cambia la forma de notificar, tengo que abrir el mismo
método que decide cuánto se cobra, y un error ahí afecta a las dos cosas. Lo
que más me preocupa es la notificación, porque es un efecto externo y hoy solo
se ve como un mensaje en consola.

El problema que parece más fácil, y que puede romper el comportamiento, es
simplificar las validaciones. Son cuatro `if` que devuelven 0, y al
reordenarlos es muy fácil cambiar `< 2` por `<= 2` sin darse cuenta. Con eso
una reserva de dos horas de anticipación dejaría de confirmarse y el programa
seguiría compilando.

Antes de tocar el código necesito pruebas para los seis escenarios de la línea
base: NORMAL con 40, VIP con 34, correo inválido, periodo inválido, dos horas y
una hora. También una para la reserva nula y otra que revise que los mensajes
solo aparecen cuando la reserva se confirma. En cada caso hay que comprobar el
retorno y además el estado de la reserva, porque el servicio devuelve 0 en
cuatro fallas distintas y solo el estado dice si se confirmó.

La primera responsabilidad que movería es el cálculo de la tarifa. Tiene bajo
riesgo, deja el descuento VIP en un solo lugar y se puede defender con dos
pruebas simples (40 y 34) que deben seguir en verde antes y después del cambio.
Mi evidencia sería esa ejecución en verde y un commit pequeño que solo contenga
ese cambio.

Refactorizar es cambiar la estructura interna sin cambiar lo que el sistema
hace hacia afuera: mismo retorno, mismo estado y mismos mensajes. Un cambio
funcional sí cambia ese resultado, por ejemplo validar mejor el correo o cambiar
el descuento. Si al refactorizar aparece algo que quiero corregir, lo anoto y
lo hago aparte, con su propio commit y sus propias pruebas.

## Checklist

- [x] Proyecto base compila y ejecuta.
- [x] Seis escenarios de línea base.
- [x] Mapa de responsabilidades.
- [x] Mínimo cinco problemas diagnosticados.
- [x] Matriz de riesgo.
- [x] Pruebas propuestas.
- [x] Plan priorizado.
- [x] Commit Git del estado inicial.
- [x] Reflexión técnica.
