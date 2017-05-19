/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import pizzaheaven.globals.Globals;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import pizzaheaven.models.Image;

/**
 *
 * @author dansc
 */
public class ImagesController extends APIController implements IAPIController {
    Image[] cachedImages; 
    
    @Override
    public Image[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/Images" + (forceUpdate ? ". Forcing cache to update..." : ""));
        Image[] imageList = new Image[0];
        if (!forceUpdate) {
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedImages != null) {
                return cachedImages;
            }
        }
        
        try {
            imageList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/Images"), Image[].class);
            cachedImages = imageList;
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return imageList;
    }
    
    public Image[] get() {
        return get(false);
    }
    
    public Image get(String name) {
        for (Image img : get()) {
            if (img.getName().equals(name)) {
                return img;
            }
        }
        return null;
    }
    
    @Override
    public int add(Object obj) {
        if (obj instanceof Image) {
            Image img = (Image)obj; 
            return post(img, Globals.API_ENDPOINT + "/Images/");
        }
        return -1;
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof Image) {
            Image img = (Image)obj;
            try {
                return put(img, Globals.API_ENDPOINT + "/Images/" + URLEncoder.encode(img.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                System.out.println("[ERR] " + e.getMessage());
            }
        }
        return -1;
    }
    
    @Override
    public int remove(Object obj) {
        if (obj instanceof Image) {
            Image img = (Image)obj;
            try {
                return delete(Globals.API_ENDPOINT + "/Images/" + URLEncoder.encode(img.getName(), "UTF-8").replace("+", "%20"));
            } catch (Exception e) {
                
            }
        }
        return -1;
    }
    
    @Override
    public Image[] getCached() {
        return this.cachedImages;
    }
}
