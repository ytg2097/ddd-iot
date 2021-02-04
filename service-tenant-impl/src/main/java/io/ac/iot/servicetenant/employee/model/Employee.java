package io.ac.iot.servicetenant.employee.model;

import io.ac.iot.servicetenant.common.Constants;
import io.ac.iot.servicetenant.common.domain.BaseEntity;
import io.ac.iot.servicetenant.common.util.RequestIdHolder;
import io.ac.iot.servicetenant.employee.command.EmployeeCreateCommand;
import io.ac.iot.servicetenant.employee.command.EmployeeModifyCommand;
import io.ac.iot.servicetenant.employeegroup.model.EmployeeGroup;
import io.ac.iot.servicetenant.representation.EmployeeRepresentation;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

import static common.util.ObjectUtil.copyField;
import static java.lang.Boolean.TRUE;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@Data
@Entity
@Where(clause = "valid=" + Constants.FLAG_NOT_DELETED )
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private String email;

    private String tenantId;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_group_id",
            foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    private EmployeeGroup employeeGroup;

    public static Employee create(EmployeeCreateCommand createCommand) {

        Employee employee = copyField(createCommand, new Employee());
        employee.setValid(TRUE);
        employee.setTenantId(RequestIdHolder.tid.get());
        return employee;
    }

    public EmployeeRepresentation toRepresentation() {

        EmployeeRepresentation result = new EmployeeRepresentation();
        result.setCreateTime(Date.from(getCreateTime()));
        result.setEmail(email);
        result.setName(name);
        result.setPhone(phone);
        result.setId(id);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void modify(EmployeeModifyCommand modifyCommand) {

        this.name = modifyCommand.getName();
        this.phone = modifyCommand.getPhone();
        this.email = modifyCommand.getEmail();
    }
}
