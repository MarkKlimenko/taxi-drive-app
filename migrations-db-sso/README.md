# Migrations DB sso
Need for default realm, client, user creation

**WARN:**
- Useful for first migration
- Don`t execute clean!

## PostgreSQL DB

### Prepare environment
<pre>
    gradlew flywayMigrate
</pre>

### Execute migrations
**Execute SQL migrations**
<pre>
    gradlew flywayMigrate -PflywayUrl=jdbc:postgresql://localhost:5432/tda 
                          -PflywayUser=tda
                          -PflywayPassword=123456
</pre>

### Useful commands
**Clean/migrate**
<pre>
    gradlew flywayMigrate
</pre