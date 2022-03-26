package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/** Creates and controls Appointments.*/
public class Appointment {
        private int apptId;
        private String title;
        private String description;
        private String location;
        private String type;
        private LocalDateTime start;
        private LocalDateTime end;
        private Date createDate;
        private String createdBy;
        private Timestamp lastUpdate;
        private String lastUpdateBy;
        private int customerId;
        private int userId;
        private int contactId;

        /**
         * Constructor for appointment.
         * @param apptId Primary key and unique ID.
         * @param title Name of the appointment.
         * @param description description of the appointment.
         * @param location Location of the appointment.
         * @param type Type of appointment.
         * @param start Start time of the appointment.
         * @param end End time of the appointment.
         * @param createDate Date the appointment was created.
         * @param createdBy Identifies who created the appointment.
         * @param lastUpdate Most recent update to the appointment.
         * @param lastUpdateBy Identifies who did the last update to the appointment.
         * @param customerId Unique ID for customer.
         * @param userId Unique ID for user.
         * @param contactId Unique ID for contact.
         */
        public Appointment(int apptId, String title, String description, String location, String type, LocalDateTime start,
                           LocalDateTime end,Date createDate,String createdBy,Timestamp lastUpdate,String lastUpdateBy,int customerId,
                            int userId,int contactId) {
            this.apptId = apptId;
            this.title = title;
            this.description = description;
            this.location=location;
            this.type=type;
            this.start=start;
            this.end=end;
            this.createDate=createDate;
            this.createdBy = createdBy;
            this.lastUpdate=lastUpdate;
            this.lastUpdateBy =lastUpdateBy;
            this.customerId=customerId;
            this.userId=userId;
            this.contactId=contactId;
        }

    /** adds appt to allAppointments list.
     * @param appt The appointment that is being added to the list.
     */
    public static void addAppt (ObservableList<Appointment> apptSet, Appointment appt) {
        apptSet.add(appt);
    }

    /**
     * Returns the list of all appointments.
     * @return allAppointments
     */
    public static ObservableList<Appointment> getAllAppointments() {return allAppointments;}

    /** list of all appointments.
     * @param allAppointments a list of all appointments.
     */
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    /**
     * Returns the list of the next 7 days appointments.
     * @return allAppointments
     */
    public static ObservableList<Appointment> getWeeksAppointments() {return weeksAppointments;}

    /** list of all appointments.
     * @param allAppointments a list of all appointments.
     */
    private static ObservableList<Appointment> weeksAppointments = FXCollections.observableArrayList();

    /**
     * Returns the list of appointments in the next 30 days.
     * @return allAppointments
     */
    public static ObservableList<Appointment> getMonthsAppointments() {return monthsAppointments;}

    /** list of all appointments.
     * @param allAppointments a list of all appointments.
     */
    private static ObservableList<Appointment> monthsAppointments = FXCollections.observableArrayList();


    /** .*/
    public static ObservableList<Appointment> contactSchedule() {return contactSchedule;}

    /** list of all appointments.
     * @param allAppointments a list of all appointments.
     */
    private static ObservableList<Appointment> contactSchedule = FXCollections.observableArrayList();
    /**
     * Returns the list of appointments in the next 30 days.
     * @return allAppointments
     */
    public static ObservableList<Appointment> getContactSchedule() {return contactSchedule;}


    /**
     * Gets appointment ID.
     * @return apptId
     */
    public int getApptId() {
        return apptId;
    }

    /**
     * Sets appointment ID.
     * @param apptId Unique ID for appointment class.
     */
    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    /**
     * Gets title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the appointment title.
     * @param title Title of the appointment.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *  Gets the description of the appointment.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sests the description of the appointment
     * @param description any special notes about the appointment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the location of the appointment.
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the appointment
     * @param location address of the building of the appointment.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the type of appointment
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of appointment
     * @param type Area of specialty of the appointment.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the start time of the appointment.
     * @return start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Sets the start time of the apointment.
     * @param start time based on users local time.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Gets the end time of the appointment.
     * @return end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Sets the end time of the appointment.
     * @param end time the appointment ends in the users local time.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Gets the date the appointment was created.
     * @return createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the Date that the appointment was created.
     * @param createDate Date and time of the appointment stored as a datetime yyyy-mm-dd hh:mm:ss.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the last time the appointment was most recently updated.
     * @return LastUpdate
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the time the appointment was most recently updated.
     * @param lastUpdate time the appointment was most recently updated; stored as a timestamp yyyy-mm-dd hh:mm:ss.
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * get who updated the appointment was most recently.
     * @return lastUpdatedBy
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * Sets who most recently updated the appointment.
     * @param lastUpdateBy Username of the person who most recently updated the appointment.
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    /**
     * Gets the customer ID.
     * @return customerId.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     * @param customerId FOREIGN KEY: Unique Identify for Contacts.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the userId.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the UserId.
     * @param userId FOREIGN KEY: Unique Identify for Contacts.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the Contact ID number.
     * @return
     */
    public int getContactId() {
        return contactId;
    }

    /**
     *  Sets the Contact ID number.
     * @param contactId FOREIGN KEY: Unique Identify for Contacts.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** .*/
    public String getCreatedBy() {
        return createdBy;
    }

    /** .*/
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
