package com.markklim.taxi.drive.db.migrator

import com.markklim.taxi.drive.db.configuration.MigratorConfiguration

class KeyspaceInitializer {
    static void main(String[] args) {
        try {
            getScript().with { implementPlaceholders(it) }
                    .with { scriptToList(it) }
                    .each { MigratorConfiguration.openSession().execute(it) }
            print 'DONE'
        }
        finally {
            MigratorConfiguration.closeSession()
        }
    }

    static String getScript() {
        File script = new File('db/src/main/resources/scripts/utils/initialize_keyspace.cql')
        script.getText('UTF-8')
    }

    static String implementPlaceholders(String script) {
        script.replace("%schema%", 'tda')
    }

    static List scriptToList(String script) {
        script.trim()
                .replace('\r', '')
                .replace('\n', '')
                .split(';')
                .findAll { it != '' }
    }
}
