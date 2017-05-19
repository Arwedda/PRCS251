/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;
import java.net.URL;
import pizzaheaven.models.Staff;
import pizzaheaven.globals.Globals;
import java.util.Date;

/**
 * 
 * @author dansc
 */
public class StaffController extends APIController implements IAPIController {
    Staff[] cachedStaff;
    
    @Override
    public Staff[] getCached() {
        return cachedStaff;
    }
    
    @Override
    public Staff[] get() {
        return get(false);
    }
    
    public Staff get(String staffID) {
        for (Staff staff : get()) {
            if (staff.getStaffID().equals(staffID)) {
                return staff;
            }
        }
        return null;
    }
    
    private Staff[] sortNumeric(Staff[] staffList){
        for (int i = (staffList.length - 1); i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (Integer.parseInt(staffList[j - 1].getStaffID()) > Integer.parseInt(staffList[j].getStaffID())) {
                    Staff temp = staffList[j - 1];
                    staffList[j - 1] = staffList[j];
                    staffList[j] = temp;
                }
            }
        }
        return staffList;
    }
    
    @Override
    public Staff[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/Staff" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate) {
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedStaff != null) {
                return cachedStaff;
            }
        }
        
        Staff[] staffList = new Staff[0];
        try {
            staffList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/Staff"), Staff[].class);
            cachedStaff = sortNumeric(staffList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cachedStaff;
    }
    
    @Override
    public int remove(Object obj) {
        if (obj instanceof Staff) {
            Staff staff = (Staff)obj;
            return delete(Globals.API_ENDPOINT + "/Staff/" + staff.getStaffID());
        }
        return -1;
    }
    
    @Override 
    public int add(Object obj) {
        if (obj instanceof Staff) {
            Staff staff = (Staff)obj;
            return post(staff, Globals.API_ENDPOINT + "/Staff/");
        }
        return -1;
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof Staff) {
            Staff staff = (Staff)obj;
            return put(staff, Globals.API_ENDPOINT + "/Staff/" + staff.getStaffID());
        }
        return -1;
    }
    
    public int remove(String staffID) {
        for (Staff staff : get()) {
            if (staff.getStaffID().equals(staffID)) {
                remove(staff);
            }
        }
        return -1;
    }
}
