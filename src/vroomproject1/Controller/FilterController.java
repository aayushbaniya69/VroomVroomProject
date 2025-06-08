/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vroomproject1.view.Filter;

/**
 *
 * @author Dell
 */
public class FilterController {
    private Filter view;
    
    public FilterController(Filter view){
        this.view=view;
        this.view.filterUser(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String date= view.getDateTextField().getText();
                String range= view.getRangeTextField().getText();
                
                // Add logic here to filter based on date & range
                System.out.println("Filtering with Date: " + date + ", Range: " + range);
            }
        }
        );
    }
}
