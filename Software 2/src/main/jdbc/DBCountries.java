package main.jdbc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.*;

public class DBCountries {

    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> clist = FXCollections.observableArrayList();

        return clist;
    }


    public static void checkDateConversion() {
        System.out.println("CREATE DATE TEST");
        String sql = "Select Create_Date from countires";
        try {
            PreparedStatement ps = DBDriver.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("Create_Date");
                System.out.println("CD: " + ts.toLocalDateTime().toString());
            }
        
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}