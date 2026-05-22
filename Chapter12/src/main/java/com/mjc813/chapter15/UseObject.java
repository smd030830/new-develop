package com.mjc813.chapter15;


import lombok.*;

@Getter
@Setter

public class UseObject {
    private String name;
    private String id;

    @Override
    public Object clone() {
        return this;
    }

    @Override
    public boolean equals(Object ob) {
        if (this.hashCode() == ob.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
