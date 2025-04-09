package com.driver.services.impl;


import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        return adminRepository1.save(admin);
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin=adminRepository1.findById(adminId).orElse(null);
        ServiceProvider serviceProvider=new ServiceProvider();
        serviceProvider.setName(providerName);
        return serviceProviderRepository1.save(serviceProvider).getAdmin();
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception {
        ServiceProvider serviceProvider=serviceProviderRepository1.findById(serviceProviderId).orElse(null);
        Country country=new Country();
        country.setCountryName(CountryName.valueOf(countryName));
        country.setCountryCode(country.getCountryCode());
        country.setServiceProvider(serviceProvider);
        countryRepository1.save(country);
        return serviceProvider;
    }
}
