package ao.multaplus.province.service;

import ao.multaplus.province.dtos.ProviceUpdateDto;
import ao.multaplus.province.dtos.ProvinceDto;
import ao.multaplus.province.response.ProvinceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProvincesService {
    ProvinceResponse create(ProvinceDto requestdtos);
    ProvinceResponse update(Long id, ProviceUpdateDto requestdtos);
    void delete(Long id);
    ProvinceResponse findById(Long id);
    List<ProvinceResponse> findAll();
    void migration();
}
