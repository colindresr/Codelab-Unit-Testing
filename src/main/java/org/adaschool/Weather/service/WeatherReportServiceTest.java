package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherApiResponse.Main;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherReport() {
        double latitude = 37.8267;
        double longitude = -122.4233;

        // Mock the response from the external API
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        Main mockMain = new Main();
        mockMain.setTemperature(22.5);
        mockMain.setHumidity(60);
        mockApiResponse.setMain(mockMain);

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=your-api-key";

        when(restTemplate.getForObject(apiUrl, WeatherApiResponse.class)).thenReturn(mockApiResponse);

        WeatherReport weatherReport = weatherReportService.getWeatherReport(latitude, longitude);

        assertEquals(22.5, weatherReport.getTemperature());
        assertEquals(60, weatherReport.getHumidity());
    }
}