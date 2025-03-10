package org.ascom.alpaca.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.device.CameraDevice;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.ImageArray;
import org.ascom.alpaca.response.*;

@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@ApplicationScoped
public class CameraResource {
    @Inject
    DeviceManager deviceManager;

    private CameraDevice getDevice(int deviceID, int clientID) {
        CameraDevice device = deviceManager.getDevice(deviceID, DeviceType.Camera);
        device.checkConnectionStatus(clientID);
        return device;
    }

    @GET
    @Path("camera/{deviceNumber}/bayeroffsetx")
    public IntResponse getBayerOffsetX(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBayerOffsetX(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/bayeroffsety")
    public IntResponse getBayerOffsetY(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBayerOffsetY(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/binx")
    public IntResponse getBinX(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBinX(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/binx")
    public AlpacaResponse setBinX(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("BinX") int binX) {
        if (binX < 1 || binX > getDevice(deviceNumber, clientID).getMaxBinX(clientID)) {
            throw new InvalidValueException("The bin x value out of supported range");
        }
        getDevice(deviceNumber, clientID).setBinX(clientID, binX);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/biny")
    public IntResponse getBinY(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBinY(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/biny")
    public AlpacaResponse setBinY(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("BinY") int binY) {
        if (binY < 1 || binY > getDevice(deviceNumber, clientID).getMaxBinX(clientID)) {
            throw new InvalidValueException("The bin x value out of supported range");
        }
        getDevice(deviceNumber, clientID).setBinY(clientID, binY);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/camerastate")
    public IntResponse getCameraState(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCameraState(clientID).ordinal());
    }

    @GET
    @Path("camera/{deviceNumber}/cameraxsize")
    public IntResponse getCameraXSize(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCameraXSize(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/cameraysize")
    public IntResponse getCameraYSize(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCameraYSize(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/canabortexposure")
    public BooleanResponse canAbortExposure(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canAbortExposure(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/canasymmetricbin")
    public BooleanResponse canAsymmetricBin(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canAsymmetricBin(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/canfastreadout")
    public BooleanResponse canFastReadout(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canFastReadout(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/cangetcoolerpower")
    public BooleanResponse canGetCoolerPower(@PathParam("deviceNumber") int deviceNumber,
                                             @QueryParam("ClientID") int clientID,
                                             @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canGetCoolerPower(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/canpulseguide")
    public BooleanResponse canPulseGuide(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canPulseGuide(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/cansetccdtemperature")
    public BooleanResponse canSetCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                                @QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetCCDTemperature(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/canstopexposure")
    public BooleanResponse canStopExposure(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canStopExposure(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/ccdtemperature")
    public DoubleResponse getCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getCCDTemperature(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/cooleron")
    public BooleanResponse isCoolerOn(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isCoolerOn(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/cooleron")
    public AlpacaResponse setCoolerOn(@PathParam("deviceNumber") int deviceNumber,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID,
                                      @FormParam("CoolerOn") boolean coolerOn) {
        getDevice(deviceNumber, clientID).setCoolerOn(clientID, coolerOn);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/coolerpower")
    public IntResponse getCoolerPower(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCoolerPower(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/electronsperadu")
    public DoubleResponse getElectronsPerADU(@PathParam("deviceNumber") int deviceNumber,
                                             @QueryParam("ClientID") int clientID,
                                             @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getElectronsPerADU(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/exposuremax")
    public DoubleResponse getExposureMax(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getExposureMax(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/exposuremin")
    public DoubleResponse getExposureMin(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getExposureMin(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/exposureresolution")
    public DoubleResponse getExposureResolution(@PathParam("deviceNumber") int deviceNumber,
                                                @QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getExposureResolution(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/fastreadout")
    public BooleanResponse getFastReadout(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).getFastReadout(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/fastreadout")
    public AlpacaResponse setFastReadout(@PathParam("deviceNumber") int deviceNumber,
                                         @FormParam("ClientID") int clientID,
                                         @FormParam("ClientTransactionID") long clientTransactionID,
                                         @FormParam("FastReadout") boolean fastReadout) {
        getDevice(deviceNumber, clientID).setFastReadout(clientID, fastReadout);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/fullwellcapacity")
    public DoubleResponse getFullWellCapacity(@PathParam("deviceNumber") int deviceNumber,
                                              @QueryParam("ClientID") int clientID,
                                              @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getFullWellCapacity(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/gain")
    public IntResponse getGain(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getGain(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/gain")
    public AlpacaResponse setGain(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("Gain") int gain) {
        getDevice(deviceNumber, clientID).setGain(clientID, gain);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/gainmax")
    public IntResponse getGainMax(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getGainMax(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/gainmin")
    public IntResponse getGainMin(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getGainMin(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/gains")
    public ListResponse<String> getGains(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getGains(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/hasshutter")
    public BooleanResponse hasShutter(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).hasShutter(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/heatsinktemperature")
    public DoubleResponse getHeatSinkTemperature(@PathParam("deviceNumber") int deviceNumber,
                                                 @QueryParam("ClientID") int clientID,
                                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getHeatSinkTemperature(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/imagearray")
    public ImageArrayResponse getImageArray(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        ImageArray image = getDevice(deviceNumber, clientID).getImageArray(clientID);
        return new ImageArrayResponse(clientTransactionID, image);
    }

    @GET
    @Path("camera/{deviceNumber}/imagearrayvariant")
    public ImageArrayResponse getImageArrayVariant(@PathParam("deviceNumber") int deviceNumber,
                                                   @QueryParam("ClientID") int clientID,
                                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        ImageArray image = getDevice(deviceNumber, clientID).getImageArrayVariant(clientID);
        return new ImageArrayResponse(clientTransactionID, image);
    }

    @GET
    @Path("camera/{deviceNumber}/imageready")
    public BooleanResponse isImageReady(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isImageReady(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/ispulseguiding")
    public BooleanResponse isPulseGuiding(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isPulseGuiding(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/lastexposureduration")
    public DoubleResponse getLastExposureDuration(@PathParam("deviceNumber") int deviceNumber,
                                                  @QueryParam("ClientID") int clientID,
                                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getLastExposureDuration(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/lastexposurestarttime")
    public StringResponse getLastExposureStartTime(@PathParam("deviceNumber") int deviceNumber,
                                                   @QueryParam("ClientID") int clientID,
                                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new StringResponse(getDevice(deviceNumber, clientID).getLastExposureStartTime(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/maxadu")
    public IntResponse getMaxADU(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxADU(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/maxbinx")
    public IntResponse getMaxBinX(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxBinX(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/maxbiny")
    public IntResponse getMaxBinY(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxBinY(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/numx")
    public IntResponse getNumX(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getNumX(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/numx")
    public AlpacaResponse setNumX(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("NumX") int numX) {
        getDevice(deviceNumber, clientID).setNumX(clientID, numX);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/numy")
    public IntResponse getNumY(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getNumY(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/numy")
    public AlpacaResponse setNumY(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("NumY") int numY) {
        getDevice(deviceNumber, clientID).setNumY(clientID, numY);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/offset")
    public IntResponse getOffset(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getOffset(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/offset")
    public AlpacaResponse setOffset(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID,
                                    @FormParam("offset") int offset) {
        getDevice(deviceNumber, clientID).setOffset(clientID, offset);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/offsetmax")
    public IntResponse getOffsetMax(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getOffsetMax(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/offsetmin")
    public IntResponse getOffsetMin(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getOffsetMin(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/offsets")
    public ListResponse<String> getOffsets(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getOffsets(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/percentcompleted")
    public IntResponse getPercentCompleted(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getPercentCompleted(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/pixelsizex")
    public DoubleResponse getPixelSizeX(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getPixelSizeX(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/pixelsizey")
    public DoubleResponse getPixelSizeY(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getPixelSizeY(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/readoutmode")
    public IntResponse getReadoutMode(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getReadoutMode(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/readoutmode")
    public AlpacaResponse setReadoutMode(@PathParam("deviceNumber") int deviceNumber,
                                         @FormParam("ClientID") int clientID,
                                         @FormParam("ClientTransactionID") long clientTransactionID,
                                         @FormParam("ReadoutMode") int readoutMode) {
        getDevice(deviceNumber, clientID).setReadoutMode(clientID, readoutMode);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/readoutmodes")
    public ListResponse<String> getReadoutModes(@PathParam("deviceNumber") int deviceNumber,
                                                @QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getReadoutModes(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/sensorname")
    public StringResponse getSensorName(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new StringResponse(getDevice(deviceNumber, clientID).getSensorName(clientID));
    }

    @GET
    @Path("camera/{deviceNumber}/sensortype")
    public IntResponse getSensorType(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getSensorType(clientID).getType());
    }

    @GET
    @Path("camera/{deviceNumber}/setccdtemperature")
    public DoubleResponse getSetCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                               @QueryParam("ClientID") int clientID,
                                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSetCCDTemperature(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/setccdtemperature")
    public AlpacaResponse setCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                            @FormParam("ClientID") int clientID,
                                            @FormParam("ClientTransactionID") long clientTransactionID,
                                            @FormParam("SetCCDTemperature") double setCCDTemperature) {
        getDevice(deviceNumber, clientID).setCCDTemperature(clientID, setCCDTemperature);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/startx")
    public IntResponse getStartX(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getStartX(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/startx")
    public AlpacaResponse setStartX(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID,
                                    @FormParam("StartX") int startX) {
        getDevice(deviceNumber, clientID).setStartX(clientID, startX);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/starty")
    public IntResponse getStartY(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getStartY(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/starty")
    public AlpacaResponse setStartY(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID,
                                    @FormParam("StartY") int startY) {
        getDevice(deviceNumber, clientID).setStartY(clientID, startY);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/subexposureduration")
    public DoubleResponse getSubExposureDuration(@PathParam("deviceNumber") int deviceNumber,
                                                 @QueryParam("ClientID") int clientID,
                                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSubExposureDuration(clientID));
    }

    @PUT
    @Path("camera/{deviceNumber}/subexposureduration")
    public AlpacaResponse setSubExposureDuration(@PathParam("deviceNumber") int deviceNumber,
                                                 @FormParam("ClientID") int clientID,
                                                 @FormParam("ClientTransactionID") long clientTransactionID,
                                                 @FormParam("SubExposureDuration") double subExposureDuration) {
        getDevice(deviceNumber, clientID).setSubExposureDuration(clientID, subExposureDuration);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("camera/{deviceNumber}/abortexposure")
    public AlpacaResponse abortExposure(@PathParam("deviceNumber") int deviceNumber,
                                        @FormParam("ClientID") int clientID,
                                        @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).abortExposure(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("camera/{deviceNumber}/pulseguide")
    public AlpacaResponse pulseGuide(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID,
                                     @FormParam("Direction") int direction,
                                     @FormParam("Duration") int duration) {
        getDevice(deviceNumber, clientID).pulseGuide(clientID, direction, duration);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("camera/{deviceNumber}/startexposure")
    public AlpacaResponse startExposure(@PathParam("deviceNumber") int deviceNumber,
                                        @FormParam("ClientID") int clientID,
                                        @FormParam("ClientTransactionID") long clientTransactionID,
                                        @FormParam("Duration") double duration,
                                        @FormParam("Light") boolean light) {
        getDevice(deviceNumber, clientID).startExposure(clientID, duration, light);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("camera/{deviceNumber}/stopexposure")
    public AlpacaResponse stopExposure(@PathParam("deviceNumber") int deviceNumber,
                                       @FormParam("ClientID") int clientID,
                                       @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).stopExposure(clientID);
        return new AlpacaResponse(clientTransactionID);
    }
}
