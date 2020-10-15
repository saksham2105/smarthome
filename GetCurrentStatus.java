import java.util.*;
import java.io.*;
import java.net.URI;
import javax.websocket.*;
import javax.servlet.*;
import javax.servlet.http.*;

@ClientEndpoint
public class GetCurrentStatus extends HttpServlet
{
private static PrintWriter pw;
private static String msg;


public void doGet(HttpServletRequest req,HttpServletResponse res)
{
try
{

File f=new File("C:/tomcat9/webapps/one.com/WEB-INF/classes/GetCurrentStatus.txt");
BufferedReader bf=new BufferedReader(new FileReader(f));


StringBuilder sb=new StringBuilder();
msg=bf.readLine();
while((msg)!=null)
{
sb.append(msg);
msg=bf.readLine();
}

System.out.println(sb);


pw=res.getWriter();
pw.println(sb);

}catch(Exception e)
{
System.out.println(e);
}


//---------


}

}