package kiev.ua.superLikar01.db.repository;

import kiev.ua.superLikar01.db.entity.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {
    Barcode findByCode(String barcodeCode);

    Barcode findByDescription(String description);
}
