package com.radiologics.morpheus.plugin;
import org.nrg.framework.annotations.XnatDataModel;
import org.nrg.framework.annotations.XnatPlugin;
import org.springframework.context.annotation.ComponentScan;

@XnatPlugin(value = "morpheusplugin", name = "Morpheus Plugin", description = "Morpheus Plugin",
    entityPackages = "com.radiologics.morpheus",
    openUrls = { "/xapi/morpheus/resetpassword/email","/xapi/morpheus/resetpassword/request","/morpheus","/morpheus/","/morpheus/**/*"},
    dataModels = {@XnatDataModel(value = "morpheus:qcGenericAssessorData",
            singular = "Morpheus QC",
            plural = "Morpheus QC",
            code = "DXQC")})
@ComponentScan({"com.radiologics.morpheus"})
public class MorpheusPlugin {
	 
}