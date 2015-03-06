package com.example.xnote.sportsmatch;

/**
 * Created by Xnote on 2015-03-06.
 */
public class UserDTO {
    private String email;
    private String name;
    private String device_id;
    private String age;
    private String location;

    public UserDTO() {
        super();
        this.email 		= " ";
        this.name 		= " ";
        this.age 		= " ";
        this.location 	= " ";
        this.device_id 	= " ";
        // TODO Auto-generated constructor stub
    }

    public UserDTO(String email, String name, String age, String location, String device_id) {
        super();
        this.email 		= email;
        this.name 		= name;
        this.age 		= age;
        this.location 	= location;
        this.device_id 	= device_id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getDevice_id() {
        return device_id;
    }
    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
    @Override
    public String toString() {
        return "UserDTO [email=" + email + ", name=" + name + ", age=" + age
                + ", location=" + location + ", device_id=" + device_id + "]";
    }
}
