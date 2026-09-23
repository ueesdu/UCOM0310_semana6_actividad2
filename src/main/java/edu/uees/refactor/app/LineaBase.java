package edu.uees.refactor.app;

import edu.uees.refactor.domain.Reserva;
import edu.uees.refactor.service.ServicioReservas;

import java.time.LocalDateTime;

/**
 * Ejecuciones controladas para la linea base del Laboratorio 1.
 *
 * Llama al servicio real con las seis entradas del enunciado y no
 * duplica ninguna regla. No modifica el diseno heredado.
 */
public class LineaBase {

    public static void main(String[] args) {

        LocalDateTime inicio =
                LocalDateTime.now().plusDays(1);

        ejecutar("LB-01 | NORMAL valida",
                new Reserva("R-001", "ana@uees.edu.ec",
                        inicio, inicio.plusHours(1), "NORMAL"), 5);

        ejecutar("LB-02 | VIP valida",
                new Reserva("R-002", "ana@uees.edu.ec",
                        inicio, inicio.plusHours(1), "VIP"), 5);

        ejecutar("LB-03 | Correo invalido",
                new Reserva("R-003", "incorrecto",
                        inicio, inicio.plusHours(1), "NORMAL"), 5);

        ejecutar("LB-04 | Periodo invalido (fin <= inicio)",
                new Reserva("R-004", "ana@uees.edu.ec",
                        inicio, inicio, "NORMAL"), 5);

        ejecutar("LB-05 | Limite valido, 2 horas",
                new Reserva("R-005", "ana@uees.edu.ec",
                        inicio, inicio.plusHours(1), "NORMAL"), 2);

        ejecutar("LB-06 | Limite invalido, 1 hora",
                new Reserva("R-006", "ana@uees.edu.ec",
                        inicio, inicio.plusHours(1), "NORMAL"), 1);
    }

    private static void ejecutar(
            String escenario,
            Reserva reserva,
            int horasAnticipacion) {

        System.out.println("== " + escenario + " ==");
        try {
            double total = new ServicioReservas()
                    .procesar(reserva, horasAnticipacion);
            System.out.println("Estado: " + reserva.getEstado());
            System.out.println("Retorno: " + total);
        } catch (RuntimeException e) {
            System.out.println("Excepcion: " + e);
        }
        System.out.println();
    }
}
