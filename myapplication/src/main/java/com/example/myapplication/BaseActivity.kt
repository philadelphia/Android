package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * @Author zhang tao
 * @Date 1/16/22 1:08 AM
 * @Desc
 */
abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    protected lateinit var mBinding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG = this.javaClass.simpleName
        Log.d(TAG, "onCreate: ")
//        mBinding = initBinding()
        mBinding = initBindingByReflection()
        if (mBinding == null) {
            return
        }
        setContentView(mBinding.getRoot())
    }

    protected abstract fun initBinding(): T
    private fun initBindingByReflection(): T {
        try {
            val parameterizedType = javaClass.genericSuperclass as ParameterizedType
            val actualTypeArguments = parameterizedType.actualTypeArguments
            val actualTypeArgument = actualTypeArguments[0] as Class<*>
            val inflate = actualTypeArgument.getDeclaredMethod("inflate", LayoutInflater::class.java)
            mBinding = inflate.invoke(null, layoutInflater) as T
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
        return mBinding
    }

    companion object {
        @JvmField
         var TAG: String? = null
    }
}