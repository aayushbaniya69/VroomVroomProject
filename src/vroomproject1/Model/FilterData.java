/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vroomproject1.Model;

/**
 *
 * @author Dell
 */
public class FilterData {
    //Attributes
    private String brand;
    private String vehicleType;
    private String range;
    private String date;
    
    //Constructor
    public FilterData(String brand,String vehicleType, String range, String date){
        this.brand=brand;
        this.vehicleType=vehicleType;
        this.range=range;
        this.date=date;
    }
    //Getter setter 
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    } 
}
