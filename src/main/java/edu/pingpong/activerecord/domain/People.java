package edu.pingpong.activerecord.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="people")
public class People extends PanacheEntityBase {

    @Id
    @NotBlank(message = "Name can not be blank")
    @Column(unique = true)
    public String name;

    @Column
    // for integers you must use @NotNull
    @NotNull(message = "Age must be specified")
    public int age;

    @ManyToOne
    @JoinColumn(name = "band_name")
    public Band band;

    public People () {}

    public People(String name, int age, Band band) {
        this.name = name;
        this.age = age;
        this.band = band;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Band getBand_name() {
        return band;
    }

    public void setBand_name(Band band) {
        this.band = band;
    }
}
