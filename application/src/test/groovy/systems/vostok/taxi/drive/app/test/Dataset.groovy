package systems.vostok.taxi.drive.app.test

import groovy.json.JsonSlurper

class Dataset {

    /**
     *
     * @param type
     * @param name
     * @return Supports only List or Map return types
     *
     */
    static Object getJsonDataset(String type, String name) {
        Dataset.getClass().getResource("/dataset/${type}/${name}.json").getText('UTF-8')
                .with { new JsonSlurper().parseText(it) }
    }

    static String getRawJsonDataset(String type, String name) {
        Dataset.getClass().getResource("/dataset/${type}/${name}.json").getText('UTF-8')
    }

    static byte[] getFile(String type, String nameWithExtension) {
        Dataset.getClass().getResource("/dataset/${type}/${nameWithExtension}").bytes
    }
}
