package fr.christopheml.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class EqualsBuilder<T> {

    private final T value;

    private final List<Function<T, Object>> providers = new ArrayList<>();

    private EqualsBuilder(T value) {
        this.value = value;
    }

    /**
     * Creates a new instance of EqualsBuilder.
     *
     * @param value Object we want to implement equals() for, must be non-null
     * @param <T> Type of the objects to test for equality
     * @return a new instance of EqualsBuilder for the object implementing equals()
     */
    public static <T> EqualsBuilder<T> compare(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return new EqualsBuilder<>(value);
    }

    /**
     * Adds to the equality test a function that exposes a field of the objects being tested.
     * <p>
     * Most of the time, a method reference to a getter will be provided, e.g. :
     *      {@code EqualsBuilder.compare(this).using(MyBean::getName).with(other)}
     * </p>
     *
     * @param fieldValueProvider function that returns the value of a field being used in the equality test
     * @return this instance of EqualsBuilder
     */
    public EqualsBuilder<T> using(Function<T, Object> fieldValueProvider) {
        providers.add(fieldValueProvider);
        return this;
    }

    /**
     * Evalutates the equality of the previously set object with another object.
     *
     * @param other the other object we test equality with.
     * @return {@code true} if both objects are equal, {@code otherwise}
     */
    public boolean with(Object other) {
        if (value == other) {
            return true;
        }

        if (other == null || value.getClass() != value.getClass()) {
            return false;
        }

        return providers.stream()
                .allMatch(fieldProvider -> Objects.equals(fieldProvider.apply(value), fieldProvider.apply((T) other)));
    }

}
