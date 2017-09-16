package com.markklim.taxi.drive.db.configuration

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session


class MigratorConfiguration {
    private static Cluster cluster = null
    private static Session session = null

    static Session openSession() {
        if(cluster == null ){
            cluster = Cluster.builder()
                    .addContactPoint("127.0.0.1")
                    .build()
            session = cluster.connect()
        }
        session
    }

    static void closeSession() {
        if (cluster != null) {
            session = null
            cluster.close()
            cluster = null
        }
    }
}
