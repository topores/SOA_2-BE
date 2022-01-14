package paging;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class Page<DTO> {
    private List<DTO> content;

    private int numberOfElements;
    private long totalPages;
    private long totalElements;
    private long page;

    private boolean last;
    private boolean first;

    private Sort sort;

    public Page(List<DTO> actualContent, int numberOfElements, long totalPages, Integer totalElements, int page) {
        this.content = actualContent;
        this.numberOfElements = numberOfElements;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.page = page;
        this.first = page == 1;
        this.last = page == (totalPages == 0 ? totalPages + 1 : totalPages);
    }

    public static <DTO> Page<DTO> of(List<?> content, Pageable pageable) {
        return (Page<DTO>) of(content, pageable.getPage(), pageable.getSize(), pageable.getSort());
    }

    public static <DTO> Page<DTO> of(List<?> content, int number, int size) {
        List<? extends List<?>> pages = Lists.partition(content, size);
        if (pages.isEmpty()) return emptyPage();
        if (number >= pages.size()) number = pages.size() - 1;
        List<?> actualContent = pages.get(number);

        return new Page<>(
                (List<DTO>) actualContent, actualContent.size(),
                pages.size(), pages.stream().map(List::size).reduce(0, Integer::sum), number + 1);
    }

    public static <DTO> Page<DTO> of(List<DTO> content, int number, int size, Sort sort) {
        if (sort.getFieldName() == null) return of(content, number, size);
        return of(withSort(content, sort), number, size);
    }

    private static <DTO> List<DTO> withSort(List<DTO> content, Sort sort) {
        content.sort((o1, o2) -> {
            try {
                Field left = o1.getClass().getDeclaredField(sort.getFieldName());
                left.setAccessible(true);

                Field right = o2.getClass().getDeclaredField(sort.getFieldName());
                right.setAccessible(true);

                return new CompareToBuilder()
                        .append(left.get(o1), right.get(o2))
                        .toComparison();

            } catch (NoSuchFieldException | IllegalAccessException e) {
                return -1;
            }
        });

        if (sort.getDirection() == Sort.Direction.DESC) Collections.reverse(content);
        return content;
    }

    private static <DTO> Page<DTO> emptyPage() {
        return new Page<>(Collections.emptyList(), 0, 0, 0, 1);
    }
}
