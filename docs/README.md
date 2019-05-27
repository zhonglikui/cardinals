# cardinals
cardinals是一个Android快速开发框架,将某些常用功能和第三方库进行封装可以帮你进行快速
### 功能特性
* 提供MvpActivity、MvpFragment、MvpPresenter、MvpView等可以让你可以基于mvp快速开发
* 提供BaseListAdapter、BaseRecycleAdapter等快速开发
* 提供手机的图片、音乐、视频查询功能
* 利用GPU进行加速的图片模糊功能
* 对Fresco和Retrofit的封装
* 封装几种常见的加密方式
* 封装常用的线程池
* 重用的工具类，如：动画、资源关闭、设备参数、log、像素单位转换、sd卡访问、时间日期格式化、Toast等工具类
* 常用UI控件，如：上拉加载的ListView、不可滑动的ViewPager、和圆形进度条
* 自动获取短信验证码
* 集成了Eventbus
### 初始化
在`Application`的`onCreate( )`中添加如下代码
```
                Builder builder = new Builder();
                builder.setDefaultLogTag("zhong")
                        .setNoProxy(false)
                        .setShowLog(true)
                        .setHost(HOST)
                        .setDebug(true)
                        .setNetInterface(new NetInterface() {
                            @Override
                            public void onResult(int code) {
                                //收到系统自定义的http状态码
                               }
                     });

                Cardinals.init(builder, this);
```
