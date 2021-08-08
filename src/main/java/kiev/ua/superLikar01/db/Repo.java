package kiev.ua.superLikar01.db;

import kiev.ua.superLikar01.db.repository.BarcodeRepository;
import kiev.ua.superLikar01.db.repository.BarcodeWarehouseRepository;
import kiev.ua.superLikar01.db.repository.UsersRepository;
import kiev.ua.superLikar01.db.repository.WorkPlaceRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
@Getter
public class Repo {
    private UsersRepository usersRepository;
    private WorkPlaceRepository workPlaceRepository;
    private BarcodeRepository barcodeRepository;
    private BarcodeWarehouseRepository barcodeWarehouseRepository;
}

