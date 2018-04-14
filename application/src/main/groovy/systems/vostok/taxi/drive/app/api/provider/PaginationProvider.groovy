package systems.vostok.taxi.drive.app.api.provider

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.repository.criteria.QueryPagination

import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.ParamConverterProvider
import javax.ws.rs.ext.Provider
import java.lang.annotation.Annotation
import java.lang.reflect.Type

import static java.lang.String.valueOf

@Provider
class PaginationProvider implements ParamConverterProvider {
    @Override
    <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        QueryPagination.class == type ? new ParamConverter<QueryPagination>() {
            @Override
            QueryPagination fromString(String pagination) {
                pagination ? createPagination(pagination) : null
            }

            @Override
            String toString(QueryPagination t) {
                valueOf(t)
            }
        } as ParamConverter<T> : null
    }

    private static QueryPagination createPagination(String pagination) {
        new JsonSlurper().parseText(pagination) as QueryPagination
    }
}
