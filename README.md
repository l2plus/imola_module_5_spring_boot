# imola_module_5_spring_boot
Tasks for Spring Boot module #5 for L2+ mentoring program

# Task 1  --- UPLOADED
### Hello-world application
*Cost: 1 points.*

- Using https://start.spring.io create a Spring-boot app. -- done
- Create CommandLineRunner and output 'hello world'. -- done
- Start your application.  -- done
- Check that spring context is up and there is 'hello world' message in console.  -- done

# Task 2 --- UPLOADED
### CRUD REST application 
*Cost: 1 points.*

- Create app that should support create, read, update and delete operations for some entity -- done
- Use Spring Data module -- done
- Don't use Spring Data REST starter -- done

# Task 3 --- UPLOADED
### CRUD application: security 
*Cost: 1 points.*

- Implement authentication and authorization mechanism -- done
- OAuth2 should be used -- done
- JWT Token should be used -- done

# Task 4 (Optional) --- UPLOADED
### CRUD application: externalized configuration 
*Cost: 1 points.*

- Should support different environments - local, dev, stg, prod -- done (with security profiles instead)
- Spring profiles -- done
- Each environment - _different db properties: different security authentication profiles_

# Task 5 -- UPLOADED
### CRUD application: data migrating 
*Cost: 1 points.*

- Add tool for migrating data -- done
- Flyway or Liquibase -- done

# Task 6 (Optional) 
### CRUD application: actuator
*Cost: 1 points.*

- Enable actuator -- partial
- ---->  Implement a few custom health indicators
- ---->  Implement a few custom metrics using Prometheus

# Task 7 --- UPLOADED
### CRUD application: testing
*Cost: 1 points.*

- In memory db must be used for testing purpose - done
- Implement repository testing -- some @DataJpaTest methods implemented to check repository mechanisms -- done
- Implement unit tests  -- done
- Implement tests for RestController using mock mvc -- done
- Implement integration tests -- done
