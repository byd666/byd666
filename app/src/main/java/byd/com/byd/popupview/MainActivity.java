package byd.com.byd.popupview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import byd.com.byd.popupview.adapter.ExpandableListAdapter;
import byd.com.byd.popupview.myinterface.NotChildGroupListener;
import byd.com.byd.popupview.weiget.ExpandUtils;
import byd.com.byd.popupview.weiget.PopupView;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnGroupExpandListener, ExpandableListView.OnChildClickListener, NotChildGroupListener {
    PopupView mPopupView;
    ExpandableListAdapter eAdapter;
    ExpandableListView mListView;
    @BindView(R.id.rollback)
    TextView rollback;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.menu)
    ImageView menu;

    int contentViewWidth;
    int contentViewHeight;
    int lastItem=-1;//记录上一次打开的父item的位置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        title.setText("自定义popupView");
        menu.setVisibility(View.VISIBLE);
        //设置弹出内容的适配器
        ExpandUtils utils = new ExpandUtils();
        eAdapter = new ExpandableListAdapter(this, utils.getGroupArray(), utils.getChildArray());
    }
    @OnClick({R.id.menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu:
                if (mPopupView == null) {
                    View contentView = LayoutInflater.from(this).inflate(R.layout.popup_view_item, null);
                    mListView = (ExpandableListView) contentView.findViewById(R.id.expand_item_list);
                    mListView.setGroupIndicator(null);
                    mListView.setAdapter(eAdapter);
                    mListView.setOnGroupExpandListener(this);
                    mListView.setOnChildClickListener(this);
                    eAdapter.setNotChildGroupListener(this);
                    mPopupView = new PopupView(this, contentView);
                    contentViewWidth = contentView.getWidth();
                    contentViewHeight = contentView.getHeight();
                }
                if (mPopupView.getFlag()) {
                    mPopupView.dismiss();
                } else {
                    mPopupView.show(menu);
                }
                break;
        }
    }

    @Override
    public void onGroupExpand(int groupPosition) {
        if(lastItem>0)
        {
            /*当有打开的记录位置后直接关闭
            ExpandableListView具体位置的列表，减少循环的次数*/
            mListView.collapseGroup(lastItem);
            lastItem = groupPosition;
        }else{
            int count = mListView.getExpandableListAdapter().getGroupCount();
            for(int j = 0; j < count; j++){
                if(j != groupPosition){
                    mListView.collapseGroup(j);
                }
            }
        }
    }
    //子item的点击事件
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(this, "v.getId()="+v.getId()+" \n groupPosition="+groupPosition +"\n childPosition="+childPosition, Toast.LENGTH_SHORT).show();
        return true;
    }
    //没有子item的父item的点击事件
    @Override
    public void onNotChildGroupClick(int position) {
        Toast.makeText(this, " \n position="+position , Toast.LENGTH_SHORT).show();
    }
}
