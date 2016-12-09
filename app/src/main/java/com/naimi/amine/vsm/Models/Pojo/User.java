package com.naimi.amine.vsm.Models.Pojo;

import java.util.List;

/**
 * Created by macbookpro on 27/11/16.
 */
public class User {
    public  String realm;
    public String username;
    public List<String> credentials;
    public List<String> challenges;
    public  String email;
    public  Boolean emailVerified;
    public  String status;
    public String created;
    public String lastUpdated;
    public  int id;

}
