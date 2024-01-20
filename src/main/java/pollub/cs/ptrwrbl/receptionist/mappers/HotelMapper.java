package pollub.cs.ptrwrbl.receptionist.mappers;

import org.mapstruct.Mapper;
import pollub.cs.ptrwrbl.receptionist.dtos.HotelDTO;
import pollub.cs.ptrwrbl.receptionist.entities.Hotel;

@Mapper
public interface HotelMapper {
    HotelDTO hotelToHotelDTO(Hotel hotel);
}
