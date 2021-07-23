package fr.alasdiablo.diolib.lang;

import java.util.Objects;

public record ImmutablePair<K, V>(K key, V value) {

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutablePair<?, ?> that)) return false;
        return getKey().equals(that.getKey()) && getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }
}
