package org.tapiok.blogi.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.tapiok.blogi.model.Post;
import org.tapiok.blogi.model.UserEntity;

/**
 * @author Petri Kainulainen
 */
public class TestUtil {

    private static final String CHARACTER = "C";
    public static final MediaType APPLICATION_JSON_UTF8 = 
            new MediaType(
                    MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    Charset.forName("utf8")
            );

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index <= length; index++) {
            builder.append(CHARACTER);
        }
        return builder.toString();
    }

    public static List<Post> getDummyPosts() {
        List<Post> posts = new ArrayList<Post>();
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("seppo666");
        Post post1 = new Post();
        post1.setAuthor(userEntity1);
        post1.setBody("body");
        post1.setComments(null);
        post1.setCreated(new Date(System.currentTimeMillis()));
        post1.setId(1L);
        post1.setTitle("title");
        post1.setUpdated(new Date(System.currentTimeMillis()));

        posts.add(post1);

        return posts;
    }
}
