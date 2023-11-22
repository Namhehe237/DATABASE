/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Attribute;

/**
 *
 * @author Admin
 */
public class avg1Month {
    private double avg;
    private String year;
    public avg1Month(double avg, String year) {
        this.avg = avg;
        this.year = year;
    }
    
    
    public Object[] toObject(){
        return new Object[]{
            avg,year
        };
}
}
