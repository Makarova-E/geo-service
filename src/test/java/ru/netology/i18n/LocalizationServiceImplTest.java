package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

public class LocalizationServiceImplTest {

    @Test
    void localeRussia() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        String actual = localizationService.locale(Country.RUSSIA);
        assertEquals(expected, actual);
    }

    @Test
    void localeOtherCountry() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expected = "Welcome";
        assertEquals(expected, localizationService.locale(Country.USA));
        assertEquals(expected, localizationService.locale(Country.GERMANY));
        assertEquals(expected, localizationService.locale(Country.BRAZIL));
    }
}
