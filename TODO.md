### Hibernate
- Create typeless DAO implementation
- Add pagination

### Redis
- Implement versioned Entity representation
- Add redis cache

### Security
- Add Spring security @PreFilter @PostFilter on universal crud methods

### Metrics, statistic
+ Add flyway with actuator

### Other
+ Move all constant interfaces to classes

### Docker
+ Prepare appropriate compose files

### Security
+ Add Keycloak SSO to compose
- Create migrators for SSO and others 
+ Connect SSO/Spring security


# Global
+ Primary test flow (CI ++)
    + Integration
    + Unit
    + CI 
        + DB checker
        * Run services from compose file
        + Subsequent stage fail
 
- Function flow
    - Crud entities using operations
        - Clients
            + Implement universal Entity Operations
            - Create feature tests for Client Flow
        - Rides
    - Operation executor
        + Change operation executor workflow
        + Add Operation to Entity relation using entityId
        * Create Entity to Operation table 
        - Add circuit breaker
        - Use OperationRequest/Response with Avro  
    - Implement rate flow
        - Load rates using operation
        - Get rate info flow
    - Ride flow
        - Create ride
        - Change ride status
        - Close ride
        - Active rides (Store in Redis)
        - Pending rides (Akka for changing status to active)   
     
    
- Create good readme and TODO list  

# Minor 
- Sonar for groovy 