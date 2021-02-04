package io.ac.iot.servicetenant.employeegroup.model;

import io.ac.iot.servicetenant.common.Constants;
import io.ac.iot.servicetenant.common.domain.BaseDomainEventAwareAggregate;
import io.ac.iot.servicetenant.employeegroup.event.EmployeeGroupDeletedEvent;
import io.ac.iot.servicetenant.employee.model.Employee;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.util.Collections.emptyList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-31
 **/
@Data
@Entity
@Where(clause = " valid = " + Constants.FLAG_NOT_DELETED + " ")
public class EmployeeGroup  extends BaseDomainEventAwareAggregate {

    @Id
    private String id;

    private String name;

    private String tenantId;

    private String parentId;

    private String description;

    @OneToMany(mappedBy = "employeeGroup")
    @org.hibernate.annotations.ForeignKey(name = "none")
    private List<Employee> employeeList;

    public Boolean addMember(Employee employee){

        return this.employeeList.add(employee);
    }

    public Boolean removeMember(Employee employee){

        return this.employeeList.remove(employee);
    }

    public void move(String targetGroupId) {

        this.parentId = targetGroupId;
    }

    public void destroy(){

        this.employeeList = emptyList();
        this.setValid(FALSE);
        raiseEvent(new EmployeeGroupDeletedEvent(this.id));
    }
}
