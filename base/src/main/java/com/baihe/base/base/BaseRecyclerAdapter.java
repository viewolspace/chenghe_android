package com.baihe.base.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/18 15:47
 * @describe ：
 */

public abstract class BaseRecyclerAdapter<Data>
        extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder<Data>> {
    private final List<Data> mDataList;
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    private int headCount = 0;
    private int footCount = 0;
    private List<Integer> mFooterViews;
    private List<Integer> mHeaderViews;
    private int position;
    private SparseArray<IViewHolderCreator> mHeaderViewHolders;
    private SparseArray<IViewHolderCreator> mFooterViewHolders;

    /**
     * @param headerViewLayout 添加头部
     */
    public <T> void addHeaderView(@LayoutRes int headerViewLayout, final T data, final DataInjector<T> dataInjector) {
        if (mHeaderViews == null) {
            mHeaderViews = new ArrayList<>();
        }
//        if (mHeaderViews.contains(headerViewLayout)) {
//            //同一个view只能添加一次
//            return;
//        }
//        if (headerView.getLayoutParams() == null){
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
//                    headerView.getContext().getResources().getDisplayMetrics().widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);
//            headerView.setLayoutParams(layoutParams);
//        }

        mHeaderViews.add(headerViewLayout);
        headCount = mHeaderViews.size();
        if (mHeaderViewHolders == null) {
            mHeaderViewHolders = new SparseArray<>();
        }
        mHeaderViewHolders.put(headerViewLayout, new IViewHolderCreator<T>() {
            @Override
            public ViewHolder<T> create(final View root) {
                return new ViewHolder<T>(root) {
                    @Override
                    protected void onBind(T t) {
                        dataInjector.onInject(data, root);
                    }
                };
            }
        });
        notifyItemInserted(0);
    }

    /**
     * @param headerViewLayout 添加头部
     */
    public <T> void addHeaderView(@LayoutRes int headerViewLayout) {
        if (mHeaderViews == null) {
            mHeaderViews = new ArrayList<>();
        }
//        if (mHeaderViews.contains(headerViewLayout)) {
//            //同一个view只能添加一次
//            return;
//        }
//        if (headerView.getLayoutParams() == null){
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
//                    headerView.getContext().getResources().getDisplayMetrics().widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT);
//            headerView.setLayoutParams(layoutParams);
//        }
        mHeaderViews.add(headerViewLayout);
        headCount = mHeaderViews.size();
        notifyItemInserted(0);
    }

    /**
     * @param headerViewLayout 如果有多个相同布局的HeaderView ，不要使用这个
     */
    public void removeHeaderView(@LayoutRes int headerViewLayout) {
        if (mHeaderViews != null && mHeaderViews.size() > 0) {
            int index = mHeaderViews.indexOf(headerViewLayout);
            if (index != -1) {
                mHeaderViewHolders.delete(headerViewLayout);
                mHeaderViews.remove(headerViewLayout);
                headCount = mHeaderViews.size();
                notifyItemRemoved(index);
            }
        }
    }

    /**
     * @param footerViewLayout 增加footview
     */
    public <T> void addFooterView(@LayoutRes int footerViewLayout, final T data, final DataInjector<T> dataInjector) {


        if (mFooterViews == null) {
            mFooterViews = new ArrayList<>();
        }
        if (mFooterViews.contains(footerViewLayout)) {
            //同一个view只能添加一次
            return;
        }
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        footerView.setLayoutParams(layoutParams);
        mFooterViews.add(footerViewLayout);
        footCount = mFooterViews.size();
        if (mFooterViewHolders == null) {
            mFooterViewHolders = new SparseArray<>();
        }
        mFooterViewHolders.put(footerViewLayout, new IViewHolderCreator<T>() {
            @Override
            public ViewHolder<T> create(final View root) {
                return new ViewHolder<T>(root) {
                    @Override
                    protected void onBind(T t) {
                        dataInjector.onInject(data, root);
                    }
                };
            }
        });
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * @param footerViewLayout 增加footview
     */
    public void addFooterView(@LayoutRes int footerViewLayout) {
        if (mFooterViews == null) {
            mFooterViews = new ArrayList<>();
        }
        if (mFooterViews.contains(footerViewLayout)) {
            //同一个view只能添加一次
            return;
        }
        mFooterViews.add(footerViewLayout);
        footCount = mFooterViews.size();
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * @param footerViewLayout 如果有多个相同布局的FooterView ，不要使用这个
     */
    public void removeFooterView(@LayoutRes int footerViewLayout) {
        if (mFooterViews != null && mFooterViews.size() > 0) {
            int index = mFooterViews.indexOf(footerViewLayout);
            if (index != -1) {
                mFooterViewHolders.delete(footerViewLayout);
                int oldSize = mFooterViews.size();
                mFooterViews.remove(footerViewLayout);
                footCount = mFooterViews.size();
                int notify = getItemCount() - oldSize + index + 1;//加1 是因为getItemCount() 这时已经减了1
                notifyItemRemoved(notify);
            }

        }

    }

    /**
     * 构造函数模块
     */
    public BaseRecyclerAdapter() {
        this(new ArrayList<Data>());
    }

    public BaseRecyclerAdapter(List<Data> dataList) {
        this.mDataList = dataList;
    }


    /**
     * 复写默认的布局类型返回
     *
     * @param position 坐标
     * @return 类型，其实复写后返回的都是XML文件的ID
     */
    @Override
    public int getItemViewType(int position) {
        this.position = position;
        if (mHeaderViews != null && mHeaderViews.size() > 0 && position >= 0 && position < headCount) {
            return TYPE_HEADER;
        } else if (mFooterViews != null && mFooterViews.size() > 0 && position > getItemCount() - footCount - 1 && position < getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return getItemViewType(position - headCount, mDataList.get(position - headCount));
        }
    }


    /**
     * 得到布局的类型
     *
     * @param position 坐标
     * @param data     当前的数据
     * @return XML文件的ID，用于创建ViewHolder
     */
    @LayoutRes
    protected abstract int getItemViewType(int position, Data data);

    /**
     * 创建一个ViewHolder
     *
     * @param parent   RecyclerView
     * @param viewType 界面的类型,约定为XML布局的Id
     * @return ImageViewHolder
     */
    @Override
    public ViewHolder<Data> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 得到LayoutInflater用于把XML初始化为View
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = null;
        if (viewType == TYPE_HEADER) {
            int layout = mHeaderViews.get(headCount - position - 1);
            root = inflater.inflate(layout, parent, false);
            if (mHeaderViewHolders == null) {
                return new BaseHolder(root);
            } else {
                IViewHolderCreator viewHolderCreator = mHeaderViewHolders.get(layout);
                if (viewHolderCreator != null) {
                    return viewHolderCreator.create(root);
                } else {
                    return new BaseHolder(root);
                }

            }
        } else if (viewType == TYPE_FOOTER) {
            int layout = mFooterViews.get(mFooterViews.size() + position - getItemCount());
            root = inflater.inflate(layout, parent, false);
            if (mFooterViewHolders == null) {
                return new BaseHolder(root);
            } else {
                IViewHolderCreator viewHolderCreator = mFooterViewHolders.get(layout);
                if (viewHolderCreator != null) {
                    return viewHolderCreator.create(root);
                } else {
                    return new BaseHolder(root);
                }

            }
        } else {
            // 把XML id为viewType的文件初始化为一个root View
            root = inflater.inflate(viewType, parent, false);
            // 通过子类必须实现的方法，得到一个ViewHolder
            ViewHolder<Data> holder = onCreateViewHolder(root, parent, viewType);
            // 设置View的Tag为ViewHolder，进行双向绑定
            // 设置事件点击
            // 进行界面注解绑定
            holder.unbinder = ButterKnife.bind(holder, root);
            // 绑定callback
            return holder;
        }
    }

    /**
     * 得到一个新的ViewHolder
     *
     * @param root     根布局
     * @param viewType 布局类型，其实就是XML的ID
     * @return ImageViewHolder
     */
    protected abstract ViewHolder<Data> onCreateViewHolder(View root, ViewGroup parent, int viewType);

    /**
     * 绑定数据到一个Holder上
     *
     * @param holder   ImageViewHolder
     * @param position 坐标
     */
    @Override
    public void onBindViewHolder(ViewHolder<Data> holder, int position) {
        // 得到需要绑定的数据
        if (mHeaderViews != null && mHeaderViews.size() > 0 && position >= 0 && position < headCount) {
            holder.bind(null);
        } else if (mFooterViews != null && mFooterViews.size() > 0 && position >= getItemCount() - footCount && position < getItemCount()) {
            holder.bind(null);
        } else {
            Data data = mDataList.get(position - headCount);
            holder.bind(data);
            // 触发Holder的绑定方法
        }
    }

    /**
     * 得到当前集合的数据量
     */
    @Override
    public int getItemCount() {
        return mDataList.size() + headCount + footCount;
    }

    /**
     * 返回整个集合
     *
     * @return List<Data>
     */
    public List<Data> getItems() {
        return mDataList;
    }

    /**
     * 插入一条数据并通知插入
     *
     * @param data Data
     */
    public void add(Data data) {
        mDataList.add(data);
        notifyItemInserted(getItemCount());
    }


    /**
     * 删除一条数据并通知刷新
     *
     * @param data Data
     */
    public void delete(Data data, int position) {

        mDataList.remove(data);
        notifyItemRemoved(position);
    }


    /**
     * 刷新一条数据并通知刷新
     *
     * @param data Data
     */
    public void update(Data data, int position) {
        mDataList.set(position - headCount, data);
        notifyItemChanged(position);
    }

    /**
     * 刷新一条数据并从最新插入
     *
     * @param data Data
     */
    public void updateFromEnd(Data data, int position) {
        mDataList.remove(position - headCount);
        mDataList.add(data);
        notifyItemChanged(mDataList.size() - 1);
    }

    /**
     * 从开始插入一堆数据，并通知这段集合更新
     *
     * @param dataList Data
     */
    public void addFromStar(List<Data> dataList) {

        if (dataList == null) {
            return;
        }

        if (dataList.size() > 0) {
            //int startPos = mDataList.size();
            mDataList.addAll(0, dataList);
            //notifyDataSetChanged();
            notifyItemRangeInserted(headCount, dataList.size());

        }
    }


    /**
     * 插入一堆数据，并通知这段集合更新
     *
     * @param dataList Data
     */
    public void add(List<Data> dataList) {
        if (dataList == null) {
            return;
        }
        if (dataList.size() > 0) {
            int startPos = getItemCount() + 1;
            mDataList.addAll(dataList);
            notifyItemRangeInserted(startPos, dataList.size());
        }
    }

    private static final String TAG = "BaseRecyclerAdapter";

    /**
     * 删除操作
     */
    public void clear() {

        mDataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 替换为一个新的集合，其中包括了清空
     *
     * @param dataList 一个新的集合
     */
    public void replace(Collection<Data> dataList) {
        mDataList.clear();
        if (dataList == null || dataList.size() == 0)
            return;
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    /**
     * 自定义的ViewHolder
     *
     * @param <Data> 范型类型
     */
    public static abstract class ViewHolder<Data> extends RecyclerView.ViewHolder {
        private Unbinder unbinder;
        protected Data mData;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        /**
         * 用于绑定数据的触发
         *
         * @param data 绑定的数据
         */
        void bind(Data data) {
            this.mData = data;
            onBind(data);
        }

        /**
         * 当触发绑定数据的时候，的回掉；必须复写
         *
         * @param data 绑定的数据
         */
        protected abstract void onBind(Data data);

    }

    public static class RecyclerItem {
        public RecyclerItem() {
        }

        public RecyclerItem(int type, Object data) {
            this.type = type;
            this.data = data;
        }

        public int type;

        public Object data;
    }

    class BaseHolder extends ViewHolder {

        BaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Object o) {

        }
    }

    public interface DataInjector<T> {
        void onInject(T data, View root);
    }

    private interface IViewHolderCreator<T> {
        ViewHolder<T> create(View root);
    }

    public static class BaseData {

    }

}
