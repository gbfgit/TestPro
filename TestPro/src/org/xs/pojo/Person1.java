package org.xs.pojo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamContainedType;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamInclude;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("person")
public class Person1 {
	
//	@XStreamAsAttribute()  //配置该属性，该字段会成为上级节点的属性，在转换成JavaBean时，需使用xs.aliasAttribute("person", "name")语句，否则该字段无值。person为上级节点，name为对应字段
	private String name;
	@XStreamConverter(MyConverter.class)
	private int age;
	private String gerden;
	@XStreamImplicit()  //配置该属性，在转换的字符串里看不到该名称的节点<addresses>，但在转成JavaBean时，该值依旧会有值
	private List<Address> addresses;
	
	
	public Person1() {
		
	}
	
	public Person1(String name, int age, String gerden, List<Address> addresses) {
		this.name = name;
		this.age = age;
		this.gerden = gerden;
		this.addresses = addresses;
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

	public String getGerden() {
		return gerden;
	}
	
	public void setGerden(String gerden) {
		this.gerden = gerden;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Person1 [name=" + name + ", age=" + age + ", gerden=" + gerden
				+ ", addresses=" + addresses + "]";
	}
	
	
	
}
