package com.api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.utilities.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    private static ExtentReports extent;
    // 🌟 FIXED: Changed type from ExtentReports to ExtentTest
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    @Override
    public void onStart(ITestContext context) {
        logger.info("==========================================================================");
        logger.info("🚀 STARTING AUTOMATION SUITE EXECUTION CONTEXT: {}", context.getName());
        logger.info("==========================================================================");
        
        extent = ExtentManager.getInstance();
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started: {}", result.getName());
        
        String methodName = result.getMethod().getMethodName();
        ExtentTest extentTest = extent.createTest(methodName);
        
        // 🌟 FIXED: Store the individual test node, not the global extent instance
        test.set(extentTest); 
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: {}", result.getName());
        
        // 🌟 FIXED: This will now compile perfectly!
        test.get().log(Status.PASS, "Test Step Verified and Passed Successfully!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test Failed: {}", result.getName());
        logger.error("Exception: ", result.getThrowable());
        
        // 🌟 ADDED: Log the failure to your Extent Report as well!
        test.get().log(Status.FAIL, "Test Execution Failed.");
        test.get().fail(result.getThrowable());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        logger.info("==========================================================================");
        logger.info("🏁 SUITE EXECUTION FINISHED. Writing report data loops...");
        logger.info("==========================================================================");
        
        if (extent != null) {
            extent.flush();
        }
        test.remove(); // Clean up thread memory
    }
}