package fr.alasdiablo.diolib.lang;

import java.util.Objects;

public class ImmutablePair<K, V> {

    private final K key;
    private final V value;

    public ImmutablePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutablePair)) return false;
        ImmutablePair<?, ?> that = (ImmutablePair<?, ?>) o;
        return getKey().equals(that.getKey()) && getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }
}
