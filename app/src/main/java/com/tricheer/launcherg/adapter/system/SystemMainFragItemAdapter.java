package com.tricheer.launcherg.adapter.system;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.adapter.BaseFragItemAdapter;
import com.tricheer.launcherg.model.system.SystemMainFragItem;

/**
 * [Menu-System] List Adapter
 *
 * @author Jun.Wang
 */
public class SystemMainFragItemAdapter extends BaseFragItemAdapter<SystemMainFragItem> {

    public SystemMainFragItemAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, R.layout.frag_common_item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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
        SystemMainFragItem item = getItem(position);
        if (item != null) {
            holder.tvIdx.setText(String.valueOf(getItemIdx(position)));
            holder.tvItemName.setText(item.name);
            setItemBg(convertView, (position == getSelectPos()));
        }
        return convertView;
    }
}
