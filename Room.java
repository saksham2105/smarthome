import java.util.*;
public class Room 
{

private String name;
private Integer id;
private List<Board> boards = null;
public Room()
{
this.name="";
this.id=0;
this.boards=null;
}

public String getName() {
return this.name;
}
public void setName(String name) {
this.name = name;
}

public Integer getId() {
return this.id;
}

public void setId(Integer id) {
this.id = id;
}
public List<Board> getBoards() {
return this.boards;
}

public void setBoards(List<Board> boards) {
this.boards = boards;
}
}
