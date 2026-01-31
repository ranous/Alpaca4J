package org.ascom.alpaca.impl.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.device.CameraDevice;
import org.ascom.alpaca.impl.DeviceManager;
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
        return new IntResponse(getDevice(deviceNumber, clientID).getBayerOffsetX());
    }

    @GET
    @Path("camera/{deviceNumber}/bayeroffsety")
    public IntResponse getBayerOffsetY(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBayerOffsetY());
    }

    @GET
    @Path("camera/{deviceNumber}/binx")
    public IntResponse getBinX(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBinX());
    }

    @PUT
    @Path("camera/{deviceNumber}/binx")
    public AlpacaResponse setBinX(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("BinX") int binX) {
        if (binX < 1 || binX > getDevice(deviceNumber, clientID).getMaxBinX()) {
            throw new InvalidValueException("The bin x value out of supported range");
        }
        getDevice(deviceNumber, clientID).setBinX(binX);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/biny")
    public IntResponse getBinY(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBinY());
    }

    @PUT
    @Path("camera/{deviceNumber}/biny")
    public AlpacaResponse setBinY(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("BinY") int binY) {
        if (binY < 1 || binY > getDevice(deviceNumber, clientID).getMaxBinX()) {
            throw new InvalidValueException("The bin x value out of supported range");
        }
        getDevice(deviceNumber, clientID).setBinY(binY);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/camerastate")
    public IntResponse getCameraState(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCameraState().ordinal());
    }

    @GET
    @Path("camera/{deviceNumber}/cameraxsize")
    public IntResponse getCameraXSize(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCameraXSize());
    }

    @GET
    @Path("camera/{deviceNumber}/cameraysize")
    public IntResponse getCameraYSize(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCameraYSize());
    }

    @GET
    @Path("camera/{deviceNumber}/canabortexposure")
    public BooleanResponse canAbortExposure(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canAbortExposure());
    }

    @GET
    @Path("camera/{deviceNumber}/canasymmetricbin")
    public BooleanResponse canAsymmetricBin(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canAsymmetricBin());
    }

    @GET
    @Path("camera/{deviceNumber}/canfastreadout")
    public BooleanResponse canFastReadout(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canFastReadout());
    }

    @GET
    @Path("camera/{deviceNumber}/cangetcoolerpower")
    public BooleanResponse canGetCoolerPower(@PathParam("deviceNumber") int deviceNumber,
                                             @QueryParam("ClientID") int clientID,
                                             @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canGetCoolerPower());
    }

    @GET
    @Path("camera/{deviceNumber}/canpulseguide")
    public BooleanResponse canPulseGuide(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canPulseGuide());
    }

    @GET
    @Path("camera/{deviceNumber}/cansetccdtemperature")
    public BooleanResponse canSetCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                                @QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetCCDTemperature());
    }

    @GET
    @Path("camera/{deviceNumber}/canstopexposure")
    public BooleanResponse canStopExposure(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canStopExposure());
    }

    @GET
    @Path("camera/{deviceNumber}/ccdtemperature")
    public DoubleResponse getCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getCCDTemperature());
    }

    @GET
    @Path("camera/{deviceNumber}/cooleron")
    public BooleanResponse isCoolerOn(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isCoolerOn());
    }

    @PUT
    @Path("camera/{deviceNumber}/cooleron")
    public AlpacaResponse setCoolerOn(@PathParam("deviceNumber") int deviceNumber,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID,
                                      @FormParam("CoolerOn") boolean coolerOn) {
        getDevice(deviceNumber, clientID).setCoolerOn(coolerOn);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/coolerpower")
    public IntResponse getCoolerPower(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCoolerPower());
    }

    @GET
    @Path("camera/{deviceNumber}/electronsperadu")
    public DoubleResponse getElectronsPerADU(@PathParam("deviceNumber") int deviceNumber,
                                             @QueryParam("ClientID") int clientID,
                                             @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getElectronsPerADU());
    }

    @GET
    @Path("camera/{deviceNumber}/exposuremax")
    public DoubleResponse getExposureMax(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getExposureMax());
    }

    @GET
    @Path("camera/{deviceNumber}/exposuremin")
    public DoubleResponse getExposureMin(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getExposureMin());
    }

    @GET
    @Path("camera/{deviceNumber}/exposureresolution")
    public DoubleResponse getExposureResolution(@PathParam("deviceNumber") int deviceNumber,
                                                @QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getExposureResolution());
    }

    @GET
    @Path("camera/{deviceNumber}/fastreadout")
    public BooleanResponse getFastReadout(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).getFastReadout());
    }

    @PUT
    @Path("camera/{deviceNumber}/fastreadout")
    public AlpacaResponse setFastReadout(@PathParam("deviceNumber") int deviceNumber,
                                         @FormParam("ClientID") int clientID,
                                         @FormParam("ClientTransactionID") long clientTransactionID,
                                         @FormParam("FastReadout") boolean fastReadout) {
        getDevice(deviceNumber, clientID).setFastReadout(fastReadout);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/fullwellcapacity")
    public DoubleResponse getFullWellCapacity(@PathParam("deviceNumber") int deviceNumber,
                                              @QueryParam("ClientID") int clientID,
                                              @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getFullWellCapacity());
    }

    @GET
    @Path("camera/{deviceNumber}/gain")
    public IntResponse getGain(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getGain());
    }

    @PUT
    @Path("camera/{deviceNumber}/gain")
    public AlpacaResponse setGain(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("Gain") int gain) {
        getDevice(deviceNumber, clientID).setGain(gain);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/gainmax")
    public IntResponse getGainMax(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getGainMax());
    }

    @GET
    @Path("camera/{deviceNumber}/gainmin")
    public IntResponse getGainMin(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getGainMin());
    }

    @GET
    @Path("camera/{deviceNumber}/gains")
    public ListResponse<String> getGains(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getGains());
    }

    @GET
    @Path("camera/{deviceNumber}/hasshutter")
    public BooleanResponse hasShutter(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).hasShutter());
    }

    @GET
    @Path("camera/{deviceNumber}/heatsinktemperature")
    public DoubleResponse getHeatSinkTemperature(@PathParam("deviceNumber") int deviceNumber,
                                                 @QueryParam("ClientID") int clientID,
                                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getHeatSinkTemperature());
    }

    @GET
    @Path("camera/{deviceNumber}/imagearray")
    public ImageArrayResponse getImageArray(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        ImageArray image = getDevice(deviceNumber, clientID).getImageArray();
        return new ImageArrayResponse(clientTransactionID, image);
    }

    @GET
    @Path("camera/{deviceNumber}/imagearray")
    @Produces("application/imagebytes")
    public ByteArrayResponse getImageBytes(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        byte[] image = getDevice(deviceNumber, clientID).getImageBytes();
        return new ByteArrayResponse(clientTransactionID, image);
    }

    @GET
    @Path("camera/{deviceNumber}/imagearrayvariant")
    public ImageArrayResponse getImageArrayVariant(@PathParam("deviceNumber") int deviceNumber,
                                                   @QueryParam("ClientID") int clientID,
                                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        // This is a COM-specific operation and not implemented by Alpca devices
        throw new PropertyNotImplementedException("Not implemented");
    }

    @GET
    @Path("camera/{deviceNumber}/imageready")
    public BooleanResponse isImageReady(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isImageReady());
    }

    @GET
    @Path("camera/{deviceNumber}/ispulseguiding")
    public BooleanResponse isPulseGuiding(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isPulseGuiding());
    }

    @GET
    @Path("camera/{deviceNumber}/lastexposureduration")
    public DoubleResponse getLastExposureDuration(@PathParam("deviceNumber") int deviceNumber,
                                                  @QueryParam("ClientID") int clientID,
                                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getLastExposureDuration());
    }

    @GET
    @Path("camera/{deviceNumber}/lastexposurestarttime")
    public StringResponse getLastExposureStartTime(@PathParam("deviceNumber") int deviceNumber,
                                                   @QueryParam("ClientID") int clientID,
                                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new StringResponse(getDevice(deviceNumber, clientID).getLastExposureStartTime());
    }

    @GET
    @Path("camera/{deviceNumber}/maxadu")
    public IntResponse getMaxADU(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxADU());
    }

    @GET
    @Path("camera/{deviceNumber}/maxbinx")
    public IntResponse getMaxBinX(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxBinX());
    }

    @GET
    @Path("camera/{deviceNumber}/maxbiny")
    public IntResponse getMaxBinY(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxBinY());
    }

    @GET
    @Path("camera/{deviceNumber}/numx")
    public IntResponse getNumX(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getNumX());
    }

    @PUT
    @Path("camera/{deviceNumber}/numx")
    public AlpacaResponse setNumX(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("NumX") int numX) {
        getDevice(deviceNumber, clientID).setNumX(numX);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/numy")
    public IntResponse getNumY(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getNumY());
    }

    @PUT
    @Path("camera/{deviceNumber}/numy")
    public AlpacaResponse setNumY(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("NumY") int numY) {
        getDevice(deviceNumber, clientID).setNumY(numY);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/offset")
    public IntResponse getOffset(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getOffset());
    }

    @PUT
    @Path("camera/{deviceNumber}/offset")
    public AlpacaResponse setOffset(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID,
                                    @FormParam("offset") int offset) {
        getDevice(deviceNumber, clientID).setOffset(offset);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/offsetmax")
    public IntResponse getOffsetMax(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getOffsetMax());
    }

    @GET
    @Path("camera/{deviceNumber}/offsetmin")
    public IntResponse getOffsetMin(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getOffsetMin());
    }

    @GET
    @Path("camera/{deviceNumber}/offsets")
    public ListResponse<String> getOffsets(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getOffsets());
    }

    @GET
    @Path("camera/{deviceNumber}/percentcompleted")
    public IntResponse getPercentCompleted(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getPercentCompleted());
    }

    @GET
    @Path("camera/{deviceNumber}/pixelsizex")
    public DoubleResponse getPixelSizeX(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getPixelSizeX());
    }

    @GET
    @Path("camera/{deviceNumber}/pixelsizey")
    public DoubleResponse getPixelSizeY(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getPixelSizeY());
    }

    @GET
    @Path("camera/{deviceNumber}/readoutmode")
    public IntResponse getReadoutMode(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getReadoutMode());
    }

    @PUT
    @Path("camera/{deviceNumber}/readoutmode")
    public AlpacaResponse setReadoutMode(@PathParam("deviceNumber") int deviceNumber,
                                         @FormParam("ClientID") int clientID,
                                         @FormParam("ClientTransactionID") long clientTransactionID,
                                         @FormParam("ReadoutMode") int readoutMode) {
        getDevice(deviceNumber, clientID).setReadoutMode(readoutMode);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/readoutmodes")
    public ListResponse<String> getReadoutModes(@PathParam("deviceNumber") int deviceNumber,
                                                @QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getReadoutModes());
    }

    @GET
    @Path("camera/{deviceNumber}/sensorname")
    public StringResponse getSensorName(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new StringResponse(getDevice(deviceNumber, clientID).getSensorName());
    }

    @GET
    @Path("camera/{deviceNumber}/sensortype")
    public IntResponse getSensorType(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getSensorType().getType());
    }

    @GET
    @Path("camera/{deviceNumber}/setccdtemperature")
    public DoubleResponse getSetCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                               @QueryParam("ClientID") int clientID,
                                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSetCCDTemperature());
    }

    @PUT
    @Path("camera/{deviceNumber}/setccdtemperature")
    public AlpacaResponse setCCDTemperature(@PathParam("deviceNumber") int deviceNumber,
                                            @FormParam("ClientID") int clientID,
                                            @FormParam("ClientTransactionID") long clientTransactionID,
                                            @FormParam("SetCCDTemperature") double setCCDTemperature) {
        getDevice(deviceNumber, clientID).setCCDTemperature(setCCDTemperature);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/startx")
    public IntResponse getStartX(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getStartX());
    }

    @PUT
    @Path("camera/{deviceNumber}/startx")
    public AlpacaResponse setStartX(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID,
                                    @FormParam("StartX") int startX) {
        getDevice(deviceNumber, clientID).setStartX(startX);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/starty")
    public IntResponse getStartY(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getStartY());
    }

    @PUT
    @Path("camera/{deviceNumber}/starty")
    public AlpacaResponse setStartY(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID,
                                    @FormParam("StartY") int startY) {
        getDevice(deviceNumber, clientID).setStartY(startY);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("camera/{deviceNumber}/subexposureduration")
    public DoubleResponse getSubExposureDuration(@PathParam("deviceNumber") int deviceNumber,
                                                 @QueryParam("ClientID") int clientID,
                                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSubExposureDuration());
    }

    @PUT
    @Path("camera/{deviceNumber}/subexposureduration")
    public AlpacaResponse setSubExposureDuration(@PathParam("deviceNumber") int deviceNumber,
                                                 @FormParam("ClientID") int clientID,
                                                 @FormParam("ClientTransactionID") long clientTransactionID,
                                                 @FormParam("SubExposureDuration") double subExposureDuration) {
        getDevice(deviceNumber, clientID).setSubExposureDuration(subExposureDuration);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("camera/{deviceNumber}/abortexposure")
    public AlpacaResponse abortExposure(@PathParam("deviceNumber") int deviceNumber,
                                        @FormParam("ClientID") int clientID,
                                        @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).abortExposure();
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("camera/{deviceNumber}/pulseguide")
    public AlpacaResponse pulseGuide(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID,
                                     @FormParam("Direction") int direction,
                                     @FormParam("Duration") int duration) {
        getDevice(deviceNumber, clientID).pulseGuide(direction, duration);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("camera/{deviceNumber}/startexposure")
    public AlpacaResponse startExposure(@PathParam("deviceNumber") int deviceNumber,
                                        @FormParam("ClientID") int clientID,
                                        @FormParam("ClientTransactionID") long clientTransactionID,
                                        @FormParam("Duration") double duration,
                                        @FormParam("Light") boolean light) {
        getDevice(deviceNumber, clientID).startExposure(duration, light);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("camera/{deviceNumber}/stopexposure")
    public AlpacaResponse stopExposure(@PathParam("deviceNumber") int deviceNumber,
                                       @FormParam("ClientID") int clientID,
                                       @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).stopExposure();
        return new AlpacaResponse(clientTransactionID);
    }
}
