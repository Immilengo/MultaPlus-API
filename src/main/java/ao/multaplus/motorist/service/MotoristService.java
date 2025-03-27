package ao.multaplus.motorist.service;

import ao.multaplus.motorist.dtos.MotoristDto;
import ao.multaplus.motorist.entity.Motorists;

public interface MotoristService {
    /**
     * Register a new motorist
     * @param motoristDto - motorist data
     *
     * */
    void registerMotorist(MotoristDto motoristDto);

    Motorists getMotorist(String motoristIdentifier);

    void updateMotorist(String motoristIdentifier, MotoristDto motoristDto);

    void deleteMotorist(String motoristIdentifier);

}
