# DB module
### PostgreSQL DB

**Execute SQL migrations**
<pre>
gradlew flywayMigrate -PflywayUrl=jdbc:postgresql://192.168.99.100:5432/tda 
                      -PflywayUser=tda_example
                      -PflywayPassword=12345
</pre>