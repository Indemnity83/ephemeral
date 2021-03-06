package com.indemnity83.ephemeral;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.indemnity83.ephemeral.event.EventHandler;
import com.indemnity83.ephemeral.helper.Version;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = "Ephemeral", name = "Ephemeral Servers", guiFactory = "com.indemnity83.ephemeral.config.GuiFactory")
public class Ephemeral {
    public static Configuration configFile;

    public static AmazonEC2 ec2;

    private static String accessKey = "aws-key";
    private static String secretKey = "aws-secret";
    private static String ec2Endpoint = "ec2.us-west-1.amazonaws.com";
    private static String instanceImage = "ami-c481fad3";
    private static String instanceType = "r3.large";

    public static String getDefaultInstanceImage() {
        return instanceImage;
    }

    public static String getDefaultInstanceType() {
        return instanceType;
    }

    public static String getDefaultBidPrice() {
        return bidPrice;
    }

    private static String bidPrice = "0.03";

    @Instance("Ephemeral")
    private static Ephemeral instance;

    private static void syncConfig() {
        // TODO: Improve security for key storage (not in config file)
        accessKey = configFile.getString("accessKey", Configuration.CATEGORY_GENERAL, accessKey, "The AWS access key");
        secretKey = configFile.getString("secretKey", Configuration.CATEGORY_GENERAL, secretKey, "The AWS secret access key");
        ec2Endpoint = configFile.getString("EC2Endpoint", Configuration.CATEGORY_GENERAL, ec2Endpoint, "The AWS endpoint (region) to connect to");
        instanceImage = configFile.getString("InstanceImage", Configuration.CATEGORY_GENERAL, instanceImage, "Instance image to use on launch");
        instanceType = configFile.getString("InstanceType", Configuration.CATEGORY_GENERAL, instanceType, "Instance type to launch");
        bidPrice = configFile.getString("bidPrice", Configuration.CATEGORY_GENERAL, bidPrice, "The maximum price you're willing to pay for the instance");

        if (configFile.hasChanged())
            configFile.save();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandler());

        Version.init(event.getVersionProperties());
        event.getModMetadata().version = Version.fullVersionString();

        configFile = new Configuration(event.getSuggestedConfigurationFile());

        syncConfig();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(instance);

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        ec2 = new AmazonEC2Client(credentials);
        ec2.setEndpoint(ec2Endpoint);
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.modID.equals("Ephemeral"))
            syncConfig();
    }

}
