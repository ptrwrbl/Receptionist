package pollub.cs.ptrwrbl.receptionist.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 2, max = 50)
    private String name;

    @Column
    @Size(min = 2, max = 50)
    private String location;

    @Column
    @Size(min = 2, max = 100)
    private String description;

    @Column
    @Size(min = 2, max = 50)
    private String image;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms;
}
