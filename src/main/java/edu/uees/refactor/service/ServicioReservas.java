package edu.uees.refactor.service;

import edu.uees.refactor.domain.Reserva;

/**
 * Código heredado intencional para el Laboratorio 1.
 *
 * IMPORTANTE:
 * No refactorizar antes de completar la línea base,
 * el diagnóstico y el plan de refactorización.
 */
public class ServicioReservas {

    private static final double TARIFA_BASE = 40;
    private static final double FACTOR_DESCUENTO_VIP = 0.85;

    public double procesar(
            Reserva r,
            int horasAnticipacion) {

        if (r == null) {
            return 0;
        }

        if (r.getCorreo() == null
                || !r.getCorreo().contains("@")) {
            return 0;
        }

        if (r.getInicio() == null
                || r.getFin() == null
                || !r.getFin().isAfter(r.getInicio())) {
            return 0;
        }

        if (horasAnticipacion < 2) {
            return 0;
        }

        double total = calcularTarifa(r);

        System.out.println(
                "Guardando reserva " + r.getId()
        );

        System.out.println(
                "Correo enviado a " + r.getCorreo()
        );

        r.confirmar();

        return total;
    }

    private double calcularTarifa(Reserva r) {
        if ("VIP".equals(r.getTipo())) {
            return TARIFA_BASE * FACTOR_DESCUENTO_VIP;
        }
        return TARIFA_BASE;
    }
}
