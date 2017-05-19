/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

import pizzaheaven.models.Session;

/**
 *
 * @author drscott
 */
public class SessionController {
    private Session session;
    public Session getSession() {
        return session;
    }
    
    public void createSession(Session session) {
        this.session = session;
    }
    
    public SessionController(Session session) {
        this.session = session;
    }
    
    public void destroySesion() {
        this.session = null;
    }
}
