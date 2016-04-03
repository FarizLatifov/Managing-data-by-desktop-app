/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmd.objects;

/**
 *
 * @author Fariz
 */
public class Squad {

    int id;
    String name;
    String position;
    String country;
    int number;
    String birthDate;

    public Squad() {
    }

    public Squad(String name, String position, String country, int number, String bdate) {
        this.name = name;
        this.position = position;
        this.country = country;
        this.birthDate = bdate;
        this.number = number;
      }
    
    public Squad(int id, String name, String position, String country, int number, String bdate) {
        this.id=id;
        this.name = name;
        this.position = position;
        this.country = country;
        this.birthDate = bdate;
        this.number = number;
        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getCountry() {
        return country;
    }

    public int getNumber() {
        return number;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}
