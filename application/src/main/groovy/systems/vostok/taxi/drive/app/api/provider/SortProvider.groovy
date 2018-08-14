package systems.vostok.taxi.drive.app.api.provider

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.domain.query.QuerySorter

import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.ParamConverterProvider
import javax.ws.rs.ext.Provider
import java.lang.annotation.Annotation
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

import static java.lang.String.valueOf

@Provider
class SortProvider implements ParamConverterProvider {
    @Override
    <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        isCollectionOfFilters(aClass, type) ? new ParamConverter<List<QuerySorter>>() {
            @Override
            List fromString(String filters) {
                filters ? createFilter(filters) : null
            }

            @Override
            String toString(List t) {
                valueOf(t)
            }
        } as ParamConverter<T> : null
    }

    // TODO: Refactor this
    private static <T> boolean isCollectionOfFilters(Class<T> rawType, Type type) {
        Collection.class.isAssignableFrom(rawType) && QuerySorter.class == ((ParameterizedType) type).getActualTypeArguments()[0]
    }

    private static List createFilter(String filters) {
        new JsonSlurper().parseText(filters)
                .collect { it as QuerySorter }
    }
}
