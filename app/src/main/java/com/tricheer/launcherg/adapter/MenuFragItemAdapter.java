package com.tricheer.launcherg.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.model.MenuFragItem;

/**
 * Menu fragment page adapter
 *
 * @author Jun.Wang
 */
public class MenuFragItemAdapter extends BaseFragItemAdapter<MenuFragItem> {

    public MenuFragItemAdapter(Context context, int resource) {
        super(context, R.layout.frag_common_item);
    }

    @android.support.annotation.NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mResID, null);
            holder = new ViewHolder();
            holder.tvIdx = convertView.findViewById(R.id.tv_idx);
            holder.tvItemName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Fill data
        MenuFragItem item = getItem(position);
        if (item != null) {
            holder.tvIdx.setText(String.valueOf(getItemIdx(position)));
            holder.tvItemName.setText(item.name);
            setItemBg(convertView, (position == getSelectPos()));
        }
        return convertView;
    }
}
