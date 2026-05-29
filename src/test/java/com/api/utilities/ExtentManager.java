package com.api.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	
	public static synchronized ExtentReports getInstance() {
		
		if (extent == null) {
            File reportDir = new File("reports");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }
	}
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter("reports/extent-reports.html");
		sparkReporter.config().setDocumentTitle("API Automation result");
		sparkReporter.config().setReportName("Booking Service API test report");
		sparkReporter.config().setTheme(Theme.DARK);

		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Framework Type", "Rest-Assured API Engine");
        extent.setSystemInfo("OS", System.getProperty("os.name"));

    	return extent;
	}

}
