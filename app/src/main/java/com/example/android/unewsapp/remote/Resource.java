package com.example.android.unewsapp.remote;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

public class Resource<T> {
    public static final int INITIAL = 88;
    public static final int ENQUEUED = 89;
    public static final int SUCCESS = 90;
    public static final int LOADING = 91;
    public static final int ERROR = 92;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({INITIAL, SUCCESS, LOADING, ERROR})
    @interface Status {
    }

    public final int status;

    @Nullable
    public ErrorEntity errorEntity;

    @Nullable
    public T data;

    @Nullable
    public Integer errorType;
    @Nullable
    public Object miscInfo;

    public Resource(@Status int status, @Nullable T data, @Nullable ErrorEntity errorEntity) {
        this.status = status;
        this.data = data;
        this.errorEntity = errorEntity;
    }

    public Resource(int status, @Nullable Integer errorType) {
        this.status = status;
        this.errorType = errorType;
    }

    public Resource(int status, @Nullable Integer errorType, ErrorEntity msg) {
        this.status = status;
        this.errorType = errorType;
        this.errorEntity = msg;
    }

    public Resource(int status, @Nullable Object miscInfo) {
        this.status = status;
        this.miscInfo = miscInfo;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> errorWithMisc(@Nullable Object miscInfo) {
        return new Resource<>(ERROR, miscInfo);
    }

    public static <T> Resource<T> error(@Nullable Integer errorType) {
        return new Resource<>(ERROR, errorType);
    }


    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        if (status != resource.status) {
            return false;
        }
        if (!Objects.equals(errorEntity, resource.errorEntity)) {
            return false;
        }
        return Objects.equals(data, resource.data);
    }

    @NotNull
    @Override
    public String toString() {
        return "Resource{" +
                "status=" + status +
                ", searchDataLiveData=" + data +
                '}';
    }
}
