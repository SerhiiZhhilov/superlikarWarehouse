package kiev.ua.superLikar01;

import kiev.ua.superLikar01.db.Repo;
import kiev.ua.superLikar01.db.entity.Users;
import kiev.ua.superLikar01.db.entity.WorkPlace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SuperLikar01ApplicationTests {

	@Autowired
	Repo repo;

	@Test
	void testUsers() {
		repo.getUsersRepository().save(new Users("Serhii", "Zhhilov", "zh@gmail.com", "123"));
		List<Users> usersList = repo.getUsersRepository().findAll();
		assertThat(usersList).hasSize(1);
	}

	@Test
	void testWorkplaces() {
		repo.getWorkPlaceRepository().save(new WorkPlace("Clinic 1", "Test clinic"));
		List<WorkPlace> workPlaces = repo.getWorkPlaceRepository().findAll();
		assertThat(workPlaces).hasSize(1);
		WorkPlace workPlace = workPlaces.get(0);
		assertThat(workPlace.getName()).isEqualTo("Clinic 1");
		repo.getWorkPlaceRepository().delete(workPlace);
		List<WorkPlace> workPlacesAfterDelete = repo.getWorkPlaceRepository().findAll();
		assertThat(workPlacesAfterDelete).hasSize(0);
	}

}
