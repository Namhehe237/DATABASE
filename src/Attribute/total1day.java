/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Attribute;

/**
 *
 * @author Admin
 */
public class total1day {
    private int totalRent,totalMoney;
    private String day;

    public total1day(int totalRent, int totalMoney,String day) {
        this.totalRent = totalRent;
        this.totalMoney = totalMoney;
        this.day = day;
    }
    
    public Object[] toObject(){
        return new Object[]{
            totalRent,totalMoney,day
        };
    }

    public int getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(int totalRent) {
        this.totalRent = totalRent;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
}
