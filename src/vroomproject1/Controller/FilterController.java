package vroomproject1.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vroomproject1.Dao.UserDao;
import vroomproject1.Model.FilterData;
import vroomproject1.view.Filter;






/**
 *
 * @author Dell
 */
public class FilterController {
    Filter filter;

    public FilterController(Filter view) {
        this.filter= view;
        this.filter.setFilterListener(new FilterUser()); // Assumes Filter.java has this method
    }

    public void open() {
        filter.setVisible(true);
    }

    public void close() {
        filter.dispose();
    }

    class FilterUser implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
             String brand = filter.getBrandComboBox().getSelectedItem().toString();
            String vehicleType = filter.getVehicleTypeComboBox().getSelectedItem().toString();
            String range = filter.getRangeTextField().getText();
            String date = filter.getDateTextField().getText();

            if (brand.isEmpty() || vehicleType.isEmpty() || range.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(filter, "Fill in all the fields");
            } else {
                FilterData data = new FilterData(brand, vehicleType, range, date);
                UserDao userDao = new UserDao();
                boolean result = userDao.filter(data);

                if (result) {
                    JOptionPane.showMessageDialog(filter, "Filter saved successfully");
                    // Optionally open next view or reset fields
                } else {
                    JOptionPane.showMessageDialog(filter, "Failed to save filter data");
                }
            }
        }
    
        }
}



        
    



