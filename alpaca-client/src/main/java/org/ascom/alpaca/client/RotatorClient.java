package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.Rotator;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.model.ClientException;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class RotatorClient extends CommonClient {
    private static final Logger log = Logger.getLogger(RotatorClient.class);
    private final URI serverAddress;
    private Rotator client = null;

    public RotatorClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public RotatorClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private Rotator getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(Rotator.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Rotator - " + e.getMessage());
            }
        }
        return client;
    }

    /**
     * True if the Rotator supports the Reverse method.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.CanReverse">A full description of this member's behavior is provided here</a>
     */
    public boolean canReverse() {
        BooleanResponse response = call(getClient().canReverse(getDeviceID(), getClientID(), getTransactionID()), "canReverse");
        return response.getValue();
    }

    /**
     * True if the Rotator supports the Reverse method.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.CanReverse">A full description of this member's behavior is provided here</a>
     */
    public void canReverse(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canReverse(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canReverse");
    }

    /**
     * Returns True if the rotator is currently moving to a new position.
     *
     * @return True if the rotator is currently moving to a new position
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.IsMoving">A full description of this member's behavior is provided here</a>
     */
    public boolean isMoving() {
        BooleanResponse response = call(getClient().isMoving(getDeviceID(), getClientID(), getTransactionID()), "isMoving");
        return response.getValue();
    }

    /**
     * Returns True if the rotator is currently moving to a new position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.IsMoving">A full description of this member's behavior is provided here</a>
     */
    public void isMoving(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isMoving(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isMoving");
    }

    /**
     * Returns the rotator's mechanical current position (degrees).
     *
     * @return The rotator's mechanical current position (degrees)
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.MechanicalPosition">A full description of this member's behavior is provided here</a>
     */
    public double getMechanicalPosition() {
        DoubleResponse response = call(getClient().getMechanicalPosition(getDeviceID(), getClientID(), getTransactionID()), "getMechanicalPosition");
        return response.getValue();
    }

    /**
     * Returns the rotator's mechanical current position (degrees).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.MechanicalPosition">A full description of this member's behavior is provided here</a>
     */
    public void getMechanicalPosition(AlpacaCallback<Double> callback) {
        callAsync(getClient().getMechanicalPosition(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getMechanicalPosition");
    }

    /**
     * Returns the rotator's current position (degrees).
     *
     * @return The rotator's current position (degrees)
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Position">A full description of this member's behavior is provided here</a>
     */
    public double getPosition() {
        DoubleResponse response = call(getClient().getPosition(getDeviceID(), getClientID(), getTransactionID()), "getPosition");
        return response.getValue();
    }

    /**
     * Returns the rotator's current position (degrees).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Position">A full description of this member's behavior is provided here</a>
     */
    public void getPosition(AlpacaCallback<Double> callback) {
        callAsync(getClient().getPosition(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getPosition");
    }

    /**
     * Returns the rotator’s Reverse state.
     *
     * @return True if the rotator is reversed
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Reverse">A full description of this member's behavior is provided here</a>
     */
    public boolean isReversed() {
        BooleanResponse response = call(getClient().isReversed(getDeviceID(), getClientID(), getTransactionID()), "isReversed");
        return response.getValue();
    }

    /**
     * Returns the rotator’s Reverse state.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Reverse">A full description of this member's behavior is provided here</a>
     */
    public void isReversed(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isReversed(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isReversed");
    }

    /**
     * Sets the rotator’s Reverse state.
     *
     * @param reverse True if the rotation and angular direction must be reversed to match the optical characteristcs
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Reverse">A full description of this member's behavior is provided here</a>
     */
    public void setReversed(boolean reverse) {
        AlpacaResponse response = call(getClient().setReversed(getDeviceID(), getClientID(), getTransactionID(), reverse), "setReversed", reverse);
    }

    /**
     * Sets the rotator’s Reverse state.
     *
     * @param reverse True if the rotation and angular direction must be reversed to match the optical characteristcs
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Reverse">A full description of this member's behavior is provided here</a>
     */
    public void setReversed(boolean reverse, AlpacaCallback<Void> callback) {
        callAsync(getClient().setReversed(getDeviceID(), getClientID(), getTransactionID(), reverse), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setReversed", reverse);
    }

    /**
     * Returns the minimum StepSize (degrees).
     *
     * @return The minimum StepSize (degrees)
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.StepSize">A full description of this member's behavior is provided here</a>
     */
    public double getStepSize() {
        DoubleResponse response = call(getClient().getStepSize(getDeviceID(), getClientID(), getTransactionID()), "getStepSize");
        return response.getValue();
    }

    /**
     * Returns the minimum StepSize (degrees).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.StepSize">A full description of this member's behavior is provided here</a>
     */
    public void getStepSize(AlpacaCallback<Double> callback) {
        callAsync(getClient().getStepSize(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getStepSize");
    }

    /**
     * Returns the destination position angle (degrees).
     *
     * @return The destination position angle (degrees)
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.TargetPosition">A full description of this member's behavior is provided here</a>
     */
    public double getTargetPosition() {
        DoubleResponse response = call(getClient().getTargetPosition(getDeviceID(), getClientID(), getTransactionID()), "getTargetPosition");
        return response.getValue();
    }

    /**
     * Returns the destination position angle (degrees).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.TargetPosition">A full description of this member's behavior is provided here</a>
     */
    public void getTargetPosition(AlpacaCallback<Double> callback) {
        callAsync(getClient().getTargetPosition(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getTargetPosition");
    }

    /**
     * Immediatley stops rotator motion.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Halt">A full description of this member's behavior is provided here</a>
     */
    public void halt() {
        AlpacaResponse response = call(getClient().halt(getDeviceID(), getClientID(), getTransactionID()), "halt");
    }

    /**
     * Immediatley stops rotator motion.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Halt">A full description of this member's behavior is provided here</a>
     */
    public void halt(AlpacaCallback<Void> callback) {
        callAsync(getClient().halt(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "halt");
    }

    /**
     * Moves the rotator to a new position relative to the current position.
     *
     * @param position Relative position to move in degrees from current Position.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Move">A full description of this member's behavior is provided here</a>
     */
    public void move(double position) {
        AlpacaResponse response = call(getClient().move(getDeviceID(), getClientID(), getTransactionID(), position), "move", position);
    }

    /**
     * Moves the rotator to a new position relative to the current position.
     *
     * @param position Relative position to move in degrees from current Position.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Move">A full description of this member's behavior is provided here</a>
     */
    public void move(double position, AlpacaCallback<Void> callback) {
        callAsync(getClient().move(getDeviceID(), getClientID(), getTransactionID(), position), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "move", position);
    }

    /**
     * Moves the rotator to a new absolute position (degrees).
     *
     * @param position Absolute position in degrees.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.MoveAbsolute">A full description of this member's behavior is provided here</a>
     */
    public void moveAbsolute(double position) {
        AlpacaResponse response = call(getClient().moveAbsolute(getDeviceID(), getClientID(), getTransactionID(), position), "moveAbsolute", position);
    }

    /**
     * Moves the rotator to a new absolute position (degrees).
     *
     * @param position Absolute position in degrees.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.MoveAbsolute">A full description of this member's behavior is provided here</a>
     */
    public void moveAbsolute(double position, AlpacaCallback<Void> callback) {
        callAsync(getClient().moveAbsolute(getDeviceID(), getClientID(), getTransactionID(), position), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "moveAbsolute", position);
    }

    /**
     * Moves the rotator to a new raw mechanical position (degrees).
     *
     * @param position Absolute position in degrees.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.MoveMechanical">A full description of this member's behavior is provided here</a>
     */
    public void moveMechanical(double position) {
        AlpacaResponse response = call(getClient().moveMechanical(getDeviceID(), getClientID(), getTransactionID(), position), "moveMechanical", position);
    }

    /**
     * Moves the rotator to a new raw mechanical position (degrees).
     *
     * @param position Absolute position in degrees.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.MoveMechanical">A full description of this member's behavior is provided here</a>
     */
    public void moveMechanical(double position, AlpacaCallback<Void> callback) {
        callAsync(getClient().moveMechanical(getDeviceID(), getClientID(), getTransactionID(), position), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "moveMechanical", position);
    }

    /**
     * Syncs the rotator to the specified position angle without moving it.
     *
     * @param position Absolute position in degrees.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Sync">A full description of this member's behavior is provided here</a>
     */
    public void sync(double position) {
        AlpacaResponse response = call(getClient().sync(getDeviceID(), getClientID(), getTransactionID(), position), "sync", position);
    }

    /**
     * Syncs the rotator to the specified position angle without moving it.
     *
     * @param position Absolute position in degrees.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/rotator.html#Rotator.Sync">A full description of this member's behavior is provided here</a>
     */
    public void sync(double position, AlpacaCallback<Void> callback) {
        callAsync(getClient().sync(getDeviceID(), getClientID(), getTransactionID(), position), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "sync", position);
    }
}