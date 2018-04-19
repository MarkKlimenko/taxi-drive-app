package systems.vostok.taxi.drive.app.api.provider

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.dao.repository.util.SearchParameters

import javax.ws.rs.ext.ParamConverter
import javax.ws.rs.ext.ParamConverterProvider
import javax.ws.rs.ext.Provider
import java.lang.annotation.Annotation
import java.lang.reflect.Type

import static java.lang.String.valueOf

@Provider
class SearchParametersProvider implements ParamConverterProvider {
    @Override
    <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        SearchParameters.class == type ? new ParamConverter<SearchParameters>() {
            @Override
            SearchParameters fromString(String parameters) {
                parameters ? createFilter(parameters) : null
            }

            @Override
            String toString(SearchParameters parameters) {
                valueOf(parameters)
            }
        } as ParamConverter<T> : null
    }

    private static SearchParameters createFilter(String parameters) {
        new JsonSlurper().parseText(parameters) as SearchParameters
    }
}
