package ru.dwfe.auth_jwt.dao;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority
{
    @Id
    @Column
    private String authority;

    @Column
    private String description;

    @Override
    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return authority.equals(role.authority);
    }

    @Override
    public int hashCode()
    {
        return authority.hashCode();
    }

    @Override
    public String toString()
    {
        return "Role{" +
                "authority='" + authority + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}