package com.modsen.meetup.api.util;

import com.modsen.meetup.api.exception.ModelException;

import java.util.List;

public interface ModelMapper<E,D> {
    E toEntity(D dto) throws ModelException;
    D toDto(E entity) throws ModelException;
    List<E> toEntityList(List<D> dtoList) throws ModelException;
    List<D> toDtoList(List<E> entityList) throws ModelException;
}
