package site.recomi.studentmanagement.other;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;
/*
* 成绩活动中表格数据的实体类
* */
@SmartTable(name="用户课程列表")
public class StudentGrade {
    @SmartColumn(id =1,name="课程名：" , fixed = true)
    private String name;
    @SmartColumn(id =2,name = "成绩：",autoMerge=true)
    private int grade;


    public StudentGrade(String name , int grade){
        this.name = name;
        this.grade = grade;
    }


}

