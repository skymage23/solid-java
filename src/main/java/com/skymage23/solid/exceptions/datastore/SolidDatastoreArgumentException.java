package com.skymage23.solid.exceptions.datastore;
import com.skymage23.solid.exceptions.SolidArgumentException;

public class SolidDatastoreArgumentException extends SolidArgumentException {
    public SolidDatastoreArgumentException(){
        super();
    }

    public SolidDatastoreArgumentException(String message){
        super(message);
    }
}
