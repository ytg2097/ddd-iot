package io.ac.iot.servicetenant.organization;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
@RestController
@RequestMapping("organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;
}
