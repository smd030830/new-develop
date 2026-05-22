package com.mjc813.crud;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class CrudClass<A> implements CRUD<A> {
    private final List<A> list = new ArrayList<>();
    private final Gson gson = new Gson();

    @Override
    public void add(A item) {
        list.add(item);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public A set(int index, A item) {
            list.set(index, item);
            return item;
    }

    @Override
    public A remove(int index) {
        if (index >= 0 && index < list.size()) {
            return list.remove(index);
        }
        return null;
    }

    @Override
    public A get(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    @Override
    public String getJson(int index) {
        A item = get(index);
        return (item != null) ? gson.toJson(item) : "null";
    }

    @Override
    public String getJsonAllItems() {
        return gson.toJson(list);
    }
}