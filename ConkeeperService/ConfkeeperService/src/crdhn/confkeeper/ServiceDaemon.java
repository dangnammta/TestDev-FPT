package crdhn.confkeeper;

import firo.Firo;
import firo.utils.config.Config;

/**
 *
 * @author hoaronal
 */
public class ServiceDaemon {
	public static void main(String[] args) throws NoSuchMethodException {
                Firo.getInstance().staticFileLocation("resources");
		Firo.getInstance().init(Config.getParamString("service", "host", "localhost"),Config.getParamInt("service", "port", 8113));
		Firo.getInstance().initializeControllerFromPackage(Config.getParamString("service", "controllerPackage", "crdhn.confkeeper.controller"));
	}
}
