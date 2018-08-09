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
- Primary test flow (CI ++)
    + Integration
    + Unit
    - CI 
        - DB checker
        - Subsequent stage fail
 
- Function flow
    - Create entities using operations
    - Implement rate flow
    - Ride flow
        - Create ride
        - Change ride status
        - Close ride
        - Active rides (Store in Redis)
        - Pending rides (Akka for changing status to active)   
     
    
- Create good readme and TODO list     