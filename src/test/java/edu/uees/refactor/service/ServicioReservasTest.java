package edu.uees.refactor.service;

import edu.uees.refactor.domain.EstadoReserva;
import edu.uees.refactor.domain.Reserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas de caracterizacion del comportamiento heredado de ServicioReservas.
 *
 * Describen lo que el sistema hace hoy (linea base del Laboratorio 1),
 * no lo que deberia hacer. Cada prueba sigue la estructura AAA.
 */
class ServicioReservasTest {

    private static final LocalDateTime INICIO =
            LocalDateTime.of(2026, 10, 5, 10, 0);

    private final ServicioReservas servicio = new ServicioReservas();

    private final ByteArrayOutputStream consola = new ByteArrayOutputStream();
    private PrintStream salidaOriginal;

    @BeforeEach
    void capturarConsola() {
        salidaOriginal = System.out;
        System.setOut(new PrintStream(consola, true));
    }

    @AfterEach
    void restaurarConsola() {
        System.setOut(salidaOriginal);
    }

    @Test
    @DisplayName("LB-01 | Reserva NORMAL valida se confirma y retorna 40")
    void reservaNormalValidaSeConfirmaYRetorna40() {
        // Arrange
        Reserva reserva = reserva("ana@uees.edu.ec",
                INICIO, INICIO.plusHours(1), "NORMAL");

        // Act
        double total = servicio.procesar(reserva, 5);

        // Assert
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        assertEquals(40, total, 0.001);
    }

    @Test
    @DisplayName("LB-02 | Reserva VIP valida se confirma y retorna 34")
    void reservaVipValidaSeConfirmaYRetorna34() {
        // Arrange
        Reserva reserva = reserva("ana@uees.edu.ec",
                INICIO, INICIO.plusHours(1), "VIP");

        // Act
        double total = servicio.procesar(reserva, 5);

        // Assert
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        assertEquals(34, total, 0.001);
    }

    @Test
    @DisplayName("LB-03 | Correo sin @ retorna 0 y no confirma")
    void correoInvalidoRetornaCeroYNoConfirma() {
        // Arrange
        Reserva reserva = reserva("incorrecto",
                INICIO, INICIO.plusHours(1), "NORMAL");

        // Act
        double total = servicio.procesar(reserva, 5);

        // Assert
        assertEquals(0, total, 0.001);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    @DisplayName("LB-04 | Periodo con fin igual al inicio retorna 0 y no confirma")
    void periodoInvalidoRetornaCeroYNoConfirma() {
        // Arrange
        Reserva reserva = reserva("ana@uees.edu.ec",
                INICIO, INICIO, "NORMAL");

        // Act
        double total = servicio.procesar(reserva, 5);

        // Assert
        assertEquals(0, total, 0.001);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    @DisplayName("LB-05 | Dos horas exactas de anticipacion se procesan")
    void dosHorasDeAnticipacionSeProcesan() {
        // Arrange
        Reserva reserva = reserva("ana@uees.edu.ec",
                INICIO, INICIO.plusHours(1), "NORMAL");

        // Act
        double total = servicio.procesar(reserva, 2);

        // Assert
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        assertEquals(40, total, 0.001);
    }

    @Test
    @DisplayName("LB-06 | Una hora de anticipacion retorna 0 y no confirma")
    void unaHoraDeAnticipacionRetornaCeroYNoConfirma() {
        // Arrange
        Reserva reserva = reserva("ana@uees.edu.ec",
                INICIO, INICIO.plusHours(1), "NORMAL");

        // Act
        double total = servicio.procesar(reserva, 1);

        // Assert
        assertEquals(0, total, 0.001);
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
    }

    @Test
    @DisplayName("Especial | Reserva nula retorna 0 sin lanzar excepcion")
    void reservaNulaRetornaCeroSinExcepcion() {
        // Arrange
        Reserva reserva = null;

        // Act y Assert
        double total = assertDoesNotThrow(
                () -> servicio.procesar(reserva, 5));
        assertEquals(0, total, 0.001);
    }

    @Test
    @DisplayName("Especial | Los mensajes de guardado y correo solo salen al confirmar")
    void mensajesSoloSeMuestranCuandoLaReservaSeConfirma() {
        // Arrange
        Reserva valida = new Reserva("R-010", "ana@uees.edu.ec",
                INICIO, INICIO.plusHours(1), "NORMAL");
        Reserva invalida = new Reserva("R-011", "ana@uees.edu.ec",
                INICIO, INICIO.plusHours(1), "NORMAL");

        // Act
        servicio.procesar(invalida, 1);
        servicio.procesar(valida, 5);

        // Assert
        String salida = consola.toString();
        assertTrue(salida.contains("Guardando reserva R-010"));
        assertTrue(salida.contains("Correo enviado a ana@uees.edu.ec"));
        assertFalse(salida.contains("R-011"));
    }

    private static Reserva reserva(
            String correo,
            LocalDateTime inicio,
            LocalDateTime fin,
            String tipo) {
        return new Reserva("R-001", correo, inicio, fin, tipo);
    }
}
