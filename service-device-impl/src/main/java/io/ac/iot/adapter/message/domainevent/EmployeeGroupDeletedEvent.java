package io.ac.iot.adapter.message.domainevent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.ac.starter.DomainEvent;

import java.util.Date;

import static io.ac.iot.adapter.message.EmployeeGroupEventHandler.EmployeeGroupEventTypeWithChannel.EMPLOYEEGROUP_DELETED;

@JsonTypeName(EMPLOYEEGROUP_DELETED)
public class EmployeeGroupDeletedEvent extends DomainEvent {

    public String getEmployeeGroupId() {
        return employeeGroupId;
    }

    private final String employeeGroupId;

    @JsonCreator
    public EmployeeGroupDeletedEvent(
            @JsonProperty("employeeGroupId") String employeeGroupId,
            @JsonProperty("_id") String _id,
            @JsonProperty("_type") String _type,
            @JsonProperty("_createdAt") Date _createdAt) {
        super(_id, _type, _createdAt);
        this.employeeGroupId = employeeGroupId;
    }
}