package ar.com.ada.sb.unittest.model.mapper;

import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

/**
 * @param <D> Respresent DTO class
 * @param <E> Respresent Entity class
 */
public interface DataCycleMapper<D, E> {

    /**
     *
     * @param dto
     * @param context
     * @return
     */
    E toEntity(D dto, @Context CycleAvoidingMappingContext context);

    /**
     *
     * @param dtoList
     * @param context
     * @return
     */
    List<E> toEntity(List<D> dtoList, @Context CycleAvoidingMappingContext context);

    /**
     *
     * @param entity
     * @param context
     * @return
     */
    @InheritInverseConfiguration
    D toDto(E entity, @Context CycleAvoidingMappingContext context);

    /**
     *
     * @param entityList
     * @param context
     * @return
     */
    @InheritInverseConfiguration
    List<D> toDto(List<E> entityList, @Context CycleAvoidingMappingContext context);
}

