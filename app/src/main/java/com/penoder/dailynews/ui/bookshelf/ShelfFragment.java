package com.penoder.dailynews.ui.bookshelf;

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
import com.penoder.mylibrary.utils.ScreenUtils;
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

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shelf;
    }

    @Override
    public void initView() {
        List<BookShelf> bookShelves = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            BookShelf bookShelf = new BookShelf("", "第 " + (i + 1) + " 本书");
            bookShelves.add(bookShelf);
        }
        getBinding().recyclerShelf.setFocusable(false);
        getBinding().recyclerShelf.setLayoutManager(new GridLayoutManager(mContext, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        shelfAdapter = new CommonRecycleAdapter<BookShelf>(bookShelves, R.layout.item_shelf_recycler) {
            @Override
            public void onConvertView(BookShelf bookShelf, ViewHolder holder, int position) {

                ImageView imgFeed = holder.getView(R.id.img_shelfBookFeed);
                if (!(position + "").equals(imgFeed.getTag(imgFeed.getId()))) {
                    ViewGroup.LayoutParams params = imgFeed.getLayoutParams();
                    imgFeed.post(() -> {
                        int w = imgFeed.getWidth();
                        params.height = 297 * w / 210;
                        imgFeed.setLayoutParams(params);
                    });
                    imgFeed.setTag(imgFeed.getId(), position + "");
                }

                ((TextView) holder.getView(R.id.txt_shelfBookName)).setText(bookShelf.getBookName());

                holder.getItemView().setOnClickListener(v -> {
                    ToastUtil.showShortToast(mContext, "书籍 " + (position + 1));
                });
            }
        };
        getBinding().recyclerShelf.setAdapter(shelfAdapter);
        getBinding().scrollShelf.scrollTo(0, getBinding().recyclerShelf.getTop());
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
