package com.example.rxandroid;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rxandroid.R;
import com.example.rxandroid.bean.Course;
import com.example.rxandroid.bean.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Author:   Tao.ZT.Zhang
 * Date:     2017/2/13.
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.btn_testMap)
    Button btnTestMap;
    @BindView(R.id.btn_testFlatMap)
    Button btnTestFlatMap;
    @BindView(R.id.btn_testGroupBy)
    Button btnTestGroupBy;
    @BindView(R.id.btn_testFilter)
    Button btnTestFilter;

    @BindView(R.id.btn_testOFType)
    Button btnTestOFType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }


    public void testRXAndroid() {
        Log.i(TAG, "testRXAndroid: ");
        final String[] words = {"Hello", "Hi", "Aloha"};
        //创建被观察对象
        Observable observable = Observable.from(words);
        Observable<List<String>> obs1 = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                Log.i(TAG, "call: ");
                subscriber.onNext(Arrays.asList(words));
            }
        });


        //创建观察者/订阅者
        Subscriber<List<String>> subscriber = new Subscriber<List<String>>() {
            @Override
            public void onNext(List<String> strings) {
                for (String str : strings) {
                    Log.i(TAG, "onNext: " + str);
                }
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error!");
            }
        };

        //注册观察者/订阅者
        obs1.subscribe(subscriber);
    }

    public void testMap() {
        Integer[] integers = new Integer[]{1, 3, 5, 7, 8, 9, 2, 4, 6};
        Observable.from(integers)
                .map(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integers) {
                        Log.i(TAG, "call: " + integers);
                        return integers % 2 != 0; //奇数位true，偶数位false。
                    }
                }).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.i(TAG, "onNext: " + aBoolean);
            }
        });
    }

    public void testFlatMap(){
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Chinese", 88));
        courses.add(new Course("English", 88));
        courses.add(new Course("Math", 88));

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student(i+1,courses,"xiaoming");
            students.add(student);
        }

        Student[] stus = (Student[]) students.toArray(new Student[students.size()]);

        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                Log.i(TAG, "call: " + student.getName());
                return Observable.from(student.getCourseList());
            }
        }).subscribe(new Subscriber<Course>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.i(TAG, "onNext: " + course.toString());
            }
        });


    }

    
    public void testGroupBy(){
        Observable.range(1,10).groupBy(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 == 0;
            }
        }).subscribe(new Subscriber<GroupedObservable<Boolean, Integer>>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted1: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GroupedObservable<Boolean, Integer> booleanIntegerGroupedObservable) {
                Log.i(TAG, "onNext1: " + booleanIntegerGroupedObservable.getKey());
                booleanIntegerGroupedObservable.toList().subscribe(new Subscriber<List<Integer>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted2: ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.i(TAG, "onNext2: " + integers);
                    }
                });
            }
        });
    }

    public void testFilter(){
        Observable.range(1,10).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 == 0;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: " + integer);
            }
        });
    }

    public  <T> void testOFType(Class<T> t){
            Observable.just(0,1,"xf","df",3,4,6,"xu","tao",34.03f)
                    .ofType(t)
                    .subscribe(new Subscriber<T>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(T t) {
                            Log.i(TAG, "onNext: " + t);
                        }
                    });




    }


    @OnClick({R.id.btn_test, R.id.btn_testMap, R.id.btn_testFlatMap, R.id.btn_testFilter,R.id.btn_testGroupBy,R.id.btn_testOFType})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                testRXAndroid();
                break;
            case R.id.btn_testMap:
                Log.i(TAG, "onClick: ");
                testMap();
                break;
            case R.id.btn_testFlatMap:
                testFlatMap();
                break;
            case R.id.btn_testFilter:
                testFilter();
                break;

            case R.id.btn_testGroupBy:
                testGroupBy();

            case R.id.btn_testOFType:
                testOFType(Float.class);
            default:
                break;
        }
    }
}


