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

    private final String brand;
    private final String vechileType;
    private final String date;
    private final String range;

    public String getVehicleType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public class filterData{
        private String brand;
        private String vechileType;
        private String range;
        private String date;
    }
    public FilterData(String brand, String vechileType, String range, String date){
        this.brand=brand;
        this.vechileType=vechileType;
        this.range=range;
        this.date=date;  
    }
    public String getBrand(){
        return brand;
    }
    public String getVechileType(){
        return vechileType;
    }
    public String getRange(){
        return range;
    }
    public String getDate(){
        return date;
    }
}
