/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

import javaproject.model.SellerData;
import javaproject.view.SellerDashboardView;

/**
 *
 * @author ACER
 */
public class SellerDashboardController {
    SellerDashboardView view;
    SellerData seller;
    public SellerDashboardController(SellerDashboardView view,SellerData seller){
        this.view=view;
        this.seller=seller;
    }
    public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
}
