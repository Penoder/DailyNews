package com.penoder.dailynews.ui.bookshelf;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
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
public class ShelfFragment extends BaseFragment<FragmentShelfBinding> {

    private CommonRecycleAdapter<BookShelf> shelfAdapter;
    private final List<BookShelf> bookShelves = new ArrayList<>();

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shelf;
    }

    @SuppressLint("InflateParams")
    @Override
    public void initView() {
        getBinding().recyclerShelf.setFocusable(true);
        shelfAdapter = new CommonRecycleAdapter<BookShelf>(bookShelves, R.layout.item_shelf_recycler) {
            @Override
            public void onConvertView(BookShelf bookShelf, ViewHolder holder, int position) {
                ImageView imgFeed = holder.getView(R.id.img_shelfBookFeed);
                TextView txtName = holder.getView(R.id.txt_shelfBookName);
                String tag = position + bookShelf.getBookName();
                if (!tag.equals(imgFeed.getTag(position + imgFeed.getId()))) {
                    imgFeed.post(() -> {
                        int w = imgFeed.getWidth();
                        ViewGroup.LayoutParams params = imgFeed.getLayoutParams();
                        params.height = 99 * w / 70;   // A4 纸尺寸 297:210
                        imgFeed.setLayoutParams(params);
                        imgFeed.setTag(position + imgFeed.getId(), tag);
                    });
                }
                txtName.setText(bookShelf.getBookName());
                holder.getItemView().setOnClickListener(v -> ToastUtil.showShortToast(mContext, "书籍 " + (position + 1)));
            }
        };
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        getBinding().recyclerShelf.setLayoutManager(gridLayoutManager);
        getBinding().recyclerShelf.setAdapter(shelfAdapter);
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i < 30; i++) {
            BookShelf bookShelf = new BookShelf("", "第 " + (i + 1) + " 本书");
            bookShelves.add(bookShelf);
        }
        shelfAdapter.notifyDataSetChanged();
        getBinding().coordinatorShelf.onNestedScroll(getBinding().recyclerShelf, 0, 0, 0, 0);
    }

    public ReplyCommand onSearchCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "搜索按钮点击事件");
    });

    public ReplyCommand onRecordCommand = new ReplyCommand(() -> {
        ToastUtil.showShortToast(mContext, "阅读记录点击事件");
    });
}
