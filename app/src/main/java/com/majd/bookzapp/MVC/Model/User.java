package com.majd.bookzapp.MVC.Model;

/**
 * Created by majd on 1/4/18.
 */

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthDate ;

    public User(String firstName,String lastName,String email,String password,String birthDate){
      this.firstName=firstName;
      this.lastName=lastName;
      this.email=email;
      this.password=password;
      this.birthDate=birthDate;
    }

}
