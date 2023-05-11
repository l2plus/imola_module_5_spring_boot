# imola_module_5_spring_boot
Tasks for Spring Boot module #5 for L2+ mentoring program

# Task 1  --- UPLOADED
### Hello-world application
*Cost: 1 points.*

- Using https://start.spring.io create a Spring-boot app. -- done
- Create CommandLineRunner and output 'hello world'. -- done
- Start your application.  -- done
- Check that spring context is up and there is 'hello world' message in console.  -- done

# Task 2 
### CRUD REST application
*Cost: 1 points.*

- Create app that should support create, read, update and delete operations for some entity
- Use Spring Data module -- done
- Don't use Spring Data REST starter -- done

# Task 3 
### CRUD application: security
*Cost: 1 points.*

- Implement authentication and authorization mechanism -- done
- OAuth2 should be used -- done
- JWT Token should be used

# Task 4 (Optional)
### CRUD application: externalized configuration
*Cost: 1 points.*

- Should support different environments - local, dev, stg, prod
- Spring profiles
- Each environment - different db properties

# Task 5
### CRUD application: data migrating
*Cost: 1 points.*

- Add tool for migrating data
- Flyway or Liquibase

# Task 6 (Optional) 
### CRUD application: actuator
*Cost: 1 points.*

- Enable actuator
- Implement a few custom health indicators
- Implement a few custom metrics using Prometheus

# Task 7 
### CRUD application: testing
*Cost: 1 points.*

- In memory db must be used for testing purpose - done
- Implement repository testing
- Implement unit tests
- Implement tests for RestController using mock mvc
- Implement integration tests -- partial
