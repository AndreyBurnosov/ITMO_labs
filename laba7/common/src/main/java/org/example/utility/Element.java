package org.example.utility;

import java.io.Serializable;

public abstract class Element implements Comparable<Element>, Serializable {
    abstract public Integer getId();
    abstract public String getName();
}
