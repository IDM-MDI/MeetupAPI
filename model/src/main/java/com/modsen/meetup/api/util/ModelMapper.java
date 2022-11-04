package com.modsen.meetup.api.util;

import java.util.List;

public interface ModelMapper<E,D> {
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntityList(List<D> dtoList);
    List<D> toDtoList(List<E> entityList);
}
