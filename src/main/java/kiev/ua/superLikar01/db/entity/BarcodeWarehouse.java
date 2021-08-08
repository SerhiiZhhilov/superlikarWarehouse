package kiev.ua.superLikar01.db.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
public class BarcodeWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "barcode_id")
    private Barcode barcode;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private Users userAssigned;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "workplace_id")
    private WorkPlace workPlaceAssigned;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "barcode_warehouse_previous")
    private BarcodeWarehouse barcodeWarehousePrevious;

    public BarcodeWarehouse(Date date, Barcode barcode, Users userAssigned, WorkPlace workPlaceAssigned) {
        this.date = date;
        this.barcode = barcode;
        this.userAssigned = userAssigned;
        this.workPlaceAssigned = workPlaceAssigned;
    }
}
