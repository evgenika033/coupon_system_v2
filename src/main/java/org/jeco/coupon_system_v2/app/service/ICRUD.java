package org.jeco.coupon_system_v2.app.service;

import java.util.List;

public interface ICRUD <T>{
    void add(T addObject);
    void update(T updateObject);
    void delete(int id);
    T get(int id);
    List<T> get();


}
