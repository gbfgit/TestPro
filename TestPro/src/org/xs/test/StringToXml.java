package org.xs.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringToXml {
	
	public static void main(String[] args) {
		String value = "域=附件属性;-;域字段数=3;-;题名0=01.jpg;-;原文号0=;-;发文机关0=;-;题名1=;-;原文号1=;-;发文机关1=;-;题名2=;-;原文号2=;-;发文机关2=;-;题名3=;-;原文号3=;-;发文机关3=;-;" +
				"题名4=;-;原文号4=;-;发文机关4=;-;题名5=;-;原文号5=;-;发文机关5=;-;题名6=;-;原文号6=;-;发文机关6=;-;题名7=;-;原文号7=;-;发文机关7=;-;题名8=;-;原文号8=;-;发文机关8=;-;题名9=;-;" +
				"原文号9=;-;发文机关9=;-;题名10=;-;原文号10=;-;发文机关10=;-;题名11=;-;原文号11=;-;发文机关11=;-;题名12=;-;原文号12=;-;发文机关12=;-;题名13=;-;原文号13=;-;发文机关13=;-;" +
				"题名14=;-;原文号14=;-;发文机关14=;-;题名15=;-;原文号15=;-;发文机关15=;-;题名16=;-;原文号16=;-;发文机关16=;-;题名17=;-;原文号17=;-;发文机关17=;-;题名18=;-;原文号18=;-;" +
				"发文机关18=;-;题名19=;-;原文号19=;-;发文机关19=;-;";
		String[] values = value.split(";-;");
		System.out.println(values[0]);
		int i = 0;
		i = values.length - 2;
		int total = i/3;
		Connection conn = null;
		List<Map<String, String>> xmlList = new ArrayList<Map<String,String>>();
		System.out.println(total);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.11:1521:orcl", "ZSNDA", "ZSNDA");
			System.out.println("connect success!");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select wyz,tszd from test_wj_bak where rownum < 11");
			while (rs.next()) {
				Map<String, String> xmlMap = new HashMap<String, String>();
				String s = rs.getString("tszd");
				System.out.println(s);
				s = createXml(s);
				xmlMap.put(rs.getString("wyz"), s);
				xmlList.add(xmlMap);
				System.out.println(s);
			}
			updateXml(conn, xmlList);
			conn.close();
		} catch (Exception e) {
			System.out.println("exception");
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}
	
	public static String createXml(String s)
	{
		String[] values = s.split(";-;");
		StringBuffer sb = new StringBuffer();
		
		int i = Integer.valueOf(values[1].split("=")[1]);
		int total = (values.length - 2)/i;
		System.out.println(total);
		for (int j = 2; j < values.length; j++) 
		{
			if((j+1)%3 == 0)
			{sb.append("<file type=\"" + values[0].split("=")[1] + "\">");}
			String[] v = values[j].split("=");
			if(v.length < 2){
				sb.append("<property name=\"" + v[0] + "\" value=\"\"/>");
			}
			else {
				sb.append("<property name=\"" + v[0] + "\" value=\"" + v[1] + "\"/>");
			}
			if((j+1)%3 == 2)
			{sb.append("</file>");}
		}
		
		return sb.toString();
	}
	
	public static void updateXml(Connection conn, List<Map<String, String>> xmls) throws SQLException
	{
		String sql = "update test_wj_bak set tszd = ? where wyz = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		for (int i = 0; i < xmls.size(); i++) {
			pst.setString(1, xmls.get(i).get(xmls.get(i).keySet().iterator().next()));
			pst.setString(2, xmls.get(i).keySet().iterator().next());
//			pst.addBatch(sql);
//			if(i%10 == 0)
//			{
//				pst.executeBatch();
//			}
			pst.execute();
		}
		conn.commit();
	}
	
}
