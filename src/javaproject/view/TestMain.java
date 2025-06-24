/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.view;

import javax.swing.JFrame;

/**
 * Simple Test Launcher to run UserVehiclePanel with VehicleCardPanel inside.
 */
public class TestMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("User Vehicle Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        UserVehiclePanel userPanel = new UserVehiclePanel();
        frame.add(userPanel);

        frame.setVisible(true);
    }
}

