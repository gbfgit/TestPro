package org.xs.test;

import java.util.ArrayList;
import java.util.List;

import org.xs.pojo.Address;
import org.xs.pojo.Person;
import org.xs.pojo.Person1;

import com.thoughtworks.xstream.XStream;

public class Xml2Object {
	
	public static void main(String[] args) {
		String xml = toXml();
		System.out.println(xml);
		Person p0 = toPojo(xml);
		System.out.println(p0.getName() + p0.getGerden());
		System.out.println(p0.getAddresses().get(1).getCity());
		System.out.println(p0.toString());
//		Person1 p1 = toBean(xml);
//		System.out.println(p1.getName() + " " + p1.getGerden() + " " + p1.getAge());
//		System.out.println(p1.getAddresses().get(1).getCity());
//		System.out.println(p1.toString());
	}
	
	public static String toXml()
	{
		String xmlValue = "";
		XStream xs = new XStream();
		//����δʹ��ע�������£�������xml�ַ���ʱ����Ӧ�Ľڵ���������·��ȫ����org.xs.pojo.Person ,��������2����棬
//		xs.alias("person", Person.class);
//		xs.alias("address", Address.class);
		 //���������ת�����ַ���ʱ���������nameת����person ��ԭ����Ϊname�����ԣ������滻��person �����滻ǰ<person name="lisy"> �滻��<person person="lisy">
//		xs.aliasAttribute("person", "name");
//		xs.useAttributeFor(Person.class, "name");
//		xs.omitField(Person.class, "age");
		Person person = new Person("lisy", "22��", "Ů", null);
		List<Address> adds = new ArrayList<Address>();
		adds.add(new Address("����������Ϣ���԰", 2388, "����"));
		adds.add(new Address("�ֿڽָ�ǰ·", 127, "�ӻ�"));
		person.setAddresses(adds);
		xs.setMode(XStream.NO_REFERENCES);
		xs.processAnnotations(new Class[] {Person.class, Address.class});
		xmlValue = xs.toXML(person);
		return xmlValue;
	}
	
	public static Person toPojo(String xml)
	{
		XStream xs = new XStream();
		xs.setMode(XStream.NO_REFERENCES);
		//������ַ�����û��ʹ��ȫ��������������û��ע�⣬����ʹ������2�䡣���򱨴�
//		xs.alias("person", Person.class);
//		xs.alias("address", Address.class);
		/*��ת��JavaBeanʱ����person���Զ�Ӧ��name��ֵ  ���磺������name���ԣ�����������xml���߻�ȡ����xmlû�ж�Ӧ��name�ڵ㣬Ҳû�ж�Ӧ��name���ԣ���������person����ֵ��Ӧname��ֵ�������ʹ�ø��������Ӧ��
		�����ڱ����е�toXmlת��xmlʱ����name�����滻����person���ԣ���Ӧ��ʹ�ø��������ӳ�䣬������ֶ���ֵ��xs.aliasAttribute("person", "name");*/
//		xs.aliasAttribute("person", "name"); 
//		xs.useAttributeFor(Person.class, "name");
//		xs.omitField(Person.class, "age");
		xs.processAnnotations(new Class[] {Person.class, Address.class});
		xs.autodetectAnnotations(true);
		Person person = (Person) xs.fromXML(xml);
		return person;
	}
	
	public static Person1 toBean(String xml)
	{
		xml = xml.replace("person", "person1");
		XStream xs = new XStream();
		xs.setMode(XStream.NO_REFERENCES);
		//������ַ�����û��ʹ��ȫ��������������û��ע�⣬����ʹ������2�䡣���򱨴�
		xs.alias("person1", Person1.class);
//		xs.alias("address", Address.class);
		/*��ת��JavaBeanʱ����person���Զ�Ӧ��name��ֵ  ���磺������name���ԣ�����������xml���߻�ȡ����xmlû�ж�Ӧ��name�ڵ㣬Ҳû�ж�Ӧ��name���ԣ���������person����ֵ��Ӧname��ֵ�������ʹ�ø��������Ӧ��
		�����ڱ����е�toXmlת��xmlʱ����name�����滻����person���ԣ���Ӧ��ʹ�ø��������ӳ�䣬������ֶ���ֵ��xs.aliasAttribute("person", "name");*/
//		xs.aliasAttribute("person", "name"); 
//		xs.useAttributeFor(Person.class, "name");
//		xs.omitField(Person.class, "age");
		xs.processAnnotations(new Class[] {Person.class, Address.class});
		xs.autodetectAnnotations(true);
		Person1 person = (Person1) xs.fromXML(xml);
		return person;
	}
}
