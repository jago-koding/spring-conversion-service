package id.jagokoding;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

public class GenericConverterExample {
    public static void main (String[] args) {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new NumberToBigDecimalConverter());

        BigDecimal bd = service.convert(Double.valueOf("2222.336"),
                            BigDecimal.class);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bd);
    }

    public static class NumberToBigDecimalConverter implements GenericConverter {

        @Override
        public Set<ConvertiblePair> getConvertibleTypes () {
            return Collections.singleton(new ConvertiblePair(Number.class,
                                BigDecimal.class));
        }

        @Override
        public Object convert (Object source, TypeDescriptor sourceType,
                            TypeDescriptor targetType) {
            if (sourceType.getType() == BigDecimal.class) {
                return source;
            }

            Number number = (Number) source;
            return new BigDecimal(number.doubleValue());
        }
    }
}