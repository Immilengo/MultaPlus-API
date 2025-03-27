package ao.multaplus.typeUser.service;

import ao.multaplus.typeUser.dtos.TypeUserUpdateDto;
import ao.multaplus.typeUser.dtos.TypeUsersDto;
import ao.multaplus.typeUser.response.TypeUsersResponse;

import java.util.List;

public interface TypeUserService {
    TypeUsersResponse create(TypeUsersDto requestdtos);
    TypeUsersResponse update(Long id, TypeUserUpdateDto requestdtos);
    void delete(Long id);
    List<TypeUsersResponse> getAll();
    TypeUsersResponse getById(Long id);
    void migration();
}
