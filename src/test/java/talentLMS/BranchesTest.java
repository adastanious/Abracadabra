package talentLMS;

import org.testng.annotations.Test;
import talentLMS.enums.AdminSection;
import talentLMS.fileUtils.ConfigReader;

public class BranchesTest extends BaseTest{

    @Test(priority = 1)
    public void addNewBranchTest() {
        driver.get("https://adinai.talentlms.com/index");
        loginPage.doLogin(ConfigReader.getProperty("userName"),ConfigReader.getProperty("password")).selectSection(AdminSection.BRANCHES);
        branchesPage.addNewBranch(branch);
    }

    @Test(priority = 2)
    public void editBranchTest(){
        driver.get("https://adinai.talentlms.com/branch/index");
        branchesPage.editBranch(branch);
    }

    @Test(priority = 3)
    public void cloneBranchTest(){
        driver.get("https://adinai.talentlms.com/branch/index");
        branchesPage.cloneBranch(branch);
    }

    @Test(priority = 4)
    public void deleteBranchTest(){
        driver.get("https://adinai.talentlms.com/branch/index");
        branchesPage.deleteBranch(branch);
    }



}
