import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;
import com.google.gson.Gson;
import javax.websocket.*;
import javax.websocket.server.*;


@ServerEndpoint("/jsonParserWeb")
public class JsonParserEg extends HttpServlet
{
private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
private static Thread rateThread ;
private static ArrayList<Session > closedSessions;


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
//sending json as msg
StringBuffer sb=new StringBuffer();
try
{
File file=new File("C://tomcat9/webapps/one.com/WEB-INF/classes/boards.json");
BufferedReader br=new BufferedReader(new FileReader(file));
String st;
while((st=br.readLine())!=null)
{
sb.append(st);
}
}catch(Exception e)
{
e.printStackTrace();
}
String s=sb.toString();
System.out.println("JSON Successfully sent to Send ALL");


sendAll(s);        
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
//----------------
@OnMessage
public void onMessage(Session session, String msg) {
try 
{   
//nothing
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
//---------------------------------
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
//read json should be sent
StringBuffer sb=new StringBuffer();

try
{
File file=new File("C://tomcat9/webapps/one.com/WEB-INF/classes/boards.json");
BufferedReader br=new BufferedReader(new FileReader(file));
String st;
while((st=br.readLine())!=null)
{
sb.append(st);
}
}catch(Exception e)
{
e.printStackTrace();
}
String s=sb.toString();

System.out.println("JSON Successfully sent");
session.getBasicRemote().sendText(s);        
}    
}
queue.removeAll(closedSessions);
//System.out.println("Sending "+msg+" to "+queue.size()+" clients");
}catch(Throwable e) 
{
e.printStackTrace();
}
}









//------------------------
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{

System.out.println("Request Arrived Sending JSON");
try
{
rs.setContentType("text/html");
PrintWriter pw=rs.getWriter();

File file=new File("C://tomcat9/webapps/one.com/WEB-INF/classes/boards.json");
BufferedReader br=new BufferedReader(new FileReader(file));
String st;
while((st=br.readLine())!=null)
{
pw.println(st);
}
}catch(Exception e)
{
e.printStackTrace();
}

}
}