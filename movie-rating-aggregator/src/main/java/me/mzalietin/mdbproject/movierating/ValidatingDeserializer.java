package me.mzalietin.mdbproject.movierating;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Set;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

public class ValidatingDeserializer<T> implements Deserializer<T> {

    private final Deserializer<T> delegate;
    private final Validator validator;

    public ValidatingDeserializer(Deserializer<T> delegate, Validator validator) {
        this.delegate = delegate;
        this.validator = validator;
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        T obj = delegate.deserialize(topic, data);
        return validate(obj);
    }

    @Override
    public T deserialize(final String topic, final Headers headers, final byte[] data) {
        T obj = delegate.deserialize(topic, headers, data);
        return validate(obj);
    }

    @Override
    public T deserialize(final String topic, final Headers headers, final ByteBuffer data) {
        T obj = delegate.deserialize(topic, headers, data);
        return validate(obj);
    }

    private T validate(T obj) {
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if (!violations.isEmpty()) {
            throw new SerializationException("Validation failed: " + violations);
        }
        return obj;
    }

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        delegate.configure(configs, isKey);
    }

    @Override
    public void close() {
        delegate.close();
    }
}
