
package Dashboard.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MenuType {

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public MenuType getType() {
        return type;
    }
    public MenuType(String icon, String name, MenuType type) {
        this.icon=icon;
        this.name=name;
        this.type=type;
    }
    
    public MenuType() {
        
    }


    public void setType(MenuType type) {
        this.type = type;
    }
    private String icon ;
    private String name;
    private MenuType type;
    
    
    public Icon toIcon(){
        return new ImageIcon(getClass().getResource(""));
    }
    
    

}
