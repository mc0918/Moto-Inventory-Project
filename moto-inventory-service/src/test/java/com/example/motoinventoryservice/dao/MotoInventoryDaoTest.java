package com.example.motoinventoryservice.dao;

import com.example.motoinventoryservice.dao.MotoInventoryDao;
import com.example.motoinventoryservice.model.Motorcycle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MotoInventoryDaoTest {

    @Autowired
    protected MotoInventoryDao dao;

    @Before
    public void setUp() throws Exception {
        // clean out the test db
        List<Motorcycle> mList = dao.getAllMotorcycles();

        mList.stream()
                .forEach(motorcycle -> dao.deleteMotorcycle(motorcycle.getId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteMotorcycle() {

        Motorcycle moto = new Motorcycle();
        moto.setVin("12345");
        moto.setMake("Honda");
        moto.setModel("Africa Twin");
        moto.setYear("2019");
        moto.setColor("black");

        moto = dao.addMotorcycle(moto);

        Motorcycle moto2 = dao.getMotorcycle(moto.getId());

        assertEquals(moto, moto2);

        dao.deleteMotorcycle(moto.getId());

        moto2 = dao.getMotorcycle(moto.getId());

        assertNull(moto2);
    }

    @Test
    public void getAllMotorcycles() {

        Motorcycle moto = new Motorcycle();
        moto.setVin("22222");
        moto.setMake("Honda");
        moto.setModel("CB300");
        moto.setYear("2019");
        moto.setColor("red");

        dao.addMotorcycle(moto);

        moto = new Motorcycle();
        moto.setVin("33333");
        moto.setMake("Suzuki");
        moto.setModel("DR650");
        moto.setYear("2012");
        moto.setColor("gray");

        dao.addMotorcycle(moto);

        List<Motorcycle> motoList = dao.getAllMotorcycles();

        assertEquals(motoList.size(), 2);
    }

    @Test
    public void getMotorcyclesByMake() {
        Motorcycle moto = new Motorcycle();
        moto.setVin("22222");
        moto.setMake("Honda");
        moto.setModel("CB300");
        moto.setYear("2019");
        moto.setColor("red");

        dao.addMotorcycle(moto);

        moto = new Motorcycle();
        moto.setVin("33333");
        moto.setMake("Suzuki");
        moto.setModel("DR650");
        moto.setYear("2012");
        moto.setColor("gray");

        dao.addMotorcycle(moto);

        moto = new Motorcycle();
        moto.setVin("44444");
        moto.setMake("Suzuki");
        moto.setModel("DRZ 400");
        moto.setYear("2016");
        moto.setColor("black");

        dao.addMotorcycle(moto);

        List<Motorcycle> mList = dao.getMotorcyclesByMake("Suzuki");
        assertEquals(2, mList.size());

        mList = dao.getMotorcyclesByMake("Honda");
        assertEquals(1, mList.size());

        mList = dao.getMotorcyclesByMake("Ducati");
        assertEquals(0, mList.size());

    }


    @Test
    public void updateMotorcycle() {

        Motorcycle moto = new Motorcycle();
        moto.setVin("12345");
        moto.setMake("Honda");
        moto.setModel("Africa Twin");
        moto.setYear("2019");
        moto.setColor("black");

        moto = dao.addMotorcycle(moto);

        moto.setVin("99999");
        moto.setMake("UPDATED");
        moto.setModel("NEW MODEL");
        moto.setYear("1111");
        moto.setColor("NEW COLOR");

        dao.updateMotorcycle(moto);

        Motorcycle moto2 = dao.getMotorcycle(moto.getId());

        assertEquals(moto2, moto);
    }

}