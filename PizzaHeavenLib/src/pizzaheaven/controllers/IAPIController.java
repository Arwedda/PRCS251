/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.controllers;

/**
 *
 * @author dansc
 */
public interface IAPIController<T> {
    public T get();
    public T get(Boolean forceUpdate);
    public T getCached();
    public int add(T object);
    public int update(T object);
    public int remove(T object);
}
