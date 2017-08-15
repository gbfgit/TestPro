package org.xs.pojo;

import java.lang.reflect.Method;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.ConverterMatcher;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.core.TreeMarshaller;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.XppReader;
import com.thoughtworks.xstream.mapper.Mapper;

public class MyConverter implements ConverterMatcher,Converter {

	@Override
	public boolean canConvert(Class arg0) {
		
		return true;
	}

	@Override
	public void marshal(Object arg0, HierarchicalStreamWriter arg1,
			MarshallingContext arg2) {
		System.out.println("marshal����1  " + arg0.toString());
		System.out.println("marshal����2  " + arg1.toString());
		String age = arg0.toString().replace("��", "");
		//����Ѿ����ڽڵ㣬ʹ���������startNode("age")����ԭ�ڵ����ٴδ���ͬ���ڵ㣬��<age><age>xx</age></age>
//		arg1.startNode("age");
		//����ڴ���age�ڵ�ʱ��ʹ��addAttribute("age", age)�����age�ڵ�����age���Ե���� �磺<age age="12" /> ��һ������Ϊ������
//		arg1.addAttribute("age", age);
		arg1.setValue(age);
//		arg1.endNode();
//		TreeMarshaller tree = (TreeMarshaller) arg2;
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader arg0,
			UnmarshallingContext arg1) {
		System.out.println("unmarshal����1  " + arg0.getValue());
		Method method = arg1.getClass().getDeclaredMethods()[4];
		method.setAccessible(true);
		System.out.println("unmarshal����2  " + arg1.getRequiredType());
		String s = arg0.getValue().replace("��", "");
		return arg0.getValue();
//		return Integer.valueOf(s);
	}

}
