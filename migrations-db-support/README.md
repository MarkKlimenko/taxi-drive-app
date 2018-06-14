# Migrations DB support
## Cassandra DB

### Prepare environment
<pre>
    gradlew migratorExecute
</pre>

### Execute migrations
**Commands:**
<pre>
    gradlew migratorDropKeyspace
    gradlew migratorExecute
</pre>

**Parameters:**
<pre>
    -PmigratorHost=
    -PmigratorUser= 
    -PmigratorPassword= 
    -PmigratorKeyspace=
    -PmigratorScript=
</pre>

### Useful commands 
**Examples:**
<pre>
    gradlew migratorDropKeyspace -PmigratorUser=cassandra -PmigratorPassword=cassandra 
    gradlew migratorExecute -PmigratorUser=cassandra -PmigratorPassword=cassandra -PmigratorScript=db/src/main/resources/scripts/migrations
    
    gradlew migratorExecute -PmigratorHost=192.168.99.100 -PmigratorScript=db/src/main/resources/scripts/migrations
    gradlew migratorDropKeyspace -PmigratorHost=192.168.99.100
</pre>
