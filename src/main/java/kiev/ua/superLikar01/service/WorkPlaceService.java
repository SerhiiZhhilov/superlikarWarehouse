package kiev.ua.superLikar01.service;

import kiev.ua.superLikar01.db.Repo;
import kiev.ua.superLikar01.db.entity.Users;
import kiev.ua.superLikar01.db.entity.WorkPlace;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkPlaceService {
    private Repo repo;
    private UserService userService;

    public List<WorkPlace> generateWorkPlaces() {
        List<WorkPlace> foundWorkPlaces = repo.getWorkPlaceRepository().findAll();
        if (!foundWorkPlaces.isEmpty()){
            return foundWorkPlaces;
        }
        List<WorkPlace> newWorkPlaces = new ArrayList<>();
        newWorkPlaces.add(new WorkPlace("Clinic 1", "Shevchenko 41"));
        newWorkPlaces.add(new WorkPlace("Clinic 2", "Stolichniy 1"));
        repo.getWorkPlaceRepository().saveAll(newWorkPlaces);
        return newWorkPlaces;
    }

    public Optional<WorkPlace> findWorkPlaceByName(String workplaceName){
        if (workplaceName == null || workplaceName.isEmpty()){
            return null;
        }
        return repo.getWorkPlaceRepository()
                .findAll()
                .stream()
                .filter(wp -> workplaceName.toLowerCase()
                        .contains(wp.getName().toLowerCase()))
                .findAny();
    }

    private void assignUserToWorkplace(Optional<Users> user, Optional<WorkPlace> workPlace){
        if (!user.isPresent() || !workPlace.isPresent()){
            return;
        }
        List<Users> users = workPlace.get().getUsers();
        Optional<Users> anyUserFound = users.stream().filter(u -> u.equals(user)).findAny();
        if (anyUserFound.isPresent()){
            return;
        }
        users.add(user.get());
        repo.getWorkPlaceRepository().save(workPlace.get());
    }

    public void assignsUsersToTheirWorkPlaces() {
        Optional<WorkPlace> clinic1 = findWorkPlaceByName("clinic 1");
        Optional<WorkPlace> clinic2 = findWorkPlaceByName("clinic 2");
        Optional<Users> nata = userService.findUserByName("nata");
        if (nata.isPresent() && clinic2.isPresent()){
            assignUserToWorkplace(nata, clinic2);
        }
        Optional<Users> serhii = userService.findUserByName("serhii");
        if (serhii.isPresent() && clinic1.isPresent()){
            assignUserToWorkplace(serhii, clinic1);
        }
        Optional<Users> irina = userService.findUserByName("Irina");
        if (irina.isPresent() && clinic2.isPresent()){
            assignUserToWorkplace(irina, clinic2);
        }
    }

}
