# AndroidCode
用于存放平时的Android demo

## ImageMajor
一个Bitmap四级缓存处理的demo
```
options.inMutable=true;
options.inBitmap=reusable;
```
设置上面这个属性以后bitmap所使用的内存是可以复用的.
```
Bitmap bitmap=ImageCache.getInstance().getBitmapFromMemory(String.valueOf(position));
if(null==bitmap){
    //如果内存没数据，就去复用池找
    Bitmap reuseable=ImageCache.getInstance().getReuseable(60,60,1);
    //reuseable能复用的内存
    //从磁盘找
    bitmap = ImageCache.getInstance().getBitmapFromDisk(String.valueOf(position),reuseable);
    //如果磁盘中也没缓存,就从网络下载
    if(null==bitmap){
        bitmap=ImageResize.resizeBitmap(context,R.mipmap.wyz_p,80,80,false,reuseable);
        ImageCache.getInstance().putBitmapToMemeory(String.valueOf(position),bitmap);
        ImageCache.getInstance().putBitMapToDisk(String.valueOf(position),bitmap);
        Log.i("jett","从网络加载了数据");
    }else{
        Log.i("jett","从磁盘中加载了数据");
    }

}else{
    Log.i("jett","从内存中加载了数据");
}
```

## ReferenceDemo
介绍弱引用 虚引用的区别
引用默认都会有一个引用队列，虚引用通过不能够get出对象，但是弱引用可以
他们共同的特点就是当发生GC的时候，对象会被丢到引用队列中，可以通过poll方法得到。
所以虚引用因为不能get到对象，多以并没有什么实际作用，唯一的作用用来跟踪GC，只有当发生GC的时候才能被得到。

