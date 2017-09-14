package byd.com.byd.popupview.weiget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class ExpandUtils {
    private static List<String> groupArray;
    private static List<List<String>> childArray;
    public ExpandUtils() {
        groupArray=new ArrayList<>();
        childArray =new ArrayList<>();
        initDate();
    }
    private void initDate()
    {
        addInfo("父item0", new String[]{});
        addInfo("父item1", new String[]{"item1_0","item1_1","item1_2","item1_3","item1_4","item1_5"});
        addInfo("父item2", new String[]{"item2_0","item2_1","item2_2"   });
        addInfo("父item3", new String[]{"item3_0","item3_1","item3_2","item3_3"});
        addInfo("父item4", new String[]{});
        addInfo("父item5", new String[]{});
        addInfo("父item6", new String[]{"item6_0","item6_1"});
        addInfo("父item7", new String[]{"item7_0","item7_1","item7_2"});
        addInfo("父item8", new String[]{});
    }
    private void addInfo(String group, String[] child) {
        groupArray.add(group);
        List<String> childItem =new ArrayList<>();
        for(int index=0;index<child.length;index++)
        {
            childItem.add(child[index]);
        }
        childArray.add(childItem);
    }
    public  List<String> getGroupArray()
    {
        return groupArray;
    }
    public  List<List<String>>  getChildArray()
    {
        return childArray;
    }
}
