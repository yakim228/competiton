/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competition;

import competition.tournoigui.FederationGUI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Administrator
 */
public class Competition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TournoiNonFiniException, ClassNotFoundException, SQLException {
        FederationGUI f = new FederationGUI();
        f.setLocationRelativeTo(f);
        f.setVisible(true);
    }
    
}