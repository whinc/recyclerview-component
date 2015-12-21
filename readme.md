### recyclerview-component

`RecyclerView`组件库，包含下面组件：

* `LinearItemDecoration` 用于设置`RecyclerView`（仅支持`LinearLayoutManager`和`GridLayoutManager`布局管理器）分割线间距和显示。

### Integration 集成


### How to Use 使用

设置垂直间距：

```java
recyclerView.setLayoutManager(new LinearLayoutManager(this));
RecyclerView.ItemDecoration itemDecoration = LinearItemDecoration.builder()
        .verticalDividerSpace(50)
        .create();
recyclerView.addItemDecoration(itemDecoration);
```

设置垂直间距和分割线颜色：

```java
RecyclerView verticalList = ...;
verticalList.setLayoutManager(new LinearLayoutManager(this));
RecyclerView.ItemDecoration itemDecoration = LinearItemDecoration.builder()
        .color(getResources().getColor(android.R.color.holo_blue_light))
        .verticalDividerSpace(50)
        .create();
verticalList.addItemDecoration(itemDecoration);
```

设置水平间距和颜色，以及列表整体的上下左右间距（与设置margin不一样，这个间距是列表大小的一部分）：

```java
RecyclerView horizontalList = ...;
horizontalList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
RecyclerView.ItemDecoration itemDecoration = LinearItemDecoration.builder()
        .color(getResources().getColor(android.R.color.holo_green_light))
        .orientation(RecyclerView.HORIZONTAL)
        .top(20)
        .bottom(30)
        .left(40)
        .right(50)
        .horizontalDividerSpace(20)
        .create();
horizontalList.addItemDecoration(itemDecoration);
```

设置网格列表的水平和垂直间距：

```java
RecyclerView horizontalGrid = ...;
GridLayoutManager horizontalGridLayout = new GridLayoutManager(
        this, 3, RecyclerView.HORIZONTAL, false);
horizontalGrid.setLayoutManager(horizontalGridLayout);
RecyclerView.ItemDecoration itemDecoration = LinearItemDecoration.builder()
        .horizontalDividerSpace(20)
        .verticalDividerSpace(50)
        .span(3)
        .orientation(RecyclerView.HORIZONTAL)
        .create();
horizontalGrid.addItemDecoration(itemDecoration);
```


或者使用下面方式，从`LayoutManager`中提取网格的`span`和`orientation`：

```java
RecyclerView horizontalGrid = ...;
GridLayoutManager horizontalGridLayout = new GridLayoutManager(
        this, 3, RecyclerView.HORIZONTAL, false);
horizontalGrid.setLayoutManager(horizontalGridLayout);
RecyclerView.ItemDecoration itemDecoration = LinearItemDecoration.builder()
        .horizontalDividerSpace(20)
        .verticalDividerSpace(50)
        .layoutManger(horizontalGridLayout)
        .create();
horizontalGrid.addItemDecoration(itemDecoration);
```


### LICENSE

Apache License
Version 2.0, January 2004
http://www.apache.org/licenses/
