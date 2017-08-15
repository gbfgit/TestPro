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
		//在类未使用注解的情况下，在生成xml字符串时，对应的节点名称是类路径全名如org.xs.pojo.Person ,可用以下2句代替，
//		xs.alias("person", Person.class);
//		xs.alias("address", Address.class);
		 //下列语句在转换成字符串时将属性里的name转换成person 即原本名为name的属性，将被替换成person 例：替换前<person name="lisy"> 替换后<person person="lisy">
//		xs.aliasAttribute("person", "name");
//		xs.useAttributeFor(Person.class, "name");
//		xs.omitField(Person.class, "age");
		Person person = new Person("lisy", "22岁", "女", null);
		List<Address> adds = new ArrayList<Address>();
		adds.add(new Address("火炬高新区信息光电园", 2388, "厦门"));
		adds.add(new Address("街口街府前路", 127, "从化"));
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
		//如果在字符串中没有使用全类名，且在类中没有注解，必须使用以下2句。否则报错
//		xs.alias("person", Person.class);
//		xs.alias("address", Address.class);
		/*在转成JavaBean时，将person属性对应成name的值  例如：类中有name属性，但传过来的xml或者获取到的xml没有对应的name节点，也没有对应的name属性，但有属性person，其值对应name的值，则可以使用该语句做对应。
		例如在本例中的toXml转换xml时，将name属性替换成了person属性，故应该使用该语句重新映射，否则该字段无值。xs.aliasAttribute("person", "name");*/
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
		//如果在字符串中没有使用全类名，且在类中没有注解，必须使用以下2句。否则报错
		xs.alias("person1", Person1.class);
//		xs.alias("address", Address.class);
		/*在转成JavaBean时，将person属性对应成name的值  例如：类中有name属性，但传过来的xml或者获取到的xml没有对应的name节点，也没有对应的name属性，但有属性person，其值对应name的值，则可以使用该语句做对应。
		例如在本例中的toXml转换xml时，将name属性替换成了person属性，故应该使用该语句重新映射，否则该字段无值。xs.aliasAttribute("person", "name");*/
//		xs.aliasAttribute("person", "name"); 
//		xs.useAttributeFor(Person.class, "name");
//		xs.omitField(Person.class, "age");
		xs.processAnnotations(new Class[] {Person.class, Address.class});
		xs.autodetectAnnotations(true);
		Person1 person = (Person1) xs.fromXML(xml);
		return person;
	}
}
