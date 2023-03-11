package presentation.mapper;

import data.model.Author;
import presentation.dto.AuthorDTO;

public class AuthorDTOMapper implements Mapper<AuthorDTO, Author> {
    @Override
    public Author map(AuthorDTO from) {
        return new Author(
                from.getCredentials(),
                from.getBiography(),
                from.getFirstName(),
                from.getLastName(),
                new AddressDTOMapper().map(from.getAddressDTO()),
                from.getTelephone()
        );
    }
}
