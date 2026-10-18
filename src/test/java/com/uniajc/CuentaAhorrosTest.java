package com.uniajc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CuentaAhorrosTest {

    @Test
    void cuentaConSaldoMinimoEsActivadaAlCrearse(){
        CuentaAhorros cuentaAhorros = new CuentaAhorros(10_000f, 0);
        assertTrue(cuentaAhorros.activa);
    }

    @Test
    void cuentaSinSaldoMinimoSigueInactivaAlCrearse(){
        CuentaAhorros cuentaAhorros = new CuentaAhorros(9999.99f, 0);
        assertFalse(cuentaAhorros.activa);
    }

    @Test 
    void consignarACuentaInactivaAumentaSaldo(){
        CuentaAhorros cuentaAhorros = new CuentaAhorros(100f, 0);
        cuentaAhorros.consignar(10_000f);
        assertEquals(10_100f, cuentaAhorros.saldo);
    } 

    @Test 
    void consignarLoSuficienteACuentaInactivaLaActiva(){
        CuentaAhorros cuentaAhorros = new CuentaAhorros(1f, 0);
        cuentaAhorros.consignar(9999f);
        assertTrue(cuentaAhorros.activa);
    } 

    @Test 
    void retirarACuentaActivaDisminuyeSaldo(){
        CuentaAhorros cuentaAhorros = new CuentaAhorros(100_000f, 0);
        cuentaAhorros.retirar(10_000f);
        assertEquals(90_000f, cuentaAhorros.saldo);
    }

    @Test 
    void retirarACuentaInactivaNoDisminuyeSaldo(){
        CuentaAhorros cuentaAhorros = new CuentaAhorros(100f, 0);
        cuentaAhorros.retirar(100_000f);
        assertEquals(100f, cuentaAhorros.saldo);
    }

    @Test
    void extractoMensualDisminuyeSaldoSiNumRetirosMayorQueRetirosSinComision(){
        CuentaAhorros cuentaAhorros = new CuentaAhorros(100_000f, 0);
        cuentaAhorros.numeroRetiros = 5;
        cuentaAhorros.extractoMensual();
        assertTrue(cuentaAhorros.saldo < 100_000f);
    }

    @Test
    void extractoMensualNoDisminuyeSaldoSiNumRetirosNoSuperaRetirosSinComision(){
        CuentaAhorros cuentaAhorros = new CuentaAhorros(100_000f, 0);
        cuentaAhorros.numeroRetiros = 4;
        cuentaAhorros.extractoMensual();
        assertEquals(100_000f, cuentaAhorros.saldo);
    }

}
