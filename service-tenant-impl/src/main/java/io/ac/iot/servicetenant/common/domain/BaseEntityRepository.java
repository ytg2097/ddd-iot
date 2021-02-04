package io.ac.iot.servicetenant.common.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Stream;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-01
 **/
@NoRepositoryBean
public interface BaseEntityRepository<T,ID> extends JpaRepository<T,ID> {

    default T findOrElseThrow(ID id) {

        String typeName = Stream.of(((Class) this.getClass().getGenericInterfaces()[0]).getGenericInterfaces())
                .map(cur -> ((ParameterizedType) cur).getActualTypeArguments())
                .flatMap(Stream::of).findFirst().map(Type::getTypeName).get();

        String[] split = typeName.split("\\.");
        return findById(id).
                orElseThrow(() -> new EntityNotFoundException(split[split.length - 1]));
    }
}
