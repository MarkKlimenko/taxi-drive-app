package systems.vostok.taxi.drive.app.util

import groovy.json.JsonSlurper
import systems.vostok.taxi.drive.app.util.constant.ContentTypes

import static systems.vostok.taxi.drive.app.util.exception.ContentTypeException.contentTypeException

class ContentTypeConverter {
    static Map toMap(Object content) {
        if (content instanceof Map) {
            content
        } else if (content instanceof String) {
            Object parsedContent = new JsonSlurper().parseText(content)
            if (parsedContent instanceof Map) {
                return parsedContent
            } else {
                throw contentTypeException(ContentTypes.MAP)
            }
        } else {
            throw contentTypeException(ContentTypes.MAP)
        }
    }
}
