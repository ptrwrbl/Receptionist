package pollub.cs.ptrwrbl.receptionist.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pollub.cs.ptrwrbl.receptionist.dtos.UserDTO;
import pollub.cs.ptrwrbl.receptionist.dtos.UserProfile;
import pollub.cs.ptrwrbl.receptionist.entities.User;

@Mapper
public interface UserMapper {
    @Mapping(source = "displayName", target = "displayName")
    UserDTO userToUserDTO(User user);

    @Mapping(source = "displayName", target = "displayName")
    User userDTOToUser(UserDTO dto);

    @Mapping(source = "displayName", target = "displayName")
    UserProfile userToUserProfile(User user);

    @Mapping(source = "displayName", target = "displayName")
    User userProfileToUser(UserProfile dto);
}