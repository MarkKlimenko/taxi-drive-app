package systems.vostok.taxi.drive.app.util.exception

import systems.vostok.taxi.drive.app.util.constant.ContentTypes

class ContentTypeException extends RuntimeException {
    ContentTypeException(String message) {
        super(message)
    }

    ContentTypeException(Throwable cause) {
        super(cause)
    }


    static ContentTypeException contentTypeException(ContentTypes targetContentType) {
        new ContentTypeException("Can not convert content type to: { ${targetContentType.name} }")
    }
}

