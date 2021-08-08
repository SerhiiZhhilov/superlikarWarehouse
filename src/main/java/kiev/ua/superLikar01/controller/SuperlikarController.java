package kiev.ua.superLikar01.controller;

import kiev.ua.superLikar01.db.Repo;
import kiev.ua.superLikar01.db.entity.Barcode;
import kiev.ua.superLikar01.db.entity.Users;
import kiev.ua.superLikar01.db.entity.WorkPlace;
import kiev.ua.superLikar01.service.BarcodeService;
import kiev.ua.superLikar01.service.UserService;
import kiev.ua.superLikar01.service.WorkPlaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
public class SuperlikarController {

    private Repo repo;
    private UserService userService;
    private WorkPlaceService workPlaceService;
    private BarcodeService barcodeService;

    @GetMapping
    public String index(Map<String, Object> model){
        return "index";
    }

    @GetMapping("/main")
    public String mainGet(Map<String, Object> model) {

        userService.generatesUsers();
        workPlaceService.generateWorkPlaces();
        //
        workPlaceService.assignsUsersToTheirWorkPlaces();
        //
        Optional<Users> nata = userService.findUserByName("nata");
        Optional<Users> serhii = userService.findUserByName("serhii");

        Optional<WorkPlace> clinic1 = workPlaceService.findWorkPlaceByName("Clinic 1");
        Optional<WorkPlace> clinic2 = workPlaceService.findWorkPlaceByName("Clinic 2");
        //
        Optional<Barcode> newBarcode = barcodeService.addBarcode("000001", "Gloves", nata, clinic1);
        barcodeService.changeBarcodeStatus(newBarcode, nata, clinic1, serhii, clinic2);
        //
        List<Users> users = userService.getAllUsers();
        List<Barcode> barcodes = barcodeService.getAllBarcodes();
        model.put("users", users);
        model.put("barcodes", barcodes);
        return "main";
    }






    @PostMapping("addBarcode")
    public String addBarcode(@RequestParam String barcode, String description, Map<String, Object> model) {
        boolean barcodeIsEmpty = barcode == null || barcode.isEmpty();
        if (barcodeIsEmpty){
            return "main";
        }
        Barcode barcodeEntity = new Barcode(barcode, description);
        repo.getBarcodeRepository().save(barcodeEntity);
        // Load barcodes
        List<Barcode> barcodes = repo.getBarcodeRepository().findAll();
        model.put("barcodes", barcodes);
        return "main";
    }

    @PostMapping("filterBarcode")
    public String filterBarcode(@RequestParam String barcodeDescription, Map<String, Object> model){
        boolean barcodeDescriptionIsEmpty = barcodeDescription == null || barcodeDescription.isEmpty();
        List<Barcode> foundBarcodes = barcodeDescriptionIsEmpty ?
                repo.getBarcodeRepository().findAll() :
                repo.getBarcodeRepository().findAll().stream().filter(b -> b.getDescription().equalsIgnoreCase(barcodeDescription)).collect(Collectors.toList());
        model.put("barcodes", foundBarcodes);
        return "main";
    }

    @GetMapping(path = "/webcam")
    public String webcam() {
        return "webcam";
    }
}
