package com.example.senzagro.repository;

import com.example.senzagro.model.Floradevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Device extends JpaRepository<Floradevice, String> {

    Floradevice findFirstById(String id);
}
