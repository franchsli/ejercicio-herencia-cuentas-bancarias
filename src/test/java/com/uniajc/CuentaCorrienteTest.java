package com.uniajc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CuentaCorrienteTest {

    @Test
    void consignaSinSobregiroAumentaSaldoFinal(){
        CuentaCorriente cuentaCorriente = new CuentaCorriente(10_000f, 0);
        cuentaCorriente.consignar(20_000f);

        assertEquals(30_000f, cuentaCorriente.saldo);
    }

    @Test
    void consignaConSobregiroRestaSaldoAumentado(){
        CuentaCorriente cuentaCorriente = new CuentaCorriente(0f, 0);
        cuentaCorriente.sobregiro = 10_000f;
        cuentaCorriente.consignar(20_000f);

        assertEquals(10_000f, cuentaCorriente.saldo);
    }

    @Test
    void consignaConSobregiroRestaSaldoAumentadoTotalmente(){
        CuentaCorriente cuentaCorriente = new CuentaCorriente(0f, 0);
        cuentaCorriente.sobregiro = 10_000f;
        cuentaCorriente.consignar(5000f);

        assertEquals(0f, cuentaCorriente.saldo);
        assertEquals(5000f, cuentaCorriente.sobregiro);
    }

    @Test
    void consignaMasQueSobregiroRestaSobregiroCompletamente(){
        CuentaCorriente cuentaCorriente = new CuentaCorriente(0f, 0);
        cuentaCorriente.sobregiro = 5000f;
        cuentaCorriente.consignar(20_000f);

        assertEquals(15_000f, cuentaCorriente.saldo);
        assertEquals(0f, cuentaCorriente.sobregiro);
    }

}
