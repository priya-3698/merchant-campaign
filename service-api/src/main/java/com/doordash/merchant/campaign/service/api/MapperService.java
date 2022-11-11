package com.doordash.merchant.campaign.service.api;

import java.util.List;

public interface MapperService {

  /**
   * map entity to list
   * 
   * @param entity entity
   * @param tClass class
   * @return mapped entity list
   * @param <E> Entity
   * @param <T> Type
   */
  <E, T> List<T> mapEntityToList(List<E> entity, Class<T> tClass);
}
