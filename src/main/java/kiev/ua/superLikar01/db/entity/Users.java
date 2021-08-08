package kiev.ua.superLikar01.db.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    //@OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    //@JoinColumn(name = "work_place_id")
    //private WorkPlace workPlace;

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id")
    //private List<Phone> phones = new ArrayList<>();


    public Users(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


   /* public void assignPhone(Phone phone) {
        this.phones.add(phone);
    }*/
}
