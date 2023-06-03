package com.project.storekeeper.container;

public class Contact {
    public static final String techContactID = "contactID";
    public static final String techContactAddress = "contactAddress";
    public static final String techContactPhone = "contactPhone";
    public static final String techContactEmail = "contactEmail";
    public static final String techContactTelegram = "contactTelegram";
    public static final String techContactFIO = "contactFIO";
    private Integer contactID;
    private String contactSurname;
    private String contactName;
    private String contactPatronymic;
    private String contactAddress;
    private String contactPhone;
    private String contactEmail;
    private String contactTelegram;
    private String contactFIO;

    public Contact() { }
    public Contact(Integer contactID,
                   String contactSurname,
                   String contactName,
                   String contactPatronymic,
                   String contactAddress,
                   String contactPhone,
                   String contactEmail,
                   String contactTelegram) {
        this.contactID = contactID;
        this.contactSurname = contactSurname;
        this.contactName = contactName;
        this.contactPatronymic = contactPatronymic;
        this.contactAddress = contactAddress;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.contactTelegram = contactTelegram;
    }
    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }
    public void setContactSurname(String contactSurname) {
        this.contactSurname = contactSurname;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public void setContactPatronymic(String contactPatronymic) {
        this.contactPatronymic = contactPatronymic;
    }
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public void setContactTelegram(String contactTelegram) {
        this.contactTelegram = contactTelegram;
    }
    public Integer getContactID() {
        return contactID;
    }
    public String getContactSurname() {
        return contactSurname;
    }
    public String getContactName() {
        return contactName;
    }
    public String getContactPatronymic() {
        return contactPatronymic;
    }
    public String getContactAddress() {
        return contactAddress;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public String getContactTelegram() {
        return contactTelegram;
    }

    public String getContactFIO() {
        return contactFIO;
    }

    public void setContactFIO(String contactFIO) {
        this.contactFIO = contactFIO;
    }
}