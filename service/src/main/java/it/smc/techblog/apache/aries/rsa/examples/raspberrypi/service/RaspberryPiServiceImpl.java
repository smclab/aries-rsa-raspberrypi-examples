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

package it.smc.techblog.apache.aries.rsa.examples.raspberrypi.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigital;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.system.NetworkInfo;
import com.pi4j.system.SystemInfo;
import it.smc.techblog.apache.aries.rsa.examples.raspberrypi.api.RaspberryPiService;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

/**
 * @author Antonio Musarra
 */
@Component(
	property = {
		"service.exported.interfaces=*",
		"aries.rsa.port=8203"
	})
public class RaspberryPiServiceImpl implements RaspberryPiService {

	@Override
	public String getOperatingSystemInfo()
		throws IOException, InterruptedException, ParseException {
		String operatingSystemInfoResponse =
			"OperatingSystemInfo {OS Name: %s, OS Version: %s, OS Arch.: %s, OS Firmware Build: %s, OS Firmware Date: %s}";

		return String.format(
			operatingSystemInfoResponse, SystemInfo.getOsName(),
			SystemInfo.getOsVersion(), SystemInfo.getOsArch(),
			SystemInfo.getOsFirmwareBuild(), SystemInfo.getOsFirmwareDate());
	}

	@Override
	public String getBoardInfo() throws IOException, InterruptedException {
		String boardInfoResponse = "Board Type {Name: %s}";

		return String.format(
			boardInfoResponse, SystemInfo.getBoardType().name());
	}

	@Override
	public String getCPUInfo() throws IOException, InterruptedException {
		String cpuInfoResponse = "CPUInfo {Processor: %s, Features: %s}";

		return String.format(
			cpuInfoResponse, SystemInfo.getModelName(),
			Arrays.toString(SystemInfo.getCpuFeatures()));
	}

	@Override
	public float getCPUTemperature() throws IOException, InterruptedException {
		return SystemInfo.getCpuTemperature();
	}

	@Override
	public String getGPIOStatus(String pinName) {
		final GpioController gpio = GpioFactory.getInstance();

		GpioPinDigital gpioPinDigital =
			gpio.provisionDigitalInputPin(RaspiPin.getPinByName(pinName));
		PinState pinState = gpio.getState(gpioPinDigital);
		gpio.shutdown();

		return pinState.getName();
	}

	public String getJavaEnvironmentInfo() {
		String javaEnvironmentInfoResponse =
			"Java Environment Info {Java Vendor: %s, Java Vendor URL: %s, Java VM: %s, Java Runtime: %s, Java Version: %s}";

		return String.format(
			javaEnvironmentInfoResponse, SystemInfo.getJavaVendor(),
			SystemInfo.getJavaVendorUrl(), SystemInfo.getJavaVirtualMachine(),
			SystemInfo.getJavaRuntime(), SystemInfo.getJavaVersion());
	}

	public String getNetworkInfo() throws IOException, InterruptedException {
		String networkInfoResponse =
			"Network Info {Hostname: %s, FQDN: %s, IP: %s}";

		return String.format(
			networkInfoResponse, NetworkInfo.getHostname(),
			NetworkInfo.getFQDN(), NetworkInfo.getIPAddress());
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bc = bundleContext;
	}

	private BundleContext _bc;

	@Reference
	private LogService _log;
}
