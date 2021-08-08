package kiev.ua.superLikar01.service;

import kiev.ua.superLikar01.db.Repo;
import kiev.ua.superLikar01.db.entity.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private Repo repo;

    public List<Users> generatesUsers() {
        List<Users> foundUsersList = repo.getUsersRepository().findAll();
        if (!foundUsersList.isEmpty()){
            return foundUsersList;
        }
        List<Users> newUsers = new ArrayList<>();
        newUsers.add(new Users("Serhii", "Zhhilov", "serhiizhhilov@gmail.com", "123"));
        newUsers.add(new Users("Natalia", "Zhhilova", "dr.zhhilova@gmail.com", "123"));
        newUsers.add(new Users("Irina", "Lavrenchuk", "smurfetka@gmail.com", "123"));
        repo.getUsersRepository().saveAll(newUsers);
        return newUsers;
    }

    public Optional<Users> findUserByName(String username){
        return repo.getUsersRepository()
                .findAll()
                .stream()
                .filter(u -> u.getFirstName().toLowerCase().contains(username.toLowerCase()))
                .findAny();
    }

    public List<Users> getAllUsers() {
        return repo.getUsersRepository().findAll();
    }
}
