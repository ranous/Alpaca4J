# Alpaca Meteobridge Driver 
This is an Alpaca driver for the Meteobridge weather station software.  The driver is implemented 
in Java using the Alpaca4J framework.  The driver is a RESTful web service that provides access to 
the Meteobridge weather data.  The driver is implemented as a Quarkus web service.


This driver can be configured to get data from a Meteobridge device two different ways. The first way 
is to use the Meteobridge driver to poll the Meteobridge device for the weather data.  The second way 
is to have the Meteobridge driver to receive the weather data from the Meteobridge device via an 
HTTP GET request.

To push data to the Meteobridge driver, the Meteobridge device must be configured to send the weather 
data to the Meteobridge driver.  This is done by configuring an event under the Services tab in the 
Meteobridge device management interface.  The new event should be configured to be an HTTP request.  
The URL for the HTTP request should
be in the following format:

```
http://<IP Address of alpaca driver>:11111/weather/updateWeather?dewpoint=[th0dew-act]&humidity=[th0hum-act]&pressure=[thb0press-act]&rainrate=[rain0rate-act]&temperature=[th0temp-act]&winddirection=[wind0dir-act]&windgust=[wind0wind-max2]&windspeed=[wind0wind-act]
```

Configure the time interval you want the updates to occurr. The meteobridge sensor names are defined in 
https://www.meteobridge.com/wiki/index.php?title=Templates

The other mechansim to get data from the Meteobridge device is to have the Meteobridge driver poll the 
Meteobridge device for the weather data.  This is done by configuring the Meteobridge driver to poll the
Meteobridge device at a specified interval.  

