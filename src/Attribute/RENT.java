/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Attribute;

import java.time.LocalTime;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class RENT {
    private int rentID,citizenID,filmID,remployID,fee;
    private String time;

   

    public RENT(int rentID, int citizenID, int filmID, int remployID, String time, int fee) {
        this.rentID = rentID;
        this.citizenID = citizenID;
        this.filmID = filmID;
        this.remployID = remployID;
        this.time = time;
        this.fee = fee;
    }
    
    

    public Object[] toObject(){
        return new Object[]{
            rentID,citizenID,filmID,remployID,time,fee
        };
    }

}
