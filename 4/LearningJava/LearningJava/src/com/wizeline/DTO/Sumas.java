package com.wizeline.DTO;

import java.util.Map;

public class Sumas {
    private int dato1;
    private int dato2;
    private int dato3;



    public int getDato1(){return dato1;}
    public int getDato2(){return dato2;}
    public int getDato3(){return dato3;}
    public void setdato1 (int dato1) {this.dato1 = dato1;}
    public void setdato2 (int dato2) {this.dato2 = dato2;}
    public void setdato3 (int dato3) {this.dato3 = dato3;}

    public Sumas getParameters(Map<String, String> userParam) {
        Sumas suma = new Sumas();
        suma.setdato1(Integer.parseInt(userParam.get("suma1")));
        suma.setdato2(Integer.parseInt(userParam.get("suma3")));
        suma.setdato3(Integer.parseInt(userParam.get("suma2")));
        return suma;
    }
}
