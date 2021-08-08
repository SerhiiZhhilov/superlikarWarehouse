package kiev.ua.superLikar01.service;

import kiev.ua.superLikar01.db.Repo;
import kiev.ua.superLikar01.db.entity.Barcode;
import kiev.ua.superLikar01.db.entity.BarcodeWarehouse;
import kiev.ua.superLikar01.db.entity.Users;
import kiev.ua.superLikar01.db.entity.WorkPlace;
import kiev.ua.superLikar01.db.type.BarcodeStatusType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BarcodeService {
    private Repo repo;

    public List<Barcode> getAllBarcodes() {
        return repo.getBarcodeRepository().findAll();
    }

    public Optional<Barcode> addBarcode(String barcodeCode, String description, Optional<Users> user, Optional<WorkPlace> workPlace){
        if (!user.isPresent() || !workPlace.isPresent()){
            return Optional.empty();
        }
        Optional<Barcode> foundBarcode = Optional.ofNullable(repo.getBarcodeRepository().findByCode(barcodeCode));
        if (foundBarcode.isPresent()){
            return foundBarcode;
        }
        Barcode barcode = new Barcode(barcodeCode, description);
        BarcodeWarehouse barcodeWarehouse = new BarcodeWarehouse(new Date(), barcode, user.get(), workPlace.get());
        repo.getBarcodeRepository().save(barcode);
        repo.getBarcodeWarehouseRepository().save(barcodeWarehouse);
        return Optional.of(barcode);
    }

    public Optional<BarcodeWarehouse> changeBarcodeStatus(Optional<Barcode> barcode, Optional<Users> userOld, Optional<WorkPlace> workPlaceOld, Optional<Users> userNew, Optional<WorkPlace> workPlaceNew){
        if (!barcode.isPresent() || !userOld.isPresent() || !userNew.isPresent() || !workPlaceOld.isPresent() || !workPlaceNew.isPresent()){
            return Optional.empty();
        }
        Optional<BarcodeWarehouse> barcodeWarehouseFound = Optional.of(repo.getBarcodeWarehouseRepository().findByBarcodeAndUserAssignedAndWorkPlaceAssigned(barcode.get(), userOld.get(), workPlaceOld.get()));
        barcode.get().setBarcodeStatusType(BarcodeStatusType.MOVED);
        BarcodeWarehouse barcodeWarehouseNew = new BarcodeWarehouse(new Date(), barcode.get(), userNew.get(), workPlaceNew.get());
        if (barcodeWarehouseFound.isPresent()){
            barcodeWarehouseNew.setBarcodeWarehousePrevious(barcodeWarehouseFound.get());
        }
        repo.getBarcodeRepository().save(barcode.get());
        repo.getBarcodeWarehouseRepository().save(barcodeWarehouseNew);
        return Optional.of(barcodeWarehouseNew);
    }



}
