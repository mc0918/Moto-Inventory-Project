package com.trilogyed.motoinventoryproject.controller;

import com.trilogyed.motoinventoryproject.dao.MotoInventoryDao;
import com.trilogyed.motoinventoryproject.model.Motorcycle;
import com.trilogyed.motoinventoryproject.util.feign.VinLookupClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RefreshScope
public class MotoInventoryController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private MotoInventoryDao motoInventoryDao;

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/motorcycles", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Motorcycle createMotorcycle(@RequestBody @Valid Motorcycle motorcycle) {
        Random rnd = new Random();

        motorcycle.setId(rnd.nextInt(9999));

        return motoInventoryDao.addMotorcycle(motorcycle);

//        return motorcycle;
    }

    @RequestMapping(value = "/motorcycles/{motoId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Motorcycle getMotorcycle(@PathVariable int motoId) throws IllegalArgumentException {
        if (motoId < 1) {
            throw new IllegalArgumentException("MotoId must be greater than 0.");
        }

        return motoInventoryDao.getMotorcycle(motoId);

//        Motorcycle moto = new Motorcycle();
//        moto.setId(motoId);
//        moto.setVin("54321");
//        moto.setMake("Ducati");
//        moto.setModel("Multistrada Enduro");
//        moto.setYear("2018");
//        moto.setColor("Red");
//
//        return moto;
    }

    @RequestMapping(value = "/motorcycles/{motoId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMotorcycle(@PathVariable("motoId") int motoId) {
        // do nothing here - in a real application we would delete the entry from
        // the backing data store.
        motoInventoryDao.deleteMotorcycle(motoId);
    }

    @RequestMapping(value = "/motorcycles/{motoId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMotorcycle(@RequestBody @Valid Motorcycle motorcycle, @PathVariable int motoId) {
        // make sure the motoId on the path matches the id of the motorcycle object
        if (motoId != motorcycle.getId()) {
            throw new IllegalArgumentException("Motorcycle ID on path must match the ID in the Motorcycle object.");
        }

        motoInventoryDao.updateMotorcycle(motorcycle);

        // do nothing here - in a real application we would update the entry in the backing data store

    }


    @Autowired
    private final VinLookupClient client;


    MotoInventoryController(VinLookupClient client) {
        this.client = client;
    }

    @RequestMapping(value="/vehicle/{vin}", method = RequestMethod.GET)
    public Map<String, String> helloCloud(@PathVariable @Valid String vin) {
        return client.getVehicleByVin(vin);
    }
}

