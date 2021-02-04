package io.ac.iot.manufacturer;

import io.ac.iot.adapter.rest.command.ManufacturerCreateCommand;
import io.ac.iot.manufacturer.model.Manufacturer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public String create(ManufacturerCreateCommand createCommand) {

        Manufacturer manufacturer = Manufacturer.create(createCommand);
        manufacturerRepository.save(manufacturer);
        return manufacturer.getId();
    }
}
