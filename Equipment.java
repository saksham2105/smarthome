
import java.util.*;
public class Equipment 
{
private String name;
private Integer id;
private String type;
private Integer status;
private Integer min;
private Integer max;
private Boolean isLevel;
private Integer illuminationLevel;
private Integer regulatorLevel;
private Integer temperatureLevel;
public Equipment()
{
this.name="";
this.id=0;
this.type="";
this.status=0;
this.min=0;
this.max=0;
this.isLevel=false;
this.illuminationLevel=0;
this.regulatorLevel=0;
this.temperatureLevel=0;
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

public String getType() {
return this.type;
}

public void setType(String type) {
this.type = type;
}
public Integer getStatus() {
return this.status;
}

public void setStatus(Integer status) {
this.status = status;
}

public Integer getMin() {
return this.min;
}

public void setMin(Integer min) {
this.min = min;
}

public Integer getMax() {
return this.max;
}

public void setMax(Integer max) {
this.max = max;
}

public Boolean getIsLevel() {
return this.isLevel;
}

public void setIsLevel(Boolean isLevel) {
this.isLevel = isLevel;
}
public Integer getIlluminationLevel() {
return this.illuminationLevel;
}

public void setIlluminationLevel(Integer illuminationLevel) {
this.illuminationLevel = illuminationLevel;
}

public Integer getRegulatorLevel() {
return this.regulatorLevel;
}

public void setRegulatorLevel(Integer regulatorLevel) {
this.regulatorLevel = regulatorLevel;
}
public Integer getTemperatureLevel() {
return this.temperatureLevel;
}

public void setTemperatureLevel(Integer temperatureLevel) {
this.temperatureLevel = temperatureLevel;
}
}