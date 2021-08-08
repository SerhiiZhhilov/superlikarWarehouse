package kiev.ua.superLikar01.db.entity;

import kiev.ua.superLikar01.db.type.BarcodeStatusType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
public class Barcode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String code;
    private String description;
    private BarcodeStatusType barcodeStatusType;

    public Barcode(String code, String description) {
        this.code = code;
        this.description = description;
        this.barcodeStatusType = BarcodeStatusType.NEW;
    }

    public Boolean isTheSameBarcode(Optional<Barcode> barcode) {
        if (!barcode.isPresent()) {
            return false;
        }
        return this.getCode().equalsIgnoreCase(barcode.get().getCode());
    }
}
