package com.ppjun.android.arms.base.delegate

import android.os.Bundle
import android.support.annotation.NonNull
import com.ppjun.android.arms.di.component.AppComponent
import com.ppjun.android.arms.integration.cache.Cache
import org.simple.eventbus.EventBus

/**
 * Created by ppjun on 3/6/18.
 *
 */
interface IActivity {
    /**
     * 提供在 {@link Activity} 生命周期内的缓存容器, 可向此 {@link Activity} 存取一些必要的数据
     * 此缓存容器和 {@link Activity} 的生命周期绑定, 如果 {@link Activity} 在屏幕旋转或者配置更改的情况下
     * 重新创建, 那此缓存容器中的数据也会被清空, 如果你想避免此种情况请使用 <a href="https://github.com/JessYanCoding/LifecycleModel">LifecycleModel</a>
     *
     * @return like {@link LruCache}
     */

    @NonNull
    fun provideCache(): Cache<String, Object>


    /**
     * 提供 AppComponent (提供所有的单例对象) 给实现类, 进行 Component 依赖
     *
     * @param appComponent
     */
    fun setupActivityComponent(appComponent: AppComponent)

    /**
     * 是否使用 {@link EventBus}
     * @return
     */
    fun useEventBus(): Boolean

    /**
     * 初始化 View, 如果 {@link #initView(Bundle)} 返回 0, 框架则不会调用 {@link Activity#setContentView(int)}
     *
     * @param savedInstanceState
     * @return
     */
    fun initView(savedInstanceState: Bundle):Int

    /**
     * 初始化数据
     */
    fun initData(savedInstanceState:Bundle)

    /**
     * 这个 Activity 是否会使用 Fragment,框架会根据这个属性判断是否注册 {@link FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回{@code false},那意味着这个 Activity 不需要绑定 Fragment,那你再在这个 Activity 中绑定继承于 {@link BaseFragment} 的 Fragment 将不起任何作用
     * @see ActivityLifecycle#registerFragmentCallbacks (Fragment 的注册过程)
     *
     * @return
     */
    fun useFragment():Boolean

}