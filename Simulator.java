import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import com.google.gson.Gson;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.Runtime;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import javax.websocket.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@ClientEndpoint
public class Simulator 
{
private static Object waitLock = new Object();
private static java.util.List<JLabel> labels;
private static java.util.List<String> nameList;

private static java.util.List<Integer> idList;

private static JLabel labelText[];
private static JLabel logoLabel;
private static JPanel logoPanel;
private static JPanel mainPanel;
private static JPanel roomPanel[];
private static JPanel roomLogoPanel[];
private static JLabel roomLabel[];
private static JPanel boardPanel[];
private static JLabel boardLabel[];
private static JPanel equipmentPanel[];
private static JLabel equipmentLabel[];
private static JPanel boardNamePanel[];
private static JPanel roomIconPanel[];
private static JPanel loadingPanel;
private static java.util.List<Room> rooms;
private static java.util.List<Equipment> equipments;
private static java.util.List<Equipment> equips;
private static java.util.List<Board> boards;
private static JFrame frame;
private static Container cp;
private static JButton connectButton;
private static JButton disconnectButton;

private static String status;
private static String equipName;
private static int id;
static
{
frame=new JFrame();
System.out.println("FRAME OPEN");
try
{
labels=new java.util.LinkedList<JLabel>();
equips=new java.util.LinkedList<Equipment>();
Gson gson = new Gson();
Rooms rms=(Rooms)gson.fromJson(new FileReader("C://tomcat9/webapps/one.com/WEB-INF/classes/boards.json"),Rooms.class);
rooms=rms.getRooms();
frame.setSize(1300, 700);
ImageIcon img = new ImageIcon("C:\\tomcat9\\webapps\\one.com\\WEB-INF\\classes\\smartHome.png");
frame.setIconImage(img.getImage());
frame.setResizable(true);
frame.setTitle("Simulator");
//frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
roomPanel=new JPanel[rooms.size()];
roomLabel=new JLabel[rooms.size()];
roomLogoPanel=new JPanel[rooms.size()];
labelText=new JLabel[rooms.size()];
cp=frame.getContentPane();
mainPanel=new JPanel();
nameList=new LinkedList<>();

idList=new LinkedList<>();
frame.addWindowListener(new java.awt.event.WindowAdapter() {
public void windowClosing(java.awt.event.WindowEvent e) {
e.getWindow().dispose();
}
});
mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
JLabel logo2Label=new JLabel("The Smart Home Simulator");
logo2Label.setVerticalAlignment(SwingConstants.TOP);

ImageIcon logoIcon=new ImageIcon("C:\\tomcat9\\webapps\\one.com\\WEB-INF\\classes\\smarthome.gif");
logoLabel=new JLabel(logoIcon,JLabel.CENTER);
logo2Label.setLayout(new FlowLayout());
ImageIcon logoIcon1=new ImageIcon("C:\\tomcat9\\webapps\\one.com\\WEB-INF\\classes\\smarthome2.gif");
JLabel logo1Label=new JLabel(logoIcon1);
logoPanel=new JPanel();
logoPanel.setLayout(new BoxLayout(logoPanel,BoxLayout.X_AXIS));
Font ff=new Font("Serif",Font.BOLD,40);
logo2Label.setFont(ff);
logo2Label.setHorizontalTextPosition(JLabel.LEFT);
logo2Label.setForeground(Color.BLUE);
logoPanel.setBackground(Color.BLACK);

//logoPanel.add(logoLabel);
//logoPanel.add(logo1Label);

Float ratio = new Float(2);
mainPanel.add(logoPanel,ratio);
mainPanel.setLayout(new BorderLayout());
JPanel logo2Panel=new JPanel();
logo2Panel.setLayout(new FlowLayout());
logo2Panel.setBackground(Color.WHITE);
logo2Panel.setBorder(new BevelBorder(BevelBorder.RAISED));
logo2Panel.add(new JLabel(new ImageIcon("C:\\tomcat9\\webapps\\one.com\\WEB-INF\\classes\\logo1.jpg")));
logo2Panel.add(logo2Label);
mainPanel.add(logo2Panel,BorderLayout.NORTH);
mainPanel.setBackground(Color.BLACK);
mainPanel.setBackground(Color.GRAY);
mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
mainPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
for(int i=0;i<rooms.size();i++)
{
roomIconPanel=new JPanel[rooms.size()];
roomPanel[i]=new JPanel();
boards=rooms.get(i).getBoards();
boardLabel=new JLabel[boards.size()];
boardPanel=new JPanel[boards.size()];
boardNamePanel=new JPanel[boards.size()];
roomPanel[i].setBackground(new Color(196,223,230));
roomPanel[i].setLayout(new BoxLayout(roomPanel[i],BoxLayout.Y_AXIS));
roomLabel[i]=new JLabel(new ImageIcon("C:\\tomcat9\\webapps\\one.com\\WEB-INF\\classes\\"+ rooms.get(i).getName()+".png"));
labelText[i]=new JLabel();
Font f=new Font("Verdana",Font.BOLD,28);
labelText[i].setFont(f);
labelText[i].setText("Room-Name : "+rooms.get(i).getName());
roomIconPanel[i]=new JPanel();
roomIconPanel[i].add(roomLabel[i]);
roomIconPanel[i].add(labelText[i]);
roomPanel[i].add(roomIconPanel[i],BorderLayout.CENTER);
for(int j=0;j<boards.size();j++)
{
boardLabel[j]=new JLabel("Board Name : "+boards.get(j).getName());
Font font=new Font("Verdana",Font.BOLD,22);
boardLabel[j].setFont(font);
boardLabel[j].setForeground(Color.BLACK);
boardNamePanel[j]=new JPanel();
boardNamePanel[j].add(boardLabel[j]);
boardPanel[j]=new JPanel();
boardPanel[j].setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
equipments=boards.get(j).getEquipments();
boardPanel[j].setLayout(null);
if(equipments.size()%2==0) boardPanel[j].setLayout(new GridLayout(equipments.size()/2,2));
boardPanel[j].setLayout(new GridLayout((equipments.size()/2)+1,2));
for(int y=0;y<equipments.size();y++)
{
equips.add(equipments.get(y));
nameList.add(equipments.get(y).getName());
idList.add(equipments.get(y).getId());
}
equipmentLabel=new JLabel[equipments.size()];
equipmentPanel=new JPanel[equipments.size()];
for(int k=0;k<equipments.size();k++)
{
equipmentLabel[k]=new JLabel(new ImageIcon("C:\\tomcat9\\webapps\\one.com\\WEB-INF\\classes\\"+equipments.get(k).getType()+".png"));
labels.add(equipmentLabel[k]);
if(equipments.get(k).getStatus()==0 && equipments.get(k).getType().equals("fan")==false)
{
equipmentLabel[k].setText("Appliance-Name : "+equipments.get(k).getName()+","+"status : OFF");
equipmentLabel[k].setForeground(Color.RED);
}
if(equipments.get(k).getStatus()==1 && equipments.get(k).getType().equals("fan")==false)
{
equipmentLabel[k].setText("Appliance-Name: "+equipments.get(k).getName()+","+"status : ON");
equipmentLabel[k].setForeground(new Color(20,210,26));
}
if(equipments.get(k).getStatus()==0 && equipments.get(k).getType().equals("fan"))
{
equipmentLabel[k].setText("Appliance-Name: "+equipments.get(k).getName()+","+"status : OFF"+","+"speed : 0");
equipmentLabel[k].setForeground(Color.RED);
}
if(equipments.get(k).getStatus()==1 && equipments.get(k).getType().equals("fan"))
{
equipmentLabel[k].setText("Appliance-Name : "+equipments.get(k).getName()+","+"status : OFF"+","+"speed : 0");
equipmentLabel[k].setForeground(new Color(20,210,26));
}
font=new Font("Verdana",Font.BOLD,16);
equipmentLabel[k].setFont(font);
equipmentPanel[k]=new JPanel();
equipmentPanel[k].setBackground(Color.WHITE);
equipmentPanel[k].setBorder(new BevelBorder(BevelBorder.RAISED));
equipmentPanel[k].add(equipmentLabel[k]);
boardPanel[j].add(equipmentPanel[k]);

}

roomPanel[i].add(boardNamePanel[j]);
roomPanel[i].add(boardPanel[j]);
}



mainPanel.add(roomPanel[i]);

}
JScrollPane pane=new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth(),1740*rooms.size()));
//1740

pane.setPreferredSize(new Dimension(500,500));
pane.setViewportView(mainPanel);
cp.add(pane);
frame.setVisible(true);
}catch (IOException e) 
{  
e.printStackTrace();  
}


}

@OnMessage
public void onMessage(String message) 
{
//old
int Tstatus=0;
String comma=",";
char c=comma.charAt(0);
for(int x=0;x<message.length();++x)
{
if(message.charAt(x)==c)
{
equipName=message.substring(0,x);

//id=Integer.parseInt(message.substring(0,x));

status=message.substring(x+1,x+2);
id=Integer.parseInt(message.substring(x+3,message.length()));

break;
}
}
//------------------------
//System.out.println(equipName+" , "+status+" , "+id);
for(int s=0;s<idList.size();++s)
{

if(idList.get(s)==id && equips.get(s).getStatus()==Integer.parseInt(status))
{

equips.get(s).setName(equipName);
nameList.set(s,equipName);

if(equips.get(s).getStatus()==0) labels.get(s).setText("Appliance-Name : "+equips.get(s).getName()+","+"status : OFF");
if(equips.get(s).getStatus()==1) labels.get(s).setText("Appliance-Name : "+equips.get(s).getName()+","+"status : ON");
break;
}
}
//done done
for(int s=0;s<nameList.size();++s)
{

if(nameList.get(s).equalsIgnoreCase(equipName))
{
//System.out.println("Jis index par mila : "+s);
if(status.trim().equals("0"))
{
equips.get(s).setStatus(0);

Tstatus=0;
//System.out.println("Status 0 hai");
//System.out.println(labels.get(s).getText());
labels.get(s).setForeground(Color.RED);
if(equips.get(s).getStatus()==0) labels.get(s).setText("Appliance-Name : "+equips.get(s).getName()+","+"status : OFF");
if(equips.get(s).getStatus()==1) labels.get(s).setText("Appliance-Name : "+equips.get(s).getName()+","+"status : ON");

break;
}
if(status.trim().equals("1"))
{
equips.get(s).setStatus(1);
Tstatus=1;
//System.out.println("Status 1 hai");
//System.out.println(labels.get(s).getText());
labels.get(s).setForeground(new Color(20,210,26));
if(equips.get(s).getStatus()==0) labels.get(s).setText("Appliance-Name : "+equips.get(s).getName()+","+"status : OFF");
if(equips.get(s).getStatus()==1) labels.get(s).setText("Appliance-Name : "+equips.get(s).getName()+","+"status : ON");
break;
}
}
}


//starts changes
//baylor edu
/*
try
{

JSONParser jp=new JSONParser();
 
JSONObject jsonObject = (JSONObject) readJsonSimpleDemo("boards.json");
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
if((Tequip.get("name").toString()).equalsIgnoreCase(equipName))
{
System.out.println(equipName+", Status : "+Tstatus);
Tequip.put("status",Tstatus);
try(FileWriter ff=new FileWriter("boards.json"))
{
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


*/




//ends






//System.out.println(equipName);




}
private static void  wait4TerminateSignal()
{
synchronized(waitLock)
{
try 
{
waitLock.wait();
}catch(InterruptedException e) 
{    
}
}
}
public static Object readJsonSimpleDemo(String filename) throws Exception {  
    FileReader reader = new FileReader(filename);
    JSONParser jsonParser = new JSONParser();
    return jsonParser.parse(reader);
}

public static void main(String g[])
{
Session session=null;
try
{
//Simulator s=new Simulator();
System.out.println("Simulator OBJECT CREATED");
WebSocketContainer container=null;

container = ContainerProvider.getWebSocketContainer(); 
session=container.connectToServer(Simulator.class, java.net.URI.create("ws://localhost:8080/one.com/hello")); 
wait4TerminateSignal();
}catch (Exception e) 
{
e.printStackTrace();
}


finally
{
if(session!=null)
{
try 
{
session.close();
}catch (Exception e) 
{     
e.printStackTrace();
}
}}
}
}
