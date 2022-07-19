package pl.britenet.campus.utils;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Paginator<T> {
    private int page;
    private final int pageSize;
    private final List<T> elementList;
    private final String labels;

    public Paginator(List<T> elementList, int pageSize, String labels) {
        this.elementList = elementList;
        this.pageSize = pageSize - 1;
        this.page = 1;
        this.labels = labels;
    }

    public void Paginate() {
        Scanner scanner = new Scanner(System.in);
        String option = "";
        while(!Objects.equals(option, "/")) {

            if(Objects.equals(option, "+")) this.page++;
            else if(Objects.equals(option, "-")) this.page--;

            int firstIndex = pageSize * (page - 1);
            int lastIndex = Math.min(firstIndex + pageSize, elementList.size() - 1);

            System.out.println("---------------------------PAGE: " + this.page + "---------------------------");
            System.out.println(labels);
            if(firstIndex >= 0 && firstIndex < elementList.size()) {
                if( lastIndex <= elementList.size() - 1 ) {
                    for (int i = firstIndex; i <= lastIndex && i < elementList.size(); i++) {
                        System.out.println(elementList.get(i).toString());
                    }
                }
            }
            System.out.println("           + next page, - previous page, / - stop paginator             ");
            option = scanner.nextLine();
        }
    }
}
