import javax.servlet.*;
import javax.servlet.http.*;
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

public class EditNameService extends HttpServlet
{
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{

System.out.println("Request Arrived for updating JSON");
try
{
String equipName=rq.getParameter("equipName");
String idd=rq.getParameter("equipId");
int id=Integer.parseInt(idd);
System.out.println(equipName);
System.out.println(id);

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

int eId=equipment.getInt("id");
if(id==eId) 
{
System.out.println("FOUND");
equipment.put("name",equipName);
}
}
}
}
String newjson=myJson.toString();


FileWriter fw = new FileWriter("C://tomcat9/webapps/one.com/WEB-INF/classes/boards.json");
fw.write(newjson);

fw.close();

System.out.println("Successfully updated json object to file...!!");


}catch(Exception e)
{
e.printStackTrace();
}

}
}