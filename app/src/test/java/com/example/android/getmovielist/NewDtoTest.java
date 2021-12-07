package com.example.android.getmovielist;

import static org.junit.Assert.assertEquals;

import com.google.common.truth.Truth;
import io.github.android.tang.tony.test.util.GsonTestUtil;
import io.github.android.tang.tony.test.util.TestResourcesRule;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NewDtoTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        String jsonString = new TestResourcesRule("mini.json").content();

        NewsDto newsDto = GsonTestUtil.gson().fromJson(jsonString, NewsDto.class);
        Truth.assertThat(newsDto.newsList()).hasSize(1);
        Truth.assertThat(newsDto.totalResults()).isEqualTo(14763);
    }
}