import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import javax.swing.border.*;
import javax.swing.border.Border;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class Installer
{
private JTextField regulationLevelField;
private JButton install;
private JButton addInEquipments;
private JButton addInBoards;
private Room room;
private Board board;
private Equipment equipment;
private java.util.List<Room> rooms;
private java.util.List<Board> boards;
private java.util.List<Equipment> equipments;
private JButton addInBoard;
private JTextField equipmentIdField;
private JTextField equipmentNameField;
private JTextField equipmentTypeField;
private JTextField equipmentStatusField;
private JTextField equipmentMinField;
private JTextField equipmentMaxField;
private JCheckBox isLevelCheckBox;
private JTextField illuminationLevelField;
private JTextField regulatorLevelField;
private JTextField temperatureLevelField;
private JButton addRoom;
private JButton addInRoom;
private JButton proceedBoard;
private JTextField boardIdField;
private JTextField boardNameField;
private Font font;
private Border loweredBevel;
private JPanel secondPanel;
private JFrame frame;
private JPanel mainPanel;
private JLabel logoLabel;
private JPanel roomPanel;
private JTextField roomIdField;
private JTextField roomNameField;
private JPanel boardPanel;
private JPanel equipmentPanel;
private JPanel logoPanel;
private JComboBox comboBox;
private JButton proceed;
public Installer()
{
font=new Font("Serif",Font.BOLD,20);
Border raisedbevel = BorderFactory.createRaisedBevelBorder();
Border loweredbevel = BorderFactory.createLoweredBevelBorder();
frame=new JFrame("Installer");
frame.setSize(700,700);
frame.setLocation(10,10);
frame.setResizable(false);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
loweredBevel=BorderFactory.createRaisedBevelBorder();
mainPanel=new JPanel();
mainPanel.setPreferredSize(new Dimension(700,800));
mainPanel.setBorder(BorderFactory.createRaisedBevelBorder());
mainPanel.setBackground(Color.WHITE);
mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
frame.add(mainPanel);
ImageIcon logoIcon=new ImageIcon("installer.png");
logoPanel=new JPanel();
logoPanel.setBackground(Color.WHITE);
logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
logoPanel.setPreferredSize(new Dimension(530,160));
logoLabel=new JLabel(logoIcon);
logoPanel.add(logoLabel);
mainPanel.add(logoPanel);
mainPanel.add(new JLabel());
secondPanel=new JPanel();
secondPanel.setPreferredSize(new Dimension(500,1000));
secondPanel.setBackground(Color.WHITE);
secondPanel.setLayout(new BoxLayout(secondPanel,BoxLayout.Y_AXIS));
roomPanel=new JPanel();
roomPanel.setPreferredSize(new Dimension(500,350));
roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
roomPanel.setBackground(Color.WHITE);
JPanel roomIdTextFieldPanel=new JPanel();
roomIdTextFieldPanel.setPreferredSize(new Dimension(500,30));
roomIdTextFieldPanel.setLayout(new FlowLayout());
roomIdTextFieldPanel.setBackground(Color.WHITE);
JLabel roomIdLabel=new JLabel("Enter Room Id : ");
roomIdLabel.setForeground(Color.BLUE);
roomIdLabel.setFont(font);
roomIdTextFieldPanel.add(roomIdLabel);
roomIdField=new JTextField(15);
roomIdField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
roomIdTextFieldPanel.add(roomIdField);
roomPanel.add(roomIdTextFieldPanel);
JPanel roomNameTextFieldPanel=new JPanel();
roomNameTextFieldPanel.setPreferredSize(new Dimension(500,30));
roomNameTextFieldPanel.setLayout(new FlowLayout());
roomNameTextFieldPanel.setBackground(Color.WHITE);
JLabel roomNameLabel=new JLabel("Enter Room name : ");
roomNameLabel.setForeground(Color.BLUE);
roomNameLabel.setFont(font);
roomNameTextFieldPanel.add(roomNameLabel);
roomNameField=new JTextField(15);
roomNameField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
roomNameTextFieldPanel.add(roomNameField);
roomNameTextFieldPanel.add(new JLabel());
roomNameTextFieldPanel.add(new JLabel());
roomNameTextFieldPanel.add(new JLabel());
roomNameTextFieldPanel.add(new JLabel());
roomNameTextFieldPanel.add(new JLabel());
roomNameTextFieldPanel.add(new JLabel());
roomPanel.add(roomNameTextFieldPanel);
JPanel proceedButtonPanel=new JPanel();
proceedButtonPanel.setLayout(new FlowLayout());
proceedButtonPanel.setPreferredSize(new Dimension(500,80));
proceedButtonPanel.setBackground(Color.WHITE);
Icon proceedIcon=new ImageIcon("next.png");
proceed=new JButton();
proceed.setIcon(proceedIcon);
proceed.setContentAreaFilled(false);
proceed.setBorder(null);
proceedButtonPanel.add(proceed);
addRoom=new JButton("Add Room");
addRoom.setEnabled(false);
proceedButtonPanel.add(addRoom);
Icon installIcon=new ImageIcon("install.png");
install=new JButton();
install.setIcon(installIcon);
install.setContentAreaFilled(false);
install.setBorder(null);
install.setEnabled(false);
proceedButtonPanel.add(install);
roomPanel.add(proceedButtonPanel);
secondPanel.add(roomPanel);
boardPanel=new JPanel();
boardPanel.setPreferredSize(new Dimension(500,330));
boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
boardPanel.setBackground(Color.WHITE);
JPanel boardIdTextFieldPanel=new JPanel();
boardIdTextFieldPanel.setPreferredSize(new Dimension(500,30));
boardIdTextFieldPanel.setLayout(new FlowLayout());
boardIdTextFieldPanel.setBackground(Color.WHITE);
JLabel boardIdLabel=new JLabel("Enter Board Id : ");
boardIdLabel.setForeground(Color.BLUE);
boardIdLabel.setFont(font);
boardIdTextFieldPanel.add(boardIdLabel);
boardIdField=new JTextField(15);
boardIdField.setEnabled(false);
boardIdField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
boardIdTextFieldPanel.add(boardIdField);
boardPanel.add(boardIdTextFieldPanel);
JPanel boardNameTextFieldPanel=new JPanel();
boardNameTextFieldPanel.setPreferredSize(new Dimension(500,30));
boardNameTextFieldPanel.setLayout(new FlowLayout());
boardNameTextFieldPanel.setBackground(Color.WHITE);
JLabel boardNameLabel=new JLabel("Enter Board name : ");
boardNameLabel.setForeground(Color.BLUE);
boardNameLabel.setFont(font);
boardNameTextFieldPanel.add(boardNameLabel);
boardNameField=new JTextField(15);
boardNameField.setEnabled(false);
boardNameField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
boardNameTextFieldPanel.add(boardNameField);
boardNameTextFieldPanel.add(new JLabel());
boardNameTextFieldPanel.add(new JLabel());
boardNameTextFieldPanel.add(new JLabel());
boardNameTextFieldPanel.add(new JLabel());
boardNameTextFieldPanel.add(new JLabel());
boardNameTextFieldPanel.add(new JLabel());
boardPanel.add(boardNameTextFieldPanel);
JPanel proceedBoardButtonPanel=new JPanel();
proceedBoardButtonPanel.setLayout(new FlowLayout());
proceedBoardButtonPanel.setPreferredSize(new Dimension(500,70));
proceedBoardButtonPanel.setBackground(Color.WHITE);
Icon proceedBoardIcon=new ImageIcon("next.png");
proceedBoard=new JButton();
proceedBoard.setEnabled(false);
proceedBoard.setIcon(proceedBoardIcon);
proceedBoard.setContentAreaFilled(false);
proceedBoard.setBorder(null);
proceedBoardButtonPanel.add(proceedBoard);
addInRoom=new JButton("Add in room");
addInRoom.setEnabled(false);
proceedBoardButtonPanel.add(addInRoom);
addInBoards=new JButton("Add in Boards");
addInBoards.setEnabled(false);
proceedBoardButtonPanel.add(addInBoards);
boardPanel.add(proceedBoardButtonPanel);
secondPanel.add(boardPanel);
equipmentPanel=new JPanel();
equipmentPanel.setPreferredSize(new Dimension(500,1200));
equipmentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
equipmentPanel.setBackground(Color.WHITE);
JPanel equipmentIdFieldPanel=new JPanel();
equipmentIdFieldPanel.setPreferredSize(new Dimension(500,30));
equipmentIdFieldPanel.setLayout(new FlowLayout());
equipmentIdFieldPanel.setBackground(Color.WHITE);
JLabel equipmentIdLabel=new JLabel("Enter equipment id : ");
equipmentIdLabel.setForeground(Color.BLUE);
equipmentIdLabel.setFont(font);
equipmentIdFieldPanel.add(equipmentIdLabel);
equipmentIdField=new JTextField(15);
equipmentIdField.setEnabled(false);
equipmentIdField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
equipmentIdFieldPanel.add(equipmentIdField);
equipmentPanel.add(equipmentIdFieldPanel);
JPanel equipmentNameFieldPanel=new JPanel();
equipmentNameFieldPanel.setPreferredSize(new Dimension(500,30));
equipmentNameFieldPanel.setLayout(new FlowLayout());
equipmentNameFieldPanel.setBackground(Color.WHITE);
JLabel equipmentNameLabel=new JLabel("Enter equipment name : ");
equipmentNameLabel.setForeground(Color.BLUE);
equipmentNameLabel.setFont(font);
equipmentNameFieldPanel.add(equipmentNameLabel);
equipmentNameField=new JTextField(15);
equipmentNameField.setEnabled(false);
equipmentNameField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
equipmentNameFieldPanel.add(equipmentNameField);
equipmentNameFieldPanel.add(new JLabel());
equipmentNameFieldPanel.add(new JLabel());
equipmentNameFieldPanel.add(new JLabel());
equipmentNameFieldPanel.add(new JLabel());
equipmentNameFieldPanel.add(new JLabel());
equipmentNameFieldPanel.add(new JLabel());
equipmentPanel.add(equipmentNameFieldPanel);
JPanel equipmentTypeFieldPanel=new JPanel();
equipmentTypeFieldPanel.setPreferredSize(new Dimension(500,30));
equipmentTypeFieldPanel.setLayout(new FlowLayout());
equipmentTypeFieldPanel.setBackground(Color.WHITE);
JLabel equipmentTypeLabel=new JLabel("Enter equipment type : ");
equipmentTypeLabel.setForeground(Color.BLUE);
equipmentTypeLabel.setFont(font);
equipmentTypeFieldPanel.add(equipmentTypeLabel);
equipmentTypeField=new JTextField(15);
equipmentTypeField.setEnabled(false);
equipmentTypeField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
equipmentTypeFieldPanel.add(equipmentTypeField);
equipmentTypeFieldPanel.add(new JLabel());
equipmentTypeFieldPanel.add(new JLabel());
equipmentTypeFieldPanel.add(new JLabel());
equipmentTypeFieldPanel.add(new JLabel());
equipmentTypeFieldPanel.add(new JLabel());
equipmentPanel.add(equipmentTypeFieldPanel);
JPanel equipmentStatusFieldPanel=new JPanel();
equipmentStatusFieldPanel.setPreferredSize(new Dimension(500,30));
equipmentStatusFieldPanel.setLayout(new FlowLayout());
equipmentStatusFieldPanel.setBackground(Color.WHITE);
JLabel equipmentStatusLabel=new JLabel("Enter equipment status : ");
equipmentStatusLabel.setForeground(Color.BLUE);
equipmentStatusLabel.setFont(font);
equipmentStatusFieldPanel.add(equipmentStatusLabel);
equipmentStatusField=new JTextField(15);
equipmentStatusField.setEnabled(false);
equipmentStatusField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
equipmentStatusFieldPanel.add(equipmentStatusField);
equipmentStatusFieldPanel.add(new JLabel());
equipmentStatusFieldPanel.add(new JLabel());
equipmentStatusFieldPanel.add(new JLabel());
equipmentStatusFieldPanel.add(new JLabel());
equipmentStatusFieldPanel.add(new JLabel());
equipmentStatusFieldPanel.add(new JLabel());
equipmentStatusFieldPanel.add(new JLabel());
equipmentPanel.add(equipmentStatusFieldPanel);
JPanel equipmentLevelPanel=new JPanel();
equipmentLevelPanel.add(new JLabel());
equipmentLevelPanel.add(new JLabel());
equipmentLevelPanel.add(new JLabel());
isLevelCheckBox=new JCheckBox("isLevel");
isLevelCheckBox.setBackground(Color.WHITE);
isLevelCheckBox.setEnabled(false);
equipmentLevelPanel.setPreferredSize(new Dimension(500,30));
equipmentLevelPanel.setLayout(new FlowLayout());
equipmentLevelPanel.setBackground(Color.WHITE);
JLabel illuminationLevelLabel=new JLabel("Enter illumination level : ");
illuminationLevelLabel.setForeground(Color.BLUE);
illuminationLevelLabel.setFont(font);
equipmentLevelPanel.add(illuminationLevelLabel);
illuminationLevelField=new JTextField(15);
illuminationLevelField.setEnabled(false);
illuminationLevelField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
equipmentLevelPanel.add(illuminationLevelField);
equipmentLevelPanel.add(isLevelCheckBox);
equipmentPanel.add(equipmentLevelPanel);




JPanel regulationFieldPanel=new JPanel();
regulationFieldPanel.setPreferredSize(new Dimension(500,30));
regulationFieldPanel.setLayout(new FlowLayout());
regulationFieldPanel.setBackground(Color.WHITE);
JLabel regulationFieldLabel=new JLabel("Enter regulation level : ");
regulationFieldLabel.setForeground(Color.BLUE);
regulationFieldLabel.setFont(font);
regulationFieldPanel.add(regulationFieldLabel);
regulationLevelField=new JTextField(15);
regulationLevelField.setEnabled(false);
regulationLevelField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
regulationFieldPanel.add(regulationLevelField);
regulationFieldPanel.add(new JLabel());
regulationFieldPanel.add(new JLabel());
regulationFieldPanel.add(new JLabel());
regulationFieldPanel.add(new JLabel());
regulationFieldPanel.add(new JLabel());
regulationFieldPanel.add(new JLabel());
equipmentPanel.add(regulationFieldPanel);
//done done
JPanel temperatureFieldPanel=new JPanel();
temperatureFieldPanel.setPreferredSize(new Dimension(500,30));
temperatureFieldPanel.setLayout(new FlowLayout());
temperatureFieldPanel.setBackground(Color.WHITE);
JLabel temperatureFieldLabel=new JLabel("Enter temperature level : ");
temperatureFieldLabel.setForeground(Color.BLUE);
temperatureFieldLabel.setFont(font);
temperatureFieldPanel.add(temperatureFieldLabel);
temperatureLevelField=new JTextField(15);
temperatureLevelField.setEnabled(false);
temperatureLevelField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
temperatureFieldPanel.add(temperatureLevelField);
temperatureFieldPanel.add(new JLabel());
temperatureFieldPanel.add(new JLabel());
temperatureFieldPanel.add(new JLabel());
temperatureFieldPanel.add(new JLabel());
temperatureFieldPanel.add(new JLabel());
temperatureFieldPanel.add(new JLabel());
temperatureFieldPanel.add(new JLabel());
temperatureFieldPanel.add(new JLabel());

equipmentPanel.add(temperatureFieldPanel);





JPanel equipmentMinFieldPanel=new JPanel();
equipmentMinFieldPanel.setPreferredSize(new Dimension(500,30));
equipmentMinFieldPanel.setLayout(new FlowLayout());
equipmentMinFieldPanel.setBackground(Color.WHITE);
JLabel equipmentMinLabel=new JLabel("Enter minimum speed : ");
equipmentMinLabel.setForeground(Color.BLUE);
equipmentMinLabel.setFont(font);
equipmentMinFieldPanel.add(equipmentMinLabel);
equipmentMinField=new JTextField(15);
equipmentMinField.setEnabled(false);
equipmentMinField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
equipmentMinFieldPanel.add(equipmentMinField);
equipmentMinFieldPanel.add(new JLabel());
equipmentMinFieldPanel.add(new JLabel());
equipmentMinFieldPanel.add(new JLabel());
equipmentMinFieldPanel.add(new JLabel());
equipmentMinFieldPanel.add(new JLabel());
equipmentMinFieldPanel.add(new JLabel());
equipmentPanel.add(equipmentMinFieldPanel);
JPanel equipmentMaxFieldPanel=new JPanel();
equipmentMaxFieldPanel.setPreferredSize(new Dimension(500,30));
equipmentMaxFieldPanel.setLayout(new FlowLayout());
equipmentMaxFieldPanel.setBackground(Color.WHITE);
JLabel equipmentMaxLabel=new JLabel("Enter maximum speed : ");
equipmentMaxLabel.setForeground(Color.BLUE);
equipmentMaxLabel.setFont(font);
equipmentMaxFieldPanel.add(equipmentMaxLabel);
equipmentMaxField=new JTextField(15);
equipmentMaxField.setEnabled(false);
equipmentMaxField.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
equipmentMaxFieldPanel.add(equipmentMaxField);
equipmentMaxFieldPanel.add(new JLabel());
equipmentMaxFieldPanel.add(new JLabel());
equipmentMaxFieldPanel.add(new JLabel());
equipmentMaxFieldPanel.add(new JLabel());
equipmentMaxFieldPanel.add(new JLabel());
equipmentMaxFieldPanel.add(new JLabel());
equipmentPanel.add(equipmentMaxFieldPanel);
JPanel equipmentButtonPanel=new JPanel();
equipmentButtonPanel.setPreferredSize(new Dimension(500,40));
equipmentButtonPanel.setLayout(new FlowLayout());
equipmentButtonPanel.setBackground(Color.WHITE);
addInBoard=new JButton("Add in board");
addInBoard.setEnabled(false);
equipmentButtonPanel.add(addInBoard);
addInEquipments=new JButton("Add in equipments");
addInEquipments.setEnabled(false);
equipmentButtonPanel.add(addInEquipments);
equipmentPanel.add(equipmentButtonPanel);
secondPanel.add(equipmentPanel);
rooms=new LinkedList<>();
mainPanel.add(secondPanel);
proceed.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(roomIdField.getText().equals("") || roomNameField.getText().equals(""))
{
JOptionPane.showMessageDialog(frame,"Please fill all the required details about room properly");
return;
}
room=new Room();
room.setId(Integer.parseInt(roomIdField.getText()));
room.setName(roomNameField.getText());
boards=new LinkedList<>();
proceedBoard.setEnabled(true);
addRoom.setEnabled(false);
install.setEnabled(false);
boardIdField.setEnabled(true);
boardNameField.setEnabled(true);
proceed.setEnabled(false);
roomNameField.setEnabled(false);
roomIdField.setEnabled(false);
}
});
proceedBoard.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(boardIdField.getText().equals("") || boardNameField.getText().equals(""))
{
JOptionPane.showMessageDialog(frame,"Please fill all the required details about board properly");
return;
}
equipmentMaxField.setEnabled(true);
equipmentMinField.setEnabled(true);
//regulationLevelField.setEnabled(true);
//temperatureLevelField.setEnabled(true);

board=new Board();
board.setId(Integer.parseInt(boardIdField.getText()));
board.setName(boardNameField.getText());
equipments=new LinkedList<>();
equipmentIdField.setEnabled(true);
equipmentNameField.setEnabled(true);
equipmentTypeField.setEnabled(true);
equipmentStatusField.setEnabled(true);
isLevelCheckBox.setEnabled(true);
addInEquipments.setEnabled(true);
boardIdField.setEnabled(false);
boardNameField.setEnabled(false);
proceedBoard.setEnabled(false);
addInBoards.setEnabled(false);
addInRoom.setEnabled(false);
}
});
addRoom.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
rooms.add(room);
proceed.setEnabled(true);
addRoom.setEnabled(false);
install.setEnabled(true);
roomIdField.setText("");
roomNameField.setText("");
roomIdField.setEnabled(true);
roomNameField.setEnabled(true);

JOptionPane.showMessageDialog(frame,"Succesfully added room");
}
});
install.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
generateJSON();
JOptionPane.showMessageDialog(frame,"Successfully generated file");
install.setEnabled(false);
roomIdField.setText("");
roomNameField.setText("");
}
});
addInRoom.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
room.setBoards(boards);
addRoom.setEnabled(true);
proceed.setEnabled(false);
boardNameField.setText("");
addInBoards.setEnabled(false);
proceedBoard.setEnabled(false);
addInRoom.setEnabled(false);
boardIdField.setText("");
boardNameField.setEnabled(false);
boardIdField.setEnabled(false);
proceed.setEnabled(true);
JOptionPane.showMessageDialog(frame,"Successfully added boards in room");
}
});
addInEquipments.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(equipmentIdField.getText().length()==0 || equipmentNameField.getText().length()==0 || equipmentTypeField.getText().length()==0 || equipmentStatusField.getText().length()==0)
{
JOptionPane.showMessageDialog(frame,"Please fill all the required details for equipments");
return;
}
equipment=new Equipment();
equipment.setId(Integer.parseInt(equipmentIdField.getText()));
equipment.setName(equipmentNameField.getText());
equipment.setStatus(Integer.parseInt(equipmentStatusField.getText()));
equipment.setType(equipmentTypeField.getText());
equipment.setIsLevel(false);
equipment.setIlluminationLevel(0);
equipment.setRegulatorLevel(0);
if(isLevelCheckBox.isSelected())
{
if(equipmentMinField.getText().length()==0 || equipmentMaxField.getText().length()==0 || illuminationLevelField.getText().length()==0)
{
JOptionPane.showMessageDialog(frame,"Please fill all the required fields for equipments");
return;
}
equipment.setMin(Integer.parseInt(equipmentMinField.getText()));
equipment.setMax(Integer.parseInt(equipmentMaxField.getText()));
equipment.setIsLevel(true);
if(regulationLevelField.getText().length()==0) equipment.setIlluminationLevel(Integer.parseInt(illuminationLevelField.getText()));
if(temperatureLevelField.getText().length()==0) equipment.setIlluminationLevel(Integer.parseInt(illuminationLevelField.getText()));

if(illuminationLevelField.getText().length()==0) equipment.setRegulatorLevel(Integer.parseInt(illuminationLevelField.getText()));
}
equipments.add(equipment);
equipmentIdField.setText("");
equipmentNameField.setText("");
equipmentTypeField.setText("");
equipmentStatusField.setText("");
equipmentMinField.setText("");
equipmentMaxField.setText("");
illuminationLevelField.setText("");
regulationLevelField.setText("");
temperatureLevelField.setText("");

addInBoard.setEnabled(true);
JOptionPane.showMessageDialog(frame,"Successfully added in equipment list");
}
});
addInBoards.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
addInRoom.setEnabled(true);
boards.add(board);
JOptionPane.showMessageDialog(frame,"Successfully added in board list");
}
});
addInBoard.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
addInBoards.setEnabled(true);
board.setEquipments(equipments);
equipmentIdField.setText("");
equipmentNameField.setText("");
equipmentTypeField.setText("");
equipmentStatusField.setText("");
equipmentMinField.setText("");
equipmentMaxField.setText("");
illuminationLevelField.setText("");
regulationLevelField.setText("");
equipmentIdField.setEnabled(false);
equipmentNameField.setEnabled(false);
equipmentTypeField.setEnabled(false);
equipmentStatusField.setEnabled(false);
isLevelCheckBox.setEnabled(false);
isLevelCheckBox.setSelected(false);
equipmentMinField.setEnabled(false);
equipmentMaxField.setEnabled(false);
illuminationLevelField.setEnabled(false);
regulationLevelField.setEnabled(false);
temperatureLevelField.setEnabled(false);


addInEquipments.setEnabled(false);
addInBoard.setEnabled(false);
boardNameField.setEnabled(true);
boardIdField.setEnabled(true);
proceedBoard.setEnabled(true);
addInBoards.setEnabled(true);
JOptionPane.showMessageDialog(frame,"Successfully added equipments in board");
}
});
isLevelCheckBox.addItemListener(new ItemListener(){
public void itemStateChanged(ItemEvent e)
{
int a=e.getStateChange();
if(a==1)
{
regulationLevelField.setEnabled(true);
illuminationLevelField.setEnabled(true);
temperatureLevelField.setEnabled(true);
}
if(a==2)
{
regulationLevelField.setEnabled(false);
illuminationLevelField.setEnabled(false);
temperatureLevelField.setEnabled(false);

}
}
});
JScrollPane pane=new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
pane.setPreferredSize(new Dimension(700,1200));
pane.setViewportView(mainPanel);
frame.getContentPane().add(pane);
frame.setVisible(true);
}
public void generateJSON()
{
try
{
int roomsLength=rooms.size();
JSONObject roomsObject=new JSONObject();
JSONArray roomsArray=new JSONArray();
for(int x=0;x<rooms.size();x++)
{
JSONObject roomObject=new JSONObject();
roomObject.put("name",rooms.get(x).getName());
roomObject.put("id",rooms.get(x).getId());
JSONArray boardsArray=new JSONArray();
JSONObject boardsObject=new JSONObject();
for(int y=0;y<rooms.get(x).getBoards().size();y++)
{
JSONObject boardObject=new JSONObject();
boardObject.put("name",rooms.get(x).getBoards().get(y).getName());
boardObject.put("id",rooms.get(x).getBoards().get(y).getId());
JSONArray equipmentsArray=new JSONArray();
//boardsArray.add(boardObject);
JSONObject equipmentsObject=new JSONObject();
for(int z=0;z<rooms.get(x).getBoards().get(y).getEquipments().size();z++)
{
JSONObject equipmentObject=new JSONObject();
equipmentObject.put("name",rooms.get(x).getBoards().get(y).getEquipments().get(z).getName());
equipmentObject.put("id",rooms.get(x).getBoards().get(y).getEquipments().get(z).getId());
equipmentObject.put("type",rooms.get(x).getBoards().get(y).getEquipments().get(z).getType());
equipmentObject.put("status",rooms.get(x).getBoards().get(y).getEquipments().get(z).getStatus());
equipmentObject.put("min",rooms.get(x).getBoards().get(y).getEquipments().get(z).getMin());
equipmentObject.put("max",rooms.get(x).getBoards().get(y).getEquipments().get(z).getMax());
equipmentObject.put("isLevel",rooms.get(x).getBoards().get(y).getEquipments().get(z).getIsLevel());
equipmentObject.put("illuminationLevel",rooms.get(x).getBoards().get(y).getEquipments().get(z).getIlluminationLevel());
equipmentObject.put("regulationLevel",rooms.get(x).getBoards().get(y).getEquipments().get(z).getRegulatorLevel());
equipmentObject.put("temperatureLevel",rooms.get(x).getBoards().get(y).getEquipments().get(z).getTemperatureLevel());

equipmentsArray.add(equipmentObject);
}
boardObject.put("equipments",equipmentsArray);
boardsArray.add(boardObject);
}
//boardObject.put("boards",boardsArray);
roomObject.put("boards",boardsArray);
roomsArray.add(roomObject);
}
roomsObject.put("rooms",roomsArray);
try(FileWriter ff=new FileWriter("boards.json"))
{
ff.write(roomsObject.toJSONString());
}
}catch(Exception e)
{
System.out.println(e);
}
}
public static void main(String g[])
{
Installer installer=new Installer();    
}
}