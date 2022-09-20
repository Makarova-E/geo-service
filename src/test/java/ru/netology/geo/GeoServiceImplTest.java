package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTest {
    private static final String LOCALHOST = "127.0.0.1";
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";

    @Test
    void byIpMoscow() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp(MOSCOW_IP);

        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Lenina", location.getStreet());
        assertEquals(15, location.getBuiling());
    }

    @Test
    void byIpNewYork() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp(NEW_YORK_IP);
// используется лямбда 8
        assertAll("Сравниваем по очереди параметры",
                () -> assertEquals("New York", location.getCity()),
                () -> assertEquals(Country.USA, location.getCountry()),
                () -> assertEquals(" 10th Avenue", location.getStreet()),
                () -> assertEquals(32, location.getBuiling())
        );
    }

    @Test
    void byIpLocalhost() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp(LOCALHOST);
        assertAll("Сравниваем по очереди параметры",
                () -> assertEquals(null, location.getCity()),
                () -> assertEquals(null, location.getCountry()),
                () -> assertEquals(null, location.getStreet()),
                () -> assertEquals(0, location.getBuiling())
        );
    }
}

