import javax.swing.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;
import javax.websocket.*;
import javax.websocket.server.*;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@ServerEndpoint("/hello")
public class HomeService extends HttpServlet
{
private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
private static Thread rateThread ;
private static int count=0;
private static String one="1";
private static String zero="0";
private static String name=null;
private static String idd=null;
private static int id;
//Change must be accordingly ID

private static ArrayList<Session > closedSessions;
private static String status=null;
static
{
rateThread=new Thread()
{
public void run() 
{
DecimalFormat df = new DecimalFormat("#.####");
while(true)
{
//double d=2+Math.random();     
if(queue!=null)
{
if(status==null) continue;
//System.out.println("For 0 : "+status.equals("0"));
//System.out.println("For 1 : "+status.equals("1"));
//System.out.println("Status : "+status);
if(status.equals("0")) sendAll("Status: "+one+","+"equipName : "+name+","+"Id : "+id);        
if(status.equals("1")) sendAll("Status: "+zero+","+"equipName : "+name+","+"Id : "+id);        
}
try 
{
sleep(2000);
}catch(InterruptedException e) 
{      
}
}
};
};
rateThread.start();
}
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
System.out.println("Request Arrived");
name=rq.getParameter("equipName");
status=rq.getParameter("status");
idd=rq.getParameter("equipId");
id=Integer.parseInt(idd);
String msg=name+","+id+","+status;
try
{
System.out.println("File Writer Above");
JSONObject latestEquipment = new JSONObject();
    	latestEquipment.put("name", name);
    	latestEquipment.put("code", idd);
    	latestEquipment.put("status", status);

FileWriter f=new FileWriter("C://tomcat9/webapps/one.com/WEB-INF/classes/GetCurrent.txt");

f.write(latestEquipment.toString());
f.close();
System.out.println("File Writer SUCCESS");

}catch(Exception e)
{
e.printStackTrace();
}
// SIMULATOR
try
{

JSONParser jp=new JSONParser();
 
JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("c://tomcat9/webapps/one.com/WEB-INF/classes/boards.json");
JSONArray jsonArray=(JSONArray) jsonObject.get("rooms");
int i=0;
//System.out.println(jsonArray.size()); 
//System.out.println("---------------------"); 

while(i<jsonArray.size())
{
JSONObject Tnm = (JSONObject) jsonArray.get(i);
JSONArray Tboards=(JSONArray) Tnm.get("boards");
for(int j=0;j<Tboards.size();j++)
{
JSONObject Tboard=(JSONObject) Tboards.get(j);
//String name=Tboard.get("name").toString();
//System.out.println(name);
JSONArray Tequips=(JSONArray) Tboard.get("equipments");
for(int k=0;k<Tequips.size();k++)
{
JSONObject Tequip=(JSONObject) Tequips.get(k);

System.out.println(name);

if((Tequip.get("name").toString()).equalsIgnoreCase(name))
{
	System.out.println("COME HERE TO CHANGE");
System.out.println(name+", Status : "+status);
if(Integer.parseInt(status)==0){
System.out.println("GOOOD 0");
Tequip.put("status",1);
} 
if(Integer.parseInt(status)==1){
System.out.println("GOOOD 1");
Tequip.put("status",0);
} 

try(FileWriter ff=new FileWriter("c://tomcat9/webapps/one.com/WEB-INF/classes/boards.json"))
{
	System.out.println(jsonObject.toJSONString());
ff.write(jsonObject.toJSONString());
}
}



//if((Tequip.get("type").toString()).equalsIgnoreCase("bulb")) System.out.println(equip.get("status").toString());
//System.out.println(equip);

//System.out.println("-------------------------------");
}
}
//System.out.println(Tnm);
i++;
}
//System.out.println(jsonObject);



}catch(Exception e)
{
e.printStackTrace();
}


//SIMULATOR


System.out.println("EquipName : "+name+"Status : "+status+" Id :"+id);
}

public static Object readJsonSimpleDemo(String filename) throws Exception {  
    FileReader reader = new FileReader(filename);
    JSONParser jsonParser = new JSONParser();
    return jsonParser.parse(reader);
}
@OnMessage
public void onMessage(Session session, String msg) {
try 
{   

//System.out.println("received msg "+msg+" from "+session.getId());
}catch(Exception e) 
{
e.printStackTrace();
}
}
@OnOpen
public void open(Session session) 
{
queue.add(session);
//System.out.println("New session opened: "+session.getId());
}
@OnError
public void error(Session session, Throwable t) {
queue.remove(session);
//System.err.println("Error on session "+session.getId());  
}
@OnClose
public static void closedConnection(Session session) { 
queue.remove(session);
//System.out.println("session closed: "+session.getId());
}
private static void sendAll(String msg) 
{
try 
{
closedSessions= new ArrayList<>();
for(Session session : queue) 
{
if(!session.isOpen())
{
//System.err.println("Closed session: "+session.getId());
closedSessions.add(session);
}
else
{
if(status.equals("0")) session.getBasicRemote().sendText(name+","+one+","+id);        
if(status.equals("1")) session.getBasicRemote().sendText(name+","+zero+","+id);        
}    
}
queue.removeAll(closedSessions);
//System.out.println("Sending "+msg+" to "+queue.size()+" clients");
}catch(Throwable e) 
{
e.printStackTrace();
}
}

}