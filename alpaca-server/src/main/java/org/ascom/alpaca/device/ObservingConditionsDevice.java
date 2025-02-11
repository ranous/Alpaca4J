package org.ascom.alpaca.device;

@SuppressWarnings("unused")
public interface ObservingConditionsDevice extends Device {
    double getAveragePeriod(int clientID);
    void setAveragePeriod(int clientID, double averagePeriod);
    double getCloudCover(int clientID);
    double getDewPoint(int clientID);
    double getHumidity(int clientID);
    double getPressure(int clientID);
    double getRainRate(int clientID);
    double getSkyBrightness(int clientID);
    double getSkyQuality(int clientID);
    double getSkyTemperature(int clientID);
    double getStarFWHM(int clientID);
    double getTemperature(int clientID);
    double getWindDirection(int clientID);
    double getWindGust(int clientID);
    double getWindSpeed(int clientID);
    void refresh(int clientID);
    String getSensorDescription(int clientID, String sensorName);
    double getTimeSinceLastUpdate(int clientID, String sensorName);
}
