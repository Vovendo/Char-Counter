package edu.t1consulting.charcounter.service;

import edu.t1consulting.charcounter.model.Text;
import edu.t1consulting.charcounter.service.counter.CharCounter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedHashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        CharCounterServiceImpl.class
})
public class CharCounterServiceImplTest {
    private static final String TEXT_FOR_COUNTING = "abb";
    @Autowired
    private CharCounterService charCounterService;
    @MockBean
    private CharCounter charCounter;

    @Test
    public void charCounterServiceTest() {
        Mockito.when(charCounter.calculateIdenticalCharacters(TEXT_FOR_COUNTING)).thenReturn(new LinkedHashMap<>(Map.of('a', 1, 'b', 2)));

        Text text = new Text(TEXT_FOR_COUNTING);

        LinkedHashMap<Character, Integer> expected = new LinkedHashMap<>(Map.of('a', 1, 'b', 2));
        LinkedHashMap<Character, Integer> actual = charCounterService.calculateIdenticalCharacter(text);

        Mockito.verify(charCounter).calculateIdenticalCharacters(TEXT_FOR_COUNTING);
        Assertions.assertEquals(expected, actual);
    }

}
