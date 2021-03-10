package models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class User {
    

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private int verified;

    
    public User() {}

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public int getVerified() {
        return this.verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }


    public void hashPassword() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[265];
        random.nextBytes(salt);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(this.password.getBytes(StandardCharsets.UTF_8));
            this.password = hashedPassword.toString();
            System.out.println("passowrd : " + hashedPassword.toString());
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }

                
    }


    @Override
    public String toString() {
        return "{" +
            " firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", verified='" + getVerified() + "'" +
            "}";
    }

}