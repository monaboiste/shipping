package com.github.monaboiste.shipping;

import java.util.Optional;

public interface ReadRepository<I, T> {

    Optional<T> findById(I id);

    boolean existsById(I id);
}
