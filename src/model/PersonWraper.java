package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "persons")
public class PersonWraper {

    private List persons;
    @XmlElement(name = "person")
    public List getPersons(){
        return persons;
    }
    public void setPersons(List persons){
        this.persons = persons;
    }
}
