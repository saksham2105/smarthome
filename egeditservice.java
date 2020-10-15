import java.io.*;
import java.util.*;

import java.io.FileNotFoundException; 
import java.io.PrintWriter; 
import org.json.*;
import org.json.JSONArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.*;


class egeditservice
{
public static void main(String gg[])
{
try
{
String equipName="ovn786";
int id=1;
File file=new File("C://tomcat9/webapps/one.com/WEB-INF/classes/boards.json");
BufferedReader br=new BufferedReader(new FileReader(file));
String url=br.readLine().toString();

//System.out.println(br.readLine());
//System.out.println(url);


JSONObject myJson =new JSONObject(url);
JSONArray boards=myJson.getJSONArray("rooms");
for(int i=0;i<boards.length();i++)
{
JSONObject room= boards.getJSONObject(i);
JSONArray bb=room.getJSONArray("boards");
for(int j=0;j<bb.length();j++)
{
JSONObject board=bb.getJSONObject(j);
JSONArray equipments=board.getJSONArray("equipments");
for(int k=0;k<equipments.length();k++)
{
JSONObject equipment=equipments.getJSONObject(k);
String eName=equipment.getString("name");
int eId=equipment.getInt("id");
if(id==eId )
{
 System.out.println("Mil Gaya");
equipment.put("name",equipName);
}
}
}
}
String newjson=myJson.toString();


FileWriter fw = new FileWriter("C://tomcat9/webapps/one.com/WEB-INF/classes/boards.json");
//FileWriter fw = new FileWriter("C://tomcat9/webapps/one.com/WEB-INF/classes/boards.txt");

fw.write(newjson);

fw.close();

System.out.println("Successfully updated json object to file...!!");



}catch(Exception e)
{
e.printStackTrace();
}

}

}