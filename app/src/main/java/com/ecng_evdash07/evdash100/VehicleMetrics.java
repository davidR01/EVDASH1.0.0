package com.ecng_evdash07.evdash100;

public class VehicleMetrics {

    private int batteryVoltage;
    private double distance, energy, coolantTemp;

    public VehicleMetrics(int batteryVoltage, double distance, double energy, double coolantTemp){
        this.batteryVoltage = batteryVoltage;
        this.distance = distance;
        this.energy = energy;
        this.coolantTemp = coolantTemp;
    }


    public int getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(int batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getCoolantTemp() {
        return coolantTemp;
    }

    public void setCoolantTemp(double coolantTemp) {
        this.coolantTemp = coolantTemp;
    }
}
