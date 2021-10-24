package com.alkemy.disneydemo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;

public class JsonPatchUtils {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Object applyPatch(@NotNull JsonPatch patch, Object o) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(o, JsonNode.class));
        return objectMapper.treeToValue(patched, o.getClass());
    }

}
