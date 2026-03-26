package me.mzalietin.mdbproject.queryservice.domain.model.event;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class ReviewKeyConverter implements Converter<String, ReviewKey> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ReviewKey convert(final String source) {
        return objectMapper.readValue(source, ReviewKey.class);
    }
}
