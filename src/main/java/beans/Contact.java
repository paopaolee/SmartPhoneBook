package beans;

import java.io.Serializable;

public class Contact implements Serializable {
    private static final long serialVersionUID  = 192442429816773L;
    private static int id = 1;
    private String name;
    private String telephone;
    private String alias;
    private int age;
    private Gender gender;
    public Contact() {
        id++;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", alias='" + alias + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
