package site.recomi.studentmanagement.other;

/*
* 招聘界面中行数据实体类
* */
public class RecruitmentInformationEntitiy {
    private String jobName;     //岗位名
    private String company;     //公司名
    private String location;       //公司地址
    private String sum;             //招聘人数
    private String date;            //日期
    private String salary;          //薪资
    private String category;     //招聘岗位的类别,以数字的形式代替类别,如2代表互联网相关的岗位

    public RecruitmentInformationEntitiy(String jobName, String company, String location, String sum, String date, String salary, String category) {
        this.jobName = jobName;
        this.company = company;
        this.location = location;
        this.sum = sum;
        this.date = date;
        this.salary = salary;
        this.category = category;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
