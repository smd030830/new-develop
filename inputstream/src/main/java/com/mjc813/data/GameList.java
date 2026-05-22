package com.mjc813.data;

import java.util.ArrayList;
import java.util.List;


public class GameList<G> {
    private List<G> list;

    public GameList() {
        this.list = new ArrayList<>();
    }

    public G add(G item) {
        if ( this.list.add(item) ) {
            return item;
        } else {
            return null;
        }
    }

    public G remove(int index) {
        if ( index <= -1 || index >= this.list.size() ) {
            return null;
        }
        return this.list.remove(index);
    }
}
