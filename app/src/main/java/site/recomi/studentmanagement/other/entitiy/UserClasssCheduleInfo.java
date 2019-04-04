package site.recomi.studentmanagement.other.entitiy;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name="用户课程列表")
public class UserClasssCheduleInfo {
    @SmartColumn(id =1,name="第五周" , fixed = true)
    private String time;
    @SmartColumn(id =2,name = "星期一",autoMerge=true)
    private String monday;
    @SmartColumn(id =3,name = "星期二",autoMerge=true)
    private String tuesday;
    @SmartColumn(id =4,name = "星期三",autoMerge=true)
    private String wednesday;
    @SmartColumn(id =5,name = "星期四",autoMerge=true)
    private String thursday;
    @SmartColumn(id =6,name = "星期五",autoMerge=true)
    private String friday;
    @SmartColumn(id =7,name = "星期六",autoMerge=true)
    private String saturday;
    @SmartColumn(id =8,name = "星期日",autoMerge=true)
    private String sunday;

    public UserClasssCheduleInfo(String time , String monday , String tuesday ,  String wednesday ,  String thursday , String friday , String saturday , String sunday){
        this.time = time;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.time = time;
    }


}
