package main.java;

import java.io.Serializable;

public class Pair<F, S> implements Serializable  {
    final public F FIRST;
    final public S SECOND;

    public Pair(F first, S second)
    {
        this.FIRST = first;
        this.SECOND = second;
    }
}
