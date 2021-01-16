package com.techshard.graphql.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Entity
public class Service implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column private String serviceName;
    @Column private String status;
    @Column private String lastUpdate;
    @Column private int vehicleID;
    
    public Service() {}
    
    public Service(String serviceName, String status, String lastUpdate,int vehicleID) {
		this.serviceName = serviceName;
		this.status = status;
		this.lastUpdate = lastUpdate;
		this.vehicleID = vehicleID;
	}	
    
    public int getVehicleID() {
		return vehicleID;
	}

}
