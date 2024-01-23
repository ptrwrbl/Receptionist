package pollub.cs.ptrwrbl.receptionist.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import pollub.cs.ptrwrbl.receptionist.validation.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Display name cannot be empty")
    @Size(min = 2, max = 255, message = "Display name must be between 2 and 255 characters")
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźżA-ZĄĆĘŁŃÓŚŹŻ -]+$",
            message = "Display name must use only Polish letters, spaces and hyphens")
    @Column
    private String displayName;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    @Column
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email
    @Column
    private String email;

    @Column
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 255, message = "Password must be between 7 and 255 characters")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,100}$",
            message = "Password must contain at least one capital letter, one number and one special symbol")
    @JsonIgnore
    private String password;
}
