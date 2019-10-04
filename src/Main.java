
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.io.FileWriter;
//import java.io.FileReader;
//import java.io.BufferedWriter;

public class Main {
	public static List<Map<String, String>> addressResolution(String str,String flag) {
		List<Map<String, String>> table = new ArrayList<Map<String, String>>();
		Map<String, String> txtline = null;
		txtline = new LinkedHashMap<String, String>();
		//获取省份
		String province="";
		if(str.indexOf("省")!=-1)
		{
			province=str.substring(0, str.indexOf("省")+1);
			str=str.substring(str.indexOf("省")+1);
		}
		else if(str.indexOf("自治区")!=-1)
		{
			province=str.substring(0, str.indexOf("自治区")+3);
			str=str.substring(str.indexOf("自治区")+3);
		}
		else if(str.indexOf("北京")!=-1)
			province="北京";
		else if(str.indexOf("天津")!=-1)
			province="天津";
		else if(str.indexOf("上海")!=-1)
			province="上海";
		else if(str.indexOf("重庆")!=-1)
			province="重庆";//直辖市
		else
		{
			province=str.substring(0,2)+"省";
			str=str.substring(2);
		}
		//提取市
		String city="";
		if(str.indexOf("市")!=-1)
		{
			city=str.substring(0, str.indexOf("市")+1);
			str=str.substring(str.indexOf("市")+1);
		}else if(str.indexOf("岛")!=-1){
			city=str.substring(0, str.indexOf("岛")+1);
			str=str.substring(str.indexOf("岛")+1);
		}else if(str.indexOf("自治州")!=-1){
			city=str.substring(0, str.indexOf("自治州")+3);
			str=str.substring(str.indexOf("自治州")+3);
		}else if(str.indexOf("地区")!=-1){
			city=str.substring(0, str.indexOf("地区")+2);
			str=str.substring(str.indexOf("地区")+2);
		}else if(str.indexOf("盟")!=-1){
			city=str.substring(0, str.indexOf("盟")+1);
			str=str.substring(str.indexOf("盟")+1);
		}
		String county="";
		if(str.indexOf("区")!=-1)
		{
			county=str.substring(0, str.indexOf("区")+1);
			str=str.substring(str.indexOf("区")+1);
		}else if(str.indexOf("县")!=-1)
		{
			county=str.substring(0, str.indexOf("县")+1);
			str=str.substring(str.indexOf("县")+1);
		}
		String town="";
		if(str.indexOf("街道")!=-1)
		{
			town=str.substring(0, str.indexOf("街道")+2);
			str=str.substring(str.indexOf("街道")+2);
		}else if(str.indexOf("镇")!=-1)
		{
			town=str.substring(0, str.indexOf("镇")+1);
			str=str.substring(str.indexOf("镇")+1);
		}else if(str.indexOf("乡")!=-1)
		{
			town=str.substring(0, str.indexOf("乡")+1);
			str=str.substring(str.indexOf("乡")+1);
		}
		if(flag.equals("1")) {
		txtline.put("province", province == null ? "" : province.trim());		
		txtline.put("city", city == null ? "" : city.trim());		
		txtline.put("county", county == null ? "" : county.trim());	
		txtline.put("town", town == null ? "" : town.trim());
		txtline.put("road", str == null ? "" : str.trim());
	table.add(txtline);
	return table;
		}//输出
		else {
			//提取路
			String way="";
			if(str.indexOf("路")!=-1){
				way=str.substring(0, str.indexOf("路")+1);
				str=str.substring(str.indexOf("路")+1);
			}else if(str.indexOf("街")!=-1){
				way=str.substring(0, str.indexOf("街")+1);
				str=str.substring(str.indexOf("街")+1);
			}else if(str.indexOf("巷")!=-1){
				way=str.substring(0, str.indexOf("巷")+1);
				str=str.substring(str.indexOf("巷")+1);
			}else if(str.indexOf("村")!=-1){
				way=str.substring(0, str.indexOf("村")+1);
				str=str.substring(str.indexOf("村")+1);
			}
			//门牌号
			String tablet="";
			if(str.indexOf("号")!=-1)
			{
				tablet=str.substring(0, str.indexOf("号")+1);
				str=str.substring(str.indexOf("号")+1);
			}

			txtline.put("province", province == null ? "" : province.trim());

			txtline.put("city", city == null ? "" : city.trim());

			txtline.put("county", county == null ? "" : county.trim());

			txtline.put("town", town == null ? "" : town.trim());

			txtline.put("road", way == null ? "" : way.trim());

			txtline.put("numb", tablet == null ? "" : tablet.trim());

			txtline.put("village", str == null ? "" : str.trim());
		table.add(txtline);
		return table;


		}	

	}
	public static String tel(String num) {
		if (num == null || num.length() == 0) {
			return "";
		}
		Pattern pattern = Pattern.compile("\\d{11}");
		Matcher matcher = pattern.matcher(num);
		StringBuffer bf = new StringBuffer(64);
		while (matcher.find()) {
			bf.append(matcher.group()).append(",");
		}
		int len = bf.length();
		if (len > 0) {
			bf.deleteCharAt(len - 1);
		}
		return bf.toString();
	}//提取电话号码

	public static void main(String[] args) throws FileNotFoundException {

//		File inputFile = new File(args[0]);
		File inputFile = new File("input.txt");
		Scanner in = new Scanner(inputFile);
		PrintWriter out = new PrintWriter("031702106.txt");
//		PrintWriter out = new PrintWriter(args[1]);
		// Scanner in = new Scanner(System.in);
		String [] Address = new String [5000];
		for(int j=0;in.hasNext();j++)
		{
			//Address [i] = new String();
			Address [j] = in.nextLine();
		}
		int len=0;
		for (int i=0;Address[i]!=null;i++)
		{
			len++;
		}
		out.print("[\r\n");
		
		for(int i=0;i<len;i++) {
			String address = Address[i];
			String diff = address.substring(0, address.indexOf("!"));
			String address1 = address.substring(address.indexOf("!") + 1);
			String name = address1.substring(0, address1.indexOf(","));
			String address2 = address1.substring(address1.indexOf(",") + 1);
			String mobile = tel(address2);
			String address3 = address2.replace(mobile, "");
			String address4 = address3.substring(0, address3.indexOf("."));
			out.print("{\"姓名\":" + "\"" + name + "\",");
			out.print("\"手机\":" + "\"" + mobile + "\",");
			out.print("\"地址\":[");
			List<Map<String, String>> table = addressResolution(address4,diff);
			out.print("\"" + table.get(0).get("province") + "\",");
			out.print("\"" + table.get(0).get("city") + "\",");
			out.print("\"" + table.get(0).get("county") + "\",");
			out.print("\"" + table.get(0).get("town") + "\",");
			if(diff.equals("1"))
			{
				
				out.print("\"" + table.get(0).get("road") +"\"");
			}
			
			else
			{
				//List<Map<String, String>> table = addressResolution(address_4);
				
				out.print("\"" + table.get(0).get("road") + "\",");
				out.print("\"" + table.get(0).get("numb") + "\",");
				out.print("\"" + table.get(0).get("village") + "\"");
			}
			// System.out.println(table);
			if(i!=len-1)
			out.print("]},\r\n");
			else
			out.print("]}\r\n");

		}
		out.print("]");
		in.close();
		out.close();
		}
}
