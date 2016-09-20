
import dao.DbStorage;
import dao.daoInterface;
import gui.MainMenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patmo016
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        daoInterface dao = new DbStorage();
                
        // TODO code application logic here
        //creates instance of mainMenu frame
        MainMenu mainMenu = new MainMenu (dao);
        //centers the frame;
        mainMenu.setLocationRelativeTo(null);
        //shows the frame;
        mainMenu.setVisible(true);
        
        
        
    }
}
