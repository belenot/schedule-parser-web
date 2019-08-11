package com.belenot.mirea.schedule.boot;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class TomcatBoot {
    private String docBase = "src/main/webapp";
    private String classBase = "target/classes";
    private String contextPath = "/schedule";
    
    public void boot() throws Exception {
	docBase = new File(docBase).getAbsolutePath();
	classBase = new File(classBase).getAbsolutePath();
	Tomcat tomcat = new Tomcat();
	tomcat.setPort(8082);
	StandardContext context = (StandardContext) tomcat.addWebapp(contextPath, docBase);
	WebResourceRoot resources = new StandardRoot(context);
	resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", classBase, "/"));
	context.setResources(resources);
	tomcat.start();
	tomcat.getServer().await();
    }
    
    public static void main(String[] args) throws Exception {
	TomcatBoot tomcatBoot = new TomcatBoot();
	tomcatBoot.boot();
    }

}
