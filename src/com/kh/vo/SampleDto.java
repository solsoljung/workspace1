package com.kh.vo;

public class SampleDto {

	private String name;
	private int age;
	public SampleDto(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public SampleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "SampleDto [name=" + name + ", age=" + age + "]";
	}
}
