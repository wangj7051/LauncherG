package com.tricheer.launcherg.adapter.sound;

import android.content.Context;
import android.media.Ringtone;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.adapter.BaseFragItemAdapter;

/**
 * [Menu-Sound-Ringtones] List Adapter
 *
 * @author Jun.Wang
 */
public class RingTonesFragItemAdapter extends BaseFragItemAdapter<Ringtone> {

    public RingTonesFragItemAdapter(@NonNull Context context, @LayoutRes int resource) {
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
        Ringtone item = getItem(position);
        if (item != null) {
            holder.tvIdx.setText(String.valueOf(position + 1));
            holder.tvItemName.setText(item.getTitle(getContext()));
            setItemBg(convertView, (position == getSelectPos()));
        }
        return convertView;
    }
}
