package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Animal {
    @Id
    private int id;
    private String bio;
    private String city;
    private String name;
    @ManyToMany(mappedBy = "animals")
    private Collection<Party> parties;

    public Animal() {
    }

    public Animal(int id, String bio, String city, String name, Collection<Party> parties) {
        this.id = id;
        this.bio = bio;
        this.city = city;
        this.name = name;
        this.parties = parties;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Party> getParties() {
        return parties;
    }

    public void setParties(Collection<Party> parties) {
        this.parties = parties;
    }


}
