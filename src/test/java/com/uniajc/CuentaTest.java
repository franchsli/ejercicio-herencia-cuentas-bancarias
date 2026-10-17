package com.uniajc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CuentaTest {

    private static final float DELTA = 0.001f;

    @Test
    void inicializaSaldoYTasaAnual() {
        Cuenta cuenta = new Cuenta(1000f, 0.12f);

        assertEquals(1000f, cuenta.saldo, DELTA);
        assertEquals(0.12f, cuenta.tasaAnual, DELTA);
    }

    @Test
    void consignarAumentaSaldoYCuentaLaConsignacion() {
        Cuenta cuenta = new Cuenta(1000f, 0.12f);

        cuenta.consignar(250f);

        assertEquals(1250f, cuenta.saldo, DELTA);
        assertEquals(1, cuenta.numeroConsignaciones);
    }

    @Test
    void consignarCeroNoCambiaSaldoPeroCuentaLaOperacion() {
        Cuenta cuenta = new Cuenta(1000f, 0.12f);

        cuenta.consignar(0f);

        assertEquals(1000f, cuenta.saldo, DELTA);
        assertEquals(1, cuenta.numeroConsignaciones);
    }

    @Test
    void consignarCantidadNegativaLanzaExcepcionYSinCambiarCuenta() {
        Cuenta cuenta = new Cuenta(1000f, 0.12f);

        assertThrows(IllegalArgumentException.class, () -> cuenta.consignar(-250f));

        assertEquals(1000f, cuenta.saldo, DELTA);
        assertEquals(0, cuenta.numeroConsignaciones);
    }

    @Test
    void retirarConSaldoSuficienteActualizaSaldoYContador() {
        Cuenta cuenta = new Cuenta(1000f, 0.12f);

        cuenta.retirar(300f);

        assertEquals(700f, cuenta.saldo, DELTA);
        assertEquals(1, cuenta.numeroRetiros);
    }

    @Test
    void retirarSaldoExactoDejaLaCuentaEnCero() {
        Cuenta cuenta = new Cuenta(1000f, 0.12f);

        cuenta.retirar(1000f);

        assertEquals(0f, cuenta.saldo, DELTA);
        assertEquals(1, cuenta.numeroRetiros);
    }

    @Test
    void retirarMasDelSaldoNoModificaLaCuenta() {
        Cuenta cuenta = new Cuenta(1000f, 0.12f);

        cuenta.retirar(1200f);

        assertEquals(1000f, cuenta.saldo, DELTA);
        assertEquals(0, cuenta.numeroRetiros);
    }

    @Test
    void retirarCeroNoCambiaSaldoPeroCuentaLaOperacionActualmente() {
        Cuenta cuenta = new Cuenta(1000f, 0.12f);

        cuenta.retirar(0f);

        assertEquals(1000f, cuenta.saldo, DELTA);
        assertEquals(1, cuenta.numeroRetiros);
    }

    @Test
    void retirarCantidadNegativaLanzaExcepcionYNoCambiaLaCuenta() {
        Cuenta cuenta = new Cuenta(1234f, 0.12f);

        assertThrows(IllegalArgumentException.class, () -> cuenta.consignar(-1250f));

        assertEquals(1234f, cuenta.saldo, DELTA);
        assertEquals(0, cuenta.numeroConsignaciones);
    }

    @Test
    void calcularInteresAgregaElInteresDeUnMes() {
        Cuenta cuenta = new Cuenta(1200f, 0.12f);

        cuenta.calcularInteres();

        assertEquals(1212f, cuenta.saldo, DELTA);
    }

    @Test
    void calcularInteresConTasaCeroNoCambiaSaldo() {
        Cuenta cuenta = new Cuenta(1200f, 0f);

        cuenta.calcularInteres();

        assertEquals(1200f, cuenta.saldo, DELTA);
    }

    @Test
    void calcularInteresConTasaNegativaReduceSaldoActualmente() {
        Cuenta cuenta = new Cuenta(1200f, -0.12f);

        cuenta.calcularInteres();

        assertEquals(1188f, cuenta.saldo, DELTA);
    }

    @Test
    void extractoMensualAplicaComisionAntesDelInteres() {
        Cuenta cuenta = new Cuenta(1200f, 0.12f);
        cuenta.comisionMensual = 5f;

        cuenta.extractoMensual();

        assertEquals(1206.95f, cuenta.saldo, DELTA);
    }

    @Test
    void extractoMensualConComisionCeroSoloAplicaInteres() {
        Cuenta cuenta = new Cuenta(1200f, 0.12f);

        cuenta.extractoMensual();

        assertEquals(1212f, cuenta.saldo, DELTA);
    }

    @Test
    void extractoMensualConComisionNegativaAumentaSaldoActualmente() {
        Cuenta cuenta = new Cuenta(1200f, 0.12f);
        cuenta.comisionMensual = -5f;

        cuenta.extractoMensual();

        assertEquals(1217.05f, cuenta.saldo, DELTA);
    }

    @Test
    void extractoMensualPuedeDejarSaldoNegativoSiComisionSuperaSaldo() {
        Cuenta cuenta = new Cuenta(100f, 0.12f);
        cuenta.comisionMensual = 101f;

        cuenta.extractoMensual();

        assertEquals(-1.01f, cuenta.saldo, DELTA);
    }
}
