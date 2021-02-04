package io.ac.iot.servicetenant.tenant;

import common.exception.BusinessException;
import io.ac.iot.servicetenant.account.AccountService;
import io.ac.iot.servicetenant.command.TenantSetUpSetMealCommand;
import io.ac.iot.servicetenant.common.util.PageUtil;
import io.ac.iot.servicetenant.request.TenantListRequest;
import io.ac.iot.servicetenant.tenant.command.TenantCreateCommand;
import io.ac.iot.servicetenant.tenant.factory.TenantFactory;
import io.ac.iot.servicetenant.tenant.model.SetMeal;
import io.ac.iot.servicetenant.tenant.model.Tenant;
import io.ac.iot.servicetenant.representation.TenantRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.Optional;

import static io.ac.iot.servicetenant.exception.code.TenantExceptionCode.TENANT_NOT_FOUNT;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TenantService {

    private final TenantRepository tenantRepository;
    private final TenantFactory tenantFactory;

    public TenantService(TenantRepository tenantRepository, TenantFactory tenantFactory) {
        this.tenantRepository = tenantRepository;
        this.tenantFactory = tenantFactory;
    }

    /**
     * 新增租户
     * 同时新增一个租户的root级别账号
     * @param createCommand
     * @return
     */
    public String create(TenantCreateCommand createCommand){

        Tenant tenant = tenantFactory.create(createCommand);
        tenantRepository.save(tenant);
        return tenant.getId();
    }

    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public TenantRepresentation findOne(String id){

        return findTenant(id).toRepresentation();
    }

    public void delete(String id) {

        Tenant tenant = findTenant(id);
        tenant.delete();
        tenantRepository.save(tenant);
    }

    public void active(String id) {

        Tenant tenant = findTenant(id);
        tenant.active();
        tenantRepository.save(tenant);
    }

    public void lock(String id) {

        Tenant tenant = findTenant(id);
        tenant.lock();
        tenantRepository.save(tenant);
    }

    public void inactive(String id) {

        Tenant tenant = findTenant(id);
        tenant.inactive();
        tenantRepository.save(tenant);
    }


    private Tenant findTenant(String id) {

        return tenantRepository.findById(id)
                .orElseThrow(() -> new BusinessException(TENANT_NOT_FOUNT.code, TENANT_NOT_FOUNT.description));
    }

    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Boolean valid(String id) {

        Optional<Tenant> tenant = tenantRepository.findById(id);
        if (tenant.isPresent()){

            return tenant.get().valid();
        }else {
            return Boolean.FALSE;
        }
    }

    /**
     * 分页
     * @param request
     * @return
     */
    public Page<Tenant> listTenant(TenantListRequest request) {

        return tenantRepository.findAll((root,query,builder) -> {

            Predicate predicate = builder.conjunction();

            if (StringUtils.isNotEmpty(request.getName())){
                predicate.getExpressions().add(builder.equal(root.get("name"),request.getName()));
            }
            return predicate;
        }, PageUtil.getPageable(request));
    }

    /**
     * 设置套餐
     * @param command
     */
    public void setUpSetMeal(TenantSetUpSetMealCommand command) {

        Tenant tenant = tenantRepository.findOrElseThrow(command.getTenantId());

        tenant.setUpSetMeal(command);
    }

    public SetMeal getSetMeal(String tenantId) {

        return tenantRepository.findOrElseThrow(tenantId).getSetMeal();
    }
}
