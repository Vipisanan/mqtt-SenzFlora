package com.example.senzagro.controller;


import com.example.senzagro.model.Floradevice;
import com.example.senzagro.repository.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/floradevice")
public class FloradeviceController {

    @Autowired
    Device device;

    @GetMapping("/de")
    public List<Floradevice> getAll(){
        return device.findAll();
    }

    //save data
    @PostMapping("")
    public Floradevice Save(@Valid @RequestBody Floradevice floradevice){
        return device.save(floradevice);
    }

    //get find by id
    //    @GetMapping("/{id}")
    //    public

    //update
    @PostMapping("/{id}")
    private void update(@PathVariable String id , @RequestBody Floradevice updatefloradevice){
        Floradevice floradevice = new Floradevice();
        floradevice = device.findFirstById(id);

    }


}
