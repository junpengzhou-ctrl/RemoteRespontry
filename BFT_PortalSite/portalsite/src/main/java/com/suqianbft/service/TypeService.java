package com.suqianbft.service;

import com.suqianbft.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);
    Type getType(Long id);
    Page<Type> ListType(Pageable pageable);
    Type updateType(Long id,Type type);
    void deleteType(Long id);
    Type[] findTypebyName(String name);
    List<Type> listType();
    List<Type> listTypeTop(Integer size);
}
