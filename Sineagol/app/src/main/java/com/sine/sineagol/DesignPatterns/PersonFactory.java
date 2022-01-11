package com.sine.sineagol.DesignPatterns;

import com.sine.sineagol.models.Customer;
import com.sine.sineagol.models.Person;
import com.sine.sineagol.models.Staff;

public class PersonFactory {

    public static Person getPerson(String id ,String name, String surname,String email, String phone, String type,double salary, String position, String startDate){
            if(type.equalsIgnoreCase("Staff")){
                return new Staff(id, name,surname, phone, email, salary,  position,  startDate);
            }
            else if (type.equalsIgnoreCase("Customer")){
                return new Customer( name, surname,email, phone);
            }
            return  null;
    }
}
