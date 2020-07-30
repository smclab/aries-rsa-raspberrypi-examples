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

package it.smc.techblog.apache.aries.rsa.examples.raspberrypi.api;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Antonio Musarra
 */
public interface RaspberryPiService {
    public String getOperatingSystemInfo()
        throws IOException, InterruptedException, ParseException;

    public String getBoardInfo() throws IOException, InterruptedException;

    public String getCPUInfo() throws IOException, InterruptedException;

    public float getCPUTemperature() throws IOException, InterruptedException;

    public String getGPIOStatus(String pinName);

    public String getJavaEnvironmentInfo();

    public String getNetworkInfo() throws IOException, InterruptedException;
}
