package fr.alasdiablo.diolib.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Difine a tuple of 2 value
 *
 * @param <K> Type of the first value
 * @param <V> Type of the seconds value
 */
public record ImmutablePair<K, V>(K key, V value) {

    /**
     * Static factory use to create a pair
     *
     * @param key   first value
     * @param value seconde value
     * @param <K>   Type of the first value
     * @param <V>   Type of the seconds value
     *
     * @return Create a pair containing two value
     */
    @Contract("_, _ -> new")
    public static <K, V> @NotNull ImmutablePair<K, V> of(K key, V value) {
        return new ImmutablePair<>(key, value);
    }

    /**
     * Check if two pair are equals
     *
     * @param o Pair to compare with the current one
     *
     * @return true if it is the same, false in other case
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutablePair<?, ?> that)) return false;
        return key().equals(that.key()) && value().equals(that.value());
    }

    /**
     * Create a hash of the current pair
     *
     * @return A hash build from the two value
     */
    @Override
    public int hashCode() {
        return Objects.hash(key(), value());
    }
}
