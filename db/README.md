# DB module
## PostgreSQL DB

### Prepare environment
<pre>
    gradlew flywayClean flywayMigrate
</pre>

### Execute migrations
**Execute clean**
<pre>
    gradlew flywayClean -PflywayUrl=jdbc:postgresql://localhost:5432/tda 
                        -PflywayUser=tda
                        -PflywayPassword=123456
</pre>


**Execute SQL migrations**
<pre>
    gradlew flywayMigrate -PflywayUrl=jdbc:postgresql://localhost:5432/tda 
                          -PflywayUser=tda
                          -PflywayPassword=123456
</pre>

### Useful commands
**Clean/migrate**
<pre>
    gradlew flywayClean flywayMigrate
</pre