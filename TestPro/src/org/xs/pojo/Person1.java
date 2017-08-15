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
	
//	@XStreamAsAttribute()  //���ø����ԣ����ֶλ��Ϊ�ϼ��ڵ�����ԣ���ת����JavaBeanʱ����ʹ��xs.aliasAttribute("person", "name")��䣬������ֶ���ֵ��personΪ�ϼ��ڵ㣬nameΪ��Ӧ�ֶ�
	private String name;
	@XStreamConverter(MyConverter.class)
	private int age;
	private String gerden;
	@XStreamImplicit()  //���ø����ԣ���ת�����ַ����￴���������ƵĽڵ�<addresses>������ת��JavaBeanʱ����ֵ���ɻ���ֵ
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
