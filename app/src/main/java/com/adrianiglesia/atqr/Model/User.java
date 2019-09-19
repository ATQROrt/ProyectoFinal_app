package com.adrianiglesia.atqr.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User implements Parcelable {

    //private static User instance;

    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("document")
    private Long document;
    @SerializedName("mail")
    private String mail;
    @SerializedName("birth")
    private Date birth;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("password")
    private String password;

    public User(){}


    /*public static User getInstance(){
        if(instance == null){
            instance = new User();
        }

        return instance;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        if (in.readByte() == 0) {
            document = null;
        } else {
            document = in.readLong();
        }
        mail = in.readString();
        imageUrl = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        if (document == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(document);
        }
        dest.writeString(mail);
        dest.writeString(imageUrl);
        dest.writeString(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document=" + document +
                ", mail='" + mail + '\'' +
                ", birth=" + birth +
                ", imageUrl='" + imageUrl + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
