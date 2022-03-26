package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;


public class Time {
    /** list of all appointments. * @param allAppointments a list of all appointments. */
    public static ObservableList<LocalTime> hoursOfOpperation = FXCollections.observableArrayList();
    public static void populateHoursOfOperation(){
        hoursOfOpperation.clear();
        for (int i = 8; i <= 22; i++){
            for (int j = 00; j <60 ; j = j + 15 ){
                LocalTime time = LocalTime.of(i,j);
                hoursOfOpperation.add(time);
            }
        }
    }

    public static ObservableList<Long> apptDuration = FXCollections.observableArrayList();
    public static void populateDuration(){
        apptDuration.clear();
        for (long j = 15; j <= 60 ; j = j + 15 ){
            long time = j;
            apptDuration.add(time);
        }
    }

    public static ObservableList<Integer> months = FXCollections.observableArrayList();
    public static void populatemonths(){
        months.clear();
        for (int j = 1; j <= 12 ; j++ ){
            months.add(j);
        }
    }
}
