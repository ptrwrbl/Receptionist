package pollub.cs.ptrwrbl.receptionist.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    @Column
    private String name;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 2000, message = "Description must be between 50 and 1000 characters")
    @Column
    private String description;

    @PositiveOrZero(message = "Price must be zero or positive")
    @DecimalMin(value = "0.0", message = "Price must be greater than or equal to 0")
    @Column
    private Double price;

    @NotBlank(message = "Image cannot be empty")
    @Size(min = 20, max = 100, message = "Image URL must be between 20 and 100 characters")
    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
