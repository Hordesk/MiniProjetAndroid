package fr.m2dl.miniprojet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class NewAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> groupItem, tempChild;
    public ArrayList<Object> Childtem = new ArrayList<Object>();
    public LayoutInflater minflater;
    public Activity activity;
    private final Context context;

    public NewAdapter(Context context,ArrayList<String> grList, ArrayList<Object> childItem) {
        this.context = context;
        groupItem = grList;
        this.Childtem = childItem;
    }

    public void setInflater(LayoutInflater mInflater, Activity act) {
        this.minflater = mInflater;
        activity = act;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        tempChild = (ArrayList<String>) Childtem.get(groupPosition);
        TextView text =  null;
        if (convertView == null) {
            convertView = new TextView(context);
        }
        text = (TextView) convertView;
        text.setText(">"+tempChild.get(childPosition));
        text.setTextColor(Color.WHITE);
        text.setTextSize(28);

        convertView.setTag(tempChild.get(childPosition));
        return convertView;
    }



    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) Childtem.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return groupItem.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

            switch (groupPosition){
                case 0:
                    super.onGroupExpanded(groupPosition);
                    break;

                case 1:
                    StaticData.tool = ToolStatus.NONE;
                    StaticData.photoActivity.getImageView().dismissMark();
                    StaticData.xTopLeftSquare = -10;
                    StaticData.yTopLeftSquare = -10;
                    break;

                case 2:
                    StaticData.tool = ToolStatus.NONE;
                    Intent intent = new Intent(StaticData.context, FormActivity.class);
                    StaticData.context.startActivity(intent);
                    break;

                case 3:
                    StaticData.tool = ToolStatus.NONE;
                    StaticData.photoActivity.takePhoto();
                    break;
            }

    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(context);
        }
        ((TextView) convertView).setText(groupItem.get(groupPosition));
        convertView.setTag(groupItem.get(groupPosition));

        TextView text = (TextView)convertView;
        text.setTextColor(Color.WHITE);
        text.setTextSize(32);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
