package site.recomi.studentmanagement.other;

public class CampusAssociationItem {
    private String name;            //社团名
    private Boolean isSelect = false;      //是否选中,默认为不选中

    CampusAssociationItem(String name , Boolean isSelect){
        this.name = name;
        this.isSelect = isSelect;
    }
    public CampusAssociationItem(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }
}
