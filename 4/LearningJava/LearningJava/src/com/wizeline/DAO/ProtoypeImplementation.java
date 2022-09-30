package com.wizeline.DAO;

public interface ProtoypeImplementation {

    void createPlane() throws CloneNotSupportedException;

    public static int suma(int n1, int n2){
        int op;
        op = n1 + n2;
        return op;
    }
    public static int suma(int n1, int n2, int n3){
        int op;
        op = n1 + n2 + n3;
        return op;
    }

    void notifyUser();
}
