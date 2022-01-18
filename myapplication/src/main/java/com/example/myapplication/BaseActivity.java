package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author zhang tao
 * @Date 1/16/22 1:08 AM
 * @Desc
 */
public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
    protected T mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mBinding = initBinding();
        mBinding = initBindingByReflection();
        if (mBinding == null) {
            return;
        }
        setContentView(mBinding.getRoot());
    }

    protected abstract T initBinding();

    private T initBindingByReflection() {
        try {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Class actualTypeArgument = (Class) actualTypeArguments[0];

            Method inflate = actualTypeArgument.getDeclaredMethod("inflate", LayoutInflater.class);
            mBinding = (T) inflate.invoke(null, getLayoutInflater());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return mBinding;
    }
}
