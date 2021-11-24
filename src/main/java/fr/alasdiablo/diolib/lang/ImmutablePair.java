package fr.alasdiablo.diolib.lang;

import java.util.Objects;

public record ImmutablePair<K, V>(K key, V value) {

    public static <K, V> ImmutablePair<K, V> of(K key, V value) {
        return new ImmutablePair<>(key, value);
    }

    @Deprecated
    public K getKey() {
        return key;
    }

    @Deprecated
    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutablePair<?, ?> that)) return false;
        return key().equals(that.key()) && value().equals(that.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(key(), value());
    }
}
