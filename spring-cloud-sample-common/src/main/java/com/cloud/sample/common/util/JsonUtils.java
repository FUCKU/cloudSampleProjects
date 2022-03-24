package com.cloud.sample.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Json-related utilities.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

    /**
     * Converts a given {@link Object} to a {@link JsonObject}.
     *
     * @param obj - {@link Object} to be converted.
     * @return {@link JsonObject} which came out of the given object if the given object nonnull.
     * Otherwise returns empty {@link JsonObject};
     */
    public static JsonObject safeConvertToJsonObject(Object obj) {
        if (obj == null) {
            return new JsonObject();
        }
        String json = new Gson().toJson(obj);
        return JsonParser.parseString(json).getAsJsonObject();
    }

}
