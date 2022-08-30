package framework.qe.factories.impl;


import static framework.qe.factories.IDriverConnectionFactory.TEST_MODE;

public class BaseFactory {

    enum Runmode {
        local, remote, cloud
    }

    String runMode = null ;
    Runmode runModeEnum;

    BaseFactory() {
        runMode = System.getProperty(TEST_MODE)!=null ? System.getProperty(TEST_MODE) : Runmode.local.toString();
        runModeEnum = Runmode.valueOf(runMode);
    }

}
