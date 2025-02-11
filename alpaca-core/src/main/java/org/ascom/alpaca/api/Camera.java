package org.ascom.alpaca.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.response.*;

@SuppressWarnings("SpellCheckingInspection")
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface Camera {
    @GET
    @Path("camera/{deviceNumber}/bayeroffsetx")
    IntResponse getBayerOffsetX(@PathParam("deviceNumber") int deviceNumber,
                                @QueryParam("ClientID") int clientID,
                                @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/bayeroffsety")
    IntResponse getBayerOffsetY(@PathParam("deviceNumber") int deviceNumber,
                                @QueryParam("ClientID") int clientID,
                                @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/binx")
    IntResponse getBinX(@PathParam("deviceNumber") int deviceNumber,
                        @QueryParam("ClientID") int clientID,
                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/binx")
    AlpacaResponse setBinX(@PathParam("deviceNumber") int deviceNumber,
                           @FormParam("ClientID") int clientID,
                           @FormParam("ClientTransactionID") long clientTransactionID,
                           @FormParam("BinX") int binX);

    @GET
    @Path("camera/{deviceNumber}/biny")
    IntResponse getBinY(@PathParam("deviceNumber") int deviceNumber,
                        @QueryParam("ClientID") int clientID,
                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/biny")
    AlpacaResponse setBinY(@PathParam("deviceNumber") int deviceNumber,
                           @FormParam("ClientID") int clientID,
                           @FormParam("ClientTransactionID") long clientTransactionID,
                           @FormParam("BinY") int binY);

    @GET
    @Path("camera/{deviceNumber}/camerastate")
    IntResponse getCameraState(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/cameraxsize")
    IntResponse getCameraXSize(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/cameraysize")
    IntResponse getCameraYSize(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/canabortexposure")
    BooleanResponse canAbortExposure(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/canasymmetricbin")
    BooleanResponse canAsymmetricBin(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/canfastreadout")
    BooleanResponse canFastReadout(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/cangetcoolerpower")
    BooleanResponse canGetCoolerPower(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/canpulseguide")
    BooleanResponse canPulseGuide(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/cansetccdtemperature")
    BooleanResponse canSetCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/canstopexposure")
    BooleanResponse canStopExposure(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/ccdtemperature")
    DoubleResponse getCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/cooleron")
    BooleanResponse isCoolerOn(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/cooleron")
    AlpacaResponse setCoolerOn(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID,
                               @FormParam("CoolerOn") boolean coolerOn);

    @GET
    @Path("camera/{deviceNumber}/coolerpower")
    IntResponse getCoolerPower(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/electronsperadu")
    DoubleResponse getElectronsPerADU(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/exposuremax")
    DoubleResponse getExposureMax(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/exposuremin")
    DoubleResponse getExposureMin(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/exposureresolution")
    DoubleResponse getExposureResolution(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/fastreadout")
    BooleanResponse getFastReadout(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/fastreadout")
    AlpacaResponse setFastReadout(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("FastReadout") boolean fastReadout);

    @GET
    @Path("camera/{deviceNumber}/fullwellcapacity")
    DoubleResponse getFullWellCapacity(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/gain")
    IntResponse getGain(@PathParam("deviceNumber") int deviceNumber,
                        @QueryParam("ClientID") int clientID,
                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/gain")
    AlpacaResponse setGain(@PathParam("deviceNumber") int deviceNumber,
                           @FormParam("ClientID") int clientID,
                           @FormParam("ClientTransactionID") long clientTransactionID,
                           @FormParam("Gain") int gain);

    @GET
    @Path("camera/{deviceNumber}/gainmax")
    IntResponse getGainMax(@PathParam("deviceNumber") int deviceNumber,
                           @QueryParam("ClientID") int clientID,
                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/gainmin")
    IntResponse getGainMin(@PathParam("deviceNumber") int deviceNumber,
                           @QueryParam("ClientID") int clientID,
                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/gains")
    ListResponse<String> getGains(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/hasshutter")
    BooleanResponse hasShutter(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/heatsinktemperature")
    DoubleResponse getHeatSinkTemperature(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/imagearray")
    ImageArrayResponse getImageArray(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/imagearrayvariant")
    ImageArrayResponse getImageArrayVariant(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/imageready")
    BooleanResponse isImageReady(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/ispulseguiding")
    BooleanResponse isPulseGuiding(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/lastexposureduration")
    DoubleResponse getLastExposureDuration(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/lastexposurestarttime")
    StringResponse getLastExposureStartTime(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/maxadu")
    IntResponse getMaxADU(@PathParam("deviceNumber") int deviceNumber,
                          @QueryParam("ClientID") int clientID,
                          @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/maxbinx")
    IntResponse getMaxBinX(@PathParam("deviceNumber") int deviceNumber,
                           @QueryParam("ClientID") int clientID,
                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/maxbiny")
    IntResponse getMaxBinY(@PathParam("deviceNumber") int deviceNumber,
                           @QueryParam("ClientID") int clientID,
                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/numx")
    IntResponse getNumX(@PathParam("deviceNumber") int deviceNumber,
                        @QueryParam("ClientID") int clientID,
                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/numx")
    AlpacaResponse setNumX(@PathParam("deviceNumber") int deviceNumber,
                           @FormParam("ClientID") int clientID,
                           @FormParam("ClientTransactionID") long clientTransactionID,
                           @FormParam("NumX") int numX);

    @GET
    @Path("camera/{deviceNumber}/numy")
    IntResponse getNumY(@PathParam("deviceNumber") int deviceNumber,
                        @QueryParam("ClientID") int clientID,
                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/numy")
    AlpacaResponse setNumY(@PathParam("deviceNumber") int deviceNumber,
                           @FormParam("ClientID") int clientID,
                           @FormParam("ClientTransactionID") long clientTransactionID,
                           @FormParam("NumX") int numY);

    @GET
    @Path("camera/{deviceNumber}/offset")
    IntResponse getOffset(@PathParam("deviceNumber") int deviceNumber,
                          @QueryParam("ClientID") int clientID,
                          @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/offset")
    AlpacaResponse setOffset(@PathParam("deviceNumber") int deviceNumber,
                             @FormParam("ClientID") int clientID,
                             @FormParam("ClientTransactionID") long clientTransactionID,
                             @FormParam("offset") int offset);

    @GET
    @Path("camera/{deviceNumber}/offsetmax")
    IntResponse getOffsetMax(@PathParam("deviceNumber") int deviceNumber,
                             @QueryParam("ClientID") int clientID,
                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/offsetmin")
    IntResponse getOffsetMin(@PathParam("deviceNumber") int deviceNumber,
                             @QueryParam("ClientID") int clientID,
                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/offsets")
    ListResponse<String> getOffsets(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/percentcompleted")
    IntResponse getPercentCompleted(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/pixelsizex")
    DoubleResponse getPixelSizeX(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/pixelsizey")
    DoubleResponse getPixelSizeY(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/readoutmode")
    IntResponse getReadoutMode(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/readoutmode")
    AlpacaResponse setReadoutMode(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("ReadoutMode") int readoutMode);

    @GET
    @Path("camera/{deviceNumber}/readoutmodes")
    ListResponse<String> getReadoutModes(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/sensorname")
    StringResponse getSensorName(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/sensortype")
    IntResponse getSensorType(@PathParam("deviceNumber") int deviceNumber,
                              @QueryParam("ClientID") int clientID,
                              @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("camera/{deviceNumber}/setccdtemperature")
    DoubleResponse getSetCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/setccdtemperature")
    AlpacaResponse setCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID,
                                     @FormParam("SetCCDTemperature") double setCCDTemperature);

    @GET
    @Path("camera/{deviceNumber}/startx")
    IntResponse getStartX(@PathParam("deviceNumber") int deviceNumber,
                          @QueryParam("ClientID") int clientID,
                          @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/startx")
    AlpacaResponse setStartX(@PathParam("deviceNumber") int deviceNumber,
                             @FormParam("ClientID") int clientID,
                             @FormParam("ClientTransactionID") long clientTransactionID,
                             @FormParam("StartX") int startX);

    @GET
    @Path("camera/{deviceNumber}/starty")
    IntResponse getStartY(@PathParam("deviceNumber") int deviceNumber,
                          @QueryParam("ClientID") int clientID,
                          @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/starty")
    AlpacaResponse setStartY(@PathParam("deviceNumber") int deviceNumber,
                             @FormParam("ClientID") int clientID,
                             @FormParam("ClientTransactionID") long clientTransactionID,
                             @FormParam("StartY") int startY);

    @GET
    @Path("camera/{deviceNumber}/subexposureduration")
    DoubleResponse getSubExposureDuration(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/subexposureduration")
    AlpacaResponse setSubExposureDuration(@PathParam("deviceNumber") int deviceNumber,
                                          @FormParam("ClientID") int clientID,
                                          @FormParam("ClientTransactionID") long clientTransactionID,
                                          @FormParam("SubExposureDuration") double subExposureDuration);

    @PUT
    @Path("camera/{deviceNumber}/abortexposure")
    AlpacaResponse abortExposure(@PathParam("deviceNumber") int deviceNumber,
                                 @FormParam("ClientID") int clientID,
                                 @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("camera/{deviceNumber}/pulseguide")
    AlpacaResponse pulseGuide(@PathParam("deviceNumber") int deviceNumber,
                              @FormParam("ClientID") int clientID,
                              @FormParam("ClientTransactionID") long clientTransactionID,
                              @FormParam("Direction") int direction,
                              @FormParam("Duration") int duration);

    @PUT
    @Path("camera/{deviceNumber}/startexposure")
    AlpacaResponse startExposure(@PathParam("deviceNumber") int deviceNumber,
                                 @FormParam("ClientID") int clientID,
                                 @FormParam("ClientTransactionID") long clientTransactionID,
                                 @FormParam("Duration") int duration,
                                 @FormParam("Light") boolean light);

    @PUT
    @Path("camera/{deviceNumber}/stopexposure")
    AlpacaResponse stopExposure(@PathParam("deviceNumber") int deviceNumber,
                                @FormParam("ClientID") int clientID,
                                @FormParam("ClientTransactionID") long clientTransactionID);


}


