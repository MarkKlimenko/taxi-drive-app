package systems.vostok.taxi.drive.app.api.response

import groovy.transform.Canonical

@Canonical
class SimpleRequest {
    String state = 'success'
    Object content

    SimpleRequest(Object content) {
        this.content = content
    }
}
