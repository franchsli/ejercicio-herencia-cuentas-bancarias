package com.uniajc;

public class CuentaAhorros extends Cuenta {

    protected boolean activa = false;

    final float SALDO_MINIMO = 10000f;
    final float COMISION_RETIRO = 1000f;
    final int NUMERO_RETIROS_SIN_COMISION = 4;

    CuentaAhorros(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
        cuentaActivaSaldoMinimo();
    }

    public boolean cuentaActivaSaldoMinimo() {
        if (saldo >= SALDO_MINIMO) {
            activa = true;
        } else {
            activa = false;
        } 
        return activa;
    }

    @Override
    public void consignar(float cantidad) {
        super.consignar(cantidad);
        cuentaActivaSaldoMinimo();
    }

    @Override
    public void retirar(float cantidad) {
        if (activa) {
            super.retirar(cantidad);
            cuentaActivaSaldoMinimo();
        }
    }

    @Override
    public void extractoMensual() {
        if (numeroRetiros > NUMERO_RETIROS_SIN_COMISION) {
            int retirosExcedentes = numeroRetiros - NUMERO_RETIROS_SIN_COMISION;
            comisionMensual += retirosExcedentes * COMISION_RETIRO;
            cuentaActivaSaldoMinimo();
        }
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Cuenta activa: " + activa);
    }
}
