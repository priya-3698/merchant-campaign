package com.doordash.merchant.campaign.service.api;

import java.util.List;

public interface MapperService {

  <E, T> List<T> mapEntityToList(List<E> entity, Class<T> tClass);
}
