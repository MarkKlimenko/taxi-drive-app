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
        Dataset.getClass().getResource("/dataset/${type}/${name}.json").text
                .with { new JsonSlurper().parseText(it) }
    }

    static String getRawJsonDataset(String type, String name) {
        Dataset.getClass().getResource("/dataset/${type}/${name}.json").text
    }
}
