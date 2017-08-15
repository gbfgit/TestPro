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
		String value = "��=��������;-;���ֶ���=3;-;����0=01.jpg;-;ԭ�ĺ�0=;-;���Ļ���0=;-;����1=;-;ԭ�ĺ�1=;-;���Ļ���1=;-;����2=;-;ԭ�ĺ�2=;-;���Ļ���2=;-;����3=;-;ԭ�ĺ�3=;-;���Ļ���3=;-;" +
				"����4=;-;ԭ�ĺ�4=;-;���Ļ���4=;-;����5=;-;ԭ�ĺ�5=;-;���Ļ���5=;-;����6=;-;ԭ�ĺ�6=;-;���Ļ���6=;-;����7=;-;ԭ�ĺ�7=;-;���Ļ���7=;-;����8=;-;ԭ�ĺ�8=;-;���Ļ���8=;-;����9=;-;" +
				"ԭ�ĺ�9=;-;���Ļ���9=;-;����10=;-;ԭ�ĺ�10=;-;���Ļ���10=;-;����11=;-;ԭ�ĺ�11=;-;���Ļ���11=;-;����12=;-;ԭ�ĺ�12=;-;���Ļ���12=;-;����13=;-;ԭ�ĺ�13=;-;���Ļ���13=;-;" +
				"����14=;-;ԭ�ĺ�14=;-;���Ļ���14=;-;����15=;-;ԭ�ĺ�15=;-;���Ļ���15=;-;����16=;-;ԭ�ĺ�16=;-;���Ļ���16=;-;����17=;-;ԭ�ĺ�17=;-;���Ļ���17=;-;����18=;-;ԭ�ĺ�18=;-;" +
				"���Ļ���18=;-;����19=;-;ԭ�ĺ�19=;-;���Ļ���19=;-;";
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
