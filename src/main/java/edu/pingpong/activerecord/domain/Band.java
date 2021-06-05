package edu.pingpong.activerecord.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="band")
public class Band extends PanacheEntityBase {

    @Id
    @Column(unique = true)
    @NotBlank(message = "Name can not be blank")
    public String name;

    @Column
    @NotEmpty(message = "Location can not be empty")
    public String location;

    @JsonIgnore
    @OneToMany(mappedBy = "band")
    public Set<People> people = new HashSet<>();

    public Band() {}

    public Band(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
