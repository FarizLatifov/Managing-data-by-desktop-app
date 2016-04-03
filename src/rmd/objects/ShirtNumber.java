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
public class ShirtNumber {
    private int id;
    private int number;

    public ShirtNumber(int id, int number) {
        this.id = id;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
