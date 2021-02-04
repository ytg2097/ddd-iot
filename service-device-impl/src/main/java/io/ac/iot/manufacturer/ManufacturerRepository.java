package io.ac.iot.manufacturer;

import io.ac.iot.manufacturer.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
public interface ManufacturerRepository extends JpaRepository<Manufacturer,String> {
}
