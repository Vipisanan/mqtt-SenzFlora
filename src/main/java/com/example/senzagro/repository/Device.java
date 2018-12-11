package com.example.senzagro.repository;

import com.example.senzagro.model.Floradevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Device extends JpaRepository<Floradevice, String> {

    Floradevice findFirstById(String id);
}
