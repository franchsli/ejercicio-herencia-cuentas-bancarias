package com.uniajc;

public class Cuenta {

    protected float saldo;
    protected int numeroConsignaciones = 0;
    protected int numeroRetiros = 0;
    protected float tasaAnual;
    protected float comisionMensual;

    Cuenta(float saldo, float tasaAnual) {
        this.saldo = saldo;
        this.tasaAnual = tasaAnual;
    }

    public void consignar(float cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a consignar no puede ser negativa");
        }
        saldo += cantidad;
        numeroConsignaciones++;
    }

    public void retirar(float cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a retirar no puede ser negativa");
        }
        if (cantidad <= saldo) {
            saldo -= cantidad;
            numeroRetiros++;
        }
    }

    public void calcularInteres() {
        float tasaMensual = tasaAnual / 12;
        float interesMensual = saldo * tasaMensual;
        saldo += interesMensual;
    }

    public void extractoMensual() {
        saldo -= comisionMensual;
        calcularInteres();
    }

    public void imprimir() {
        System.out.println("Saldo: " + saldo);
        System.out.println("Número de consignaciones: " + numeroConsignaciones);
        System.out.println("Número de retiros: " + numeroRetiros);
        System.out.println("Numero de transacciones: " + (numeroConsignaciones + numeroRetiros));
        System.out.println("Tasa anual: " + tasaAnual);
        System.out.println("Comisión mensual: " + comisionMensual);
    }

}
