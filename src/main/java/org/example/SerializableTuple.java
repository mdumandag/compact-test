package org.example;

import java.io.Serializable;

public class SerializableTuple<E0, E1> implements Serializable {

    private final E0 e0;
    private final E1 e1;

    public SerializableTuple(E0 e0, E1 e1) {
        this.e0 = e0;
        this.e1 = e1;
    }

    public E0 getE0() {
        return e0;
    }

    public E1 getE1() {
        return e1;
    }

}
