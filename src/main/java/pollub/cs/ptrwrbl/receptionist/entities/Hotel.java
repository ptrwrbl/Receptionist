package pollub.cs.ptrwrbl.receptionist.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    @Column
    private String name;

    @NotBlank(message = "Location cannot be empty")
    @Size(min = 3, max = 255, message = "Location must be between 5 and 255 characters")
    @Column
    private String location;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 2000, message = "Description must be between 100 and 5000 characters")
    @Column
    private String description;

    @NotBlank(message = "Image cannot be empty")
    @Size(min = 20, max = 100, message = "Image URL must be between 20 and 100 characters")
    @Column
    private String image;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms;
}
