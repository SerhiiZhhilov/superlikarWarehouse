package kiev.ua.superLikar01.db.repository;

import kiev.ua.superLikar01.db.entity.Barcode;
import kiev.ua.superLikar01.db.entity.BarcodeWarehouse;
import kiev.ua.superLikar01.db.entity.Users;
import kiev.ua.superLikar01.db.entity.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BarcodeWarehouseRepository extends JpaRepository<BarcodeWarehouse, Long> {

    BarcodeWarehouse findByBarcodeAndUserAssignedAndWorkPlaceAssigned(Barcode barcode, Users userAssigned, WorkPlace workPlaceAssigned);
}
