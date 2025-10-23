package io.github.mananshr.crud.model;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity
public class User {
    @Id
    private String id;
    private String email;
    private String auth0Id;
    private String name;
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAuth0Id() { return auth0Id; }
    public void setAuth0Id(String auth0Id) { this.auth0Id = auth0Id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
