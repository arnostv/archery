package me.arnost.hibernatemetadata;


import javax.persistence.*;

@Entity
@Table(name="person")
public class ClassAnnotatedJpa {
    private int id;
    private String name;
    private String email;

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "SEQ_PERSON_GEN") @SequenceGenerator(name = "SEQ_PERSON_GEN", sequenceName = "SEQ_PERSON")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "EMAIL_ADDRESS")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
