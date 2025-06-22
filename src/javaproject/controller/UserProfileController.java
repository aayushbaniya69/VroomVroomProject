/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject.controller;

/**
 *
 * @author ACER
 */
public class UserProfileController {
    private user view;
    private String email; // Email is the identifier

    public UserprofileController(user view, String email) {
        this.view = view;
        this.email = email;
        init();

        // Set up listeners
        view.backDashboard(new openDashboard());
        view.changeProfile(new ChangeProfile());
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    // Initialize profile fields
    private void init() {
        UserprofileDAO dao = new UserprofileDAO();
        UserModel user = dao.getUserByEmail(email);

        if (user != null) {
            view.getUpdatename().setText(user.getUsername());
            view.getUpdateemail().setText(user.getEmail());
            view.getUpdateaddress().setText(user.getAddress());
            view.getUpdatecontact().setText(user.getContact());
        } else {
            JOptionPane.showMessageDialog(view, "User Profile not found!", "Error", JOptionPane.ERROR_MESSAGE);
            close();
        }
    }

    // Dashboard navigation
    public class openDashboard implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
            Dashboard dashboard = new Dashboard();
            DashboardController dashboardcontroller = new DashboardController(dashboard, email);
            dashboardcontroller.open();
        }
    }

    // Change or save profile logic
    public class ChangeProfile implements ActionListener {
        private boolean isEditing = false;
        private UserModel originalUser;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isEditing) {
                // Start editing
                originalUser = new UserModel(
                    view.getUpdatename().getText(),
                    view.getUpdateemail().getText(),
                    view.getUpdateaddress().getText(),
                    view.getUpdatecontact().getText()
                );
                view.setFieldsEnabled(true);
                view.setChangeProfileButtonText("Save Changes");
                isEditing = true;
            } else {
                // Save logic
                int response = JOptionPane.showConfirmDialog(view, "Save changes?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
                if (response == JOptionPane.OK_OPTION) {
                    boolean hasChanges = !view.getUpdatename().getText().equals(originalUser.getUsername()) ||
                                         !view.getUpdateemail().getText().equals(originalUser.getEmail()) ||
                                         !view.getUpdateaddress().getText().equals(originalUser.getAddress()) ||
                                         !view.getUpdatecontact().getText().equals(originalUser.getContact());

                    if (hasChanges) {
                        UserModel updatedUser = new UserModel(
                            view.getUpdatename().getText(),
                            view.getUpdateemail().getText(),
                            view.getUpdateaddress().getText(),
                            view.getUpdatecontact().getText()
                        );
                        UserprofileDAO dao = new UserprofileDAO();
                        if (dao.updateUser(updatedUser)) {
                            JOptionPane.showMessageDialog(view, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            originalUser = updatedUser;
                            email = updatedUser.getEmail(); // Update email
                        } else {
                            JOptionPane.showMessageDialog(view, "Failed to update profile!", "Error", JOptionPane.ERROR_MESSAGE);
                            resetToOriginal();
                        }
                    }
                    finishEditing();
                } else {
                    resetToOriginal();
                    finishEditing();
                }
            }
        }

        private void resetToOriginal() {
            if (originalUser != null) {
                view.getUpdatename().setText(originalUser.getUsername());
                view.getUpdateemail().setText(originalUser.getEmail());
                view.getUpdateaddress().setText(originalUser.getAddress());
                view.getUpdatecontact().setText(originalUser.getContact());
            }
        }

        private void finishEditing() {
            view.setFieldsEnabled(false);
            view.setChangeProfileButtonText("Change Profile");
            isEditing = false;
        }
    }
}
