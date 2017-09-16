package com.markklim.taxi.drive.db.executor

import com.markklim.taxi.drive.db.configuration.Migrator
import groovy.io.FileType

import static com.markklim.taxi.drive.db.utils.ScriptUtilities.*

class ScriptExecutor {
    static void main(String[] args) {
            print args

        //executeScript('db/src/main/resources/scripts/utils/initialize_keyspace.cql')
        //executeScript('db/src/main/resources/scripts/migrations')
    }

    static void executeScript(String scriptSource) {
        try {
            File source = new File(scriptSource)
            if (source.isDirectory()) {
                source.eachFile(FileType.FILES) {
                    executeFile(it)
                }
            } else {
                executeFile(source)
            }
        }
        finally {
            Migrator.closeSession()
        }
    }

    static void executeFile(File source){
        getScriptFromFile(source)
                .with { implementPlaceholders(it) }
                .with { scriptToList(it) }
                .each { executeLine(it) }
        print "Script executed: file ${source.getName()}"
    }

    static void executeLine(String scriptLine) {
       Migrator.openSession().execute(scriptLine)
       // println scriptLine
    }


    // Print response of executions
    // Parameters
    // Ignore comments multi lines
    // Fail after wrong execution or not
}
