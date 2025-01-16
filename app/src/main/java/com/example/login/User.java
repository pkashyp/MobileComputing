package com.example.login;

public class User {

    private String id;
    private String firstname;

    private String lastname;



    private String email;

    private String password;
    private String phone;


    // Constructor to create a new user with the given details.
// commment1
    public User( String firstname, String lastname, String email, String phone, String password){



        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.password=password;
        this.phone=phone;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getId(){
        return id;
    }

    public void setFirstname(String firstname){
        this.firstname=firstname;
    }

    public String getFirstname(){
        return firstname;
    }
    public void setLastname(String lastname){
        this.lastname=lastname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return password;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }

    public String getPhone(){
        return phone;
    }

}
