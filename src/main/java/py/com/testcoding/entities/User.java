package py.com.testcoding.entities;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.net.URI;
import java.util.Date;

/**
 * @author Alfredo Cano
 * @since 1.0.0
 */
@Entity
// @Table(name = "user")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
// @NamedQuery(name = User.FIND_ALL, query = "select g from User g")
public class User {

    // public static final String FIND_ALL = "findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date created;

    public User() {}

    public User(@NotNull String name, @NotNull String email, @NotNull Date created) {
        this.name = name;
        this.email = email;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return new StringBuilder("User [")
                .append(id).append(", ")
                .append(name).append(", ")
                .append(created).append("]").toString();
    }

    public JsonObject toJson(URI self) {
        return Json.createObjectBuilder()
                .add("name", this.name)
                .add("created", this.created.toString())
                .add("_links", Json.createObjectBuilder()
                        .add("rel", "self")
                        .add("href", self.toString())
                )
                .build();
    }
}