package presentation.mapper;

import data.model.Address;
import presentation.dto.AddressDTO;

public class AddressDTOMapper implements Mapper<AddressDTO, Address> {
    @Override
    public Address map(AddressDTO from) {
        return new Address(
                from.getStreet(),
                from.getCity(),
                from.getState(),
                from.getZip()
        );
    }
}
