package fr.alasdiablo.mods.lib.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents an immutable pair of two values. This class provides a type-safe way to store and handle
 * two related values together as a single unit. Being immutable, once created, the values cannot be modified.
 *
 * @param <K> The type of the first value (key)
 * @param <V> The type of the second value (value)
 */
public record ImmutablePair<K, V>(K key, V value) {

    /**
     * A static factory method that creates a new ImmutablePair instance. This method provides a more
     * readable alternative to using the constructor directly and enables type inference in many cases.
     *
     * @param key   The first value to be stored in the pair (must not be null)
     * @param value The second value to be stored in the pair (must not be null)
     * @param <K>   The type of the first value (key)
     * @param <V>   The type of the second value (value)
     * @return A new ImmutablePair containing the specified key and value
     * @throws NullPointerException if either key or value is null
     */
    @Contract("_, _ -> new")
    public static <K, V> @NotNull ImmutablePair<K, V> of(K key, V value) {
        return new ImmutablePair<>(key, value);
    }

    /**
     * Determines whether the specified object is equal to the current ImmutablePair.
     * Two ImmutablePairs are considered equal if both their keys and values are equal.
     *
     * @param o The object to compare with the current ImmutablePair
     * @return true if the specified object is equal to the current ImmutablePair; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutablePair<?, ?>(Object key1, Object value1))) return false;
        return key().equals(key1) && value().equals(value1);
    }

    /**
     * Generates a hash code for this ImmutablePair. The hash code is calculated
     * using both the key and value components of the pair to ensure a good distribution
     * of hash codes across different pairs.
     *
     * @return An integer hash code representing this ImmutablePair
     */
    @Override
    public int hashCode() {
        return Objects.hash(key(), value());
    }
}