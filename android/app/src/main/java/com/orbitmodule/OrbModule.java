// ToastModule.java

package com.orbitmodule;

import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.*;

public class OrbModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    OrbModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    // ReactContextBaseJavaModule requires that a method called getName is implemented.
    // The purpose of this method is to return the string name of the NativeModule which represents this class in JavaScript.
    // So here we will call this OrbitSDK so that we can access it through React.NativeModules.OrbitSDK in JavaScript.
    @Override
    public String getName() {
        return "OrbModuleSDK";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    @ReactMethod
    public String getMessage(String message, int duration) {
        return message;
    }

    @ReactMethod
    public void testCallback(
            Callback successCallback,
            Callback errorCallback ) {
        try {
            successCallback.invoke("test Callback Success");
        } catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void testPromise(Promise promise) {
        try {
            WritableMap map = Arguments.createMap();
            map.putBoolean("label", true);
            promise.resolve(map);
        } catch (IllegalViewOperationException e) {
            promise.reject("Error", e.getMessage());
        }
    }
}