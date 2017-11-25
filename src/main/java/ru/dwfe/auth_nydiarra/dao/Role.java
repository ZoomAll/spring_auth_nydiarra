package ru.dwfe.auth_nydiarra.dao;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role
{
    @Id
    @Column
    private String name;

    @Column
    private String description;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}