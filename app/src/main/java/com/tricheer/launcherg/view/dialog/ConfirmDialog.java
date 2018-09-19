package com.tricheer.launcherg.view.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tricheer.launcherg.R;
import com.tricheer.launcherg.engine.KeypadManager;
import com.tricheer.launcherg.engine.SysFontManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Confirm Dialog
 *
 * @author Jun.Wang
 */
public class ConfirmDialog extends BaseDialog {

    //Variables
    private Context mContext;
    private OpItemsAdapter mOpItemsAdapter;
    private List<ItemModel> mListItems = new ArrayList<ItemModel>();

    //Widgets
    private ListView lvItems;
    private View layoutOps;
    private ImageView ivArrow1, ivArrow2;

    //
    private int mLvWidth = 0;

    /**
     * {@link ConfirmDelegate} Object
     */
    private ConfirmDelegate mConfirmDelegate;

    public interface ConfirmDelegate {
        /**
         * ConfirmDelegate Callback
         *
         * @param flag <p>-1 YES</p>
         *             or <p>-2 NO</p>
         *             or <p>0,1,2... means item position </p>
         */
        public void onCallback(int flag);
    }

    public ConfirmDialog(Context context) {
        super(context, SysFontManager.getFontStyleID2());
        init(context);
    }

    private void init(Context context) {
        //Variables
        mContext = context;
    }

    public void setOpItems(String... sItems) {
        mListItems.clear();
        for (String str : sItems) {
            ItemModel model = new ItemModel();
            model.name = str;
            mListItems.add(model);
        }
    }

    public void setConfirmCallback(ConfirmDelegate delegate) {
        mConfirmDelegate = delegate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_confirm_dialog);
        init();
    }

    private void init() {
        //Widgets
        lvItems = findViewById(R.id.lv_items);
        layoutOps = findViewById(R.id.layout_ops);
        ivArrow1 = findViewById(R.id.iv_arrow1);
        ivArrow1.setVisibility(View.VISIBLE);
        ivArrow2 = findViewById(R.id.iv_arrow2);
        ivArrow2.setVisibility(View.INVISIBLE);

        //Initialize
        loadData();
    }

    private void loadData() {
        //Set Dialog
        int loop = mListItems.size();
        if (loop == 1) {
            layoutOps.setVisibility(View.VISIBLE);
        } else if (loop > 1) {
            layoutOps.setVisibility(View.GONE);
        }

        //Load ListView
        mOpItemsAdapter = new OpItemsAdapter(mContext, 0);
        mOpItemsAdapter.setListItems(mListItems);
        lvItems.setAdapter(mOpItemsAdapter);
    }

    @Override
    public void onKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeypadManager.LEFT:
                if (layoutOps.getVisibility() == View.VISIBLE) {
                    ivArrow1.setVisibility(View.VISIBLE);
                    ivArrow2.setVisibility(View.INVISIBLE);
                }
                break;
            case KeypadManager.RIGHT:
                if (layoutOps.getVisibility() == View.VISIBLE) {
                    ivArrow1.setVisibility(View.INVISIBLE);
                    ivArrow2.setVisibility(View.VISIBLE);
                }
                break;
            case KeypadManager.UP:
                if (layoutOps.getVisibility() == View.GONE) {
                    mOpItemsAdapter.selectPrev();
                }
                break;
            case KeypadManager.DOWN:
                if (layoutOps.getVisibility() == View.GONE) {
                    mOpItemsAdapter.selectNext();
                }
                break;
            case KeypadManager.FUNC:
                if (mConfirmDelegate != null) {
                    if (layoutOps.getVisibility() == View.VISIBLE) {
                        if (ivArrow1.getVisibility() == View.VISIBLE) {
                            mConfirmDelegate.onCallback(-1);
                        } else if (ivArrow2.getVisibility() == View.VISIBLE) {
                            mConfirmDelegate.onCallback(-2);
                        }
                    } else if (layoutOps.getVisibility() == View.GONE) {
                        mConfirmDelegate.onCallback(mOpItemsAdapter.getSelectPos());
                    }
                    dismiss();
                }
                break;
        }
    }

    private class ItemModel implements Serializable {
        String name = "";
    }

    private class OpItemsAdapter extends ArrayAdapter<ItemModel> {
        //Variables
        private Context mmContext;
        private LayoutInflater mmLayoutInflater;

        private int mmFontStyleResID = SysFontManager.getFontStyleID2();
        private List<ItemModel> mmListItems;
        private int mmSelectPos;
        private boolean mmIsShowArrow = false;

        OpItemsAdapter(@NonNull Context context, int resource) {
            super(context, resource);
            mmContext = context;
            mmLayoutInflater = LayoutInflater.from(mmContext);
        }

        public void setListItems(List<ItemModel> listItems) {
            mmListItems = listItems;
            mmIsShowArrow = (getCount() > 1);
        }

        public void setSelectPos(int pos) {
            mmSelectPos = pos;
        }

        public int getSelectPos() {
            return mmSelectPos;
        }

        public void selectNext() {
            mmSelectPos++;
            int loop = getCount();
            if (mmSelectPos <= loop - 1) {
                notifyDataSetChanged();
            } else {
                mmSelectPos = loop - 1;
            }
        }

        public void selectPrev() {
            mmSelectPos--;
            if (mmSelectPos >= 0) {
                notifyDataSetChanged();
            } else {
                mmSelectPos = 0;
            }
        }

        @Override
        public int getCount() {
            if (mmListItems == null) {
                return 0;
            }
            return mmListItems.size();
        }

        @Nullable
        @Override
        public ItemModel getItem(int position) {
            if (mmListItems == null || mmListItems.size() == 0) {
                return null;
            }
            return mmListItems.get(position);
        }

        @SuppressLint("InflateParams")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mmLayoutInflater.inflate(R.layout.v_confirm_dialog_item, null);
                holder = new ViewHolder();
                holder.ivArrow = convertView.findViewById(R.id.iv_arrow);
                holder.tvItem = convertView.findViewById(R.id.tv_item);
                holder.tvItem.setTextAppearance(mmContext, mmFontStyleResID);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //Fill
            ItemModel item = getItem(position);
            if (item != null) {
                holder.tvItem.setText(item.name);
                if (mmIsShowArrow) {
                    holder.ivArrow.setVisibility((position == mmSelectPos) ? View.VISIBLE : View.INVISIBLE);
                } else {
                    holder.ivArrow.setVisibility(View.GONE);
                }

                //Adjust ListView Width
                adjustLvWidth(holder.tvItem.getMeasuredWidth());
            }
            return convertView;
        }

        private void adjustLvWidth(int itemPxW) {
            if (mLvWidth < itemPxW) {
                mLvWidth = itemPxW * 4 / 3;
                Log.i("ConfirmDialog", mLvWidth + "--" + itemPxW + "--" + lvItems.getWidth());

                //Set layout
                ViewGroup.LayoutParams params = lvItems.getLayoutParams();
                params.width = mLvWidth;
                lvItems.setLayoutParams(params);
            }
        }

        private final class ViewHolder {
            ImageView ivArrow;
            TextView tvItem;
        }
    }
}
