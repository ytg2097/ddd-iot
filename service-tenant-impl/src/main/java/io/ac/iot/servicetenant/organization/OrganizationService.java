package io.ac.iot.servicetenant.organization;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationService {


}
