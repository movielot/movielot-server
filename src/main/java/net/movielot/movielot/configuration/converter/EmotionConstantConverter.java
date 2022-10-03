package net.movielot.movielot.configuration.converter;

import net.movielot.movielot.enums.EmotionConstant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Locale;

public class EmotionConstantConverter implements Converter<String, EmotionConstant> {
    @Override
    public EmotionConstant convert(String source) {
        return EmotionConstant.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
