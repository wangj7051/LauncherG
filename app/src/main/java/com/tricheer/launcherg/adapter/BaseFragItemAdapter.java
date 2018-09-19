package com.tricheer.launcherg.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tricheer.launcherg.R;

import java.util.List;

/**
 * Base fragment adapter for common model
 *
 * @param <T> : the Model that u want to fill in.
 */
public class BaseFragItemAdapter<T> extends ArrayAdapter<T> {

    private Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected int mResID;
    private List<T> mListItems;
    private int mSelectPos;

    /**
     * The selected/common background color of select item
     */
    private int mSelectColor, mComColor;

    public BaseFragItemAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        mContext = context;
        mSelectColor = mContext.getResources().getColor(R.color.white_50percent);
        mComColor = mContext.getResources().getColor(
                android.R.color.transparent);

        mLayoutInflater = LayoutInflater.from(context);
        mResID = resource;
    }

    public void setListItems(List<T> listItems) {
        mListItems = listItems;
    }

    public void setSelectPos(int selectPos) {
        mSelectPos = selectPos;
    }

    public int getSelectPos() {
        return mSelectPos;
    }

    public void selectNext() {
        mSelectPos++;
        int loop = getCount();
        if (mSelectPos > loop - 1) {
            mSelectPos = 0;
        }
        refresh(mSelectPos);
    }

    public void selectPrev() {
        mSelectPos--;
        if (mSelectPos < 0) {
            mSelectPos = getCount() - 1;
        }
        refresh(mSelectPos);
    }

    public void selectPos(int pos) {
        int loop = getCount();
        //If select invalid position
        if (pos > loop - 1 || pos < 0) {
            return;
        }

        //Select new position
        mSelectPos = pos;
        refresh(mSelectPos);
    }

    public void refresh(List<T> listItems) {
        setListItems(listItems);
        notifyDataSetChanged();
    }

    public void refresh(int selectPos) {
        setSelectPos(selectPos);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mListItems == null) {
            return 0;
        }
        return mListItems.size();
    }

    @Override
    public T getItem(int position) {
        return mListItems.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    /**
     * Get index according to keypad number.
     *
     * @param pos : Item original index
     * @return : Index matching keypad number[0~9].
     */
    protected int getItemIdx(int pos) {
        ++pos;
        if (pos >= 10) {
            pos = 0;
        }
        return pos;
    }

    protected void setItemBg(View itemV, boolean isSelected) {
        if (isSelected) {
            itemV.setBackgroundColor(mSelectColor);
        } else {
            itemV.setBackgroundColor(mComColor);
        }
    }

    /**
     * View Holder
     */
    public static class ViewHolder {
        public TextView tvIdx, tvItemName;
    }
}
