import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class listener extends utility implements ITestListener {
    public void onTestStart(ITestResult ıTestResult) {
        System.out.println("Test başlıyor...");
    }

    public void onTestSuccess(ITestResult ıTestResult) {
        System.out.println("Success");
    }

    public void onTestFailure(ITestResult ıTestResult) {
        System.out.println("Fail...");
    }

    public void onTestSkipped(ITestResult ıTestResult) {
        System.out.println("Fail...");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult ıTestResult) {

    }

    public void onStart(ITestContext ıTestContext) {

    }

    public void onFinish(ITestContext ıTestContext) {
        System.out.println("Test sonu...");
    }
}
