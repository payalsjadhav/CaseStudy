package com.carwash.packageservice.service;

import com.carwash.packageservice.exception.AddOnNotFoundException;
import com.carwash.packageservice.model.AddOn;
import com.carwash.packageservice.repository.AddonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AddOnServiceImpl implements AddOnService{

    @Autowired
    AddonRepository addonRepository;

    @Override
    public AddOn createAddOn(AddOn addOn) {
     AddOn addOn1  = addonRepository.save(addOn);
        return addOn1;
    }

    @Override
    public AddOn updateAddon(AddOn addOn) {
        Optional<AddOn> addOn1=addonRepository.findById(addOn.getAddOnName());
        if (addOn1.isEmpty())
            throw new AddOnNotFoundException("nothing found with this name");
          addOn1.get().setAddOnPrice(addOn.getAddOnPrice());
          addOn1.get().setDescription(addOn.getDescription());
          addonRepository.save(addOn);
        return addOn;
    }

    @Override
    public List<AddOn> getAddonList() {
        return addonRepository.findAll();
    }

    @Override
    public AddOn getAddOnByName(String addOnName) {
        return addonRepository.findById(addOnName)
                .orElseThrow(()->new AddOnNotFoundException("nothing found with this name"));
    }

    @Override
    public void deleteAddOn(String addOnName) {
        addonRepository.findById(addOnName)
             .orElseThrow(()->new AddOnNotFoundException("nothing found with this name"));
        addonRepository.deleteById(addOnName);

    }
}
