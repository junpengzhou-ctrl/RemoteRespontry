package com.suqianbft.service;

import com.suqianbft.dao.TypeRepository;
import com.suqianbft.pojo.Type;
import com.suqianbft.web.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getReferenceById(id);
    }
    @Transactional
    @Override
    public Page<Type> ListType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type one = typeRepository.getReferenceById(id);
        if (one ==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,one);     //对象拷贝 赋值
        return typeRepository.save(one);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
       typeRepository.deleteById(id);
    }

    @Override
    public Type[] findTypebyName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Pageable pageable = PageRequest.of(0,size,Sort.Direction.DESC,"article.size");
        return typeRepository.findTop(pageable);
    }
}
