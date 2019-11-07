package com.trilogyed.motoinventoryproject.dao;

import com.trilogyed.motoinventoryproject.model.Motorcycle;

import java.util.List;

public interface MotoInventoryDao {
    Motorcycle getMotorcycle(int id);

    List<Motorcycle> getAllMotorcycles();

    Motorcycle addMotorcycle(Motorcycle motorcycle);

    void updateMotorcycle(Motorcycle motorcycle);

    void deleteMotorcycle(int id);

    List<Motorcycle> getMotorcyclesByMake(String make);

}

