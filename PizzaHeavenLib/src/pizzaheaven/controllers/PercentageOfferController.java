/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import pizzaheaven.globals.Globals;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import pizzaheaven.models.PercentageOffer;

/**
 *
 * @author Joseph Kellaway + Craig Banyard
 */
public class PercentageOfferController extends APIController implements IAPIController {
    private PercentageOffer[] cachedPercentageOffers;
    
    @Override 
    public PercentageOffer[] get(Boolean forceUpdate) {
        System.out.println("[INFO] Fetching resource from: " + Globals.API_ENDPOINT + "/PercentageOffers" + (forceUpdate ? ". Forcing cache to update..." : ""));
        if (!forceUpdate) {
            long millisecondsSinceLastUpdate = (new Date().getTime() - lastUpdated.getTime());
            if (millisecondsSinceLastUpdate <= Globals.API_CACHE_INTERVAL && cachedPercentageOffers != null) {
                return cachedPercentageOffers;
            }
        }
        
        PercentageOffer[] percentageOffersList  = new PercentageOffer[0];
        try {
            percentageOffersList = mapper.readValue(new URL(Globals.API_ENDPOINT + "/PercentageOffers"), PercentageOffer[].class);
            cachedPercentageOffers = sortHighToLow(percentageOffersList);
            lastUpdated = new Date();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return percentageOffersList;
    }
    
    private PercentageOffer[] sortHighToLow(PercentageOffer[] offers){
        Arrays.sort(offers, new Comparator<PercentageOffer>() {
            @Override
            public int compare(PercentageOffer p1, PercentageOffer p2) {
                return p1.getDiscount().compareToIgnoreCase(p2.getDiscount());
            }
        });
        Collections.reverse(Arrays.asList(offers));
        return offers;
    }
    
    public PercentageOffer[] getForID(int id) {
        ArrayList<PercentageOffer> offers = new ArrayList<PercentageOffer>();
        for (PercentageOffer offer : get(false)) {
            if (offer.getOfferID().equals(String.valueOf(id))) {
                offers.add(offer);
            }
        }
        return offers.toArray(new PercentageOffer[offers.toArray().length]);
    }
    
    @Override 
    public int update(Object obj) {
        if (obj instanceof PercentageOffer) {
            return put((PercentageOffer)obj, Globals.API_ENDPOINT + "/PercentageOffers/" + ((PercentageOffer)obj).getOfferID());
        }
        return -1;
    }
    
    @Override 
    public PercentageOffer[] getCached() {
        return cachedPercentageOffers;
    }
    
    @Override
    public int add(Object obj) {
       if (obj instanceof PercentageOffer) {
           return post((PercentageOffer)obj, Globals.API_ENDPOINT + "/PercentageOffers/");
       }
       return -1;
    }
    
    @Override
    public int remove(Object obj) {
        if (obj instanceof PercentageOffer) {
            try {
                return delete(Globals.API_ENDPOINT + "/PercentageOffers/" + ((PercentageOffer)obj).getOfferID());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }

    @Deprecated
    @Override
    public Object get() {
        //old get - deprecated
        return null;
    }
}