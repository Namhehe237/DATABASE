/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Attribute;

/**
 *
 * @author Admin
 */
public class total1month {
    private int totalRent,totalMoney;

    public total1month(int totalRent, int totalMoney) {
        this.totalRent = totalRent;
        this.totalMoney = totalMoney;
    }

    public total1month(int aInt, int aInt0, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Object[] toObject(){
        return new Object[]{
            totalRent,totalMoney
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
