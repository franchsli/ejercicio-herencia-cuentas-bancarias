package com.uniajc;

public class CuentaCorriente extends Cuenta {

    protected float sobregiro = 0f; 

    CuentaCorriente(float saldo, float tasaAnual) {
        super(saldo, tasaAnual);
    }

    @Override
    public void consignar(float cantidad) {
        if (sobregiro > 0) {
            if (cantidad >= sobregiro) {
                cantidad -= sobregiro;
                sobregiro = 0;
                super.consignar(cantidad);
            } else {
                sobregiro -= cantidad;
            }
        } else {
            super.consignar(cantidad);
        }
    }


    // caso 1;
    // saldo = 100.000
    // retirarMonto = 120.000
    // sobregiro = 20.000

    // caso 2:
    // saldo = 100.000
    // retirarMonto = 80.000
    // sobregiro = 0
    // saldo final = 20.000

    @Override
    public void retirar(float cantidad) {
        if (saldo < cantidad) {
            sobregiro = cantidad - saldo;
            super.retirar(saldo);
        } else {
            super.retirar(cantidad);
        }
    }

    @Override
    public void extractoMensual() {
        super.extractoMensual();
    }

    @Override 
    public void imprimir() {
        super.imprimir();
        System.out.println("Sobregiro: " + sobregiro);
    }

}
