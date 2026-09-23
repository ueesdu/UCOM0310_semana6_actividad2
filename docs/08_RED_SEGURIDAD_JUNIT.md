# Laboratorio 2 | Red de seguridad con JUnit 5

Suite: `src/test/java/edu/uees/refactor/service/ServicioReservasTest.java`,
8 pruebas con estructura AAA. Se ejecuta con `mvn clean test`.

| Prueba | Escenario | Comportamiento que protege |
|---|---|---|
| `reservaNormalValidaSeConfirmaYRetorna40` | LB-01 | Una reserva NORMAL válida queda CONFIRMADA y cobra 40 |
| `reservaVipValidaSeConfirmaYRetorna34` | LB-02 | El descuento VIP del 15 %: queda CONFIRMADA y cobra 34 |
| `correoInvalidoRetornaCeroYNoConfirma` | LB-03 | Un correo sin @ devuelve 0 y la reserva sigue PENDIENTE |
| `periodoInvalidoRetornaCeroYNoConfirma` | LB-04 | Un periodo con fin igual al inicio devuelve 0 y no confirma |
| `dosHorasDeAnticipacionSeProcesan` | LB-05 | El límite: 2 horas exactas sí se confirman y cobran 40 |
| `unaHoraDeAnticipacionRetornaCeroYNoConfirma` | LB-06 | Menos de 2 horas devuelve 0 y no confirma |
| `reservaNulaRetornaCeroSinExcepcion` | Especial | Una reserva nula devuelve 0 y no lanza excepción |
| `mensajesSoloSeMuestranCuandoLaReservaSeConfirma` | Especial | Los mensajes de guardado y correo solo salen si la reserva se confirma |

## Resultado

```text
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Primera refactorización protegida

Se extrajo el cálculo al método `calcularTarifa` con las constantes
`TARIFA_BASE` y `FACTOR_DESCUENTO_VIP`. Las 8 pruebas siguieron en verde.

## Experimento de regresión

Con `FACTOR_DESCUENTO_VIP = 0.80` falla una sola prueba:

```text
reservaVipValidaSeConfirmaYRetorna34:73 expected: <34.0> but was: <32.0>
Tests run: 8, Failures: 1, Errors: 0, Skipped: 0
BUILD FAILURE
```

El cambio se revirtió con `git checkout` y la suite volvió a verde.
