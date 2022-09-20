package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {
    @Mock
    private GeoService geoService;
    private LocalizationService localizationService;

    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
    }

    @Test
    void sendMessageToRussia() {
        Mockito.when(geoService.byIp(Mockito.startsWith("172"))).thenReturn(
                new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        String expected = messageSender.send(headers);
        String actual = "Добро пожаловать";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sendMessageToUSA() {
        Mockito.when(geoService.byIp(Mockito.startsWith("96"))).thenReturn(
                new Location("New York", Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.150");
        String expected = messageSender.send(headers);
        String actual = "Welcome";
        Assertions.assertEquals(expected, actual);
    }
}
