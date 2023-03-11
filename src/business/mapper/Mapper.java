package business.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<F, T> {
    T map(F from);

    default List<T> mapList(List<F> froms) {
        return froms.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
