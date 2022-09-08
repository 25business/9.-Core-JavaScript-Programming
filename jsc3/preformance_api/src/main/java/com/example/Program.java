package com.example;

import com.example.handler.GeneralHDDHandler;
import com.example.handler.GeneralRAMHandler;
import io.javalin.Javalin;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class Program {
    public static void main(String[] args) {
       Javalin server = Javalin.create(config -> {
           config.enableCorsForAllOrigins();
       });

       server.get("/api/v1/hdd", new GeneralHDDHandler());
       server.get("/api/v1/ram", new GeneralRAMHandler());

       server.start(9000);

       /*
        File cDrive = new File("C:");
        System.out.println(String.format("Total space: %.2f GB",
                (double)cDrive.getTotalSpace() /1073741824));
        System.out.println(String.format("Free space: %.2f GB",
                (double)cDrive.getFreeSpace() /1073741824));
        System.out.println(String.format("Usable space: %.2f GB",
                (double)cDrive.getUsableSpace() /1073741824));

        File[] paths;
        FileSystemView fsv = FileSystemView.getFileSystemView();

// returns pathnames for files and directory
        paths = File.listRoots();

// for each pathname in pathname array
        for(File path:paths)
        {
            // prints file and directory paths
            System.out.println("Drive Name: "+path);
            System.out.println("Description: "+fsv.getSystemTypeDescription(path));
        }

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        var RAM_memory = hal.getMemory();
        System.out.println(RAM_memory.getTotal() / 1073741824.0);

        */
        /*
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        var hdds = hal.getDiskStores();
        for(var hdd : hdds) {
            var hdd_partitions = hdd.getPartitions();
            for(var partition : hdd_partitions) {
                System.out.println(partition.getMountPoint());
                System.out.println(partition.getSize() / 1073741824.0);
            }
        }*/



    }
}
