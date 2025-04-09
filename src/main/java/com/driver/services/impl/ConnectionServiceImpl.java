package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;

    @Override
    public User connect(int userId, String countryName) throws Exception {
        User user = userRepository2.findById(userId).orElse(null);
        Country country = new Country();
        country.setCountryName(CountryName.valueOf(countryName));
        user.setOriginalCountry(country);
        return userRepository2.save(user);

    }
    @Override
    public User disconnect(int userId) throws Exception {
        User user=userRepository2.findById(userId).orElse(null);
        user.setOriginalCountry(null);
        return userRepository2.save(user);
    }
    @Override
    public User communicate(int senderId, int receiverId) throws Exception {
        User sender=userRepository2.findById(senderId).orElse(null);
        User receiver=userRepository2.findById(receiverId).orElse(null);

        if(sender==null || receiver==null){
            throw new Exception("User not connected with the same vpn");
        }
        System.out.println("Message from "+sender.getUsername()+" to "+receiver.getUsername());
        return sender;
    }
}
