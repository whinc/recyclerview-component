package com.whinc.recyclerview.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2015/12/21.
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "LinearItemDecoration";

    @IntDef({RecyclerView.VERTICAL, RecyclerView.HORIZONTAL})
    public @interface Orientation{}

    private final int mOrientation;
    private final int mSpanCount;
    private final int mTopSpace;
    private final int mBottomSpace;
    private final int mLeftSpace;
    private final int mRightSpace;
    private final int mVerticalDividerSpace;
    private final int mHorizontalDividerSpace;
    private final int mColor;

    private LinearItemDecoration(@Orientation int orientation, int spanCount,
                                int topSpace, int bottomSpace, int leftSpace, int rightSpace,
                                int verticalDividerSpace, int horizontalDividerSpace,
                                @ColorInt int color) {
        super();
        mOrientation = orientation;
        mSpanCount = spanCount;
        mTopSpace = topSpace;
        mBottomSpace = bottomSpace;
        mLeftSpace = leftSpace;
        mRightSpace = rightSpace;
        mVerticalDividerSpace = verticalDividerSpace;
        mHorizontalDividerSpace = horizontalDividerSpace;
        mColor = color;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        c.drawColor(mColor);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pos = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();

        int verticalItemSpace = mVerticalDividerSpace / 2;
        int horizontalItemSpace = mHorizontalDividerSpace / 2;
        outRect.set(horizontalItemSpace, verticalItemSpace, horizontalItemSpace, verticalItemSpace);

        if (mOrientation == RecyclerView.VERTICAL) {
            int columnCount = mSpanCount;
            int columnIndex = pos % columnCount;

            if (pos >= 0 && pos < columnCount) {    // top edge
                outRect.top = mTopSpace;
            }

            if (pos >= (itemCount - columnCount) && pos < itemCount) {  // bottom edge
                outRect.bottom = mBottomSpace;
            }

            if (columnIndex == 0) {   // left edge
                outRect.left = mLeftSpace;
            }

            if (columnIndex == columnCount - 1) { // right edge
                outRect.right = mRightSpace;
            }
        } else {
            int rowCount = mSpanCount;
            int rowIndex = pos % rowCount;

            if (rowIndex == 0) {  // top edge
                outRect.top = mTopSpace;
            }

            if (rowIndex == rowCount - 1) { // bottom edge
                outRect.bottom = mBottomSpace;
            }

            if (pos >= 0 && pos < rowCount) {   // left edge
                outRect.left = mLeftSpace;
            }

            if (pos >= (itemCount - rowCount) && pos < itemCount) { // right edge
                outRect.right = mRightSpace;
            }
        }

        Log.i(TAG, "outRect:" + outRect.toString());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int mOrientation = RecyclerView.VERTICAL;
        private int mSpanCount = 1;
        private int mTopSpace = 0;
        private int mBottomSpace = 0;
        private int mLeftSpace = 0;
        private int mRightSpace = 0;
        private int mVerticalDividerSpace = 0;
        private int mHorizontalDividerSpace = 0;
        private int mColor = Color.TRANSPARENT;

        private Builder(){}

        public Builder orientation(@Orientation int orientation) {
            mOrientation = orientation;
            return this;
        }

        public Builder span(@IntRange(from = 1) int spanCount) {
            mSpanCount = spanCount;
            return this;
        }

        public Builder top(@IntRange(from = 0) int space) {
            mTopSpace = space;
            return this;
        }


        public Builder bottom(@IntRange(from = 0) int space) {
            mBottomSpace = space;
            return this;
        }
        public Builder left(@IntRange(from = 0) int space) {
            mLeftSpace = space;
            return this;
        }
        public Builder right(@IntRange(from = 0) int space) {
            mRightSpace = space;
            return this;
        }

        public Builder verticalDividerSpace(@IntRange(from = 0) int space) {
            mVerticalDividerSpace = space;
            return this;
        }

        public Builder horizontalDividerSpace(@IntRange(from = 0) int space) {
            mHorizontalDividerSpace = space;
            return this;
        }

        public Builder color(@ColorInt int color) {
            mColor = color;
            return this;
        }

        public Builder layoutManger(@NonNull RecyclerView.LayoutManager layoutManager) {
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                mOrientation = linearLayoutManager.getOrientation();
                if (layoutManager instanceof GridLayoutManager) {
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                    mSpanCount = gridLayoutManager.getSpanCount();
                }
            } else {
                throw new RuntimeException("LayoutManger must be a instance of LinearLayoutManager"
                        + "or GridLayoutManager");
            }
            return this;
        }

        public LinearItemDecoration create() {
            return new LinearItemDecoration(
                    mOrientation, mSpanCount,
                    mTopSpace, mBottomSpace, mLeftSpace, mRightSpace,
                    mVerticalDividerSpace, mHorizontalDividerSpace,
                    mColor
            );
        }
    }
}
