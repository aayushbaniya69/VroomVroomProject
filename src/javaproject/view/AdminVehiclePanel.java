/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaproject.view;

import javaproject.model.Vehicle;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javaproject.controller.VehicleController;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class AdminVehiclePanel extends javax.swing.JPanel {
    private File selectedImageFile;
    private VehicleController controller;
    
    private DefaultTableModel tableModel;
    public AdminVehiclePanel() {
        initComponents(); 
        controller = VehicleController.getInstance();

        // Define table model with correct column class for ImageIcon
        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Type", "Price", "Status", "Image"}
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) {
                    return ImageIcon.class;
                } else if (columnIndex == 3) {
                    return Double.class; // Assuming price is Double
                } else {
                    return String.class;
                }
            }
        };

        jTable1.setModel(tableModel);
        jTable1.setRowHeight(150);

        // Image cell renderer
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                if (value instanceof ImageIcon) {
                    setIcon((ImageIcon) value);
                    setText("");
                } else {
                    super.setValue(value);
                }
            }
        });

        // Load vehicles into table
        loadVehicleList(controller.getAllVehicles());

        // Add row selection listener to fill form
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow != -1) {
                    Vehicle selectedVehicle = controller.getAllVehicles().get(selectedRow);
                    numberField.setText(selectedVehicle.getVehicleId());
                    nameField.setText(selectedVehicle.getName());
                    typeField.setText(selectedVehicle.getType());
                    priceField.setText(String.valueOf(selectedVehicle.getPrice()));
                    statusField.setText(selectedVehicle.getStatus());

                    String imagePath = selectedVehicle.getImagePath();
                    if (imagePath != null && !imagePath.isEmpty()) {
                        File imgFile = new File(imagePath);
                        if (imgFile.exists()) {
                            updateImagePreview(imgFile);
                        } else {
                            imagePreview.setIcon(null);
                        }
                    } else {
                        imagePreview.setIcon(null);
                    }
                }
            }
        });
    }

    private void loadVehicleList(List<Vehicle> vehicleList) {
        tableModel.setRowCount(0); // Clear table

        for (Vehicle v : vehicleList) {
            ImageIcon icon = null;
            if (v.getImagePath() != null && !v.getImagePath().isEmpty()) {
                File imgFile = new File(v.getImagePath());
                if (imgFile.exists()) {
                    ImageIcon originalIcon = new ImageIcon(v.getImagePath());
                    Image scaledImage = originalIcon.getImage().getScaledInstance(145, 145, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(scaledImage);
                }
            }

            Object[] row = {
                v.getVehicleId(),
                v.getName(),
                v.getType(),
                v.getPrice(),
                v.getStatus(),
                icon
            };

            tableModel.addRow(row);
        }
    }

    private void updateImagePreview(File imageFile) {
        ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
        Image scaledImage = icon.getImage().getScaledInstance(imagePreview.getWidth(), imagePreview.getHeight(), Image.SCALE_SMOOTH);
        imagePreview.setIcon(new ImageIcon(scaledImage));
    }
    private String saveImageToProject(File sourceFile) {
    try {
        // Destination directory
        File destDir = new File("Dashboard/images/");
        if (!destDir.exists()) {
            destDir.mkdirs(); // Create directory if not exists
        }

        // Destination file (with same name)
        File destFile = new File(destDir, sourceFile.getName());

        // Copy file
        java.nio.file.Files.copy(
            sourceFile.toPath(),
            destFile.toPath(),
            java.nio.file.StandardCopyOption.REPLACE_EXISTING
        );

        // Return the relative path you want to store in Vehicle
        return "Dashboard/images/" + sourceFile.getName();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error saving image: " + e.getMessage());
        return null;
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        numberField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        typeField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        priceField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        statusField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        selectImage = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        imagePreview = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(30, 30, 47));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("VroomVroom");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1029, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Vehicle Id", "Name", "Type", "Price", "Status", "Image"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(44, 47, 54));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Vehicle Id:");

        numberField.setBackground(new java.awt.Color(102, 102, 102));
        numberField.setForeground(new java.awt.Color(255, 255, 255));
        numberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberFieldActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name:");

        nameField.setBackground(new java.awt.Color(102, 102, 102));
        nameField.setForeground(new java.awt.Color(255, 255, 255));
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Type:");

        typeField.setBackground(new java.awt.Color(102, 102, 102));
        typeField.setForeground(new java.awt.Color(255, 255, 255));
        typeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeFieldActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Price:");

        priceField.setBackground(new java.awt.Color(102, 102, 102));
        priceField.setForeground(new java.awt.Color(255, 255, 255));
        priceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Status:");

        statusField.setBackground(new java.awt.Color(102, 102, 102));
        statusField.setForeground(new java.awt.Color(255, 255, 255));
        statusField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusFieldActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(0, 0, 255));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(255, 0, 0));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Choose Image:");

        selectImage.setBackground(new java.awt.Color(0, 102, 0));
        selectImage.setForeground(new java.awt.Color(255, 255, 255));
        selectImage.setText("Select Image");
        selectImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectImageActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Image Preview:");

        imagePreview.setForeground(new java.awt.Color(255, 255, 255));
        imagePreview.setText("Image");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(addButton)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numberField)
                    .addComponent(nameField)
                    .addComponent(typeField)
                    .addComponent(priceField)
                    .addComponent(statusField)
                    .addComponent(selectImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(deleteButton)
                                .addGap(73, 73, 73)
                                .addComponent(editButton))
                            .addComponent(imagePreview, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(numberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(selectImage))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(imagePreview, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(deleteButton)
                    .addComponent(editButton))
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        try {
        String vehicleId = numberField.getText();
        String name = nameField.getText();
        String type = typeField.getText();
        double price = Double.parseDouble(priceField.getText());
        String status = statusField.getText();

        // Validate required fields
        if (vehicleId.isEmpty() || name.isEmpty() || type.isEmpty() || status.isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled");
        }

        // Save image if selected
        String imagePath = null;
        if (selectedImageFile != null) {
            imagePath = saveImageToProject(selectedImageFile);
        }

        Vehicle vehicle = new Vehicle(vehicleId, name, type, price, status, imagePath);
        controller.addVehicle(vehicle); // Add vehicle to controller
        loadVehicleList(controller.getAllVehicles()); // Refresh table
        clearForm(); // Reset form
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
    
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try {
         String vehicleId = numberField.getText(); // Get the vehicle ID from the form
         if (vehicleId.isEmpty()) {
             throw new IllegalArgumentException("Vehicle ID cannot be empty");
         }
         controller.deleteVehicle(vehicleId); // Delete the vehicle
         loadVehicleList(controller.getAllVehicles()); // Refresh the table
         clearForm(); // Clear the form
     } catch (Exception ex) {
         JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
     }
    }//GEN-LAST:event_deleteButtonActionPerformed
    private void clearForm() {
        numberField.setText("");
        nameField.setText("");
        typeField.setText("");
        priceField.setText("");
        statusField.setText("");
        selectedImageFile = null;
        imagePreview.setIcon(null);
    }
    private void priceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceFieldActionPerformed

    private void statusFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusFieldActionPerformed

    private void numberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numberFieldActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void typeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeFieldActionPerformed

    private void selectImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectImageActionPerformed
JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");
    fileChooser.setFileFilter(filter);

    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        selectedImageFile = fileChooser.getSelectedFile();
        // Update image preview
        updateImagePreview(selectedImageFile); // This is where we call the new method
    }     
    }//GEN-LAST:event_selectImageActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        try {
        String vehicleId = numberField.getText();
        String name = nameField.getText();
        String type = typeField.getText();
        double price = Double.parseDouble(priceField.getText());
        String status = statusField.getText();

        // Basic validation
        if (vehicleId.isEmpty() || name.isEmpty() || type.isEmpty() || status.isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled.");
        }

        // Find the existing vehicle
        Vehicle oldVehicle = controller.findVehicleById(vehicleId);
        if (oldVehicle == null) {
            throw new IllegalArgumentException("Vehicle with the given ID not found.");
        }

        // Get image path: copy new image or keep old
        String imagePath;
        if (selectedImageFile != null) {
            imagePath = saveImageToProject(selectedImageFile);
        } else {
            imagePath = oldVehicle.getImagePath(); // retain old image if no new selected
        }

        // Create updated vehicle object
        Vehicle updatedVehicle = new Vehicle(vehicleId, name, type, price, status, imagePath);

        // Update and refresh
        controller.updateVehicle(vehicleId, updatedVehicle);
        loadVehicleList(controller.getAllVehicles());
        clearForm();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
    }//GEN-LAST:event_editButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel imagePreview;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField numberField;
    private javax.swing.JTextField priceField;
    private javax.swing.JButton selectImage;
    private javax.swing.JTextField statusField;
    private javax.swing.JTextField typeField;
    // End of variables declaration//GEN-END:variables

public javax.swing.JButton getAddButton(){
    return addButton;
     }
public javax.swing.JButton getDeleteButton(){
    return deleteButton;
}
public javax.swing.JButton getEditButton(){
    return editButton;
}
public javax.swing.JButton getSelectImage(){
    return selectImage;
}
public javax.swing.JTextField getNameField(){
    return nameField;
}
public javax.swing.JTextField getNumberField(){
    return numberField;
}
public javax.swing.JTextField getPriceField(){
    return priceField;
}
public javax.swing.JTextField getStatusField(){
    return statusField;
}
public javax.swing.JTextField getTypeField(){
    return typeField;
}
}
