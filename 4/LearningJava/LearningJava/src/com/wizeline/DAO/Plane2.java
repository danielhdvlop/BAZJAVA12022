package com.wizeline.DAO;

import java.util.logging.Logger;

public class Plane2 implements ProtoypeImplementation {

    private static final Logger LOGGER = Logger.getLogger(ProtoypeImplementation.class.getName());
    public void createPlane() {
        try {
            Object obj = this.clone();
            LOGGER.info( "Este es el avión 2 <-- Implementando Prototype");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyUser() {
        LOGGER.info( "Esta es notificacion de usuario de avión 2 usando Factor Method");
    }
}
