package com.example.nace.job.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component("ecnaMapper")
public class Mapper {
    public void map(Object source, Object target){
        BeanUtils.copyProperties(source, target);
    }
}
