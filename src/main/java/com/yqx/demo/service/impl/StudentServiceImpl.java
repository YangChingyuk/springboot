package com.yqx.demo.service.impl;

import org.springframework.stereotype.Service;

import com.yqx.demo.entity.Student;
import com.yqx.demo.service.StudentService;


@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService{

}
