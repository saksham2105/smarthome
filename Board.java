
import java.util.*;
public class Board {
private String name;
private Integer id;
private List<Equipment> equipments = null;

public Board()
{
this.name="";
this.id=0;
this.equipments=null;
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

public List<Equipment> getEquipments() {
return this.equipments;
}

public void setEquipments(List<Equipment> equipments) {
this.equipments = equipments;
}

}