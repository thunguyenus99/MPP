package presentation.mapper;

import data.model.LibraryMember;
import presentation.dto.LibraryMemberDTO;

public class LibraryMemberDTOMapper implements Mapper<LibraryMemberDTO, LibraryMember> {

    @Override
    public LibraryMember map(LibraryMemberDTO from) {
        return new LibraryMember(
                from.getMemberId(),
                from.getFirstName(),
                from.getLastName(),
                new AddressDTOMapper().map(from.getAddress()),
                from.getTelephone()
        );
    }
}
