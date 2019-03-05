package com.yqx.demo.dao.impl;

import org.springframework.stereotype.Repository;

import com.yqx.demo.dao.StudentDao;
import com.yqx.demo.entity.Student;


@Repository("studentDao")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao{

}
