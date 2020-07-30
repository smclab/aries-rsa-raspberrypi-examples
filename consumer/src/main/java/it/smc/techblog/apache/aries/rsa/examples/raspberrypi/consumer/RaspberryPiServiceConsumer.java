/**
 * Copyright (c) 2020 SMC Treviso Srl. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package it.smc.techblog.apache.aries.rsa.examples.raspberrypi.consumer;

import it.smc.techblog.apache.aries.rsa.examples.raspberrypi.api.RaspberryPiService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Antonio Musarra
 */
@Component(immediate=true)
public class RaspberryPiServiceConsumer {

    RaspberryPiService _raspberryPiService;

    @Activate
    public void activate() {
        System.out.println("Sending to Raspberry Pi Service");
        try {
            System.out.println(_raspberryPiService.getCPUTemperature());
            System.out.println(_raspberryPiService.getOperatingSystemInfo());
            System.out.println(_raspberryPiService.getBoardInfo());
            System.out.println(_raspberryPiService.getCPUInfo());
            System.out.println(_raspberryPiService.getJavaEnvironmentInfo());
            System.out.println(_raspberryPiService.getNetworkInfo());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Reference
    public void setRaspberryPiService(RaspberryPiService raspberryPiService) {
        this._raspberryPiService = raspberryPiService;
    }
}
