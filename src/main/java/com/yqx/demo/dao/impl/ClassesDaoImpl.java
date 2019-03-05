package com.yqx.demo.dao.impl;

import org.springframework.stereotype.Repository;

import com.yqx.demo.dao.ClassesDao;
import com.yqx.demo.entity.Classes;


@Repository("classesDao")
public class ClassesDaoImpl extends BaseDaoImpl<Classes> implements ClassesDao{

}
