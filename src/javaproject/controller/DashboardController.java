/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import javaproject.model.UserData;
import javaproject.view.DashboardView;

/**
 *
 * @author ACER
 */
public class DashboardController {

    DashboardView view;
    UserData user;
    public DashboardController(DashboardView view,UserData user){
        this.view=view;
        this.user=user;
        //this.firstName=firstname;
        this.view.getWelcomeLable().setText("Welcome ");
    }
    public DashboardController(DashboardView view){
        this.view=view;
    }
    public void open(){
       view.setVisible(true); 
    }
    public void close(){
        view.dispose();
    }
    
}
