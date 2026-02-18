package com.example.odev.pattern.factory;

import com.example.odev.entity.Etkinlik;
import com.example.odev.entity.Haber;
import com.example.odev.entity.Duyuru;
import com.example.odev.enums.EtkinlikTipi;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class EtkinlikFactory {

    private final Map<EtkinlikTipi, Supplier<Etkinlik>> factoryMap = new EnumMap<>(EtkinlikTipi.class);

    public EtkinlikFactory() {
        factoryMap.put(EtkinlikTipi.HABER, Haber::new);
        factoryMap.put(EtkinlikTipi.DUYURU, Duyuru::new);
    }

    public Etkinlik olustur(EtkinlikTipi tip) {
        Supplier<Etkinlik> supplier = factoryMap.get(tip);

        if (supplier == null) {
            throw new IllegalArgumentException("Geçersiz etkinlik tipi: " + tip);
        }

        return supplier.get();
    }
}