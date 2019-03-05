package com.yqx.demo.entity;

import java.util.List;

public class Classes {
	private int id;
	private String name;
	/*
	 *һ�Զ��ϵ�У���һ��ʵ��������Ӷ෽�༯������ 
	 * */
	private List<Student> students;
	
	public Classes(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Classes() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	@Override
	public String toString() {
		return "Classes [id=" + id + ", name=" + name + "]";
	}
	
	
}
