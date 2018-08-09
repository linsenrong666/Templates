package com.linsr.common.net;


import com.linsr.common.model.ResponsePojo;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Description
 * @author Linsr
 */
public class RxHelper implements NetConstants {

    public static <T> ObservableTransformer<T, T> ioMain() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<ResponsePojo<T>, T> handleResponse() {
        return new ObservableTransformer<ResponsePojo<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<ResponsePojo<T>> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<ResponsePojo<T>, T>() {
                            @Override
                            public T apply(@NonNull ResponsePojo<T> result) throws Exception {
                                if (result.getCode() == NetConstants.CODE_SUCCESS) {
                                    if (result.getData() != null) {
                                        return result.getData();
                                    } else {
                                        return (T) new Object();
                                    }
                                } else {
                                    throw new ApiException(result.getMsg());
                                }
                            }
                        });
            }
        };

    }

    public static ObservableTransformer<ResponsePojo, ResponsePojo> handleResponse1() {
        return new ObservableTransformer<ResponsePojo, ResponsePojo>() {
            @Override
            public ObservableSource<ResponsePojo> apply(@NonNull Observable<ResponsePojo> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<ResponsePojo, ResponsePojo>() {
                            @Override
                            public ResponsePojo apply(@NonNull ResponsePojo result) throws Exception {
                                if (result.getCode() == NetConstants.CODE_SUCCESS) {
                                    return result;
                                } else {
                                    throw new ApiException(result.getMsg());
                                }
                            }
                        });
            }
        };
    }


}
