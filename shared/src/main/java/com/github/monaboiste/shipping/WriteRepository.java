package com.github.monaboiste.shipping;

@SuppressWarnings("squid:S2326")
public interface WriteRepository<I, T> {

    void save(T t);
}
