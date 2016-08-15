package ua.challenge.hibernate.examples.relationship.many.to.many.bidirectional.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by d.bakal on 8/15/2016.
 */
@Entity
@Table(name = "cds")
public class CD {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private Float price;
    private String description;

    @ManyToMany(mappedBy = "appearsOnCDs")
    private List<Artist> createdByArtists;

    public CD() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Artist> getCreatedByArtists() {
        return createdByArtists;
    }

    public void setCreatedByArtists(List<Artist> createdByArtists) {
        this.createdByArtists = createdByArtists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CD cd = (CD) o;

        if (id != cd.id) return false;
        if (title != null ? !title.equals(cd.title) : cd.title != null) return false;
        if (price != null ? !price.equals(cd.price) : cd.price != null) return false;
        if (description != null ? !description.equals(cd.description) : cd.description != null) return false;
        return !(createdByArtists != null ? !createdByArtists.equals(cd.createdByArtists) : cd.createdByArtists != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdByArtists != null ? createdByArtists.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CD{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", createdByArtists=" + createdByArtists +
                '}';
    }
}
