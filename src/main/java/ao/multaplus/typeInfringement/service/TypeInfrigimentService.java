package ao.multaplus.typeInfringement.service;


import ao.multaplus.typeInfringement.dtos.TypeInfringementsRequestdtos;
import ao.multaplus.typeInfringement.dtos.TypeInfringementsResponsedtos;

import java.util.List;

public interface TypeInfrigimentService {
    TypeInfringementsResponsedtos create(TypeInfringementsRequestdtos requestdtos);
    TypeInfringementsResponsedtos findById(Long id);
    List<TypeInfringementsResponsedtos> findAll();
    TypeInfringementsResponsedtos update(Long id, TypeInfringementsRequestdtos requestdtos);
    void delete(Long id);
    void migration();
}
