package com.calboot.cmdutil.util;

public class MetaData {
    public static final String NAME = "Command-Util";
    public static final String VERSION_ID = MetaData.class.getPackage().getImplementationVersion();
    public static final String VERSION = VERSION_ID == null ? "dev" : "v" + VERSION_ID;
}
