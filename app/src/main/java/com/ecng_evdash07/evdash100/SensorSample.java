package com.ecng_evdash07.evdash100;



class SensorSample {
    private float voltage;
    private float distance;
    private float milage;

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getMilage() {
        return milage;
    }

    public void setMilage(float milage) {
        this.milage = milage;
    }

    @Override
    public String toString() {
        return "SensorSample{" +
                "voltage=" + voltage +
                ", distance=" + distance +
                ", milage=" + milage +
                '}';
    }
}
