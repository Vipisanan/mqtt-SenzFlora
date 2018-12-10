package com.example.senzagro.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Floradevice")
public class Floradevice {

    @Id
    private String id;
    private String mi_light;
    private String mi_conductivity;
    private String mi_battery;
    private String temperature;
    private String mac_id;

    private Date created;
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public Floradevice() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMi_light() {
        return mi_light;
    }

    public void setMi_light(String mi_light) {
        this.mi_light = mi_light;
    }

    public String getMi_conductivity() {
        return mi_conductivity;
    }

    public void setMi_conductivity(String mi_conductivity) {
        this.mi_conductivity = mi_conductivity;
    }

    public String getMi_battery() {
        return mi_battery;
    }

    public void setMi_battery(String mi_battery) {
        this.mi_battery = mi_battery;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMac_id() {
        return mac_id;
    }

    public void setMac_id(String mac_id) {
        this.mac_id = mac_id;
    }
}
