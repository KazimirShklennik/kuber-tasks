package com.epam.postservice.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.experimental.UtilityClass;

import java.io.IOException;

@UtilityClass
public class GsonUtils {

    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(new IgnoreFailureTypeAdapterFactory())
                .create();
    }

    public static synchronized Gson getGson() {
        return GSON;
    }

    private static class IgnoreFailureTypeAdapterFactory implements TypeAdapterFactory {

        public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
            return createCustomTypeAdapter(delegate);
        }

        private <T> TypeAdapter<T> createCustomTypeAdapter(TypeAdapter<T> delegate) {
            return new TypeAdapter<>() {
                @Override
                public void write(JsonWriter jsonWriter, T value) throws IOException {
                    delegate.write(jsonWriter, value);
                }

                @Override
                public T read(JsonReader jsonReader) throws IOException {
                    try {
                        return delegate.read(jsonReader);
                    } catch (Exception e) {
                        jsonReader.skipValue();
                        return null;
                    }
                }
            };
        }
    }
}
