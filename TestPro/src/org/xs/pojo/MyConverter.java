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
		System.out.println("marshal参数1  " + arg0.toString());
		System.out.println("marshal参数2  " + arg1.toString());
		String age = arg0.toString().replace("岁", "");
		//如果已经存在节点，使用以下语句startNode("age")会在原节点下再次创建同名节点，如<age><age>xx</age></age>
//		arg1.startNode("age");
		//如果在存在age节点时，使用addAttribute("age", age)会产生age节点生产age属性的情况 如：<age age="12" /> 第一个参数为属性名
//		arg1.addAttribute("age", age);
		arg1.setValue(age);
//		arg1.endNode();
//		TreeMarshaller tree = (TreeMarshaller) arg2;
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader arg0,
			UnmarshallingContext arg1) {
		System.out.println("unmarshal参数1  " + arg0.getValue());
		Method method = arg1.getClass().getDeclaredMethods()[4];
		method.setAccessible(true);
		System.out.println("unmarshal参数2  " + arg1.getRequiredType());
		String s = arg0.getValue().replace("岁", "");
		return arg0.getValue();
//		return Integer.valueOf(s);
	}

}
