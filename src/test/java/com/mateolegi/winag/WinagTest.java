package com.mateolegi.winag;

import com.mateolegi.winag.annotation.Winag;
import com.mateolegi.winag.annotation.Field;

import java.time.LocalDate;

@Winag(title = "Prueba de ventana", width = 400, height = 300)
public class WinagTest {

    @Field(label = "Nombre del cliente: ")
    private String nombreCliente;
    @Field(label = "Producto: ")
    private String producto;
    @Field(label = "Valor: ")
    private String valor;
    @Field(label = "Fecha de compra: ")
    private LocalDate fechaCompra;

    public static void main(String[] args) {
        WinagApplication.run(WinagTest.class, args);
    }
}
