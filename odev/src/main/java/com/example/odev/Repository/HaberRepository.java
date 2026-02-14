package com.example.odev.Repository;

import com.example.odev.entity.Haber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HaberRepository extends JpaRepository<Haber, Long> {
}
