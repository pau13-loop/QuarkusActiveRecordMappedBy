package edu.pingpong.activerecord.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;

import edu.pingpong.activerecord.domain.Band;
import edu.pingpong.activerecord.domain.People;

@ApplicationScoped
public class ServicePeople {
    
    public ServicePeople() {}

    public Set<People> listPeople() {
        Stream<People> people = People.streamAll();
        return people.collect(Collectors.toSet());
    }

    public void addPeople(People people) {
        Optional<Band> band = Band.find("name", people.band.name).firstResultOptional();
        if(band.isPresent()) {
            people.band = band.get();
        } else {
            people.band.persist();
        }
        people.persist();
    }

    public void removePeople(String name) {
        Optional<People> people = People.find("name", name).firstResultOptional();
        if(people.isPresent()) {
            people.get().delete();
        }
    }

    public Optional<People> getPeople(String name) {
        return name.isBlank()? 
        Optional.ofNullable(null) :
        People.find("name", name).firstResultOptional();
    }
}
