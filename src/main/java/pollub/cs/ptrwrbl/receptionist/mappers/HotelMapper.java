package pollub.cs.ptrwrbl.receptionist.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pollub.cs.ptrwrbl.receptionist.dtos.HotelDTO;
import pollub.cs.ptrwrbl.receptionist.entities.Hotel;

@Mapper
public interface HotelMapper {
    HotelDTO hotelToHotelDTO(Hotel hotel);

    @Mapping(target = "rooms", ignore = true)
    Hotel hotelDTOToHotel(HotelDTO dto);
}
