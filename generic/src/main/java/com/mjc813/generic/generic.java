package com.mjc813.generic;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class generic<G> implements GINTERFACE<G>{

    private final List<G> list = new ArrayList<>();
    private final Gson gson = new Gson();

    @Override
    public void add(G item) {
        list.add(item);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public G set(int index, G item) {
        list.set(index, item);
        return item;
    }

    @Override
    public G remove(int index) {
        if (index >= 0 && index < list.size()) {
            return list.remove(index);
        }
        return null;
    }

    @Override
    public G get(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    @Override
    public String getJson(int index) {
        G item = get(index);
        return (item != null) ? gson.toJson(item) : "null";
    }

    @Override
    public String getJsonAllItems() {
        return gson.toJson(list);
    }

}
