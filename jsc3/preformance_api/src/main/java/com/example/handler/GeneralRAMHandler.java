package com.example.handler;

import com.google.gson.Gson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneralRAMHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        SystemInfo si = new SystemInfo();
        HashMap<String, Object> response_data = new HashMap<>();
        HardwareAbstractionLayer hal = si.getHardware();
        var global_memory = hal.getMemory();
        HashMap<String, Object> memory_data = new HashMap<>();
        memory_data.put("used", (global_memory.getTotal() -
                        global_memory.getAvailable()) / 1073741824.0);
        memory_data.put("total", global_memory.getTotal() / 1073741824.0);

        response_data.put("status", 200);
        response_data.put("memory", memory_data);
        Gson gson = new Gson();
        context.status(200);
        context.header("Content-Type", "application/json");
        context.result(gson.toJson(response_data));
    }
}
