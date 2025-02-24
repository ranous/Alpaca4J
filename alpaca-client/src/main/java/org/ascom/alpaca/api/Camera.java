package org.ascom.alpaca.api;

import org.ascom.alpaca.response.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface Camera {

    @GET("api/v1/camera/{deviceNumber}/bayeroffsetx")
    Call<IntResponse> getBayerOffsetX(@Path("deviceNumber") int deviceNumber,
                                      @Query("ClientID") int clientID,
                                      @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/bayeroffsety")
    Call<IntResponse> getBayerOffsetY(@Path("deviceNumber") int deviceNumber,
                                      @Query("ClientID") int clientID,
                                      @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/binx")
    Call<IntResponse> getBinX(@Path("deviceNumber") int deviceNumber,
                              @Query("ClientID") int clientID,
                              @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/binx")
    Call<AlpacaResponse> setBinX(@Path("deviceNumber") int deviceNumber,
                                 @Field("ClientID") int clientID,
                                 @Field("ClientTransactionID") long clientTransactionID,
                                 @Field("BinX") int binX);

    @GET("api/v1/camera/{deviceNumber}/biny")
    Call<IntResponse> getBinY(@Path("deviceNumber") int deviceNumber,
                              @Query("ClientID") int clientID,
                              @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/biny")
    Call<AlpacaResponse> setBinY(@Path("deviceNumber") int deviceNumber,
                                 @Field("ClientID") int clientID,
                                 @Field("ClientTransactionID") long clientTransactionID,
                                 @Field("BinY") int binY);

    @GET("api/v1/camera/{deviceNumber}/camerastate")
    Call<IntResponse> getCameraState(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/cameraxsize")
    Call<IntResponse> getCameraXSize(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/cameraysize")
    Call<IntResponse> getCameraYSize(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/canabortexposure")
    Call<BooleanResponse> canAbortExposure(@Path("deviceNumber") int deviceNumber,
                                           @Query("ClientID") int clientID,
                                           @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/canasymmetricbin")
    Call<BooleanResponse> canAsymmetricBin(@Path("deviceNumber") int deviceNumber,
                                           @Query("ClientID") int clientID,
                                           @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/canfastreadout")
    Call<BooleanResponse> canFastReadout(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/cangetcoolerpower")
    Call<BooleanResponse> canGetCoolerPower(@Path("deviceNumber") int deviceNumber,
                                            @Query("ClientID") int clientID,
                                            @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/canpulseguide")
    Call<BooleanResponse> canPulseGuide(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/cansetccdtemperature")
    Call<BooleanResponse> canSetCCDTemperature(@Path("deviceNumber") int deviceNumber,
                                               @Query("ClientID") int clientID,
                                               @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/canstopexposure")
    Call<BooleanResponse> canStopExposure(@Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/ccdtemperature")
    Call<DoubleResponse> getCCDTemperature(@Path("deviceNumber") int deviceNumber,
                                           @Query("ClientID") int clientID,
                                           @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/cooleron")
    Call<BooleanResponse> isCoolerOn(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/cooleron")
    Call<AlpacaResponse> setCoolerOn(@Path("deviceNumber") int deviceNumber,
                                     @Field("ClientID") int clientID,
                                     @Field("ClientTransactionID") long clientTransactionID,
                                     @Field("CoolerOn") boolean coolerOn);

    @GET("api/v1/camera/{deviceNumber}/coolerpower")
    Call<IntResponse> getCoolerPower(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/electronsperadu")
    Call<DoubleResponse> getElectronsPerADU(@Path("deviceNumber") int deviceNumber,
                                            @Query("ClientID") int clientID,
                                            @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/exposuremax")
    Call<DoubleResponse> getExposureMax(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/exposuremin")
    Call<DoubleResponse> getExposureMin(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/exposureresolution")
    Call<DoubleResponse> getExposureResolution(@Path("deviceNumber") int deviceNumber,
                                               @Query("ClientID") int clientID,
                                               @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/fastreadout")
    Call<BooleanResponse> getFastReadout(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/fastreadout")
    Call<AlpacaResponse> setFastReadout(@Path("deviceNumber") int deviceNumber,
                                        @Field("ClientID") int clientID,
                                        @Field("ClientTransactionID") long clientTransactionID,
                                        @Field("FastReadout") boolean fastReadout);

    @GET("api/v1/camera/{deviceNumber}/fullwellcapacity")
    Call<DoubleResponse> getFullWellCapacity(@Path("deviceNumber") int deviceNumber,
                                             @Query("ClientID") int clientID,
                                             @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/gain")
    Call<IntResponse> getGain(@Path("deviceNumber") int deviceNumber,
                              @Query("ClientID") int clientID,
                              @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/gain")
    Call<AlpacaResponse> setGain(@Path("deviceNumber") int deviceNumber,
                                 @Field("ClientID") int clientID,
                                 @Field("ClientTransactionID") long clientTransactionID,
                                 @Field("Gain") int gain);

    @GET("api/v1/camera/{deviceNumber}/gainmax")
    Call<IntResponse> getGainMax(@Path("deviceNumber") int deviceNumber,
                                 @Query("ClientID") int clientID,
                                 @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/gainmin")
    Call<IntResponse> getGainMin(@Path("deviceNumber") int deviceNumber,
                                 @Query("ClientID") int clientID,
                                 @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/gains")
    Call<ListResponse<String>> getGains(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/hasshutter")
    Call<BooleanResponse> hasShutter(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/heatsinktemperature")
    Call<DoubleResponse> getHeatSinkTemperature(@Path("deviceNumber") int deviceNumber,
                                                @Query("ClientID") int clientID,
                                                @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/imagearray")
    Call<ImageArrayResponse> getImageArray(@Path("deviceNumber") int deviceNumber,
                                           @Query("ClientID") int clientID,
                                           @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/imagearrayvariant")
    Call<ImageArrayResponse> getImageArrayVariant(@Path("deviceNumber") int deviceNumber,
                                                  @Query("ClientID") int clientID,
                                                  @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/imageready")
    Call<BooleanResponse> isImageReady(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/ispulseguiding")
    Call<BooleanResponse> isPulseGuiding(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/lastexposureduration")
    Call<DoubleResponse> getLastExposureDuration(@Path("deviceNumber") int deviceNumber,
                                                 @Query("ClientID") int clientID,
                                                 @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/lastexposurestarttime")
    Call<StringResponse> getLastExposureStartTime(@Path("deviceNumber") int deviceNumber,
                                                  @Query("ClientID") int clientID,
                                                  @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/maxadu")
    Call<IntResponse> getMaxADU(@Path("deviceNumber") int deviceNumber,
                                @Query("ClientID") int clientID,
                                @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/maxbinx")
    Call<IntResponse> getMaxBinX(@Path("deviceNumber") int deviceNumber,
                                 @Query("ClientID") int clientID,
                                 @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/maxbiny")
    Call<IntResponse> getMaxBinY(@Path("deviceNumber") int deviceNumber,
                                 @Query("ClientID") int clientID,
                                 @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/numx")
    Call<IntResponse> getNumX(@Path("deviceNumber") int deviceNumber,
                              @Query("ClientID") int clientID,
                              @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/numx")
    Call<AlpacaResponse> setNumX(@Path("deviceNumber") int deviceNumber,
                                 @Field("ClientID") int clientID,
                                 @Field("ClientTransactionID") long clientTransactionID,
                                 @Field("NumX") int numX);

    @GET("api/v1/camera/{deviceNumber}/numy")
    Call<IntResponse> getNumY(@Path("deviceNumber") int deviceNumber,
                              @Query("ClientID") int clientID,
                              @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/numy")
    Call<AlpacaResponse> setNumY(@Path("deviceNumber") int deviceNumber,
                                 @Field("ClientID") int clientID,
                                 @Field("ClientTransactionID") long clientTransactionID,
                                 @Field("NumY") int numY);

    @GET("api/v1/camera/{deviceNumber}/offset")
    Call<IntResponse> getOffset(@Path("deviceNumber") int deviceNumber,
                                @Query("ClientID") int clientID,
                                @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/offset")
    Call<AlpacaResponse> setOffset(@Path("deviceNumber") int deviceNumber,
                                   @Field("ClientID") int clientID,
                                   @Field("ClientTransactionID") long clientTransactionID,
                                   @Field("Offset") int offset);

    @GET("api/v1/camera/{deviceNumber}/offsetmax")
    Call<IntResponse> getOffsetMax(@Path("deviceNumber") int deviceNumber,
                                   @Query("ClientID") int clientID,
                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/offsetmin")
    Call<IntResponse> getOffsetMin(@Path("deviceNumber") int deviceNumber,
                                   @Query("ClientID") int clientID,
                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/offsets")
    Call<ListResponse<String>> getOffsets(@Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/percentcompleted")
    Call<IntResponse> getPercentCompleted(@Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/pixelsizex")
    Call<DoubleResponse> getPixelSizeX(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/pixelsizey")
    Call<DoubleResponse> getPixelSizeY(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/readoutmode")
    Call<IntResponse> getReadoutMode(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/readoutmode")
    Call<AlpacaResponse> setReadoutMode(@Path("deviceNumber") int deviceNumber,
                                        @Field("ClientID") int clientID,
                                        @Field("ClientTransactionID") long clientTransactionID,
                                        @Field("ReadoutMode") int readoutMode);

    @GET("api/v1/camera/{deviceNumber}/readoutmodes")
    Call<ListResponse<String>> getReadoutModes(@Path("deviceNumber") int deviceNumber,
                                               @Query("ClientID") int clientID,
                                               @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/sensorname")
    Call<StringResponse> getSensorName(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/sensortype")
    Call<IntResponse> getSensorType(@Path("deviceNumber") int deviceNumber,
                                    @Query("ClientID") int clientID,
                                    @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/camera/{deviceNumber}/setccdtemperature")
    Call<DoubleResponse> getSetCCDTemperature(@Path("deviceNumber") int deviceNumber,
                                              @Query("ClientID") int clientID,
                                              @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/setccdtemperature")
    Call<AlpacaResponse> setCCDTemperature(@Path("deviceNumber") int deviceNumber,
                                           @Field("ClientID") int clientID,
                                           @Field("ClientTransactionID") long clientTransactionID,
                                           @Field("SetCCDTemperature") double setCCDTemperature);

    @GET("api/v1/camera/{deviceNumber}/startx")
    Call<IntResponse> getStartX(@Path("deviceNumber") int deviceNumber,
                                @Query("ClientID") int clientID,
                                @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/camera/{deviceNumber}/startx")
    Call<AlpacaResponse> setStartX(@Path("deviceNumber") int deviceNumber,
                                   @Field("ClientID") int clientID,
                                   @Field("ClientTransactionID") long clientTransactionID,
                                   @Field("StartX") int startX);

    @GET("api/v1/camera/{deviceNumber}/starty")
    Call<IntResponse> getStartY(@Path("deviceNumber") int deviceNumber,
                                @Query("ClientID") int clientID,
                                @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("camera/{deviceNumber}/starty")
    Call<AlpacaResponse> setStartY(@Path("deviceNumber") int deviceNumber,
                                   @Field("ClientID") int clientID,
                                   @Field("ClientTransactionID") long clientTransactionID,
                                   @Field("StartY") int startY);

    @GET("camera/{deviceNumber}/subexposureduration")
    Call<DoubleResponse> getSubExposureDuration(@Path("deviceNumber") int deviceNumber,
                                                @Query("ClientID") int clientID,
                                                @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("camera/{deviceNumber}/subexposureduration")
    Call<AlpacaResponse> setSubExposureDuration(@Path("deviceNumber") int deviceNumber,
                                                @Field("ClientID") int clientID,
                                                @Field("ClientTransactionID") long clientTransactionID,
                                                @Field("SubExposureDuration") double subExposureDuration);

    @FormUrlEncoded
    @PUT("camera/{deviceNumber}/abortexposure")
    Call<AlpacaResponse> abortExposure(@Path("deviceNumber") int deviceNumber,
                                       @Field("ClientID") int clientID,
                                       @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("camera/{deviceNumber}/pulseguide")
    Call<AlpacaResponse> pulseGuide(@Path("deviceNumber") int deviceNumber,
                                    @Field("ClientID") int clientID,
                                    @Field("ClientTransactionID") long clientTransactionID,
                                    @Field("Direction") int direction,
                                    @Field("Duration") int duration);

    @FormUrlEncoded
    @PUT("camera/{deviceNumber}/startexposure")
    Call<AlpacaResponse> startExposure(@Path("deviceNumber") int deviceNumber,
                                       @Field("ClientID") int clientID,
                                       @Field("ClientTransactionID") long clientTransactionID,
                                       @Field("Duration") int duration,
                                       @Field("Light") boolean light);

    @FormUrlEncoded
    @PUT("camera/{deviceNumber}/stopexposure")
    Call<AlpacaResponse> stopExposure(@Path("deviceNumber") int deviceNumber,
                                      @Field("ClientID") int clientID,
                                      @Field("ClientTransactionID") long clientTransactionID);
}


