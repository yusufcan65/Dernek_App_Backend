package com.example.odev.Repository;

import com.example.odev.entity.Etkinlik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtkinlikRepository extends JpaRepository<Etkinlik, Long> {
}
