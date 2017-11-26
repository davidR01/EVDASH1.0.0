package com.ecng_evdash07.evdash100;

public class VehicleMetrics {

    private String batteryVoltage;
    private String distance, energy, coolantTemp;

    public VehicleMetrics(String batteryVoltage, String distance, String energy, String coolantTemp){
        this.batteryVoltage = batteryVoltage;
        this.distance = distance;
        this.energy = energy;
        this.coolantTemp = coolantTemp;
    }


    public String getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(String batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public String  getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getCoolantTemp() {
        return coolantTemp;
    }

    public void setCoolantTemp(String coolantTemp) {
        this.coolantTemp = coolantTemp;
    }
}
