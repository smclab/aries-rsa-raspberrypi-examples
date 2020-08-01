/**
 * Copyright (c) 2020 SMC Treviso Srl. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package it.smc.techblog.apache.aries.rsa.examples.raspberrypi.consumer;

import it.smc.techblog.apache.aries.rsa.examples.raspberrypi.api.RaspberryPiService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Antonio Musarra
 */
@Component(immediate = true,
	property = {
		"osgi.command.function=operatingSystemInfo",
        "osgi.command.function=javaEnvironmentInfo",
        "osgi.command.function=cpuInfo",
        "osgi.command.function=cpuTemperature",
        "osgi.command.function=networkInfo",
		"osgi.command.scope=rpi"
	},
	service = Object.class
)
public class RaspberryPiServiceConsumerCommand {

	public String cpuInfo() throws IOException, InterruptedException {
		return _raspberryPiService.getCPUInfo();
	}

    public float cpuTemperature() throws IOException, InterruptedException {
		return _raspberryPiService.getCPUTemperature();
	}

    public String networkInfo() throws IOException, InterruptedException {
        return _raspberryPiService.getNetworkInfo();
    }

	public String operatingSystemInfo()
		throws InterruptedException, ParseException, IOException {
		return _raspberryPiService.getOperatingSystemInfo();
	}

	public String javaEnvironmentInfo() {
		return _raspberryPiService.getJavaEnvironmentInfo();
	}

    @Reference
    private RaspberryPiService _raspberryPiService;
}
