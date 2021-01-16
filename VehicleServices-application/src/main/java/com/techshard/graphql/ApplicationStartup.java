package com.techshard.graphql;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.techshard.graphql.dao.entity.Vehicle;
import com.techshard.graphql.service.VehicleService;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
    private VehicleService vehicleService;
    
    private static final String VEHICLE_LIST = "http://localhost:1337/vehicle/list";
    private static final String VEHICLE_INFO = "http://localhost:1337/vehicle/info";
    private static final String SERVICES_LIST = "http://localhost:1337/vehicle/services";
    
    private String getValue(JSONObject jsonObject, String key) {
    	try {
			return jsonObject.getString(key);
		} catch (Exception e) {
			return "";
		}
    }
    
    private String getJSONString(String link) throws Exception {
    	URL url = new URL(link);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        
        
        int responsecode = conn.getResponseCode();

        if (responsecode != 200) {
            return "{}";
        } else {
    		String text = "";
	        Scanner scanner = new Scanner(conn.getInputStream());
	
	        while (scanner.hasNext()) {
	        	text += scanner.nextLine();
	        }
	        scanner.close();
	        return text;
        }
    }
    
    private void saveData() throws Exception{
    	JSONObject vehiclesStr = new JSONObject(getJSONString(VEHICLE_LIST));
        
        JSONArray vehicles = (JSONArray) vehiclesStr.get("vehicles");

        for (int i = 0; i < vehicles.length(); i++) {
            JSONObject vehicle = (JSONObject) vehicles.get(i);
            
            String id = getValue(vehicle, "id");
            JSONObject infoStr = new JSONObject(getJSONString(VEHICLE_INFO + "?id=" + id));
            String name = getValue(vehicle, "name");
            String msidn = getValue(infoStr, "msidn");
            String engineStatus = getValue(infoStr, "engineStatus");
            String fleet = getValue(infoStr, "fleet");
            String brand = getValue(infoStr, "brand");
            String countryOfOperation = getValue(infoStr, "countryOfOperation");
            String chassisNumber = getValue(infoStr, "chassisNumber");
            String cassisSeries = getValue(infoStr, "cassisSeries");
            JSONObject servicesStr = new JSONObject(getJSONString(SERVICES_LIST + "?id=" + id));
            String communicationStatus = getValue(servicesStr, "communicationStatus");
            
            Vehicle newVehicle = vehicleService.createVehicle(name, msidn, engineStatus, fleet, brand, countryOfOperation, chassisNumber, cassisSeries, communicationStatus);
            
            JSONArray services = (JSONArray) servicesStr.get("services");
            for (int j = 0; j < services.length(); j++) {
            	JSONObject service = (JSONObject) services.get(j);
                
                String serviceName = getValue(service, "serviceName");
                String status = getValue(service, "status");
                String lastUpdate = getValue(service, "lastUpdate");
                vehicleService.createService(serviceName, status, lastUpdate, newVehicle.getID());
            }
            
            System.out.println(name);
            System.out.println(id);            
        }
    }

	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		try {
			saveData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}