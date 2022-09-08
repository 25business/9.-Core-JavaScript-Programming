package com.example.handler;

import com.google.gson.Gson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.ArrayList;
import java.util.HashMap;


public class GeneralHDDHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        var hdds = hal.getDiskStores();
        HashMap<String, Object> response_data = new HashMap<>();
        ArrayList<HashMap<String, Object>> hdd_data = new ArrayList<>();
        for(var hdd : hdds) {
            var hdd_partitions = hdd.getPartitions();
            for(var partition : hdd_partitions) {
               String partition_name = partition.getMountPoint();
               partition_name = partition_name.split(":")[0];
               double partition_size = partition.getSize() / 1073741824.0;
               HashMap<String, Object> tmp_data = new HashMap<>();
               tmp_data.put("mount_point", partition_name);
               tmp_data.put("partition_size", partition_size);
               hdd_data.add(tmp_data);
            }
        }
        response_data.put("status", 200);
        response_data.put("partitions", hdd_data);
        Gson gson = new Gson();
        context.status(200);
        context.header("Content-Type", "application/json");
        context.result(gson.toJson(response_data));
    }
}
