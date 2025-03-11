package com.github.monaboiste.shipping;

public interface WriteRepository<I, T> {

    void save(T t);
}
