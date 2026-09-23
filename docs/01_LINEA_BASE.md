# Fase C | Línea base manual

Los seis escenarios se ejecutaron **sin refactorizar el diseño**, con la clase
`edu.uees.refactor.app.LineaBase`, que llama al servicio real con cada entrada.

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="edu.uees.refactor.app.LineaBase"
```

| ID | Escenario | Entrada principal | Estado | Retorno | Mensajes / excepción |
|---|---|---|---|---|---|
| LB-01 | NORMAL válida | NORMAL, correo válido, 5h | CONFIRMADA | 40.0 | "Guardando reserva R-001" y "Correo enviado a ana@uees.edu.ec". Sin excepción |
| LB-02 | VIP válida | VIP, correo válido, 5h | CONFIRMADA | 34.0 | "Guardando reserva R-002" y "Correo enviado a ana@uees.edu.ec". Sin excepción |
| LB-03 | Correo inválido | "incorrecto" | PENDIENTE | 0.0 | Ningún mensaje. Sin excepción |
| LB-04 | Periodo inválido | fin <= inicio | PENDIENTE | 0.0 | Ningún mensaje. Sin excepción |
| LB-05 | Límite válido | 2h anticipación | CONFIRMADA | 40.0 | "Guardando reserva R-005" y "Correo enviado a ana@uees.edu.ec". Sin excepción |
| LB-06 | Límite inválido | 1h anticipación | PENDIENTE | 0.0 | Ningún mensaje. Sin excepción |

## Preguntas

1. **¿Qué valores cambian entre NORMAL y VIP?** Solo el retorno: 40.0 para NORMAL y 34.0 para VIP. El estado final y los dos mensajes son iguales.
2. **¿Qué casos dejan la reserva en PENDIENTE?** Correo inválido, periodo inválido y anticipación de 1 hora. La reserva nula no tiene estado.
3. **¿Qué devuelve `procesar()` cuando una entrada no es procesable?** Devuelve 0 en todos los casos. No indica cuál regla falló.
4. **¿Existe alguna excepción visible en el flujo actual?** No. Ni la reserva nula lanza excepción, porque el primer `if` devuelve 0.
5. **¿Qué mensajes aparecen solo cuando la reserva se confirma?** "Guardando reserva <id>" y "Correo enviado a <correo>", en ese orden y antes de confirmar.
