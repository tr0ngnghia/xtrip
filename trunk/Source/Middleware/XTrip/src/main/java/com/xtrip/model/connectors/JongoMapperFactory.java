package com.xtrip.model.connectors;

import org.jongo.Mapper;
import org.jongo.marshall.jackson.JacksonMapper;

/**
 * @author longnh
 */
public interface JongoMapperFactory {

    /**
     * Create the {@link org.jongo.Mapper} that shall be used by jongo.
     */
    Mapper create();

    static class DefaultFactory implements JongoMapperFactory {
        @Override
        public Mapper create() {
            return new JacksonMapper.Builder().build();
        }
    }

}