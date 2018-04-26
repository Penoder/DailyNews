package com.penoder.dailynews.ui.bookshelf;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.penoder.dailynews.R;
import com.penoder.dailynews.adapter.CommonRecycleAdapter;
import com.penoder.dailynews.databinding.FragmentShelfBinding;
import com.penoder.dailynews.entity.BookShelf;
import com.penoder.dailynews.ui.basic.BaseFragment;
import com.penoder.mylibrary.mvvm.command.ReplyCommand;
import com.penoder.mylibrary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 书架
 *
 * @author Penoder
 * @date 18-4-25.
 */
public class ShelfFragment extends BaseFragment {

    private FragmentShelfBinding shelfBinding;

    private Context mContext;

    private CommonRecycleAdapter<BookShelf> shelfAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        shelfBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shelf, container, false);
        shelfBinding.executePendingBindings();
        shelfBinding.setViewModel(this);

        initView();

        return shelfBinding.getRoot();
    }

    private void initView() {
        List<BookShelf> bookShelves = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            BookShelf bookShelf = new BookShelf("", "第 " + (i + 1) + " 本书");
            bookShelves.add(bookShelf);
        }

        shelfBinding.recyclerShelf.setLayoutManager(new GridLayoutManager(mContext, 3));
        shelfAdapter = new CommonRecycleAdapter<BookShelf>(bookShelves, R.layout.item_shelf_recycler) {
            @Override
            public void onConvertView(BookShelf bookShelf, ViewHolder holder, int position) {

                ImageView imgFeed = holder.getView(R.id.img_shelfBookFeed);
                if (!(position + "").equals(imgFeed.getTag(imgFeed.getId()))) {
                    ViewGroup.LayoutParams params = imgFeed.getLayoutParams();
                    int width = params.width;
                    params.height = 210 * width / 148;
                    imgFeed.setLayoutParams(params);
                    imgFeed.setTag(imgFeed.getId(), position + "");
                }


                ((TextView) holder.getView(R.id.txt_shelfBookName)).setText(bookShelf.getBookName());

            }
        };
        shelfBinding.recyclerShelf.setAdapter(shelfAdapter);
    }

    /**
     * 搜索框点击事件
     */
    public ReplyCommand onSearchCommand = new ReplyCommand(() -> {
        ToastUtil.showLongToast(mContext, "666666666");
    });

    /**
     * 阅读历史点击事件
     */
    public ReplyCommand onHistoryCommand = new ReplyCommand(() -> {
        ToastUtil.showLongToast(mContext, "23333333");
    });
}
