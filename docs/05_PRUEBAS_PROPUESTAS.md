# Fase K | Proponer pruebas antes de proponer código

| Refactorización candidata | Comportamiento a proteger | Prueba propuesta |
|---|---|---|
| Extraer cálculo VIP | VIP conserva el resultado actual | vipConservaResultadoActual() |
| Simplificar validación | 1h retorna 0 y no confirma | unaHoraNoPermiteProcesar() |
| Separar notificación | Reserva válida sigue confirmándose | reservaValidaSeConfirma() |
| Introducir periodo | Periodo inválido sigue rechazándose | periodoInvalidoNoProcesa() |
| Introducir Correo | Correo sin @ o nulo retorna 0 y no confirma | correoInvalidoNoProcesa() |
| Simplificar validación | 2h exactas sí se procesan (límite) | dosHorasSiPermiteProcesar() |
| Separar notificación y persistencia | Los mensajes solo salen si la reserva se confirma | mensajesSoloEnReservaValida() |
| Extraer validador | Reserva nula retorna 0 sin excepción | reservaNulaRetornaCero() |

> Primero define qué comportamiento necesitas proteger; después decide cómo reorganizar la estructura.
